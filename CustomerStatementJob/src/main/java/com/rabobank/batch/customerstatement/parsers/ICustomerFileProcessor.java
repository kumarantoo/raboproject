package com.rabobank.batch.customerstatement.parsers;

import java.util.List;
import java.util.Optional;

import com.rabobank.batch.customerstatement.model.CustomerRecord;
import com.rabobank.batch.customerstatement.utils.CustomerException;


/**
 * Interface hold BL for file processing
 * @author kumaran
 *
 */
public interface ICustomerFileProcessor {

	public List<CustomerRecord> getDataFromFile(ClassLoader inputFile,String format)throws CustomerException;
}
