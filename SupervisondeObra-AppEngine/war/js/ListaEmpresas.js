/**
 * 
 */function init(){
		var x = document.getElementById("bodyTable");
		var peticion = 'peticion';
		var linkCelda = "./EditEmpresas.jsp";
			$.ajax({
				url : '/listaempresas',
				type : 'post',
				data : {'objectJson' : JSON.stringify(peticion)},
				success: function(data)
				{
					for (var i = 0; i < data.length; i++) 
					{
						var checkbox = document.createElement("input");
						checkbox.type = "checkbox";    // make the element a checkbox
						checkbox.name = "checkbox";      // give it a name we can check on the server side
						checkbox.value = data[i].idEmpresa;
						
						var tr = document.createElement("tr");
					//	var td1 = document.createElement("td");
						var td2 = document.createElement("td");
						var td3 = document.createElement("td");
						var td4 = document.createElement("td");
						var td5 = document.createElement("td");
						var td6 = document.createElement("td");
						var td7 = document.createElement("td");
						var td8 = document.createElement("td");
						var td9 = document.createElement("td");
						
					//	td1.innerHTML = data[i].idEmpresa;
						td2.innerHTML = "<a href="+linkCelda+"?idEmpresa="+data[i].idEmpresa+">"+data[i].nombre+"</a>";
						td3.innerHTML = data[i].tipoEmpresa;
						td4.innerHTML = data[i].rfc;
						td5.innerHTML = data[i].siglas;
						td6.innerHTML = data[i].direccion;
						td7.innerHTML = data[i].codPostal;
						td8.appendChild(checkbox);
						td9.innerHTML = "<img src="+data[i].Path_Imagen+" style='max-width: 160px; max-height: 120px; border: none;'></>";
						
						tr.appendChild(td8);
						//tr.appendChild(td1);
						tr.appendChild(td2);
						tr.appendChild(td3);
						tr.appendChild(td4);
						tr.appendChild(td5);
						tr.appendChild(td6);
						tr.appendChild(td7);
						tr.appendChild(td9);
						
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

 var empresasborrar= new Array();

 $(function () {
 	empresasborrar=[];
 	$('#borrar').click(function () {
 	    $('#exampleTable').find('tr').each(function () {
 	        var row = $(this);
 	        if (row.find('input[type="checkbox"]').is(':checked')) {
 	        	var object = {
 	        	 		'id' : row.find('input[type="checkbox"]').val(),
 	        	 		'index' : row.index() 
 	        	 	};
 	        	empresasborrar.push(object);
 	        }
 	    });
 	    borrar();
 	});
 });

 function borrar() {
 	
	var table = $('#exampleTable').DataTable();
 	var object = {
 		'borrar' : empresasborrar
 	};
 	$.ajax({
 		url : '/listaempresas',
 		type : 'post',
 		data : {
 			'objectJson' : JSON.stringify(object)
 		},
 		success : function(data) {
 			var table = $('#exampleTable').DataTable();
 			if(data.length != 0){
 				var cont = 0;
 				for (var i = 0; i < data.length; i++) {
 					table.row(data[i].index-cont).remove().draw( false );
 					cont++;
				}
 				empresasborrar.length = 0;
 			}else{
 				empresasborrar.length = 0;
 				alert("La(s) empresa(s) no se pueden eliminar por que estan asociada(s).");
 			}
 		},
 		error : function(jHR, e, throwsError) {
 			empresasborrar.length = 0;
 			alert(e);
// 			window.location.reload();
 		}

 	});
 }
