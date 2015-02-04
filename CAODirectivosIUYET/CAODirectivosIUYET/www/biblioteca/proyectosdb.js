
var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
function consultarProyectos() {
   console.log("Consultando Proyectos");
   
    getIdDirectivo();
    directorio_proyecto();
    get_proyecto();
   
   //alert("Consultando Proyectos");
    var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
  
             db.transaction(function(tx) {
              tx.executeSql('SELECT p.idProyecto, p.NombreCorto,'+
                            ' af.PAvanceFisico, af.FechaReporte as fechaFisico, af.Estado as efisico, af.Tendencia as tendenciaFisico,'+
                            ' av.PAvanceFinanciero,  av.FechaReporte as fechaFinanciero, av.Estado as efinanciero, av.Tendencia as tendenciaFinanciero'+
                            ' FROM'+
                            ' Proyectos as p,'+
                            ' AvanceFinancieroProyectos as av,'+
                            ' AvanceFisicoProyectos as af,'+
                            ' TempAvanceProyectos as temp'+
                            ' WHERE CAST(temp.idAvanceFisico as int) = CAST(af.IdAvanceFisico as int)'+
                            ' and CAST(temp.idAvanceFinanciero as int) = CAST(av.IdAvanceFinanciero as int)'+
                            ' and CAST(temp.idProyecto as int) = CAST(p.idProyecto as int)',[],  function(tx, rs) {
                console.log("Proyectos: " + rs.rows.length);
               // alert("Proyectos: " + rs.rows.length);
                for (var i = 0; i < rs.rows.length; i++) {
                      var p = rs.rows.item(i);
                      //document.getElementById("nombreProyecto").innerHTML = p.NombreLargo;
                      var estatus = "";
                      console.log("Estatus: fisico: "+p.efisico+" financiero:"+p.efinanciero);
                      //ESTATUS
                      if (p.efisico == "Conforme a programa" && p.efinanciero == "Conforme a programa") {
                        //code
                        estatus =
                         "<span class=\"label label-success\" style=\"color:#3BDF7D;\">Estatus Fisico </span>    |     "+
                          "<span class=\"label label-success\" style=\"color:#3BDF7D;\">Estatus Financiero  </span><br><br>";
                      }else if (p.efisico == "Atrasado" && p.efinanciero == "Atrasado") {
                        //code
                         estatus =
                         "<span class=\"label label-important\" style=\"color:#993333;\">Estatus Fisico </span>   |     "+
                          "<span class=\"label label-important\" style=\"color:#993333;\">Estatus Financiero</span><br><br>";
                      }else if (p.efisico == "Adelantado" && p.efinanciero == "Adelantado") {
                        //code
                         estatus =
                         "<span class=\"label label-warning\" style=\"color:#cc6633;\">Estatus Fisico </span>   |     "+
                          "<span class=\"label label-warning\" style=\"color:#cc6633;\">Estatus Financiero</span><br><br>";
                      }else{
                         estatus =
                         "<span class=\"label label-warning\">Estatus fisico = NA</span>   |     "+
                          "<span class=\"label label-warning\">Estatus financiero = NA</span><br><br>";
                      }
                      
                      
                      
                      //console.log("idfisico = " +p.idFisico+" idfinanciero: " +p.idFinanciero + " avanceFisico: " +p.PAvanceFisico);
console.log("nombre del proyecto " + p.NombreCorto);
                        document.getElementById("L").innerHTML += 
                      "<div class=\"col-md-4 col-vlg-3 col-sm-6\">  " +
		
			"<div class=\"tiles green added-margin  m-b-20\"> " +
			  "<div class=\"tiles-body\"> "+
				    //"<div class=\"controller\"> <a href=\"javascript:;\" class=\"reload\"></a></div> " +
				    "<div class=\"tiles-title text-white\" id = \"nombreProyecto\">"+p.NombreCorto+"</div> " +
                
			 "  <center><div class=\"widget-stats\"> " +
			     " <div class=\"wrapper transparent\"> " +
				   " <span class=\"item-title\">Avance Fisico</span> <span id = \"avanceFinanciero\" class=\"item-count animate-number semi-bold\" data-value=\"10%\" data-animation-duration=\"700\">"+p.PAvanceFisico+"%</span> " +
			    "  </div> " +
			   "</div> " +
			  " <div class=\"widget-stats\"> " +
                             " <div class=\"wrapper transparent\"> " +
				  "  <span class=\"item-title\">Avance Financiero</span> <span id = \"avanceFisico\" class=\"item-count animate-number semi-bold\" data-value=\"23%\" data-animation-duration=\"700\">"+p.PAvanceFinanciero+"%</span> " +
			     " </div> " +
                          " </div> </center>" + 
                         // " <div class=\"widget-stats \"> " +
                              "<div class=\"wrapper transparent\"> " +
				 //  " <span class=\"item-title\">Tendencia: Faltan "+p.tendenciaFisico+" d&iacute;as para finalizar </span> " +
			     " </div><br> <center>" + estatus +"</center>"+
                          // "</div> <br>" +
                        //   "<div class=\"progress transparent progress-small no-radius m-t-20\" style=\"width:90%\"> " +
                          //      "<div class=\"progress-bar progress-bar-white animate-progress-bar\" data-percentage=\"64.8%\" ></div> " +
                        //  " </div> " +
                         
                           "<a href=\"detalle_proyectos.html?idProyecto="+p.idProyecto+"\"><button class=\"btn btn-block btn-white\">VER</button></a> " +
                    
			"</div> " +
		      "</div> " +
	            "</div>";
                    
                    
                    
                    
                }
          }, errorCB);
          }, errorCB, successConsulta2);
}



function successConsulta2(proyectos, db) {
    console.log("Obras cargadas.....");
}



function successConsulta( idProyecto) {
 console.log("Cargando Obras del proyecto........idP: " + idProyecto);
    db.transaction(function(tx) {
             
              tx.executeSql('SELECT o.idObra, o.Nombre,'+
                            ' af.PAvanceFisico, af.FechaReporte as fechaFisico, af.Estado as efisico, af.Tendencia as tendenciaFisico,'+
                            ' av.PAvanceFinanciero,  av.FechaReporte as fechaFinanciero, av.Estado as efinanciero, av.Tendencia as tendenciaFinanciero'+
                            ' FROM'+
                            ' Obras as o,'+
                            ' AvanceFinancieroObras as av,'+
                            ' AvanceFisicoObras as af,'+
                            ' TempAvanceObras as temp'+
                            ' WHERE CAST(temp.idAvanceFisico as int) = CAST(af.IdAvanceFisico as int)'+
                            ' and CAST(temp.idAvanceFinanciero as int) = CAST(av.IdAvanceFinanciero as int)'+
                            ' and CAST(temp.idObra as int) = CAST(o.idObra as int) and CAST(o.idProyecto as int) = ? '
                           ,[idProyecto],  function(tx, rs) {
                console.log("Obras del Proyecto :::::::::::::::: " + rs.rows.length);
               // alert("Proyectos: " + rs.rows.length);
                for (var i = 0; i < rs.rows.length; i++) {
                      var p = rs.rows.item(i);
                      //document.getElementById("nombreProyecto").innerHTML = p.NombreLargo;
                      var estatus = "";
                      console.log("Estatus: fisico: "+p.efisico+" financiero:"+p.efinanciero);
                      //ESTATUS
                      if (p.efisico == "Conforme a programa" && p.efinanciero == "Conforme a programa") {
                        //code
                        estatus =
                         "<span class=\"label label-success\" style=\"color:#3BDF7D;\">Estatus F&iacute;sico</span>    |     "+
                          "<span class=\"label label-success\" style=\"color:#3BDF7D;\">Estatus Financiero</span><br><br>";
                      }else if (p.efisico == "Atrasado" && p.efinanciero == "Atrasado") {
                        //code
                         estatus =
                         "<span class=\"label label-important\" style=\"color:#993333;\">Estatus F&iacute;sico</span>   |     "+
                          "<span class=\"label label-important\" style=\"color:#993333;\">Estatus Financiero</span><br><br>";
                      }else if (p.efisico == "Adelantado" && p.efinanciero == "Adelantado") {
                        //code
                         estatus =
                         "<span class=\"label label-warning\" style=\"color:#cc6633;\">Estatus F&iacute;sico</span>   |     "+
                          "<span class=\"label label-warning\" style=\"color:#cc6633;\">Estatus Financiero</span><br><br>";
                      }else{
                         estatus =
                         "<span class=\"label label-warning\" >Estatus F&iacute;sico</span>   |     "+
                          "<span class=\"label label-warning\" >Estatus Financiero</span><br><br>";
                      }
                      
                      
                      
                      //console.log("idfisico = " +p.idFisico+" idfinanciero: " +p.idFinanciero + " avanceFisico: " +p.PAvanceFisico);
                        document.getElementById("O").innerHTML += 
                      "<div class=\"col-md-4 col-vlg-3 col-sm-6\">  " +
		
			"<div class=\"tiles green added-margin  m-b-20\"> " +
			  "<div class=\"tiles-body\"> "+
				    //"<div class=\"controller\"> <a href=\"javascript:;\" class=\"reload\"></a></div> " +
				    "<div class=\"tiles-title text-white\" id = \"nombreProyecto\">"+p.Nombre+"</div> " +
                
			 "  <center><div class=\"widget-stats\"> " +
			     " <div class=\"wrapper transparent\"> " +
				   " <span class=\"item-title\">Avance F&iacute;sico</span> <span id = \"avanceFinanciero\" class=\"item-count animate-number semi-bold\" data-value=\"10%\" data-animation-duration=\"700\">"+p.PAvanceFisico+"%</span> " +
			    "  </div> " +
			   "</div> " +
			  " <div class=\"widget-stats\"> " +
                             " <div class=\"wrapper transparent\"> " +
				  "  <span class=\"item-title\">Avance Financiero</span> <span id = \"avanceFisico\" class=\"item-count animate-number semi-bold\" data-value=\"23%\" data-animation-duration=\"700\">"+p.PAvanceFinanciero+"%</span> " +
			     " </div> " +
                          " </div> </center>" + 
                         // " <div class=\"widget-stats \"> " +
                              "<div class=\"wrapper transparent\"> " +
				   //" <span class=\"item-title\">Tendencia: Faltan "+p.tendenciaFisico+" d&iacute;as para finalizar </span> " +
			     " </div><br> <center>" + estatus +"</center>"+
                          // "</div> <br>" +
                        //   "<div class=\"progress transparent progress-small no-radius m-t-20\" style=\"width:90%\"> " +
                          //      "<div class=\"progress-bar progress-bar-white animate-progress-bar\" data-percentage=\"64.8%\" ></div> " +
                        //  " </div> " +
                         
                           //"<a href=\"detalle_obras.html?idProyecto="+idProyecto+"&&idObra="+p.idObra+"\"><button class=\"btn btn-block btn-white\">VER</button></a> " +
                           //link para el detalle de la obra
                           "<a href=\"detalle_obras_reporte.html?idProyecto="+idProyecto+"&&idObra="+p.idObra+"\"><button class=\"btn btn-block btn-white\">VER</button></a> " +
			"</div> " +
		      "</div> " +
	            "</div>";
                    
                    
                    
                    
                }
        
             
              }, errorCB);
               
          }, errorCB, successConsulta2);
   
}

function errorCB(err) {
    console.log("Error processing SQL: "+err.code + " Message: "+err.message);
}

      
function detalleProyectos(idProyecto) {
    
    var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
    console.log("mostrando datos de proyecto id: " +idProyecto);
    
    db.transaction(function(tx) {
             
            /*  tx.executeSql('select p.NombreLargo, p.NombreCorto, p.LimiteDesvio, p.Desvio, p.Secretaria, em.Nombre as Dependencia' +
                            ' from Proyectos as p, Empresa as em' +
                            ' where p.idProyecto=? and p.Dependencia = em.IdEmpresa',*/
              tx.executeSql('SELECT  p.idProyecto, p.NombreLargo, p.NombreCorto, p.LimiteDesvio, p.Desvio,'+
                            'av.PAvanceFisico, av.Tendencia as ftendencia, av.FechaReporte as ffecha, av.Estado as efisico,'+
                            'af.PAvanceFinanciero, af.Tendencia as etendencia, af.FechaReporte as efecha, af.Estado as efinanciero, dep.nombre as Nombredep, sec.nombre as Nombresec'+
                            ' FROM Proyectos as p, AvanceFisicoProyectos as av, AvanceFinancieroProyectos as af, Dependencia as dep, Secretaria as sec'+
                            ' where CAST(av.idProyecto AS INT) = ?'+
                            ' and CAST(af.idProyecto AS INT) = ? '+
                            ' and CAST(p.idProyecto AS INT) = ? ' +
                            ' and CAST(dep.idDependencia AS INT) = CAST(p.Dependencia AS INT) '+
                            ' and CAST(sec.idSecretaria AS INT)= CAST(p.Secretaria AS INT)',
                            [idProyecto,idProyecto,idProyecto],  function(tx, rs) {
                               
                            console.log("Proyectos: " + rs.rows.length);
                      var p = rs.rows.item(0);
                       //alert("secretaria " + p.Secretaria);
                      document.getElementById("detalle").innerHTML = 
                     " <h3><strong>"+p.NombreLargo+"</strong></h3> " +
                      " <h4><strong>"+p.NombreCorto+"</strong><br><br> " +
                       " <strong> Dependencia: </strong> "+p.Nombredep +
                        " <strong> |   Secretar&iacute;a: </strong> "+p.Nombresec+
                         "<strong> |   L&iacute;mite de Desv&iacute;o: </strong>"+p.LimiteDesvio+
                        " <strong> |   Desv&iacute;o Actual: </strong>"+p.Desvio+"</h4> ";
                         
                       /*document.getElementById("fisicoFecha").innerHTML = p.ffecha;
                       document.getElementById("fisicoCantidadAvance").innerHTML = p.PAvanceFisico +"%";
                       document.getElementById("fisicoEstado").innerHTML = p.efisico;
                       
                       document.getElementById("financieroFecha").innerHTML = p.efecha;
                       document.getElementById("financieroCantidadAvance").innerHTML = p.PAvanceFinanciero+"%";
                       document.getElementById("financieroEstado").innerHTML = p.efinanciero;
                       
                       document.getElementById("fisicoTendencia").innerHTML = "Faltan "+p.ftendencia +" d&iacute;as para finalizar";
                       document.getElementById("financieroTendencia").innerHTML = "Faltan "+p.etendencia+" d&iacute;as para finalizar";
                       */
                       
              }, errorCB);
               
          }, errorCB, successConsulta(idProyecto));
 
}
function updateFinanciero(){
var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
  var valor =desvioFinanciero();
  console.log("valor de alerta " + valor);

  db.transaction(function(tx) {
    tx.executeSql('UPDATE AlertasProyectos SET LimiteDesvio = ? WHERE LimiteDesvio = 0 AND Tipo = 1',[valor]);
    console.log("Se actualizo el dato:");
  }, errorCB, function (){
    console.log("::::::::::::::::: Se actualizo el limite de la tabla AlertasProyectos" );
  });
}
// Actuliza el valor de la alerta del desvio fisico
function updateFisico(){

var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
  var valor =desvioFisico();
  // alert("valor de alerta " + valor);

  db.transaction(function(tx) {
    tx.executeSql('UPDATE AlertasProyectos SET LimiteDesvio = ? WHERE LimiteDesvio = 0 AND Tipo = 2',
      [valor]);
    console.log("Se actualizo la tabla");
    alerta_desvio();
  }, errorCB, function (){
    console.log("::::::::::::::::: Se actualizo el limite de la tabla AlertasProyectos" ); 
  });
}

function alerta_desvio() {
    
    var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
    //console.log("mostrando datos de proyecto id: ");
    
    db.transaction(function(tx) {
             
        console.log("entra a la consulta");
              tx.executeSql('SELECT * FROM AlertasProyectos',[],  function(tx, rs) {
                               
                     console.log("Proyectos: " + rs.rows.length);
                      var p = rs.rows.item(0);
                      for(var i = 0; i < rs.rows.length; i++){
                        
                       console.log("secretaria " + p.idAlerta);
                       //idProyecto, LimiteDesvio, Tipo
                       console.log("Proyecto " + p.idProyecto);
                       console.log("limite desvio " + p.LimiteDesvio);
                       console.log("tipo " + p.Tipo);
                      }
                      
                       
              }, errorCB);
               
          }, errorCB, successConsulta());
 
}

/*NOTIFICACIONES*/
function update_valores(){
      idProyecto = getGET().idProyecto;
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
        
          tx.executeSql('select * from  AlertasProyectos',[],function(tx, rs) {
            console.log("Proyectos: " + rs.rows.length);
               for(var i = 0; i < rs.rows.length; i++){
                  var p = rs.rows.item(i);
                  if (p.Tipo == 0) {
                     //code
                     console.log("id de Alerta Fisica " + p.idAlerta + " Proyecto " + p.idProyecto + " limite desvio " + p.LimiteDesvio +" tipo " + p.Tipo);
                        limte_fisico = p.LimiteDesvio;
                  }else{
                     console.log("id de Alerta Financiera " + p.idAlerta + " Proyecto " + p.idProyecto + " limite desvio " + p.LimiteDesvio +" tipo " + p.Tipo);
                  }
                  
                  //idAlerta unique,  idProyecto, LimiteDesvio, Tipo
               }
               
            }, errorCB);  
        
        
        
         tx.executeSql('UPDATE AlertasProyectos SET LimiteDesvio = ? WHERE Tipo = 0 and CAST(idProyecto as int)=?',[desvio_fisico, idProyecto]);
         //alerta_desvio();
      }, errorCB, function (){
         console.log("::::::::::::::::: Se actualizo el limite de la tabla Alertas Proyectos Avance fisico" );
      });
      
      db.transaction(function(tx) {
         tx.executeSql('UPDATE AlertasProyectos SET LimiteDesvio = ? WHERE Tipo = 1 and CAST(idProyecto as int)=?',[desvio_financiero, idProyecto]);
         console.log("Se actualizo la alerta Financiera");
         //alerta_desvio();
      }, errorCB, function (){
         console.log("::::::::::::::::: Se actualizo el limite de la tabla Alertas Proyectos Avance Financiero" );
      });
      //alerta_desvio();
   }

 function mostrar_datos() {
       var limte_fisico;
       var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
       //console.log("mostrando datos de proyecto id: ");
       
       db.transaction(function(tx) {
           tx.executeSql('select * from  AlertasProyectos',[],function(tx, rs) {
            console.log("Proyectos: " + rs.rows.length);
               for(var i = 0; i < rs.rows.length; i++){
                  var p = rs.rows.item(i);
                  if (p.Tipo == 0) {
                     //code
                     console.log("id de Alerta Fisica " + p.idAlerta + " Proyecto " + p.idProyecto + " limite desvio " + p.LimiteDesvio +" tipo " + p.Tipo);
                        limte_fisico = p.LimiteDesvio;
                  }else{
                     console.log("id de Alerta Financiera " + p.idAlerta + " Proyecto " + p.idProyecto + " limite desvio " + p.LimiteDesvio +" tipo " + p.Tipo);
                  }
                  
                  //idAlerta unique,  idProyecto, LimiteDesvio, Tipo
               }
               
            }, errorCB);            
      }, errorCB, successConsulta3());
   }
/*function buscar(pagina){
     //code
     var get = getGET();
     proyecto = get.idProyecto;
     dato = document.getElementById("txtbuscar").value;
     location.href="resultado_busqueda.html?pagina="+pagina+"&dato="+dato+"&idProyecto="proyecto;
}*/
/*
console.log("id de secretaria: " +p.Secretaria);
              tx.executeSql('select * from Empresa where IdEmpresa=? ',
                            [p.Secretaria],  function(tx, rse) { 
                            console.log("Secretaria: " + rse.rows.length);
                            var pa = rse.rows.item(0); 
                            }, errorCB);
               */