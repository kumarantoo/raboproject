package com.rabo.customer.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class to run Customer batch processing job for validating transcation data 
 * format will be xml and csv , lets take both the format and parse the data based on configuration
 * @author kumaran
 *
 */
@SpringBootApplication
public class CustomerBatchMain {
	public static void main(String[] args) {
		SpringApplication.run(CustomerBatchMain.class, args);
	}
}
