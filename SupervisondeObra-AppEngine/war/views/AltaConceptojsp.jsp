<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">



<link rel="stylesheet" type="text/css" href="./jquery.datetimepicker.css"/>
<style type="text/css">

.custom-date-style {
	background-color: red !important;
}

</style>





<title>Add Punto by Endpoint</title>
</head>
<body>

	<h3>DateTimePicker</h3>

	
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
	var ArrConceptos;
	var obras;
	var idObra;
	
	
	//Funcion que se encargara de tomar los datos insertados por el usuario, para despues insertarlos en el data store
	function guardar(){
	
		
		
		
		
	if(cantidad_elementos==0)id_Concepto=1;
	else 
	id_Concepto=cantidad_elementos+1;
	
	
var fechaInicio= $('#datetimepicker').val()+"";
var fechaFin =$('#datetimepicker2').val();

var res = fechaInicio.split('/',3);
var res2 = res[2].split(" ");
res2[1]+=":00";
fechaInicio=res2[0]+":"+res[1]+":"+res[0]+"-"+res2[1];

	
	var res = fechaFin.split('/',3);
	var res2 = res[2].split(" ");
	res2[1]+=":00";

	fechaFin=res2[0]+":"+res[1]+":"+res[0]+"-"+res2[1];
	
	
		

	 var  cantidadAvance= $('#CantidadAvance').val();
	  var  cantidadTotal= $('#CantidadTotal').val();
	   var  clave= $('#Clave').val();
	    var  descripcion= $('#DescripciÃ³n').val();
	     var  fecha_Fin= fechaFin;
	      var  fecha_Inicio= fechaInicio;
	 
	        var  id_Obra= $('#IdObra').val();
	         var  importe= $('#Importe').val();
	          var  precioUnitario= $('#PrecioUnitario').val();
	           var  unidadMedida= $('#UnidadMedida').val();
	  

		
		
		var aux = gapi.client.load('usuarioendpoint', 'v1', function(){

// 			
				
				var reques = gapi.client.usuarioendpoint.getUsuario({
				

				
"id": "0",	
"user":"Edgaryuyuh",
"contrasena":"edgar",		



				}).execute(function(resp){

				
					window.alert(resp.id_Tipo_Persona);
				});
				
			//	window.alert("Guardado");
				
			//}
			
		}, API_URL);

										

          
		}
		
	function obtener(){
	var x = document.getElementById("obraselect");
	var strUser = x.options[x.selectedIndex].value;
	for(var t=0;t<obras.length;t++)
	{
		if(obras[t].nombre==strUser)
		{
		idObra = obras[t].id_Obra;
		}
	}
		var z = document.getElementById("padreselect");
	for(var y=0; y<ArrConceptos.length; y++){
	
		if(ArrConceptos.id_obra == idObra){
	
												
													
				var option = document.createElement("option");
				option.text = ArrConceptos[y].clave;
				z.add(option);
			
		}
	
	}
	
	
		
	}

	function init(){
	

var loadAreaPoligonal = gapi.client.load('conceptoendpoint','v1',function(){
												gapi.client.conceptoendpoint.listConcepto().execute(function(resp){
												//	window.alert("obraendpoint Loaded");
													//manageAreasPoligonales(resp);
													ArrConceptos = resp.items;
													if(!resp.items){
													cantidad_elementos=0;
													}
													else
													cantidad_elementos=resp.items.length;
												
													
												
													
												});
											}, API_URL);
											obras = gapi.client.load('obraendpoint','v1',function(){
												gapi.client.obraendpoint.listObra().execute(function(resp){
												//	window.alert("obraendpoint Loaded");
													//manageAreasPoligonales(resp);
													obras = resp.items;	
													var x = document.getElementById("obraselect");
													for(var y=0; y<resp.items.length;y++){
													
													var option = document.createElement("option");
													option.text = obras[y].nombre;
													x.add(option);
													}
												});
											}, API_URL);
		
		
	}

</script>



<div>
	Obra: <select id="obraselect" onchange="obtener1();">
	
	
	</select> 
</div>
<!--
<div>
	Padre: <select id="obraselect" onchange="obtener();">
	 <option value="volvo">Seleccione una opcion</option>
	</select> 
</div>
-->
<div>
	//id_Obra: <input type="text" placeholder="numero" name="IdObra" id="IdObra"/>
</div>
<div>
	Clave: <input type="text" placeholder="numero " name="Clave" id="Clave"/>
</div>
<div>
	DescripciÃ³n: <input type="text" name="DescripciÃ³n" id="DescripciÃ³n"/>
</div>
<div>
	UnidadMedida: <input type="text"  name="UnidadMedida" id="UnidadMedida"/>
</div>
<div>
	CantidadTotal: <input type="text" placeholder="numero " name="CantidadTotal" id="CantidadTotal"/>
</div>
<div>
	PrecioUnitario: <input type="text" placeholder="numero " name="PrecioUnitario" id="PrecioUnitario"/>
</div>
<div>
	Importe: <input type="text" placeholder="numero " name="Importe" id="Importe"/>
</div>
<div>
	CantidadAvance: <input type="text" placeholder="numero " name="CantidadAvance" id="CantidadAvance"/>
</div>
<div>
	FechaInicio: <input type="text"  name="FechaInicio" id="datetimepicker"/>
</div>
<div>
	FechaFin: <input type="text"  name="FechaFin" id="datetimepicker2"/>
</div>
<div>
	<input type="button" onclick="guardar();" value="Add">
</div>






	<script src="https://apis.google.com/js/client.js?onload=init"></script>
	<script src="/js/jquery-1.9.0.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>

</body>
<script src="./jquery.js"></script>
<script src="./jquery.datetimepicker.js"></script>
<script>
$('#datetimepicker').datetimepicker({
dayOfWeekStart : 1,
lang:'es',
startDate:	'1986/01/05'
});
$('#datetimepicker').datetimepicker({value:'2015/04/15 05:03',step:10});


$('#datetimepicker2').datetimepicker({
dayOfWeekStart : 1,
lang:'es',
startDate:	'1986/01/05'
});
$('#datetimepicker2').datetimepicker({value:'2015/04/15 05:03',step:10});


</script>
</html>