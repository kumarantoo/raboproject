package com.rabobank.upload.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Location properties class
 * @author kumaran
 *
 */
@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = System.getProperty("user.dir");

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
