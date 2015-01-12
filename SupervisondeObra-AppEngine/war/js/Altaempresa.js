function init(){
		var x = document.getElementById("empresa");
		var peticion = 'peticion';
		var object = {
				'peticion' :  peticion
			};
			$.ajax({
				url : '/altaempresa',
				type : 'post',
				data : {'objectJson' : JSON.stringify(peticion)},
				success: function(data){
					//alert("todo bn");
					var elementos = [];
					
					for (var i = 0; i < data.length; i++) {
						var option = document.createElement("option");
						option.text = data[i];
						x.add(option);
						
					}

				},
				error: function(jHR,e,throwsError){
					alert(e);
				}
					
		});
		
	}

	
	
function enviar(){
	var x = document.getElementById("empresa");
	var tipoEmpresa = x.options[x.selectedIndex].value;
	//alert(tipoEmpresa);
	
	if( !parseInt(document.getElementById("num_ext").value)){
		alert("num_ext debe ser un número");
	}else if(!parseInt(document.getElementById("num_int").value)){
		alert("num_int debe ser un número");
	}else if(!parseInt(document.getElementById("codigo_postal").value)){
		alert("codigo postal debe ser un número");
	}else{

	var object = {
			'tipoEmpresa' : tipoEmpresa,
			'rfc' :      $('#rfc').val().trim(),
			'nombre' :   $('#nombre').val().trim(),
			'siglas' :   $('#siglas').val().trim(),
			'calle' :    $('#calle').val().trim(),
			'numExt' :   $('#num_ext').val().trim(),
			'numInt' :   $('#num_int').val().trim(),
			'colonia' :  $('#colonia').val().trim(),
			'delMun' :   $('#del_mun').val().trim(),
			'entidad' :  $('#entidad').val().trim(),
			'codPostal' : $('#codigo_postal').val().trim(),
	};		
	
		$.ajax({
			url : '/altaempresa',
			type : 'post',
			data : {'objectJson' : JSON.stringify(object)},
			success: function(data){
				alert(data);
			},
			error: function(jHR,e,throwsError){
				alert(e);
			}
				
	});
	}
	
}
/*
 * 'datosArrayUbicacion' : puntos,
					'idDescripcion' : $('#id_Descripcion').val().trim(),
 * 
 */
//$(function(){
//
//	/*
// <div> Tipo de Empresa:<select id="empresa"></select> </div> 
//    <div>RFC: <input type="text"            id="rfc"/></div>
//    <div>Nombre: <input type="text"         id="nombre"/></div>
//    <div>Siglas: <input type="text"         id="siglas"/></div>
//    <div>Calle: <input type="text"          id="calle"/></div>
//    <div>Num_Ext: <input type="text"        id="num_ext"/></div>
//    <div>Num_Int: <input type="text"        id="num_int"/></div>
//    <div>Colonia: <input type="text"        id="colinia"/></div>
//    <div>Del_o_Mun: <input type="text"      id="del_mun"/></div>
//    <div>Entidad: <input type="text"        id="entidad"/></div>
//    <div>Codi_Postal: <input type="text"    id="codigo_postal"/></div>
//    
//    	var x = document.getElementById("userdp");
//		limpiar();
//		var strUser = x.options[x.selectedIndex].value;
//	*/
//	
//	
//	
//	
//	$('#guardar').click(function(e){
//		alert("presionaron el boton");
//	  	var x = document.getElementById("empresa");
//		//Es solo un ejemplo de peticion
//		var object = {
//						'tipoEmpresa' : $('#empresa').value().trim(),
//						
//					};
//		console.log(object);
//		$.ajax({
//			url : '/altaproyecto',
//			type : 'post',
//			data : {'objectJson' : JSON.stringify(object)},
//			success: function(data){ //Si el request se ejecuta correctamente y el response también, se ejecutara automaticamente el success ok, en este caso puse una funcion anonima
//				console.log(data);  //puedes poner una funcion definida con n nombre u otra cosa va, y si regresas datos, estos datos regresaran sobre la variable data
//				$('#respuesta').val("resultado de la evaluacion: "+data);
//			},						//normalmente se regresan json y javascri´t los parsea automaticamente, es decir ahora hacemos lo contrario de una clase de java pasamos a json y regresamos
//			
//			error : function(jHR, e, throwsError){ //si el request o el response tienen problemas, se ejecuta el metodo error, puse una funcion anonima, la variable e, se carga con el error, que puedes
//				/*console.log('Status de la peticion: ' + jHR.status); 
//				console.log('texto del status: ' + jHR.statusText); 
//				console.log('texto de la respuesta: ' + jHR.responseText);*/
//				if(jHR.status=="400") alert('no a insertado los puntos'); 
//								//especificar desde el servlet, estamos???? si ok, hay dos cosas, dependiendo de lo que necesites:
//			}					// 1.- Un servlet puede regresar hacia una vista (jsp)
//								// 2.- Un servlet puede regresar un flujo de datos (json, img, archivos, xml, ect)
//								// 3.- Un servlet puede regresar hacia otro servlet o hacer un response redirect (reenvia hacia otro dominio, sale de su capa web)	
//		});
//	});
//	
//	
//	
//}):
	