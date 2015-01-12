
$(document).ready(function() {		


function Graficas(){
// Moris Charts - Line Charts
	var get = getGET();
	var puntos = [];	
                       // alert(get.idProyecto);
		        var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
	db.transaction(function(tx) {
              tx.executeSql('SELECT * FROM AvanceFisicoProyectos',[],  function(tx, rs) {
                console.log("graficando avances: " + rs.rows.length);
		var r= 20;
                for (var i = 0; i < rs.rows.length; i++) {
                      var p = rs.rows.item(i);
                   
		   puntos[i]={d:p.FechaReporte, a:r+5, b:p.PAvanceFisico};
		   
		    //puntos[2].d = "2014-12-01"; 
                }
          }, errorCB);
          }, errorCB, successConsulta);	

}

function successConsulta() {
             console.log("consulta para graficacion OK");
	     Morris.Line({
	     element: 'line-example',
	     data: puntos,
             xkey: 'd',
	     ykeys: ['a', 'b'],
	     labels: ['Programado', 'Real'],
	     lineColors:['#0090d9','#000000'],//'#d1dade'
	     });
	     
	  Morris.Line({
	      element: 'line-example2',
	      data: puntos,
              xkey: 'd',
	      ykeys: ['a', 'b'],
	      labels: ['Programado', 'Real'],
	      lineColors:['#0090d9','#000000'],//'#d1dade'
	   });
	  
          Morris.Line({
	      element: 'line-example3',
	      data: puntos,
              xkey: 'd',
	      ykeys: ['a', 'b'],
	      labels: ['Programado', 'Real'],
	      lineColors:['#0090d9','#000000'],//'#d1dade'
	   });
         }

     function errorCB(err) {
           console.log("Error al graficar avances processing SQL: "+err.code + " Message: "+err.message);
     }
     
});