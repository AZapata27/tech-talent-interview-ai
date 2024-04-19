package co.com.flypass.jpa.mysql.query.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "mysqlEntityManagerFactory",
        transactionManagerRef = "mysqlTransactionManager",
        basePackages = {
                "co.com.flypass.jpa.mysql.query.repositories"
        }
)
public class JpaConfigMySql {
    @Value("${spring.datasource.mysql.read.driverClassName}")
    private String driverClass;
    @Value("${spring.datasource.mysql.read.username}")
    private String username;
    @Value("${spring.datasource.mysql.read.password}")
    private String password;
    @Value("${spring.jpa.mysql.read.databasePlatform}")
    private String dialect;
    @Value("${spring.datasource.mysql.read.url}")
    private String url;
    @Bean
    public DataSource mysqlDataSource()
    {
        return DataSourceBuilder.create()
                .driverClassName(driverClass)
                .url(url)
                .username(username)
                .password(password)
                .build();
    }

    @Bean("mysqlEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("mysqlDataSource")  DataSource dataSource
            ) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("co.com.flypass.jpa.mysql.query.entities");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", dialect);
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        em.setJpaProperties(properties);

        return em;
    }

    @Bean("mysqlTransactionManager")
    @Primary
    public PlatformTransactionManager transactionManager(
            @Qualifier("mysqlEntityManagerFactory")  EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
