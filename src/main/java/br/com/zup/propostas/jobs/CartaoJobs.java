package br.com.zup.propostas.jobs;

import br.com.zup.propostas.cartao.Cartao;
import br.com.zup.propostas.cartao.CartaoEstado;
import br.com.zup.propostas.cartao.CartaoRequest;
import br.com.zup.propostas.cartao.CartaoResponse;
import br.com.zup.propostas.cartao.bloqueio.BloqueioRequest;
import br.com.zup.propostas.cartao.bloqueio.BloqueioResponse;
import br.com.zup.propostas.cartao.bloqueio.BloqueioStatus;
import br.com.zup.propostas.compartilhado.ExecutorTransacao;
import br.com.zup.propostas.feignclient.CartaoClient;
import br.com.zup.propostas.proposta.Proposta;
import br.com.zup.propostas.proposta.PropostaEstado;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Component
@EnableScheduling
public class CartaoJobs {

    Logger log = LoggerFactory.getLogger(CartaoJobs.class);
    private final ExecutorTransacao executorTransacao;
    private final CartaoClient cartaoClient;

    @Autowired
    public CartaoJobs(ExecutorTransacao executorTransacao, CartaoClient cartaoClient) {
        this.executorTransacao = executorTransacao;
        this.cartaoClient = cartaoClient;
    }

    //@Scheduled(fixedRate = 5000)
    private void associarCartaoJob(){
        log.info("Tarefa de vinculação de cartões");
        EntityManager entityManager = executorTransacao.getManager();
        Query query = entityManager.createQuery("select p from Proposta p where  p.estado =:estado and p.cartao is empty");
        query.setParameter("estado", PropostaEstado.ELEGIVEL);
        List<Proposta> listaPropostas = query.getResultList();
        listaPropostas.forEach(proposta -> {
            try {
                CartaoResponse cartaoResponse = cartaoClient
                        .solicitarCartao(
                                new CartaoRequest(proposta.getId().toString(), proposta.getDocumento(), proposta.getNome()));
                Cartao cartao = cartaoResponse.toCartao(proposta);
                executorTransacao.salvar(cartao);
                log.info("Sucesso ao vincular cartão para proposta: " + proposta.getId());
            } catch (FeignException ex) {
                log.error("Falha ao vincular cartão para proposta: " + proposta.getId());
            }
        });
    }

   // @Scheduled(fixedRate = 5000)
    private void bloquearCartaoJob(){
        log.info("Tarefa de notificar o sistema legado sobre bloqueios de cartão");
        EntityManager entityManager = executorTransacao.getManager();
        Query query = entityManager.createQuery("select c from Cartao c where  c.estado =:estado");
        query.setParameter("estado", CartaoEstado.AGUARDANDO_BLOQUEIO);
        List<Cartao> listaCartao = query.getResultList();
        listaCartao.forEach(cartao -> {
            try {
               BloqueioResponse bloqueioResponse = cartaoClient
                        .bloquearCartao(cartao.getIdCartao(), new BloqueioRequest("propostas"));
                if(bloqueioResponse.getBloqueioStatus().equals(BloqueioStatus.BLOQUEADO)){
                    cartao.bloqueadoNoLegado();
                    executorTransacao.salvar(cartao);
                    log.info("Sucesso ao bloquear cartão "+ cartao.getIdCartao()+" no sistema legado");
                }
                else if(bloqueioResponse.getBloqueioStatus().equals(BloqueioStatus.FALHA)){
                    log.error("Falha ao bloquear cartão "+ cartao.getIdCartao()+" no sistema legado");
                }
            } catch (FeignException ex) {
                log.error("Falha ao comunicar com sistema legado de cartão");
            }
        });
    }
}
