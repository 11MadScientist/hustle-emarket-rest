package org.emarket.hustle.hustleemarketrest.error;

public class SellerNotFoundException extends RuntimeException
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public SellerNotFoundException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public SellerNotFoundException(String message)
	{
		super(message);
	}

	public SellerNotFoundException(Throwable cause)
	{
		super(cause);
	}

}
