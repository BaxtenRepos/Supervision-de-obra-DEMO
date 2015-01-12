
var idMaquinaria;
var idMultimedia;
function init() {
	var loc = document.location.href;
    var getString = loc.split('?')[1];
    var GET = getString.split('&');
 
    for(var i = 0, l = GET.length; i < l; i++){
       var tmp = GET[i].split('=');
       idMaquinaria=tmp[1];
    }
    limpiar();
	var opcionmaquinaria = 'opcionmaquinaria:'+ idMaquinaria;
	if(idMaquinaria != 0){
		$.ajax({
			url : '/editmaquinaria',
			type : 'post',
			data : {
				'objectJson' : JSON.stringify(opcionmaquinaria)
			},
			success : function(data) {
				mostrarSelect(data.id_tipo_Maquinaria);
				document.getElementById("EditMaquinaria_Id").value = data.id_Maquinaria;
			    document.getElementById("EditMaquinaria_Nombre").value = data.Nombre;
			    document.getElementById("EditMaquinaria_Descripcion").value = data.Descripcion;
			    document.getElementById("blah").src = data.Path_Imagen;
			    idMultimedia = data.idMultimedia;
			    document.getElementById("isNew").value = 0;
			},
			error : function(jHR, e, throwsError) {
				alert(e);
			}
		});
	}
}

function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#blah').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
        document.getElementById("isNew").value = 1;
    }
}

$(function () {
	$("#myFile").change(function(){
	    readURL(this);
	});
});

function limpiar(){
	   $('#TipoMaquinaria option').remove();
	   document.getElementById("EditMaquinaria_Nombre").value="";
	   document.getElementById("EditMaquinaria_Descripcion").value="";
}

function mostrarSelect(ida){
	var a = document.getElementById("TipoMaquinaria");
	var opciontipoMaquinaria = 'opciontipoMaquinaria';
	$.ajax({
		url : '/editmaquinaria',
		type : 'post',
		data : {'objectJson' : JSON.stringify(opciontipoMaquinaria)},
		success: function(data){
			for (var i = 0; i < data.length; i++) {
				var option = document.createElement("option");
				option.text = data[i].Tipo_Maquinaria;
				option.value = data[i].id_tipo_Maquinaria;
				a.add(option);
				if(data[i].id_tipo_Maquinaria == ida){
					a.options[i].selected = true;
				}
			}
		},
		error: function(jHR,e,throwsError){
			alert(e);
		}			
	});
}

window.addEventListener('load', listenerForm, false);

function listenerForm(){
    document.getElementById("formulario1").addEventListener('submit', enviar, false);  
}

function enviar(evt){
	var x = document.getElementById("TipoMaquinaria");
	var TipoMaquinaria = x.options[x.selectedIndex].value;	
	
	if(TipoMaquinaria == 0){
			alert('Debe dar de alta tipo maquinaria'); 
			evt.preventDefault();
	}else if(document.getElementById("EditMaquinaria_Nombre").value.trim()==""|
 	   document.getElementById("EditMaquinaria_Descripcion").value.trim()==""){ 
			alert('Ingrese todos los valores antes de guardar'); 
			evt.preventDefault();
	}else if(document.getElementById("blah").value==''){	
			alert('Debe seleccionar una imagen');
			evt.preventDefault();
	}
	console.log('OK para envio');	
}
