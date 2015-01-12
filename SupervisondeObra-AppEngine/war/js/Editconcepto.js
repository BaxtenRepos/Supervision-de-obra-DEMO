var idConcepto;
//editConcepto_conceptoselect
function init() {	
	var loc = document.location.href;
    var getString = loc.split('?')[1];
    var GET = getString.split('&');
 
    for(var i = 0, l = GET.length; i < l; i++){
       var tmp = GET[i].split('=');
       idConcepto=tmp[1];
    }
    limpiar();
    
    var peticion = 'concepto:'+ idConcepto;
	if(idConcepto != 0){
		$.ajax({
			url : '/editconcepto',
			type : 'post',
			data : {'objectJson' : JSON.stringify(peticion)},
			success: function(data){
				opcionobra(data.id_Obra);
				mostrarpadre(data.padre);
				document.getElementById("editConcepto_claveConcepto").value = data.Clave;
			    document.getElementById("editConcepto_descripcionConcepto").value = data.Descripcion.value;
		  	    document.getElementById("editConcepto_medidaConcepto").value = data.UnidadMedida;
		  	    document.getElementById("editConcepto_cantidadtotal").value = data.CantidadTotal;
		  	    document.getElementById("editConcepto_preciounitario").value =accounting.formatMoney(data.PrecioUnitario);
		  	    document.getElementById("editConcepto_importe").value = accounting.formatMoney(data.Importe);
		  	    document.getElementById("editConcepto_cantidadavance").value = data.CantidadAvance;
		  	    document.getElementById("editConcepto_fechainicio").value = data.Fecha_Inicio;
		  	    document.getElementById("editConcepto_fechafin").value = data.Fecha_Fin;
			},
			error: function(jHR,e,throwsError){
				alert(e);
			}			
		});	
	}
}
/*
function changeConceptoEdit() {
	var x = document.getElementById("editConcepto_conceptoselect");
	limpiar();
	var peticion = 'concepto:'+ x.value;
	if(x.value != 0){
		$.ajax({
			url : '/editconcepto',
			type : 'post',
			data : {'objectJson' : JSON.stringify(peticion)},
			success: function(data){
				mostrarobras(data.id_Obra);
				mostrarpadre(data.padre);
				document.getElementById("editConcepto_claveConcepto").value = data.Clave;
			    document.getElementById("editConcepto_descripcionConcepto").value = data.Descripcion;
		  	    document.getElementById("editConcepto_medidaConcepto").value = data.UnidadMedida;
		  	    document.getElementById("editConcepto_cantidadtotal").value = data.CantidadTotal;
		  	    document.getElementById("editConcepto_preciounitario").value = data.PrecioUnitario;
		  	    document.getElementById("editConcepto_importe").value = data.Importe;
		  	    document.getElementById("editConcepto_cantidadavance").value = data.CantidadAvance;
		  	    document.getElementById("editConcepto_fechainicio").value = data.Fecha_Inicio;
		  	    document.getElementById("editConcepto_fechafin").value = data.Fecha_Fin;
			},
			error: function(jHR,e,throwsError){
				alert(e);
			}			
		});	
	}
}*/

function enviar() {
	var y = document.getElementById("Editobraselect");
	var z = document.getElementById("Editpadreselect");
	
	if(document.getElementById("editConcepto_claveConcepto").value.trim()==""|
		   document.getElementById("editConcepto_descripcionConcepto").value.trim()==""|
		   document.getElementById("editConcepto_medidaConcepto").value.trim()==""|
     	   document.getElementById("editConcepto_cantidadtotal").value.trim()==""|
     	   document.getElementById("editConcepto_preciounitario").value.trim()==""|
     	   document.getElementById("editConcepto_importe").value.trim()==""|
     	   document.getElementById("editConcepto_cantidadavance").value.trim()==""|
     	   document.getElementById("editConcepto_fechainicio").value.trim()==""|
     	   document.getElementById("editConcepto_fechafin").value.trim()==""){ 
		alert('Ingrese todos los valores antes de guardar');
	}else if( !parseInt(document.getElementById("editConcepto_cantidadtotal").value))
		alert("Cantidad total debe ser un número");
	else if( isNaN(document.getElementById("editConcepto_cantidadavance").value))
		alert("Cantidad de avance debe ser un número");
	else{
		var object = {
//				'id_Concepto'	  : idConcepto,
//				'id_Obra'		  : y.value,
//				'padre'			  : z.value,
//				'Clave'			  : $('#editConcepto_claveConcepto').val().trim(),
//				'Descripcion'	  : $('#editConcepto_descripcionConcepto').val().trim(),
//				'UnidadMedida'    : $('#editConcepto_medidaConcepto').val().trim(),
//				'CantidadTotal'	  : $('#editConcepto_cantidadtotal').val().trim(),
//				'PrecioUnitario'  : $('#editConcepto_preciounitario').val().trim(),
//				'Importe' 		  : $('#editConcepto_importe').val().trim(),
//				'CantidadAvance'  : $('#editConcepto_cantidadavance').val().trim(),
//				'Fecha_Inicio' 	  : $('#editConcepto_fechainicio').val().trim(),
//				'Fecha_Fin'		  : $('#editConcepto_fechafin').val().trim()
				
				'id_Concepto'	  : idConcepto,
				'obra'			  : y.value,
				'padre'			  : z.value,
				'clave'			  : $('#editConcepto_claveConcepto').val().trim(),
				'descripcion'	  : $('#editConcepto_descripcionConcepto').val().trim(),
				'medida'		  : $('#editConcepto_medidaConcepto').val().trim(),
				'cantidadtotal'	  : $('#editConcepto_cantidadtotal').val().trim(),
				'preciounitario'  : $('#editConcepto_preciounitario').val().trim().replace("$","").split(',').join(''),
				'importe' 		  : $('#editConcepto_importe').val().trim().replace("$","").split(',').join(''),
				'cantidadavance'  : $('#editConcepto_cantidadavance').val().trim(),
				'fechainicio' 	  : $('#editConcepto_fechainicio').val().trim(),
				'fechafin'		  : $('#editConcepto_fechafin').val().trim()
			};

			$.ajax({
				url : '/editconcepto',
				type : 'post',
				data : {
					'objectJson' : JSON.stringify(object)
				},
				success : function(data) {
					//alert(data);
					$.confirm({
						'title'		: 'Confirmaci&oacute;n',
						'message'	: 'Concepto editado correctamente',
						'buttons'	: {
							'Cancelar'	: {
								'class'	: 'gray',
								'action': function(){	location.href="ListaConceptos.html";
								}
							},
							'Aceptar'	: {
								'class'	: 'blue',
								'action': function(){
									location.href="ListaConceptos.html";
								}
							}
						}
					});
//					window.location.reload();
			
				},
				error : function(jHR, e, throwsError) {
					$.confirm({
						'title'		: 'Error',
						'message'	: e,
						'buttons'	: {
							'Cancelar'	: {
								'class'	: 'gray',
								'action': function(){		window.location.reload();;
								}
							},
							'Aceptar'	: {
								'class'	: 'blue',
								'action': function(){
									window.location.reload();
								}
							}
						}
					});
				}
			});
	}	
}

function limpiar(){
	document.getElementById("editConcepto_claveConcepto").value = "";
    document.getElementById("editConcepto_descripcionConcepto").value = "";
    document.getElementById("editConcepto_medidaConcepto").value = "";
    document.getElementById("editConcepto_cantidadtotal").value = "";
    document.getElementById("editConcepto_preciounitario").value = "";
    document.getElementById("editConcepto_importe").value = "";
    document.getElementById("editConcepto_cantidadavance").value = "";
    document.getElementById("editConcepto_fechainicio").value = "";
    document.getElementById("editConcepto_fechafin").value = "";
}

function opcionobra(obras){
	var x = document.getElementById("Editobraselect");
	$('#Editobraselect option').remove();
	var opcionobra = 'opcionobra:' + obras;
	$.ajax({
		url : '/editconcepto',
		type : 'post',
		data : {'objectJson' : JSON.stringify(opcionobra)},
		success: function(data){
			var option = document.createElement("option");
			option.text = data.nombre + " - " + data.tipo;
			option.value = data.id;
			x.add(option);
		},
		error: function(jHR,e,throwsError){
			alert(e);
		}			
	});
}


function mostrarpadre(padres){
	var x = document.getElementById("Editpadreselect");
	$('#Editpadreselect option').remove();
	var opcionpadre = 'opcionpadre';
	$.ajax({
		url : '/editconcepto',
		type : 'post',
		data : {'objectJson' : JSON.stringify(opcionpadre)},
		success: function(data){
			
			var option = document.createElement("option");
			option.text = "Sere padre";
			option.value = 0;
			x.add(option);
			for (var i = 0; i < data.length; i++) {
				var option = document.createElement("option");
				option.text = data[i].Clave + " - " + data[i].Descripcion.value;
				option.value = data[i].id_Concepto;
				x.add(option);
				if(data[i].id_Concepto == padres){
					x.options[i + 1].selected = true;
				}
			}
		},
		error: function(jHR,e,throwsError){
			alert(e);
		}			
	});
}

$(function () {
	$("#editConcepto_fechainicio").datepicker();
});
$(function () {
	$("#editConcepto_fechafin").datepicker();
});