

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

window.addEventListener('load', listenerForm, false);

function listenerForm() 
{
    document.getElementById("formulario1").addEventListener('submit', enviar, false);
}	
	
function enviar(evt)
{
	var x = document.getElementById("empresa");
	var tipoEmpresa = x.options[x.selectedIndex].value;	
	
	if(document.getElementById("rfc").value.trim()==""|
	     	   document.getElementById("nombre").value.trim()==""|
	     	   document.getElementById("siglas").value.trim()==""|
	     	   document.getElementById("imss").value.trim()==""|
	     	   document.getElementById("calle").value.trim()==""|
	     	   document.getElementById("num_ext").value.trim()==""|
	     	   document.getElementById("colonia").value.trim()==""|
	     	   document.getElementById("del_mun").value.trim()==""|
	 		   document.getElementById("entidad").value.trim()==""|
	    	   document.getElementById("codigo_postal").value.trim()=="")
		{ 
			alert('Ingrese todos los valores antes de guardar');
			//FALTA agragar el boon a la pag y los 
			evt.preventDefault();
		}
//	else if( !parseInt(document.getElementById("num_ext").value)){
//		alert("num_ext debe ser un número");
//		evt.preventDefault();
//	}
//	else if(!parseInt(document.getElementById("num_int").value)){
//		alert("num_int debe ser un número");
//		evt.preventDefault();
//	}
	else if(!parseInt(document.getElementById("codigo_postal").value)){
		alert("codigo postal debe ser un número");
		evt.preventDefault();
	}else if(document.getElementById("myFile_altaempresa").value=='')	{	
			alert('Seleccionar una foto');
			evt.preventDefault();
	}else {
		var rfc = document.getElementById('rfc').value;
		var formato = /^[A-Z]{3,4}(\d{6})((\D|\d){3})?$/;
		if(!(formato.test(rfc))){
			alert('RFC inválido');
			document.getElementById('rfc').focus();
			evt.preventDefault();
		}
	}
	
	console.log('OK para envio');
		//evt.preventDefault();
		
		
		
		
		/*var object = {
				'tipoEmpresa' : tipoEmpresa,
				'rfc' :      $('#rfc').val().trim(),
				'nombre' :   $('#nombre').val().trim(),
				'siglas' :   $('#siglas').val().trim(),
				'imss' :     $('#imss').val().trim(),
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
		});*/
	
}

function readURL(input) 
{
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#blah').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}

$(function () {
	$("#myFile_altaempresa").change(function(){
	    readURL(this);
	});
});

function validarRFC(){
	var rfc = document.getElementById('rfc').value;
	var formato = /^[A-Z]{3,4}(\d{6})((\D|\d){3})?$/;
	var error = '<div class="alert alert-error">'+
    ' <button class="close" data-dismiss="alert"></button>';
	if(!(formato.test(rfc))){
		document.getElementById("errorRFC").innerHTML= error + 'Favor de introducir un RFC válido. </div>';
		document.getElementById('rfc').focus();
	}else{
		document.getElementById("errorRFC").innerHTML= "";
	}
}
