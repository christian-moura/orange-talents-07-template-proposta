package br.com.zup.propostas.biometria;

import br.com.zup.propostas.cartao.Cartao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class BiometriaController {

    private EntityManager entityManager;

    public BiometriaController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @PostMapping("/biometria/{id}")
        public ResponseEntity<?> cadastrarBiometria(@PathVariable("id") String id, @Valid @RequestBody BiometriaRequest biometriaRequest){
        Query query = entityManager.createQuery("select c from Cartao c where c.idCartao = :value");
        query.setParameter("value", id);
        if(query.getResultList().isEmpty()) return ResponseEntity.notFound().build();
        Cartao cartao = (Cartao) query.getSingleResult();
        Biometria biometria = biometriaRequest.toBiometria(cartao);
        entityManager.persist(biometria);
        URI uri = UriComponentsBuilder.fromUriString("/api/biometria/{id}").buildAndExpand((biometria.getId())).toUri();
        return ResponseEntity.created(uri).build();
    }
}


