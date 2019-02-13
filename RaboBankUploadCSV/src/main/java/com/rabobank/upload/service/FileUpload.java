package com.rabobank.upload.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.rabobank.upload.model.Records;

/**
 * Upload interface to hold service logic
 * @author kumaran
 *
 */
public interface FileUpload {

	List<Records>  store(MultipartFile file);
	List<Records> getFileRecords();
}
