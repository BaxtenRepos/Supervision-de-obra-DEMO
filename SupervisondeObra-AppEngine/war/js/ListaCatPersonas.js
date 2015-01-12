/**
 * 
 */function init(){
		var x = document.getElementById("bodyTable");
		var linkCelda = "./EditCatTipoPersonas.html";
		var peticion = 'peticion';
			$.ajax({
				url : '/listacatpersonas',
				type : 'post',
				data : {'objectJson' : JSON.stringify(peticion)},
				success: function(data)
				{
					for (var i = 0; i < data.length; i++) 
					{
						var checkbox = document.createElement("input");
						checkbox.type = "checkbox";    // make the element a checkbox
						checkbox.name = "checkbox";      // give it a name we can check on the server side
						checkbox.value = data[i].id_Tipo_Personal;
						
						var tr = document.createElement("tr");
					//	var td1 = document.createElement("td");
						var td2 = document.createElement("td");
						var td3 = document.createElement("td");
						var td4 = document.createElement("td");
						
					//	td1.innerHTML = data[i].id_Tipo_Personal;
						td2.innerHTML = "<a href="+linkCelda+"?idTipoPersona="+data[i].id_Tipo_Personal+">"+data[i].Tipo_Personal+"</a>";
						td3.innerHTML = data[i].Descipcion;
						td4.appendChild(checkbox);
						
						tr.appendChild(td4);
					//	tr.appendChild(td1);
						tr.appendChild(td2);
						tr.appendChild(td3);
						
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
 
 var catpersonaborrar = new Array();

 $(function () {
 	catpersonaborrar=[];
 	$('#borrar').click(function () {
 	    $('#exampleTable').find('tr').each(function () {
 	        var row = $(this);
 	        if (row.find('input[type="checkbox"]').is(':checked')) {
 	        	var object = {
 	        	 		'id' : row.find('input[type="checkbox"]').val(),
 	        	 		'index' : row.index() 
 	        	 	};
 	        	catpersonaborrar.push(object);
 	        }
 	    });
 	    borrar();
 	});
 });

 function borrar() {
 	
	var table = $('#exampleTable').DataTable();
 	var object = {
 		'borrar' : catpersonaborrar
 	};
 	$.ajax({
 		url : '/listacatpersonas',
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
 				catpersonaborrar.length = 0;
 			}
 		},
 		error : function(jHR, e, throwsError) {
 			catpersonaborrar.length = 0;
 			alert(e);
// 			window.location.reload();
 		}

 	});
 }

 

 

