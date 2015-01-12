/**
 * 
 */function init(){
		var x = document.getElementById("bodyTable");
		var linkCelda = "./EditObra.html";
		var peticion = 'peticion';
			$.ajax({
				url : '/listaobras',
				type : 'post',
				data : {'objectJson' : JSON.stringify(peticion)},
				success: function(data){
					for (var i = 0; i < data.length; i++){
						var checkbox = document.createElement("input");
						checkbox.type = "checkbox";    // make the element a checkbox
						checkbox.name = "checkbox";      // give it a name we can check on the server side
						checkbox.value = data[i].id_Obra;
						
						var tr = document.createElement("tr");
						//var td1 = document.createElement("td");
						var td2 = document.createElement("td");
						var td3 = document.createElement("td");
						var td4 = document.createElement("td");
						var td5 = document.createElement("td");
						var td7 = document.createElement("td");
						var td8 = document.createElement("td");
						var td9 = document.createElement("td");
						var td10 = document.createElement("td");
						
						//td1.innerHTML = data[i].id_Obra;
						td2.innerHTML = "<a href="+linkCelda+"?idObra="+data[i].id_Obra+">"+data[i].Nombre+"</a>";
						td3.innerHTML = data[i].Descripcion;
						td4.innerHTML = data[i].Poyecto;
						td5.innerHTML = data[i].Dependencia;
						td7.innerHTML = data[i].FechaInicioContrato;
						td8.innerHTML = data[i].FechaTerminoContrato;
					//	td9.innerHTML = data[i].ImporteContratoSinIVA;
						td9.innerHTML = accounting.formatMoney(data[i].ImporteContratoSinIVA);
						td10.appendChild(checkbox);
						
						tr.appendChild(td10);
					//	tr.appendChild(td1);
						tr.appendChild(td2);
						tr.appendChild(td3);
						tr.appendChild(td4);
						tr.appendChild(td5);
						tr.appendChild(td7);
						tr.appendChild(td8);
						tr.appendChild(td9);
						
						x.appendChild(tr);
					}
					$(document).ready(function() {
						 $('#exampleTable').DataTable();
					} );
				},
				error: function(jHR,e,throwsError){
					alert(e);
				}		
		});
}
 
 var obrasborrar= new Array();

 $(function () {
	 obrasborrar=[];
 	$('#borrar').click(function () {
 	    $('#exampleTable').find('tr').each(function () {
 	        var row = $(this);
 	        if (row.find('input[type="checkbox"]').is(':checked')) {
 	        	var object = {
 	        	 		'id' : row.find('input[type="checkbox"]').val(),
 	        	 		'index' : row.index() 
 	        	 	};
 	        	obrasborrar.push(object);
 	        }
 	    });
 	    borrar();
 	});
 });

 function borrar() {
 	
	var table = $('#exampleTable').DataTable();
 	var object = {
 		'borrar' : obrasborrar
 	};
 	$.ajax({
 		url : '/listaobras',
 		type : 'post',
 		data : {
 			'objectJson' : JSON.stringify(object)
 		},
 		success : function(data) {
 			var table = $('#exampleTable').DataTable();
 			if(data.length != 0){
 				$('#exampleTable').find('tr').each(function () {
					var table = $('#exampleTable').DataTable();
 		 	        var fila = $(this);
 		 	        if (fila.find('input[type="checkbox"]').is(':checked') && data.contains(fila.find('input[type="checkbox"]').val())) {
	 	        		table.row(fila.index()).remove().draw(false);
 		 	        }
 		 	    }); 				
// 				var cont = 0;
// 				for (var i = 0; i < data.length; i++) {
// 					table.row(data[i].index-cont).remove().draw( false );
// 					cont++;
//				}
 				obrasborrar.length = 0;
 			}else{
 				obrasborrar.length = 0;
 				alert("La(s) obra(s) no se pueden eliminar por que estan asociada(s).");
 			}
 		},
 		error : function(jHR, e, throwsError) {
 			obrasborrar.length = 0;
 			alert(e);
// 			window.location.reload();
 		}

 	});
 } 

 Array.prototype.contains = function(obj) {
	    var i = this.length;
	    while (i--) {
	        if (this[i] == obj) {
	            return true;
	        }
	    }
	    return false;
	}

