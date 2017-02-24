package it.tbridge.maptest.war.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

	public static Properties readProperties(String propertiesName) {
		Properties properties = new Properties();
		InputStream fis = null;
		String DirName = System.getProperty("jboss.server.config.dir");
		try {
			if (DirName != null) {
				String fileName = DirName + "/" + propertiesName;
				fis = new FileInputStream(fileName);
			} else {
				fis = PropertiesReader.class.getClassLoader().getResourceAsStream(propertiesName);
			}
			if (fis!=null){
				properties.load(fis);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

}
