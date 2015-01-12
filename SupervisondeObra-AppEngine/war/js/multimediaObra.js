/**
 * 
 */
function init(){
	var x = document.getElementById("obraselect");
	$.ajax({
		url : '/altamultimedia',
		type : 'post',
		data : {'objectJson' : JSON.stringify("obras")},
		success: function(data){
			if(data.length == 0){
					document.getElementById("obraselect").value = "Seleccione una obra";
			}else{
				for (var t = 0; t < data.length; t++) {
					var option = document.createElement("option");
						option.text = data[t].nombre;
						option.value = data[t].tipo;
						x.add(option);
				}

			}

		},
		error: function(jHR,e,throwsError){
			alert(e);
		}
			
});
	
}
function cambio(){
	//alert($( "#obraselect option:selected" ).text());
	
	var t = $( "#obraselect option:selected" ).text();
	var r =$( "#obraselect option:selected" ).val();
	document.getElementById("idreferencia").value = $( "#obraselect option:selected" ).val();
	document.getElementById("tiporeferencia").value =1;
	
	
	
}