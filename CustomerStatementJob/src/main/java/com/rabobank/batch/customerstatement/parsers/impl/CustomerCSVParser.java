package com.rabobank.batch.customerstatement.parsers.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;
import com.rabobank.batch.customerstatement.CustomerJobMain;
import com.rabobank.batch.customerstatement.model.CustomerRecord;
import com.rabobank.batch.customerstatement.parsers.ICustomerFileProcessor;
import com.rabobank.batch.customerstatement.utils.CustUtils;
import com.rabobank.batch.customerstatement.utils.CustomerException;

import lombok.extern.slf4j.Slf4j;


/**
 * CustomerCSVParser class will process CSV file and provide report for duplicate and end balance validation
 * @author kumaran
 *
 */
@Component("csv")
@Slf4j
public class CustomerCSVParser implements ICustomerFileProcessor{

	@Autowired
	private CustUtils utils;
	
	String[] record = null;
	private List<CustomerRecord> customerRecordList;
	private List<CustomerRecord> dupCustomerRecordList;
	private Map<String,CustomerRecord> customerRecordMap = new HashMap<String,CustomerRecord>();
	private List<CustomerRecord> invalidEndBalance;

	/**
	 * Processor method will return List of customer record or prinnt duplicate records
	 * @author kumaran
	 * @param ClassLoader inputFile, String format
	 * @return CustomerRecord
	 */
	public List<CustomerRecord> getDataFromFile(ClassLoader inputFile, String format) throws CustomerException  {
		log.info("-------Starting CustomerCSVParser::getDataFromFile--------------");
		BufferedReader filerReader;
		CSVReader reader = null;
		List<CustomerRecord> finalList = new ArrayList<CustomerRecord>();
		try {
			filerReader = new BufferedReader(new FileReader(inputFile.getResource(format).getFile()));
			reader = new CSVReader(filerReader, ',', '\'', 1);
			while ((record = reader.readNext()) != null) {
				CustomerRecord customerRecord = utils.formCustomerRecord(record);
				if(!utils.isValidEndBalance(customerRecord.getEndBalance(), customerRecord.getStartBalance(), customerRecord.getMutation())){
					if(null == invalidEndBalance){
						invalidEndBalance = new ArrayList<CustomerRecord>();
					}
					invalidEndBalance.add(customerRecord);
				}
				if(!customerRecordMap.containsKey(record[0])){
					customerRecordMap.put(record[0], customerRecord);
				}else{
					if(null == dupCustomerRecordList){
						dupCustomerRecordList = new ArrayList<CustomerRecord>();
					}
					dupCustomerRecordList.add(customerRecord);
				}
				if(null == customerRecordList){
					customerRecordList = new ArrayList<CustomerRecord>();
				}
				customerRecordList.add(customerRecord);
			}
			reader.close();
		} catch (IOException e) {
			log.error("-------Exception CustomerCSVParser::getDataFromFile--------------");
			new CustomerException(1,"IOException file not found or invalid file");
		} finally{

		}
		finalList.addAll(dupCustomerRecordList);
		if(invalidEndBalance !=null) {
			finalList.addAll(invalidEndBalance);
		}
		log.error("-------End CustomerCSVParser::getDataFromFile--------------");
		return finalList;
	}
}
