package br.com.zup.propostas.proposta;

import br.com.zup.propostas.feignclient.AnaliseClient;
import br.com.zup.propostas.proposta.analise.AnaliseRequest;
import br.com.zup.propostas.proposta.analise.AnaliseResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
    private AnaliseClient analiseClient;

    @Autowired
    public PropostaController(EntityManager entityManager, AnaliseClient analiseClient) {
        this.entityManager = entityManager;
        this.analiseClient = analiseClient;
    }

    @Transactional
    @PostMapping("/proposta")
    public ResponseEntity<?> cadastroProposta(@Valid @RequestBody PropostaRequest propostaRequest){
        Proposta proposta = propostaRequest.toProposta(entityManager);
        entityManager.persist(proposta);
        AnaliseResponse analiseResponse = analiseClient.solicitarAnalise(new AnaliseRequest(proposta));
        proposta.adicionaEstado(analiseResponse);
        URI uri = UriComponentsBuilder.fromUriString("/api/proposta/{id}").buildAndExpand((proposta.getId())).toUri();
        return ResponseEntity.created(uri).build();
    }
}
