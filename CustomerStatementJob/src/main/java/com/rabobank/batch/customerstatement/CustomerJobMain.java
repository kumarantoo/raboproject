package com.rabobank.batch.customerstatement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rabobank.batch.customerstatement.exception.CustomerException;
import com.rabobank.batch.customerstatement.model.CustomerRecord;
import com.rabobank.batch.customerstatement.processor.CustomerBatchProcessor;

import lombok.extern.slf4j.Slf4j;

/**
 * Main class to execute the Job
 * @author kumaran
 *
 */
@SpringBootApplication
@Slf4j
public class CustomerJobMain implements CommandLineRunner{
	
	@Autowired
	private CustomerBatchProcessor processor;
	
	public static void main(String[] args) {
		SpringApplication.run(CustomerJobMain.class, args);
	}

	public void run(String... args) throws CustomerException {
		log.info("Executing CustomerJobMain main batch for ::"+args[1]);
		Optional<List<CustomerRecord>> lis = processor.getValidationReport(null, args[1], args[0]);
		if(lis.isPresent()) {
			log.info("Final List contains duplicaate or innvalid end balance ---->"+lis);
		} else {
			log.info("Final List contains no duplicate records reference number or innvalid end balance ");
		}
	}
}
