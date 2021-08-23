package br.com.zup.propostas.proposta;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;

@RestController
@RequestMapping("/api")
public class ConsultaPropostaController {

    private EntityManager entityManager;

    public ConsultaPropostaController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @GetMapping("/proposta/{id}")
    public ResponseEntity<?> buscarPropostaPorID(@PathVariable("id") Long idProposta){
        Proposta proposta = entityManager.find(Proposta.class, idProposta);
        if(proposta==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(new ConsultaPropostaResponse(proposta));
    }
}
