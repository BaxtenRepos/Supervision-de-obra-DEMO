function consultarProyectos() {
   //get_mapa();
   //console.log("Consultando Proyectos");
   var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
  
             db.transaction(function(tx) {
              tx.executeSql('SELECT p.idProyecto, p.NombreCorto, p.idUbicacion,'+
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
                for (var i = 0; i < rs.rows.length; i++) {
                      var p = rs.rows.item(i);
                      var estatus = "";
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
                        document.getElementById("L").innerHTML += 
                      "<div class=\"col-md-4 col-vlg-3 col-sm-6\">  " +
		
			"<div class=\"tiles green added-margin  m-b-20\"> " +
			  "<div class=\"tiles-body\"> "+
			      "<div class=\"tiles-title text-white\" id = \"nombreProyecto\">"+p.NombreCorto+"</div> " +
                
			"<center><div class=\"widget-stats\"> " +
			     " <div class=\"wrapper transparent\"> " +
				   " <span class=\"item-title\">Avance Fisico</span> <span id = \"avanceFinanciero\" class=\"item-count animate-number semi-bold\" data-value=\"10%\" data-animation-duration=\"700\">"+p.PAvanceFisico+"%</span> " +
			    "  </div> " +
			   "</div> " +
			  " <div class=\"widget-stats\"> " +
                             " <div class=\"wrapper transparent\"> " +
				  "  <span class=\"item-title\">Avance Financiero</span> <span id = \"avanceFisico\" class=\"item-count animate-number semi-bold\" data-value=\"23%\" data-animation-duration=\"700\">"+p.PAvanceFinanciero+"%</span> " +
			     " </div> " +
                          " </div> </center>" + 
                              "<div class=\"wrapper transparent\"> " +
			     " </div><br> <center>" + estatus +"</center>"+
                           "<a href=\"mapa_proyectos.html?idProyecto="+p.idProyecto+"&&idUbicacion="+p.idUbicacion+"\"><button class=\"btn btn-block btn-white\">VER</button></a> " +
                    
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
function errorCB(err) {
   console.log("Error processing SQL: "+err.code + " Message: "+err.message);
}