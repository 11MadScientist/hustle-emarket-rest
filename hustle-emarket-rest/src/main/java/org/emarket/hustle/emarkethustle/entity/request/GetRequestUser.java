package org.emarket.hustle.emarkethustle.entity.request;

public class GetRequestUser
{

	private String searchField;

	private String field;

	private String searchPattern;

	private boolean authorized;

	private boolean prohibited;

	private int page;

	private int size;

	public GetRequestUser()
	{
		searchPattern = "";
		field = "";
		authorized = false;
		prohibited = false;
		page = 0;
		size = 50;
	}

	public String getSearchField()
	{
		return searchField;
	}

	public void setSearchField(String searchField)
	{
		this.searchField = searchField;
	}

	public String getSearchPattern()
	{
		return searchPattern;
	}

	public void setSearchPattern(String searchPattern)
	{
		this.searchPattern = searchPattern;
	}

	public String getName()
	{
		return searchPattern;
	}

	public void setName(String name)
	{
		searchPattern = name;
	}

	public String getField()
	{
		return field;
	}

	public void setField(String field)
	{
		this.field = field;
	}

	public boolean isAuthorized()
	{
		return authorized;
	}

	public void setAuthorized(boolean authorize)
	{
		authorized = authorize;
	}

	public boolean isProhibited()
	{
		return prohibited;
	}

	public void setProhibited(boolean prohibited)
	{
		this.prohibited = prohibited;
	}

	public int getPage()
	{
		return page;
	}

	public void setPage(int page)
	{
		this.page = page;
	}

	public int getSize()
	{
		return size;
	}

	public void setSize(int size)
	{
		this.size = size;
	}

}
