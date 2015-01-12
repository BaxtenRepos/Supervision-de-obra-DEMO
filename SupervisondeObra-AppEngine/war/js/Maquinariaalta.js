

function init() {
	var x = document.getElementById("TipoMaquinaria");
	var peticion = 'peticion';
	var object = {
		'peticion' : peticion
	};
	$.ajax({
		url : '/uploadMaquinaria',
		type : 'post',
		data : {
			'objectJson' : JSON.stringify(peticion)
		},
		success : function(data) {
			// alert("todo bn");
			var elementos = [];
			if(data.length!=0){
				for (var i = 0; i < data.length; i++) {
					var option = document.createElement("option");
					option.text = data[i].Tipo_Maquinaria;
					option.value = data[i].id_tipo_Maquinaria;
					x.add(option);

				}
			}else{
				var option = document.createElement("option");
				option.text = "Debe dar de alta tipo maquinaria";
				option.value = 0;
				x.add(option);
			}
			

		},
		error : function(jHR, e, throwsError) {
			alert(e);
		}

	});

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

$(function () {
	$("#myFile").change(function(){
	    readURL(this);
	});
});

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
	}else if(document.getElementById("AltaMaquinaria_Nombre").value.trim()==""|
 	   document.getElementById("AltaMaquinaria_Descripcion").value.trim()==""){ 
			alert('Ingrese todos los valores antes de guardar'); 
			evt.preventDefault();
	}else if(document.getElementById("myFile").value==''){	
			alert('Debe seleccionar una imagen');
			evt.preventDefault();
	}
	console.log('OK para envio');	
}

