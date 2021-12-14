package org.emarket.hustle.emarkethustle.service;

import org.emarket.hustle.emarkethustle.entity.ImageEntity;

public interface ImageService
{
	public byte [] getImage(String entity, int id);

	public void saveImage(ImageEntity imageEntity);

	public void deleteImage(String entity, int id);
}
