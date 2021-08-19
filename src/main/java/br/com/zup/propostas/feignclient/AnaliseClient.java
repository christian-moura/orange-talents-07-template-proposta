package br.com.zup.propostas.feignclient;

import br.com.zup.propostas.proposta.analise.AnaliseRequest;
import br.com.zup.propostas.proposta.analise.AnaliseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "analise-financeira-client", url = "${client.avaliacao-financeira.url}")
public interface AnaliseClient {

    @PostMapping(value = "/solicitacao")
    AnaliseResponse solicitarAnalise(AnaliseRequest analiseRequest);
}
