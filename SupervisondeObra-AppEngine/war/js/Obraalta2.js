/**
 * 
 */

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
    function clearMarkers(){
    	
    	var retVal = confirm("Desea modificar la ubicacion?");
    	   if( retVal == true ){
    	  
    	   poligonocreado=false;
    	  

    	  
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
	
	var retVal = confirm("Desea modificar la ubicacion?");
   if( retVal == true ){
  
   poligonocreado=false;
  

  
  setAllMap(null);
     puntosPoligono = [];
   // document.getElementById("guardar").style.display="block"

  }
   
	}	
		});
										
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////
function init(){
		var x = document.getElementById("dependenciaselect");
		var y = document.getElementById("secretariaselect");
		var z = document.getElementById("gobiernoselect");
		var p = document.getElementById("proyectoselect");
		var c = document.getElementById("contratistaselect");
		//alert("estoy en el init");
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
							console.log('');
							//document.getElementById("newsecretaria").style.display="none"
						
						if(dependencia.length == 0)	document.getElementById("dependenciadiv").style.display="none"
						else
							console.log('');
							//document.getElementById("newdependencia").style.display="none"
					
						if(gobierno.length == 0)	document.getElementById("gobiernodiv").style.display="none"	
						else
							console.log('');
							//document.getElementById("newgobierno").style.display="none"	
						if(proyecto.length == 0)	document.getElementById("proyectodiv").style.display="none"	
						else
							console.log('');
							//document.getElementById("newproyect").style.display="none"	
						if(proyecto.length == 0)	document.getElementById("contratistadiv").style.display="none"	
						else
							console.log('');
							//document.getElementById("newcontratista").style.display="none"	
									
					}

				},
				error: function(jHR,e,throwsError){
					alert(e);
				}
					
		});
			$.ajax({
				url : '/altaobra',
				type : 'post',
				data : {'objectJson' : JSON.stringify('getsupervisor')},
				success: function(data){
					var y = document.getElementById("supervisorselect");
				
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
			initializemap();
	
}
function guardardatos(){

	//Es solo un ejemplo de peticion
	//var j = document.getElementById("id_Descripcion").value.trim();
	if(puntos.length==0){
		alert("inserte una ubicación");
	}

	else{
//		if( !parseInt(document.getElementById("ImporteContratoSinIVA").value))
//			alert("ImporteContratoSinIVA debe ser un número");
		 if( !parseInt(document.getElementById("ImporteFiscal1SinIVA").value))
			alert("ImporteFiscal1SinIVA debe ser un número");
		else if(document.getElementById("ImporteConvenioAmpliacion").value.trim()!="" && !parseInt(document.getElementById("ImporteConvenioAmpliacion").value.trim()))
			alert("ImporteConvenioAmpliacion debe ser un número");
		else if(document.getElementById("ImporteConvenioReduccion").value.trim()!="" && !parseInt(document.getElementById("ImporteConvenioReduccion").value.trim()))
			alert("ImporteConvenioReduccion debe ser un número");
		else if(document.getElementById("ImporteAjusteCostos").value.trim()!="" && !parseInt(document.getElementById("ImporteAjusteCostos").value.trim()))
			alert("ImporteAjusteCostos debe ser un número");
		else if( !parseInt(document.getElementById("PeriodoEjucionDias").value))
			alert("PeriodoEjucionDias debe ser un número");
		else if( !parseInt(document.getElementById("PartidaPresupuestal").value))
			alert("PartidaPresupuestal debe ser un número");
		else if(document.getElementById("Anticipo").value.trim()!="" && !parseInt(document.getElementById("Anticipo").value.trim()))
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
		else if(!validate_fechaMayorQue($('#FechaInicioContrato').val().trim(),$('#FechaTerminoContrato').val().trim()))
			alert("La fecha de Termino de Contrato "+$('#FechaTerminoContrato').val().trim()+" NO es superior a la fecha de inicio de contrato "+$('#FechaInicioContrato').val().trim());
		
	//	<div>Limite de Desvio: <input type="text" id="limiteDesvio"/></div>
		var supervisor = $( "#supervisorselect option:selected" ).val();
	var object = {
					'datosArrayUbicacion' : puntos,
					'noContrato' : $('#NoContrato').val().trim(),
					'proyecto' : $('#proyectoselect').val().trim(),
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
					'borrador' : 0,
					'limiteDesvio' : $('#limiteDesvio').val().trim(),
					'supervisor':supervisor,
					'porcentajeDesvio':  $('#PorcentajeAnticipo').val().trim()
	};
	console.log(object);
	$.ajax({
		url : '/altaobra',
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
						'message'	: 'Obra insertada correctamente. <br />¿Desea asignar el directorio a la obra ahora?',
						'buttons'	: {
							'Otro Momento'	: {
								'class'	: 'gray',
								'action': function(){
									location.href="ListaObras.html";
								}
							},
							'Aceptar'	: {
								'class'	: 'blue',
								'action': function(){
									location.href="Directorioobra.html?idObra="+data;
								}
							}
						}
					});
				 document.getElementById("demo").innerHTML = x;
//				 if (confirm("Obra insertada correctamente, ¿desea asignar el directorio a la obra ahora?") == true) {
//				    	location.href="Directorioobra.html?idObra="+data;
//
//				    } else {
//				    	location.href="ListaObras.html";
//				    }
//				    document.getElementById("demo").innerHTML = x;
			 }	
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

function enviar(){
	if(document.getElementById("NoContrato").value.trim()==""|
     	   document.getElementById("proyectoselect").value.trim()==""|
     	   document.getElementById("Nombre").value.trim()==""|
     	   document.getElementById("gobiernoselect").value.trim()==""|
     	   document.getElementById("secretariaselect").value.trim()==""|
     	   document.getElementById("dependenciaselect").value.trim()==""|
//     	   document.getElementById("Direccion").value.trim()==""|
//     	   document.getElementById("Subdireccion").value.trim()==""|
//     	   document.getElementById("Area").value.trim()==""|
     	   document.getElementById("contratistaselect").value.trim()==""|
     	   document.getElementById("Superintendente").value.trim()==""|
     	   document.getElementById("EntidadFederativa").value.trim()==""|
     	   document.getElementById("Descripcion").value.trim()==""|
     	   document.getElementById("FechaContrato").value.trim()==""|
     	   document.getElementById("TipoContrato").value.trim()==""|
     	 //  document.getElementById("ImporteContratoSinIVA").value.trim()==""|
     	   document.getElementById("NombreEjercicioFiscal1").value.trim()==""|
     	   document.getElementById("ImporteFiscal1SinIVA").value.trim()==""|
//     	   document.getElementById("ImporteConvenioAmpliacion").value.trim()==""|
//     	   document.getElementById("ImporteConvenioReduccion").value.trim()==""|
//     	   document.getElementById("ImporteAjusteCostos").value.trim()==""|
     	   document.getElementById("FechaInicioContrato").value.trim()==""|
     	   document.getElementById("FechaTerminoContrato").value.trim()==""|
     	   document.getElementById("PeriodoEjucionDias").value.trim()==""|
     	   document.getElementById("PartidaPresupuestal").value.trim()==""|
//     	   document.getElementById("Anticipo").value.trim()==""|
     	   document.getElementById("NoFianzaAnticipo").value.trim()==""|
     	   document.getElementById("FechaFianzaAnticipo").value.trim()==""|
     	   document.getElementById("MontoFianzaAnticipo").value.trim()==""|
     	   document.getElementById("NoFianzaCumplimiento").value.trim()==""|
     	   document.getElementById("FechaFianzaCumplimiento").value.trim()==""|
     	   document.getElementById("MontoFianzaCumplimiento").value.trim()==""|
//     	   document.getElementById("CargoRevision1").value.trim()==""|
//     	   document.getElementById("NombreRevision1").value.trim()==""|
//     	   document.getElementById("CargoRevision2").value.trim()==""|
//     	   document.getElementById("NombreRevision2").value.trim()==""|
     	   document.getElementById("NombreQuienAutoriza").value.trim()==""|
//     	   document.getElementById("CargoVoBo").value.trim()==""|
//     	   document.getElementById("NombreVoBo").value.trim()==""|
     	  // document.getElementById("Borrador").value.trim()==""|
//     	  document.getElementById("PorcentajeAnticipo").value.trim()==""|
     	   document.getElementById("limiteDesvio").value.trim()=="")	alert('ingrese todos los valores');
//	else  if( !parseInt(document.getElementById("ImporteContratoSinIVA").value))
//			alert("ImporteContratoSinIVA debe ser un número");
		else if( !parseInt(document.getElementById("ImporteFiscal1SinIVA").value))
			alert("ImporteFiscal1SinIVA debe ser un número");
		else if(document.getElementById("ImporteConvenioAmpliacion").value.trim()!="" && !parseInt(document.getElementById("ImporteConvenioAmpliacion").value.trim()))
			alert("ImporteConvenioAmpliacion debe ser un número");
		else if(document.getElementById("ImporteConvenioReduccion").value.trim()!="" && !parseInt(document.getElementById("ImporteConvenioReduccion").value.trim()))
			alert("ImporteConvenioReduccion debe ser un número");
		else if(document.getElementById("ImporteAjusteCostos").value.trim()!="" && !parseInt(document.getElementById("ImporteAjusteCostos").value.trim()))
			alert("ImporteAjusteCostos debe ser un número");
		else if( !parseInt(document.getElementById("PeriodoEjucionDias").value))
			alert("PeriodoEjucionDias debe ser un número");
		else if( !parseInt(document.getElementById("PartidaPresupuestal").value))
			alert("PartidaPresupuestal debe ser un número");
		else if(document.getElementById("Anticipo").value.trim()!="" && !parseInt(document.getElementById("Anticipo").value.trim()))
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
		else if(!validate_fechaMayorQue($('#FechaInicioContrato').val().trim(),$('#FechaTerminoContrato').val().trim()))
			alert("La fecha de Termino de Contrato "+$('#FechaTerminoContrato').val().trim()+" NO es superior a la fecha de inicio de contrato "+$('#FechaInicioContrato').val().trim());
		else
	  guardardatos();
}
//////////////////////////////////////////////////////////////////////////////////////////////////

$(function () {
	$("#FechaContrato").datepicker();
	});
$(function () {
	$("#FechaInicioContrato").datepicker();
	});
$(function () {
	$("#FechaTerminoContrato").datepicker();
	});
$(function () {
	$("#FechaFianzaAnticipo").datepicker();
	});
$(function () {
	$("#FechaFianzaCumplimiento").datepicker();
	});

function justNumbers(e){
var keynum = window.event ? window.event.keyCode : e.which;
if ((keynum == 8) || (keynum == 46))
return true;
 
return /\d/.test(String.fromCharCode(keynum));
}

function calcular(){
	var porcentaje = $('#PorcentajeAnticipo').val().trim();
	var importe = $('#ImporteContratoSinIVA').val().trim();
	var anticipo = "";
	if(porcentaje != "" && !isNaN(porcentaje) && importe !="" && !isNaN(importe)){
		anticipo = redondeodecimales(porcentaje * importe);
		document.getElementById("Anticipo").value = anticipo;
	}else{
		document.getElementById("Anticipo").value = "";
	}
}

function redondeodecimales(numero){
	var flotante = parseFloat(numero);
	var resultado = Math.round(flotante*100)/100;
	return resultado;
}

function calcularEjecusionDias(){
	var fechaInicio = $('#FechaInicioContrato').val().trim();
	var fechaFin = $('#FechaTerminoContrato').val().trim();
	
	if(fechaInicio != "" && fechaFin !=""){
		var dias = restaFechas(fechaInicio, fechaFin);
		document.getElementById("PeriodoEjucionDias").value = dias;
	}else{
		document.getElementById("PeriodoEjucionDias").value = "";
	}
}

restaFechas = function(f1,f2){
	var aFecha1 = f1.split('/'); 
	var aFecha2 = f2.split('/'); 
	var fFecha1 = Date.UTC(aFecha1[2],aFecha1[1]-1,aFecha1[0]); 
	var fFecha2 = Date.UTC(aFecha2[2],aFecha2[1]-1,aFecha2[0]); 
	var dif = fFecha2 - fFecha1;
	var dias = Math.floor(dif / (1000 * 60 * 60 * 24)); 
	return dias;
}

function validate_fechaMayorQue(fechaInicial,fechaFinal){
    valuesStart=fechaInicial.split("/");
    valuesEnd=fechaFinal.split("/");

    // Verificamos que la fecha no sea posterior a la actual
    var dateStart=new Date(valuesStart[2],(valuesStart[1]-1),valuesStart[0]);
    var dateEnd=new Date(valuesEnd[2],(valuesEnd[1]-1),valuesEnd[0]);
    if(dateStart>=dateEnd)
    {
        return 0;
    }
    return 1;
}
