$(document).ready(function() {
//function grafica() {
     //code
	//---------------------------------
	//cargar();
	var puntos;
	var idObra = getGET().idObra;
	var indice;
	var tendencia = 0, iteracion = 1, a = 0;
	puntos = new Array(21);
	var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
	  console.log("id de Obra" + idObra + "Tendencia");
	db.transaction(function(tx) {
	  tx.executeSql('SELECT * FROM  AvanceFisicoObras ' +
		     'where cast(idObra as int) = ?',[idObra],  function(tx, rs) {
               console.log("graficando Tendencia: " + rs.rows.length);
	       //puntos = new Array(rs.rows.length);
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
	       a = promedio - (b* promedio1);
	       console.log("valor de a: " + a);
	       day = 15;
	       for(var i = 0; i < 20; i++){
		//puntos = new Array(20);
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
                */
          }, errorCB);
	     /*tx.executeSql('SELECT * FROM  AvanceFisicoObras ' +
		     'where cast(idObra as int) = ?',[idObra],  function(tx, rs) {
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
	       fechaadd = moment(fecha_11);
	       console.log("fecha moment: " + fechaadd);
	       console.log("funccion: " + moment_2());
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
	       for(var i = 0; i < 20; i++){
		    tendencia= a + (b * iteracion);
		    //fecha_suma = moment().add(15, 'days');
		    console.log("resultado: " + tendencia );
		    iteracion++;
	       }
                for (var i = 0; i < rs.rows.length; i++) {
                      var p = rs.rows.item(i);
		      console.log("fecha: " +p.FechaReporte+ " tendencia: "+p.Tendencia);
		      fecha = convertir(p.FechaReporte);		      
		      puntos[i]={d:fecha, a:p.Tendencia, b:null};
		      
                }
                
          }, errorCB);*/
        }, errorCB, successConsulta);
	
function successConsulta() {
     
     console.log("consulta realizada de Tendencia");
     Morris.Line({
	     //element: 'line-example3',
	     data: puntos,
             xkey: 'd',
	     ykeys: ['a', 'b'],
	     labels: ['Programado', 'Real'],
	     //lineColors:['#0090d9','#000000'],
	     lineColors:['#464646','#AE0C00'],
	     
	     });
}

function errorCB(err) {
	console.log("Error al graficar avances processing SQL: "+err.code + " Message: "+err.message);
}

});
//}
// funcion que convierte a la fecha en otro formato
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
function moment_2() {
     //code
     var fechaadd;
     fechaadd = moment();
     console.log("fecha: " + fechaadd);
     return fechaadd;
}

