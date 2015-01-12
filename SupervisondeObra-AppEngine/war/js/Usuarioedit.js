var idUsuario;
function init() 
{
	var loc = document.location.href;
    var getString = loc.split('?')[1];
    var GET = getString.split('&');
 
    for(var i = 0, l = GET.length; i < l; i++){
       var tmp = GET[i].split('=');
       idUsuario=tmp[1];
    }
    limpiar();
	var peticion = 'opcionusuario:'+ idUsuario;
	if(idUsuario != 0){
		$.ajax({
			url : '/editusuario',
			type : 'post',
			data : {'objectJson' : JSON.stringify(peticion)},
			success: function(data){
				mostrarSelect(data.id_Tipo_Persona,data.Id_Persona);
			    document.getElementById("EditUsuario_Usuario").value = data.Usuario;
			    document.getElementById("EditUsuario_Contrasenia").value = data.contrasena;
			},
			error: function(jHR,e,throwsError){
				alert(e);
			}			
		});	
	}
}

function enviar() {
	var y = document.getElementById("EditSelectTipoUsuario");
	var z = document.getElementById("personaselect");
	
	if(document.getElementById("EditUsuario_Usuario").value.trim()==""|
		   document.getElementById("EditUsuario_Contrasenia").value.trim()==""){ 
		alert('Ingrese todos los valores antes de guardar');
	}else {
		var object = {
				'Id_Propietario'  : idUsuario,
				'id_Tipo_Persona' : y.value,
				'Id_Persona'	  : z.value,
				'Usuario' 		  : $('#EditUsuario_Usuario').val().trim(),
				'contrasena' 	  : $('#EditUsuario_Contrasenia').val().trim()
			};
		$.ajax({
			url : '/editusuario',
			type : 'post',
			data : {
				'objectJson' : JSON.stringify(object)
			},
			success : function(data) {
				alert(data);
				location.href="ListaUsuarios.html";
			},
			error : function(jHR, e, throwsError) {
				alert(e);
				location.href="EditUsuario.html";
			}
		});
	}	
}

function limpiar(){
	   $('#EditSelectTipoUsuario option').remove();
	   $('#personaselect option').remove();
	   document.getElementById("EditUsuario_Usuario").value="";
	   document.getElementById("EditUsuario_Contrasenia").value="";
}

function mostrarSelect(ida, idb){
	var a = document.getElementById("EditSelectTipoUsuario");
	var b = document.getElementById("personaselect");
	
	var opciontipoUsuario = 'opciontipoUsuario';
	var opcionpersona = 'opcionpersona:'+idUsuario;
	$.ajax({
		url : '/editusuario',
		type : 'post',
		data : {'objectJson' : JSON.stringify(opciontipoUsuario)},
		success: function(data){
			for (var i = 0; i < data.length; i++) {
				var option = document.createElement("option");
				option.text = data[i].nombre;
				option.value = data[i].id;
				a.add(option);
				if(data[i].id == ida){
					a.options[i].selected = true;
				}
			}
		},
		error: function(jHR,e,throwsError){
			alert(e);
		}			
	});
	$.ajax({
		url : '/editusuario',
		type : 'post',
		data : {'objectJson' : JSON.stringify(opcionpersona)},
		success: function(data){
			for (var i = 0; i < data.length; i++) {
				var option = document.createElement("option");
				option.text = data[i].nombre;
				option.value = data[i].id;
				b.add(option);
				if(data[i].id == idb){
					b.options[i].selected = true;
				}
			}
		},
		error: function(jHR,e,throwsError){
			alert(e);
		}			
	});
}


$(function () {
	$("#EditUsuario_FechaNaci").datepicker();
	});
