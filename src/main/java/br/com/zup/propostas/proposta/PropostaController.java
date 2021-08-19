package br.com.zup.propostas.proposta;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class PropostaController {

    private EntityManager entityManager;

    public PropostaController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @PostMapping("/proposta")
    public ResponseEntity<?> cadastroProposta(@Valid @RequestBody PropostaRequest propostaRequest){
        Proposta proposta = propostaRequest.toProposta(entityManager);
        entityManager.persist(proposta);
        URI uri = UriComponentsBuilder.fromUriString("/api/proposta/{id}").buildAndExpand((proposta.getId())).toUri();
        return ResponseEntity.created(uri).build();
    }
}
