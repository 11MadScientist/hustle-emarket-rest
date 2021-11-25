package org.emarket.hustle.hustleemarketrest.error;

public class ErrorResponse
{
	private String className;
	private int status;
	private String message;
	private long timeStamp;

	public ErrorResponse()
	{
	}

	public ErrorResponse(String className, int status, String message, long timeStamp)
	{
		this.className = className;
		this.status = status;
		this.message = message;
		this.timeStamp = timeStamp;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public long getTimeStamp()
	{
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp)
	{
		this.timeStamp = timeStamp;
	}

	public String getClassName()
	{
		return className;
	}

	public void setClassName(String className)
	{
		this.className = className;
	}

}
