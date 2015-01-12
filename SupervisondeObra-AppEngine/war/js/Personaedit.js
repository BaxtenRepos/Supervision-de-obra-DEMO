var idPersona;
//var idMultimedia;
function init() 
{	
	var loc = document.location.href;
    var getString = loc.split('?')[1];
    var GET = getString.split('&');
 
    for(var i = 0, l = GET.length; i < l; i++){
       var tmp = GET[i].split('=');
       idPersona=tmp[1];
    }
    limpiar();
	var opcionpersona = 'opcionpersona:'+ idPersona;
	if(idPersona != 0){
		$.ajax({
			url : '/editpersonal',
			type : 'post',
			data : {'objectJson' : JSON.stringify(opcionpersona)},
			success: function(data){
				//console.log('recibe idEmpresa= '+data.idEmpresa);
				mostrarEmpresa(data.idEmpresa);
				document.getElementById("EditPersona_Nombre").value = data.Nombre;
		  	    document.getElementById("EditPersona_ApPaterno").value = data.Ap_Paterno;
		  	    document.getElementById("EditPersona_ApMaterno").value = data.Ap_Materno;
		  	    document.getElementById("EditPersona_Iniciales").value = data.Iniciales;
		  	    document.getElementById("EditPersona_FechaNaci").value = data.Fecha_Nacimiento;
		  	    document.getElementById("EditPersona_Direccion").value = data.Direccion;
		  	    document.getElementById("EditPersona_Telefono").value = data.Telefonos;
		  	    document.getElementById("EditPersona_Radio").value = data.Radios;
		  	    document.getElementById("form3Email").value = data.Emails;
		  	    document.getElementById("EditPersona_rfc").value = data.Rfc;
		  	    document.getElementById("EditPersona_Cargo").value = data.Cargo;
		  	    document.getElementById("EditPersona_TituloProfesional").value = data.TituloProfesional;
		  	    document.getElementById("EditPersona_CedulaProfesional").value = data.CedulaProfesional;
		  	    document.getElementById("EditPersona_UsuarioSkype").value = data.UsuarioSkype;
		  	    document.getElementById("blah").src = data.Fotografia;
		  	    document.getElementById("EditPersona_Id").value = idPersona;
		  	    document.getElementById("isNew").value = 0;
		  	    //console.log('Valor de Foto= '+data.Fotografia);
			    //idMultimedia = data.idMultimedia;
			},
			error: function(jHR,e,throwsError){
				alert(e);
			}			
		});	
	}
    
	
}

window.addEventListener('load', listenerForm, false);

function listenerForm() 
{
    document.getElementById("formularioE").addEventListener('submit', EditPersonaenviar, false);  
}


function EditPersonaenviar(evt) {
	if(document.getElementById("EditPersona_Nombre").value.trim()==""|
     	   document.getElementById("EditPersona_ApPaterno").value.trim()==""|
     	   document.getElementById("EditPersona_ApMaterno").value.trim()==""|
     	   document.getElementById("EditPersona_Iniciales").value.trim()==""|
     	   document.getElementById("EditPersona_FechaNaci").value.trim()==""|
     	   document.getElementById("EditPersona_Direccion").value.trim()==""|
     	   document.getElementById("EditPersona_Telefono").value.trim()==""|
     	   document.getElementById("EditPersona_Radio").value.trim()==""|
     	   document.getElementById("form3Email").value.trim()==""|
           document.getElementById("EditPersona_rfc").value.trim()==""|
           document.getElementById("EditPersona_Cargo").value.trim()==""|
    	   document.getElementById("EditPersona_TituloProfesional").value.trim()==""|
    	   document.getElementById("EditPersona_CedulaProfesional").value.trim()==""|
    	   document.getElementById("EditPersona_UsuarioSkype").value.trim()==""){ 
		alert('Ingrese todos los valores antes de guardar');
		evt.preventDefault();
	}else if (!parseInt(document.getElementById("EditPersona_Telefono").value)) 
	{
		alert("Telefono debe ser un número");
		document.getElementById('EditPersona_Telefono').focus();
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
		else {
			var rfc = document.getElementById('EditPersona_rfc').value;
			var formato = /^[A-Z]{3,4}(\d{6})((\D|\d){3})?$/;
			if(!(formato.test(rfc))){
				alert('RFC inválido');
				document.getElementById('EditPersona_rfc').focus();
				evt.preventDefault();
			}
		}
	}
		
}

function limpiar(){
	   document.getElementById("EditPersona_Nombre").value = "";
	   document.getElementById("EditPersona_ApPaterno").value = "";
	   document.getElementById("EditPersona_ApMaterno").value = "";
	   document.getElementById("EditPersona_Iniciales").value = "";
	   document.getElementById("EditPersona_FechaNaci").value = "";
	   document.getElementById("EditPersona_Direccion").value = "";
	   document.getElementById("EditPersona_Telefono").value = "";
	   document.getElementById("EditPersona_Radio").value = "";
	   document.getElementById("form3Email").value = "";
	   document.getElementById("EditPersona_rfc").value = "";
 	   document.getElementById("EditPersona_Cargo").value = "";
 	   document.getElementById("EditPersona_TituloProfesional").value = "";
 	   document.getElementById("EditPersona_CedulaProfesional").value = "";
 	   document.getElementById("EditPersona_UsuarioSkype").value = "";
}


function mostrarEmpresa(idEmpresa)
{	
	var x = document.getElementById("EditPersona_empresa");
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
				
				//console.log('Persona Edit Busca idEmpresa= '+idEmpresa); 
				var elementos = [];
				for (var i = 0; i < data.length; i++) 
				{
					var option = document.createElement("option");	
					option.value = data[i].idEmpresa;
					option.text = data[i].nombre;
					x.add(option);					
					if(data[i].idEmpresa == idEmpresa)
					{
						//console.log('Seleccionada '+data[i].idEmpresa);
						x.options[i].selected = true;
					}
				}

			},
			error: function(jHR,e,throwsError){
				alert(e);
			}		
	});	
}

function validarRFC(){
	var rfc = document.getElementById('EditPersona_rfc').value;
	var formato = /^[A-Z]{3,4}(\d{6})((\D|\d){3})?$/;
	var error = '<div class="alert alert-error">'+
    ' <button class="close" data-dismiss="alert"></button>';
	if(!(formato.test(rfc))){
		document.getElementById("errorRFC").innerHTML= error + 'Favor de introducir un RFC válido. </div>';
		document.getElementById('EditPersona_rfc').focus();
	}else{
		document.getElementById("errorRFC").innerHTML= "";
	}
}


$(function () {
	$("#EditPersona_FechaNaci").datepicker();
	});


function readURL(input) 
{
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#blah').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
        document.getElementById("isNew").value = 1;
    }
}

$(function () 
{
	$("#myFile").change(function(){
	    readURL(this);
	});
});

