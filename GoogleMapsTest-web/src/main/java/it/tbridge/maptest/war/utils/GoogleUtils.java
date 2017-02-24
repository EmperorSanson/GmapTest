package it.tbridge.maptest.war.utils;

import java.util.Properties;

import com.google.maps.GeoApiContext;

public class GoogleUtils {
	private static Properties gprop=null;
	
	private static GeoApiContext context=null; 
	
	public String getGoogleApiKey(){
		if (gprop==null){
			gprop = PropertiesReader.readProperties("google.properties");
		}
		if (gprop!=null){
			return gprop.getProperty("google.api.key");
		}
		return null;
	}
	
	public GeoApiContext getGeoApiContext(){
		if (context==null){
			if (getGoogleApiKey()!=null){
				context = new GeoApiContext().setApiKey(getGoogleApiKey());
			}
			else{
				throw new RuntimeException("Missing Google API  Key or missing google properties");
			}
		}
		return context;
	}
}
