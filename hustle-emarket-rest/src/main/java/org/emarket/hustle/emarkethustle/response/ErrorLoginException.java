package org.emarket.hustle.emarkethustle.response;

public class ErrorLoginException extends RuntimeException
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public ErrorLoginException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ErrorLoginException(String message)
	{
		super(message);
	}

	public ErrorLoginException(Throwable cause)
	{
		super(cause);
	}

}
