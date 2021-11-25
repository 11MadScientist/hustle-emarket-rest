package org.emarket.hustle.hustleemarketrest.error;

public class UniqueErrorException extends RuntimeException
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public UniqueErrorException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public UniqueErrorException(String message)
	{
		super(message);
	}

	public UniqueErrorException(Throwable cause)
	{
		super(cause);
	}

}
