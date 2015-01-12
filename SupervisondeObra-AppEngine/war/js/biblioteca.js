/**
 * 
 *
 *
 *
 */

var personas= new Array();

function init(){
	/*
	 * data-img="assets/plugins/jquery-superbox/img/superbox/superbox-full-1.jpg" alt="" class="superbox-img"
	 */
//	var x = document.getElementById("superbox");
//	var y = document.getElementById("list");
//	
	var peticion = "biblioteca";
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
	var object = {
			'peticion' :  peticion
		};
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
				"<button class=\"btn btn-primary btn-cons \" name=eliminar id=eliminar value ="+data[size].idMultimedia+" onclick=\"eliminar(\'"+data[size].tipo+"\')\">Eliminar </button>"
				
				
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

	
}
function elimina(evt){
	switch (this.id) {
	case 1:
		alert('soy perrito');
		evt.preventDefault();
		break;

	default:
		break;
	}
	
}
function eliminar(a){
	alert();
}







