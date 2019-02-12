package com.rabobank.batch.customerstatement.parsers.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import com.rabobank.batch.customerstatement.model.CustomerRecord;
import com.rabobank.batch.customerstatement.parsers.ICustomerFileProcessor;
import com.rabobank.batch.customerstatement.utils.CustomerException;
import com.rabobank.batch.customerstatement.utils.SAXCustomProcessor;

import lombok.extern.slf4j.Slf4j;

/**
 * This class holds login for XML processing
 * @author kumaran
 *
 */
@Component("xml")
@Slf4j
public class CustomerXMLParser implements ICustomerFileProcessor {

	@Autowired
	private SAXCustomProcessor saxCustomProcessor;
	
	/**
	 * Method to hold logic for proceesing XML file
	 * 
	 * 
	 */
	public List<CustomerRecord> getDataFromFile(ClassLoader inputFile, String format) throws CustomerException {
		log.info("---Starting CustomerXMLParser::getDataFromFile-----");
		List<CustomerRecord> finalList = new ArrayList<CustomerRecord>();
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = saxParserFactory.newSAXParser();
			saxParser.parse(new File(inputFile.getResource(format).getFile()), saxCustomProcessor);
			List<CustomerRecord> dupCustomerStatementList = saxCustomProcessor.getDupCustomerRecordList();
			List<CustomerRecord> invalidCustomerStatementList = saxCustomProcessor.getInvalidEndBalanceList();
			finalList.addAll(dupCustomerStatementList);
			finalList.addAll(invalidCustomerStatementList);
		}catch (ParserConfigurationException | SAXException | IOException e) {
			new CustomerException(2, "Exception occurend in CustomerXMLParser::getDataFromFile::"+e.getMessage());
		}
		log.info("---Ending CustomerXMLParser::getDataFromFile-----");
		return finalList;
	}
}
