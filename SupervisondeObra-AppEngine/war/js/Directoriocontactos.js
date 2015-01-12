/**
 * 
 */

function iniciadirectorio(){
		var proyectos = 'proyectos';
			$.ajax({
				url : '/directoriocontactos',
				type : 'post',
				data : {'objectJson' : JSON.stringify(proyectos)},
				success: function(data){
					document.getElementById("proyectos_list").innerHTML ="";
					for (var i = 0; i < data.length; i++) {
						document.getElementById("proyectos_list").innerHTML += "<li><a href=javascript:mostrarobras('"+data[i].id+"'); id='"+data[i].id+"' ><div class='status-icon green'></div>"+data[i].nombre+"</a></li>";
					}
				},
				error: function(jHR,e,throwsError){
					alert(e);
				}		
		});
}

function mostrarobras(id){
	var obra = 'obra:'+id;
	$.ajax({
		url : '/directoriocontactos',
		type : 'post',
		data : {'objectJson' : JSON.stringify(obra)},
		success: function(data){
			document.getElementById("obras_list").innerHTML ="";
			for (var i = 0; i < data.length; i++) {
				document.getElementById("obras_list").innerHTML += "<li><a href=javascript:mostrarContactos('"+data[i].id+"','1'); id='"+data[i].id+"' ><div class='status-icon green'></div>"+data[i].nombre+"</a></li>";
			}
			mostrarContactos(id, 0);
		},
		error: function(jHR,e,throwsError){
			alert(e);
		}		
	});
	
}

function mostrarContactos(id, referencia){
	var contactoproyecto = 'contactoproyecto:'+id + ':' + referencia;
	$.ajax({
		url : '/directoriocontactos',
		type : 'post',
		data : {'objectJson' : JSON.stringify(contactoproyecto)},
		success: function(data){
			document.getElementById("regTable").innerHTML ="";
			for (var i = 0; i < data.length; i++) {
				 document.getElementById("regTable").innerHTML +=
                      "<tr>"+
                           "<td>"+
                                "<div class=\"user-details-wrapper active\" data-chat-status=\"online\" data-chat-user-pic=\""+data[i].path+"\" data-chat-user-pic-retina=\""+data[i].path+"\" data-user-name=\"Jane Smith\" onclick=\"mostrarDetalleContacto("+data[i].id+")\">"+
                                	"<div class=\"user-profile\">"+
                                          "<img src=\""+data[i].path+"\" alt=\"\" data-src=\""+data[i].path+"\" data-src-retina=\"assets/img/profiles/d2x.jpg\" width=\"35\" height=\"35\">"+
                                     "</div>"+
                                     "<div class=\"user-details\">"+
                                          "<div class=\"user-name\" type=\"text\" id=\""+data[i].id+"\">"+
                                          		data[i].nombre	+
                                          "</div>"+
                                          "<div class=\"user-more\">"+
                                          		data[i].tipo	+
                                          "</div>"+
                                     "</div>"+
                                     "<div class=\"clearfix\"></div>"+
                                "</div>"+
                           "</td>"+
                      "</tr>";
				
				
			}
		},
		error: function(jHR,e,throwsError){
			alert(e);
		}		
	});

}

function mostrarDetalleContacto(id){
	$('#messages-wrapper').addClass('animated');
	$('#messages-wrapper').show();			
	$('#chat-users').removeClass('animated');
	$('#chat-users').hide();
	$('.chat-input-wrapper').show();	
	
	var detalleContacto = 'detalleContacto:'+id;
	$.ajax({
		url : '/directoriocontactos',
		type : 'post',
		data : {'objectJson' : JSON.stringify(detalleContacto)},
		success: function(data){
			document.getElementById("divPopupContent").innerHTML = 
                "<div class=\"grid simple\">"+
                     "<div class=\"grid-title no-border\">"+
                          "<img src=\""+data.Path_Imagen+"\" alt=\"\" data-src=\""+data.Path_Imagen+"\" data-src-retina=\""+data.Path_Imagen+"\" width=\"35\" height=\"35\" />"+
                          "<span class=\"semi-bold\" style=\"font-size: large\"> "+data.nombre+"</span>"+
                     "</div>"+
                     "<div class=\"grid-body no-border\">"+
                          "<div class=\"row-fluid\">"+
                               "<div class=\"scroller\" data-height=\"220px\" data-always-visible=\"1\">"+
                                   "<p><span class=\"bold\">Dependencia:</span> "+data.empresa+"</p>"+
                                   "<p><span class=\"bold\">Cargo:</span> "+data.cargo+"</p>"+
                                   "<p><span class=\"bold\">Telefono(s):</span> "+data.telefono+"</p>"+
                                   "<p><span class=\"bold\">RFC:</span> "+data.rfc+"</p>"+
                                   "<p><span class=\"bold\">Correo (s):</span> "+
                                       "<input readonly=\"true\" id=\"mail\" style=\"background-color: transparent; border: hidden\" size=\"30\" value=\""+data.email+"\"></input>"+
                                   "</p>"+
                                   "<button class=\"btn btn-block btn-success\" onClick=\"enviarCorreo('mapa_proyectos')\">Enviar correo</button>"+
//                                   "<button class=\"btn btn-block btn-success\" onClick=\"hidePopup()\">Cancelar</button>"+
                               "</div>"+
                          "</div>"+
                     "</div>"+
                "</div>";
				
		},
		error: function(jHR,e,throwsError){
			alert(e);
		}		
	});
}

function doSearch(){
	  var tableReg = document.getElementById('regTable');
	  var searchText = document.getElementById('searchTerm').value.toLowerCase();
	  for (var i = 0; i < tableReg.rows.length; i++) {
	    var cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
	    var found = false;
	    for (var j = 0; j < cellsOfRow.length && !found; j++) {
	      var compareWith = cellsOfRow[j].innerHTML.toLowerCase();
	      if (searchText.length == 0 || (compareWith.indexOf(searchText) > -1)) {
	        found = true;
	      }
	    }
	    if (found){
	      tableReg.rows[i].style.display = '';
	    }else{
	      tableReg.rows[i].style.display = 'none';
	    }
	  }
	}
