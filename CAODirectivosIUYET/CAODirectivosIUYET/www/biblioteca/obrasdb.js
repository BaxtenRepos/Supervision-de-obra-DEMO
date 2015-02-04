    
    var ROOT = 'https://' + '1-dot-superobra-adquem.appspot.com' + '/_ah/api';//adquem
    //var ROOT = 'https://' + '1-dot-iuyet-csgit-cao.appspot.com' + '/_ah/api';//desarrollo
    //var ROOT = 'https://' + '1-dot-cao-iuyet-pruebas.appspot.com' + '/_ah/api';//pruebas
    var ruta = '1-dot-superobra-adquem.appspot.com';
    
    function successConsulta() {
	//console.log("Obras cargadas.....");
    }
    
    function errorCB(err) {
	console.log("Error processing SQL: "+err.code + " Message: "+err.message);
    }
       
    function nombreObra(idObra){
	getIdDirectivo();
	//directorio_obra();
	//proyecto2(pagina);
	var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
	//console.log("mostrando datos de la obra id: " +idObra);
	
	db.transaction(function(tx) {
		  tx.executeSql('select * from Obras where idObra=?',
				[idObra],  function(tx, rs) {
				    
				console.log("Obra: " + rs.rows.length);
			  var p = rs.rows.item(0);
			  document.getElementById("nombre_obra").innerHTML =
			"<h3><strong>"+p.Nombre+"</strong></h3> ";
			// +
			//"<h4><strong>"+p.Descripcion+"</strong></h4>"
			     
			    
		  }, errorCB);
		   
	      }, errorCB, successConsulta());
    }
    
    function ubicacionObra(idObra) {
	
	var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
	//console.log("mostrando datos de la obra id: " +idObra);
	
	db.transaction(function(tx) {
		  tx.executeSql('select * from Obras where idObra=?',
				[idObra],  function(tx, rs) {
				    
				console.log("Obra: " + rs.rows.length);
			  var p = rs.rows.item(0);
			  document.getElementById("ubicacion").innerHTML =
			    "<table class=\"table no-more-tables\">" +
				
				    "<tr>" +
					"<td><strong> Entidad Federativa: </strong>"+p.EntidadFederativa+"</td>" +
					"<td><strong> Direcci&oacute;n: </strong> "+p.Direccion + "</td>" +
				    "</tr>" +
				
			    "</table>";
			     
		  }, errorCB);
		   
	      }, errorCB, successConsulta());
    }
    
    function contratoObra(idObra) {
	
	var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
	//console.log("mostrando datos de la obra id: " +idObra);
	
	db.transaction(function(tx) {
		  tx.executeSql('select * from Obras where idObra=?',
				[idObra],  function(tx, rs) {
				    
				console.log("Obra: " + rs.rows.length);
			  var p = rs.rows.item(0);
			  // variables para que tome el formato de moneda
			  var importecontratosiniva = accounting.formatMoney(p.ImporteContratoSinIVA);
			  var partidapresupuestal = accounting.formatMoney(p.PartidaPresupuestal);
			  var importefiscal = accounting.formatMoney(p.importeFiscal1SinIVA);
			  //var nombreejercicio = accounting.formatMoney();
			  
			  //Get elementid para que muestre los datos de la consulta 
			  document.getElementById("contrato").innerHTML =
			    "<table class=\"table no-more-tables\">" +
				"<tr>" +
				    "<td><strong>N&uacute;mero de Contrato: </strong>"+p.NoContrato+"</td>" +
				    "<td><strong> Tipo de Contrato: </strong> "+p.TipoContrato + "</td>" +
				"</tr>" +
				"<tr>" +
				    "<td><strong> Fecha de Contrato: </strong> "+p.FechaContrato +"</td>" +
				    "<td><strong> Fecha de Inicio de Contrato: </strong> "+p.FechaInicioContrato+ "</td>" +
				"</tr>" +
				"<tr>" +
				    "<td><strong> Fecha de Termino de Contrato: </strong>"+p.fechaTerminoContrato+"</td>" +
				    "<td><strong> Periodo de Ejecuci&oacute;n en D&iacute;as: </strong>"+p.PeriodoEjucionDias+"</td>" +
				"</tr>" +
				"<tr>" +
				    "<td><strong> Importe de Contrato sin IVA: </strong>"+ importecontratosiniva +"</td>" +
				    "<td><strong> Partida Presupuestal: </strong>"+ partidapresupuestal +"</td>" +
				"</tr>" +
				"<tr>" +
				    "<td><strong> Importe Fiscal 1 sin IVA: </strong>"+importefiscal+"</td>" +
				    "<td><strong> Nombre Ejercicio Fiscal 1: </strong>"+ p.nombreEjercicioFiscal1 +"</td>" +
				"</tr>" +
				"<tr>"+
			    "</table>";
			     
		  }, errorCB);
		   
	      }, errorCB, successConsulta());
    }
    
    function contratistaObra(idObra) {
	
	var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
	//console.log("mostrando datos de la obra id: " +idObra);
	
	db.transaction(function(tx) {
	    //select o.Superintendente as superintendente, c.nombre as Nombre from Obras as o, Contratista as c where o.idObra= "1" and c.idContratista == o.EmpresaContratista
	    //tx.executeSql('select * from Obras where idObra=?',
		  tx.executeSql('select o.Superintendente as superintendente, c.nombre as Nombre from Obras as o, Contratista as c where cast(o.idObra as int) = ? and c.idContratista == o.EmpresaContratista',
				[idObra],  function(tx, rs) {
				    
				console.log("Obra: " + rs.rows.length);
			  var p = rs.rows.item(0);
			  document.getElementById("contratista").innerHTML =
			    "<table class=\"table no-more-tables\">" +
				"<tr>" +
				    //"<td><strong> Empresa Contratista: </strong>Dragados Pakal de Chiapas, S.A. de. C.V</td>" +
				    "<td><strong> Empresa Contratista: </strong>"+p.Nombre+"</td>" +
				    "<td><strong> Superintendente: </strong>"+p.superintendente+"</td>" +
				"</tr>" +
			    "</table>";
			     
		  }, errorCB);
		   
	      }, errorCB, successConsulta());
    }
    
    function dependenciaObra(idObra) {
	
	var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
	//console.log("mostrando datos de la obra id: " +idObra);
	
	db.transaction(function(tx) {
	    //SELECT g.nombre as Nombre_gobierno, s.nombre as Nombre_sec, d.nombre as Nombredepen, o.RFC as rfc, o.Subdireccion as sub, o.Area as area FROM Gobierno as g, Obras as o, Secretaria as s, Dependencia as d WHERE o.idObra == "1" and g.idGobierno == o.Gobierno and s.idSecretaria == o.Secretaria and d.idDependencia == o.Dependencia
		  //tx.executeSql('select * from Obras where idObra=?',
		  tx.executeSql('SELECT g.nombre as Nombre_gobierno, s.nombre as Nombre_sec, d.nombre as Nombredepen, o.RFC as rfc, o.Subdireccion as sub, o.Area as area FROM Gobierno as g, Obras as o, Secretaria as s, Dependencia as d WHERE cast(o.idObra as int) == ? and g.idGobierno == o.Gobierno and s.idSecretaria == o.Secretaria and d.idDependencia == o.Dependencia',
				[idObra],  function(tx, rs) {
				    
				console.log("Obra: " + rs.rows.length);
			  var p = rs.rows.item(0);
			  document.getElementById("dependencia").innerHTML =
			    "<table class=\"table no-more-tables\">" +
				"<tr>" +
				    "<td><strong> Gobierno: </strong>"+p.Nombre_gobierno+"</td>" +
				    "<td><strong> Secretar&iacute;a: </strong>"+p.Nombre_sec+"</td>" +
				"</tr>" +
				"<tr>" +
				    "<td><strong> Dependencia: </strong>"+p.Nombredepen+"</td>" +
				    "<td><strong> Subdirecci&oacute;n: </strong> "+p.sub +"</td>" +
				"</tr>" +
				"<tr>" +
				    "<td><strong> &Aacute;rea: </strong> "+p.area+"</td>" +
				"</tr>" +
			    "</table>";
			     
		  }, errorCB);
		   
	      }, errorCB, successConsulta());
    }
    
    function anticipoObra(idObra) {
	
	var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
	//console.log("mostrando datos de la obra id: " +idObra);
	
	db.transaction(function(tx) {
		  tx.executeSql('select * from Obras where idObra=?',
				[idObra],  function(tx, rs) {
				    
				console.log("Obra: " + rs.rows.length);
			  var p = rs.rows.item(0);
			  
			  //variables para formato de moneda
			  var montofianzaanticipo = accounting.formatMoney(p.MontoFianzaAnticipo);
			  var montofianzacumplimiento = accounting.formatMoney(p.MontoFianzaCumplimiento);
			  
			  // Muestra los elementos de la BD
			  document.getElementById("anticipo").innerHTML =
			    "<table class=\"table no-more-tables\">" +
				"<tr>" +
				    "<td><strong> Anticipo: </strong> "+p.Anticipo +"</td>" +
				    "<td><strong> N&uacute;mero de Fianza Anticipo: </strong> "+p.NoFianzaAnticipo+"</td>" +
				"</tr>" +
				"<tr>" +
				    "<td><strong> Fecha de Fianza Anticipo: </strong>"+p.FechaFianzaAnticipo+"</td>" +
				    "<td><strong> Monto de Fianza Anticipo: </strong>"+montofianzaanticipo+"</td>" +
				"</tr>" +
				"<tr>" +
				    "<td><strong> N&uacute;mero de Fianza Cumplimiento: </strong> "+p.NoFianzaCumplimiento +"</td>" +
				    "<td><strong> Fecha de Fianza Cumplimiento: </strong>"+p.FechaFianzaCumplimiento+"</td>" +
				"</tr>" +
				"<tr>" +
				    "<td><strong> Monto de Fianza Cumplimiento: </strong>"+ montofianzacumplimiento +"</td>" +
				    
				"</tr>" +
			    "</table>";
			     
		  }, errorCB);
		   
	      }, errorCB, successConsulta());
    }
    
    function desvioObra(idObra) {
	
	var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
	//console.log("mostrando datos de la obra id: " +idObra);
	
	db.transaction(function(tx) {
		  tx.executeSql('select * from Obras where idObra=?',
				[idObra],  function(tx, rs) {
				    
				console.log("Obra: " + rs.rows.length);
			  var p = rs.rows.item(0);
			  document.getElementById("desvio").innerHTML =
			    "<table class=\"table no-more-tables\">" +
				"<tr>" +
				    "<td><strong> L&iacute;mite de Desv&iacute;o: </strong>"+p.LimiteDesvio+"</td>" +
				    "<td><strong> Desv&iacute;o Actual : </strong>"+p.Desvio+"</td>" +
				"</tr>" +
			    "</table>";
			     
		  }, errorCB);
		   
	      }, errorCB, successConsulta());
    }
    
    function convenioObra(idObra) {
	
	var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
	//console.log("mostrando datos de la obra id: " +idObra);
	
	db.transaction(function(tx) {
		  tx.executeSql('select * from Obras where idObra=?',
				[idObra],  function(tx, rs) {
				    
				console.log("Obra: " + rs.rows.length);
			  var p = rs.rows.item(0);
			  //variables para el formato de moneda
			  var importeconvenioreduccion = accounting.formatMoney(p.ImporteConvenioReduccion);
			  var importeconvenioampliacion = accounting.formatMoney(p.ImporteConvenioAmpliacion);
			  var importeajustecosto = accounting.formatMoney(p.ImporteAjusteCostos);
			  
			  //datos mostrados en el elemento de html
			  document.getElementById("convenios").innerHTML =
			    "<table class=\"table no-more-tables\">" +
				"<tr>" +
				    "<td><strong> Importe Convenio Reducci&oacute;n: </strong>"+ importeconvenioreduccion +"</td>" +
				    "<td><strong> Importe Convenio Ampliaci&oacute;n: </strong>"+ importeconvenioampliacion +"</td>" +
				"</tr>" +
				"<tr>" +
				    "<td><strong> Importe Ajuste Costos: </strong>"+ importeajustecosto +"</td>" +
				"</tr>" +
			    "</table>";
			     
		  }, errorCB);
		   
	      }, errorCB, successConsulta());
    }
    
    function revisoresObra(idObra) {
	
	var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
	//console.log("mostrando datos de la obra id: " +idObra);
	
	db.transaction(function(tx) {
		  tx.executeSql('select * from Obras where idObra=?',
				[idObra],  function(tx, rs) {
				    
				console.log("Obra: " + rs.rows.length);
			  var p = rs.rows.item(0);
			  document.getElementById("revisores").innerHTML =
			    "<table class=\"table no-more-tables\">" +
				"<tr>" +
				    "<td><strong> Cargo Revisi&oacute;n 1: </strong>"+p.CargoRevision1+"</td>" +
				    "<td><strong> Nombre Revisi&oacute;n 1: </strong>"+p.NombreRevision1+"</td>" +
				"</tr>" +
				"<tr>" +
				    "<td><strong> Cargo Revisi&oacute;n 2: </strong>"+p.CargoRevision2+"</td>" +
				    "<td><strong> Nombre Revisi&oacute;n 2: </strong>"+p.NombreRevision2+"</td>" +
				"</tr>" +
				"<tr>" + 
				    "<td><strong> Nombre de Quien Autoriza: </strong>"+p.NombreQuienAutoriza+"</td>" +
				    "<td><strong> Nombre VoBo: </strong>"+p.NombreVoBo+"</td>" +
				"</tr>" +
				"<tr>" +
				    "<td><strong> Cargo VoBo: </strong>"+p.CargoVoBo+"</td>" +
				"</tr>" +
			    "</table>";
			     
		  }, errorCB);
		   
	      }, errorCB, successConsulta());
    }
    function update_valores(){
      
      var desvio_fisico;
      var desvio_financiero;
      
      desvio_fisico = document.getElementById("amount").value;
      //alert("valor slide: " + desvio_fisico);
      desvio_financiero = document.getElementById("amount-2").value;
      //alert("valor slide: " + desvio_financiero);
      
      //var fisico =slide_valor();
      //var financiero =desvioFinanciero();
      var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
      
      db.transaction(function(tx){
         tx.executeSql('UPDATE AlertasObras SET LimiteDesvio = ? WHERE Tipo = 0',[desvio_fisico]);
         //alerta_desvio();
      }, errorCB, function (){
         console.log("::::::::::::::::: Se actualizo el limite de la tabla Alertas Proyectos Avance fisico" );
      });
      
      db.transaction(function(tx) {
         tx.executeSql('UPDATE AlertasObras SET LimiteDesvio = ? WHERE Tipo = 1',[desvio_financiero]);
         console.log("Se actualizo la alerta Financiera");
         //alerta_desvio();
      }, errorCB, function (){
         console.log("::::::::::::::::: Se actualizo el limite de la tabla Alertas Proyectos Avance Financiero" );
      });
      //alerta_desvio();
   }
 function minutas(idobra) {
    //code
    //var get = getGET();
    //var url = get.path;
    //var obra = get.idObra;
    gapi.client.load('communicationchannel', 'v1', function () {
	gapi.client.communicationchannel.listMultimedia({"tipoReferencia": "7","idReferencia":String(idobra),"tipoArchivo":"0",}).execute(function (resp) { //alert("2");
            if (resp.code){
		//alert("Error :::" + resp.message);
		alert("No hay videos :::");
	    }else{
		MultimediaItems = resp.items;
		console.log("Multimedia de video: " + MultimediaItems.length);
		//videos(MultimediaItems);
	    }
        });
    }, ROOT);
}

/*
function tendencia(idobra) {
    //code
    var puntos;
    var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
    console.log("id de Obra" + idobra);
    
    db.transaction(function(tx) {
	     tx.executeSql('SELECT * FROM  AvanceFisicoObras ' +
		     'where cast(idObra as int) = ?',[idobra],  function(tx, rs) {
               console.log("graficando Tendencia: " + rs.rows.length);
	       puntos = new Array(rs.rows.length);
	       indice = rs.rows.length;
	       var j = 1, k= 0;
	       var acumulador_1 = 0;
	       for(var i = 0; i < rs.rows.length; i++){
		    var p = rs.rows.item(i);
		    console.log("j: " + j +" fecha: " +p.FechaReporte+ " tendencia: "+p.Tendencia + " Avance fisico " + p.PAvanceFisico);
		    fecha_1 = p.FechaReporte;
		    b = p.Tendencia;
		    k = k + j;
		    j++;
		    var acumulador = p.PAvanceFisico;
		    var acumulador_1 = acumulador_1+ acumulador;
	       }
	       fecha_11 = convertir(fecha_1);
	       console.log("fecha cenvertida: " + fecha_11);
	       var fechaadd = 0;
	       console.log("fecha moment: " + fechaadd);
	       //console.log("funccion: " + moment_2());
	       console.log("pendiente: " + b);
	       console.log("suma: " + acumulador_1);
	       console.log("indice: " + indice);
	       console.log("suma de j: " + k);
	       var promedio = acumulador_1 / indice;
	       var promedio1 = k /indice;
	       console.log("promedio de y: "+ promedio);
	       console.log("promedio de x: "+ promedio1);
	       var a = 0;
	       a = promedio - (b* promedio1);
	       console.log("valor de a: " + a);
	       var tendencia = 0, iteracion = 1;
	       day = 15;
	       for(var i = 0; i < 20; i++){
		puntos = new Array(20);
		    tendencia= a + (b * iteracion);
		    fechaadd = moment(fecha_11).add(day, 'd').format("YYYY/MM/DD");
		    console.log("resultado: " + tendencia + "fecha: " + fechaadd);
		    puntos[i]={d:fechaadd, a:tendencia, b:null};
		    iteracion++;
		    day =day + 15;
	       }
                /*for (var i = 0; i < rs.rows.length; i++) {
                      var p = rs.rows.item(i);
		      console.log("fecha: " +p.FechaReporte+ " tendencia: "+p.Tendencia);
		      fecha = convertir(p.FechaReporte);		      
		      puntos[i]={d:fecha, a:p.Tendencia, b:null};
		      
                }
                
          }, errorCB);
    }, errorCB, function() {
     
     console.log("consulta realizada de Tendencia");
     Morris.Line({
	     element: 'line-example3',
	     data: puntos,
             xkey: 'd',
	     ykeys: ['a'],
	     labels: ['Tendencia'],
	     //lineColors:['#0090d9','#000000'],
	     lineColors:['#464646','#AE0C00'],
	     });
});
}
*/
