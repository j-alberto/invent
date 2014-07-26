package org.jar.invent.config;

import javax.sql.DataSource;

import org.jar.invent.config.interceptor.AuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource datasource;
	
	private static final String QUERY_AUTH_BY_UNAME = "select u.name as 'username', a.name as 'authority' "
			+ "from sys_user u, sys_group g, sys_user_group ug, sys_authority a, sys_group_authority ga "
			+ "where u.id = ug.iduser"
			+ "  and g.id = ug.idgroup"
			+ "  and g.id = ga.idgroup"
			+ "  and ga.idauthority = a.id"
			+ "  and u.name = ?";
	
	private static final String QUERY_GROUP_AUTH_BY_UNAME = "select g.id as 'id', g.name "
			+ ", a.name as 'authority' "
			+ "from sys_user u, sys_group g, sys_user_group ug, sys_authority a, sys_group_authority ga "
			+ "where u.id = ug.iduser"
			+ "  and g.id = ug.idgroup"
			+ "  and g.id = ga.idgroup"
			+ "  and ga.idauthority = a.id"
			+ "  and u.name = ?";
	
	private static final String QUERY_USER_BY_UNAME = "select u.name as 'username', u.password, u.enabled "
			+ "from sys_user u "
			+ "where u.name = ?";
	
	
	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}

	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .jdbcAuthentication()
                .dataSource(datasource)
                .groupAuthoritiesByUsername(QUERY_GROUP_AUTH_BY_UNAME)
                .authoritiesByUsernameQuery(QUERY_AUTH_BY_UNAME)
                .usersByUsernameQuery(QUERY_USER_BY_UNAME);

    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
        	.antMatchers("/css/**", "/js/**","/error/**").permitAll()
        .anyRequest().authenticated() //we want any request be made by authenticated user
            .and()
        .formLogin()
            .loginPage("/login").permitAll()
            .and()
            .logout().permitAll(); //we want login page to be accessible by everyone
    }
    
/**
 * Creates and adapter bean in charge of providing credentials to Views
 * @return a WebMvcConfigurerAdapter 
 */
    @Bean
    public WebMvcConfigurerAdapter webAdapter(){
    	WebMvcConfigurerAdapter confAdapter = new WebMvcConfigurerAdapter() {
    		@Override
    		public void addInterceptors(InterceptorRegistry registry) {
    			registry.addInterceptor(new AuthenticationInterceptor());
    			super.addInterceptors(registry);
    		}
    		
		};
		
		return confAdapter;
    }
    
    
}