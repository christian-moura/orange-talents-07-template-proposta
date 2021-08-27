package br.com.zup.propostas.jobs;

import br.com.zup.propostas.cartao.Cartao;
import br.com.zup.propostas.cartao.CartaoRequest;
import br.com.zup.propostas.cartao.CartaoResponse;
import br.com.zup.propostas.compartilhado.ExecutorTransacao;
import br.com.zup.propostas.feignclient.CartaoClient;
import br.com.zup.propostas.proposta.Proposta;
import br.com.zup.propostas.proposta.PropostaEstado;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
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

    public CartaoJobs(ExecutorTransacao executorTransacao, CartaoClient cartaoClient) {
        this.executorTransacao = executorTransacao;
        this.cartaoClient = cartaoClient;
    }

    @Scheduled(fixedRate = 5000)
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
}
