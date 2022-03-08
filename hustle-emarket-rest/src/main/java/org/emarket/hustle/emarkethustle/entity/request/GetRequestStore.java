package org.emarket.hustle.emarkethustle.entity.request;

public class GetRequestStore
{

	private String searchField;

	private String searchPattern;

	private String field;

	private boolean authorized;

	private boolean prohibited;

	private String storeAddress;

	private int page;

	private int size;

	public GetRequestStore()
	{
		searchPattern = "";
		field = "storeName";
		storeAddress = "";
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

	public String getStoreAddress()
	{
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress)
	{
		this.storeAddress = storeAddress;
	}

	public boolean isAuthorized()
	{
		return authorized;
	}

	public void setAuthorized(boolean authorized)
	{
		this.authorized = authorized;
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
