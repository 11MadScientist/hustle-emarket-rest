package org.emarket.hustle.emarkethustle.entity;

/**
 * @author houou
 *
 */
public class ImageEntity
{
	private int id;
	private String entity;
	private String base64Image;
	private String relativePath;

	public ImageEntity()
	{
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getEntity()
	{
		return entity;
	}

	public void setEntity(String entity)
	{
		this.entity = entity;
	}

	public String getBase64Image()
	{
		return base64Image;
	}

	public void setBase64Image(String base64Image)
	{
		this.base64Image = base64Image;
	}

	public String getRelativePath()
	{
		return relativePath;
	}

	public void setRelativePath(String relativePath)
	{
		this.relativePath = relativePath;
	}

}
