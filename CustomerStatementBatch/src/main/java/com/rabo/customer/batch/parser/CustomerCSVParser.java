package com.rabo.customer.batch.parser;

import org.springframework.stereotype.Component;

import com.rabo.customer.batch.model.CustomerRecord;

@Component
public class CustomerCSVParser implements CustomerFileProcessor{

	@Override
	public CustomerRecord getDataFromFile(String inputFile, String format) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getClassObject() {
		// TODO Auto-generated method stub
		return "xml";
	}

}
