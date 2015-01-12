var idUbicacion;
var idProyecto;
var Directivos_Proyecto;
var idDirectivos=new Array();

function init() {
	var loc = document.location.href;
    var getString = loc.split('?')[1];
    var GET = getString.split('&');
 
    for(var i = 0, l = GET.length; i < l; i++){
       var tmp = GET[i].split('=');
       idProyecto=tmp[1];
    }
    var peticion = 'opcionproyecto:'+ idProyecto;
	limpiar();
	
	if(idProyecto != 0){
		$.ajax({
			url : '/editproyecto',
			type : 'post',
			data : {'objectJson' : JSON.stringify(peticion)},
			success: function(data){
				Directivos_Proyecto = data.Directivo_Proyecto;
				mostrarSelect(data.id_secretaria, data.id_dependencia, Directivos_Proyecto);
				idUbicacion = data.id_ubicacion;
				idDirectivo_Proyecto = data.idDirectivo_Proyecto;
				document.getElementById("editProyecto_nombre_largo").value = data.Nombre_largo;
			    document.getElementById("editProyecto_nombre_corto").value = data.Nombre_corto;
		  	    document.getElementById("editProyecto_Descripcion").value = data.Descripcion;
		  	    
		  	  $.ajax({
					url : '/editproyecto',
					type : 'post',
					data : {'objectJson' : JSON.stringify("opcionubicacion:"+idUbicacion)},
					success: function(data){
						iniciamapa(data);
					},
					error: function(jHR,e,throwsError){
						alert(e);
					}			
				});	
		  	},
			error: function(jHR,e,throwsError){
				alert(e);
			}			
		});	
	}
    
}

function editProyecto_enviar() {
	var a = document.getElementById("editProyecto_selectSecretaria");
	var b = document.getElementById("editProyecto_selectDependencia");
	var c = document.getElementById("editProyecto_selectDirectivo");
	
	if(document.getElementById("editProyecto_nombre_largo").value.trim()==""|
    	document.getElementById("editProyecto_nombre_corto").value.trim()==""|
	    document.getElementById("editProyecto_Descripcion").value.trim()==""){		  	    
	  	alert('Ingrese todos los valores antes de guardar los cambio');
    }else if(puntos.length==0){
    		alert("inserte una ubicación");
    }else if(idDirectivos.length==0){
    		alert("Agregue al menos un directivo al proyecto");
    }else{
		var object = {
				'id_Proyecto'	  		: idProyecto,
				'id_secretaria'			: a.value,
				'id_dependencia'		: b.value,
				'directivo'	  		    : idDirectivos,
				'Nombre_largo'  		: $('#editProyecto_nombre_largo').val().trim(),
				'Nombre_corto' 		  	: $('#editProyecto_nombre_corto').val().trim(),
				'Descripcion'  			: $('#editProyecto_Descripcion').val().trim(),
				'id_ubicacion'			: idUbicacion,
				'datosArrayUbicacion'   : puntos,
				'Directivo_Proyecto'    : Directivos_Proyecto				
			};

			$.ajax({
				url : '/editproyecto',
				type : 'post',
				data : {
					'objectJson' : JSON.stringify(object)
				},
				success : function(data) {
					 if(!isNaN(data)){
						 $.confirm({
								'title'		: 'Confirmaci&oacute;n',
								'message'	: 'Proyecto editado correctamente. <br />¿Desea asignar el directorio al proyecto ahora?',
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
											location.href="Directorioedit.html?idProyecto="+data+"="+0;
										}
									}
								}
							});
						 
//						 if (confirm("Proyecto editado correctamente, Desea asignar el directorio al proyecto ahora?") == true) {
//						    	location.href="Directorioedit.html?idProyecto="+data+"="+0;
//
//						    } else {
//						    	location.href="PruebaProyecto.html";
//						    }
					 }else{
						 alert(data); 
					 }
				},
				error : function(jHR, e, throwsError) {
					alert(e);
					window.location.reload();
				}
			});
	}	
}

function mostrarSelect(ida, idb, idc){
	var a = document.getElementById("editProyecto_selectSecretaria");
	var b = document.getElementById("editProyecto_selectDependencia");
	var c = document.getElementById("editProyecto_selectDirectivo");
	
	var secretariastring = new Array();
	var dependenciastring = new Array();
	
	var opcionselect = 'opcionselect';
	var opciondirectivos = 'opciondirectivos';
	$.ajax({
		url : '/editproyecto',
		type : 'post',
		data : {'objectJson' : JSON.stringify(opcionselect)},
		success: function(data){
	//		OrdenaEmpresas(data);
//			for(var r=0 ; r<data.length; r++){
//				if(data[r].tipoEmpresa == "Secretaria")
//					secretariastring.push(data[r].nombre);
//				else if(data[r].tipoEmpresa == "Dependencia")
//					dependenciastring.push(data[r].nombre);
//			}
//			dependenciastring.sort();
//			secretariastring.sort();
			for (var t = 0; t < data.length; t++) {
				if(data[t].tipoEmpresa == "Secretaria"){
					var option = document.createElement("option");
					option.text = data[t].nombre;
					option.value = data[t].id;
					a.add(option);
					if(data[t].id == ida){
						$("#editProyecto_selectSecretaria option[value="+ida+"]").attr("selected",true);
					}
				}else if(data[t].tipoEmpresa == "Dependencia"){
					var option = document.createElement("option");
					option.text = data[t].nombre;
					option.value = data[t].id;
					b.add(option);
					if(data[t].id == idb){
						$("#editProyecto_selectDependencia option[value="+idb+"]").attr("selected",true);
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
		url : '/editproyecto',
		type : 'post',
		data : {'objectJson' : JSON.stringify(opciondirectivos)},
		success: function(data){
			for(var q=0;q<data.length;q++){
				var option = document.createElement("option");
				option.value = data[q].id;
				option.text = data[q].nombre;
				c.add(option);
				for (var int = 0; int < idc.length; int++) {
					if(data[q].id == idc[int].id_directivo){
						c.options[q].selected = true;
						idDirectivos.push(data[q].id);
					}
				}
			}
			$(document).ready(function(){
			    $("#editProyecto_selectDirectivo").multiSelect({
			    	  afterSelect: function(values){
			    		  idDirectivos.push(values[0]);
//			    		    alert("Select value: "+idDirectivos.length);
			    		  },
			    		  afterDeselect: function(values){
			    			  for(var i = 0; i < idDirectivos.length; i++) {
			    				    if(idDirectivos[i] == values[0]) {
			    				    	idDirectivos.splice(i, 1);
			    				    }
			    				}
//			    		    alert("Deselect value: "+idDirectivos.length);
			    		  }
			    		});
			});
		},
		error: function(jHR,e,throwsError){
			alert(e);
		}			
	});
}

function limpiar(){
	$('#editProyecto_selectSecretaria option').remove();
	$('#editProyecto_selectDependencia option').remove();
	$('#editProyecto_selectDirectivo option').remove();
	
	document.getElementById("editProyecto_nombre_largo").value = "";
    document.getElementById("editProyecto_nombre_corto").value = "";
	document.getElementById("editProyecto_Descripcion").value = "";
}
