/**
 * 
 *
 *
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
	/*
	 * data-img="assets/plugins/jquery-superbox/img/superbox/superbox-full-1.jpg" alt="" class="superbox-img"
	 */
//	var x = document.getElementById("superbox");
//	var y = document.getElementById("list");
//	
	document.getElementById("superbox").innerHTML ='';
	var r =$( "#obraselect option:selected" ).val();
	var peticion = "1,"+r;
	var loc = document.location.href;
	var getString = loc.split('?')[1];
	if(getString){
	var GET = getString.split('&');
	var get = {};// this object will be filled with the key-value pairs and
					// returned.

	for (var i = 0, l = GET.length; i < l; i++) {
		var tmp = GET[i].split('=');
		tipoArchivo = tmp[1];
	
		get[tmp[0]] = unescape(decodeURI(tmp[1]));
		
 	}
	}
	var elementos = new Array();
	var imagen;
	var archivo;
	
	$.ajax({
		url : '/biblioteca',
		type : 'post',
		data : {'objectJson' : JSON.stringify(peticion)},
		success: function(data){
		for(var size=0 ; size<data.length ; size++){
			var url = data[size].tipo;
			var elementos = url.split("*-*");
			url =  elementos[0]+elementos[1];
			
			if(data[size].id == 3 || data[size].id== 4 || data[size].id== 9) {
				imagen = "<img src=\"views/assets/img/vid.png\" ";
				//archivo=data[size].nombre+"mp4";
		}
			else if(data[size].id == 5){
				imagen = "<img src=\"views/assets/img/pdf.png\" ";
				//archivo=data[size].nombre+"pdf;
			}
			else if(data[size].id == 6){
				imagen =  "<img src=\"views/assets/img/xls.png\" ";
				//archivo=data[size].nombre+"xls";
			}
			else if(data[size].id == 7){ 
				imagen = "views/assets/img/doc.png";
				//archivo=data[size].nombre+"doc";
			}
			else if(data[size].id == 8){ 
				imagen = "views/assets/img/ppt.png";
				//archivo=data[size].nombre+"ppt";
			}
			else{
				
				imagen = "<img src=\""+url+"\" ";
				//archivo=data[size].nombre+"png";
			}

			//btn.onclick = function() { alert(data[size].tipo); }
//			var btn = document.createElement("BUTTON");
//			btn.text="botontgrwtgtegtegergdegvdgefefgfr";
//			btn.onclick=function(){
//				alert("grbvfd");}
//			document.getElementById("superbox").appendChild(btn);
			 var element = document.createElement("button");
			    //Assign different attributes to the element. 
			  
			    element.value = "cmbio"; // Really? You want the default value to be the type string?
			    // And the name too?
			    element.onclick = function() { // Note this is a function
			        alert("blabla");
			    };

			   
			
				
				
			document.getElementById("superbox").innerHTML +=
				
				
				"<div class=\"superbox-list\" id=\"img\">"+
				//"<img src=\""+p.Path+"\" "+
				//"<img src=\"http://1-dot-cao-iuyet-server.appspot.com/serve?blob-key="+p.Path+"\" "+
				imagen+
				//"<img src=\"http://1-dot-iuyet-csgit-cao.appspot.com/serve?blob-key="+imagen+"\" "+
				//"data-img=\""+p.Path+"\" class=\"superbox-img\" style= \"width: 170px; height: 180px;\">"+
				//"data-img=\"http://1-dot-cao-iuyet-server.appspot.com/serve?blob-key="+p.Path+"\" class=\"superbox-img\" style= \"width: 170px; height: 180px;\">"+
				"data-img=\"assets/plugins/jquery-superbox/img/superbox/superbox-full-1.jpg\" class=\"superbox-img\" style= \"width: 170px; height: 180px;\">"+
				"<span>"+data[size].nombre+"</span>"+"</br>"+
				//"<button id=boton>frfccd</button>"+
				//"<button onclick=\""+descarga(data[size].tipo)+"\">Ver </button>"+
				//btn+
				"<button class=\"btn btn-primary btn-cons \" name=blob-key value="+elementos[1]+" onclick=\"descarga(\'"+data[size].tipo+"\')\">Descargar </button>"+"</br>"+
				"<button class=\"btn btn-primary btn-cons \" name=eliminar id=eliminar value ="+data[size].idMultimedia+":obra"+" onclick=\"eliminar(\'"+data[size].tipo+"\')\">Eliminar </button>"
				;
				"</div>";

		
			
			
			
		}
			
		},
		error: function(jHR,e,throwsError){
			alert(e);
		}		
});
	
	
	function descarga (){
		alert("llegue a la descarga");
		
	}
//for(var t=0 ; t<25 ;t++){
//
//		//code
//	
//	
//			document.getElementById("superbox").innerHTML +=
//			
//			"<div class=\"superbox-list\" id=\"img\">"+
//			//"<img src=\""+p.Path+"\" "+
//			//"<img src=\"http://1-dot-cao-iuyet-server.appspot.com/serve?blob-key="+p.Path+"\" "+
//			"<img src=\"assets/plugins/jquery-superbox/img/superbox/superbox-thumb-1.jpg\" "+
//			//"data-img=\""+p.Path+"\" class=\"superbox-img\" style= \"width: 170px; height: 180px;\">"+
//			//"data-img=\"http://1-dot-cao-iuyet-server.appspot.com/serve?blob-key="+p.Path+"\" class=\"superbox-img\" style= \"width: 170px; height: 180px;\">"+
//			"data-img=\"assets/plugins/jquery-superbox/img/superbox/superbox-full-1.jpg\" class=\"superbox-img\" style= \"width: 170px; height: 180px;\">"+
//			"<button>Descripcion</button>"+
//			"</div>";
//			
//}
	
}
//function cambio(){
//	alert($( "#obraselect option:selected" ).text());
//	
//	var t = $( "#obraselect option:selected" ).text();
//	var r =$( "#obraselect option:selected" ).val();
//	document.getElementById("idreferencia").value = $( "#obraselect option:selected" ).val();
//	document.getElementById("tiporeferencia").value =0;
//	
//	
//	
//}