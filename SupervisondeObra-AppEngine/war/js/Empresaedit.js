var idEmpresa;
function init() 
{
	var loc = document.location.href;
    var getString = loc.split('?')[1];
    var GET = getString.split('&');
 
    for(var i = 0, l = GET.length; i < l; i++){
       var tmp = GET[i].split('=');
       idEmpresa=tmp[1];
    }
	limpiar();
	var opcionempresa = 'opcionempresa:'+ idEmpresa;
	if(idEmpresa != 0){
		$.ajax({
			url : '/editempresa',
			type : 'post',
			data : {'objectJson' : JSON.stringify(opcionempresa)},
			success: function(data){
				mostrartipoempresa(data.id_tipo_empresa);
				document.getElementById("editEmpresa_Id").value = idEmpresa;
				document.getElementById("editEmpresa_rfc").value = data.RFC;
				document.getElementById("editEmpresa_nombre").value = data.Nombre;
			    document.getElementById("editEmpresa_siglas").value = data.Siglas;
			    document.getElementById("editEmpresa_imss").value = data.IMSS;
		  	    document.getElementById("editEmpresa_calle").value = data.Calle;
		  	    document.getElementById("editEmpresa_num_ext").value = data.Num_Ext;
		  	    document.getElementById("editEmpresa_num_int").value = data.Num_Int;
		  	    document.getElementById("editEmpresa_colonia").value = data.Colonia;
		  	    document.getElementById("editEmpresa_del_mun").value = data.Del_o_Mun;
		  	    document.getElementById("editEmpresa_entidad").value = data.Entidad;
		  	    document.getElementById("editEmpresa_codigo_postal").value = data.Codi_Postal;
		  	    document.getElementById("blah").src = data.Logo;
		  	    document.getElementById("isNew").value = 0;
		  	    
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
    document.getElementById("formularioE").addEventListener('submit', enviar, false);  
}

function enviar(evt) 
{
	var y = document.getElementById("editEmpresa_selectempresa");
	
	if(document.getElementById("editEmpresa_rfc").value.trim()==""|
		   document.getElementById("editEmpresa_nombre").value.trim()==""|
		   document.getElementById("editEmpresa_siglas").value.trim()==""|
		   document.getElementById("editEmpresa_imss").value.trim()==""|
     	   document.getElementById("editEmpresa_calle").value.trim()==""|
     	   document.getElementById("editEmpresa_num_ext").value.trim()==""|
     	   document.getElementById("editEmpresa_colonia").value.trim()==""|
     	   document.getElementById("editEmpresa_del_mun").value.trim()==""|
     	   document.getElementById("editEmpresa_entidad").value.trim()==""|
     	  document.getElementById("editEmpresa_codigo_postal").value.trim()==""){ 
		alert('Ingrese todos los valores antes de guardar');
		evt.preventDefault();
	}
	else if(!parseInt(document.getElementById("editEmpresa_codigo_postal").value)){
		alert("codigo postal debe ser un número");
		evt.preventDefault();
	}else{
		var rfc = document.getElementById('editEmpresa_rfc').value;
		var formato = /^[A-Z]{3,4}(\d{6})((\D|\d){3})?$/;
		if(!(formato.test(rfc))){
			alert('RFC inválido');
			document.getElementById('editEmpresa_rfc').focus();
			evt.preventDefault();
		}
	}
		console.log('OK para envio '); 
		//evt.preventDefault();
		
		/*
		var object = {
				'id_Empresa'	  : idEmpresa,
				'id_tipo_empresa' : y.value,
				'RFC'			  : $('#editEmpresa_rfc').val().trim(),
				'Nombre'	      : $('#editEmpresa_nombre').val().trim(),
				'Siglas'          : $('#editEmpresa_siglas').val().trim(),
				'IMSS'            : $('#editEmpresa_imss').val().trim(),
				'Calle'	          : $('#editEmpresa_calle').val().trim(),
				'Num_Ext'         : $('#editEmpresa_num_ext').val().trim(),
				'Num_Int' 		  : $('#editEmpresa_num_int').val().trim(),
				'Colonia'         : $('#editEmpresa_colonia').val().trim(),
				'Del_o_Mun' 	  : $('#editEmpresa_del_mun').val().trim(),
				'Entidad' 	      : $('#editEmpresa_entidad').val().trim(),
				'Codi_Postal'	  : $('#editEmpresa_codigo_postal').val().trim()
			};

			$.ajax({
				url : '/editempresa',
				type : 'post',
				data : {
					'objectJson' : JSON.stringify(object)
				},
				success : function(data) {
					alert(data);
					window.location.reload();
				},
				error : function(jHR, e, throwsError) {
					alert(e);
					window.location.reload();
				}

			});*/
}

function limpiar(){
	document.getElementById("editEmpresa_rfc").value = "";
	document.getElementById("editEmpresa_nombre").value = "";
    document.getElementById("editEmpresa_siglas").value = "";
    document.getElementById("editEmpresa_calle").value = "";
    document.getElementById("editEmpresa_num_ext").value = "";
    document.getElementById("editEmpresa_num_int").value = "";
    document.getElementById("editEmpresa_colonia").value = "";
    document.getElementById("editEmpresa_del_mun").value = "";
    document.getElementById("editEmpresa_entidad").value = "";
    document.getElementById("editEmpresa_codigo_postal").value = "";
}

function mostrartipoempresa(idTipo){
	var x = document.getElementById("editEmpresa_selectempresa");
	var opciondependencias = 'opciondependencias';
	$.ajax({
		url : '/editempresa',
		type : 'post',
		data : {'objectJson' : JSON.stringify(opciondependencias)},
		success: function(data){
			for(var q=0;q<data.length;q++){
				var option = document.createElement("option");
				option.value = data[q].id;
				option.text = data[q].tipoEmpresa;
				x.add(option);
				if(data[q].id == idTipo){
					x.options[q].selected = true;
				}
			}
		},
		error: function(jHR,e,throwsError){
			alert(e);
		}			
	});
}

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
	$("#myFile_editempresa").change(function(){
	    readURL(this);
	});
});

function validarRFC(){
	var rfc = document.getElementById('editEmpresa_rfc').value;
	var formato = /^[A-Z]{3,4}(\d{6})((\D|\d){3})?$/;
	var error = '<div class="alert alert-error">'+
    ' <button class="close" data-dismiss="alert"></button>';
	if(!(formato.test(rfc))){
		document.getElementById("errorRFC").innerHTML= error + 'Favor de introducir un RFC válido. </div>';
		document.getElementById('editEmpresa_rfc').focus();
	}else{
		document.getElementById("errorRFC").innerHTML= "";
	}
}




