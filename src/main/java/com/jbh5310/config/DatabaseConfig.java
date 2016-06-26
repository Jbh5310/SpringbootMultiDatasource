package com.jbh5310.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jbh5310 on 2016-06-26.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
public class DatabaseConfig {
    private static final String DEFAULT_NAMING_STRATEGY
            = "org.springframework.boot.orm.jpa.hibernate.SpringNamingStrategy";

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "datasource.article")
    public DataSource articleDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder entityBuilder) {

        Map<String, String> propertiesHashMap = new HashMap<>();
        propertiesHashMap.put("hibernate.ejb.naming_strategy",DEFAULT_NAMING_STRATEGY);

        return entityBuilder.dataSource(articleDataSource())
                .packages("com.jbh5310.domain.db1")
                .properties(propertiesHashMap)
                .build();
    }

    @Primary
    @Bean(name = "transactionManager")
    PlatformTransactionManager transactionManager(
            EntityManagerFactoryBuilder entityBuilder) {
        return new JpaTransactionManager(entityManagerFactory(entityBuilder).getObject());
    }

    @Configuration
    @EnableJpaRepositories(
            basePackages="com.jbh5310.repositories.db1",
            entityManagerFactoryRef = "entityManagerFactory",
            transactionManagerRef = "transactionManager")
    static class DbArticleJpaRepositoriesConfig {
    }


    @Bean
    @ConfigurationProperties(prefix = "datasource.user")
    public DataSource userDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "entityManagerFactoryUser")
    public LocalContainerEntityManagerFactoryBean userEntityManagerFactory(
            EntityManagerFactoryBuilder entityBuilder) {

        return entityBuilder.dataSource(userDataSource())
                .packages("com.jbh5310.domain.db2")
                .build();
    }

    @Bean(name = "transactionManagerUser")
    @Primary
    PlatformTransactionManager userTransactionManagerMain(
            EntityManagerFactoryBuilder entityBuilder) {
        return new JpaTransactionManager(userEntityManagerFactory(entityBuilder).getObject());
    }

    @Configuration
    @EnableJpaRepositories(
            basePackages="com.jbh5310.repositories.db2",
            entityManagerFactoryRef = "entityManagerFactoryUser",
            transactionManagerRef = "transactionManagerUser")
    static class DbUserJpaRepositoriesConfig {
    }
}
