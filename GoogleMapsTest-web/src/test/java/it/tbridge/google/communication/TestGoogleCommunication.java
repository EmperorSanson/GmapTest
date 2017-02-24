package it.tbridge.google.communication;

import org.junit.Test;

import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeocodingApi;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.GeocodedWaypoint;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

import it.tbridge.maptest.entity.GeographicLocation;
import it.tbridge.maptest.war.utils.ConvertingUtils;
import it.tbridge.maptest.war.utils.GoogleUtils;

public class TestGoogleCommunication {

    @Test
    public void TestKeyPresence() {
    	GoogleUtils googleUtils= new GoogleUtils();
    	assert(googleUtils.getGoogleApiKey()!=null);
    }

    @Test
    public void testContextCreation() {
    	GoogleUtils googleUtils= new GoogleUtils();
    	assert(googleUtils.getGeoApiContext()!=null);
    }
	
    @Test
    public void testLocation(){
    	GeographicLocation geo = null;
    	GeocodingResult[] results;
    	GoogleUtils googleUtils= new GoogleUtils();
			try {
				results = GeocodingApi.geocode(googleUtils.getGeoApiContext(), "Via del capriolo, 4, Genova, Italia")
						.await();
				if (results.length > 0) {
					geo = new GeographicLocation();
					geo = ConvertingUtils.convert(results[0]);
				}

				results = GeocodingApi.geocode(googleUtils.getGeoApiContext(), "Via Giacomo Puccini, 2, Genova, Italia")
						.await();
				if (results.length > 0) {
					geo = new GeographicLocation();
					geo = ConvertingUtils.convert(results[0]);
				}

				results = GeocodingApi.geocode(googleUtils.getGeoApiContext(), "Via della Mercanzia, 2, Genova, Italia")
						.await();
				if (results.length > 0) {
					geo = new GeographicLocation();
					geo = ConvertingUtils.convert(results[0]);
				}
				results = GeocodingApi
						.geocode(googleUtils.getGeoApiContext(), "Piazza Giuseppe Verdi, 3, Genova, Italia").await();
				if (results.length > 0) {
					geo = new GeographicLocation();
					geo = ConvertingUtils.convert(results[0]);
				}

				results = GeocodingApi.geocode(googleUtils.getGeoApiContext(), "Via S. Martino, 65, Genova, Italia")
						.await();
				if (results.length > 0) {
					geo = new GeographicLocation();
					geo = ConvertingUtils.convert(results[0]);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				assert(false);
			}
			assert(true);
			
    }
    
    @Test
    public void testDirection(){
    	GeographicLocation geo = null;
    	GeocodingResult[] results;
    	GoogleUtils googleUtils= new GoogleUtils();
    	try{
    	results = GeocodingApi.geocode(googleUtils.getGeoApiContext(), "Via del capriolo, 4, Genova, Italia")
				.await();
		if (results.length > 0) {
			geo = new GeographicLocation();
			geo = ConvertingUtils.convert(results[0]);
			geo.setStartPoint(true);
			geo.setEndPoint(true);
			geo.setWayPoint(true);
		}

    	DirectionsApiRequest request = DirectionsApi.newRequest(googleUtils.getGeoApiContext());
    	LatLng origin = results[0].geometry.location;
    	LatLng destination = new LatLng(44.422071, 8.852295);

    	request.origin(origin);
    	request.destination(destination);

    	DirectionsResult res = request.await();
    	for (GeocodedWaypoint a : res.geocodedWaypoints) {
    		System.out.println(a.placeId);
    		System.out.println(a.types[0]);
    	}
    	for (DirectionsRoute a : res.routes) {
    		System.out.println(a.overviewPolyline.getEncodedPath());
    		System.out.println(a.overviewPolyline.decodePath().size());
    		System.out.println(a.summary);
    	}
    	}catch (Exception e){
    		// TODO Auto-generated catch block
			e.printStackTrace();
			assert(false);
		}
		assert(true);
    }
    
    
}
