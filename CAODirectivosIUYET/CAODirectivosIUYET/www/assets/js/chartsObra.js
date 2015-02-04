$(document).ready(function() {		

	//---------------------------------
	var puntos, fecha, fecha_2;
	var idObra = getGET().idObra;
	//var fecha_avance, fecha_programa;
	//var avance_fisico, avance_programado;
	var indice;
	var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
console.log("id de Obra" + idObra);
	db.transaction(function(tx) {
	     tx.executeSql('SELECT * FROM  Programa_Obra ' +
		     'where cast(idObra as int) = ? and TipoAvance = 0',[idObra],  function(tx, rs) {
                //console.log("graficando programas fisico: " + rs.rows.length+ " obra: "+ idObra);
		puntos = new Array(rs.rows.length);
		indice = rs.rows.length;
                for (var i = 0; i < rs.rows.length; i++) {
                      var p = rs.rows.item(i);
		      fecha = convertir(p.Fecha);		      
		      puntos[i]={d:fecha, a:p.PorcentajeAvance, b:null};
		      //labels: ['Programado'];
                }
		
          }, errorCB);
        }, errorCB, successConsulta);
	
function successConsulta() {
	var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);

	db.transaction(function(tx) {
	    
	     tx.executeSql('SELECT * FROM  AvanceFisicoObras where cast(idObra as int) = ?',[idObra],  function(tx, rs) {
                //console.log("graficando avances: " + rs.rows.length);
                for (var i = 0; i < rs.rows.length; i++) {
                      var p = rs.rows.item(i);
		      fecha_2 = convertir(p.FechaReporte);
		      puntos[indice + i]={d:fecha_2, b:p.PAvanceFisico};
		      //labels: ['Real'];
                }
          }, errorCB);
        }, errorCB, succesConsulta2);
}

function errorCB(err) {
	console.log("Error al graficar avances processing SQL: "+err.code + " Message: "+err.message);
}

function succesConsulta2() {
	
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
	var indice;
	var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);

	db.transaction(function(tx) {
	     tx.executeSql('SELECT * FROM  Programa_Obra ' +
		     'where cast(idObra as int) = ? and TipoAvance = 1',[idObra],  function(tx, rs) {
                //console.log("graficando programas financiero: " + rs.rows.length + " obra: " +idObra);
		puntos2 = new Array(rs.rows.length);
		indice = rs.rows.length;
                for (var i = 0; i < rs.rows.length; i++) {
                      var p = rs.rows.item(i);
		      fecha = convertir(p.Fecha);
		      //console.log("fecha: " + fecha + " avance: "+p.PorcentajeAvance);
		      puntos2[i]={d:fecha, a:p.PorcentajeAvance, b:null};
                }
		
          }, errorCB);
        }, errorCB, successConsulta3);
	
	
function successConsulta3() {
	var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);

	db.transaction(function(tx) {
	    
	     tx.executeSql('SELECT * FROM  AvanceFinancieroObras where cast(idObra as int) = ?',[idObra],  function(tx, rs) {
                //console.log("graficando financiero: " + rs.rows.length);
                for (var i = 0; i < rs.rows.length; i++) {
                      var p = rs.rows.item(i);
		      fecha_2 = convertir(p.FechaReporte);
		      console.log("fecha de avance: " + fecha_2+" avance: "+p.PAvanceFinanciero);
		      puntos2[indice + i]={d:fecha_2, b:p.PAvanceFinanciero};
                }
          }, errorCB);
        }, errorCB, succesConsulta4);
}


function succesConsulta4() {
	
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
     //-----------------------------------------------------------------------
     db.transaction(function(tx) {
	     tx.executeSql('SELECT * FROM  Programa_Obra ' +
		     'where cast(idObra as int) = ? and TipoAvance = 0',[idObra],  function(tx, rs) {
                //console.log("graficando programas de tendencia: " + rs.rows.length);
		indice = rs.rows.length;
		puntos3 = new Array(rs.rows.length);//quite el 20
                for (var i = 0; i < rs.rows.length; i++) {
                      var p = rs.rows.item(i);
		      fecha = convertir(p.Fecha);
		      puntos3[i]={d:fecha, a:null, b:p.PorcentajeAvance};
		      //console.log("puntos de programa: " + iteracion);
		      //iteracion++;
                }
		
          }, errorCB);
        }, errorCB, successConsulta5);
     //-----------------------------------------------------------------------
     
function successConsulta5() {
	var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
	//-----------------------------------------------------------------------
	db.transaction(function(tx) {
	  tx.executeSql('SELECT * FROM  AvanceFisicoObras ' +
		     'where cast(idObra as int) = ?',[idObra],  function(tx, rs) {
	       var j = 1, k= 0;
	       var acumulador_1 = 0;
	       for(var i = 0; i < rs.rows.length; i++){
		    var p = rs.rows.item(i);
		    console.log("j: " + j +" fecha: " +p.FechaReporte+ " tendencia: "+p.Tendencia + " Avance fisico " + p.PAvanceFisico);
		    /*if (p.Tendencia == 1) {
		    //code
		    document.getElementById("line-example3").innerHTML = " No hay Tendencia";
	       }else{*/
		    fecha_1 = p.FechaReporte;
		    b = p.Tendencia;
		    k = k + j;
		    j++;
		    var acumulador = p.PAvanceFisico;
		    var acumulador_1 = acumulador_1+ acumulador;
		    
	       //}
		    
	       }
	       fecha_11 = convertir(fecha_1);
	       //console.log("fecha cenvertida: " + fecha_11);
	       var fechaadd = 0;
	       /*console.log("pendiente: " + b);
	       console.log("suma: " + acumulador_1);
	       console.log("indice: " + indice);
	       console.log("suma de j: " + k);*/
	       var promedio = acumulador_1 / indice;
	       var promedio1 = k /indice;
	       /*console.log("promedio de y: "+ promedio);
	       console.log("promedio de x: "+ promedio1);*/
	       a = promedio - (b* promedio1);
	       //console.log("valor de a: " + a);
	       day = 0;
	       //console.log("indice: " + indice);
	       var i = 0;
	       do{
		    tendencia= a + (b * iteracion);
		    tendencia_2 = Math.round(tendencia);
		    fechaadd = moment(fecha_11).add(day, 'd').format("YYYY-MM-DD");
		    //console.log("i: "+ i +" fecha: " + fechaadd +" resultado: " + tendencia_2);
		    //grafica(tendencia, fechaadd);
		    puntos3[indice + i]={d:fechaadd, a:tendencia_2};//linea para graficar
		    //console.log("d: " + puntos3[indice +i].d +" a: " + puntos3[indice +i].a);
		    //console.log("i: "+ i +" fecha: " + fechaadd +" resultado: " + tendencia_2 + " indice: " + indice+ " interaccion: "+iteracion);
		    //console.log("a: " + puntos3[i].a);
		    iteracion++;
		    i++;
		    day =day + 15;
	       }while(tendencia_2 < 100);
	       /*for(var i = 0; i < 20; i++){
		    tendencia= a + (b * iteracion);
		    tendencia_2 = Math.round(tendencia);
		    fechaadd = moment(fecha_11).add(day, 'd').format("YYYY-MM-DD");
		    console.log("i: "+ i +" fecha: " + fechaadd +" resultado: " + tendencia + " indice: " + indice+ " interaccion: "+iteracion);
		    //grafica(tendencia, fechaadd);
		    puntos3[indice + i]={d:fechaadd, a:tendencia_2};//linea para graficar
		    //console.log("d: " + puntos3[i].d +" a: " + puntos3[i].a);
		    //console.log("a: " + puntos3[i].a);
		    iteracion++;
		    day =day + 15;
	       }
	       */
	       
          }, errorCB);
        //}, errorCB, successConsulta5);
	}, errorCB, successConsulta6);
	//-----------------------------------------------------------------------
/*
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
*/
}
function successConsulta6() {
     
     //console.log("consulta realizada de Tendencia");
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
// funcion que convierte a la fecha en otro formato
//---------------------------- Grafica Tendencia Financiera Pendiente----------------------------------------
     var puntos4;
     var idObra = getGET().idObra;
     var indice_f;
     var tendencia_f = 0, iteracion_f = 1, a_f = 0, tendencia_3 = 0;
     
     var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
     
     db.transaction(function(tx) {
	  tx.executeSql('SELECT * FROM  Programa_Obra ' +
			'where cast(idObra as int) = ? and TipoAvance = 1',[idObra],  function(tx, rs) {
			 indice_f = rs.rows.length;
			 puntos4 = new Array(rs.rows.length);//quite el 20
			 for (var i = 0; i < rs.rows.length; i++) {
			      var p = rs.rows.item(i);
			      fecha = convertir(p.Fecha);
			      puntos4[i]={d:fecha, a:null, b:p.PorcentajeAvance};
			 }
	  }, errorCB);
     }, errorCB, successConsulta7);
     function successConsulta7() {
	  //code
	  var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
	//-----------------------------------------------------------------------
	db.transaction(function(tx) {
	  tx.executeSql('SELECT * FROM  AvanceFinancieroObras ' +
		     'where cast(idObra as int) = ?',[idObra],  function(tx, rs) {
	       var j = 1, k= 0;
	       var acumulador_1 = 0;
	       for(var i = 0; i < rs.rows.length; i++){
		    var p = rs.rows.item(i);
		    fecha_1 = p.FechaReporte;
		    b = p.Tendencia;
		    k = k + j;
		    j++;
		    var acumulador = p.PAvanceFinanciero;
		    var acumulador_1 = acumulador_1+ acumulador;
	       }
	       fecha_11 = convertir(fecha_1);
	       var fechaadd = 0;
	       var promedio = acumulador_1 / indice_f;
	       var promedio1 = k /indice_f;
	       a_f = promedio - (b* promedio1);
	       day = 0;
	       var i = 0;
	       do{
		    tendencia= a_f + (b * iteracion_f);
		    tendencia_3 = Math.round(tendencia);
		    fechaadd = moment(fecha_11).add(day, 'd').format("YYYY-MM-DD");
		    puntos4[indice_f + i]={d:fechaadd, a:tendencia_3};//linea para graficar
		    iteracion_f++;
		    i++;
		    day =day + 15;
	       }while(tendencia_3 < 100);
          }, errorCB);
        //}, errorCB, successConsulta5);
	}, errorCB, successConsulta8);
     }
     function successConsulta8() {
	  //code
	  console.log("se imprime la grafica");
	   Morris.Line({
	     element: 'line-example4',
	     data: puntos4,
             xkey: 'd',
	     ykeys: ['a', 'b'],
	     labels: ['Tendencia financiera', 'Programa'],
	     //lineColors:['#0090d9','#000000'],
	     lineColors:['#464646','#AE0C00'],
	     });
     }
//---------------------------- Grafica Tendencia Financiera fin ----------------------------------------
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