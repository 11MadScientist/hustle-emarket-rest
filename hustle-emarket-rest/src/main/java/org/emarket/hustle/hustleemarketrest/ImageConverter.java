package org.emarket.hustle.hustleemarketrest;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;
import org.emarket.hustle.hustleemarketrest.response.FailedException;

public class ImageConverter
{
	Logger log = Logger.getLogger(ImageConverter.class.getName());

	private final Path basePath = FileSystems.getDefault()
			.getPath(".", "src", "main", "resources", "images");

	public byte [] getImage(String relativePath)
	{
		String filePath = basePath.normalize().toAbsolutePath() + relativePath;
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
}
