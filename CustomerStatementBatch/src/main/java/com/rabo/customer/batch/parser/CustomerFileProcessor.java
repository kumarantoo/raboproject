package com.rabo.customer.batch.parser;

import java.util.List;
import java.util.Optional;

import com.rabo.customer.batch.model.CustomerRecord;

/**
 * Interface to load factory
 * @author kumar
 *
 */
public interface CustomerFileProcessor {

	public List<CustomerRecord> getDataFromFile(ClassLoader inputFile,String format);
}
