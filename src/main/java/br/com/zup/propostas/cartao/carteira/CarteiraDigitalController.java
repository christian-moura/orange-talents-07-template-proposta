package br.com.zup.propostas.cartao.carteira;

import br.com.zup.propostas.cartao.Cartao;
import br.com.zup.propostas.feignclient.CartaoClient;
import br.com.zup.propostas.handler.ErrorSingleMessageBody;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/cartao")
public class CarteiraDigitalController {

    private EntityManager entityManager;
    private CartaoClient cartaoClient;

    public CarteiraDigitalController(EntityManager entityManager, CartaoClient cartaoClient) {
        this.entityManager = entityManager;
        this.cartaoClient = cartaoClient;
    }

    @Transactional
    @PostMapping("/{id}/carteira")
    public ResponseEntity<?> vincularCarteiraDigital(@PathVariable("id") String id,
                                            @Valid @RequestBody CarteiraDigitalRequest carteiraDigitalRequest){
        Query consultaCartao = entityManager.createQuery("select c from Cartao c where c.idCartao = :value");
        consultaCartao.setParameter("value", id);
        if(consultaCartao.getResultList().isEmpty()) return ResponseEntity.notFound().build();
        Cartao cartao = (Cartao) consultaCartao.getSingleResult();
        Query consultaCartaoCarteira = entityManager.createQuery("select c from CarteiraDigital c join c.cartao ca where ca.idCartao = :cartao and c.carteira = :carteira");
        consultaCartaoCarteira.setParameter("cartao", id);
        consultaCartaoCarteira.setParameter("carteira", carteiraDigitalRequest.getCarteira());
        if(!consultaCartaoCarteira.getResultList().isEmpty()) return ResponseEntity.unprocessableEntity()
                .body(new ErrorSingleMessageBody("Este cartão já está vinculado à uma carteira: " + carteiraDigitalRequest.getCarteira()+"."));
        CarteiraDigital carteiraDigital = carteiraDigitalRequest.toCarteiraDigital(cartao);
        try{
            CarteiraDigitalSistemaLegadoRequest carteiraDigitalSistemaLegadoRequest =
                    new CarteiraDigitalSistemaLegadoRequest(
                            carteiraDigital.getCarteira().toString(),carteiraDigital.getEmail());
            cartaoClient.vincularCarteira(id, carteiraDigitalSistemaLegadoRequest);
            entityManager.persist(carteiraDigital);
            URI uri = UriComponentsBuilder.fromUriString("/api/carteira/{id}").buildAndExpand((carteiraDigital.getId())).toUri();
            return ResponseEntity.created(uri).build();
        }
        catch(FeignException e){
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ErrorSingleMessageBody("Não foi possível processar a requisição no momento, tente mais tarde."));
        }
    }
}
