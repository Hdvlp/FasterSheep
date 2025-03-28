package com.fastersheep.fastersheep.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class ActuatorConfig {

    @Value("${fasterSheepDataSourceDriverClassName}")
    private String fasterSheepDataSourceDriverClassName;

    @Value("${fasterSheepDataSourceUrl}")
    private String fasterSheepDataSourceUrl;

    @Value("${fasterSheepDataSourceUsername}")
    private String fasterSheepDataSourceUsername;
    
    @Value("${fasterSheepDataSourcePassword}")
    private String fasterSheepDataSourcePassword;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(fasterSheepDataSourceDriverClassName);
        dataSource.setUrl(fasterSheepDataSourceUrl);
        dataSource.setUsername(fasterSheepDataSourceUsername);
        dataSource.setPassword(fasterSheepDataSourcePassword);
        return dataSource;
    }
}
