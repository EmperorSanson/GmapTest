package it.tbridge.maptest.war.beans.request;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import it.tbridge.maptest.bean.GeographicLocationBean;
import it.tbridge.maptest.entity.GeographicLocation;
import it.tbridge.maptest.war.beans.application.ApplicationBean;

@ManagedBean(name = "waypointBean")
public class WaypointBean {

	@EJB
	GeographicLocationBean geographicLocation;

	List<String> selectWaypoints = new ArrayList<String>();
	String selectStart;
	String selectEnd;
	
	@ManagedProperty(value = "#{applicationBean}")
	private ApplicationBean applicationBean;

	public void setApplicationBean(ApplicationBean applicationBean) {
		this.applicationBean = applicationBean;
	}

	
	public List<String> getSelectWaypoints() {
		return selectWaypoints;
	}
	
	public void setSelectWaypoints(List<String> selectWaypoints) {
		this.selectWaypoints = selectWaypoints;
	}
	
	public GeographicLocationBean getGeographicLocation() {
		return geographicLocation;
	}

	public void setGeographicLocation(GeographicLocationBean geographicLocation) {
		this.geographicLocation = geographicLocation;
	}

	public String getSelectStart() {
		return selectStart;
	}

	public void setSelectStart(String selectStart) {
		this.selectStart = selectStart;
	}

	public String getSelectEnd() {
		return selectEnd;
	}

	public void setSelectEnd(String selectEnd) {
		this.selectEnd = selectEnd;
	}

	public List<GeographicLocation> findWaypoints() {
		List<GeographicLocation> ret = geographicLocation.findByWaytPoint();
		return ret;
	}

	public List<GeographicLocation> findStarts() {
		List<GeographicLocation> ret = geographicLocation.findByStartPoint();
		return ret;
	}
	
	public List<GeographicLocation> findEnds() {
		List<GeographicLocation> ret = geographicLocation.findByEndPoint();
		return ret;
	}
	
	public void testmaps() {
			

	}

}
