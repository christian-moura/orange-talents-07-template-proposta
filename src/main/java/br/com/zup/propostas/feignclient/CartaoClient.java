package br.com.zup.propostas.feignclient;


import br.com.zup.propostas.cartao.CartaoRequest;
import br.com.zup.propostas.cartao.CartaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "accounts-client", url = "${client.accounts.url}")
public interface CartaoClient {

    @PostMapping(value = "/cartoes")
    CartaoResponse solicitarCartao(CartaoRequest cartaoRequest);
}