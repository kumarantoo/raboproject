package com.rabobank.batch.customerstatement.config;

import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.rabobank.batch.customerstatement.parsers.ICustomerFactory;

/**
 * Configuration class to load factory based on xml or csv file user input from 
 * command line 
 * @author kumaran
 *
 */
@Configuration
@ComponentScan("com.rabobank.batch")
public class CustomerStatementConfig {

	/**
	 * Kumaran service locatory factroy will load factory design based on input bean by id
	 * @return
	 */
	@Bean
	public ServiceLocatorFactoryBean serviceLocatorBean(){
		ServiceLocatorFactoryBean bean = new ServiceLocatorFactoryBean();
		bean.setServiceLocatorInterface(ICustomerFactory.class);
		return bean;
	}
}
