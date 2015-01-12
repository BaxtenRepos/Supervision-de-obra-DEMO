/**
 * 
 */function init(){
		var x = document.getElementById("bodyTable");
		var peticion = 'peticion';
		var linkCelda = "./EditUsuario.html";
			$.ajax({
				url : '/listausuarios',
				type : 'post',
				data : {'objectJson' : JSON.stringify(peticion)},
				success: function(data)
				{
					for (var i = 0; i < data.length; i++) 
					{
						var checkbox = document.createElement("input");
						checkbox.type = "checkbox";    // make the element a checkbox
						checkbox.name = "checkbox";      // give it a name we can check on the server side
						checkbox.value = data[i].id;
						
						var tr = document.createElement("tr");
						//var td1 = document.createElement("td");
						var td2 = document.createElement("td");
						var td3 = document.createElement("td");
						var td4 = document.createElement("td");
						var td5 = document.createElement("td");
						var td6 = document.createElement("td");
						
						//td1.innerHTML = data[i].id;
						td2.innerHTML = "<a href="+linkCelda+"?idUsuario="+data[i].id+">"+data[i].usuario+"</a>";
						td3.innerHTML = data[i].nombre;
						td4.innerHTML = data[i].tipoUsuario;
						td5.innerHTML = data[i].telefonos;
						td6.appendChild(checkbox);
						
						tr.appendChild(td6);
						//tr.appendChild(td1);
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
 
 var usuariosborrar= new Array();

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
 	        	usuariosborrar.push(object);
 	        }
 	    });
 	    borrar();
 	});
 });

 function borrar() {
 	
	var table = $('#exampleTable').DataTable();
 	var object = {
 		'borrar' : usuariosborrar
 	};
 	$.ajax({
 		url : '/listausuarios',
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
 				usuariosborrar.length = 0;
 			}else{
 				usuariosborrar.length = 0;
 				alert("El usuario(s) no se pueden eliminar por que estan asociada(s).");
 			}
 		},
 		error : function(jHR, e, throwsError) {
 			usuariosborrar.length = 0;
 			alert(e);
// 			window.location.reload();
 		}

 	});
 }

 

 

