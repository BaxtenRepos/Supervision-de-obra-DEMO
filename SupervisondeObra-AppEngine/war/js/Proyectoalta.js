function init(){
		var x = document.getElementById("empresa");
		var y = document.getElementById("secretaria");
		//alert("estoy en el init");
		var peticion = 'peticion';
		var dependencia =[];
		var secretaria = [];
		var object = {
				'peticion' :  peticion
			};
		
			$.ajax({
				url : '/altaproyecto',
				type : 'post',
				data : {'objectJson' : JSON.stringify(peticion)},
				success: function(data){
					//alert("todo bn");
					if(data.length == 0){
						//alert("el arreglo viene vacio");
					//		document.getElementById("divv").style.display="none"
					//		document.getElementById("divv2").style.display="none"
					}else{
						for (var t = 0; t < data.length; t++) {
							var option = document.createElement("option");
							if(data[t].tipoEmpresa == "Secretaria"){
								secretaria.push(data[t]);
							
								option.text = data[t].nombre;
								y.add(option);
							}else{
								dependencia.push(data[t]);
							
								option.text = data[t].nombre;
								x.add(option);
							}
						}
						if(secretaria.length == 0) document.getElementById("divv2").style.display="none"
						else {
							document.getElementById("altasecretaria").style.display="none"	
						}
						if(dependencia.length == 0)	document.getElementById("divv").style.display="none"
						else{
							document.getElementById("altaempresa").style.display="none"	
							
						}
					}

				},
				error: function(jHR,e,throwsError){
					alert(e);
				}
					
		});
			
			$.ajax({
				url : '/altaproyecto',
				type : 'post',
				data : {'objectJson' : JSON.stringify('directivos')},
				success: function(data){
					var y = document.getElementById("directivo");
				
				for(var t=0;t<data.length;t++){
					var option = document.createElement("option");
					option.value = data[t].id;
					option.text = data[t].nombre;
					y.add(option);
					
					
				}	
					
				},
				error: function(jHR,e,throwsError){
					alert(e);
				}
					
		});
		
	
}
function changedirectivo(){
	
	var texto = $( "#directivo option:selected" ).text();
	//alert(texto);
	var texto1 = $( "#directivo option:selected" ).val();
	//alert(texto1);
}

$(function(){
	var cantidad_elementos;
	var puntosPoligono = [];
	var puntoInicialPoligono = [];
	var idPuntosPoligono = [];
	var map;
	var poligonocreado=false;
	var puntos=[];
	var bermudaTriangle;
	var flightPath; 
        var id_ubicaciones;
        var cantidad_ubicaciones;
        
        
        
        
        
        
        
        $('#mapaenviar').click(function(e){
        	
        	if(document.getElementById("id_Descripcion").value.trim()==""|
        	   document.getElementById("nombre_corto").value.trim()==""|
        	   document.getElementById("nombre_largo").value.trim()==""|
        	   document.getElementById("secretaria").value.trim()==""|
        	   document.getElementById("empresa").value.trim()==""){
        	alert('ingrese todos los valores antes de seleccionar la ubicacion');
        		
        	}else{ 
       	 
       	 document.getElementById('map-canvas').setAttribute("style","width:1200px");
       	  document.getElementById('map-canvas').setAttribute("style","height:800px");
       	   var mapOptions = {
       	    zoom: 5,
       	    center: new google.maps.LatLng(22.936025, -101.626261)
       	  };
       	  map = new google.maps.Map(document.getElementById('map-canvas'),
       	      mapOptions);


       	google.maps.event.addListener(map, 'click', function(event) {
       		
       		if(poligonocreado!=true){		
       				var punto = event.latLng;
       					var markerPoligono = new google.maps.Marker({
       					    position: punto,
       					    map: map
       					    
       					  });
       					  	if(punto!=puntosPoligono[0]){
       					  	puntos.push(punto);
       						puntosPoligono.push(markerPoligono);
       						}
       						
       						
       					google.maps.event.addListener(markerPoligono, 'click', function() {
       					    if(puntosPoligono[0].position==markerPoligono.getPosition()){
       					    puntosPoligono.push(markerPoligono.getPosition());
       					    puntos.push(markerPoligono.getPosition());
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
       		
       		var retVal = confirm("Desea modificar el poligono?");
       	   if( retVal == true ){
       	  
       	   poligonocreado=false;
       	  

       	  
       	  setAllMap(null);
       	     puntosPoligono = [];
       	    document.getElementById("guardar").style.display="block"

       	  }
       	   
       		}	
       			});
       	 

       	    
       	    $( "#dialog-message" ).dialog({
       	      modal: true,
       	      resizable: false,
       	      width:1200,
       	      height:800,
       	      buttons: {
       	        Ok: function() {
       	        	
       	        	if(puntos.length==0){
       	     		alert("inserte una ubicación");
       	     	}else{
       	     	 $( this ).dialog( "close" );
         	       guardardatos();
       	     	}
            

       	        }
       	      }
       	    });
        	}
       	  });
        /*
	function initialize() {
  var mapOptions = {
    zoom: 6,
    center: new google.maps.LatLng(22.936025, -101.626261)
  };
  map = new google.maps.Map(document.getElementById('map-canvas'),
      mapOptions);


google.maps.event.addListener(map, 'click', function(event) {
	
	if(poligonocreado!=true){		
			var punto = event.latLng;
				var markerPoligono = new google.maps.Marker({
				    position: punto,
				    map: map
				    
				  });
				  	if(punto!=puntosPoligono[0]){
				  	puntos.push(punto);
					puntosPoligono.push(markerPoligono);
					}
					
					
				google.maps.event.addListener(markerPoligono, 'click', function() {
				    if(puntosPoligono[0].position==markerPoligono.getPosition()){
				    puntosPoligono.push(markerPoligono.getPosition());
				    puntos.push(markerPoligono.getPosition());
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
	
	var retVal = confirm("Desea modificar el poligono?");
   if( retVal == true ){
  
   poligonocreado=false;
  

  
  setAllMap(null);
     puntosPoligono = [];
    document.getElementById("guardar").style.display="block"

  }
   
	}	
		});


											
											

											
}*/
        
 
        
function setAllMap(mapa) {
 //alert("setAll puntosPoligono.length: " + puntosPoligono.length);
  for (var i = 0; i < puntosPoligono.length-1; i++) {
    puntosPoligono[i].setMap(null);
  }
  puntosPoligono=[];
  puntos=[];
  if(bermudaTriangle)
  bermudaTriangle.setMap(null);
  flightPath.setMap(null);
  
}

function placeMarker(position, map) {
  var marker = new google.maps.Marker({
    position: position,
    map: map
  });

}

	function poligono(){
//"block"

//document.getElementById("guardar").style.display="none"
poligonocreado=true;
		bermudaTriangle = new google.maps.Polygon({
		    paths: puntos,
		    strokeColor: '#FFFF00',
		    strokeOpacity: 0.8,
		    strokeWeight: 3,
		    fillColor: '#00FF5D',
		    fillOpacity: 0.35
	  	});
	 	bermudaTriangle.setMap(map);
	


	}
	function getConfirmation(){
   var retVal = confirm("No se a cerrado un poligono, desea guardar?");
   if( retVal == true ){
    document.getElementById("guardar").style.display="none"
    poligonocreado=true;

	  return true;
   }else{
     // alert("User does not want to continue!");
	  return false;
   }
}
function funcion(){

alert("soy la funcion");

}
function guardardatos(){
	
	//Es solo un ejemplo de peticion
	var j = document.getElementById("id_Descripcion").value.trim();
	if(puntos.length==0){
		alert("inserte una ubicación");
	}
	
	
	else if(document.getElementById("id_Descripcion").value.trim()==""){
		alert("inserte una descripcion");
	}else if(document.getElementById("nombre_corto").value.trim()==""){
		alert("inserte un nombre corto");
	}else if(document.getElementById("nombre_largo").value.trim()==""){
		alert("inserte un nombre largo");
	}
	else{
		
		var directivo = $( "#directivo option:selected" ).val();
		
	var object = {
					'datosArrayUbicacion' : puntos,
					'idDescripcion' : $('#id_Descripcion').val().trim(),
					'idDependencia' : $('#empresa').val().trim(),
					'idSecretaria' : $('#secretaria').val().trim(),
					'nombreCorto' : $('#nombre_corto').val().trim(),
					'nombreLargo' : $('#nombre_largo').val().trim(),
					'directivo'   :directivo,
				};
	console.log(object);
	$.ajax({
		url : '/altaproyecto',
		type : 'post',
		data : {'objectJson' : JSON.stringify(object)},
		success: function(data){ //Si el request se ejecuta correctamente y el response también, se ejecutara automaticamente el success ok, en este caso puse una funcion anonima
			console.log(data);  //puedes poner una funcion definida con n nombre u otra cosa va, y si regresas datos, estos datos regresaran sobre la variable data
		//	$('#respuesta').val("resultado de la evaluacion: "+data);
			alert(data);
			 var x;
			    if (confirm("Desea asignar el directorio al proyecto ahora?") == true) {
			    	location.href="Directorioproyecto.html?idProyecto="+data;

			    } else {
			    	  window.location.reload();
			    }
			    document.getElementById("demo").innerHTML = x;
     		
		},						//normalmente se regresan json y javascri´t los parsea automaticamente, es decir ahora hacemos lo contrario de una clase de java pasamos a json y regresamos
		
		error : function(jHR, e, throwsError){ //si el request o el response tienen problemas, se ejecuta el metodo error, puse una funcion anonima, la variable e, se carga con el error, que puedes
			/*console.log('Status de la peticion: ' + jHR.status); 
			console.log('texto del status: ' + jHR.statusText); 
			console.log('texto de la respuesta: ' + jHR.responseText);*/
			alert(e);
     		  window.location.reload();
			if(jHR.status=="400") alert('no a insertado los puntos'); 
							//especificar desde el servlet, estamos???? si ok, hay dos cosas, dependiendo de lo que necesites:
		}					// 1.- Un servlet puede regresar hacia una vista (jsp)
							// 2.- Un servlet puede regresar un flujo de datos (json, img, archivos, xml, ect)
							// 3.- Un servlet puede regresar hacia otro servlet o hacer un response redirect (reenvia hacia otro dominio, sale de su capa web)	
	});
  }
	
}
//google.maps.event.addDomListener(window, 'load', initialize);

$('#enviar').click(function(e){
	//Es solo un ejemplo de peticion
	var j = document.getElementById("id_Descripcion").value.trim();
	if(puntos.length==0){
		alert("inserte una ubicación");
	}
	
	
	else if(document.getElementById("id_Descripcion").value.trim()==""){
		alert("inserte una descripcion");
	}else if(document.getElementById("nombre_corto").value.trim()==""){
		alert("inserte un nombre corto");
	}else if(document.getElementById("nombre_largo").value.trim()==""){
		alert("inserte un nombre largo");
	}
	else{
	
	var object = {
					'datosArrayUbicacion' : puntos,
					'idDescripcion' : $('#id_Descripcion').val().trim(),
					'idDependencia' : $('#empresa').val().trim(),
					'idSecretaria' : $('#secretaria').val().trim(),
					'nombreCorto' : $('#nombre_corto').val().trim(),
					'nombreLargo' : $('#nombre_largo').val().trim(),
				};
	console.log(object);
	$.ajax({
		url : '/altaproyecto',
		type : 'post',
		data : {'objectJson' : JSON.stringify(object)},
		success: function(data){ //Si el request se ejecuta correctamente y el response también, se ejecutara automaticamente el success ok, en este caso puse una funcion anonima
			console.log(data);  //puedes poner una funcion definida con n nombre u otra cosa va, y si regresas datos, estos datos regresaran sobre la variable data
		//	$('#respuesta').val("resultado de la evaluacion: "+data);
			alert(data);
     		  window.location.reload();
			
		},						//normalmente se regresan json y javascri´t los parsea automaticamente, es decir ahora hacemos lo contrario de una clase de java pasamos a json y regresamos
		
		error : function(jHR, e, throwsError){ //si el request o el response tienen problemas, se ejecuta el metodo error, puse una funcion anonima, la variable e, se carga con el error, que puedes
			/*console.log('Status de la peticion: ' + jHR.status); 
			console.log('texto del status: ' + jHR.statusText); 
			console.log('texto de la respuesta: ' + jHR.responseText);*/
			alert(e);
     		  window.location.reload();
			if(jHR.status=="400") alert('no a insertado los puntos'); 
							//especificar desde el servlet, estamos???? si ok, hay dos cosas, dependiendo de lo que necesites:
		}					// 1.- Un servlet puede regresar hacia una vista (jsp)
							// 2.- Un servlet puede regresar un flujo de datos (json, img, archivos, xml, ect)
							// 3.- Un servlet puede regresar hacia otro servlet o hacer un response redirect (reenvia hacia otro dominio, sale de su capa web)	
	});
  }
});

});