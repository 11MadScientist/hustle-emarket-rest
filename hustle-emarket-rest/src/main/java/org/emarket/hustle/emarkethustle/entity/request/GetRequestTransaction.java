package org.emarket.hustle.emarkethustle.entity.request;

public class GetRequestTransaction
{

	private String userProfile;

	private int userId;

	private String field;

	private int page;

	private int size;

	public GetRequestTransaction()
	{
		field = "id";
		page = 0;
		size = 1000;
	}

	public String getUserProfile()
	{
		return userProfile;
	}

	public void setUserProfile(String userProfile)
	{
		this.userProfile = userProfile;
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public String getField()
	{
		return field;
	}

	public void setField(String field)
	{
		this.field = field;
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

	@Override
	public String toString()
	{
		return "GetRequestTransaction [userProfile=" + userProfile + ", userId=" + userId + ", field=" + field
				+ ", page=" + page + ", size=" + size + "]";
	}

}
