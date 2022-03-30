package org.emarket.hustle.emarkethustle.algorithms;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.apache.commons.io.IOUtils;
import org.emarket.hustle.emarkethustle.response.FailedException;
import org.springframework.web.multipart.MultipartFile;

public class DocumentConverter
{
	public void saveDocument(
			String filePath,
			String fileName,
			MultipartFile file)
	{
		File directory = new File(filePath);
		String fs = FileSystems.getDefault().getSeparator();

		if(!directory.exists())
		{
			directory.mkdir();
		}

		try
		{

			file.transferTo(new File(filePath + fs + fileName));
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
	}

	public byte [] getDocument(String entity, String fileName, int id)
	{
		String fs = FileSystems.getDefault().getSeparator();

		Path basePath = FileSystems.getDefault()
				.getPath(".", "src", "main", "resources", "documents", entity);

		String filePath = basePath.normalize().toAbsolutePath()
				+ fs + id + fs + fileName;

		try (InputStream inputStream = new BufferedInputStream(new FileInputStream(filePath)))
		{
			return IOUtils.toByteArray(inputStream);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new FailedException("FINDING DOCUMENT WITH NAME " + fileName);
		}
	}

	public void deleteDocument(String entity, int id)
	{
		String fs = FileSystems.getDefault().getSeparator();
		Path basePath = FileSystems.getDefault()
				.getPath(".", "src", "main", "resources", "documents", entity);
		File file = new File(basePath + fs + id);

		if(file.exists())
		{
			if(file.listFiles() != null)
			{
				for (File childFile : file.listFiles())
				{
					childFile.delete();
				}
			}
			file.delete();
		}
	}
}
