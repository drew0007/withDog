<%@ page contentType="text/html; charset=EUC-KR"%>
<%@ page pageEncoding="EUC-KR"%>

<!DOCTYPE html>
<html>
  <head>
  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
  <script type="text/javascript" src="../js/bootstrap.js"></script>
  <link rel="stylesheet" href="../css/bootstrap.css" />
   <meta charset="utf-8">
    <title>Marker animations with <code>setTimeout()</code></title>
    <style>
      /* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
     #map {
        height: 100%;
        align-left:100;
        /* left: 100%; */

      }
      #list{
      	height: 70%;
        align-left:100;
        /* right: 75%; */
      }
      /* Optional: Makes the sample page fill the window. */
      html, body {
        height: 100%;
        margin: 0;
        padding: 0;
        
      } 
      #floating-panel {
        position: absolute;
        top: 10px;
        left: 25%;
        z-index: 5;
        background-color: #fff;
        padding: 5px;
        border: 1px solid #999;
        text-align: center;
        font-family: 'Roboto','sans-serif';
        line-height: 30px;
        padding-left: 10px;
      }
      #floating-panel {
        margin-left: -52px;
      }
      /* #move{
      width:100%;
      } */
    </style>
  </head>
  		<div align="center">
  		<!-- <button type="button" class="btn btn-primary btn-lg btn-block">함 께 할 맵</button> -->
  		<img alt="" src="../images/quick/title.png" width="100%" height="25%">
  		</div>
		<div class="container2" id="list">
			<div class="col-md-2 col-sm-2">
				<div class="row" align="center">
					<p>음식점이름</p>										
					<p>음식점이름</p>										
					<p>음식점이름</p>										
					<p>음식점이름</p>										
					<p>음식점이름</p>										
					<p>음식점이름</p>										
					<p>음식점이름</p>										
					<p>음식점이름</p>										
					<p>음식점이름</p>										
					<p>음식점이름</p>										
					<p>음식점이름</p>										
					<p>음식점이름</p>										
					<p>음식점이름</p>										
					<p>음식점이름</p>										
				</div>
			</div>
			
			<div id="map" class="col-md-10 col-sm-10"></div>
		</div>
	    <div align="center">
	    <img alt="" src="../images/quick/dogicon.png" width="180" height="180">
	    &nbsp;
		<img alt="" src="../images/quick/park.png" width="180" height="180">
		&nbsp;
		<img alt="" src="../images/quick/pun.png" width="180" height="180">
		&nbsp;
		<img alt="" src="../images/quick/ret.png" width="180" height="180">
		&nbsp;
		<img alt="" src="../images/quick/sub.png" width="180" height="180">
		</div>
	
    <script>

      // If you're adding a number of markers, you may want to drop them on the map
      // consecutively rather than all at once. This example shows how to use
      // window.setTimeout() to space your markers' animation.
	  function move(){
    	  self.location.href="http://maps.google.com/maps";
      }
      
       
      
      var placelat = new Array();
      var placelng = new Array();
      
      var plname = new Array();
      var plplace = new Array();
      
      var plcontentString = new Array();
      
      ////////////////////////////////
      

      var markers = [];
      var map;
     
      /* 경로설정을 위한 현재위치 */
      var culat;
      var culng;
 
      
      function initMap() {
    	  directionsService = new google.maps.DirectionsService;
          directionsDisplay = new google.maps.DirectionsRenderer;
    	  map = new google.maps.Map(document.getElementById('map'), {
              center: {lat: -34.397, lng: 150.644},
              zoom: 15
            });
            var infoWindow = new google.maps.InfoWindow({map: map});
		
            // Try HTML5 geolocation.
            if (navigator.geolocation) {
              navigator.geolocation.getCurrentPosition(function(position) {
                var pos = {
                  lat: position.coords.latitude,
                  lng: position.coords.longitude
                };
                
                culat=position.coords.latitude;
                culng=position.coords.longitude;
                
          
                console.log(pos);
                
                	$.ajax({
                		url : '/quick/json/GoogleMap',
                		method:'POST',
                		data:JSON.stringify({
                			place:pos
                		}),
                		dataType:"json",
                		headers:{
                			'Accept' : 'application/json',
        					'Content-Type' : 'application/json'
                		},
                		success:function(location,status){
                			console.log(JSON.stringify(location.all))
                			console.log(JSON.stringify(location.all[0].placeinfo.name))
                			
                			for(var i=0; i<location.all.length; i++) {
                				placelat.push(Number(location.all[i].placeinfo.lat));
                				placelng.push(Number(location.all[i].placeinfo.lng));
                				
                				name1 = JSON.stringify(location.all[i].placeinfo.name);
                				plname.push(JSON.stringify(location.all[i].placeinfo.name));
                				plplace.push(JSON.stringify(location.all[i].placeinfo.place));
        				
                			}
                			console.log(placelat);
                			console.log(placelng);
                			console.log(plname);
                			console.log(plplace);
                			
                		}
                	});
                infoWindow.setPosition(pos);
                infoWindow.setContent('현재위치.');
                map.setCenter(pos);
              }, function() {
                handleLocationError(true, infoWindow, map.getCenter());
              });
            } else {
              // Browser doesn't support Geolocation
              handleLocationError(false, infoWindow, map.getCenter());
            }
            directionsDisplay.setMap(map);
      }
      
      
      function calculateAndDisplayRoute(directionsService, directionsDisplay) {
        directionsService.route({
          origin: culat+","+culng,
          destination: arlat+","+arlng,
          travelMode: 'TRANSIT'
        }, function(response, status) {
          if (status === 'OK') {
            directionsDisplay.setDirections(response);
          } else {
            window.alert('Directions request failed due to ' + status);
          }
        });
      }
        
      var neighborhoods = new Array();
      var tempname;
      var tempplace;
      var tempList = new Array();
      
      
       
       
      function dropAnimal() {
        	console.log(placelat.length+":ㅋㅋㅋ")
        	
        	for(var i=0; i<placelat.length;i++){
            	
            	neighborhoods.push({lat:placelat[i],lng:placelng[i]});	
            	
            }
        	
        console.log(neighborhoods)
    	  clearMarkers();
       
        
        for (var i = 0; i < neighborhoods.length; i++) {
          addMarkerWithTimeout(neighborhoods[i], i * 200);
          console.log(neighborhoods[i])
        }
      }
      var contentString = "Hi";
      
      var infowindow = [];
      var marker =[];
      var temp=0;
      var i=0;
      
      /* 경로받기 자원 */
      var dislng;
      var dislat;
      var arlng;
      var arlat;
      /* 여기까지 */
      
      
      function addMarkerWithTimeout(position, timeout) {
    	
        window.setTimeout(function() {
          plcontentString[i] = plname[temp]+"<br/>"+plplace[temp]
      	  
        	temp+=1;
        	console.log(temp);
        	
          markers.push(marker[i]=new google.maps.Marker({
            position: position,
            map: map,
            animation: google.maps.Animation.DROP
          }),
          
          infowindow[i] = new google.maps.InfoWindow({
            content: plcontentString[i]
          }));
          marker[i].index=i;
          console.log(marker[i].index);
          
          marker[i].addListener('click', function() {
        	  
              console.log(this.index)
        	  infowindow[this.index].open(map, marker[this.index]);
              
              arlat=placelat[this.index];
              arlng=placelng[this.index];
              calculateAndDisplayRoute(directionsService, directionsDisplay);
             
              console.log("여기보세요!")
              console.log(culat+","+culng)
              console.log(arlat+","+arlng)
              
            });
          i+=1;
        }, timeout);
       
              
      }

      function clearMarkers() {
        for (var i = 0; i < markers.length; i++) {
          markers[i].setMap(null);
        }
        markers = [];
      }
      
      function handleLocationError(browserHasGeolocation, infoWindow, pos) {
          infoWindow.setPosition(pos);
          infoWindow.setContent(browserHasGeolocation ?
                                'Error: The Geolocation service failed.' :
                                'Error: Your browser doesn\'t support geolocation.');
        }
      
        
        
      
    </script>
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAyJUiL4ifUuucPRfc1SDXbO1kv-ci_CtE&callback=initMap">
    </script>
        
    
  </body>
</html>