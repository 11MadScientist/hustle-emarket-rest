package org.emarket.hustle.emarkethustle.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter
{
//	@Autowired
//	UserDetailServiceImpl userDetailService;

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception
//	{
//		auth.userDetailsService(userDetailService);
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{

//		http.authorizeRequests()
//				.antMatchers("/admins/**").hasRole("ADMIN")
//				.antMatchers("/", "resources/**").permitAll()
//				.and().formLogin()
//				.loginPage("/login.html");

		http.csrf()
				.disable()
				.authorizeRequests()
				.antMatchers("/**")
				.permitAll();

	}

}
