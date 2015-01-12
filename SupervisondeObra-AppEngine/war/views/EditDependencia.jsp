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
	API_URL = 'https://' + 'focal-furnace-615.appspot.com' + '/_ah/api'
	
	
	if (window.location.hostname == 'localhost'
			|| window.location.hostname == '127.0.0.1'
			|| ((window.location.port != "") && (window.location.port > 999))) {
		// We're probably running against the DevAppServer
		API_URL = 'http://' + 'focal-furnace-615.appspot.com' + '/_ah/api';
	}
	
	window.alert(API_URL);
	
	
	var arrDep;
	var cantidad_elementos;
	var indice=0;
	
	
	//Funcion que se encargara de tomar los datos insertados por el usuario, para despues insertarlos en el data store
	function guardar(){
	
		
		
		
		
	if(cantidad_elementos==0)id_Empresa=1;
	else 
	id_Empresa=cantidad_elementos+1;	
	var y = document.getElementById("tipo");
		
		var valor = y.options[y.selectedIndex].value
		if(valor=='Contratista')valor=2;
		else if(valor=='Supervisora')valor=1;
		else if(valor=='Dependencia')valor=3;
		

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
	 
		
		
		var aux = gapi.client.load('empresaendpoint', 'v1', function(){

// 			
				
				var reques = gapi.client.empresaendpoint.updateEmpresa({
"calle": calle,
"codi_Postal": parseInt(codi_Postal),
"colonia": colonia,
"del_o_Mun": del_o_Mun,
"entidad": entidad,
"id_Empresa": arrDep[indice].id_Empresa+"",
"id_tipo_empresa": parseInt(id_tipo_empresa),
"nombre": nombre,
"num_Ext": num_Ext,
"num_Int": num_Int,
"rfc": rfc,
"siglas": siglas
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
													arrDep = resp.items;
													if(!resp.items){
													cantidad_elementos=0;
													}
													else
													cantidad_elementos=resp.items.length;
												
													
													llenaDatos();
																		//				var option = document.createElement("option");
		//	option.value = arrU[xcont].name + "_" + arrU[xcont].appP + "_" + arrU[xcont].appM + "_" + arrU[xcont].id_device;
																								
												var x = document.getElementById("userdp");
		var xcont = 0;
		
		while(xcont<arrDep.length){
			var option = document.createElement("option");
			option.text = arrDep[xcont].nombre;
			x.add(option);
			xcont++;
		}
							
													
												});
											}, API_URL);
		
		
	}
	
			function info(){
	
	var x = document.getElementById("userdp");
		
		var strUser = x.options[x.selectedIndex].value;
		indice=x.selectedIndex;
		llenaDatos();
	
	}
	function llenaDatos(){




													document.getElementById("calle").value = arrDep[indice].calle;	
													document.getElementById("codi_Postal").value = arrDep[indice].codi_Postal;	
													document.getElementById("colonia").value = arrDep[indice].colonia;	
													document.getElementById("del_o_Mun").value = arrDep[indice].del_o_Mun;	
													document.getElementById("entidad").value = arrDep[indice].entidad;
													document.getElementById("siglas").value = arrDep[indice].siglas;
													
													if(arrDep[indice].id_tipo_empresa==1)document.getElementById("tipo").value = "Supervisora";
													else if(arrDep[indice].id_tipo_empresa==2)document.getElementById("tipo").value = "Contratista";
													else if(arrDep[indice].id_tipo_empresa==3)document.getElementById("tipo").value = "Dependencia";
																										
													
													document.getElementById("nombre").value = arrDep[indice].nombre;	
													document.getElementById("num_Ext").value = arrDep[indice].num_Ext;	
													document.getElementById("num_Int").value = arrDep[indice].num_Int;	
													document.getElementById("rfc").value = arrDep[indice].rfc;	
													
	}

</script>




<label>Obra: <><</label>

  <select id="userdp">
  
  </select>
 
<input type="submit" value="Mostrar"
        OnClick="info();">


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