package com.rabo.customer.batch.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.rabo.customer.batch.model.CustomerRecord;
import com.rabo.customer.batch.utils.SAXCustomProcessor;
/**
 * 
 * @author kumar
 *
 */
public class CustomerXMLParser implements CustomerFileProcessor {

	/**
	 * 
	 */
	@Override
	public List<CustomerRecord> getDataFromFile(ClassLoader inputFile, String format) {
		List<CustomerRecord> finalList = new ArrayList<CustomerRecord>();
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = saxParserFactory.newSAXParser();
			SAXCustomProcessor saxProcess = new SAXCustomProcessor();
			saxParser.parse(new File(inputFile.getResource(format).getFile()), saxProcess);
			List<CustomerRecord> dupCustomerStatementList = saxProcess.getDupCustomerRecordList();
			List<CustomerRecord> invalidCustomerStatementList = saxProcess.getInvalidEndBalanceList();
			System.out.println("dupCustomerStatementList xml starts");
			//dupCustomerStatementList.stream().forEach(System.out::println);
			System.out.println("invalidCustomerStatementList xml starts");
			//invalidCustomerStatementList.stream().forEach(System.out::println);
			//finalList.get().addAll(dupCustomerStatementList);
			//finalList.get().addAll(invalidCustomerStatementList);
			finalList.addAll(dupCustomerStatementList);
			finalList.addAll(invalidCustomerStatementList);
		}catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return finalList;
	}
}
