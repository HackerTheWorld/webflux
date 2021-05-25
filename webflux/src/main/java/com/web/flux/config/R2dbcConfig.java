package com.web.flux.config;

import java.time.Duration;

import io.r2dbc.mssql.MssqlConnection;
import io.r2dbc.mssql.MssqlConnectionConfiguration;
import io.r2dbc.mssql.MssqlConnectionFactory;
import io.r2dbc.mssql.MssqlConnectionFactoryProvider;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.pool.PoolingConnectionFactoryProvider;
import reactor.core.publisher.Mono;

@Configuration
@EnableR2dbcRepositories
public class R2dbcConfig extends AbstractR2dbcConfiguration{

    @Override
    public ConnectionFactory connectionFactory() {

        MssqlConnectionConfiguration configuration = MssqlConnectionConfiguration.builder()
                .host("10.178.11.21")
                .username("o-aps")
                .password("o-aps.aac")
                .database("jurisdiction")
                .build();
        MssqlConnectionFactory factory = new MssqlConnectionFactory(configuration);

        ConnectionPoolConfiguration poolConfiguration = ConnectionPoolConfiguration.builder(factory)
            .maxIdleTime(Duration.ofMinutes(30))
            .initialSize(10)
            .maxSize(100)
            .maxCreateConnectionTime(Duration.ofSeconds(100))
            .build();
        return new ConnectionPool(poolConfiguration);
    }
    
}
