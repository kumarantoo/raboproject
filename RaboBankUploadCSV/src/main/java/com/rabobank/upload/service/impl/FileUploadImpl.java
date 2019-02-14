package com.rabobank.upload.service.impl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.rabobank.upload.model.Records;
import com.rabobank.upload.service.FileUpload;
import com.rabobank.upload.utils.StorageException;
import com.rabobank.upload.utils.StorageProperties;

import lombok.extern.slf4j.Slf4j;

/**
 * File upload service class to hold all logice related to file handling
 * @author kumaran
 *
 */
@Service
@Slf4j
public class FileUploadImpl implements FileUpload {


	private final Path rootLocation;

	@Autowired
	public FileUploadImpl(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}

	/**
	 * Service method to save file in the project path directory
	 * @param file
	 */
	@Override
	public List<Records> store(MultipartFile file) {
		log.info("Inside store logic service");
		List<Records> recordList = new ArrayList<Records>();
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file " + filename);
			}
			if (filename.contains("..")) {
				// This is a security check
				throw new StorageException(
						"Cannot store file with relative path outside current directory "
								+ filename);
			}
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, this.rootLocation.resolve(filename),
						StandardCopyOption.REPLACE_EXISTING);
			}
		}
		catch (IOException e) {
			log.error("Exception in  saving file");
			throw new StorageException("Failed to store file " + filename, e);
		}
		log.error("End of saving file service");
		return recordList;
	}

	/**
	 * Setting values fro csv to java object
	 * @param record
	 * @return
	 */
	private Records formCustomerRecord(String[] record){
		Records customerRecord = new Records();
		customerRecord.setFirstName(record[0]);
		customerRecord.setSurName(record[1]);
		customerRecord.setIssueCount(record[2]);
		customerRecord.setDob(record[3]);
		return customerRecord;
	}

	/**
	 * Service method to get data from file in the project path directory
	 * @param null
	 * returns List<Records> 
	 */
	@Override
	public List<Records> getFileRecords() {
		log.error("Begin getting recoresfrom file");
		String[] record = null;
		CSVReader reader = null;
		List<Records> recordList = new ArrayList<Records>();
		FileReader readerFile;
		try {
			readerFile = new FileReader(rootLocation+"/issues.csv");
			reader = new CSVReader(readerFile, ',', '\'', 1);
			while ((record = reader.readNext()) != null) {
				Records customerRecord = formCustomerRecord(record);
				recordList.add(customerRecord);
			}
		} catch (FileNotFoundException e) {
			log.error("File not found error"+e.getLocalizedMessage());
		}  
		catch (IOException e) {
			log.error("IO exception not found error"+e.getLocalizedMessage());
		}
		log.info("End getting recoresfrom file");
		return recordList;
	}
}
