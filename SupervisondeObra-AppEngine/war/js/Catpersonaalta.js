
function enviar(){
	
	if(document.getElementById("CatPersona_tipoPersona").value.trim()==""){
		alert("Inserte un Tipo de Persona");
	}else if(document.getElementById("CatPersona_Descripcion").value.trim()==""){
		alert("Inserte una descripción");
	}else{
		var object = {
				'Tipo_Personal' :   $('#CatPersona_tipoPersona').val().trim(),
				'Descipcion' 	:   $('#CatPersona_Descripcion').val().trim(),
		};		
			$.ajax({
				url : '/altacatpersona',
				type : 'post',
				data : {'objectJson' : JSON.stringify(object)},
				success: function(data){
					alert(data);
					location.href="ListaCatPeronas.html";
				},
				error: function(jHR,e,throwsError){
					alert(e);
					window.location.reload();
				}
					
		});

	}	
	
}

function init(){
	
}
	