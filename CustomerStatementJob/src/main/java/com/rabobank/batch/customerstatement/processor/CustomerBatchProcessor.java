package com.rabobank.batch.customerstatement.processor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rabobank.batch.customerstatement.exception.CustomerException;
import com.rabobank.batch.customerstatement.model.CustomerRecord;
import com.rabobank.batch.customerstatement.parsers.ICustomerFactory;
import com.rabobank.batch.customerstatement.parsers.ICustomerFileProcessor;

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
	 * @throws IOException 
	 */
	public Optional<List<CustomerRecord>> getValidationReport(ClassLoader file,String fileName,String fileExt) throws CustomerException {
		log.info("-----Starting CustomerBatchProcessor--------");
		ICustomerFileProcessor parserClass = serviceFactory.getCustomerProcessor(fileExt);
		List<CustomerRecord> finalList = null;
		ClassLoader classLoader = new CustomerBatchProcessor().getClass().getClassLoader();
		try {
			finalList = parserClass.getDataFromFile(classLoader, fileName);
			if(finalList!= null && finalList.size() > 0) {
				StringBuilder sb = new StringBuilder();
				String desc = finalList
				           .stream()
				           .map(a -> String.valueOf(a.getDescription()))
				           .collect(Collectors.joining(","));
				log.error("----CustomerBatchProcessor--------"+desc);
				String trancationRef = finalList
				           .stream()
				           .map(a -> String.valueOf(a.getRecordRefNumber()))
				           .collect(Collectors.joining(","));
				sb.append(desc);
				sb.append(trancationRef);
				  FileWriter fw=new FileWriter(classLoader.getResource("reports.txt").getFile());   
				//BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(classLoader.getResource("reports.txt").getFile()));
				fw.write(sb.toString());
				fw.close();
			}
		}catch(CustomerException exe) {
			log.error("-----Error in  CustomerBatchProcessor--------"+exe.getMessage());
		} catch (IOException e) {
			log.error("-----Error in  CustomerBatchProcessor--------"+e.getMessage());
		}
		log.info("-----End CustomerBatchProcessor--------");
		return Optional.ofNullable(finalList);
	}
}
