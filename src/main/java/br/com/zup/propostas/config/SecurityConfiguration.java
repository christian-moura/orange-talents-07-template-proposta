package br.com.zup.propostas.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests -> authorizeRequests
                .antMatchers(HttpMethod.GET, "/api/proposta/**").hasAuthority("SCOPE_propostas_principal")
                .antMatchers(HttpMethod.POST, "/api/proposta/**").hasAuthority("SCOPE_propostas_principal")
                .antMatchers(HttpMethod.GET, "/api/biometria/**").hasAuthority("SCOPE_propostas_principal")
                .anyRequest().authenticated()
        ).oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }
}