package br.com.zup.propostas.proposta;

import br.com.zup.propostas.feignclient.AnaliseClient;
import br.com.zup.propostas.proposta.analise.AnaliseRequest;
import br.com.zup.propostas.proposta.analise.AnaliseResponse;
import io.opentracing.Tracer;
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
    private final Tracer tracer;


    @Autowired
    public PropostaController(EntityManager entityManager, AnaliseClient analiseClient, Tracer tracer) {
        this.entityManager = entityManager;
        this.analiseClient = analiseClient;
        this.tracer = tracer;
    }



    @Transactional
    @PostMapping("/proposta")
    public ResponseEntity<?> cadastroProposta(@Valid @RequestBody PropostaRequest propostaRequest){
        tracer.activeSpan().setTag("user.email", "christian@zup.com.br");
        Proposta proposta = propostaRequest.toProposta(entityManager);
        entityManager.persist(proposta);
        AnaliseResponse analiseResponse = analiseClient.solicitarAnalise(new AnaliseRequest(proposta));
        proposta.adicionaEstado(analiseResponse);
        tracer.activeSpan().setTag("operation.status", "success");
        tracer.activeSpan().setBaggageItem("proposta.id", proposta.getId().toString());
        URI uri = UriComponentsBuilder.fromUriString("/api/proposta/{id}").buildAndExpand((proposta.getId())).toUri();
        return ResponseEntity.created(uri).build();
    }
}
