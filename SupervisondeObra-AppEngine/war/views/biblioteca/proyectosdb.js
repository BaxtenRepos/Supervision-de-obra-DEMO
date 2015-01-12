

function consultarProyectos() {
   console.log("Consultando Proyectos");
    var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
  
             db.transaction(function(tx) {
              tx.executeSql('SELECT MAX(CAST(av.IdAvanceFisico AS INT)) as idFisico, MAX(CAST(af.IdAvanceFinanciero AS INT)) as idFinanciero,'+
                            ' p.idProyecto, p.NombreLargo, av.PAvanceFisico, av.Tendencia, af.PAvanceFinanciero'+
                            ' FROM Proyectos p, AvanceFisico av, AvanceFinanciero af'+
                            ' where af.TipoEntidad = 0 and av.TipoEntidad = 0 and av.idReferencia = CAST(p.idProyecto AS INT)'+
                            ' and af.idReferencia = CAST(p.idProyecto AS INT) group by p.idProyecto',[],  function(tx, rs) {
                console.log("Proyectos: " + rs.rows.length);
                for (var i = 0; i < rs.rows.length; i++) {
                      var p = rs.rows.item(i);
                      //document.getElementById("nombreProyecto").innerHTML = p.NombreLargo;
                      console.log("idfisico = " +p.idFisico+" idfinanciero: " +p.idFinanciero + " avanceFisico: " +p.PAvanceFisico);
                        document.getElementById("L").innerHTML += 
                      "<div class=\"col-md-4 col-vlg-3 col-sm-6\">  " +
		
			"<div class=\"tiles green added-margin  m-b-20\"> " +
			  "<div class=\"tiles-body\"> "+
				    "<div class=\"controller\"> <a href=\"javascript:;\" class=\"reload\"></a></div> " +
				    "<div class=\"tiles-title text-black\" id = \"nombreProyecto\">"+p.NombreLargo+"</div> " +
                
			 "  <div class=\"widget-stats\"> " +
			     " <div class=\"wrapper transparent\"> " +
				   " <span class=\"item-title\">A. Fisico</span> <span id = \"avanceFinanciero\" class=\"item-count animate-number semi-bold\" data-value=\"10%\" data-animation-duration=\"700\">"+p.PAvanceFisico+"%</span> " +
			    "  </div> " +
			   "</div> " +
			  " <div class=\"widget-stats\"> " +
                             " <div class=\"wrapper transparent\"> " +
				  "  <span class=\"item-title\">A. Financiero</span> <span id = \"avanceFisico\" class=\"item-count animate-number semi-bold\" data-value=\"23%\" data-animation-duration=\"700\">"+p.PAvanceFinanciero+"%</span> " +
			     " </div> " +
                          " </div> " +
                          " <div class=\"widget-stats \"> " +
                              "<div class=\"wrapper last\"> " +
				   " <span class=\"item-title\">Tendencia</span> <span id = \"tendencia\" class=\"item-count animate-number semi-bold\" data-value=\"234 d&iacute;as\" data-animation-duration=\"700\">"+p.Tendencia+" d&iacute;as</span> " +
			     " </div> " +
                           "</div> " +
                           "<div class=\"progress transparent progress-small no-radius m-t-20\" style=\"width:90%\"> " +
                                "<div class=\"progress-bar progress-bar-white animate-progress-bar\" data-percentage=\"64.8%\" ></div> " +
                          " </div> " +
                           "<div class=\"description\"> <span class=\"text-white mini-description \" id = \"status\">Estatus: <span class=\"blend\"></span></span></div> " +
			   "<a href=\"detalle_proyectos.html?idProyecto="+p.idProyecto+"\"><button class=\"btn btn-block btn-white\">VER</button></a> " +
                    
			"</div> " +
		      "</div> " +
	            "</div>";
                }
          }, errorCB);
          }, errorCB, successConsulta);
}







function successConsulta(proyectos, db) {
    //alert("consulta OK");
   
}

function errorCB(err) {
    console.log("Error processing SQL: "+err.code + " Message: "+err.message);
}

      
function detalleProyectos(idProyecto) {
   // alert("Consultar 1");
    var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
    console.log("mostrando datos de proyecto id: " +idProyecto);
    
    db.transaction(function(tx) {
             
        
              tx.executeSql('select p.NombreLargo, p.NombreCorto, p.LimiteDesvio, p.Desvio, p.Secretaria, em.Nombre as Dependencia' +
                            ' from Proyectos as p, Empresa as em' +
                            ' where p.idProyecto=? and p.Dependencia = em.IdEmpresa',
                            [idProyecto],  function(tx, rs) {
                                
                            console.log("Proyectos: " + rs.rows.length);
                      var p = rs.rows.item(0);
                      document.getElementById("detalle").innerHTML = 
                     " <strong>"+p.NombreLargo+"</strong><br> " +
                      " <strong>"+p.NombreCorto+"</strong><br> " +
                       " <strong> Dependencia: "+p.Dependencia+"</strong><br> " +
                        " <strong> Secretaria: "+p.Secretaria+" </strong><br> " +
                         " <strong> Limite de desvio: "+p.LimiteDesvio+"</strong><br> " +
                          " <strong> Desvio: "+p.Desvio+"</strong><br> ";
              }, errorCB);
               
          }, errorCB, successConsulta);
    
    //var SC = Secretaria(p.Secretaria); 
                      
                      
}

/*
console.log("id de secretaria: " +p.Secretaria);
              tx.executeSql('select * from Empresa where IdEmpresa=? ',
                            [p.Secretaria],  function(tx, rse) { 
                            console.log("Secretaria: " + rse.rows.length);
                            var pa = rse.rows.item(0); 
                            }, errorCB);
               */