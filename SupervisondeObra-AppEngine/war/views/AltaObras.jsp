<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Punto by Endpoint</title>
</head>
    <style>
      html, body, #map-canvas {
        height: 87%;
        margin: 0px;
        padding: 0px;
      }
            #panel {
        position: absolute;
        top: 10px;
        left: 50%;
        margin-left: -180px;
        z-index: 5;
        background-color: #fff;
        padding: 5px;
        border: 1px solid #999;
      }
    </style>
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>
<body>


<script>
	API_URL = 'https://' + 'focal-furnace-615.appspot.com' + '/_ah/api'
	
	
	if (window.location.hostname == 'localhost'
			|| window.location.hostname == '127.0.0.1'
			|| ((window.location.port != "") && (window.location.port > 999))) {
		// We're probably running against the DevAppServer
		API_URL = 'http://' + 'focal-furnace-615.appspot.com' + '/_ah/api';
	}
	
	window.alert(API_URL);
	
	
	
	var cantidad_elementos;
	var cantidad_ubicaciones;
	
	
	var puntosPoligono = [];

	var puntoInicialPoligono = [];
	var idPuntosPoligono = [];
	var map;
	var poligonocreado=false;
	var puntos=[];
	var bermudaTriangle;
	var flightPath; 
    var id_ubicaciones;
var s;
	
	
	//Funcion que se encargara de tomar los datos insertados por el usuario, para despues insertarlos en el data store
	function guardar(){
	
		
		
		
		
	if(cantidad_elementos==0)id_Obra=1;
	else 
	id_Obra=cantidad_elementos+1;	
		
	// var  id_Obra= $('#id_Obra').val();
	 var  id_Poyecto= $('#id_Poyecto').val();
	 var  NoContrato= $('#NoContrato').val();
	 var  RFC= $('#RFC').val();
	 var  Nombre= $('#Nombre').val();
	 var  Gobierno= $('#Gobierno').val();
	 var  Secretaria= $('#Secretaria').val();
	 var  Dependencia= $('#Dependencia').val();
	 var  Direccion= $('#Direccion').val();
	 var  Subdireccion= $('#Subdireccion').val();
	 var  Area= $('#Area').val();
	 var  id_EmpresaContratista= $('#id_EmpresaContratista').val();
	 var  Superintendente= $('#Superintendente').val();
	 var  EntidadFederativa= $('#EntidadFederativa').val();
	 var  Descripcion= $('#Descripcion').val();
	 var  FechaContrato= $('#FechaContrato').val();
	 var  TipoContrato= $('#TipoContrato').val();
	 var ImporteContratoSinIVA = $('#ImporteContratoSinIVA').val();
	 var NombreEjercicioFiscal1= $('#NombreEjercicioFiscal1').val();
	 var ImporteFiscal1SinIVA= $('#ImporteFiscal1SinIVA').val();
	 var ImporteConvenioAmpliacion= $('#ImporteConvenioAmpliacion').val();
	 var ImporteConvenioReduccion= $('#ImporteConvenioReduccion').val();
	 var ImporteAjusteCostos= $('#ImporteAjusteCostos').val();
	 var FechaInicioContrato= $('#FechaInicioContrato').val();
	 var FechaTerminoContrato= $('#FechaTerminoContrato').val();
	 var PeriodoEjucionDias= $('#PeriodoEjucionDias').val();
	 var PartidaPresupuestal= $('#PartidaPresupuestal').val();
	 var Anticipo= $('#Anticipo').val();
	 var NoFianzaAnticipo= $('#NoFianzaAnticipo').val();
	 var FechaFianzaAnticipo = $('#FechaFianzaAnticipo').val();
	 var MontoFianzaAnticipo= $('#MontoFianzaAnticipo').val();
	 var NoFianzaCumplimiento= $('#NoFianzaCumplimiento').val();
	 var FechaFianzaCumplimiento= $('#FechaFianzaCumplimiento').val();
	 var MontoFianzaCumplimiento= $('#MontoFianzaCumplimiento').val();
	 var CargoRevision1= $('#CargoRevision1').val();
	 var NombreRevision1= $('#NombreRevision1').val();
	 var CargoRevision2= $('#CargoRevision2').val();
	 var NombreRevision2= $('#NombreRevision2').val();
	 var NombreQuienAutoriza= $('#NombreQuienAutoriza').val();
	 var CargoVoBo= $('#CargoVoBo').val();
	 var NombreVoBo= $('#NombreVoBo').val();
	 var Borrador= $('#Borrador').val();
		
		
		var aux = gapi.client.load('obraendpoint', 'v1', function(){

// 			
				
				var reques = gapi.client.obraendpoint.insertObra({
"anticipo":Anticipo+"",
"area": Area,
"borrador": Borrador,
"cargoRevision1": CargoRevision1,
"cargoRevision2": CargoRevision2,
"cargoVoBo": CargoVoBo,
"dependencia": Dependencia,
"direccion": Direccion,
"descripcion": Descripcion,
"entidadFederativa": EntidadFederativa,
"fechaContrato": FechaContrato,
"fechaFianzaCumplimiento": FechaFianzaCumplimiento,
"fechaFianzaAnticipo": FechaFianzaAnticipo,
"fechaInicioContrato": FechaInicioContrato,
"fechaTerminoContrato": FechaTerminoContrato,
"gobierno": Gobierno,
"id_EmpresaContratista": id_EmpresaContratista+"",
"id_Obra": id_Obra+"",
"id_Poyecto": id_Poyecto+"",
"importeAjusteCostos": parseInt(ImporteAjusteCostos),
"importeContratoSinIVA": parseInt(ImporteContratoSinIVA),
"importeConvenioAmpliacion": ImporteConvenioAmpliacion+"",
"importeConvenioReduccion": parseInt(ImporteConvenioReduccion),
"importeFiscal1SinIVA": parseInt(ImporteFiscal1SinIVA),
"montoFianzaAnticipo": parseInt(MontoFianzaAnticipo),
"montoFianzaCumplimiento": MontoFianzaCumplimiento+"",
"noContrato": NoContrato+"",
"noFianzaAnticipo": NoFianzaAnticipo,
"noFianzaCumplimiento": NoFianzaCumplimiento,
"nombre": Nombre,
"tipoContrato": TipoContrato,
"superintendente": Superintendente,
"secretaria": Secretaria,
"subdireccion": Subdireccion,
"periodoEjucionDias": PeriodoEjucionDias+"",
"rfc": RFC,
"partidaPresupuestal": PartidaPresupuestal,
"nombreVoBo": NombreVoBo,
"nombreEjercicioFiscal1": NombreEjercicioFiscal1,
"nombreQuienAutoriza": NombreQuienAutoriza,
"nombreRevision1": NombreRevision1,
"nombreRevision2": NombreRevision2
				}).execute(function(resp){

				
					window.alert(resp.message);
				});
				
			//	window.alert("Guardado");
				
			//}
			
		}, API_URL);
if(cantidad_ubicaciones==0)cantidad_ubicaciones=1;
	else 
	cantidad_ubicaciones=cantidad_ubicaciones+1;	

		
		//"lat" : event.latLng.k,
		//"lon" : event.latLng.A,
		var ubi=[];
		for(var t=0;t<puntos.length;t++)
		{
			var latitud=puntos[t].k;
			var longitud=puntos[t].B;
		ubi.push(latitud+","+longitud+"");
			
		}
		var detente;
		
				var aux2 = gapi.client.load('ubicacionesendpoint', 'v1', function(){

// 			
				



				
				
				
				
				
				var reques = gapi.client.ubicacionesendpoint.insertUbicaciones({
"id_Ubicacion" :cantidad_ubicaciones,
"id_Ubicado" : parseInt(id_Obra),
"ubicacion" : ubi,
"tipo_Entidad":1

				}).execute(function(resp){

				
					window.alert(resp.message);
				});
				
			//	window.alert("Guardado");
				
			//}
			
		}, API_URL);

		
				////insertamos la ubicacion

										

          
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


function initialize() {
  var mapOptions = {
    zoom: 6,
    center: new google.maps.LatLng(22.936025, -101.626261)
  };
  map = new google.maps.Map(document.getElementById('map-canvas'),
      mapOptions);


google.maps.event.addListener(map, 'click', function(event) {
	
	if(poligonocreado!=true){		
	//		var e = document.getElementById("areaType");
	//		var strUser = e.options[e.selectedIndex].value;

			var punto = event.latLng;
			/*gapi.client.puntoendpoint.insertPunto({
					"lat" : punto.k,
					"lon" : punto.A,
					"timestamp" : 1,
					"id_device" : 0
				}).execute(function(resp){
					arrPPids.push(resp.id);
				});
				*/
			
				
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
				    	puntos.push(punto);
					puntosPoligono.push(markerPoligono);
				    	poligono();
				    //	puntos = [];
				    }
				});
				
				if(puntosPoligono.length>1){
				// var flightPlanCoordinates = puntosPoligono.position;
				
				
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
  //  document.getElementById("delete").style.display="none"
   

  }
   
	}	
		});

/////// EndPoint
				var loadAreaPoligonal = gapi.client.load('ubicacionesendpoint','v1',function(){
												gapi.client.ubicacionesendpoint.listUbicaciones().execute(function(resp){
												//	window.alert("obraendpoint Loaded");
													//manageAreasPoligonales(resp);
													arrAreasf = resp.items;
													if(!resp.items){
													cantidad_ubicaciones=0;
													}
													else
													cantidad_ubicaciones=resp.items.length;
												
													
												
													
												});
											}, API_URL);
														var loadAreaPoligonal3 = gapi.client.load('multimediaendpoint','v1',function(){
												gapi.client.multimediaendpoint.listMultimedia().execute(function(resp){
												//	window.alert("obraendpoint Loaded");
													//manageAreasPoligonales(resp);
													arrAreasf = resp.items;
													if(!resp.items){
													cantidad_ubicaciones=0;
													}
													else
													cantidad_ubicaciones=resp.items.length;
												
													
												
													
												});
											}, API_URL);
															var reques = gapi.client.multimediaendpoint.insertMultimedia({
"idMultimedia" :"1",
"idReferencia" : 4,
"tipoArchivo" : 2,
"tipoReferencia":1,
"path":"path"


				}).execute(function(resp){

				
					window.alert(resp.message);
				});
				
			//	window.alert("Guardado");
				
			//}
			
		}, API_URL);
	

var loadAreaPoligonal1 = gapi.client.load('obraendpoint','v1',function(){
												gapi.client.obraendpoint.listObra().execute(function(resp){
												//	window.alert("obraendpoint Loaded");
													//manageAreasPoligonales(resp);
													arrAreasf = resp.items;
													if(!resp.items){
													cantidad_elementos=0;
													}
													else
													cantidad_elementos=resp.items.length;
												
													
												
													
												});
											}, API_URL);
											
											

											
}

	function init(){

		
		
	}
		
		function poligono(){
//"block"

document.getElementById("guardar").style.display="none"
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
function setAllMap(mapa) {
 //alert("setAll puntosPoligono.length: " + puntosPoligono.length);
  for (var i = 0; i < puntosPoligono.length; i++) {
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
	
google.maps.event.addDomListener(window, 'load', initialize);

</script>


 <div id="map-canvas">  </div>
    <div id="panel" style="margin-left: -52px">
        <button id="guardar" onclick="getConfirmation();">Guardar Puntos</button>

     </div>
<div>
	id_Obra: <input type="text" placeholder="numero " name="id_obra" id="id_obra"/>
</div>
<div>
	NoContrato: <input type="text"  placeholder="numero "name="NoContrato" id="NoContrato"/>
</div>
<div>
	id_Poyecto: <input type="text" placeholder="numero " name="id_Poyecto" id="id_Poyecto"/>
</div>



<div>
	RFC: <input type="text" name="RFC" id="RFC"/>
</div>

<div>
	Nombre: <input type="text" name="Nombre" id="Nombre"/>
</div>
<div>
	Gobierno: <input type="text" name="Gobierno" id="Gobierno"/>
</div>


<div>
	Secretaria: <input type="text" name="Secretaria" id="Secretaria"/>
</div>
<div>
	Dependencia: <input type="text" name="Dependencia" id="Dependencia"/>
</div>


<div>
	Direccion: <input type="text" name="Direccion" id="Direccion"/>
</div>
<div>
	Subdireccion: <input type="text" name="Subdireccion" id="Subdireccion"/>
</div>


<div>
	Area: <input type="text" name="Area" id="Area"/>
</div>
<div>
	id_EmpresaContratista: <input type="text" placeholder="numero " name="id_EmpresaContratista" id="id_EmpresaContratista"/>
</div>


<div>
	Superintendente: <input type="text" name="Superintendente" id="Superintendente"/>
</div>
<div>
	EntidadFederativa: <input type="text" name="EntidadFederativa" id="EntidadFederativa"/>
</div>


<div>
	Descripcion: <input type="text" name="Descripcion" id="Descripcion"/>
</div>
<div>
	FechaContrato: <input type="text" name="FechaContrato" id="FechaContrato"/>
</div>


<div>
	TipoContrato: <input type="text" name="TipoContrato" id="TipoContrato"/>
</div>
<div>
	ImporteContratoSinIVA: <input type="text" placeholder="numero " name="ImporteContratoSinIVA" id="ImporteContratoSinIVA"/>
</div>


<div>
	NombreEjercicioFiscal1: <input type="text" name="NombreEjercicioFiscal1" id="NombreEjercicioFiscal1"/>
</div>
<div>
	ImporteFiscal1SinIVA: <input type="text" placeholder="numero " name="ImporteFiscal1SinIVA" id="ImporteFiscal1SinIVA"/>
</div>


<div>
	ImporteConvenioAmpliacion: <input placeholder="numero " type="text" name="ImporteConvenioAmpliacion" id="ImporteConvenioAmpliacion"/>
</div>
<div>
	ImporteConvenioReduccion: <input type="text" placeholder="numero " name="ImporteConvenioReduccion" id="ImporteConvenioReduccion"/>
</div>


<div>
	ImporteAjusteCostos: <input type="text" placeholder="numero " name="ImporteAjusteCostos" id="ImporteAjusteCostos"/>
</div>
<div>
	FechaInicioContrato: <input type="text" name="FechaInicioContrato" id="FechaInicioContrato"/>
</div>




<div>
	FechaTerminoContrato: <input type="text" name="FechaTerminoContrato" id="FechaTerminoContrato"/>
</div>
<div>
	PeriodoEjucionDias: <input  placeholder="numero "type="text" name="PeriodoEjucionDias" id="PeriodoEjucionDias"/>
</div>
<div>
	PartidaPresupuestal: <input  placeholder="numero "type="text" name="PartidaPresupuestal" id="PartidaPresupuestal"/>
</div>
<div>
	Anticipo: <input type="text" name="Anticipo"  placeholder="numero "id="Anticipo"/>
</div>
<div>
	NoFianzaAnticipo: <input  placeholder="numero "type="text" name="NoFianzaAnticipo" id="NoFianzaAnticipo"/>
</div>
<div>
	FechaFianzaAnticipo : <input type="text" name="FechaFianzaAnticipo" id="FechaFianzaAnticipo"/>
</div>
<div>
	MontoFianzaAnticipo: <input type="text" placeholder="numero " name="MontoFianzaAnticipo" id="MontoFianzaAnticipo"/>
</div>
<div>
	NoFianzaCumplimiento: <input type="text" placeholder="numero " name="NoFianzaCumplimiento" id="NoFianzaCumplimiento"/>
</div>
<div>
	FechaFianzaCumplimiento: <input type="text" name="FechaFianzaCumplimiento" id="FechaFianzaCumplimiento"/>
</div>
<div>
	MontoFianzaCumplimiento: <input  placeholder="numero "type="text" name="MontoFianzaCumplimiento" id="MontoFianzaCumplimiento"/>
</div>
<div>
	CargoRevision1: <input type="text" name="CargoRevision1" id="CargoRevision1"/>
</div>
<div>
	NombreRevision1: <input type="text" name="NombreRevision1" id="NombreRevision1"/>
</div>
<div>
	CargoRevision2: <input type="text" name="CargoRevision2" id="CargoRevision2"/>
</div>
<div>
	NombreRevision2: <input type="text" name="NombreRevision2" id="NombreRevision2"/>
</div>
<div>
	NombreQuienAutoriza: <input type="text" name="NombreQuienAutoriza" id="NombreQuienAutoriza"/>
</div>
<div>
	CargoVoBo: <input type="text" name="CargoVoBo" id="CargoVoBo"/>
</div>
<div>
	NombreVoBo: <input type="text" name="NombreVoBo" id="NombreVoBo"/>
</div>
<div>
	Borrador: <input type="text" name="Borrador" id="Borrador"/>
</div>
<div>
	<input type="button" onclick="guardar();" value="Add">
</div>



	
	<script src="/js/jquery-1.9.0.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
		<script src="https://apis.google.com/js/client.js?onload=initialize"></script>
	

</body>
</html>