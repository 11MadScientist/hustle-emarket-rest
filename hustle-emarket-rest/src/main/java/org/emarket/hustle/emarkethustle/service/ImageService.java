package org.emarket.hustle.emarkethustle.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.util.Base64;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.emarket.hustle.emarkethustle.algorithms.LocalFileImpl;
import org.emarket.hustle.emarkethustle.entity.ImageEntity;
import org.emarket.hustle.emarkethustle.response.FailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService
{
	Logger log = Logger.getLogger(ImageService.class.getName());

	@Autowired
	private LocalFileImpl localFile;

	private String fs = FileSystems.getDefault().getSeparator();

	private String filePath = fs + "images";
	private final String format = ".jpg";

	public byte [] getImage(String entity, int id)
	{
		try
		{
			return localFile.getFile(filePath + fs + entity + fs + id + format);
		}
		catch (Exception e)
		{
			throw new FailedException("FINDING IMAGE WITH PATH " + filePath);
		}

	}

	@Transactional
	public void saveImage(ImageEntity imageEntity)
	{

		byte [] data = Base64.getDecoder().decode(imageEntity.getBase64Image());

		try
		{

			localFile.saveFile(data, filePath + fs +
					imageEntity.getEntity() + fs + imageEntity.getId() + format);
		}
		catch (Exception e)
		{
			e.getMessage();
		}

	}

	public void deleteImage(String entity, int id)
	{
		try
		{
			localFile.deleteFile(filePath + fs + entity + fs + id + format);
		}
		catch (Exception e)
		{
			throw new FailedException("DELETING DOCUMENT WITH PATH " + filePath + fs + entity + fs + id + format);
		}

	}

	public File writeByte(byte [] bytes, String fileName)
	{
		File convertedFile = new File(fileName);
		try (OutputStream os = new FileOutputStream(fileName))
		{
			os.write(bytes);
		}
		catch (IOException e)
		{
			e.getMessage();
		}
		return convertedFile;
	}

}
