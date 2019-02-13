package com.rabobank.upload.utils;

/**
 * Custome exception class to hold exception logic
 * @author kumaran
 *
 */
public class StorageException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
