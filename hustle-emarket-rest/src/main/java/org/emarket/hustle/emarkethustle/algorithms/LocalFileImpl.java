package org.emarket.hustle.emarkethustle.algorithms;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystems;

import org.apache.commons.io.IOUtils;
import org.emarket.hustle.emarkethustle.response.FailedException;
import org.springframework.web.multipart.MultipartFile;

public class LocalFileImpl
{
	private final String basePath = FileSystems.getDefault()
			.getPath(".", "src", "main", "resources").toString();

	public void saveFile(MultipartFile file, String filePath)
	{
		try (FileOutputStream fos = new FileOutputStream(basePath + filePath))
		{
			fos.write(file.getBytes());
		}
		catch (IOException e)
		{
			e.getMessage();
		}
	}

	public void saveFile(byte [] img, String filePath)
	{
		System.out.println(basePath + filePath);
		try (OutputStream os = new FileOutputStream(basePath + filePath))
		{
			os.write(img);
		}
		catch (IOException e)
		{
			e.getMessage();
		}
	}

	public byte [] getFile(String filePath)
	{
		try (InputStream inputStream = new BufferedInputStream(new FileInputStream(basePath + filePath)))
		{
			return IOUtils.toByteArray(inputStream);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			throw new FailedException("FINDING DOCUMENT WITH PATH " + basePath);
		}
	}

	public void deleteFile(String filePath)
	{
		File file = new File(basePath + filePath);

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
