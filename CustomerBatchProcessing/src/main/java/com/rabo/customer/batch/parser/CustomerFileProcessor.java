package com.rabo.customer.batch.parser;

import com.rabo.customer.batch.model.CustomerRecord;

public interface CustomerFileProcessor {

	public CustomerRecord getDataFromFile(String inputFile,String format);
}
