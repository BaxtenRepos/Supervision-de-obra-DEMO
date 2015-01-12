<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Punto by Endpoint</title>
</head>
<body>

<script>
	//API_URL = 'https://' + '1-dot-iuyet-cao-csgit.appspot.com' + '/_ah/api'
	//http://1-dot-iuyet-cao-csgit.appspot.com/
	API_URL = 'https://' + '1-dot-midyear-lattice-729.appspot.com' + '/_ah/api'
	
	if (window.location.hostname == 'localhost'
			|| window.location.hostname == '127.0.0.1'
			|| ((window.location.port != "") && (window.location.port > 999))) {
		// We're probably running against the DevAppServer
		API_URL = 'http://' + '1-dot-iuyet-cao-csgit.appspot.com' + '/_ah/api';
	}
	
	window.alert(API_URL);
	
	
	
	var cantidad_elementos;
	
	
	//Funcion que se encargara de tomar los datos insertados por el usuario, para despues insertarlos en el data store
	function guardar(){
	
		
		var y = document.getElementById("tipo");
		
		var valor = y.options[y.selectedIndex].value
		
		
	if(cantidad_elementos==0)id_Empresa=1;
	else 
	id_Empresa=cantidad_elementos+1;	

		

	 var  calle= $('#calle').val();
	 var  codi_Postal= $('#codi_Postal').val();
	 var  colonia= $('#colonia').val();
	 var  del_o_Mun= $('#del_o_Mun').val();
	 var  entidad= $('#entidad').val();
	 
	 var  id_tipo_empresa= valor;
	 var  nombre= $('#nombre').val();
	 var  num_Ext= $('#num_Ext').val();
	 var  num_Int= $('#num_Int').val();
	 var  rfc= $('#rfc').val();
	 var  siglas= $('#siglas').val();
	 
		
		
		var aux = gapi.client.load('communicationchannel', 'v1', function(){

// 			
				
				var reques = gapi.client.communicationchannel.getEmpresa({
"id": '1',

				}).execute(function(resp){

				
					window.alert(resp);
				});
				
			//	window.alert("Guardado");
				
			//}
			
		}, API_URL);

										

          
		}
	
	function init(){


var loadAreaPoligonal = gapi.client.load('empresaendpoint','v1',function(){
												gapi.client.empresaendpoint.listEmpresa().execute(function(resp){
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

</script>







<div>
	calle: <input type="text" name="calle" id="calle"/>
</div>
<div>
	codi_Postal: <input type="text"  placeholder="numero "  name="codi_Postal" id="codi_Postal"/>
</div>

<div>
	colonia: <input type="text" name="colonia" id="colonia"/>
</div>

<div>
	del_o_Mun: <input type="text"  name="del_o_Mun" id="del_o_Mun"/>
</div>

<div>
	entidad: <input type="text" name="entidad" id="entidad"/>
</div>



<div>
<select id="tipo">
   <option value="Supervisora">Supervisora</option>
  <option value="Contratista">Contratista</option>
  <option value="Dependencia">Dependencia</option>

  </select>

</div>

<div>
	nombre: <input type="text"  name="nombre" id="nombre"/>
</div>

<div>
	num_Ext: <input type="text"  name="num_Ext" id="num_Ext"/>
</div>
<div>
	num_Int: <input type="text"  name="num_Int" id="num_Int"/>
</div>
<div>
	rfc: <input type="text"  name="rfc" id="rfc"/>
</div>

<div>
	siglas: <input type="text"  name="siglas" id="siglas"/>
</div>






<div>
	<input type="button" onclick="guardar();" value="Add">
</div>



	<script src="https://apis.google.com/js/client.js?onload=init"></script>
	<script src="/js/jquery-1.9.0.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>

</body>
</html>