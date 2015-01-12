/**
 * 
 */function init(){
		var x = document.getElementById("dependenciaselect");
		var y = document.getElementById("secretariaselect");
		var z = document.getElementById("gobiernoselect");
		var p = document.getElementById("proyectoselect");
		var c = document.getElementById("contratistaselect");
		alert("estoy en el init");
		var peticion = 'empresas';
		var peticionproyectos = 'proyectos';
		var dependencia =[];
		var secretaria = [];
		var gobierno = [];
		var proyecto = [];
		var contratista = [];
		var object = {
				'peticion' :  peticion
			};
		var object2 = {
				'peticion' :  peticionproyectos
			};
		
			$.ajax({
				url : '/altaobra',
				type : 'post',
				data : {'objectJson' : JSON.stringify(peticion)},
				success: function(data){
					//alert("todo bn");
					if(data.length == 0){
						//alert("el arreglo viene vacio");
							document.getElementById("gobiernodiv").style.display="none"
							document.getElementById("secretariadiv").style.display="none"
							document.getElementById("dependenciadiv").style.display="none"
							document.getElementById("proyectodiv").style.display="none"
							document.getElementById("contratistadiv").style.display="none"
								
					}else{
						for (var t = 0; t < data.length; t++) {
							var option = document.createElement("option");
							if(data[t].tipoEmpresa == "Secretaria"){
								secretaria.push(data[t]);
								option.text = data[t].nombre;
								y.add(option);
							}else if(data[t].tipoEmpresa == "Dependencia"){
								dependencia.push(data[t]);
								option.text = data[t].nombre;
								x.add(option);
							}else if(data[t].tipoEmpresa == "proyecto"){
								proyecto.push(data[t]);
								option.text = data[t].nombre;
								p.add(option);
							}else if(data[t].tipoEmpresa == "Contratista"){
								contratista.push(data[t]);
								option.text = data[t].nombre;
								c.add(option);
							}
							else{
								gobierno.push(data[t]);
								option.text = data[t].nombre;
								z.add(option);
								
							}
							
						}
						if(secretaria.length == 0)document.getElementById("secretariadiv").style.display="none"
						else
							document.getElementById("newsecretaria").style.display="none"
						
						if(dependencia.length == 0)	document.getElementById("dependenciadiv").style.display="none"
						else
							document.getElementById("newdependencia").style.display="none"
					
						if(gobierno.length == 0)	document.getElementById("gobiernodiv").style.display="none"	
						else
							document.getElementById("newgobierno").style.display="none"	
						if(proyecto.length == 0)	document.getElementById("proyectodiv").style.display="none"	
						else
							document.getElementById("newproyect").style.display="none"	
						if(proyecto.length == 0)	document.getElementById("contratistadiv").style.display="none"	
						else
							document.getElementById("newcontratista").style.display="none"	
									
					}

				},
				error: function(jHR,e,throwsError){
					alert(e);
				}
					
		});
		
			
			
			
			
			
			
			
			$.ajax({
				url : '/altaobra',
				type : 'post',
				data : {'objectJson' : JSON.stringify('supervisor')},
				success: function(data){
					//alert("todo bn")
						//alert("el arreglo viene vacio");
							//document.getElementById("gobiernodiv").style.display="none"
								//supervisorselect
					var c = document.getElementById("supervisorselect");
								
						for (var t = 0; t < data.length; t++) {
							var option = document.createElement("option");
							if(data[t].tipoEmpresa == "Secretaria"){
								secretaria.push(data[t]);
								option.text = data[t].nombre;
								y.add(option);
							}
							
						}
	
									
					

				},
				error: function(jHR,e,throwsError){
					alert(e);
				}
					
		});
			
			
			
	
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


											
											

											
}
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

google.maps.event.addDomListener(window, 'load', initialize);

$('#enviar').click(function(e){
	//Es solo un ejemplo de peticion
	//var j = document.getElementById("id_Descripcion").value.trim();
	if(puntos.length==0){
		alert("inserte una ubicación");
	}
	

	else{
	

		if( !parseInt(document.getElementById("NoContrato").value))
			alert("NoContrato debe ser un número");
		else if( !parseInt(document.getElementById("ImporteContratoSinIVA").value))
			alert("ImporteContratoSinIVA debe ser un número");
		else if( !parseInt(document.getElementById("ImporteFiscal1SinIVA").value))
			alert("ImporteFiscal1SinIVA debe ser un número");
		else if( !parseInt(document.getElementById("ImporteConvenioAmpliacion").value))
			alert("ImporteConvenioAmpliacion debe ser un número");
		else if( !parseInt(document.getElementById("ImporteConvenioReduccion").value))
			alert("ImporteConvenioReduccion debe ser un número");
		else if( !parseInt(document.getElementById("ImporteAjusteCostos").value))
			alert("ImporteAjusteCostos debe ser un número");
		else if( !parseInt(document.getElementById("PeriodoEjucionDias").value))
			alert("PeriodoEjucionDias debe ser un número");
		else if( !parseInt(document.getElementById("PartidaPresupuestal").value))
			alert("PartidaPresupuestal debe ser un número");
		else if( !parseInt(document.getElementById("Anticipo").value))
			alert("Anticipo debe ser un número");
		else if( !parseInt(document.getElementById("NoFianzaAnticipo").value))
			alert("NoFianzaAnticipo debe ser un número");
		else if( !parseInt(document.getElementById("MontoFianzaAnticipo").value))
			alert("MontoFianzaAnticipo debe ser un número");
		else if( !parseInt(document.getElementById("NoFianzaCumplimiento").value))
			alert("NoFianzaCumplimiento debe ser un número");
		else if( !parseInt(document.getElementById("MontoFianzaCumplimiento").value))
			alert("MontoFianzaCumplimiento debe ser un número");
		else if( !parseInt(document.getElementById("limiteDesvio").value))
			alert("Limite de Desvio debe ser un número");
														
	//	<div>Limite de Desvio: <input type="text" id="limiteDesvio"/></div>
		
	var object = {
					'datosArrayUbicacion' : puntos,
					'noContrato' : $('#NoContrato').val().trim(),
					'proyecto' : $('#proyectoselect').val().trim(),
					'rfc' : $('#RFC').val().trim(),
					'nombre' : $('#Nombre').val().trim(),
					'gobierno' : $('#gobiernoselect').val().trim(),
					'secretaria' : $('#secretariaselect').val().trim(),
					'dependencia' : $('#dependenciaselect').val().trim(),
					'direccion' : $('#Direccion').val().trim(),
					'subdireccion' : $('#Subdireccion').val().trim(),
					'area' : $('#Area').val().trim(),
					'contratista' : $('#contratistaselect').val().trim(),
					'superintendente' : $('#Superintendente').val().trim(),
					'entidadFederativa' : $('#EntidadFederativa').val().trim(),
					'descripcion' : $('#Descripcion').val().trim(),
					'fechaContrato' : $('#FechaContrato').val().trim(),
					'tipoContrato' : $('#TipoContrato').val().trim(),
					'importeContratoSinIva' : $('#ImporteContratoSinIVA').val().trim(),
					'nombreEjercicioFiscal1' : $('#NombreEjercicioFiscal1').val().trim(),
					'importeFiscal1SinIva' : $('#ImporteFiscal1SinIVA').val().trim(),
					'importeConvenioAmpliacion' : $('#ImporteConvenioAmpliacion').val().trim(),
					'importeConvenioReduccion' : $('#ImporteConvenioReduccion').val().trim(),
					'importeAjusteCostos' : $('#ImporteAjusteCostos').val().trim(),
					'fechaInicioContrato' : $('#FechaInicioContrato').val().trim(),
					'fechaTerminoContrato' : $('#FechaTerminoContrato').val().trim(),
					'perdiodoEjecucionDias' : $('#PeriodoEjucionDias').val().trim(),
					'partidaPresupuestal' : $('#PartidaPresupuestal').val().trim(),
					'anticipo' : $('#Anticipo').val().trim(),
					'noFianzaAnticipo' : $('#NoFianzaAnticipo').val().trim(),
					'fechaFianzaAnticipo' : $('#FechaFianzaAnticipo').val().trim(),
					'montoFianzaAnticipo' : $('#MontoFianzaAnticipo').val().trim(),
					'noFianzaCumplimiento' : $('#NoFianzaCumplimiento').val().trim(),
					'fechaFianzaCumplimiento' : $('#FechaFianzaCumplimiento').val().trim(),
					'montoFianzaCumplimiento' : $('#MontoFianzaCumplimiento').val().trim(),
					'cargoRevision1' : $('#CargoRevision1').val().trim(),
					'nombreRevision1' : $('#NombreRevision1').val().trim(),
					'cargoRevision2' : $('#CargoRevision2').val().trim(),
					'nombreRevision2' : $('#NombreRevision2').val().trim(),
					'nombreQuienAutoriza' : $('#NombreQuienAutoriza').val().trim(),
					'cargoVoBo' : $('#CargoVoBo').val().trim(),
					'nombreVoBo' : $('#NombreVoBo').val().trim(),
					'borrador' : $('#Borrador').val().trim(),
					'limiteDesvio' : $('#limiteDesvio').val().trim(),

	};
	console.log(object);
	$.ajax({
		url : '/altaobra',
		type : 'post',
		data : {'objectJson' : JSON.stringify(object)},
		success: function(data){ //Si el request se ejecuta correctamente y el response también, se ejecutara automaticamente el success ok, en este caso puse una funcion anonima
			console.log(data);  //puedes poner una funcion definida con n nombre u otra cosa va, y si regresas datos, estos datos regresaran sobre la variable data
		//	$('#respuesta').val("resultado de la evaluacion: "+data);
			alert(data);
		},						//normalmente se regresan json y javascri´t los parsea automaticamente, es decir ahora hacemos lo contrario de una clase de java pasamos a json y regresamos
		
		error : function(jHR, e, throwsError){ //si el request o el response tienen problemas, se ejecuta el metodo error, puse una funcion anonima, la variable e, se carga con el error, que puedes
			/*console.log('Status de la peticion: ' + jHR.status); 
			console.log('texto del status: ' + jHR.statusText); 
			console.log('texto de la respuesta: ' + jHR.responseText);*/
			alert(e);
			if(jHR.status=="400") alert('no a insertado los puntos'); 
							//especificar desde el servlet, estamos???? si ok, hay dos cosas, dependiendo de lo que necesites:
		}					// 1.- Un servlet puede regresar hacia una vista (jsp)
							// 2.- Un servlet puede regresar un flujo de datos (json, img, archivos, xml, ect)
							// 3.- Un servlet puede regresar hacia otro servlet o hacer un response redirect (reenvia hacia otro dominio, sale de su capa web)	
	});
  }
});

});