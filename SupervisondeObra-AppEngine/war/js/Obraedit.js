var borrador;
var id_ubicacion;

var idObra;
var idProyecto;
function init() {
//	var x = document.getElementById("editObra_obraselect");
	var loc = document.location.href;
    var getString = loc.split('?')[1];
    var GET = getString.split('&');
 
    for(var i = 0, l = GET.length; i < l; i++){
       var tmp = GET[i].split('=');
       idObra=tmp[1];
    }
	
	limpiar();
	var opcionobra = 'opcionobra:'+ idObra;
	if(idObra != 0){
		$.ajax({
			url : '/editobra',
			type : 'post',
			data : {'objectJson' : JSON.stringify(opcionobra)},
			success: function(data){
				if(data.ImporteConvenioAmpliacion!=null){
			  	    document.getElementById("editObra_ImporteConvenioAmpliacion").value = data.ImporteConvenioAmpliacion;
				}if(data.ImporteConvenioReduccion!=null){
			  	    document.getElementById("editObra_ImporteConvenioReduccion").value = data.ImporteConvenioReduccion;
				}if(data.ImporteAjusteCostos!=null){
			  	    document.getElementById("editObra_ImporteAjusteCostos").value = data.ImporteAjusteCostos;
				}if(data.porcentajeDesvio!=null){
			  	    document.getElementById("PorcentajeAnticipo").value = data.porcentajeDesvio;
				}
				mostrarSelect(data.idSecretaria,data.idDependencia,data.idGobierno,data.id_EmpresaContratista,data.idUsuario);
				borrador = data.Borrador;
				id_ubicacion = data.id_ubicacion;
				idProyecto = data.id_Poyecto;
				document.getElementById("editObra_NoContrato").value = data.NoContrato;
		  	    document.getElementById("editObra_Direccion").value = data.Direccion;
		  	    document.getElementById("editObra_Subdireccion").value = data.Subdireccion;
		  	    document.getElementById("editObra_Area").value = data.Area;
		  	    document.getElementById("editObra_Superintendente").value = data.Superintendente;
		  	    document.getElementById("editObra_EntidadFederativa").value = data.EntidadFederativa;
		  	    document.getElementById("editObra_Nombre").value = data.Nombre;
		  	    document.getElementById("editObra_Descripcion").value = data.Descripcion;
		  	    document.getElementById("editObra_FechaContrato").value = data.FechaContrato;
			    document.getElementById("editObra_TipoContrato").value = data.TipoContrato;
		  	    document.getElementById("editObra_ImporteContratoSinIVA").value = accounting.formatMoney(data.ImporteContratoSinIVA);
		  	    document.getElementById("editObra_NombreEjercicioFiscal1").value = data.NombreEjercicioFiscal1;
		  	    document.getElementById("editObra_ImporteFiscal1SinIVA").value = data.ImporteFiscal1SinIVA;
		  	    document.getElementById("editObra_FechaInicioContrato").value = data.FechaInicioContrato;
		  	    document.getElementById("editObra_FechaTerminoContrato").value = data.FechaTerminoContrato;
			    document.getElementById("editObra_PeriodoEjucionDias").value = data.PeriodoEjucionDias;
		  	    document.getElementById("editObra_PartidaPresupuestal").value = data.PartidaPresupuestal;
		  	    document.getElementById("editObra_Anticipo").value = accounting.formatMoney(data.Anticipo);
		  	    document.getElementById("editObra_NoFianzaAnticipo").value = data.NoFianzaAnticipo;
		  	    document.getElementById("editObra_FechaFianzaAnticipo").value = data.FechaFianzaAnticipo;
		  	    document.getElementById("editObra_MontoFianzaAnticipo").value = data.MontoFianzaAnticipo;
		  	    document.getElementById("editObra_NoFianzaCumplimiento").value = data.NoFianzaCumplimiento;
		  	    document.getElementById("editObra_FechaFianzaCumplimiento").value = data.FechaFianzaCumplimiento;
		  	    document.getElementById("editObra_MontoFianzaCumplimiento").value = data.MontoFianzaCumplimiento;
			    document.getElementById("editObra_CargoRevision1").value = data.CargoRevision1;
		  	    document.getElementById("editObra_NombreRevision1").value = data.NombreRevision1;
		  	    document.getElementById("editObra_CargoRevision2").value = data.CargoRevision2;
		  	    document.getElementById("editObra_NombreRevision2").value = data.NombreRevision2;
		  	    document.getElementById("editObra_NombreQuienAutoriza").value = data.NombreQuienAutoriza;
		  	    document.getElementById("editObra_CargoVoBo").value = data.CargoVoBo;
		  	    document.getElementById("editObra_NombreVoBo").value = data.NombreVoBo;
		  	    document.getElementById("editObra_limiteDesvio").value = data.Limite_Desvio;
		  	  $.ajax({
					url : '/editobra',
					type : 'post',
					data : {'objectJson' : JSON.stringify("ubicacion:-"+id_ubicacion)},
					success: function(data){
						iniciamapa(data);
					},
					error: function(jHR,e,throwsError){
						alert(e);
					}			
				});	
				
			},
			error: function(jHR,e,throwsError){
				alert(e);
			}			
		});	
	}
}

function editObra_enviar() {
//	var x = document.getElementById("editObra_proyectoselect");
//	var y = document.getElementById("editObra_obraselect");
	var a = document.getElementById("editObra_secretariaselect");
	var b = document.getElementById("editObra_dependenciaselect");
	var c = document.getElementById("editObra_gobiernoselect");
	var d = document.getElementById("editObra_contratistaselect");
	var e = document.getElementById("editObra_supervisorselect");
	
	if(document.getElementById("editObra_NoContrato").value.trim()==""|
//	    document.getElementById("editObra_Direccion").value.trim()==""|
//	    document.getElementById("editObra_Subdireccion").value.trim()==""|
//	    document.getElementById("editObra_Area").value.trim()==""|
	    document.getElementById("editObra_Superintendente").value.trim()==""|
	    document.getElementById("editObra_EntidadFederativa").value.trim()==""|
	    document.getElementById("editObra_Nombre").value.trim()==""|
	    document.getElementById("editObra_Descripcion").value.trim()==""|
	    document.getElementById("editObra_FechaContrato").value.trim()==""|
	    document.getElementById("editObra_TipoContrato").value.trim()==""|
	//    document.getElementById("editObra_ImporteContratoSinIVA").value.trim()==""|
	    document.getElementById("editObra_NombreEjercicioFiscal1").value.trim()==""|
	    document.getElementById("editObra_ImporteFiscal1SinIVA").value.trim()==""|
//	    document.getElementById("editObra_ImporteConvenioAmpliacion").value.trim()==""|
//	    document.getElementById("editObra_ImporteConvenioReduccion").value.trim()==""|
//	    document.getElementById("editObra_ImporteAjusteCostos").value.trim()==""|
	    document.getElementById("editObra_FechaInicioContrato").value.trim()==""|
	    document.getElementById("editObra_FechaTerminoContrato").value.trim()==""|
	    document.getElementById("editObra_PeriodoEjucionDias").value.trim()==""|
	    document.getElementById("editObra_PartidaPresupuestal").value.trim()==""|
//	    document.getElementById("editObra_Anticipo").value.trim()==""|
	    document.getElementById("editObra_NoFianzaAnticipo").value.trim()==""|
	    document.getElementById("editObra_FechaFianzaAnticipo").value.trim()==""|
	    document.getElementById("editObra_MontoFianzaAnticipo").value.trim()==""|
	    document.getElementById("editObra_NoFianzaCumplimiento").value.trim()==""|
	    document.getElementById("editObra_FechaFianzaCumplimiento").value.trim()==""|
	    document.getElementById("editObra_MontoFianzaCumplimiento").value.trim()==""|
//	    document.getElementById("editObra_CargoRevision1").value.trim()==""|
//	    document.getElementById("editObra_NombreRevision1").value.trim()==""|
//	    document.getElementById("editObra_CargoRevision2").value.trim()==""|
//	    document.getElementById("editObra_NombreRevision2").value.trim()==""|
	    document.getElementById("editObra_NombreQuienAutoriza").value.trim()==""|
//	    document.getElementById("editObra_CargoVoBo").value.trim()==""|
//	    document.getElementById("editObra_NombreVoBo").value.trim()==""|
//	    document.getElementById("PorcentajeAnticipo").value.trim()==""|
	    document.getElementById("editObra_limiteDesvio").value.trim()==""){		  	    
	  	alert('Ingrese todos los valores antes de guardar los cambio');
    }else {
		
	if( !parseInt(document.getElementById("editObra_ImporteFiscal1SinIVA").value))
			alert("ImporteFiscal1SinIVA debe ser un número");
		else if(document.getElementById("editObra_ImporteConvenioAmpliacion").value.trim()!="" && !parseInt(document.getElementById("editObra_ImporteConvenioAmpliacion").value.trim()))
			alert("ImporteConvenioAmpliacion debe ser un número");
		else if(document.getElementById("editObra_ImporteConvenioReduccion").value.trim()!="" && !parseInt(document.getElementById("editObra_ImporteConvenioReduccion").value.trim()))
			alert("ImporteConvenioReduccion debe ser un número");
		else if(document.getElementById("editObra_ImporteAjusteCostos").value.trim()!="" && !parseInt(document.getElementById("editObra_ImporteAjusteCostos").value.trim()))
			alert("ImporteAjusteCostos debe ser un número");
		else if( !parseInt(document.getElementById("editObra_PeriodoEjucionDias").value))
			alert("PeriodoEjucionDias debe ser un número");
		else if( !parseInt(document.getElementById("editObra_PartidaPresupuestal").value))
			alert("PartidaPresupuestal debe ser un número");
//		else if(document.getElementById("editObra_Anticipo").value.trim()!="" && !parseInt(document.getElementById("editObra_Anticipo").value.trim()))
//			alert("Anticipo debe ser un número");
		else if( !parseInt(document.getElementById("editObra_NoFianzaAnticipo").value))
			alert("NoFianzaAnticipo debe ser un número");
		else if( !parseInt(document.getElementById("editObra_MontoFianzaAnticipo").value))
			alert("MontoFianzaAnticipo debe ser un número");
		else if( !parseInt(document.getElementById("editObra_NoFianzaCumplimiento").value))
			alert("NoFianzaCumplimiento debe ser un número");
		else if( !parseInt(document.getElementById("editObra_MontoFianzaCumplimiento").value))
			alert("MontoFianzaCumplimiento debe ser un número");
		else if( !parseInt(document.getElementById("editObra_limiteDesvio").value))
			alert("Limite de Desvio debe ser un número");
		else if(!validate_fechaMayorQue($('#editObra_FechaInicioContrato').val().trim(),$('#editObra_FechaTerminoContrato').val().trim()))
			alert("La fecha de Termino de Contrato "+$('#editObra_FechaTerminoContrato').val().trim()+" NO es superior a la fecha de inicio de contrato "+$('#editObra_FechaInicioContrato').val().trim());
		else {
			var as = $('#editObra_ImporteContratoSinIVA').val().trim().replace("$","").replace(",","");
			var er = $('#editObra_Anticipo').val().trim().replace("$","").split('.').join('');
			var object = {
					'id_Poyecto'	  		: idProyecto,
					'id_Obra'		 		: idObra,
					'idSecretaria'			: a.value,
					'idDependencia'			: b.value,
					'idGobierno'	  		: c.value,
					'id_EmpresaContratista' : d.value,
					'idUsuario'	  			: e.value,
					'NoContrato'  			: $('#editObra_NoContrato').val().trim(),
					'Nombre'  				: $('#editObra_Nombre').val().trim(),
					'Direccion' 	  		: $('#editObra_Direccion').val().trim(),
					'Subdireccion'		  	: $('#editObra_Subdireccion').val().trim(),
					'Area'	  				: $('#editObra_Area').val().trim(),
					'Superintendente'		: $('#editObra_Superintendente').val().trim(),
					'EntidadFederativa'		: $('#editObra_EntidadFederativa').val().trim(),
					'Descripcion'			: $('#editObra_Descripcion').val().trim(),
					'FechaContrato'	  		: $('#editObra_FechaContrato').val().trim(),
					'TipoContrato'    		: $('#editObra_TipoContrato').val().trim(),
					'ImporteContratoSinIVA'	: $('#editObra_ImporteContratoSinIVA').val().trim().replace("$","").split(',').join(''),
					'NombreEjercicioFiscal1': $('#editObra_NombreEjercicioFiscal1').val().trim(),
					'ImporteFiscal1SinIVA'  : $('#editObra_ImporteFiscal1SinIVA').val().trim(),
					'ImporteConvenioAmpliacion': $('#editObra_ImporteConvenioAmpliacion').val().trim(),
					'ImporteConvenioReduccion' : $('#editObra_ImporteConvenioReduccion').val().trim(),
					'ImporteAjusteCostos'	: $('#editObra_ImporteAjusteCostos').val().trim(),
					'FechaInicioContrato'	: $('#editObra_FechaInicioContrato').val().trim(),
					'FechaTerminoContrato'	: $('#editObra_FechaTerminoContrato').val().trim(),
					'PeriodoEjucionDias'	: $('#editObra_PeriodoEjucionDias').val().trim(),
					'PartidaPresupuestal'	: $('#editObra_PartidaPresupuestal').val().trim(),
					'Anticipo'    			: $('#editObra_Anticipo').val().trim().replace("$","").split(',').join(''),
					'NoFianzaAnticipo'	 	: $('#editObra_NoFianzaAnticipo').val().trim(),
					'FechaFianzaAnticipo'   : $('#editObra_FechaFianzaAnticipo').val().trim(),
					'MontoFianzaAnticipo' 	: $('#editObra_MontoFianzaAnticipo').val().trim(),
					'NoFianzaCumplimiento'  : $('#editObra_NoFianzaCumplimiento').val().trim(),
					'FechaFianzaCumplimiento': $('#editObra_FechaFianzaCumplimiento').val().trim(),
					'MontoFianzaCumplimiento': $('#editObra_MontoFianzaCumplimiento').val().trim(),
					'CargoRevision1'		: $('#editObra_CargoRevision1').val().trim(),
					'NombreRevision1'		: $('#editObra_NombreRevision1').val().trim(),
					'CargoRevision2'	    : $('#editObra_CargoRevision2').val().trim(),
					'NombreRevision2'    	: $('#editObra_NombreRevision2').val().trim(),
					'NombreQuienAutoriza'	: $('#editObra_NombreQuienAutoriza').val().trim(),
					'CargoVoBo'  			: $('#editObra_CargoVoBo').val().trim(),
					'NombreVoBo'  			: $('#editObra_NombreVoBo').val().trim(),
					'Limite_Desvio'			: $('#editObra_limiteDesvio').val().trim(),
					'Borrador'				: borrador,
					'id_ubicacion'			: id_ubicacion,
					'datosArrayUbicacion'   :puntos,
					'porcentajeDesvio'  	:  $('#PorcentajeAnticipo').val().trim()
					
				};

			$.ajax({
				url : '/editobra',
				type : 'post',
				data : {
					'objectJson' : JSON.stringify(object)
				},
				success : function(data) {
					if(data){
						 $.confirm({
								'title'		: 'Confirmaci&oacute;n',
								'message'	: 'Obra editada correctamente. <br />¿Desea asignar el directorio a la obra ahora?',
								'buttons'	: {
									'Otro Momento'	: {
										'class'	: 'gray',
										'action': function(){
											location.href="ListaObras.html";
										}
									},
									'Aceptar'	: {
										'class'	: 'blue',
										'action': function(){
											location.href="Directorioedit.html?idObra="+idObra+"="+1;
										}
									}
								}
							});
						 
//						 if (confirm("Obra editada correctamente, Desea asignar el directorio a la obra ahora?") == true) {
//						    	location.href="Directorioedit.html?idObra="+idObra+"="+1;
//
//						    } else {
//						    	location.href="ListaObras.html";
//						    }
					 }else{
						 alert(data); 
						 window.location.reload();
					 }
				},
				error : function(jHR, e, throwsError) {
					alert(e);
					window.location.reload();
				}

			});
		}
	}
}

function limpiar(){
	$('#editObra_secretariaselect option').remove();
	$('#editObra_dependenciaselect option').remove();
	$('#editObra_gobiernoselect option').remove();
	$('#editObra_contratistaselect option').remove();
	$('#editObra_supervisorselect option').remove();
	
	document.getElementById("editObra_NoContrato").value = "";
    document.getElementById("editObra_Direccion").value = "";
    document.getElementById("editObra_Subdireccion").value = "";
    document.getElementById("editObra_Area").value = "";
    document.getElementById("editObra_Superintendente").value = "";
    document.getElementById("editObra_EntidadFederativa").value = "";
    document.getElementById("editObra_Nombre").value = "";
    document.getElementById("editObra_Descripcion").value = "";
    document.getElementById("editObra_FechaContrato").value = "";
    document.getElementById("editObra_TipoContrato").value = "";
    document.getElementById("editObra_ImporteContratoSinIVA").value = "";
    document.getElementById("editObra_NombreEjercicioFiscal1").value = "";
    document.getElementById("editObra_ImporteFiscal1SinIVA").value = "";
    document.getElementById("editObra_ImporteConvenioAmpliacion").value = "";
    document.getElementById("editObra_ImporteConvenioReduccion").value = "";
    document.getElementById("editObra_ImporteAjusteCostos").value = "";
    document.getElementById("editObra_FechaInicioContrato").value = "";
    document.getElementById("editObra_FechaTerminoContrato").value = "";
    document.getElementById("editObra_PeriodoEjucionDias").value = "";
    document.getElementById("editObra_PartidaPresupuestal").value = "";
    document.getElementById("editObra_Anticipo").value = "";
    document.getElementById("editObra_NoFianzaAnticipo").value = "";
    document.getElementById("editObra_FechaFianzaAnticipo").value = "";
    document.getElementById("editObra_MontoFianzaAnticipo").value = "";
    document.getElementById("editObra_NoFianzaCumplimiento").value = "";
    document.getElementById("editObra_FechaFianzaCumplimiento").value = "";
    document.getElementById("editObra_MontoFianzaCumplimiento").value = "";
    document.getElementById("editObra_CargoRevision1").value = "";
    document.getElementById("editObra_NombreRevision1").value = "";
    document.getElementById("editObra_CargoRevision2").value = "";
    document.getElementById("editObra_NombreRevision2").value = "";
    document.getElementById("editObra_NombreQuienAutoriza").value = "";
    document.getElementById("editObra_CargoVoBo").value = "";
    document.getElementById("editObra_NombreVoBo").value = "";
    document.getElementById("editObra_limiteDesvio").value = "";
    document.getElementById("PorcentajeAnticipo").value = "";
}

function mostrarSelect(ida, idb, idc, idd, ide ){
	var x = document.getElementById("editObra_obraselect");
	var a = document.getElementById("editObra_secretariaselect");
	var b = document.getElementById("editObra_dependenciaselect");
	var c = document.getElementById("editObra_gobiernoselect");
	var d = document.getElementById("editObra_contratistaselect");
	var e = document.getElementById("editObra_supervisorselect");
	
	var opcionselect = 'opcionselect';
	var opcionsupervisor = 'opcionsupervisor';
	$.ajax({
		url : '/editobra',
		type : 'post',
		data : {'objectJson' : JSON.stringify(opcionselect)},
		success: function(data){
			for (var t = 0; t < data.length; t++) {
				if(data[t].tipoEmpresa == "Secretaria"){
					var option = document.createElement("option");
					option.text = data[t].nombre;
					option.value = data[t].id;
					a.add(option);
					if(data[t].id == ida){
						$("#editObra_secretariaselect option[value="+ida+"]").attr("selected",true);
					}
				}else if(data[t].tipoEmpresa == "Dependencia"){
					var option = document.createElement("option");
					option.text = data[t].nombre;
					option.value = data[t].id;
					b.add(option);
					if(data[t].id == idb){
						$("#editObra_dependenciaselect option[value="+idb+"]").attr("selected",true);
					}
				}else if(data[t].tipoEmpresa == "Gobierno"){
					var option = document.createElement("option");
					option.text = data[t].nombre;
					option.value = data[t].id;
					c.add(option);
					if(data[t].id == idc){
						$("#editObra_gobiernoselect option[value="+idc+"]").attr("selected",true);
					}
				}else if(data[t].tipoEmpresa == "Contratista"){
					var option = document.createElement("option");
					option.text = data[t].nombre;
					option.value = data[t].id;
					d.add(option);
					if(data[t].id == idd){
						$("#editObra_contratistaselect option[value="+idd+"]").attr("selected",true);
					}
				}
			}
		},
		error: function(jHR,e,throwsError){
			alert(e);
		}			
	});
	$.ajax({
		url : '/editobra',
		type : 'post',
		data : {'objectJson' : JSON.stringify(opcionsupervisor)},
		success: function(data){
			for(var q=0;q<data.length;q++){
				var option = document.createElement("option");
				option.value = data[q].id;
				option.text = data[q].nombre;
				e.add(option);
				if(data[q].id == ide){
					e.options[q].selected = true;
				}
			}
		},
		error: function(jHR,e,throwsError){
			alert(e);
		}			
	});
}

$(function () {
	$("#editObra_FechaContrato").datepicker();
	});
$(function () {
	$("#editObra_FechaInicioContrato").datepicker();
	});
$(function () {
	$("#editObra_FechaTerminoContrato").datepicker();
	});
$(function () {
	$("#editObra_FechaFianzaAnticipo").datepicker();
	});
$(function () {
	$("#editObra_FechaFianzaCumplimiento").datepicker();
	});


function justNumbers(e){
	var keynum = window.event ? window.event.keyCode : e.which;
	if ((keynum == 8) || (keynum == 46))
	return true;
	 
	return /\d/.test(String.fromCharCode(keynum));
	}

	function calcular(){
		var porcentaje = $('#PorcentajeAnticipo').val().trim();
		var importe = $('#editObra_ImporteContratoSinIVA').val().trim();
		var anticipo = "";
		if(porcentaje != "" && !isNaN(porcentaje) && importe !="" && !isNaN(importe)){
			anticipo = redondeodecimales(porcentaje * importe);
			document.getElementById("editObra_Anticipo").value = anticipo;
		}else{
			document.getElementById("editObra_Anticipo").value = "";
		}
	}

	function redondeodecimales(numero){
		var flotante = parseFloat(numero);
		var resultado = Math.round(flotante*100)/100;
		return resultado;
	}
	
	
	function calcularEjecusionDias(){
		var fechaInicio = $('#editObra_FechaInicioContrato').val().trim();
		var fechaFin = $('#editObra_FechaTerminoContrato').val().trim();
		
		if(fechaInicio != "" && fechaFin !=""){
			var dias = restaFechas(fechaInicio, fechaFin);
			document.getElementById("editObra_PeriodoEjucionDias").value = dias;
		}else{
			document.getElementById("editObra_PeriodoEjucionDias").value = "";
		}
	}

	restaFechas = function(f1,f2){
		var aFecha1 = f1.split('/'); 
		var aFecha2 = f2.split('/'); 
		var fFecha1 = Date.UTC(aFecha1[2],aFecha1[1]-1,aFecha1[0]); 
		var fFecha2 = Date.UTC(aFecha2[2],aFecha2[1]-1,aFecha2[0]); 
		var dif = fFecha2 - fFecha1;
		var dias = Math.floor(dif / (1000 * 60 * 60 * 24)); 
		return dias;
	}

	function validate_fechaMayorQue(fechaInicial,fechaFinal){
	    valuesStart=fechaInicial.split("/");
	    valuesEnd=fechaFinal.split("/");

	    // Verificamos que la fecha no sea posterior a la actual
	    var dateStart=new Date(valuesStart[2],(valuesStart[1]-1),valuesStart[0]);
	    var dateEnd=new Date(valuesEnd[2],(valuesEnd[1]-1),valuesEnd[0]);
	    if(dateStart>=dateEnd)
	    {
	        return 0;
	    }
	    return 1;
	}

