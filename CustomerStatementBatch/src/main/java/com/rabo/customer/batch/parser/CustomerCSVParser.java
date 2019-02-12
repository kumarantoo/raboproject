package com.rabo.customer.batch.parser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.opencsv.CSVReader;
import com.rabo.customer.batch.model.CustomerRecord;
import com.rabo.customer.batch.utils.CustUtils;

/**
 * CustomerCSVParser class will process CSV file and provide report for duplicate and end balance validation
 * @author kumaran
 *
 */
public class CustomerCSVParser implements CustomerFileProcessor{
	// read line by line
	String[] record = null;
	private List<CustomerRecord> CustomerRecordList;
	private List<CustomerRecord> dupCustomerRecordList;
	private Map<String,CustomerRecord> CustomerRecordMap = new HashMap<String,CustomerRecord>();
	private List<CustomerRecord> invalidEndBalance;
	
	/**
	 * Processor method will return List of customer record or prinnt duplicate records
	 */
	@Override
	public List<CustomerRecord> getDataFromFile(ClassLoader inputFile, String format)  {
		FileReader file;
		CSVReader reader = null;
		List<CustomerRecord> finalList = new ArrayList<CustomerRecord>();
		try {
			file = new FileReader(inputFile.getResource(format).getFile());

			reader = new CSVReader(file, ',', '\'', 1); //skips first line

			while ((record = reader.readNext()) != null) {
				CustomerRecord customerRecord = CustUtils.formCustomerRecord(record);
				if(!CustUtils.isValidEndBalance(customerRecord.getEndBalance(), customerRecord.getStartBalance(), customerRecord.getMutation())){
					if(null == invalidEndBalance){
						invalidEndBalance = new ArrayList<CustomerRecord>();
					}
					invalidEndBalance.add(customerRecord);
				}
				if(!CustomerRecordMap.containsKey(record[0])){
					CustomerRecordMap.put(record[0], customerRecord);
				}else{
					if(null == dupCustomerRecordList){
						dupCustomerRecordList = new ArrayList<CustomerRecord>();
					}
					dupCustomerRecordList.add(customerRecord);
				}
				if(null == CustomerRecordList){
					CustomerRecordList = new ArrayList<CustomerRecord>();
				}
				CustomerRecordList.add(customerRecord);
			}
			System.out.println("inside csv starts");
			//dupCustomerRecordList.stream().forEach(System.out::println);		
			//invalidEndBalance.stream().forEach(System.out::println);	
			System.out.println("inside csv ends");
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			
		}
		finalList.addAll(dupCustomerRecordList);
		finalList.addAll(invalidEndBalance);
		return finalList;
	}
}
