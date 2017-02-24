package it.tbridge.maptest.war.beans.application;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.google.maps.GeoApiContext;

import it.tbridge.maptest.war.utils.GoogleUtils;

@ManagedBean(name="applicationBean")
@ApplicationScoped
public class ApplicationBean {

	GeoApiContext context =null;
	
	@PostConstruct
	private void init(){
		GoogleUtils gutils = new GoogleUtils();
		context = gutils.getGeoApiContext();
	}
	
	public GeoApiContext getGeoApiContext(){
		return context;
	}
	
		
}
