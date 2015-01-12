/**
 * 
 */
	
$(function(){
	$('#check').change(function(){
		document.getElementById("cambios").style.display = 'block';
      
});
});


 function init(){
		var x = document.getElementById("proyectoselect");
		//var x = document.getElementById("obraselect");
		//document.getElementById("obraselect").style.display = 'none';
				$.ajax({
			url : '/generareporte',
			type : 'post',
			data : {'objectJson' : JSON.stringify("proyectos")},
			success: function(data){
			
				for (var t = 0; t < data.length; t++) {
					var option = document.createElement("option");
						option.text = data[t].nombre;
						option.value = data[t].id;
						x.add(option);
				}
				
			},
			error: function(jHR,e,throwsError){
				alert(e);
			}
				
	});
		
	 
 }
function cambioProyecto(){
	var x = document.getElementById("obraselect");
	
	document.getElementById("obraselect").style.display = 'block';

	var obra = document.getElementById("proyectoselect").value;
	console.log();
	
	$.ajax({
		url : '/generareporte',
		type : 'post',
		data : {'objectJson' : JSON.stringify("obras:"+obra+":")},
		success: function(data){
			if(data.length == 0){
					document.getElementById("obraselect").value = "No hay obras existentes";
			}else{
				for (var t = 0; t < data.length; t++) {
					var option = document.createElement("option");
						option.text = data[t].nombre ;
						option.value = data[t].id;
						x.add(option);
				}

			}

		},
		error: function(jHR,e,throwsError){
			alert(e);
		}
			
});
	
}



function enviar(){
	var object = {
			'idProyecto' : document.getElementById("proyectoselect").value,
			'idObra' : document.getElementById("obraselect").value,
			'NumeroConvenio' : $('#NumeroConvenio').val().trim(),
			'Fechaconvenio' : $('#Fechaconvenio').val().trim(),
			'ImporteConvenio' : $('#ImporteConvenio').val().trim(),
			'Dias' : $('#Dias').val().trim(),
			'Fechainicio' : $('#Fechainicio').val().trim(),
			'Fechafin' : $('#Fechafin').val().trim(),
			
		};
	$.ajax({
		url : '/generareporte',
		type : 'post',
		data : {'objectJson' : JSON.stringify(object)},
		success: function(data){
			if(data.length == 0){
					document.getElementById("obraselect").value = "No hay obras existentes";
			}else{
				for (var t = 0; t < data.length; t++) {
					var option = document.createElement("option");
						option.text = data[t].nombre ;
						option.value = data[t].id;
						x.add(option);
				}

			}

		},
		error: function(jHR,e,throwsError){
			alert(e);
		}
			
});
	
}
function cambioObra(){
	var checkconvenio = document.getElementById("checkconvenio");
	//document.getElementById("checkconvenio").style.display = 'none';
	document.getElementById("checkconvenio").style.display = 'block';
	document.getElementById("boton").style.display = 'block';
}
$(function () {
	$("#Fechainicio").datepicker();
	});
$(function () {
	$("#Fechafin").datepicker();
	});
$(function () {
	$("#Fechaconvenio").datepicker();
	});

//funcion solo para ocupar valida sesion
