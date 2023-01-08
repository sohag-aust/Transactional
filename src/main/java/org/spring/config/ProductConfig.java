package org.spring.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "org.spring.*") // configure all beans inside spring package or sub packages
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class ProductConfig {

    @Bean("customDataSource")
    public DataSource getDataSource() {
        String connectionString = "jdbc:mysql://localhost:3306/productnew";
        String username = "root";
        String password = "password";
        return new DriverManagerDataSource(connectionString, username, password);
    }

    @Bean("customJdbcTemplate")
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }

    @Bean("customTransactionManager")
    public TransactionManager transactionManager(@Qualifier("customDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
