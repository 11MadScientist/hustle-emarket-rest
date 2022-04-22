package org.emarket.hustle.emarkethustle.service;

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

	// for file cloud storage
//	@Value("${application.bucket.name}")
//	private String bucketName;
//
//	@Autowired
//	private AmazonS3 s3Client;

	// for local filestorage

	@Autowired
	private LocalFileImpl localFile;

	public void saveDocument(MultipartFile file, String basePath)
	{
//		File fileObj = convertMultiPartFileToFile(file);

		try
		{
//			s3Client.putObject(new PutObjectRequest(bucketName, basePath, fileObj));
//			fileObj.delete();

			localFile.saveFile(file, basePath);
		}
		catch (Exception e)
		{
			log.error("Error saving file", e);
		}
	}

	public byte [] getDocument(String basePath)
	{
//		S3Object s3Object = s3Client.getObject(bucketName, basePath);
//		S3ObjectInputStream inputStream = s3Object.getObjectContent();
//		try
//		{
//			byte [] content = IOUtils.toByteArray(inputStream);
//			return content;
//		}
//		catch (IOException e)
//		{
//			e.printStackTrace();
//			throw new FailedException("FINDING DOCUMENT WITH PATH " + basePath);
//		}

		try
		{
			return localFile.getFile(basePath);
		}
		catch (Exception e)
		{
			throw new FailedException("FINDING DOCUMENT WITH PATH " + basePath);
		}
	}

	public void deleteDocument(int id, String entity)
	{
		String fileName = "documents/" + entity + "/" + id;
		try
		{
//			s3Client.deleteObject(bucketName, fileName);
			localFile.deleteFile(fileName);

		}
		catch (Exception e)
		{
			throw new FailedException("DELETING DOCUMENT WITH PATH " + fileName);
		}
	}

//	private File convertMultiPartFileToFile(MultipartFile file)
//	{
//		File convertedFile = new File(file.getOriginalFilename());
//		try (FileOutputStream fos = new FileOutputStream(convertedFile))
//		{
//			fos.write(file.getBytes());
//		}
//		catch (IOException e)
//		{
//			log.error("Error converting multipartFile to file", e);
//		}
//		return convertedFile;
//	}
}
