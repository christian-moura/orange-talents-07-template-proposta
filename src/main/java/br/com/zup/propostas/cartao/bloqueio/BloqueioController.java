package br.com.zup.propostas.cartao.bloqueio;

import br.com.zup.propostas.cartao.Cartao;
import br.com.zup.propostas.handler.ErrorSingleMessageBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

@RestController
@RequestMapping("/api/cartao")
public class BloqueioController {

    private EntityManager entityManager;

    public BloqueioController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @PatchMapping("/{id}/bloqueio")
    public ResponseEntity<?> bloquearCartao(@PathVariable("id") String id,
                                                @RequestHeader(value = "IP",required = false) String IP, // coloquei como não obrigatório para conseguir mandar a mensagem personalziada na validação abaixo.
                                                @RequestHeader(value = "User-Agent", required = false) String userAgent){
        Query query = entityManager.createQuery("select c from Cartao c where c.idCartao = :value");
        query.setParameter("value", id);
        if(query.getResultList().isEmpty()) return ResponseEntity.notFound().build();
        Cartao cartao = (Cartao) query.getSingleResult();
        if(cartao.getBloqueio() != null) return ResponseEntity.unprocessableEntity().body(new ErrorSingleMessageBody("Este cartão já está bloqueado."));
        if(IP==null) return ResponseEntity.badRequest().body(new ErrorSingleMessageBody("Ip inexistente no header da requisição."));
        if(userAgent==null) return ResponseEntity.badRequest().body(new ErrorSingleMessageBody("User Agent inexistente no header da requisição."));
        Bloqueio bloqueio = new Bloqueio(IP,userAgent,cartao);
        entityManager.persist(bloqueio);
        return ResponseEntity.ok().build();
    }
}
