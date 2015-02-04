$(document).ready(function() {
	
	var puntos, fecha, fecha_2;
	var idProyecto = getGET().idProyecto;
	var programa;
	var indice;
	var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
	
	db.transaction(function(tx) {
	     tx.executeSql('SELECT * FROM  Programa_Proyecto ' +
		     'where cast(idProyecto as int) = ? and TipoAvance = 0',[idProyecto],  function(tx, rs) {
                //console.log("graficando programas de Proyecto: " + rs.rows.length);
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
	    
	     tx.executeSql('SELECT * FROM  AvanceFisicoProyectos where cast(idProyecto as int) = ?',[idProyecto],  function(tx, rs) {
                //console.log("graficando avances: " + rs.rows.length);
                for (var i = 0; i < rs.rows.length; i++) {
                      var p = rs.rows.item(i);
		      fecha_2 = convertir(p.FechaReporte);
		      puntos[indice + i]={d:fecha_2, b:p.PAvanceFisico};
                }
          }, errorCB);
        }, errorCB, succesConsulta2);
}
function succesConsulta2(args) {
	
	Morris.Line({
	     element: 'line-example',
	     data: puntos,
             xkey: 'd',
	     ykeys: ['a', 'b'],
	     labels: ['Programado', 'Real'],
	     lineColors:['#464646','#AE0C00'],
	     });
}
// Grafica de Avance Financiero
	var puntos2;//puntos para la grafica de avance financiero
	var idProyecto = getGET().idProyecto;
	var fecha_avance, fecha_programa;
	var programa;
	var avance_fisico, avance_programado;
	var indice;
	var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);

	db.transaction(function(tx) {
	     tx.executeSql('SELECT * FROM  Programa_Proyecto ' +
		     'where cast(idProyecto as int) = ? and TipoAvance = 1',[idProyecto],  function(tx, rs) {
                //console.log("graficando programas: " + rs.rows.length);
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
	    
	     tx.executeSql('SELECT * FROM  AvanceFinancieroProyectos where cast(idProyecto as int) = ?',[idProyecto],  function(tx, rs) {
                //console.log("graficando avances: " + rs.rows.length);
                for (var i = 0; i < rs.rows.length; i++) {
                      var p = rs.rows.item(i);
		      fecha_2 = convertir(p.FechaReporte);
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
	     lineColors:['#464646','#AE0C00'],
	     });
}

function errorCB(err) {
           console.log("Error al graficar avances processing SQL: "+err.code + " Message: "+err.message);
}
//Tendencia
	var puntos3;//puntos para la grafica de avance financiero
	var idProyecto = getGET().idProyecto;
	var indice;
	
	var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
	
	db.transaction(function(tx) {
	     tx.executeSql('SELECT * FROM  Programa_Proyecto ' +
		     'where cast(idProyecto as int) = ? and TipoAvance = 0',[idProyecto],  function(tx, rs) {
                //console.log("graficando programas de Proyecto: " + rs.rows.length);
		puntos3 = new Array(rs.rows.length);
		indice = rs.rows.length;
                for (var i = 0; i < rs.rows.length; i++) {
                      var p = rs.rows.item(i);
		      fecha = convertir(p.Fecha);
		      //console.log("fecha: "+fecha+" Avance Fisico:"+p.PorcentajeAvance);
		      puntos3[i]={d:fecha, b:p.PorcentajeAvance};
                }
		
          }, errorCB);
        }, errorCB, successConsulta5);
	
	function successConsulta5() {
		
		var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
		var a = 0,b = 0, j = 1, k = 0, acumulador = 0, acumulador2 = 0, fechadd = 0, day = 0, promedio_x = 0, promedio_y = 0, tendencia = 0, tendencia2 = 0, iteracion = 0;

		db.transaction(function(tx) {
		     tx.executeSql('SELECT * FROM  AvanceFisicoProyectos where cast(idProyecto as int) = ?',[idProyecto],  function(tx, rs) {
		        console.log("graficando avances: " + rs.rows.length);
		        for (var i = 0; i < rs.rows.length; i++) {
		              var p = rs.rows.item(i);
			      /*if (p.Tendencia == 1) {
				//code
				document.getElementById("line-example3").innerHTML = " No hay Tendencia";
			      }else{*/
				fecha_2 = p.FechaReporte;
				b = p.Tendencia;
				k = k + j;
				j++;
				acumulador = p.PAvanceFisico;
				acumulador2 = acumulador2 + acumulador;
			      //}
			      
			}
			fecha2 = convertir(fecha_2);
			promedio_x = k/indice;
			promedio_y = acumulador2/indice;
			//console.log("fecha de prueba: "+fecha2);
			a = promedio_y - (b *promedio_x);
			var i = 0;
			do{
				tendencia= a + (b * iteracion);
				tendencia2 = Math.round(tendencia);
				fechaadd = moment(fecha2).add(day, 'd').format("YYYY-MM-DD");
				//console.log("i: "+ i +" fecha: " + fechaadd +" resultado: " + tendencia2);
				puntos3[indice + i]={d:fechaadd, a:tendencia2};//linea para graficar
				iteracion++;
				i++;
				day = day + 15;
			}while(tendencia2 < 100);
		     }, errorCB);
		}, errorCB, succesConsulta6);
	}
	function succesConsulta6() {
	
		Morris.Line({
		     element: 'line-example3',
		     data: puntos3,
	             xkey: 'd',
		     ykeys: ['a', 'b'],
		     labels: ['Programado', 'Tendencia'],
		     lineColors:['#464646','#AE0C00'],
		});
	}

});
//-----------------------------------------Tendencia Financiera del proyecto----------------------------------------------------------------------------

	var puntos4;//puntos para la grafica de avance financiero
	var idProyecto = getGET().idProyecto;
	var indice;

	var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
	
	db.transaction(function(tx) {
	     tx.executeSql('SELECT * FROM  Programa_Proyecto ' +
		     'where cast(idProyecto as int) = ? and TipoAvance = 1',[idProyecto],  function(tx, rs) {
                //console.log("graficando programas de Proyecto: " + rs.rows.length);
		puntos4 = new Array(rs.rows.length);
		indice = rs.rows.length;
                for (var i = 0; i < rs.rows.length; i++) {
                      var p = rs.rows.item(i);
		      fecha = convertir(p.Fecha);
		      //console.log("fecha: "+fecha+" Avance Fisico:"+p.PorcentajeAvance);
		      puntos4[i]={d:fecha, b:p.PorcentajeAvance};
                }
		
          }, errorCB);
        }, errorCB, successConsulta7);
	function successConsulta7() {
		//code
		var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
		var a = 0,b = 0, j = 1, k = 0, acumulador = 0, acumulador2 = 0, fechadd = 0, day = 0, promedio_x = 0, promedio_y = 0, tendencia = 0, tendencia2 = 0, iteracion = 0;

		db.transaction(function(tx) {
		     tx.executeSql('SELECT * FROM  AvanceFinancieroProyectos where cast(idProyecto as int) = ?',[idProyecto],  function(tx, rs) {
		        console.log("graficando avances: " + rs.rows.length);
		        for (var i = 0; i < rs.rows.length; i++) {
		              var p = rs.rows.item(i);
				fecha_2 = p.FechaReporte;
				b = p.Tendencia;
				k = k + j;
				j++;
				acumulador = p.PAvanceFinanciero;
				acumulador2 = acumulador2 + acumulador;
			      //}
			      
			}
			//if (b == 0) {
				//code
			//	console.log("la tendencia es de 0");
			//	fechaadd = moment(fecha2).add(day, 'd').format("YYYY-MM-DD");
			//	puntos4[0]={d:fechaadd, a:0};//linea para graficar
				
			//}else{
				fecha2 = convertir(fecha_2);
				promedio_x = k/indice;
				promedio_y = acumulador2/indice;
			//console.log("fecha de prueba: "+fecha2);
				a = promedio_y - (b *promedio_x);
				var i = 0;
				do{
					tendencia= a + (b * iteracion);
					tendencia2 = Math.round(tendencia);
					fechaadd = moment(fecha2).add(day, 'd').format("YYYY-MM-DD");
				//console.log("i: "+ i +" fecha: " + fechaadd +" resultado: " + tendencia2);
					puntos4[indice + i]={d:fechaadd, a:tendencia2};//linea para graficar
					iteracion++;
					i++;
					day = day + 15;
				}while(tendencia2 < 100);
			//}
			
		     }, errorCB);
		}, errorCB, successConsulta8);
	}
	function successConsulta8() {
		//code
		console.log("se pinta la grafica de tendencia");
		Morris.Line({
		     element: 'line-example4',
		     data: puntos4,
	             xkey: 'd',
		     ykeys: ['a', 'b'],
		     labels: ['Programado', 'Tendencia'],
		     lineColors:['#464646','#AE0C00'],
		});
	}
	
//---------------------------------------------------------------------------------------------------------------------
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