package org.emarket.hustle.emarkethustle.security;

import org.emarket.hustle.emarkethustle.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	UserDetailServiceImpl userDetailService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userDetailService);
	}

	@Order(1)
	@Configuration
	class RestSecurityConfig extends WebSecurityConfigurerAdapter
	{
		@Override
		protected void configure(HttpSecurity http) throws Exception
		{
			http.csrf()
					.disable()
					.antMatcher("/api/**")
					.authorizeRequests()
//			initializing get/post/delete necessary urls so that users can access them
					.antMatchers(HttpMethod.GET, "/api/**").permitAll()
					.antMatchers(HttpMethod.POST, "/api/customers").permitAll()
					.antMatchers(HttpMethod.DELETE, "/api/baskets/**").hasAnyRole("CUSTOMER")
					.antMatchers(HttpMethod.DELETE, "/api/favourites/**").hasAnyRole("CUSTOMER")
					.antMatchers(HttpMethod.DELETE, "/api/customer-addresses/**").hasAnyRole("ADMIN", "CUSTOMER")

//			other delete urls can only be accessed by admin
					.antMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
					.antMatchers("/api/promtions/**").hasRole("ADMIN")

//			customer urls
					.antMatchers("/api/customers/**").hasAnyRole("ADMIN", "CUSTOMER")
					.antMatchers("/api/customer-details/**").hasAnyRole("ADMIN", "CUSTOMER")
					.antMatchers("/api/customer-addresses/**").hasAnyRole("ADMIN", "CUSTOMER")
					.antMatchers("/api/baskets/**").hasAnyRole("ADMIN", "CUSTOMER")
					.antMatchers("/api/favourites/**").hasAnyRole("ADMIN", "CUSTOMER")

//			seller urls
					.antMatchers("/api/sellers/**").hasAnyRole("ADMIN", "SELLER")
					.antMatchers("/api/seller-details/**").hasAnyRole("ADMIN", "SELLER")
					.antMatchers("/api/stores/**").hasAnyRole("ADMIN", "SELLER")
					.antMatchers("/api/items/**").hasAnyRole("ADMIN", "SELLER")
					.antMatchers("/api/histories/**").hasAnyRole("ADMIN", "SELLER")

//			rider urls
					.antMatchers("/api/riders/**").hasAnyRole("ADMIN", "RIDER")

//			joint urls
					.antMatchers("/api/transactions/**").hasAnyRole("ADMIN", "CUSTOMER", "RIDER")
					.antMatchers("/api/images/**").hasAnyRole("ADMIN", "CUSTOMER", "RIDER", "SELLER")
					.antMatchers("/api/**").permitAll()
					.and()
					.httpBasic();
		}

	}

	@Order(2)
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter
	{
		@Override
		protected void configure(HttpSecurity http) throws Exception
		{

			http
					.csrf()
					.disable()
					.authorizeRequests()

					.antMatchers("/admins/**").hasRole("ADMIN")
					.antMatchers("/*", "resources/**").permitAll()
					.and().formLogin()
					.loginPage("/login")
					.defaultSuccessUrl("/admins/dashboard");

		}
	}

}
