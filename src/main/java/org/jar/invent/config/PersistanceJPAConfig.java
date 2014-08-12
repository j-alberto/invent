package org.jar.invent.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.QueryLookupStrategy.Key;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource(value="classpath:hibernate.properties")
@EnableSpringDataWebSupport
//Allows creation of proxy instances for repository(DAO) interfaces
@EnableJpaRepositories(basePackages="org.jar.invent.core.domain.dao" //where to look for repositories
						,queryLookupStrategy=Key.CREATE_IF_NOT_FOUND)//default strategy 
/**
 * Configuration of JPA repository (database)
 * @author zero
 *
 */
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
	      em.setPackagesToScan("org.jar.invent.core.domain");
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

}
