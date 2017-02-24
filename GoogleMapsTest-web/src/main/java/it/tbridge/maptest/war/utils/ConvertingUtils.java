package it.tbridge.maptest.war.utils;

import java.util.HashMap;
import java.util.Map;

import com.google.maps.model.AddressComponent;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

import it.tbridge.maptest.entity.AddressElement;
import it.tbridge.maptest.entity.AddressElement.AddressComponentType;
import it.tbridge.maptest.entity.GeoPoint;
import it.tbridge.maptest.entity.GeographicLocation;

public class ConvertingUtils {

	public static GeographicLocation convert(GeocodingResult start){
		GeographicLocation geo = new GeographicLocation();
		geo.setPlaceId(start.placeId);
		geo.setAddressData(convert(start.addressComponents));
		if (start.geometry.bounds != null){
			geo.setBoxNEPoint(convert(start.geometry.bounds.northeast));
			geo.setBoxSWPoint(convert(start.geometry.bounds.southwest));
		}
		geo.setLocationPoint(convert(start.geometry.location));
		return geo;
		
	}

	private static Map<AddressComponentType, AddressElement> convert(AddressComponent[] addressComponents) {
		Map<AddressComponentType, AddressElement> ret = new HashMap<AddressComponentType, AddressElement>();
		for (AddressComponent ad : addressComponents ){
			AddressElement ae = new AddressElement();
			ae.setType(AddressElement.AddressComponentType.valueOf(ad.types[0].name()));
			ae.setValue(ad.longName);
			ret.put(ae.getType(),ae);
		}
		return ret;
	}

	private static GeoPoint convert(LatLng location) {
		GeoPoint gp = new GeoPoint();
		gp.setLatitude(location.lat);
		gp.setLongitude(location.lng);
		return gp;
	}
	
}
