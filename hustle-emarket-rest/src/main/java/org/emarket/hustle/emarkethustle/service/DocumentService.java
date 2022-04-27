package org.emarket.hustle.emarkethustle.service;

import java.nio.file.FileSystems;

import org.emarket.hustle.emarkethustle.algorithms.LocalFileImpl;
import org.emarket.hustle.emarkethustle.response.FailedException;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DocumentService
{
	Logger log = Logger.getLogger(DocumentService.class.getName());

	@Autowired
	private LocalFileImpl localFile;

	private String fs = FileSystems.getDefault().getSeparator();

	private String filePath = fs + "documents";

	public void saveDocument(MultipartFile file, String fileSave)
	{

		try
		{
			localFile.saveFile(file, filePath + fs + fileSave);
		}
		catch (Exception e)
		{
			log.error("Error saving file", e);
		}
	}

	public byte [] getDocument(String fileName)
	{

		try
		{
			return localFile.getFile(filePath + fs + fileName);
		}
		catch (Exception e)
		{
			throw new FailedException("FINDING DOCUMENT WITH PATH " + fileName);
		}
	}

	public void deleteDocument(int id, String entity)
	{
		try
		{
			localFile.deleteFile(filePath + fs + entity + fs + id);

		}
		catch (Exception e)
		{
			throw new FailedException("DELETING DOCUMENT WITH PATH " + filePath + fs + entity + fs + id);
		}
	}

}
