package org.emarket.hustle.emarkethustle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HustleEmarketRestApplication
{

	public static void main(String [] args)
	{
		SpringApplication.run(HustleEmarketRestApplication.class, args);

	}

	@Bean
	public BcryptSecurity Bcrypt()
	{
		return new BcryptSecurity();
	}

	@Bean
	public ImageConverter imageConverter()
	{
		return new ImageConverter();
	}

}
