package com.mgustran.homeassistant.client.util;

import com.mgustran.homeassistant.client.exception.HaConfigurationException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesProvider {

    private final Properties properties;

    public PropertiesProvider() {
        this.properties = getClasspathProperties();
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    private static Properties getClasspathProperties() {
        final Properties properties = new Properties();
        InputStream iStream = null;
        try {
            // Loading properties file from the classpath
            iStream = PropertiesProvider.class.getClassLoader().getResourceAsStream("application.properties");
            if(iStream == null){
                throw new IOException("File not found, you must define application.properties file in resources folder according to README setup");
            }
            properties.load(iStream);
        } catch (IOException e) {
            throw new HaConfigurationException(e);
        }finally {
            try {
                if(iStream != null){
                    iStream.close();
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
        return properties;
    }
}
