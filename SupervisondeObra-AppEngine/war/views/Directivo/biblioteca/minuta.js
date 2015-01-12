function init_3(){
    
    get_mapa();
    get_proyecto();
    var get = getGET();
    var proyecto = get.idProyecto;
    var obra = get.idObra;
    var fechas_2;
    document.getElementById("returnObra").innerHTML =
    "<a href=\"detalle_obras_reporte.html?idProyecto="+proyecto+"&&idObra="+obra+"\"><i class=\"icon-custom-left\"></i></a>"
    console.log("Maquinaria de la obra: "+ obra);
    gapi.client.load('communicationchannel', 'v1', function () {
	gapi.client.communicationchannel.listMultimedia({"tipoReferencia": "7","idReferencia":String(obra),"tipoArchivo":"0",}).execute(function (resp) { //alert("2");
            if (resp.code){
		//alert("Error :::" + resp.message);
		alert("No hay minutas :::");
	    }else{
		MultimediaItems = resp.items;
		console.log("Minutas: " + MultimediaItems.length);
                ver(MultimediaItems);
	    }
        });
    }, ROOT);
    
    /*directorio_obra();
    get_proyecto();
    var get = getGET();
    var obra = get.idObra;
    var fechas_2;
    document.getElementById("returnObra").innerHTML =
    "<a href=\"detalle_obras_reporte.html?idObra="+obra+"\"><i class=\"icon-custom-left\"></i></a>"
    console.log("Maquinaria de la obra: "+ obra);
    var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
    db.transaction(function(tx) {
	tx.executeSql('select * from MultimediaMinuta where cast(idObra as int) = ?',[obra],  function(tx, rs) {
            var minutas = rs.rows.length;
	    console.log("Minutas de la Obra: " + minutas);
            if (minutas == 0) {
                //code
                document.getElementById("minuta").innerHTML +=
                    "<div align = \"center\">"+
			"<h3><strong>No hay Minutas</strong></h3>"+
		    "</div>";
            }else{
                var j = 1;
                for (var i = 0; i < rs.rows.length; i++) {
                    var p = rs.rows.item(i);
                    fechas_2 = convertir(p.Fecha);
                    console.log("fecha de minuta: " + fechas_2);
                    document.getElementById("minuta").innerHTML +=
                        "<div class=\"superbox-list\" id=\"img\">"+
			    "<img src=\"http://1-dot-iuyet-csgit-cao.appspot.com/serve?blob-key="+p.Path+"\" "+
			    "data-img=\"http://1-dot-iuyet-csgit-cao.appspot.com/serve?blob-key="+p.Path+"\" class=\"superbox-img\" style= \"width: 170px; height: 180px;\">"+
			    "<span> Fecha: " +fechas_2+"</span>"+
			"</div>";
		    j++;
                }
            }
        }, errorCB);
               
    }, errorCB, successConsulta());
    */
}
function successConsulta() {
    console.log("Minutas cargadas.....");
}

function errorCB(err) {
    console.log("Error processing SQL: "+err.code + " Message: "+err.message);
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
 function ver(MultimediaItems) {
    //code
    console.log("minutas: " + MultimediaItems.length);
        if (MultimediaItems.length == 0) {
                //code
                document.getElementById("minuta").innerHTML +=
                    "<div align = \"center\">"+
			"<h3><strong>No hay Minutas</strong></h3>"+
		    "</div>";
        }else{
                //var j = 1;
            for (var i = 0; i < MultimediaItems.length; i++) {
                fecha = MultimediaItems[i].fecha;
                fechas_2 = convertir(fecha);
                console.log("fecha de minuta: " + fechas_2);
                document.getElementById("minuta").innerHTML +=
                    "<div class=\"superbox-list\" id=\"img\">"+
		        "<img src=\"http://1-dot-iuyet-csgit-cao.appspot.com/serve?blob-key="+MultimediaItems[i].path+"\" "+
                        "data-img=\"http://1-dot-iuyet-csgit-cao.appspot.com/serve?blob-key="+MultimediaItems[i].path+"\" class=\"superbox-img\" style= \"width: 170px; height: 180px;\">"+
                        "<span> Fecha: " +fechas_2+"</span>"+
                    "</div>";
		    //j++;
            }
        }
 }