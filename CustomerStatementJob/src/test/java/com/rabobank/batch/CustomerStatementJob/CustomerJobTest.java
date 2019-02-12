package com.rabobank.batch.CustomerStatementJob;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.rabobank.batch.customerstatement.config.CustomerStatementConfig;
import com.rabobank.batch.customerstatement.exception.CustomerException;
import com.rabobank.batch.customerstatement.model.CustomerRecord;
import com.rabobank.batch.customerstatement.processor.CustomerBatchProcessor;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(properties=
{"spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration"})
@ContextConfiguration(classes = CustomerStatementConfig.class, loader = AnnotationConfigContextLoader.class)
public class CustomerJobTest {

	@Autowired 
	private CustomerBatchProcessor processor;
	
	/**
	 * Test classes to check for xml based file loder
	 * @throws CustomerException
	 */
	@Test
	public void testFileReadXML() throws CustomerException {
		ClassLoader classLoader = new CustomerBatchProcessor().getClass().getClassLoader();
		Optional<List<CustomerRecord>> re = processor.getValidationReport(classLoader, "records.xml", "xml");
		assertNotNull(re);
	}
	
	/**
	 * Test classes to check for xml based file loder
	 * @throws CustomerException
	 */
	@Test
	public void testDuplicateReferenceXML() throws CustomerException {
		ClassLoader classLoader = new CustomerBatchProcessor().getClass().getClassLoader();
		Optional<List<CustomerRecord>> re = processor.getValidationReport(classLoader, "records.xml", "xml");
		assertNotNull(re);
		assertEquals(re.get().get(0).getRecordRefNumber(),Long.valueOf(130498));
	}
	
	/**
	 * Test classes to check for xml based file loder
	 * @throws CustomerException
	 */
	@Test
	public void testInvalidBalanceXML() throws CustomerException {
		ClassLoader classLoader = new CustomerBatchProcessor().getClass().getClassLoader();
		Optional<List<CustomerRecord>> re = processor.getValidationReport(classLoader, "records.xml", "xml");
		assertNotNull(re);
		assertEquals(re.get().get(0).getAccountNumber(),new String("NL93ABNA0585619023"));
	}
	
	/**
	 * Test classes to check for CSV based file loder
	 * @throws CustomerException
	 */
	@Test
	public void testFileReadCSV() throws CustomerException {
		ClassLoader classLoader = new CustomerBatchProcessor().getClass().getClassLoader();
		Optional<List<CustomerRecord>> re = processor.getValidationReport(classLoader, "records.csv", "csv");
		assertNotNull(re);
	}
	
	/**
	 * Test classes to check for CSV based file loder
	 * @throws CustomerException
	 */
	@Test
	public void testDuplicateReferenceCSV() throws CustomerException {
		ClassLoader classLoader = new CustomerBatchProcessor().getClass().getClassLoader();
		Optional<List<CustomerRecord>> re = processor.getValidationReport(classLoader, "records.csv", "csv");
		assertNotNull(re);
		assertEquals(re.get().get(0).getRecordRefNumber(),Long.valueOf(112806));
	}
	
	/**
	 * Test classes to check for CSV based file loder
	 * @throws CustomerException
	 */
	@Test
	public void testInvalidBalanceCSV() throws CustomerException {
		ClassLoader classLoader = new CustomerBatchProcessor().getClass().getClassLoader();
		Optional<List<CustomerRecord>> re = processor.getValidationReport(classLoader, "records.csv", "csv");
		assertNotNull(re);
		assertEquals(re.get().get(0).getAccountNumber(),new String("NL69ABNA0433647324"));
	}
}
