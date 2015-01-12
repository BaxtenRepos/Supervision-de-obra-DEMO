
function enviarCatTipoMaquinaria(){
	if(document.getElementById("CatMaquinaria_tipoMaquinaria").value.trim()==""){
		alert("Inserte un Tipo de Maquinaria");
	}else{
		var object = {
				'Tipo_Maquinaria' :   $('#CatMaquinaria_tipoMaquinaria').val().trim(),
				'Descripcion' 	:   $('#CatMaquinaria_Descripcion').val().trim()
		};		
			$.ajax({
				url : '/altacatmaquinaria',
				type : 'post',
				data : {'objectJson' : JSON.stringify(object)},
				success: function(data){
					alert(data);
				},
				error: function(jHR,e,throwsError){
					alert(e);
				}
					
		});

	}	
	
}
	