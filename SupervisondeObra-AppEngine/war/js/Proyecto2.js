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
    var idDirectivos=new Array();
    
    function clearMarkers(){
    	
    	$.confirm({
			'title'		: 'Confirmaci&oacute;n',
			'message'	: '¿Desea modificar la ubicacion?',
			'buttons'	: {
				'Cancelar'	: {
					'class'	: 'gray',
					'action': function(){}
				},
				'Aceptar'	: {
					'class'	: 'blue',
					'action': function(){
						 poligonocreado=false;
				    	 setAllMap(null);
				    	 puntosPoligono = [];
					}
				}
			}
		});
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
    	  flightPath.setMap(null);
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
function initializemap() {
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
			clearMarkers();
		}	
	});
										
}

function init(){

		var x = document.getElementById("empresa");
		var y = document.getElementById("secretaria");
		//alert("estoy en el init");
		var peticion = 'peticion';
		var dependencia =[];
		var secretaria = [];
		var dependenciastring = new Array();
		var secretariastring  = new Array();
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
								secretariastring.push(data[t].nombre);
							
							//	option.text = data[t].nombre;
							//	y.add(option);
							}else{
								dependencia.push(data[t]);
								dependenciastring.push(data[t].nombre);
							//	option.text = data[t].nombre;
							//	x.add(option);
							}
						}
						if(secretaria.length == 0) document.getElementById("divv2").style.display="none"
						else {
	//						document.getElementById("altasecretaria").style.display="none"	
								secretariastring.sort();
							
								for(var t=0 ; t<secretaria.length; t++){
									var option = document.createElement("option");
									option.text = secretariastring[t];
									y.add(option);
								}
							
						}
						if(dependencia.length == 0)	document.getElementById("divv").style.display="none"
						else{
	//						document.getElementById("altaempresa").style.display="none"	
								dependenciastring.sort();
							for(var t=0 ; t<dependencia.length; t++){
								var option = document.createElement("option");
								option.text = dependenciastring[t];
								x.add(option);
							}
							
						}
						
					}

				},
				error: function(jHR,e,throwsError){
					alert(e);
				}
					
		});
		idDirectivos = [];
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
					$(document).ready(function(){
					    $("#directivo").multiSelect({
					    	  afterSelect: function(values){
					    		    idDirectivos.push(values[0]);
//					    		    alert("Select value: "+idDirectivos.length);
					    		  },
					    		  afterDeselect: function(values){
					    			  for(var i = 0; i < idDirectivos.length; i++) {
					    				    if(idDirectivos[i] == values[0]) {
					    				    	idDirectivos.splice(i, 1);
					    				    }
					    				}
//					    		    alert("Deselect value: "+idDirectivos.length);
					    		  }
					    		});
					});	
				},
				error: function(jHR,e,throwsError){
					alert(e);
				}
					
		});
		
			initializemap();
	

}
function changedirectivo(){
	
	var texto = $( "#directivo option:selected" ).text();
	//alert(texto);
	var texto1 = $( "#directivo option:selected" ).val();
	//alert(texto1);
}

function enviar(){	
//	if(document.getElementById("id_Descripcion").value.trim()==""|
//     	   document.getElementById("nombre_corto").value.trim()==""|
//     	   document.getElementById("nombre_largo").value.trim()==""|
//     	   document.getElementById("secretaria").value.trim()==""|
//     	   document.getElementById("empresa").value.trim()==""|
//		   isNaN(texto1))
//     	alert('ingrese todos los valores antes de seleccionar la ubicacion');
//     	else 
	
	var error = '<div class="alert alert-error">'+
    ' <button class="close" data-dismiss="alert"></button>';
	if(puntos.length==0){
		limpiarDiv();
		document.getElementById("errorMapa").innerHTML= error + 'Favor de introducir los puntos en el mapa. </div>';
		document.getElementById("errorGeneral").innerHTML= error + 'Favor de introducir los puntos en el mapa. </div>';
	}else if(document.getElementById("id_Descripcion").value.trim()==""){
		limpiarDiv();
		document.getElementById("errorDescripcion").innerHTML= error + 'Favor de introducir una descripcion. </div>';
		document.getElementById("errorGeneral").innerHTML= error + 'Favor de introducir una descripcion. </div>';
	}else if(document.getElementById("nombre_corto").value.trim()==""){
		limpiarDiv();
		document.getElementById("errorNombre_corto").innerHTML= error + 'Favor de introducir un nombre corto. </div>';
		document.getElementById("errorGeneral").innerHTML= error + 'Favor de introducir un nombre corto. </div>';
	}else if(document.getElementById("nombre_largo").value.trim()==""){
		limpiarDiv();
		document.getElementById("errorNombre_largo").innerHTML= error + 'Favor de introducir un nombre largo. </div>';
		document.getElementById("errorGeneral").innerHTML= error + 'Favor de introducir un nombre largo. </div>';
	}else if(document.getElementById("secretaria").value.trim()==""){
		limpiarDiv();
		document.getElementById("errorSecretaria").innerHTML= error + 'Favor de introducir una Secretaria. </div>';
		document.getElementById("errorGeneral").innerHTML= error + 'Favor de introducir una Secretaria. </div>';
	}else if(document.getElementById("empresa").value.trim()==""){
		limpiarDiv();
		document.getElementById("errorEmpresa").innerHTML= error + 'Favor de introducir una Empresa. </div>';
		document.getElementById("errorGeneral").innerHTML= error + 'Favor de introducir una Empresa. </div>';
	}else if(idDirectivos.length==0){
		limpiarDiv();
		document.getElementById("errorDirectivo").innerHTML= error + 'Favor de asignar un Directivo. </div>';
		document.getElementById("errorGeneral").innerHTML= error + 'Favor de asignar un Directivo. </div>';
	}else{
		guardardatos();
	}	
}
function guardardatos(){
	
	//Es solo un ejemplo de peticion
	var j = document.getElementById("id_Descripcion").value.trim();
		
		var directivo = $( "#directivo option:selected" ).val();
		
	var object = {
					'datosArrayUbicacion' : puntos,
					'idDescripcion' : $('#id_Descripcion').val().trim(),
					'idDependencia' : $('#empresa').val().trim(),
					'idSecretaria' : $('#secretaria').val().trim(),
					'nombreCorto' : $('#nombre_corto').val().trim(),
					'nombreLargo' : $('#nombre_largo').val().trim(),
					'directivo'   : idDirectivos,
				};
	console.log(object);
	$.ajax({
		url : '/altaproyecto',
		type : 'post',
		data : {'objectJson' : JSON.stringify(object)},
		success: function(data){ //Si el request se ejecuta correctamente y el response también, se ejecutara automaticamente el success ok, en este caso puse una funcion anonima
			console.log(data);  //puedes poner una funcion definida con n nombre u otra cosa va, y si regresas datos, estos datos regresaran sobre la variable data
		//	$('#respuesta').val("resultado de la evaluacion: "+data);
//			alert(data);
			 var x;
			 if(!isNaN(data)){
				 $.confirm({
						'title'		: 'Confirmaci&oacute;n',
						'message'	: 'Proyecto insertado correctamente. <br />¿Desea asignar el directorio al proyecto ahora?',
						'buttons'	: {
							'Otro Momento'	: {
								'class'	: 'gray',
								'action': function(){
									location.href="PruebaProyecto.html";
								}
							},
							'Aceptar'	: {
								'class'	: 'blue',
								'action': function(){
									location.href="Directorioproyecto.html?idProyecto="+data;	// Nothing to do in this case. You can as well omit the action property.
								}
							}
						}
					});
			 }
//			    if (confirm("Proyecto insertado correctamente, desea asignar el directorio al proyecto ahora?") == true) {
//			    	location.href="Directorioproyecto.html?idProyecto="+data;
//
//			    } else {
//			    	location.href="PruebaProyecto.html";
////			    	  window.location.reload();
//			    }
//			    document.getElementById("demo").innerHTML = x;
     		
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

function limpiarDiv(){
	document.getElementById("errorMapa").innerHTML= "";
	document.getElementById("errorDescripcion").innerHTML= "";
	document.getElementById("errorNombre_corto").innerHTML= "";
	document.getElementById("errorNombre_largo").innerHTML= "";
	document.getElementById("errorSecretaria").innerHTML= "";
	document.getElementById("errorEmpresa").innerHTML= "";
	document.getElementById("errorDirectivo").innerHTML= "";
	document.getElementById("errorGeneral").innerHTML= "";
}

