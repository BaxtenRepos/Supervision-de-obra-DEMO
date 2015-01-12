//var datos;
//function init() {
//	var x = document.getElementById("catTipoPersona");
//	var peticion = 'peticion';
//	var object = {
//		'peticion' : peticion
//	};
//	$.ajax({
//		url : '/altapersonal',
//		type : 'post',
//		data : {
//			'objectJson' : JSON.stringify(peticion)
//		},
//		success : function(data) {
////			alert("todo bn");
//			datos = data;
//			if(data.length != 0){
//				for (var i = 0; i < data.length; i++) {
//					var option = document.createElement("option");
//					option.text = data[i].Tipo_Personal;
//					x.add(option);
//				}
//			}else{
//				var option = document.createElement("option");
//				option.text = "Debe dar de alta tipo persona";
//				x.add(option);
//			}
//		},
//		error : function(jHR, e, throwsError) {
//			alert(e);
//		}
//
//	});
//
//}

function init()
{
	var x = document.getElementById("AltaPersona_empresa");
	var peticion = 'peticion';
	var object = {
			'peticion' :  peticion
		};
		$.ajax({
			url : '/listaempresas',
			type : 'post',
			data : {'objectJson' : JSON.stringify(peticion)},
			success: function(data)
			{
				//alert("todo bn");
				//console.log('Persona Alta length= '+data.length); 
				for (var i = 0; i < data.length; i++) 
				{
					var option = document.createElement("option");
					option.value = data[i].idEmpresa;
					option.text = data[i].nombre;
					x.add(option);
				}
			},
			error: function(jHR,e,throwsError){
				alert(e);
			}	
	});
}

window.addEventListener('load', listenerForm, false);

function listenerForm() 
{
    document.getElementById("formulario1").addEventListener('submit', AltaPersonaenviar, false);
}

function AltaPersonaenviar(evt) 
{
	if(document.getElementById("AltaPersona_Nombre").value.trim()==""|
     	   document.getElementById("AltaPersona_ApPaterno").value.trim()==""|
     	   document.getElementById("AltaPersona_ApMaterno").value.trim()==""|
     	   document.getElementById("AltaPersona_Iniciales").value.trim()==""|
     	   document.getElementById("AltaPersona_FechaNaci").value.trim()==""|
     	   document.getElementById("AltaPersona_Direccion").value.trim()==""|
     	   document.getElementById("AltaPersona_Telefono").value.trim()==""|
     	   document.getElementById("AltaPersona_Radio").value.trim()==""|
     	   document.getElementById("form3Email").value.trim()==""|
 		   document.getElementById("AltaPersona_rfc").value.trim()==""|
    	   document.getElementById("AltaPersona_Cargo").value.trim()==""|
    	   document.getElementById("AltaPersona_TituloProfesional").value.trim()==""|
    	   document.getElementById("AltaPersona_CedulaProfesional").value.trim()==""|
    	   document.getElementById("AltaPersona_UsuarioSkype").value.trim()=="")
	{ 
		alert('Ingrese todos los valores antes de guardar');
		evt.preventDefault();
	}else {

		if (!parseInt(document.getElementById("AltaPersona_Telefono").value)) {
			alert("Telefono debe ser un número");
			document.getElementById('AltaPersona_Telefono').focus();
			evt.preventDefault();
		} 
		else 
		{
			var email = document.getElementById('form3Email').value;
			var formato = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
			if(!(formato.test(email)))
			{
				alert('Email no válido');
				document.getElementById('form3Email').focus();
				evt.preventDefault();
			}
			else
			{
				//alert('Valor foto='+document.getElementById("myFile").value+'= '+document.getElementById("blah").value);
				//|document.getElementById("blah").value==undefined
				if(document.getElementById("myFile").value=='')	
				{
					alert('Seleccionar una foto');
					evt.preventDefault();
				}else {
					var rfc = document.getElementById('AltaPersona_rfc').value;
					var formato = /^[A-Z]{3,4}(\d{6})((\D|\d){3})?$/;
					if(!(formato.test(rfc))){
						alert('RFC inválido');
						document.getElementById('AltaPersona_rfc').focus();
						evt.preventDefault();
					}
				}
				
			}
			
			
			
		/*
			
			var object = {
//				'id_Tipo_Persona' : datos[x.selectedIndex].id_Tipo_Personal,
				'Nombre' 		  : $('#AltaPersona_Nombre').val().trim(),
				'Ap_Paterno' 	  : $('#AltaPersona_ApPaterno').val().trim(),
				'Ap_Materno' 	  : $('#AltaPersona_ApMaterno').val().trim(),
				'Fecha_Nacimiento': $('#AltaPersona_FechaNaci').val().trim(),
				'Direccion' 	  : $('#AltaPersona_Direccion').val().trim(),
				'Telefonos' 	  : $('#AltaPersona_Telefono').val().trim(),
				'Radios' 		  : $('#AltaPersona_Radio').val().trim(),
				'Emails' 		  : $('#form3Email').val().trim(),
				'Rfc'			  : $('#AltaPersona_rfc').val().trim(),
				'Cargo' 	  	  : $('#AltaPersona_Cargo').val().trim(),
				'TituloProfesional': $('#AltaPersona_TituloProfesional').val().trim(),
				'CedulaProfesional': $('#AltaPersona_CedulaProfesional').val().trim(),
				'UsuarioSkype' 	  : $('#AltaPersona_UsuarioSkype').val().trim()
			};

			$.ajax({
				url : '/altapersonal',
				type : 'post',
				data : {
					'objectJson' : JSON.stringify(object)
				},
				success : function(data) {
					alert(data);
				},
				error : function(jHR, e, throwsError) {
					alert(e);
				}

			});*/
		}
	}	
}

function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#blah').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}

function validarRFC(){
	var rfc = document.getElementById('AltaPersona_rfc').value;
	var formato = /^[A-Z]{3,4}(\d{6})((\D|\d){3})?$/;
	var error = '<div class="alert alert-error">'+
    ' <button class="close" data-dismiss="alert"></button>';
	if(!(formato.test(rfc))){
		document.getElementById("errorRFC").innerHTML= error + 'Favor de introducir un RFC válido. </div>';
		document.getElementById('AltaPersona_rfc').focus();
	}else{
		document.getElementById("errorRFC").innerHTML= "";
	}
}

$(function () {
	$("#myFile").change(function(){
	    readURL(this);
	});
});

$(function () {
	$("#AltaPersona_FechaNaci").datepicker();
	});
