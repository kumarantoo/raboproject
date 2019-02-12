package com.rabo.customer.batch.processor;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.rabo.customer.batch.parser.CustomerFileProcessor;
import com.rabo.customer.batch.parser.FileServiceFactory;

/**
 * 
 * @author kumar
 *
 */
public class CustomerBatchProcessor {

	private FileServiceFactory fileprocessor;
	
	public void getOb(String xmformat) {
		CustomerFileProcessor p = fileprocessor.getService(xmformat);
		System.out.println(p);
	}
}
