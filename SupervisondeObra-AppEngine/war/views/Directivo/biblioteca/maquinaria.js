
function init_2() {
    //code
    directorio_obra();
    get_proyecto();
    maquinaria();
    get_mapa();
    //personal();
    var get = getGET();
    var proyecto = get.idProyecto;
    var obra = get.idObra;
    document.getElementById("returnObra").innerHTML =
    "<a href=\"detalle_obras_reporte.html?idProyecto="+proyecto+"&&idObra="+obra+"\"><i class=\"icon-custom-left\"></i></a>";
    //"<a href=\"detalle_obras.html?idObra="+obra+"\"><i class=\"icon-custom-left\"></i></a>";
    console.log("Maquinaria de la obra: "+ obra);
    //
    var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
    db.transaction(function(tx) {
	tx.executeSql('select * from ReporteDiario where cast(idObra as int) = ?',[obra],  function(tx, rs) {
	    var rp = rs.rows.length;
	    console.log("Reportes diarios: " + rp);
	    if (rp == 0) {
		//code
		document.getElementById("reporte_diario").innerHTML =
		    "<div align = \"center\">"+
			"<h3><strong>No hay Reportes Diarios</strong></h3>"+
		    "</div>";
		document.getElementById("personal").innerHTML = 
                     "<strong>No hay Personal en el Reporte Diario</strong>";
	    }else{
		var j = 1;
		for (var i = 0; i < rs.rows.length; i++) {
		    var p = rs.rows.item(i);
		    document.getElementById("id_reporte").innerHTML +=
		    "<option value=\""+p.idReporte+"\">"+ p.fecha +"</option>";
		    j++;
		}
	    }
        }, errorCB);
               
    }, errorCB, successConsulta());
}
function successConsulta() {
    console.log("Multimedia cargadas.....");
}

function errorCB(err) {
    console.log("Error processing SQL: "+err.code + " Message: "+err.message);
}
function maquinaria() {
    //code
    gapi.client.load('communicationchannel', 'v1', function () {
	gapi.client.communicationchannel.listMultimedia({"tipoReferencia": "2","idReferencia":"0","tipoArchivo":"0",}).execute(function (resp) { //alert("2");
            if (resp.code){
		//alert("Error :::" + resp.message);
		alert("No hay minutas :::");
	    }else{
		MultimediaItems = resp.items;
		console.log("Multimedia de catalogo: " + MultimediaItems.length);
		//multimedia_m(MultimediaItems);
		valor(MultimediaItems);
	    }
        });
    }, ROOT);
}
/*
function multimedia_m(MultimediaItems) {
    //code
    var reporte = $( "#id_reporte" ).val();
    var reportes_diarios = 0;
    console.log("valor selecionado: " + reporte);
    var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
    db.transaction(function(tx) {
	//WHERE r.idReporte == "1" and rm.idReporte == "1" and rm.idCatMaquinaria == mm.idMaquinaria and m.idMaquinaria == rm.idCatMaquinaria
	//tx.executeSql('SELECT r.idReporte as ReporteDiario, rm.idCatMaquinaria as Maquinaria, rm.cantidad as Cantidad, m.nombre as Nombre,mm.Path as path '+
	//	      'FROM ReporteDiario as r, RepMaquinaria as rm, MultimediaMaquinaria as mm, Maquinaria as m '+
	//	      'WHERE cast(r.idReporte as int) == ? and cast(rm.idReporte as int) == ? and rm.idCatMaquinaria == mm.idMaquinaria and m.idMaquinaria == rm.idCatMaquinaria',[reporte, reporte],  function(tx, rs) {
	tx.executeSql('SELECT r.idReporte as ReporteDiario, rm.idCatMaquinaria as Maquinaria, rm.cantidad as Cantidad, m.nombre as Nombre '+
		      'FROM ReporteDiario as r, RepMaquinaria as rm, Maquinaria as m '+
		      'WHERE cast(r.idReporte as int) == ? and cast(rm.idReporte as int) == ? and m.idMaquinaria == rm.idCatMaquinaria',[reporte, reporte],  function(tx, rs) {
			reportes_diarios = rs.rows.length;
	    console.log("Reporte de maquinaria: " + reportes_diarios);
	    var contador = 0;
	    if (reportes_diarios == 0) {
		console.log("no hay imagenes");
		document.getElementById("reporte_diario").innerHTML =
		    "<div align = \"center\">"+
			"<h3><strong>No hay Maquinaria en el Reporte Diario</strong></h3>"+
		    "</div>";
	    }else{
	        for (var i = 0; i < rs.rows.length; i++) {
		    contador++;
		    var p = rs.rows.item(i);
		    //fechas_2 = convertir(p.fecha);
		    //document.getElementById("reporte_diario").innerHTML = "fecha" + fechas_2;
		    if (contador == 1 && MultimediaItems[i] == Maquinaria) {
		        //console.log("reporte de maquinaria: " + p );
		        console.log("id Reporte: " + p.ReporteDiario+ " Cat_Maquinaria "+p.Maquinaria + " cantidad: " + p.Cantidd +" Path" + p.path);
		        document.getElementById("reporte_diario").innerHTML =
			    "<div class=\"superbox-list\" id=\"img\">"+
			        "<img src=\"http://1-dot-iuyet-csgit-cao.appspot.com/serve?blob-key="+MultimediaItems[i].path+"\" "+
				"data-img=\"http://1-dot-iuyet-csgit-cao.appspot.com/serve?blob-key="+MultimediaItems[i].path+"\" class=\"superbox-img\" style= \"width: 170px; height: 180px;\">"+
				"<span> Cantidad: "+p.Cantidad+"</span><br>"+
				"<span> Nombre: " +p.Nombre+"</span>"+
			    "</div>";
		    }else{
			console.log("id Reporte: " + p.ReporteDiario+ " Cat_Maquinaria "+p.Maquinaria + " cantidad: " + p.Cantidd +" Path" + p.path);
			document.getElementById("reporte_diario").innerHTML +=
			"<div class=\"superbox-list\" id=\"img\">"+
				"<img src=\"http://1-dot-iuyet-csgit-cao.appspot.com/serve?blob-key="+MultimediaItems[i].path+"\" "+
				"data-img=\"http://1-dot-iuyet-csgit-cao.appspot.com/serve?blob-key="+MultimediaItems[i].path+"\" class=\"superbox-img\" style= \"width: 170px; height: 180px;\">"+
				"<span> Cantidad: "+p.Cantidad+"</span><br>"+
				"<span> Nombre: " +p.Nombre+"</span>"+
			"</div>";
		    }
		}
	    }
        }, errorCB);
               
    }, errorCB, successConsulta());
    //personal(reporte);
    personas_2(reporte);
}
*/
function valor(MultimediaItems) {
    //code
    var reporte = $( "#id_reporte" ).val();
    var reportes_diarios = 0;
    console.log("valor selecionado: " + reporte);
    var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
    db.transaction(function(tx) {
	//WHERE r.idReporte == "1" and rm.idReporte == "1" and rm.idCatMaquinaria == mm.idMaquinaria and m.idMaquinaria == rm.idCatMaquinaria
	/*tx.executeSql('SELECT r.idReporte as ReporteDiario, rm.idCatMaquinaria as Maquinaria, rm.cantidad as Cantidad, m.nombre as Nombre,mm.Path as path '+
		      'FROM ReporteDiario as r, RepMaquinaria as rm, MultimediaMaquinaria as mm, Maquinaria as m '+
		      'WHERE cast(r.idReporte as int) == ? and cast(rm.idReporte as int) == ? and rm.idCatMaquinaria == mm.idMaquinaria and m.idMaquinaria == rm.idCatMaquinaria',[reporte, reporte],  function(tx, rs) {*/
	tx.executeSql('SELECT r.idReporte as ReporteDiario, rm.idCatMaquinaria as Maquinaria, rm.cantidad as Cantidad, m.nombre as Nombre '+
		      'FROM ReporteDiario as r, RepMaquinaria as rm, Maquinaria as m '+
		      'WHERE cast(r.idReporte as int) == ? and cast(rm.idReporte as int) == ? and m.idMaquinaria == rm.idCatMaquinaria',[reporte, reporte],  function(tx, rs) {
			reportes_diarios = rs.rows.length;
	    console.log("Reporte de maquinaria: " + reportes_diarios);
	    var contador = 0, contador_1 = 0;
	    if (reportes_diarios == 0) {
		console.log("no hay imagenes");
		document.getElementById("reporte_diario").innerHTML =
		    "<div align = \"center\">"+
			"<h3><strong>No hay Maquinaria en el Reporte Diario</strong></h3>"+
		    "</div>";
	    }else{
		//
		for (var i = 0; i<rs.rows.length; i++) {
		    //code
		    var p = rs.rows.item(i);
		    for (var j = 0; j < MultimediaItems.length; j++) {
			//code
			contador_1++;
			var maquinaria = parseInt(p.Maquinaria)
			if(MultimediaItems[j].idReferencia == maquinaria){
			    console.log("del if referencia multimedia: " + MultimediaItems[j].idReferencia);
			    console.log("del if maquinaria: "+p.Maquinaria);
			    if (contador_1 == 1) {
				console.log("path de multimedia: " + MultimediaItems[j].path);
				document.getElementById("reporte_diario").innerHTML =
				    "<div class=\"superbox-list\" id=\"img\">"+
					"<img src=\"http://1-dot-iuyet-csgit-cao.appspot.com/serve?blob-key="+MultimediaItems[j].path+"\" "+
					    "data-img=\"http://1-dot-iuyet-csgit-cao.appspot.com/serve?blob-key="+MultimediaItems[j].path+"\" class=\"superbox-img\" style= \"width: 170px; height: 180px;\">"+
					"<span> Cantidad: "+p.Cantidad+"</span><br>"+
					"<span> Nombre: " +p.Nombre+"</span>"+
				    "</div>";
			    }else{
				document.getElementById("reporte_diario").innerHTML +=
				    "<div class=\"superbox-list\" id=\"img\">"+
				    	"<img src=\"http://1-dot-iuyet-csgit-cao.appspot.com/serve?blob-key="+MultimediaItems[j].path+"\" "+
					    "data-img=\"http://1-dot-iuyet-csgit-cao.appspot.com/serve?blob-key="+MultimediaItems[j].path+"\" class=\"superbox-img\" style= \"width: 170px; height: 180px;\">"+
					"<span> Cantidad: "+p.Cantidad+"</span><br>"+
					"<span> Nombre: " +p.Nombre+"</span>"+
				    "</div>";
			    }
			}
			
		    }
		}
	    }
        }, errorCB);
               
    }, errorCB, successConsulta());
    //personal(reporte);
    personas_2(reporte);
}
function personas_2(reporte) {
    //code
    var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
    db.transaction(function(tx) {
	/*
	 *SELECT r.idReporte, rcp.Cantidad, cp.Tipo_Persona FROM ReporteDiario as r, CatPersonal as cp, RepCatPersonal as rcp WHERE R.idReporte == "1" and rcp.idCatPersonal == cp.idCatPersonal
	*/
	tx.executeSql('SELECT * FROM RepCatPersonal where cast(idReporte as int) == ?',[reporte],  function(tx, rs) {
	    var personas = rs.rows.length;
	    console.log("Personas 2: " + personas);
	    if (personas == 0) {
		//code
		console.log("no hay personal");
		document.getElementById("personal").innerHTML = 
                     "<strong>No hay Personal en el Reporte Diario</strong>";
	    }else{
		console.log("imprime el personal del reporte diario");
		personal(reporte);
	    }
        }, errorCB);
               
    }, errorCB, successConsulta());
}
function personal(reporte) {
    //code
    console.log("Reporte del personal:" + reporte);
    //var reporte = $( "#id_reporte" ).val();
    var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
    db.transaction(function(tx) {
	/*
	 *SELECT r.idReporte, rcp.Cantidad, cp.Tipo_Persona FROM ReporteDiario as r, CatPersonal as cp, RepCatPersonal as rcp WHERE R.idReporte == "1" and rcp.idCatPersonal == cp.idCatPersonal
	*/
	tx.executeSql('SELECT r.idReporte as Reporte, rcp.Cantidad as Cantidad, cp.Tipo_Persona as Tipo_Persona FROM ReporteDiario as r, CatPersonal as cp, RepCatPersonal as rcp WHERE cast(R.idReporte as int) = ? and rcp.idCatPersonal = cp.idCatPersonal',[reporte],  function(tx, rs) {
	    console.log("Catalogo de personal: " + rs.rows.length);
	    var j = 1;
	    //var fechas_2 = new Array();
	    for (var i = 0; i < rs.rows.length; i++) {
		var p = rs.rows.item(i);
		console.log("Reporte: " +p.Reporte+ " Cantidad: " +p.Cantidad+ " Tipo de persona: "+p.Tipo_Persona);
		       document.getElementById("personal").innerHTML =
			    "<table class=\"table no-more-tables\">" +
				"<tr>" +
				    "<td><strong> Reporte: </strong>"+p.Reporte+"</td>" +
				    "<td><strong> Cantidad: </strong> "+p.Cantidad + "</td>" +
			        "</tr>" +
				"<tr>" +
				    "<td><strong> Tipo de Persona: </strong>"+ p.Tipo_Persona +"</td>" +
				"</tr>" +
			    "</table>";
	    }
        }, errorCB);
               
    }, errorCB, successConsulta());
}
//fecha
function convertir(fecha){
         //alert("entra convetir");
     var fecha1 = fecha;
     
     var split1= fecha1.split("/");//cambiar si es necesario a  (/)
     
     var dia = split1[0];
     var mes = split1[1];
     var aniohora = split1[2];
        var split2=aniohora.split(" ");
	var fechaUni = String(aniohora+"-"+mes+"-"+dia);
	//alert("fecha " + fechaUni);
	if (split2[0]) {
		//c
		var anio = split2[0];
        
		fechaUni = String(anio+"-"+mes+"-"+dia);
		//alert(fechaUni);
	}	
    return fechaUni;  
 }