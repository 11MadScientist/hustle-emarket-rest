package org.emarket.hustle.emarkethustle;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;
import org.emarket.hustle.emarkethustle.entity.ImageEntity;
import org.emarket.hustle.emarkethustle.response.FailedException;

public class ImageConverter
{
	Logger log = Logger.getLogger(ImageConverter.class.getName());

	private final Path basePath = FileSystems.getDefault()
			.getPath(".", "src", "main", "resources", "images");

	public byte [] getImage(String relativePath)
	{
		String filePath = basePath.normalize().toAbsolutePath() + relativePath + ".jpg";
		log.info(basePath.normalize().toAbsolutePath() + relativePath);
		log.info(FileSystems.getDefault().getSeparator());

		try (InputStream inputStream = new BufferedInputStream(new FileInputStream(filePath)))
		{
			return IOUtils.toByteArray(inputStream);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new FailedException("FINDING IMAGE WITH PATH " + relativePath);
		}
	}

	public void saveImage(ImageEntity imageEntity)
	{
		String fs = FileSystems.getDefault().getSeparator();

		String savePath = basePath.normalize().toAbsolutePath() + fs + imageEntity.getEntity() + fs
				+ imageEntity.getId() + ".jpg";

		byte [] data = Base64.getDecoder().decode(imageEntity.getBase64Image());

		try (FileOutputStream fileOutputStream = new FileOutputStream(savePath);)
		{
			fileOutputStream.write(data);

		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new FailedException("SAVING DIGITAL PROFILE OF CUSTOMER " + imageEntity.getId());
		}

	}

	public void deleteImage(String relativePath)
	{

		String deletePath = basePath.normalize().toAbsolutePath() + relativePath + ".jpg";

		try
		{
			Files.deleteIfExists(Paths.get(deletePath));

		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new FailedException("DELETING DIGITAL PROFILE OF CUSTOMER " + relativePath);
		}

	}

}
