package ua.com.alevel.config.jpa;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "ua.com.alevel.persistence.repository")
public class HibernateConfig {

    private static final String HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String MAX_SIZE = "hibernate.c3p0.max_size";
    private static final String MIN_SIZE = "hibernate.c3p0.min_size";
    private static final String BATCH_SIZE = "hibernate.jdbc.batch_size";
    private static final String FETCH_SIZE = "hibernate.jdbc.fetch_size";
    private static final String ENTITY_MANAGER_PACKAGES_TO_SCAN = "ua.com.alevel.persistence.entity";
    private static final String NEW_GENERATOR_MAPPINGS = "hibernate.id.new_generator_mappings";

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String dialect;

    @Value("${spring.jpa.properties.hibernate.id.new_generator_mappings}")
    private String generatorId;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String hbm2ddl;

    @Value("${spring.jpa.show-sql}")
    private Boolean showSql;

    @Value("${spring.jpa.properties.hibernate.jdbc.max_size}")
    private String maxSize;

    @Value("${spring.jpa.properties.hibernate.jdbc.min_size}")
    private String minSize;

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private String batchSize;

    @Value("${spring.jpa.properties.hibernate.jdbc.fetch_size}")
    private String fetchSize;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Autowired
    @Bean(name="entityManagerFactory")
    public LocalSessionFactoryBean sessionFactoryBean(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setPackagesToScan(ENTITY_MANAGER_PACKAGES_TO_SCAN);
        sessionFactoryBean.setHibernateProperties(hibernateProperties());
        try {
            sessionFactoryBean.afterPropertiesSet();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sessionFactoryBean;
    }

    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put(HIBERNATE_DIALECT, dialect);
        properties.put(HIBERNATE_HBM2DDL_AUTO, hbm2ddl);
        properties.put(HIBERNATE_SHOW_SQL, showSql);
        properties.put(MAX_SIZE, maxSize);
        properties.put(MIN_SIZE, minSize);
        properties.put(BATCH_SIZE, batchSize);
        properties.put(FETCH_SIZE, fetchSize);
        properties.put(NEW_GENERATOR_MAPPINGS, generatorId);
        return properties;
    }
}

