package com.rabo.customer.batch.parser;

import org.springframework.stereotype.Component;

import com.rabo.customer.batch.model.CustomerRecord;

@Component
public interface CustomerFileProcessor {

	public String getClassObject();
	public CustomerRecord getDataFromFile(String inputFile,String format);
}
