package org.jar.invent.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="org.pke.liberalbus.domain.dao")
@PropertySource(value="classpath:hibernate.properties")
public class PersistanceJPAConfig {
	
    @Value("${hibernate.connection.driver_class}")
    private String className;
    @Value("${hibernate.connection.url}")
    private String url;
    @Value("${hibernate.connection.username}")
    private String username;
    @Value("${hibernate.connection.password}")
    private String password;
	
    @Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
	      em.setDataSource(dataSource());
	      em.setPackagesToScan("org.pke.liberalbus.domain");
	      em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
	 
	      return em;
	}
	

	@Bean
	   public DataSource dataSource(){
	      DriverManagerDataSource dataSource = new DriverManagerDataSource();
	      dataSource.setDriverClassName(className);
	      dataSource.setUrl(url);
	      dataSource.setUsername(username);
	      dataSource.setPassword(password);
	      return dataSource;
	   }
	
//	@Bean
//	   public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
//	      JpaTransactionManager transactionManager = new JpaTransactionManager();
//	      transactionManager.setEntityManagerFactory(entityManagerFactory);
//	 
//	      return transactionManager;
//	   }
//	
//	 @Bean
//	   public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
//	      return new PersistenceExceptionTranslationPostProcessor();
//	   }

}
