package org.emarket.hustle.emarkethustle;

import org.emarket.hustle.emarkethustle.algorithms.LocalFileImpl;
import org.emarket.hustle.emarkethustle.algorithms.QuickSort;
import org.emarket.hustle.emarkethustle.algorithms.RecallibrateRatings;
import org.emarket.hustle.emarkethustle.security.BcryptSecurity;
import org.emarket.hustle.emarkethustle.service.DocumentService;
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
	public QuickSort quickSort()
	{
		return new QuickSort();
	}

	@Bean
	public DocumentService documentConverter()
	{
		return new DocumentService();
	}

	@Bean
	public RecallibrateRatings recallibrateRatings()
	{
		return new RecallibrateRatings();
	}

	@Bean
	public LocalFileImpl localFile()
	{
		return new LocalFileImpl();
	}

}
