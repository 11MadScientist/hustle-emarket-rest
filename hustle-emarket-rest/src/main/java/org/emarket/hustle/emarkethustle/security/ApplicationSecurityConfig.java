package org.emarket.hustle.emarkethustle.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter
{

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.csrf()
				.disable()
				.authorizeRequests()

				.antMatchers("/", "/public/**", "/resources/**", "/resources/public/**")
				.permitAll();

	}

}
