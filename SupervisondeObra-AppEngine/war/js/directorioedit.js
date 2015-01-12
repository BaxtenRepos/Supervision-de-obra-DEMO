/**
 * 
 
 *
 */

var idRef;
var idDirectorio;
var idTipoRef;
var personas= new Array();


function inicializaDirectorio(idreferencia,idtiporeferencia){
	//alert("el valodr de x es "+idreferencia +"y el valor de y es"+idtiporeferencia);
	idRef=idreferencia;
	idTipoRef=idtiporeferencia;
	solicitaelementos(idreferencia,idtiporeferencia);	
}

function init(){
	var loc = document.location.href;
    var getString = loc.split('?')[1];
    var GET = getString.split('&');
//    var get = {};//this object will be filled with the key-value pairs and returned.
 
    for(var i = 0, l = GET.length; i < l; i++){
       var tmp = GET[i].split('=');
       idRef=tmp[1];
       idTipoRef=tmp[2];
//       get[tmp[0]] = unescape(decodeURI(tmp[1]));
    }
    solicitaelementos(idRef,idTipoRef);
}
function solicitaelementos(idreferencia,idtiporeferencia){
//	$.ajax({
//		url : '/directorioedit',
//		type : 'post',
//		data : {'objectJson' : JSON.stringify("valores-"+idreferencia+"-"+idtiporeferencia)},
//		success: function(data){
//	
//			if(data.length==0){
//				alert('No se tiene ningun directorio asociado');
//			}
//				else{
//			for(var t=0;t<data.length;t++){
//				idDirectorio=data[t].id_directiorio;
//		
//				var label= document.createElement("label");
//				var description = document.createTextNode(data[t].nombre);
//				var checkbox = document.createElement("input");
//				checkbox.type = "checkbox";    // make the element a checkbox
//				checkbox.name = "checkbox";      // give it a name we can check on the server side
//				checkbox.value = data[t].id;         // make its value "pair"
//
//				label.appendChild(checkbox);   // add the box to the element
//				label.appendChild(description);// add the description to the element
//
//				// add the label element to your div
//				var mybr = document.createElement('br');
//				document.getElementById('CheckboxList').appendChild(mybr);
//				document.getElementById('CheckboxList').appendChild(label);
//				if(data[t].existe)
//				checkbox.checked = true;
//			}
//			}
//		},
//		error: function(jHR,e,throwsError){
//			alert(e);
//		}		
//	});
	
	var x = document.getElementById("bodyTable");
	
		$.ajax({
			url : '/directorioedit',
			type : 'post',
			data : {'objectJson' : JSON.stringify("valores-"+idreferencia+"-"+idtiporeferencia)},
			success: function(data){
				for (var i = 0; i < data.length; i++){
					idDirectorio=data[i].id_directiorio;
					var checkbox = document.createElement("input");
					checkbox.type = "checkbox";    // make the element a checkbox
					checkbox.name = "checkbox";      // give it a name we can check on the server side
					checkbox.value = data[i].id;
				
					if(data[i].existe){
						checkbox.checked = true;
					}
					
					var tr = document.createElement("tr");
					var td1 = document.createElement("td");
					var td2 = document.createElement("td");
					var td3 = document.createElement("td");
					var td4 = document.createElement("td");
					var td5 = document.createElement("td");
					
					td1.appendChild(checkbox);
					td2.innerHTML = data[i].id;
					td3.innerHTML = data[i].nombre;
					td4.innerHTML = data[i].cargo;
					td5.innerHTML = data[i].email;
					
					tr.appendChild(td1);
					tr.appendChild(td2);
					tr.appendChild(td3);
					tr.appendChild(td4);
					tr.appendChild(td5);
					
					x.appendChild(tr);
				}
				$(document).ready(function() {
					var table = $('#exampleTable').DataTable();
					$('#exampleTable tbody').on( 'click', 'tr', function () {
						if ( $(this).hasClass('selected') ) {
							$(this).removeClass('selected');
						}
						else {
							table.$('tr.selected').removeClass('selected');
							$(this).addClass('selected');
						}
					} );

				} );
			},
			error: function(jHR,e,throwsError){
				alert(e);
			}		
	});
}


function GuardaDirectorio(){
	var object = {
			'personas' :  personas,
			'idDirectorio': idDirectorio,
			'idReferencia':idRef,
			'idTipoRef' : idTipoRef,
		};
	$.ajax({
		url : '/directorioedit',
		type : 'post',
		data : {'objectJson' : JSON.stringify(object)},
		success: function(data){
			alert(data);
			if(idTipoRef == 0){
				location.href="PruebaProyecto.html";
			}else{
				location.href="ListaObras.html";
			}
		},
		error: function(jHR,e,throwsError){
			alert(e);
			if(idTipoRef == 0){
				location.href="PruebaProyecto.html";
			}else{
				location.href="ListaObras.html";
			}
		}	
	});	
}

$(function () {
	personas=[];
	$('#save').click(function () {
	    $('#exampleTable').find('tr').each(function () {
	        var row = $(this);
	        if (row.find('input[type="checkbox"]').is(':checked')) {
//	            alert("El valor del checkbox es "+ row.find('input[type="checkbox"]').val());
	            personas.push(row.find('input[type="checkbox"]').val());
	        }
	    });
	    GuardaDirectorio();
	});
});