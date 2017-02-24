package it.tbridge.maptest.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class GeographicLocation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne
	private GeoPoint boxSWPoint;

	@OneToOne
	private GeoPoint boxNEPoint;

	@OneToOne
	private GeoPoint locationPoint;

	private Boolean startPoint = false;

	private Boolean endPoint = false;

	private Boolean wayPoint = false;

	@ManyToMany(cascade = CascadeType.ALL)
	private Map<AddressElement.AddressComponentType, AddressElement> AddressData = new HashMap<AddressElement.AddressComponentType, AddressElement>();
	
	@Column(unique=true,nullable=false)
	private String placeId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GeoPoint getBoxSWPoint() {
		return boxSWPoint;
	}

	public void setBoxSWPoint(GeoPoint boxSWPoint) {
		this.boxSWPoint = boxSWPoint;
	}

	public GeoPoint getBoxNEPoint() {
		return boxNEPoint;
	}

	public void setBoxNEPoint(GeoPoint boxNEPoint) {
		this.boxNEPoint = boxNEPoint;
	}

	public GeoPoint getLocationPoint() {
		return locationPoint;
	}

	public void setLocationPoint(GeoPoint locationPoint) {
		this.locationPoint = locationPoint;
	}

	public Map<AddressElement.AddressComponentType, AddressElement> getAddressData() {
		return AddressData;
	}

	public void setAddressData(Map<AddressElement.AddressComponentType, AddressElement> addressData) {
		AddressData = addressData;
	}

	public Boolean getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Boolean startPoint) {
		this.startPoint = startPoint;
	}

	public Boolean getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Boolean endPoint) {
		this.endPoint = endPoint;
	}

	public Boolean getWayPoint() {
		return wayPoint;
	}

	public void setWayPoint(Boolean wayPoint) {
		this.wayPoint = wayPoint;
	}

	public void setPlaceId(String placeId) {
		this.placeId=placeId;
		
	}
	
	public String getPlaceId() {
		return placeId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GeographicLocation other = (GeographicLocation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getFformattedAddress() {
		return "";
	}

}

//
//
//
// {
// "results" : [
// {
//
// "geometry" : {
// "bounds" : {
// "northeast" : {
// "lat" : 44.4197366,
// "lng" : 8.9663346
// },
// "southwest" : {
// "lat" : 44.4197233,
// "lng" : 8.966331499999999
// }
// },
// "location" : {
// "lat" : 44.4197366,
// "lng" : 8.966331499999999
// },
// "location_type" : "RANGE_INTERPOLATED",
// "viewport" : {
// "northeast" : {
// "lat" : 44.4210789302915,
// "lng" : 8.967682030291503
// },
// "southwest" : {
// "lat" : 44.4183809697085,
// "lng" : 8.964984069708498
// }
// }
// },
// "partial_match" : true,
// "place_id" : "EilWaWEgZGVsIENhcHJpb2xvLCA0LCAxNjE0NCBHZW5vdmEsIEl0YWxpYQ",
// "types" : [ "street_address" ]
// }
// ],
// "status" : "OK"
// }