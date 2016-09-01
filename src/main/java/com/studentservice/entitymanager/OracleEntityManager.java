/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.studentservice.entitymanager;

import java.sql.Driver;
import java.util.Properties;
import javax.sql.DataSource;
import static org.hibernate.jpa.AvailableSettings.SCHEMA_GEN_DATABASE_ACTION;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Manish
 */
@Configuration
@EnableTransactionManagement
public class OracleEntityManager {
    
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String[] ENTITYMANAGER_PACKAGES_TO_SCAN = {"com.studentservice"};
    
    @Autowired
    private Environment env;
    
    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        try {
            dataSource.setDriver((Driver)Class.forName(env.getProperty("jdbc.driverClassName")).newInstance());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            System.err.println("Exception Occured"+ex);
        }
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        return dataSource;
    }
    
    @Bean
    public JpaTransactionManager jpaTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return transactionManager;
    }
    
    private HibernateJpaVendorAdapter vendorAdaptor() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        return vendorAdapter;
    }
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdaptor());
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);
        entityManagerFactoryBean.setJpaProperties(jpaHibernateProperties());
        
        return entityManagerFactoryBean;
    }
    
    private Properties jpaHibernateProperties() {
        
        Properties properties = new Properties();
        properties.put("spring.jpa.show-sql", env.getProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
        properties.put("spring.jpa.database-platform","org.hibernate.dialect.Oracle10gDialect");
        properties.put("spring.jpa.hibernate.ddl-auto","none");
        properties.put(SCHEMA_GEN_DATABASE_ACTION, "none");
        return properties;
    }
    
}
