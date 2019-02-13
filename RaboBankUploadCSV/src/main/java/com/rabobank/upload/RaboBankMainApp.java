package com.rabobank.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.rabobank.upload.utils.StorageProperties;

/**
 * Main Application to boot webapp
 * @author kumaran
 *
 */
@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class RaboBankMainApp {
	public static void main(String[] args) {
		SpringApplication.run(RaboBankMainApp.class, args);
	}
}

