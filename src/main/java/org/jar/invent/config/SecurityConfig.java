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
	
	private static final String QUERY_AUTH_BY_UNAME = "select u.user_name as 'username', a.authority_name as 'authority' "
			+ "from USER u, `GROUP` g, GROUP_MEMBER gm, AUTHORITY a, GROUP_AUTHORITY ga "
			+ "where u.user_id = gm.user_fk"
			+ "  and g.group_id = gm.group_fk"
			+ "  and g.group_id = ga.group_fk"
			+ "  and ga.authority_fk = a.authority_id"
			+ "  and u.user_name = ?";
	
	private static final String QUERY_GROUP_AUTH_BY_UNAME = "select g.group_id as 'id', g.group_name "
			+ ", a.authority_name as 'authority' "
			+ "from USER u, `GROUP` g, GROUP_MEMBER gm, AUTHORITY a, GROUP_AUTHORITY ga "
			+ "where u.user_id = gm.user_fk"
			+ "  and g.group_id = gm.group_fk"
			+ "  and g.group_id = ga.group_fk"
			+ "  and ga.authority_fk = a.authority_id"
			+ "  and u.user_name = ?";
	
	private static final String QUERY_USER_BY_UNAME = "select u.user_name as 'username', u.password, u.enabled "
			+ "from USER u "
			+ "where u.user_name = ?";
	
	
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