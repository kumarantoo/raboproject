package com.rabobank.batch.customerstatement.model;

import lombok.Data;

/**
 * Model class holds all the records from file
 * @author kumaran
 *
 */
@Data
public class CustomerRecord {

	private Long recordRefNumber;
	private String accountNumber;
	private String description;
	private double startBalance;
	private double mutation;
	private double endBalance;
	@Override
	public String toString() {
		return "CustomerRecord [recordRefNumber=" + recordRefNumber + ", accountNumber=" + accountNumber
				+ ", description=" + description + ", startBalance=" + startBalance + ", mutation=" + mutation
				+ ", endBalance=" + endBalance + "]";
	}
}

