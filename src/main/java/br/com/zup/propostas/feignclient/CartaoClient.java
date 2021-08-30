package br.com.zup.propostas.feignclient;


import br.com.zup.propostas.cartao.CartaoRequest;
import br.com.zup.propostas.cartao.CartaoResponse;
import br.com.zup.propostas.cartao.aviso.AvisoSistemaLegadoRequest;
import br.com.zup.propostas.cartao.aviso.AvisoSistemaLegadoResponse;
import br.com.zup.propostas.cartao.bloqueio.BloqueioRequest;
import br.com.zup.propostas.cartao.bloqueio.BloqueioResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "accounts-client", url = "${client.accounts.url}")
public interface CartaoClient {

    @PostMapping(value = "/cartoes")
    CartaoResponse solicitarCartao(CartaoRequest cartaoRequest);

    @PostMapping(value = "/cartoes/{id}/bloqueios")
    BloqueioResponse bloquearCartao(@PathVariable String id, @RequestBody BloqueioRequest bloqueioRequest);

    @PostMapping(value = "/cartoes/{id}/avisos")
    AvisoSistemaLegadoResponse avisarViagem(@PathVariable String id, @RequestBody AvisoSistemaLegadoRequest avisoSistemaLegadoRequest);
}
