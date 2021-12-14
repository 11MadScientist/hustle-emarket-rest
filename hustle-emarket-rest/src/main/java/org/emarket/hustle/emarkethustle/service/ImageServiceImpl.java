package org.emarket.hustle.emarkethustle.service;

import java.nio.file.FileSystems;

import javax.transaction.Transactional;

import org.emarket.hustle.emarkethustle.ImageConverter;
import org.emarket.hustle.emarkethustle.entity.ImageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService
{
	@Autowired
	ImageConverter imageConverter;

	@Override
	public byte [] getImage(String entity, int id)
	{
		String fs = FileSystems.getDefault().getSeparator();
		return (imageConverter.getImage(fs + entity + fs + id));
	}

	@Override
	@Transactional
	public void saveImage(ImageEntity imageEntity)
	{

		imageConverter.saveImage(imageEntity);

	}

	@Override
	public void deleteImage(String entity, int id)
	{
		String fs = FileSystems.getDefault().getSeparator();
		imageConverter.deleteImage(fs + entity + fs + id);

	}

}
