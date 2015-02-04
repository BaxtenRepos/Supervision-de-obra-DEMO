//var ROOT = 'https://' + '1-dot-iuyet-csgit-cao.appspot.com' + '/_ah/api';//desarrollo
var ROOT = 'https://' + '1-dot-superobra-adquem.appspot.com' + '/_ah/api';//adquem
//var ROOT = 'https://' + '1-dot-cao-iuyet-pruebas.appspot.com' + '/_ah/api';//pruebas
var ruta = '1-dot-superobra-adquem.appspot.com';//adquem
//var ruta = '1-dot-iuyet-csgit-cao.appspot.com';//desarrollo

function init(){
    //directorio_obra();
    //directorio_proyecto();
    get_proyecto();
    searchProyecto();
    get_mapa();
    var get = getGET();
    console.log("IdObra en proyecto obra: "+get.idProyecto);
    console.log("IdObra en multimedia obra: "+get.idObra);
    var idObra = get.idObra;
    //video(get.idProyecto, get.idObra);
    getIdDirectivo();
    document.getElementById("returnObra").innerHTML =
    "<a href=\"detalle_obras_reporte.html?idProyecto="+get.idProyecto+"&&idObra="+idObra+"\"><i class=\"icon-custom-left\"></i></a>";
    //funcion de la galeria
     //fullimage_2(idObra);
}
function successConsulta() {
    console.log("Multimedia cargadas.....");
}

function errorCB(err) {
    console.log("Error processing SQL: "+err.code + " Message: "+err.message);
}
//galeria de carrusel
function ver() {
    var get = getGET();
    var obra = get.idObra;
    var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
    db.transaction(function(tx) {
	tx.executeSql('select * from MultimediaObras where cast(idObra as int) = ?',[obra],  function(tx, rs) {
	    console.log("Imagenes: " + rs.rows.length);
	    var temp = 1;
		for (var i = 0; i < rs.rows.length; i++) {
		    var p = rs.rows.item(i);
		    var indice = p.idMultimedia;
		    var desc = p.Descripcion;
			    document.getElementById("links").innerHTML =
			    
			    "<a href=\"http://"+ruta+"/serve?blob-key="+p.Path+"\" class=\"zoom\"  title=\""+desc+"\" data-gallery=\"\" data-unique-id=\""+indice+"\" id=\"disabled\">"+
		      //"<a href=\"http://1-dot-cao-iuyet-server.appspot.com/serve?blob-key="+p.Path+"\" class=\"zoom\"  title=\""+desc+"\" data-gallery=\"\" data-unique-id=\""+indice+"\" id=\"disabled\">"+
		      //"<a href=\"http://1-dot-superobra-adquem.appspot.com/serve?blob-key="+p.Path+"\" class=\"zoom\"  title=\""+desc+"\" data-gallery=\"\" data-unique-id=\""+indice+"\" id=\"disabled\">"+
			"<div class=\"superbox-list\">"+
	                  "<img src=\""+p.Path+"\" "+
		             "class=\"superbox-img\" style= \"width: 150px; height: 180px;\" id=\"disable\"/>"+
	               "</div>" +
		       "</a>";
		    cargar2();   
		}
        }, errorCB);
               
    }, errorCB, successConsulta());
    
  //cargar2();
}

function cargar2(){
    //fullimage_2(id_obra);
    blueimp.Gallery(
	document.getElementById('links').getElementsByTagName('a'),{
	    container: '#blueimp-gallery',
	    carousel: true,
	    closeOnSlideClick: true,
	    onslide: function (index, slide) {
				// Callback function executed on slide change.
	        console.log("index" + index);
				//var $anchor = jQuery('#links').find('a>div>img:eq(' + index + ')');
	        var $anchor = jQuery('#links').find('a:eq(' + index + ')');
		console.log('unique-id value is : '  + $anchor.data('unique-id'));
	    }
	}
    );
 }
 // fecha
 function fecha_2(fecha1, fecha2) {
    //code
    var get = getGET();
    /*
     *Fecha que recibe (dd/mm/yyyy), y la convierte a formato (yyyy/mm/dd),
     *para que se haga la comparacion de los dias, con la funcion de moment
     */
    fecha01 = convertir(fecha1);
    fecha02 = convertir(fecha2);
    //Recibe las fechas para la comparacion entre ellas
    fecha_01 = moment(fecha01);
    fecha_02 = moment(fecha02);
    //funcion que realiza la diferencia entre ellas
    dif = fecha_01.diff(fecha_02, 'days');
    //Comparacion para que los dias no sean mayores a 30 dias
    console.log("IdObra en proyecto obra: "+get.idProyecto);
    console.log("IdObra en multimedia obra: "+get.idObra);
    var idObra = get.idObra;
    //video(get.idProyecto, get.idObra, fecha01, fecha02);
    console.log("diferencia entre los dias: " + dif);
    if (dif <= -30 || dif >= 30) {
	//code
	alert("Se pasa de los 30 dias");
    }else{
	//console.log("se muestran las imagenes");
	gapi.client.load('communicationchannel', 'v1', function () {
	    gapi.client.communicationchannel.listMultimedia({"tipoReferencia": "8","idReferencia":String(idObra),"tipoArchivo":"0",}).execute(function (resp) { //alert("2");
	        if (resp.code){
		    alert("No hay imagenes de la obra :::");
		}else{
		    MultimediaItems = resp.items;
		    if (MultimediaItems) {
			//code
			console.log("Imagenes de obra: " + MultimediaItems.length);
			verimagenes(MultimediaItems, fecha01, fecha02);
		    }else{
			console.log("no existen imagenes");
			document.getElementById("galeria").innerHTML =
			    "<div class=\"superbox-list\">"+
				"No hay Imagenes y Videos que mostrar."+
			    "</div>";
		    }
		}
	    });
	}, ROOT);
    }
 }
 //
 function verimagenes(MultimediaItems, fecha01, fecha02) {
    //code
    var get = getGET();
    var obra = get.idObra;
    var proyecto = get.idProyecto;
    var contador = 0;
    var contador_1 = 0;
    console.log("multimedia de obras:" + MultimediaItems);
    for (var i = 0; i < MultimediaItems.length; i++) {
	var fechabd = MultimediaItems[i].fecha;
	var fechabd_2 =convertir(fechabd);
	switch (MultimediaItems[i].formato) {
	    //case
	    case '0':
		console.log("es una imagen.");
		if (moment(fecha02).isAfter(fechabd_2)) {
		    //code
		    if (moment(fecha01).isBefore(fechabd_2) && moment(fecha02).isAfter(fechabd_2)) { // la que funciona bien
			contador ++;
			fechabd = MultimediaItems[i].fecha;
			if (1 == contador) {
		    //code
			    document.getElementById("galeria").innerHTML =
				"<a href=\"fullimage.html?path="+MultimediaItems[i].path+"&&idObra="+obra+"&&idProyecto="+proyecto+"\">"+
				    "<div class=\"superbox-list\" id=\"img\">"+
					//"<img src=\"http://1-dot-cao-iuyet-pruebas.appspot.com/serve?blob-key="+MultimediaItems[i].path+"\" "+
					//"data-img=\"http://1-dot-cao-iuyet-pruebas.appspot.com/serve?blob-key="+MultimediaItems[i].path+"\" class=\"superbox-img\" style= \"width: 170px; height: 180px;\">"+
					"<img src=\"http://"+ruta+"/serve?blob-key="+MultimediaItems[i].path+"\" "+
					"data-img=\"http://"+ruta+"/serve?blob-key="+MultimediaItems[i].path+"\" class=\"superbox-img\" style= \"width: 170px; height: 180px;\">"+
					"<span>"+MultimediaItems[i].descripcion+"</span>"+
				    "</div>"+
				"</a>";
			}else{
			    document.getElementById("galeria").innerHTML +=
				"<a href=\"fullimage.html?path="+MultimediaItems[i].path+"&&idObra="+obra+"&&idProyecto="+proyecto+"\">"+
				    "<div class=\"superbox-list\" id=\"img\">"+
					"<img src=\"http://"+ruta+"/serve?blob-key="+MultimediaItems[i].path+"\" "+
					"data-img=\"http://"+ruta+"/serve?blob-key="+MultimediaItems[i].path+"\" class=\"superbox-img\" style= \"width: 170px; height: 180px;\">"+
					"<span>"+MultimediaItems[i].descripcion+"</span>"+
				    "</div>"+
				"</a>";
			} 
		    }else{
			console.log("no hay imagenes");
			document.getElementById("galeria").innerHTML =
			"<div class=\"superbox-list\">"+
			"No hay imagenes que mostrar"+
			"</div>";
		    }
		}
		break;
	    case '4':
		console.log("Es video.");
		if (moment(fecha02).isAfter(fechabd_2)) {
		    //code
		    if (moment(fecha01).isBefore(fechabd_2) && moment(fecha02).isAfter(fechabd_2)) { // la que funciona bien
			contador_1 ++;
			if (1 == contador_1) {
			    //code
			    console.log("hay videos");
			    document.getElementById("galeria2").innerHTML =
			    "<div class=\"superbox-list\" id=\"img\">"+
			        "<a href=\"fullvideo.html?idObra="+obra+"&&path="+MultimediaItems[i].path+"&&idProyecto="+proyecto+"\"><img src= \"iconos_png/vid.png\" width=\"100\" height=\"100\"></a>" +
				"<span>"+MultimediaItems[i].descripcion+"</span>" +
			    "<div>";
			}else{
			    console.log("ya no hay videos");
			    document.getElementById("galeria2").innerHTML +=
			    "<div class=\"superbox-list\" id=\"img\">"+
				"<a href=\"fullvideo.html?idObra="+obra+"&&path="+MultimediaItems[i].path+"&&idProyecto="+proyecto+"\"><img src= \"iconos_png/vid.png\" width=\"100\" height=\"100\"></a>" +
				"<span>"+MultimediaItems[i].descripcion+"</span>" +
			    "<div>";
			}
		    }else{
			console.log("no hay videos antes de esa fecha");
			console.log("no hay videos");
			document.getElementById("galeria2").innerHTML =
			"<div class=\"superbox-list\">"+
			"No hay videos que mostrar"+
			"</div>";
		    }
		}
		break;
	    default:
		console.log("no es imagen o video.");
		break;
	};
    } 
}
 //
 function fullimage_2(id_obra) {
    console.log("entra a fullimage2");
    console.log("id de la obra: " + id_obra);
    var get = getGET();
    //var url = get.path;
    var obra = get.idObra;
    var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
    db.transaction(function(tx) {
	tx.executeSql('select * from MultimediaObras where cast(idObra as int) = ?',[obra],  function(tx, rs) {
	    var temp = 1;
		for (var i = 0; i < rs.rows.length; i++) {
		    var p = rs.rows.item(i);
		    var indice = p.idMultimedia;
		    var desc = p.Descripcion;
			    document.getElementById("links").innerHTML =
			    "<center>" +
				"<a href=\"http://"+ruta+"/serve?blob-key=" + p.Path + "\" class=\"disabled\" title=\"\" data-gallery=\"\" data-unique-id=\"" + p.idMultimedia + "\">" +
					"<img src=\"http://"+ruta+"/serve?blob-key=" + p.Path + "\" /> " +
				"</a>" +
			    "</center>";
		    cargar2();   
		}
        }, errorCB);
               
    }, errorCB, successConsulta());
    cargar2();
}
function convertir(fecha){
         //alert("entra convetir");
     var fecha1 = fecha;
     
     var split1= fecha1.split("/");//cambiar si es necesario a  (/)
     
     var dia = split1[0];
     var mes = split1[1];
     var aniohora = split1[2];
        var split2=aniohora.split(" ");
	var fechaUni = aniohora+"-"+mes+"-"+dia;
	//alert("fecha " + fechaUni);
	if (split2[0]) {
		//c
		var anio = split2[0];
        
		fechaUni = anio+"-"+mes+"-"+dia;
		//alert(fechaUni);
	}	
     return fechaUni;  
 }
function video(idproyecto, idobra, fecha1, fecha2) {
    //code
    var imagen;
    console.log("seleccion de la fecha01: " +fecha1);
    console.log("seleccion de la fecha02: " +fecha2);
    fecha_01 = moment(fecha01);
    fecha_02 = moment(fecha02);
    //var MultimediaItems = new Array();
    var contador_2 = 0;
    //1-dot-cao-iuyet-pruebas.appspot.com
    //var ROOT = 'https://' + '1-dot-cao-iuyet-pruebas.appspot.com' + '/_ah/api';//desarrollo
    //var ROOT = 'https://' + '1-dot-iuyet-csgit-cao.appspot.com' + '/_ah/api';//desarrollo
    //var ROOT = 'https://' + '1-dot-superobra-adquem.appspot.com' + '/_ah/api';//adquem
    //var ROOT = 'https://' + 'supervision.adquem.com' + '/_ah/api';
    gapi.client.load('communicationchannel', 'v1', function () {
	gapi.client.communicationchannel.listMultimedia({"tipoReferencia": "1","idReferencia":String(idobra),"tipoArchivo":"2",}).execute(function (resp) { //alert("2");
            if (resp.code){
		//alert("Error :::" + resp.message);
		alert("No hay videos :::");
	    }else{
		MultimediaItems = resp.items;
		console.log("Multimedia de video: " + MultimediaItems.length);
		videos(MultimediaItems);
	    }
        });
    }, ROOT);
    //console.log("fecha del video: " + MultimediaItems.fecha);
}
function videos(MultimediaItems) {
    //code
    var get = getGET();
    //obtiene el numero de la obra
    var obra = get.idObra;
    var proyecto = get.idProyecto;
    //code
    var contador_2 = 0;
    for (var i = 0; i< MultimediaItems.length; i++) {
		    //code
	if (MultimediaItems[i].formato == 4) {
			//code
	    imagen = "iconos_png/vid.png";
	    if (moment(fecha_01).isBefore(convertir(MultimediaItems[i].fecha)) && moment(fecha_02).isAfter(convertir(MultimediaItems[i].fecha))){
			    console.log("fecha de Multimedia: " + convertir(MultimediaItems[i].fecha));
			    contador_2++;
		if (1 == contador_2) {
			        document.getElementById("galeria2").innerHTML =
				"<div class=\"superbox-list\" id=\"img\">"+
				"<a href=\"fullvideo.html?idObra="+obra+"&&path="+MultimediaItems[i].path+"&&idProyecto="+proyecto+"\"><img src= \"iconos_png/vid.png\" width=\"100\" height=\"100\"></a>" +
				"<span>"+MultimediaItems[i].descripcion+"</span>" +
				"<div>";
				    /*"<video width=\"320\" height=\"240\" controls>" +
				    "<source src=\"https://1-dot-iuyet-csgit-cao.appspot.com/serve?blob-key="+MultimediaItems[i].path+"\" webkit-playsinline preload=\"auto\" type=\"video/mp4\">"+ // ,mime type=\"video/mp4\" y video/mpeg, (H.264/AAC), video/3gp ///webkit-playsinline preload=\"auto\" codecs= \"H.264/AAC\"
				    //"<source src=\"https://www.youtube.com/watch?v=Tj75Arhq5ho\" type=\"video/ogg\">"+
				    //"<source src=\"https://www.youtube.com/watch?v=Tj75Arhq5ho\" type=\"video/mp4\">"+
				    "</video>" +
				    "<span>"+MultimediaItems[i].descripcion+"</span>";*/
		}else{
				document.getElementById("galeria2").innerHTML +=
				"<div class=\"superbox-list\" id=\"img\">"+
				"<a href=\"fullvideo.html?idObra="+obra+"&&path="+MultimediaItems[i].path+"&&idProyecto="+proyecto+"\"><img src= \"iconos_png/vid.png\" width=\"100\" height=\"100\"></a>" +
				"<span>"+MultimediaItems[i].descripcion+"</span>" +
				//"<button onclick=\""+vervideo(1)+"\">Ver</button>"+
				"<div>";
				    /*"<video width=\"320\" height=\"240\" controls>" +
				    "<source src=\"https://1-dot-iuyet-csgit-cao.appspot.com/serve?blob-key="+MultimediaItems[i].path+"\" webkit-playsinline preload=\"auto\" type=\"video/mp4\">"+ // ,mime type=\"video/mp4\" y video/mpeg, (H.264/AAC), video/3gp ///webkit-playsinline preload=\"auto\"
				    //"<source src=\"https://www.youtube.com/watch?v=Tj75Arhq5ho\" type=\"video/ogg\">"+
				    //"<source src=\"https://www.youtube.com/watch?v=Tj75Arhq5ho\" type=\"video/mp4\">"+
				    "</video>" +
				    "<span>"+MultimediaItems[i].descripcion+"</span>";*/
		}
	    }else{
			    console.log("no hay video");
			    document.getElementById("galeria2").innerHTML =
			    "<div class=\"superbox-list\">"+
			        "No hay Videos que Mostrar"+
			    "</div>";
	    }
	}
    }
}