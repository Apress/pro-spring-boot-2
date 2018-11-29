package com.apress.todo.config;


import com.apress.todo.domain.ToDo;
import com.apress.todo.repository.ToDoRepository;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@EnableJpaRepositories(
        entityManagerFactoryRef = "todoEntityManager",
        transactionManagerRef = "todoTransactionManager",
        basePackageClasses = {ToDoRepository.class})
@Configuration
public class ToDoConfig {

    private final PersistenceUnitManager persistenceUnitManager;

    public ToDoConfig(ObjectProvider<PersistenceUnitManager> persistenceUnitManager) {
        this.persistenceUnitManager = persistenceUnitManager.getIfAvailable();
    }

    @Bean
    @ConfigurationProperties("todo.jpa")
    public JpaProperties todoJpaProperties() {
        return new JpaProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("todo.datasource")
    public DataSourceProperties todoDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "todo.datasource.properties")
    public DataSource todoDataSource() {
        return (DataSource)todoDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean todoEntityManager(
            JpaProperties todoJpaProperties) {
        EntityManagerFactoryBuilder builder = createEntityManagerFactoryBuilder(
                todoJpaProperties);
        return builder
                .dataSource(todoDataSource())
                .packages(ToDo.class)
                .persistenceUnit("todoDs")
                .build();
    }

    @Bean
    @Primary
    public JpaTransactionManager todoTransactionManager(
            EntityManagerFactory todoEntityManager) {
        return new JpaTransactionManager(todoEntityManager);
    }

    private EntityManagerFactoryBuilder createEntityManagerFactoryBuilder(
            JpaProperties todoJpaProperties) {
        JpaVendorAdapter jpaVendorAdapter = createJpaVendorAdapter(todoJpaProperties);
        return new EntityManagerFactoryBuilder(jpaVendorAdapter,
                todoJpaProperties.getProperties(), this.persistenceUnitManager);
    }

    private JpaVendorAdapter createJpaVendorAdapter(JpaProperties jpaProperties) {
        AbstractJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(jpaProperties.isShowSql());
        adapter.setDatabase(jpaProperties.getDatabase());
        adapter.setDatabasePlatform(jpaProperties.getDatabasePlatform());
        adapter.setGenerateDdl(jpaProperties.isGenerateDdl());
        return adapter;
    }

}
