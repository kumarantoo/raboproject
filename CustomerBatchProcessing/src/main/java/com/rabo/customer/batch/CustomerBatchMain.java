package com.rabo.customer.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rabo.customer.batch.processor.CustomerBatchProcessor;

/**
 * Main class to run Customer batch processing job for validating transcation data 
 * format will be xml and csv , lets take both the format and parse the data based on configuration
 * @author kumaran
 *
 */
public class CustomerBatchMain {
	public static void main(String[] args) {
		CustomerBatchProcessor process = new CustomerBatchProcessor();
		process.getOb("xml");
	}
}
