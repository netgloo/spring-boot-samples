package coderpills.configs;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Class DatabaseConfig
 * <br/>
 * Contains the database configurations.
 */
@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

  // ==============
  // PRIVATE FIELDS
  // ==============
  
  @Autowired
  private Environment _env;

  @Autowired
  private DataSource _dataSource;

  @Autowired
  private LocalContainerEntityManagerFactoryBean _entityManagerFactory;

  // ==============
  // PUBLIC METHODS
  // ==============

  /**
   * Method dataSource
   * <br />
   * DataSource definition for database connection.
   */
  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(_env.getProperty("db.driver"));
    dataSource.setUrl(_env.getProperty("db.url"));
    dataSource.setUsername(_env.getProperty("db.username"));
    dataSource.setPassword(_env.getProperty("db.password"));
    return dataSource;
  }

  /**
   * Method entityManagerFactory
   * <br />
   * Declare the JPA entity manager factory.
   */
  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean entityManagerFactory =
        new LocalContainerEntityManagerFactoryBean();
    
    entityManagerFactory.setDataSource(_dataSource);
    
    // Classpath scanning of @Component, @Service, etc annotated class
    entityManagerFactory.setPackagesToScan(
        _env.getProperty("entitymanager.packagesToScan"));
    
    // Vendor adapter
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
    
    // Hibernate properties
    Properties additionalProperties = new Properties();
    additionalProperties.put(
        "hibernate.dialect", 
        _env.getProperty("hibernate.dialect"));
    additionalProperties.put(
        "hibernate.show_sql", 
        _env.getProperty("hibernate.show_sql"));
    additionalProperties.put(
        "hibernate.hbm2ddl.auto", 
        _env.getProperty("hibernate.hbm2ddl.auto"));
    entityManagerFactory.setJpaProperties(additionalProperties);
    
    return entityManagerFactory;
  }

  /**
   * Method transactionManager
   * <br />
   * Declare the transaction manager.
   */
  @Bean
  public JpaTransactionManager transactionManager() {
    JpaTransactionManager transactionManager = 
        new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(
        _entityManagerFactory.getObject());
    return transactionManager;
  }
  
  /**
   * Method persistenceExceptionTranslationPostProcessor
   * <br />
   * PersistenceExceptionTranslationPostProcessor is a bean post processor 
   * which adds an advisor to any bean that's annotated with \@Repository so
   * that any platform-specific exceptions are caught and then rethrown as one
   * Spring's unchecked data access exceptions (i.e. a subclass of 
   * DataAccessException).
   */
  @Bean
  public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
    return new PersistenceExceptionTranslationPostProcessor();
  }

} // class DatabaseConfig
