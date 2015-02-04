$(document).ready(function() {		

	//---------------------------------
	var puntos, fecha, fecha_2;
	var idObra = getGET().idObra;
	var fecha_avance, fecha_programa;
	var avance_fisico, avance_programado;
	var indice;
	var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
console.log("id de Obra" + idObra);
	db.transaction(function(tx) {
	     tx.executeSql('SELECT * FROM  Programa_Obra ' +
		     'where cast(idObra as int) = ? and TipoAvance = 0',[idObra],  function(tx, rs) {
                console.log("graficando programas: " + rs.rows.length);
		puntos = new Array(rs.rows.length);
		indice = rs.rows.length;
                for (var i = 0; i < rs.rows.length; i++) {
                      var p = rs.rows.item(i);
		      fecha = convertir(p.Fecha);		      
		      puntos[i]={d:fecha, a:p.PorcentajeAvance, b:null};
		      
                }
		
          }, errorCB);
        }, errorCB, successConsulta);
	
function successConsulta() {
	var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);

	db.transaction(function(tx) {
	    
	     tx.executeSql('SELECT * FROM  AvanceFisicoObras where cast(idObra as int) = ?',[idObra],  function(tx, rs) {
                console.log("graficando avances: " + rs.rows.length);
                for (var i = 0; i < rs.rows.length; i++) {
                      var p = rs.rows.item(i);
		      fecha_2 = convertir(p.FechaReporte);
		      puntos[indice + i]={d:fecha_2, b:p.PAvanceFisico};
                }
          }, errorCB);
        }, errorCB, succesConsulta2);
}

function errorCB(err) {
	console.log("Error al graficar avances processing SQL: "+err.code + " Message: "+err.message);
}

function succesConsulta2(args) {
	
	Morris.Line({
	     element: 'line-example',
	     data: puntos,
             xkey: 'd',
	     ykeys: ['a', 'b'],
	     labels: ['Programado', 'Real'],
	     //lineColors:['#0090d9','#000000'],
	     lineColors:['#464646','#AE0C00'],
	     
	     });
}
// grafica para avance financiero

	var puntos2, fecha, fecha_2;//puntos para la grafica de avance financiero
	var idObra = getGET().idObra;
	var fecha_avance, fecha_programa;
	var avance_fisico, avance_programado;
	var indice;
	var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);

	db.transaction(function(tx) {
	     tx.executeSql('SELECT * FROM  Programa_Obra ' +
		     'where cast(idObra as int) = ? and TipoAvance = 1',[idObra],  function(tx, rs) {
                console.log("graficando programas: " + rs.rows.length);
		puntos2 = new Array(rs.rows.length);
		indice = rs.rows.length;
                for (var i = 0; i < rs.rows.length; i++) {
                      var p = rs.rows.item(i);
		      fecha = convertir(p.Fecha);		      
		      puntos2[i]={d:fecha, a:p.PorcentajeAvance, b:null};
		      
                }
		
          }, errorCB);
        }, errorCB, successConsulta3);
	
	
function successConsulta3() {
	var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);

	db.transaction(function(tx) {
	    
	     tx.executeSql('SELECT * FROM  AvanceFinancieroObras where cast(idObra as int) = ?',[idObra],  function(tx, rs) {
                console.log("graficando avances: " + rs.rows.length);
                for (var i = 0; i < rs.rows.length; i++) {
                      var p = rs.rows.item(i);
		      fecha_2 = convertir(p.FechaReporte);
		      puntos2[indice + i]={d:fecha_2, b:p.PAvanceFinanciero};
                }
          }, errorCB);
        }, errorCB, succesConsulta4);
}


function succesConsulta4(args) {
	
	Morris.Line({
	     element: 'line-example2',
	     data: puntos2,
             xkey: 'd',
	     ykeys: ['a', 'b'],
	     labels: ['Programado', 'Real'],
	     //lineColors:['#0090d9','#000000'],
	     lineColors:['#464646','#AE0C00'],
	     });
}
//Grafica Tendencia
     var puntos3;
     var idObra = getGET().idObra;
     var indice;
     var tendencia = 0, iteracion = 1, a = 0, tendencia_2 = 0;
     
     var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
     
     db.transaction(function(tx) {
	  tx.executeSql('SELECT * FROM  AvanceFisicoObras ' +
		     'where cast(idObra as int) = ?',[idObra],  function(tx, rs) {
               console.log("graficando Tendencia: " + rs.rows.length);
	       indice = rs.rows.length;
	       puntos3 = new Array(indice);//quite el 20
	       //puntos3 = [40];
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
	       day = 0;
	       /*do{
		    tendencia= a + (b * iteracion);
		    tendencia_2 = Math.round(tendencia);
		    fechaadd = moment(fecha_11).add(day, 'd').format("YYYY-MM-DD");
		    //console.log("i: "+ i +" fecha: " + fechaadd +" resultado: " + tendencia);
		    //grafica(tendencia, fechaadd);
		    puntos3[i]={d:fechaadd, a:tendencia_2};//linea para graficar
		    console.log("d: " + puntos3[i].d +" a: " + puntos3[i].a);
		    //console.log("a: " + puntos3[i].a);
		    iteracion++;
		    day =day + 15;
	       }while(tendencia_2 < 100);*/
	       for(var i = 0; i < 20; i++){
		    tendencia= a + (b * iteracion);
		    tendencia_2 = Math.round(tendencia);
		    fechaadd = moment(fecha_11).add(day, 'd').format("YYYY-MM-DD");
		    console.log("i: "+ i +" fecha: " + fechaadd +" resultado: " + tendencia + " indice: " + indice+ " interaccion: "+iteracion);
		    //grafica(tendencia, fechaadd);
		    puntos3[i]={d:fechaadd, a:tendencia_2};//linea para graficar
		    //console.log("d: " + puntos3[i].d +" a: " + puntos3[i].a);
		    //console.log("a: " + puntos3[i].a);
		    iteracion++;
		    day =day + 15;
	       }
          }, errorCB);
        //}, errorCB, successConsulta5);
	}, errorCB, successConsulta5);
function successConsulta5() {
	var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);

	db.transaction(function(tx) {
	     tx.executeSql('SELECT * FROM  Programa_Obra ' +
		     'where cast(idObra as int) = ? and TipoAvance = 0',[idObra],  function(tx, rs) {
                console.log("graficando programas de tendencia: " + rs.rows.length);
		indice = rs.rows.length;
                for (var i = 0; i < rs.rows.length; i++) {
                      var p = rs.rows.item(i);
		      fecha = convertir(p.Fecha);
		      puntos3[indice + i]={d:fecha, a:null, b:p.PorcentajeAvance};
		      console.log("puntos de programa: " + iteracion);
		      iteracion++;
                }
		
          }, errorCB);
        }, errorCB, successConsulta6);
}
function successConsulta6() {
     
     console.log("consulta realizada de Tendencia");
     Morris.Line({
	     element: 'line-example3',
	     data: puntos3,
             xkey: 'd',
	     ykeys: ['a', 'b'],
	     labels: ['Tendencia', 'Programa'],
	     //lineColors:['#0090d9','#000000'],
	     lineColors:['#464646','#AE0C00'],
	     });
     
}
});

function grafica(tendencia, dia) {
     //code
     Morris.Line({
	  element: 'line-example3',
	  //data: puntos3,
	  data: {d: dia, a: tendencia},
          xkey: 'd',
	  ykeys: ['a'],
	  labels: ['Tendencia'],
	     //lineColors:['#0090d9','#000000'],
	  lineColors:['#464646','#AE0C00'],
	     
     });
}
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


