function init(){
		var x = document.getElementById("obraselect");
		var peticion = 'obras';
		var dependencia =[];
		var secretaria = [];

			$.ajax({
				url : '/altaconcepto',
				type : 'post',
				data : {'objectJson' : JSON.stringify(peticion)},
				success: function(data){
					if(data.length == 0){
							document.getElementById("obraselect").value = "No hay obras existentes";
					}else{
						for (var t = 0; t < data.length; t++) {
							var option = document.createElement("option");
								option.text = data[t].nombre +"- "+ data[t].tipo;
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
function changeobra(){
	
	var x = document.getElementById("padreselect");
	var length = x.options.length;
	for (i = 0; i < length; i++) {
	  x.options[i] = null;
	}
	document.getElementById("padreselect").disabled = false;
	var obra = document.getElementById("obraselect").value;
	var peticion = 'conceptos:'+ obra;
	var object = {
			'peticion' :  peticion,
			'obra'     :  obra,
		};
	$.ajax({
		url : '/altaconcepto',
		type : 'post',
		data : {'objectJson' : JSON.stringify(peticion)},
		success: function(data){
			//alert("todo bn");
			if(data.length == 0){
				//alert("el arreglo viene vacio");
				//	document.getElementById("obraselect").value = "No hay obras existentes";
				var option = document.createElement("option");
				option.text = "No hay Conceptos en esa obra para seleccionar como padre";
				option.value=0;
				x.add(option);
			}else{
				var option = document.createElement("option");
				//option.text = data[t].nombre +"-"+ data[t].tipo;
			option.text = "Sere padre";
				x.add(option);
				for (var t = 0; t < data.length; t++) {
					var option = document.createElement("option");
					option.value = data[t].nombre +"-"+ data[t].tipo;
					option.text=data[t].nombre +"-"+ data[t].tipo;
				//	option.text = data[t].nombre;;
						x.add(option);

				}

			}

		},
		error: function(jHR,e,throwsError){
			alert(e);
		}
			
});
	
}	

function envia(){
	
	var idObra = document.getElementById("obraselect").value;
	$.ajax({
		url : '/validarsesion',
		type : 'post',
		data : {'objectJson' : JSON.stringify('idObra:'+idObra)},
		success: function(data){
		
		},
		error: function(jHR,e,throwsError){
			alert(e);
		}		
});
	
}
function enviar(){
	var padre=0;
	//var prueba1= parseInt($('#padreselect').val());
	var  prueba= $('#padreselect').val();
	if(prueba==null)padre=0;
	else if(isNaN(prueba)&&(prueba!="Sere padre"))
		padre=prueba;
//	else 
//		padre=prueba;
	
	//if($('#padreselect').val()=="No hay Conceptos en esa obra para seleccionar como padre"){padre = 0;}
	var object = {
					'obra' : $('#obraselect').val().trim(),
					'padre' : padre,
					'clave' : $('#clave-concepto').val().trim(),
					'descripcion' : $('#descripcion-concepto').val().trim(),
					'medida' : $('#medida-concepto').val().trim(),
					'cantidadtotal' : $('#cantidadtotal').val().trim(),
					'preciounitario' : $('#preciounitario').val().trim(),
					'importe' : $('#importe').val().trim(),
					'cantidadavance' : $('#cantidadavance').val().trim(),
					'fechainicio' : $('#fechainicio').val().trim(),
					'fechafin' : $('#fechafin').val().trim(),

				};
	console.log(object);
	$.ajax({
		url : '/altaconcepto',
		type : 'post',
		data : {'objectJson' : JSON.stringify(object)},
		success: function(data){ 
			console.log(data);  
			alert(data);
			
		},						
		
		error : function(jHR, e, throwsError){ //si el request o el response tienen problemas, se ejecuta el metodo error, puse una funcion anonima, la variable e, se carga con el error, que puedes
	
			alert(e);
			if(jHR.status=="400") alert('no a insertado los puntos'); 
							
		}					
							
							
	});
 // }else alert('no hay un padre');

	
}
function enviar_guardar(){
	enviar();
	
	$.ajax({
		url : '/altaconcepto',
		type : 'post',
		data : {'objectJson' : JSON.stringify('cerrarobra-'+$('#obraselect').val().trim()+'-f')},
		success: function(data){ 
			console.log(data);  
			alert(data);
			
		},						
		
		error : function(jHR, e, throwsError){ //si el request o el response tienen problemas, se ejecuta el metodo error, puse una funcion anonima, la variable e, se carga con el error, que puedes
	
			alert(e);
			if(jHR.status=="400") alert('no a insertado los puntos'); 
							
		}					
							
							
	});
	
	
}

$(function () {
	$("#fechainicio").datepicker();
});
$(function () {
	$("#fechafin").datepicker();
});
