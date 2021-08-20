package br.com.zup.propostas.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Component("databasePostgres")
public class DatabaseHealthContributor
        implements HealthIndicator {

    @Autowired
    private DataSource dataSource;

    @Override
    public Health health() {
        try(Connection connection = dataSource.getConnection()){
            Statement statement = connection.createStatement();
            statement.execute("select count(*) from proposta");
        } catch (SQLException ex) {
            return Health.outOfService().withException(ex).build();
        }
        return Health.up().build();
    }
}