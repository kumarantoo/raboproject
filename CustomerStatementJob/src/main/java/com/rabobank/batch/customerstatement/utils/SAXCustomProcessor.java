package com.rabobank.batch.customerstatement.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.rabobank.batch.customerstatement.model.CustomerRecord;

/**
 * SAX processor customized for adding BL related to xml
 * @author kumaran
 *
 */
@Component
public class SAXCustomProcessor extends DefaultHandler {

	@Autowired
	private CustUtils utils;
	
	boolean accountNumber = false;
	boolean description = false;
	boolean startBalance = false;
	boolean mutation = false;
	boolean endBalance = false;

	private List<CustomerRecord> dupCustomerRecordList = new ArrayList<CustomerRecord>();
	private Map<Long,CustomerRecord> customerRecordMap = new HashMap<Long,CustomerRecord>();
	private List<CustomerRecord> invalidEndBalanceList = new ArrayList<CustomerRecord>();

	private CustomerRecord custRecord;
	private List<CustomerRecord> customerRecordList;

	/**
	 * Method to get first elements as root and logic for checking duplicate records
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("record")) {
			String id = attributes.getValue("reference");
			custRecord = new CustomerRecord();
			custRecord.setRecordRefNumber(Long.valueOf(id));
			if (customerRecordList == null){
				customerRecordList = new ArrayList<CustomerRecord>();
			}
		} else if (qName.equalsIgnoreCase("accountNumber")) {
			accountNumber = true;
		} else if (qName.equalsIgnoreCase("description")) {
			description = true;
		} else if (qName.equalsIgnoreCase("startBalance")) {
			startBalance = true;
		} else if (qName.equalsIgnoreCase("mutation")) {
			mutation = true;
		}else if (qName.equalsIgnoreCase("endBalance")) {
			endBalance = true;
		}
	}

	/**
	 *  Method to get end elements as root and logic for checking duplicate records
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("record")) {
			customerRecordList.add(custRecord);
			if(!customerRecordMap.containsKey(custRecord.getRecordRefNumber())){
				customerRecordMap.put(custRecord.getRecordRefNumber(), custRecord);
			}else{
				dupCustomerRecordList.add(custRecord);
			}
		}
	}
	
	/**
	 * Overriden method of sax processor to add our own logic
	 */
	@Override
	public void characters(char ch[], int start, int length) throws SAXException {

		if (accountNumber) {
			custRecord.setAccountNumber(new String(ch, start, length));
			accountNumber = false;
		} else if (description) {
			custRecord.setDescription(new String(ch, start, length));
			description = false;
		} else if (startBalance) {
			custRecord.setStartBalance(Double.parseDouble(new String(ch, start, length)));
			startBalance = false;
		} else if (mutation) {
			custRecord.setMutation(Double.parseDouble(new String(ch, start, length)));
			mutation = false;
		}else if (endBalance) {
			//perform currency checking logic
			custRecord.setEndBalance(Double.parseDouble(new String(ch, start, length)));
			if(!utils.isValidEndBalance(custRecord.getEndBalance(), custRecord.getStartBalance(), custRecord.getMutation())){
				invalidEndBalanceList.add(custRecord);
			}
			endBalance = false;
		}
	}

	public List<CustomerRecord> getCustomerRecordList() {
		return customerRecordList;
	}
	public List<CustomerRecord> getDupCustomerRecordList() {
		return dupCustomerRecordList;
	}
	public List<CustomerRecord> getInvalidEndBalanceList() {
		return invalidEndBalanceList;
	}
}
