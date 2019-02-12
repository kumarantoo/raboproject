package com.rabobank.batch.customerstatement.parsers;

/**
 * Factory interface to get processing class
 * @author kumaran
 *
 */
public interface ICustomerFactory {
	ICustomerFileProcessor getCustomerProcessor(String inputFileFormat);
}
