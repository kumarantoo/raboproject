package com.rabo.customer.batch;



import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.rabo.customer.batch.model.CustomerRecord;
import com.rabo.customer.batch.processor.CustomerBatchProcessor;

/**
 * Main class to run Customer batch processing job for validating transcation data 
 * format will be xml and csv , lets take both the format and parse the data based on configuration
 * @author kumaran
 * Arugments should me file name and file format example records.xml xml
 *
 */
public class CustomerBatchMain {
	/* Get actual class name to be printed on */
	static Logger log = Logger.getLogger(CustomerBatchMain.class.getName());
	public static void main(String[] args) {
		/** Start with processor class **/
		log.info("----------Starting batch process for customer statement---------------");
		CustomerBatchProcessor process = new CustomerBatchProcessor();
		/** Class path loader to load file from resources folder **/
		ClassLoader classLoader = new CustomerBatchMain().getClass().getClassLoader();
		List<CustomerRecord> finalList = process.getValidationReport(classLoader, args[0],args[1]);
		System.out.println(finalList.size());
		log.info("----------End batch process for customer statement---------------");
	}
}
