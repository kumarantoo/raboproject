package com.rabo.customer.batch.parser;



/**
 * Factory class using factory design to get object of xml and csv parser 
 * dynamicalally based on our input
 * @author kumaran
 *
 */
public class FileServiceFactory {

	/**
	 * Factory method will reutrn instance of file processor to be called bases on file input
	 * @param fileType
	 * @return
	 */
	public static CustomerFileProcessor getFileClass(String fileType) {
		if ( fileType.equals("csv") )
			return new CustomerCSVParser();
		else if ( fileType.equals("xml") )
			return new CustomerXMLParser();
		return null;
	}
}
