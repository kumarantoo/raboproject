package com.rabo.customer.batch.processor;

import java.util.List;

import org.apache.log4j.Logger;

import com.rabo.customer.batch.CustomerBatchMain;
import com.rabo.customer.batch.model.CustomerRecord;
import com.rabo.customer.batch.parser.CustomerFileProcessor;
import com.rabo.customer.batch.parser.FileServiceFactory;

/**
 * Processor class will load factory class based on file format csv and xml
 * @author kumaran
 *
 */
public class CustomerBatchProcessor {

	static Logger log = Logger.getLogger(CustomerBatchProcessor.class.getName());
	/**
	 * Method will load factory class either CSV or XML based on the input file format
	 * @param file
	 * @param fileName
	 * @param fileExt
	 * @return
	 */
	public List<CustomerRecord> getValidationReport(ClassLoader file,String fileName,String fileExt) {
		log.debug("Executing::CustomerBatchProcessor::getValidationReport::File format::"+fileExt);
		CustomerFileProcessor p = FileServiceFactory.getFileClass(fileExt);
		List<CustomerRecord> listCust = p.getDataFromFile(file, fileName);
		log.debug("Completed::CustomerBatchProcessor::getValidationReport::File format::"+fileExt);
		return listCust; /** Final list will have collections of records with duplicate and end balance **/
	}
}
