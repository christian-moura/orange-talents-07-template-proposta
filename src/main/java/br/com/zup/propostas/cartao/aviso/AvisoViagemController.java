package br.com.zup.propostas.cartao.aviso;

import br.com.zup.propostas.cartao.Cartao;
import br.com.zup.propostas.feignclient.CartaoClient;
import br.com.zup.propostas.handler.ErrorSingleMessageBody;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/cartao")
public class AvisoViagemController {

    private EntityManager entityManager;
    private CartaoClient cartaoClient;

    public AvisoViagemController(EntityManager entityManager, CartaoClient cartaoClient) {
        this.entityManager = entityManager;
        this.cartaoClient = cartaoClient;
    }

    @Transactional
    @PostMapping("/{id}/aviso-viagem")
    public ResponseEntity<?> avisoViagem(@PathVariable("id") String id,
                                            @RequestHeader(value = "Ip",required = false) String IP, // coloquei como não obrigatório para conseguir mandar a mensagem personalziada na validação abaixo.
                                            @RequestHeader(value = "User-Agent", required = false) String userAgent,
                                            @Valid @RequestBody AvisoViagemRequest avisoViagemRequest){
        Query query = entityManager.createQuery("select c from Cartao c where c.idCartao = :value");
        query.setParameter("value", id);
        if(query.getResultList().isEmpty()) return ResponseEntity.notFound().build();
        Cartao cartao = (Cartao) query.getSingleResult();
        if(IP==null) return ResponseEntity.badRequest().body(new ErrorSingleMessageBody("Ip inexistente no header da requisição."));
        if(userAgent==null) return ResponseEntity.badRequest().body(new ErrorSingleMessageBody("User Agent inexistente no header da requisição."));
        AvisoViagem avisoViagem =  avisoViagemRequest.toAvisoViagem(IP,userAgent,cartao);
        try{
            String destino = avisoViagem.getDestino().toString();
            cartaoClient.avisarViagem(id,new AvisoSistemaLegadoRequest(destino, avisoViagem.getTerminaEm().toString()));
            entityManager.persist(avisoViagem);
            return ResponseEntity.ok().build();
        }
        catch(FeignException e){
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ErrorSingleMessageBody("Não foi possível processar a requisição no momento, tente mais tarde."));
        }
    }
}