package com.rabo.customer.batch.utils;

import com.rabo.customer.batch.model.CustomerRecord;

/**
 * 
 * @author kumar
 *
 */
public class CustUtils {
	
	
	public static Boolean isValidEndBalance(Double endBalance,Double startBalance,Double mut) {
		Double finalBalance = startBalance + mut;
		if (Math.round(finalBalance*100.0/100.0) == endBalance) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	public static CustomerRecord formCustomerRecord(String[] record){
		CustomerRecord CustomerRecord = new CustomerRecord();
		CustomerRecord.setRecordRefNumber(Long.valueOf(record[0]));
		CustomerRecord.setAccountNumber(record[1]);
		CustomerRecord.setDescription(record[2]);
		CustomerRecord.setStartBalance(Double.parseDouble(record[3]));
		CustomerRecord.setMutation(Double.parseDouble(record[4]));
		CustomerRecord.setEndBalance(Double.parseDouble(record[5]));
		return CustomerRecord;
	}
}
