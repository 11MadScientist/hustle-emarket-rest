package org.emarket.hustle.emarkethustle.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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

//	@Value("${application.bucket.name}")
//	private String bucketName;

//	@Autowired
//	private AmazonS3 s3Client;

	@Autowired
	private LocalFileImpl localFile;

	private final String format = ".jpg";

	public byte [] getImage(String entity, int id)
	{

		String filePath = "images/" + entity + "/" + id + format;
		System.out.println(filePath);
//		S3Object s3Object = s3Client.getObject(bucketName, filePath);
//		S3ObjectInputStream inputStream = s3Object.getObjectContent();

		try
		{
			return localFile.getFile(filePath);
		}
		catch (Exception e)
		{
			throw new FailedException("FINDING IMAGE WITH PATH " + filePath);
		}

	}

	@Transactional
	public void saveImage(ImageEntity imageEntity)
	{

		String savePath = "images/" + imageEntity.getEntity() + "/"
				+ imageEntity.getId() + format;

		byte [] data = Base64.getDecoder().decode(imageEntity.getBase64Image());

//		File fileObj = writeByte(data, imageEntity.getId() + "");

		try
		{
//			s3Client.putObject(new PutObjectRequest(bucketName, savePath, fileObj));
//			fileObj.delete();

			localFile.saveFile(data, savePath);
		}
		catch (Exception e)
		{
			e.getMessage();
		}

	}

	public void deleteImage(String entity, int id)
	{
		String fileName = "images/" + entity + "/" + id + format;
//		System.out.println(fileName);
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
