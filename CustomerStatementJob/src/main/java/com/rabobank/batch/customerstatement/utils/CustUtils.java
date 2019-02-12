package com.rabobank.batch.customerstatement.utils;

import org.springframework.stereotype.Component;

import com.rabobank.batch.customerstatement.model.CustomerRecord;
import com.rabobank.batch.customerstatement.parsers.impl.CustomerCSVParser;

import lombok.extern.slf4j.Slf4j;

/**
 * Util class holds common logic for validation and mapping
 * @author kumaran
 *
 */
@Component
@Slf4j
public class CustUtils {
	
	/**
	 * Check for valid end balance logic
	 * @param endBalance
	 * @param startBalance
	 * @param mut
	 * @return
	 */
	public Boolean isValidEndBalance(Double endBalance,Double startBalance,Double mut) {
		log.info("----Starting isValidEndBalance validation ------");
		Double finalBalance = startBalance + mut;
		double bal =  (double) Math.round(finalBalance * 100) / 100;
		if (bal == endBalance) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	/**
	 * Setting values fro csv to java object
	 * @param record
	 * @return
	 */
	public CustomerRecord formCustomerRecord(String[] record){
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
