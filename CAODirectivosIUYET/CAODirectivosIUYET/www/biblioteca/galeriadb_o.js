function init(){
    directorio_obra();
    get_proyecto();
    var get = getGET();
        console.log("IdObra en multimedia obra:::::"+get.idObra);
    var idObra = get.idObra;
    
    document.getElementById("returnObra").innerHTML =
    //"<a href=\"detalle_obras_reporte.html?idObra="+idObra+"\"><i class=\"icon-custom-left\"></i></a>";
    "<a href=\"detalle_obras.html?idObra="+idObra+"\"><i class=\"icon-custom-left\"></i></a>";
    
    var ROOT = 'https://' + '1-dot-cao-iuyet-pruebas.appspot.com' + '/_ah/api';
    //var ROOT = 'https://' + '1-dot-iuyet-csgit-cao.appspot.com' + '/_ah/api';
            //gapi.client.load('multimediaendpoint', 'v1', function () {
	    gapi.client.load('communicationchannel', 'v1', function () {
                //gapi.client.multimediaendpoint.listMultimedia({"tipoReferencia":"0","idReferencia":"0","tipoArchivo":"0",}).execute(function (resp) { //alert("2");
		gapi.client.communicationchannel.listMultimedia({"tipoReferencia":"0","idReferencia":"0","tipoArchivo":"0",}).execute(function (resp) { //alert("2");
                    proyectos = resp; //alert("3");
                    if (resp.code) alert("Error :::" + resp.message);
                    MultimediaItems = resp.items;
		    console.log("Multimedias init galeriadb: " + MultimediaItems.length);
                });
            }, ROOT);
	    getIdDirectivo();
    //funcion de la galeria
     //fullimage_2(idObra);
}
function cargar(x) {
    var get = getGET();
    console.log("IdObra en multimedia obra:::::"+get.idObra);
    var idObra = get.idObra;
    
     document.getElementById("returnObra").innerHTML =
    //"<a href=\"detalle_obras_reporte.html?idObra="+idObra+"\"><i class=\"icon-custom-left\"></i></a>"
    "<a href=\"detalle_obras.html?idObra="+idObra+"\"><i class=\"icon-custom-left\"></i></a>";
          if (MultimediaItems && x >= MultimediaItems.length) {
                      //code
                      console.log("termino de insertar" );
           }
  
           else if (MultimediaItems && MultimediaItems.length > 0) {
                      console.log("Cargando datos espere un momento ::: 4 X=" + x + " Multimedia: "+ MultimediaItems.length);       
            idMultimedia =MultimediaItems[x].idMultimedia;
            M_idReferencia=MultimediaItems[x].idReferencia;
            M_TipoEntidad=MultimediaItems[x].tipoReferencia;
            M_Path=MultimediaItems[x].path;
            M_TipoArchivo=MultimediaItems[x].tipoArchivo;
            M_Fecha="17/09/2014";
            M_Descripcion="Descripcion";
	    
	    var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
	    db.transaction(function(tx) {
	    
	    if (x==0) {
		//code
		 //Multimedia
	      tx.executeSql('DROP TABLE IF EXISTS MultimediaProyectos');
	      tx.executeSql('CREATE TABLE IF NOT EXISTS MultimediaProyectos (idMultimedia unique, idProyecto, TipoArchivo, Path, Fecha, Descripcion)');
	         //Multimedia
	      tx.executeSql('DROP TABLE IF EXISTS MultimediaObras');
	      tx.executeSql('CREATE TABLE IF NOT EXISTS MultimediaObras (idMultimedia unique, idObra, TipoArchivo, Path, Fecha, Descripcion)');
    
	    }
	   
                   if (M_TipoEntidad == 0 || M_TipoEntidad == "0") {
                       console.log("Insertando multimedia Proyectos: " + idMultimedia + " idProyecto : "+M_idReferencia); 
                                tx.executeSql('INSERT INTO MultimediaProyectos(idMultimedia, idProyecto, TipoArchivo, Path, Fecha, Descripcion)' +
                               ' VALUES (?, ?, ?, ?, ?, ?)',
                                [idMultimedia, M_idReferencia, M_TipoArchivo, M_Path, M_Fecha, M_Descripcion]);
                   }
                   if (M_TipoEntidad == 1 || M_TipoEntidad == "1") {
                                console.log("Insertando multimedia Obras: " + idMultimedia + " idObra : "+M_idReferencia); 
                                tx.executeSql('INSERT INTO MultimediaObras(idMultimedia, idObra, TipoArchivo, Path, Fecha, Descripcion)' +
                               ' VALUES (?, ?, ?, ?, ?, ?)',
                                [idMultimedia, M_idReferencia, M_TipoArchivo, M_Path, M_Fecha, M_Descripcion]);
                   }
            }, errorCB, function (){
                console.log("Se inserto correctamente el multimedia: " + idMultimedia);
                    x++;
                    cargar(x);
                }); 
           
           } else {
                      console.log("No hay avances multimedia :::::::::::::::::::::::");
           }
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
		      //"<a href=\"http://1-dot-cao-iuyet-server.appspot.com/serve?blob-key="+p.Path+"\" class=\"zoom\"  title=\""+desc+"\" data-gallery=\"\" data-unique-id=\""+indice+"\" id=\"disabled\">"+
		      "<a href=\"http://1-dot-iuyet-csgit-cao.appspot.com/serve?blob-key="+p.Path+"\" class=\"zoom\"  title=\""+desc+"\" data-gallery=\"\" data-unique-id=\""+indice+"\" id=\"disabled\">"+
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
    //obtiene el numero de la obra
    var obra = get.idObra;
    //abre la base de datos
    var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
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
    console.log("diferencia entre los dias: " + dif);
    if (dif <= -30 || dif >= 30) {
	//code
	alert("Se pasa de los 30 dias");
    }else{
	console.log("se muestran las imagenes");
	
	db.transaction(function(tx) {
	//SELECT formato, Descripcion, Path, Fecha FROM MultimediaObras WHERE TipoArchivo = "0" and idObra = "2"
	//SELECT Descripcion, Path, Fecha FROM MultimediaObras WHERE idObra = "1"
	//tx.executeSql('select * from MultimediaObras where cast(idObra as int) = ?',[obra],  function(tx, rs) {
	//tx.executeSql('SELECT formato, Descripcion, Path, Fecha FROM MultimediaObras WHERE formato = "0" and cast(idObra as int) = ?',[obra],  function(tx, rs) {
	tx.executeSql('SELECT Descripcion, Path, Fecha FROM MultimediaObras WHERE cast(idObra as int) = ? and Formato == "0"',[obra],  function(tx, rs) {
	    var imagenes = rs.rows.length;
	    console.log("Imagenes: " + imagenes);
	    var contador = 0;
	    if (imagenes == 0) {
		//code
		console.log("no hay imagenes");
		    document.getElementById("galeria").innerHTML =
		    "<div class=\"superbox-list\">"+
		    "No hay imagenes que mostrar"+
		    "</div>";
	    }else{
		console.log(" hay imagenes");
		for (var i = 0; i < rs.rows.length; i++) {
		    var p = rs.rows.item(i);
		    console.log("idobra: "+p.idObra + " fecha: "+p.Fecha );
		    var fechabd = p.Fecha;
		    var fechabd_2 =convertir(fechabd);
		    console.log("idobra: "+p.idObra + " fechabd_2: "+fechabd_2 );
		//console.log(moment(fecha01).isAfter(fechabd));//despues
		//console.log(moment(fecha02).isBefore(fechabd));//antes
		//if (moment(fecha01).isBefore(fechabd) && moment(fecha02).isAfter(fechabd)) {
		//if (moment(fecha01).isBefore(fechabd_2) && moment(fecha02).isAfter(fechabd_2) || moment(fecha02).isBefore(fechabd_2)) {
		    if (moment(fecha01).isBefore(fechabd_2) && moment(fecha02).isAfter(fechabd_2)) { // la que funciona bien
		        //code
		        contador ++;
		        var p = rs.rows.item(i);
		        fechabd = p.Fecha;
		        console.log("path: " + p.Path);
		        console.log("fechas: " + fechabd);
		        if (1 == contador) {
			//code
			    document.getElementById("galeria").innerHTML =
			    "<a href=\"fullimage.html?path="+p.Path+"&&idObra="+obra+"\">"+
			        "<div class=\"superbox-list\" id=\"img\">"+
				//"<img src=\"http://1-dot-cao-iuyet-server.appspot.com/serve?blob-key="+p.Path+"\" "+
				//"<img src=\"http://1-dot-iuyet-csgit-cao.appspot.com/serve?blob-key="+p.Path+"\" "+
				"<img src=\"http://1-dot-cao-iuyet-pruebas.appspot.com/serve?blob-key="+p.Path+"\" "+
				//"data-img=\"http://1-dot-cao-iuyet-server.appspot.com/serve?blob-key="+p.Path+"\" class=\"superbox-img\" style= \"width: 170px; height: 180px;\">"+
				//"data-img=\"http://1-dot-iuyet-csgit-cao.appspot.com/serve?blob-key="+p.Path+"\" class=\"superbox-img\" style= \"width: 170px; height: 180px;\">"+
				"data-img=\"http://1-dot-cao-iuyet-pruebas.appspot.com/serve?blob-key="+p.Path+"\" class=\"superbox-img\" style= \"width: 170px; height: 180px;\">"+
				"<span>"+p.Descripcion+"</span>"+
				"</div>"+
			    "</a>";
			}else{
			    document.getElementById("galeria").innerHTML +=
			    "<a href=\"fullimage.html?path="+p.Path+"&&idObra="+obra+"\">"+
			    "<div class=\"superbox-list\" id=\"img\">"+
			        //"<img src=\""+p.Path+"\" "+
				    //"<img src=\"http://1-dot-cao-iuyet-server.appspot.com/serve?blob-key="+p.Path+"\" "+
				    //"<img src=\"http://1-dot-iuyet-csgit-cao.appspot.com/serve?blob-key="+p.Path+"\" "+
				    "<img src=\"http://1-dot-cao-iuyet-pruebas.appspot.com/serve?blob-key="+p.Path+"\" "+
				//"data-img=\""+p.Path+"\" class=\"superbox-img\" style= \"width: 170px; height: 180px;\">"+
				    //"data-img=\"http://1-dot-cao-iuyet-server.appspot.com/serve?blob-key="+p.Path+"\" class=\"superbox-img\" style= \"width: 170px; height: 180px;\">"+
				    //"data-img=\"http://1-dot-iuyet-csgit-cao.appspot.com/serve?blob-key="+p.Path+"\" class=\"superbox-img\" style= \"width: 170px; height: 180px;\">"+
				    "data-img=\"http://1-dot-cao-iuyet-pruebas.appspot.com/serve?blob-key="+p.Path+"\" class=\"superbox-img\" style= \"width: 170px; height: 180px;\">"+
				    "<span>"+p.Descripcion+"</span>"+
				"</div>"+
			    "</a>";
			} 
		    }else{
			if (i <= 0) {// esta linea queda bien
			//code
			console.log("no hay imagenes");
			    document.getElementById("galeria").innerHTML =
			"<div class=\"superbox-list\">"+
			"No hay imagenes que mostrar"+
			"</div>";
		    }
		    //break;
		}
	    }
	    }
        }, errorCB); 
    }, errorCB, successConsulta());
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
				/*"<a href=\"http://1-dot-iuyet-csgit-cao.appspot.com/serve?blob-key=" + p.Path + "\" class=\"disabled\" title=\"\" data-gallery=\"\" data-unique-id=\"" + p.idMultimedia + "\">" +
					"<img src=\"http://1-dot-iuyet-csgit-cao.appspot.com/serve?blob-key=" + p.Path + "\" /> " +
					*/
					"<a href=\" http://1-dot-cao-iuyet-pruebas.appspot.com/serve?blob-key=" + p.Path + "\" class=\"disabled\" title=\"\" data-gallery=\"\" data-unique-id=\"" + p.idMultimedia + "\">" +
					"<img src=\"http://1-dot-cao-iuyet-pruebas.appspot.com/serve?blob-key=" + p.Path + "\" /> " +
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
 //funcion que obtiene el id de la obra
 function pagina(obra){
    document.getElementById("returnObra_2").innerHTML =
    "<a href=\"galeria_3.html?idObra="+idObra+"\"></a>"
    
    return obra;
}
 