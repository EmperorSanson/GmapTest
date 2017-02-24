var labels = ['00','01','02','03'];
var labelIndex = 0;
var sestri = {
	lat : 44.422071,
	lng : 8.852295
}

function initMap() {
	var map = new google.maps.Map(document.getElementById('map'), {
		zoom : 17,
		center : sestri
	});
	
	var waypts = [];
	var checkboxArray = document.getElementById('mainform:waypoints');
	for (var i = 0; i < checkboxArray.length; i++) {
		if (checkboxArray.options[i].selected) {
			val =  checkboxArray[i].value;
			var point = {lat : Number(val.split(',')[0]), lng :Number(val.split(',')[1])};
			waypts.push(
				point
			);
		}
	}
	var val = document.getElementById('mainform:start').value;
	var startPoint = {lat : Number(val.split(',')[0]), lng :Number(val.split(',')[1])};
	val = document.getElementById('mainform:end').value;
	var endPoint =  {lat : Number(val.split(',')[0]), lng :Number(val.split(',')[1])};
	var directionsService = new google.maps.DirectionsService;
	var directionsDisplay = new google.maps.DirectionsRenderer;
	directionsDisplay.setMap(map);
	
	new google.maps.Marker({
		position : startPoint,
		label : 'start',
		map : map
	});

	new google.maps.Marker({
		position : endPoint,
		label : 'end',
		map : map
	});
	
	for (i =0; i<waypts.length; i++){
		new google.maps.Marker({
			position : waypts[i],
			label : labels[labelIndex++ % labels.length],
			map : map
		});
	}
	document.getElementById('submit').addEventListener('click', function() {
		calculateAndDisplayRoute(directionsService, directionsDisplay);
	});
}

function initMap2() {
	var map = new google.maps.Map(document.getElementById('map'), {
		zoom : 17,
		center : sestri
	});

	var directionsService = new google.maps.DirectionsService;
	var directionsDisplay = new google.maps.DirectionsRenderer({
		draggable : true,
		map : map,
		panel : document.getElementById('right-panel')
	});

	directionsDisplay.addListener('directions_changed', function() {
		computeTotalDistance(directionsDisplay.getDirections());
	});

	displayRoute(startPoint, endPoint,
			directionsService, directionsDisplay);
}

function calculateAndDisplayRoute(directionsService, directionsDisplay) {
	var val = document.getElementById('mainform:start').value;
	var startPoint = {lat : Number(val.split(',')[0]), lng :Number(val.split(',')[1])};
	val = document.getElementById('mainform:end').value;
	var endPoint =  {lat : Number(val.split(',')[0]), lng :Number(val.split(',')[1])};
	var waypts = [];
	var checkboxArray = document.getElementById('mainform:waypoints');
	for (var i = 0; i < checkboxArray.length; i++) {
		if (checkboxArray.options[i].selected) {
			val =  checkboxArray[i].value;
			var point = {lat : Number(val.split(',')[0]), lng :Number(val.split(',')[1])};
			waypts.push({
				location : point,
				stopover : true
			});
		}
	}

	
	directionsService.route({
		origin : startPoint,
		destination : endPoint,
		waypoints : waypts,
		optimizeWaypoints : true,
		travelMode : 'DRIVING'
	}, function(response, status) {
		if (status === 'OK') {
			directionsDisplay.setDirections(response);
			var route = response.routes[0];
			var summaryPanel = document.getElementById('directions-panel');
			summaryPanel.innerHTML = '';
			// For each route, display summary information.
			for (var i = 0; i < route.legs.length; i++) {
				var routeSegment = i + 1;
				summaryPanel.innerHTML += '<b>Route Segment: ' + routeSegment
						+ '</b><br>';
				summaryPanel.innerHTML += route.legs[i].start_address + ' to ';
				summaryPanel.innerHTML += route.legs[i].end_address + '<br>';
				summaryPanel.innerHTML += route.legs[i].distance.text
						+ '<br><br>';
			}
		} else {
			window.alert('Directions request failed due to ' + status);
		}
	});
}

function displayRoute(origin, destination, service, display) {
	service.route({
		origin : origin,
		destination : destination,
		waypoints : [ {
			location : '44.426187, 8.847927'
		}, {
			location : '44.425806, 8.847686'
		} ],
		travelMode : 'DRIVING',
		avoidTolls : true
	}, function(response, status) {
		if (status === 'OK') {
			display.setDirections(response);
		} else {
			alert('Could not display directions due to: ' + status);
		}
	});
}

function computeTotalDistance(result) {
	var total = 0;
	var myroute = result.routes[0];
	for (var i = 0; i < myroute.legs.length; i++) {
		total += myroute.legs[i].distance.value;
	}
	total = total / 1000;
	document.getElementById('total').innerHTML = total + ' km';
}

// Address to coordinate
// https://maps.googleapis.com/maps/api/geocode/json?address=4+via+del+capriolo,+Genova,+Italia&key=AIzaSyAbYMPG_CQacJqVAtNsr9tCMAB6BkzchmM
