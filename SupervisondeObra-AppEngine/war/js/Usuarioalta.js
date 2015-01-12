function init() {
	var x = document.getElementById("SelectTipoUsuario");
	var peticion = 'peticion';
	$.ajax({
		url : '/altausuario',
		type : 'post',
		data : {
			'objectJson' : JSON.stringify(peticion)
		},
		success : function(data) {
			// alert("todo bn");
			for (var i = 0; i < data.length; i++) {
				var option = document.createElement("option");
				option.text = data[i].tipo;
				option.value = data[i].id;
				x.add(option);
			}
		},
		error : function(jHR, e, throwsError) {
			alert(e);
		}
	});
	var y = document.getElementById("personaselect");
	var opciopersona = 'opciopersona';
	$.ajax({
		url : '/altausuario',
		type : 'post',
		data : {
			'objectJson' : JSON.stringify(opciopersona)
		},
		success : function(data) {
			var option = document.createElement("option");
			option.text = "Seleccione una persona";
			option.value = 0;
			y.add(option);
			for (var i = 0; i < data.length; i++) {
				var option = document.createElement("option");
				option.text = data[i].nombre;
				option.value = data[i].id;
				y.add(option);
			}
		},
		error : function(jHR, e, throwsError) {
			alert(e);
		}
	});
}

function enviar() {
	var x = document.getElementById("SelectTipoUsuario");
	var y = document.getElementById("personaselect");
	
	if(y.value == 0 |
		document.getElementById("AltaUsuario_Usuario").value.trim()==""|
		document.getElementById("AltaUsuario_Contrasenia").value.trim()==""){ 
		alert('Ingrese todos los valores antes de guardar');
	}else {
		var object = {
				'Id_Persona'      : y.value,
				'id_Tipo_Persona' : x.value,
				'Usuario' 		  : $('#AltaUsuario_Usuario').val().trim(),
				'contrasena' 	  : $('#AltaUsuario_Contrasenia').val().trim()
			};

			$.ajax({
				url : '/altausuario',
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
					location.href="AltaUsuario.jsp";
				}

			});
	}	
}

$(function () {
$("#AltaUsuario_FechaNaci").datepicker();
	});

function hacerClick(){
   checkbox = document.getElementById("check1");
    passField = document.getElementById("AltaUsuario_Contrasenia");
    if(checkbox.checked===true){
        passField.type = "text";
    }else{
        passField.type = "password";
    }
}

