/**
 * 
*/

function init(){
		var x = document.getElementById("bodyTable");
		var peticion = 'peticion';
		var linkCelda = "/views/EditProyecto.html";
			$.ajax({
				url : '/pruebaproyecto',
				type : 'post',
				data : {'objectJson' : JSON.stringify(peticion)},
				success: function(data){
				for (var i = 0; i < data.length; i++) {
						var tr = document.createElement("tr");
						//var td1 = document.createElement("td");
						var td2 = document.createElement("td");
						var td3 = document.createElement("td");
						var td4 = document.createElement("td");
						var td5 = document.createElement("td");
						var td6 = document.createElement("td");
						var td7 = document.createElement("td");
						
						//td1.innerHTML = data[i].id_Proyecto;
						td2.innerHTML = "<a href="+linkCelda+"?idProyecto="+data[i].id_Proyecto+">"+data[i].Nombre_largo+"</a>";
						td3.innerHTML = data[i].Nombre_corto;
						td4.innerHTML = data[i].Descripcion;
						td5.innerHTML = data[i].dependencia;
						td6.innerHTML = data[i].secretaria;
						var array_directivo = data[i].directivo;
						var nombres_directivos = "<ul> ";
						for (var int = 0; int < array_directivo.length; int++) {
							nombres_directivos += "<li>"+array_directivo[int] + "</li>";
						}
						nombres_directivos += "</ul> ";
						td7.innerHTML = nombres_directivos;
						//tr.appendChild(td1);
						tr.appendChild(td2);
						tr.appendChild(td3);
						tr.appendChild(td4);
						tr.appendChild(td5);
						tr.appendChild(td6);
						tr.appendChild(td7);
						
						x.appendChild(tr);
					}
					$(document).ready(function() 
					{
						$('#exampleTable').DataTable();

					} );
				},
				error: function(jHR,e,throwsError){
					alert(e);
				}		
		});
}
 

 

