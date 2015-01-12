var idCatpersona;
function init() 
{
	var loc = document.location.href;
    var getString = loc.split('?')[1];
    var GET = getString.split('&');
 
    for(var i = 0, l = GET.length; i < l; i++){
       var tmp = GET[i].split('=');
       idCatpersona=tmp[1];
    }
    limpiar();
	var peticion = 'opcioncatpersona:'+ idCatpersona;
	if(idCatpersona != 0){
		$.ajax({
			url : '/editcatpersona',
			type : 'post',
			data : {'objectJson' : JSON.stringify(peticion)},
			success: function(data){
			    document.getElementById("edit_CatPersona_tipoPersona").value = data.Tipo_Personal;
			    document.getElementById("edit_CatPersona_Descripcion").value = data.Descipcion;
			},
			error: function(jHR,e,throwsError){
				alert(e);
			}			
		});	
	}
}

function enviar() {
	var error = '<div class="alert alert-error">'+
    ' <button class="close" data-dismiss="alert"></button>';
	if(document.getElementById("edit_CatPersona_tipoPersona").value.trim()==""){
		limpiarDiv();
		document.getElementById("error_TipoPersona").innerHTML= error + 'Favor de introducir un tipo de persona. </div>';
	}else if(document.getElementById("edit_CatPersona_Descripcion").value.trim()==""){
		limpiarDiv();
		document.getElementById("error_Descripcion").innerHTML= error + 'Favor de introducir una descripción. </div>';
	}else {
		var object = {
				'id_Tipo_Personal' :   idCatpersona,
				'Tipo_Personal'    :   $('#edit_CatPersona_tipoPersona').val().trim(),
				'Descipcion' 	   :   $('#edit_CatPersona_Descripcion').val().trim()
		};		
			$.ajax({
				url : '/editcatpersona',
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

function limpiar(){
	 document.getElementById("edit_CatPersona_tipoPersona").value = "";
	 document.getElementById("edit_CatPersona_Descripcion").value = "";
}

function limpiarDiv(){
	document.getElementById("error_TipoPersona").innerHTML= "";
	document.getElementById("error_Descripcion").innerHTML= "";
}

