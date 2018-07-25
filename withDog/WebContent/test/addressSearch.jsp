<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
  <head>
  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
  <script>
  var address = encodeURIComponent('대전광역시 유성구 갑동로15번길 20-39 (갑동)');
  $.ajax({
		url : 'http://maps.googleapis.com/maps/api/geocode/json?sensor=false&language=ko&address='+address,
		method:'GET',
		headers:{
			'Accept' : 'application/json',
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		},
		success:function(location,status){
			console.log(location)			
		}
	});
  </script>
  </head>
<body>
<!-- <div id="locationField">
  <input id="autocomplete" placeholder="Enter your address" type="text" value="서울특별시 역삼동 강남역">
</div> -->
  
<input class="field" id="lat" />
<input class="field" id="lng" />
<!-- Replace the value of the key parameter with your own API key. -->
<!-- <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDtz18kZbobsZXEZs7SyXN_2XBgP9kB39k&libraries=places&callback=initAutocomplete" async defer></script> -->
 
 <jsp:include page="/test/GoogleMapGeo.jsp"></jsp:include>
  </body>
</html>