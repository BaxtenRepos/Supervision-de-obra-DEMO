/**
 * 
 */
var cantidad_elementos;
	var puntosPoligono = [];

	var puntoInicialPoligono = [];
	var idPuntosPoligono = [];
	var map;
	var poligonocreado=false;
	var puntos=[];//arreglo de nuevo puntos
	var bermudaTriangle;
	var flightPath; 
    var id_ubicaciones;
    var cantidad_ubicaciones;
	var mapapuntos;
	var marker;
    
    
	var coordenadas= new Array();
	
	function init(){
		iniciamapa();
	}
	
	  function clearMarkers(){
	    	
	    	var retVal = confirm("Desea modificar la ubicacion?");
	    	   if( retVal == true ){
	    	  
	    	   poligonocreado=false;
	    	  coordenadas=[];

	    	  
	    	  setAllMap(null);
	    	     puntosPoligono = [];
	    	    //document.getElementById("guardar").style.display="block"

	    	  }
	    	
	    }
	    function setAllMap(mapa) {
	    	 //alert("setAll puntosPoligono.length: " + puntosPoligono.length);
	    	var l;
	    	if(bermudaTriangle)l=puntosPoligono.length-1;
	    	else l=puntosPoligono.length;
	    	for (var i = 0; i < l; i++) {
	    	puntosPoligono[i].setMap(null);
	    	  }
	    	  puntosPoligono=[];
	    	  puntos=[];
	    	  if(bermudaTriangle){
	    	  bermudaTriangle.setMap(null);
	    	 
	    	  bermudaTriangle=null;
	    	  
	    	  }
	    	  coordenadas=[];
	    	       flightPath.setMap(null);
	    
	    	}
function iniciamapa( ){//puntos de la base
	

	 var mapOptions = {
			    zoom: 6,
			    center: new google.maps.LatLng(22.936025, -101.626261)
			  };
			  map = new google.maps.Map(document.getElementById('map-canvas'),
			      mapOptions);
			
			  poligonocreado=true;
			  google.maps.event.addListener(map, 'click', function(event) {
					
					if(poligonocreado!=true){		
							var punto = event.latLng;
								var markerPoligono = new google.maps.Marker({
								    position: punto,
								    map: map
								    
								  });
								 // 	if(punto!=puntosPoligono[0]){
								  	puntos.push(punto);
									puntosPoligono.push(markerPoligono);
								//	}
									
									
								google.maps.event.addListener(markerPoligono, 'click', function() {
								    if(puntosPoligono[0].position==markerPoligono.getPosition()){
								    	
								    puntosPoligono.push(markerPoligono.getPosition());
								    puntos.push(markerPoligono.getPosition());
								    flightPath.setMap(null);
								    	poligono();
								    //	puntos = [];
								    }
								});
								
								if(puntosPoligono.length>1){
								if(puntosPoligono.length>2)
									flightPath.setMap(null);
									
								 for(var x=0;x<puntosPoligono.length;x++)
								 {
								 	//puntos.push(puntosPoligono.position);
								 }
				  
				    flightPath = new google.maps.Polyline({
				    path: puntos,
				    geodesic: true,
				    strokeColor: '#FF0000',
				    strokeOpacity: 1.0,
				    strokeWeight: 2
				  });

				  flightPath.setMap(map);
								}
					}	
					
					else{
					
					var retVal = confirm("Desea modificar la ubicacion?");
				   if( retVal == true ){
				  
				   poligonocreado=false;
				  

				  
				  setAllMap(null);
				     puntosPoligono = [];
				   // document.getElementById("guardar").style.display="block"

				  }
				   
					}	
						});
			  creamapa();//llamar pinta puntos

}
function poligono(){
//    	coordenadas=[];
//	puntosPoligono=[];
	for(var t=0;t<puntos.length-1;t++){
		coordenadas.push(new google.maps.LatLng(puntos[t].k,puntos[t].B));


	//	poligonocreado=true;
		
			}
	
	  // Construct the polygon.
	  bermudaTriangle = new google.maps.Polygon({
	    paths: coordenadas,
	    strokeColor: '#FF0000',
	    strokeOpacity: 0.8,
	    strokeWeight: 2,
	    fillColor: '#FF0000',
	    fillOpacity: 0.35
	  });

	  bermudaTriangle.setMap(map);
	  flightPath = new google.maps.Polyline({
		    path: puntos,
		    geodesic: true,
		    strokeColor: '#FF0000',
		    strokeOpacity: 1.0,
		    strokeWeight: 2
		  });

		  flightPath.setMap(map);
			poligonocreado=true;
}
function puntos(){
	
	
}
function creamapa(){

	var puntos2=new Array();
	$.ajax({
		url : '/getpuntos',
		type : 'post',
		data : {'objectJson' : JSON.stringify('puntos')},
		success: function(data){
			puntos=data;
			pintapuntos();
			
		},
		error: function(jHR,e,throwsError){
			alert(e);
		}

		
			
});
	
	 function pintapuntos(){
		 var tamano=puntos.length;
			var B1=puntos[0].B;
			var k1 = puntos[0].k;
			
			if(puntos[0].k==puntos[tamano-1].k&&puntos[0].B==puntos[tamano-1].B){
				
				for(var t=0;t<puntos.length;t++){
					var myLatlng = new google.maps.LatLng(puntos[t].k,puntos[t].B);
					if(t<puntos.length-1)
					var marker = new google.maps.Marker({
					      position: myLatlng,
					      map: map,
					      title: 'Hello World!'
					  });
					puntosPoligono.push(marker);
					puntos2.push(marker.getPosition());
					
			}
				poligono();
			}	
			else{
				for(var t=0;t<puntos.length;t++){
					var myLatlng = new google.maps.LatLng(puntos[t].k,puntos[t].B);
				//puntos.push(myLatlng);
					var marker = new google.maps.Marker({
					      position: myLatlng,
					      map: map,
					      title: 'Hello World!'
					  });
					puntosPoligono.push(marker);
					puntos2.push(marker.getPosition());
			
					
			}
				
				flightPath = new google.maps.Polyline({
				    path: puntos2,
				    geodesic: true,
				    strokeColor: '#FF0000',
				    strokeOpacity: 1.0,
				    strokeWeight: 2
				  });

				  flightPath.setMap(map);
			}
			

		 
	 }
}
