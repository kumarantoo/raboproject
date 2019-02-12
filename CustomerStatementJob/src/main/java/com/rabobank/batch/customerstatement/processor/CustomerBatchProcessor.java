package com.rabobank.batch.customerstatement.processor;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.rabobank.batch.customerstatement.model.CustomerRecord;
import com.rabobank.batch.customerstatement.parsers.ICustomerFactory;
import com.rabobank.batch.customerstatement.parsers.ICustomerFileProcessor;
import com.rabobank.batch.customerstatement.utils.CustomerException;
import lombok.extern.slf4j.Slf4j;

/**
 * Processor class will load factory class based on file format csv and xml
 * @author kumaran
 *
 */
@Component
@Slf4j
public class CustomerBatchProcessor {

	@Autowired
	private ICustomerFactory serviceFactory;
	
	/**
	 * Method will load factory class either CSV or XML based on the input file format
	 * @param file
	 * @param fileName
	 * @param fileExt
	 * @return List<CustomerRecord
	 */
	public Optional<List<CustomerRecord>> getValidationReport(ClassLoader file,String fileName,String fileExt) throws CustomerException{
		log.info("-----Starting CustomerBatchProcessor--------");
		ICustomerFileProcessor parserClass = serviceFactory.getCustomerProcessor(fileExt);
		List<CustomerRecord> finalList = null;
		ClassLoader classLoader = new CustomerBatchProcessor().getClass().getClassLoader();
		try {
			finalList = parserClass.getDataFromFile(classLoader, fileName);
		}catch(CustomerException exe) {
			log.error("-----Error in  CustomerBatchProcessor--------"+exe.getMessage());
		}
		log.info("-----End CustomerBatchProcessor--------");
		return Optional.ofNullable(finalList);
	}
}
