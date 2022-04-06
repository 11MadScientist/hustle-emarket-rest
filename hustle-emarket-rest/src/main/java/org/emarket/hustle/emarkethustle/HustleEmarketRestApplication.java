package org.emarket.hustle.emarkethustle;

import org.emarket.hustle.emarkethustle.algorithms.DocumentConverter;
import org.emarket.hustle.emarkethustle.algorithms.ImageConverter;
import org.emarket.hustle.emarkethustle.algorithms.QuickSort;
import org.emarket.hustle.emarkethustle.algorithms.RecallibrateRatings;
import org.emarket.hustle.emarkethustle.security.BcryptSecurity;
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

	@Bean
	public QuickSort quickSort()
	{
		return new QuickSort();
	}

	@Bean
	public DocumentConverter documentConverter()
	{
		return new DocumentConverter();
	}

	@Bean
	public RecallibrateRatings recallibrateRatings()
	{
		return new RecallibrateRatings();
	}

}
