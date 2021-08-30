package br.com.zup.propostas.config.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.net.Socket;
import java.net.URL;

@Component("apiFinaceira")
public class ApiFinanceiroHealthContributor implements HealthIndicator {
    private static final String url ="http://localhost:9999/api/solicitacao";

    @Override
    public Health health() {
        try (Socket socket= new Socket(new URL(url).getHost(),9999)){
        } catch (Exception e){
            return Health.down().withDetail("error",e.getMessage()).build();
        }
        return Health.up().build();
    }
}
