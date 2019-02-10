package com.rabo.customer.batch.parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Factory class using spring factory annotation design to get object of xml and csv parser 
 * dynamicalally based on our input
 * @author kumar
 *
 */
@Component
public class FileServiceFactory {

	@Autowired
	private List<CustomerFileProcessor> processorService;

	private static final Map<String, CustomerFileProcessor> custServiceMap = new HashMap<>();

	@PostConstruct
	public void init() {
		for(CustomerFileProcessor service : processorService) {
			custServiceMap.put(service.getClassObject(), service);
		}
	}

	public CustomerFileProcessor getService(String type) {
		CustomerFileProcessor service = custServiceMap.get(type);
		if(service == null) throw new RuntimeException("Unknown service type: " + type);
		return service;
	}
}
