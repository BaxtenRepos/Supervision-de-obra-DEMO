/**
 * 
 */

	var avanceFinanciero = new Array();
	var avanceFisico= new Array();
	var catContactos= new Array();
	var catPersonal= new Array();
	var catTipoEmpresa= new Array();
	var catTipoMaquinaria= new Array();
	var catTipoPersona= new Array();
	var concepto= new Array();
	var deviceInfo= new Array();
	var directivoProyecto= new Array();
	var directorio= new Array();
	var empresa= new Array();
	var estimacion= new Array();
	var maquinaria= new Array();
	var message= new Array();
	var multimedia= new Array();
	var notas= new Array();
	var obra= new Array();
	var orgGubernamental= new Array();
	var persona= new Array();
	var programa= new Array();
	var proyecto= new Array();
	var push= new Array();
	var refCalendarizacion= new Array();
	var repMaqCatMaq= new Array();
	var repoPersonalCatPersonal= new Array();
	var reporteDiario= new Array();
	var sincronizacion= new Array();
	var ubicaciones= new Array();
	var usuario= new Array();
	var proyectos = new Array();
	var obras = new Array();
	var obrastamanio = 0;
	var iproyecto=0;
	
	
	
	var ROOT = 'https://1-dot-iuyet-csgit-cao.appspot.com/_ah/api';
	
	
	
	var ROOT = 'https://1-dot-iuyet-csgit-cao.appspot.com/_ah/api';
	var nombres = new Array("avanceFinanciero","avanceFisico","catContactos","catPersonal","catTipoEmpresa","catTipoMaquinaria",
			"catTipoPersona","concepto","deviceInfo","directivoProyecto","directorio","empresa","estimacion",
			"maquinaria","message","multimedia","notas","obra","orgGubernamental","persona","programa","proyecto",
			"push","refCalendarizacion","repMaqCatMaq","repoPersonalCatPersonal","reporteDiario","sincronizacion","ubicaciones","usuario");
//	{"avanceFinanciero","avanceFisico","catContactos","catPersonal","catTipoEmpresa","catTipoMaquinaria",
//			"catTipoPersona","concepto","deviceInfo","directivoProyecto","directorio","empresa","estimacion",
//			"maquinaria","message","multimedia","notas","obra","orgGubernamental","persona","programa","proyecto",
//			"push","refCalendarizacion","repMaqCatMaq","repoPersonalCatPersonal","reporteDiario","sincronizacion","ubicaciones","usuario"};
//		

function respalda(){
	
	
	
}
function inserta2(x,datos){
	//var ROOT = 'https://1-dot-prueba-xmpp-2.appspot.com/_ah/api';;
	   gapi.client.load('communicationchannel', 'v1', function(){
	    	
		   
		    	
		    	gapi.client.communicationchannel.listObra(
		    	{
		    		idObra:"0",
    				idProyecto:"0"	
		    		
		    	}		
		    	).execute(function(resp){
		            if(resp.message=="javax.persistence.EntityExistsException: Object already exists") {
		            	inserta2(x+1,datos);
		            }else{
		               //EmpresaItems = resp.items;
		               //console.log("EmpresaItems: " + EmpresaItems.length);
		            	inserta2(x+1,datos)
		            }
		            
		    	
		          });//list
		    	
		}, 'https://1-dot-prueba-xmpp-2.appspot.com/_ah/api');
	
}
function inserta(){
	
	var ROOT = 'https://1-dot-iuyet-cao-csgit.appspot.com/_ah/api';
	   gapi.client.load('communicationchannel', 'v1', function(){
	    	
		   
		    	
		    	gapi.client.communicationchannel.listObra(
		    			{
		    				idObra:"0",
		    				idProyecto:"0"
		    				
		    			}).execute(function(resp){
		            if(resp.message=="javax.persistence.EntityExistsException: Object already exists") {
		            	
		            }else{
		               //EmpresaItems = resp.items;
		               //console.log("EmpresaItems: " + EmpresaItems.length);
		            	inserta2(0,resp.items)
		            }
		            
		    	
		          });//list
		    	
		},ROOT);
//	
	
//	var tamaño=0;
//	
//	 
//	
//
//    
//	alert("llegue a inserta");
//	
//	$.ajax({
//	url : '/respaldo',
//	type : 'post',
//	data : {'objectJson' : JSON.stringify("avanceFinanciero")},
//	success: function(data)
//	{
//		
//		avanceFinanciero=data.items;
//		inserta2(0,avanceFinanciero);
//		
//	 	
//	
//	},
//	error: function(jHR,e,throwsError){
//		alert(e);
//	}	
//});
//	$.ajax({
//	url : '/respaldo',
//	type : 'post',
//	data : {'objectJson' : JSON.stringify("avanceFisico")},
//	success: function(data)
//	{
//		
//	},
//	error: function(jHR,e,throwsError){
//		alert(e);
//	}	
//});
//$.ajax({
//	url : '/respaldo',
//	type : 'post',
//	data : {'objectJson' : JSON.stringify("catContactos")},
//	success: function(data)
//	{
//		
//	},
//	error: function(jHR,e,throwsError){
//		alert(e);
//	}	
//});
//$.ajax({
//	url : '/respaldo',
//	type : 'post',
//	data : {'objectJson' : JSON.stringify("catPersonal")},
//	success: function(data)
//	{
//		
//	},
//	error: function(jHR,e,throwsError){
//		alert(e);
//	}	
//});
//$.ajax({
//	url : '/respaldo',
//	type : 'post',
//	data : {'objectJson' : JSON.stringify("catTipoEmpresa")},
//	success: function(data)
//	{
//		
//	},
//	error: function(jHR,e,throwsError){
//		alert(e);
//	}	
//});
//$.ajax({
//	url : '/respaldo',
//	type : 'post',
//	data : {'objectJson' : JSON.stringify("catTipoMaquinaria")},
//	success: function(data)
//	{
//		
//	},
//	error: function(jHR,e,throwsError){
//		alert(e);
//	}	
//});
//$.ajax({
//	url : '/respaldo',
//	type : 'post',
//	data : {'objectJson' : JSON.stringify("catTipoPersona")},
//	success: function(data)
//	{
//		
//	},
//	error: function(jHR,e,throwsError){
//		alert(e);
//	}	
//});
//$.ajax({
//	url : '/respaldo',
//	type : 'post',
//	data : {'objectJson' : JSON.stringify("concepto")},
//	success: function(data)
//	{
//		
//	},
//	error: function(jHR,e,throwsError){
//		alert(e);
//	}	
//});
//$.ajax({
//	url : '/respaldo',
//	type : 'post',
//	data : {'objectJson' : JSON.stringify("deviceInfo")},
//	success: function(data)
//	{
//		
//	},
//	error: function(jHR,e,throwsError){
//		alert(e);
//	}	
//});
//$.ajax({
//	url : '/respaldo',
//	type : 'post',
//	data : {'objectJson' : JSON.stringify("directivoProyecto")},
//	success: function(data)
//	{
//		
//	},
//	error: function(jHR,e,throwsError){
//		alert(e);
//	}	
//});
//$.ajax({
//	url : '/respaldo',
//	type : 'post',
//	data : {'objectJson' : JSON.stringify("directorio")},
//	success: function(data)
//	{
//		
//	},
//	error: function(jHR,e,throwsError){
//		alert(e);
//	}	
//});
//$.ajax({
//	url : '/respaldo',
//	type : 'post',
//	data : {'objectJson' : JSON.stringify("empresa")},
//	success: function(data)
//	{
//		
//	},
//	error: function(jHR,e,throwsError){
//		alert(e);
//	}	
//});
//$.ajax({
//	url : '/respaldo',
//	type : 'post',
//	data : {'objectJson' : JSON.stringify("estimacion")},
//	success: function(data)
//	{
//		
//	},
//	error: function(jHR,e,throwsError){
//		alert(e);
//	}	
//});
//$.ajax({
//	url : '/respaldo',
//	type : 'post',
//	data : {'objectJson' : JSON.stringify("maquinaria")},
//	success: function(data)
//	{
//		
//	},
//	error: function(jHR,e,throwsError){
//		alert(e);
//	}	
//});
//$.ajax({
//	url : '/respaldo',
//	type : 'post',
//	data : {'objectJson' : JSON.stringify("message")},
//	success: function(data)
//	{
//		
//	},
//	error: function(jHR,e,throwsError){
//		alert(e);
//	}	
//});
//$.ajax({
//	url : '/respaldo',
//	type : 'post',
//	data : {'objectJson' : JSON.stringify("multimedia")},
//	success: function(data)
//	{
//		
//	},
//	error: function(jHR,e,throwsError){
//		alert(e);
//	}	
//});
//$.ajax({
//	url : '/respaldo',
//	type : 'post',
//	data : {'objectJson' : JSON.stringify("notas")},
//	success: function(data)
//	{
//		
//	},
//	error: function(jHR,e,throwsError){
//		alert(e);
//	}	
//});
//$.ajax({
//	url : '/respaldo',
//	type : 'post',
//	data : {'objectJson' : JSON.stringify("obra")},
//	success: function(data)
//	{
//		
//	},
//	error: function(jHR,e,throwsError){
//		alert(e);
//	}	
//});
//$.ajax({
//	url : '/respaldo',
//	type : 'post',
//	data : {'objectJson' : JSON.stringify("orgGubernamental")},
//	success: function(data)
//	{
//		
//	},
//	error: function(jHR,e,throwsError){
//		alert(e);
//	}	
//});
//$.ajax({
//	url : '/respaldo',
//	type : 'post',
//	data : {'objectJson' : JSON.stringify("persona")},
//	success: function(data)
//	{
//		
//	},
//	error: function(jHR,e,throwsError){
//		alert(e);
//	}	
//});
//$.ajax({
//	url : '/respaldo',
//	type : 'post',
//	data : {'objectJson' : JSON.stringify("programa")},
//	success: function(data)
//	{
//		
//	},
//	error: function(jHR,e,throwsError){
//		alert(e);
//	}	
//});
//$.ajax({
//	url : '/respaldo',
//	type : 'post',
//	data : {'objectJson' : JSON.stringify("proyecto")},
//	success: function(data)
//	{
//		
//	},
//	error: function(jHR,e,throwsError){
//		alert(e);
//	}	
//});
//$.ajax({
//	url : '/respaldo',
//	type : 'post',
//	data : {'objectJson' : JSON.stringify("push")},
//	success: function(data)
//	{
//		
//	},
//	error: function(jHR,e,throwsError){
//		alert(e);
//	}	
//});
//$.ajax({
//	url : '/respaldo',
//	type : 'post',
//	data : {'objectJson' : JSON.stringify("refCalendarizacion")},
//	success: function(data)
//	{
//		
//	},
//	error: function(jHR,e,throwsError){
//		alert(e);
//	}	
//});
//$.ajax({
//	url : '/respaldo',
//	type : 'post',
//	data : {'objectJson' : JSON.stringify("repMaqCatMaq")},
//	success: function(data)
//	{
//		
//	},
//	error: function(jHR,e,throwsError){
//		alert(e);
//	}	
//});
//$.ajax({
//	url : '/respaldo',
//	type : 'post',
//	data : {'objectJson' : JSON.stringify("repoPersonalCatPersonal")},
//	success: function(data)
//	{
//		
//	},
//	error: function(jHR,e,throwsError){
//		alert(e);
//	}	
//});
//$.ajax({
//	url : '/respaldo',
//	type : 'post',
//	data : {'objectJson' : JSON.stringify("reporteDiario")},
//	success: function(data)
//	{
//		
//	},
//	error: function(jHR,e,throwsError){
//		alert(e);
//	}	
//});
//$.ajax({
//	url : '/respaldo',
//	type : 'post',
//	data : {'objectJson' : JSON.stringify("sincronizacion")},
//	success: function(data)
//	{
//		
//	},
//	error: function(jHR,e,throwsError){
//		alert(e);
//	}	
//});
//$.ajax({
//	url : '/respaldo',
//	type : 'post',
//	data : {'objectJson' : JSON.stringify("ubicaciones")},
//	success: function(data)
//	{
//		
//	},
//	error: function(jHR,e,throwsError){
//		alert(e);
//	}	
//});
//$.ajax({
//	url : '/respaldo',
//	type : 'post',
//	data : {'objectJson' : JSON.stringify("usuario")},
//	success: function(data)
//	{
//		
//	},
//	error: function(jHR,e,throwsError){
//		alert(e);
//	}	
//});
	/*
	 *	var avanceFinanciero = new Array();
	var avanceFisico= new Array();
	var catContactos= new Array();
	var catPersonal= new Array();
	var catTipoEmpresa= new Array();
	var catTipoMaquinaria= new Array();
	var catTipoPersona= new Array();
	var concepto= new Array();
	var deviceInfo= new Array();
	var directivoProyecto= new Array();
	var directorio= new Array();
	var empresa= new Array();
	var estimacion= new Array();
	var maquinaria= new Array();
	var message= new Array();
	var multimedia= new Array();
	var notas= new Array();
	var obra= new Array();
	var orgGubernamental= new Array();
	var persona= new Array();
	var programa= new Array();
	var proyecto= new Array();
	var push= new Array();
	var refCalendarizacion= new Array();
	var repMaqCatMaq= new Array();
	var repoPersonalCatPersonal= new Array();
	var reporteDiario= new Array();
	var sincronizacion= new Array();
	var ubicaciones= new Array();
	var usuario= new Array(); 
	 */

}


function inicia2(){
	
	gapi.client.load('communicationchannel','v1',function(){
		gapi.client.communicationchannel.listDirectivo_Proyecto({"directivo":"5"}).execute(function(resp){
			//window.alert(resp);
			proyectos = resp.items;
			if(proyectos.length){
				console.log("si esxisten proyectos"+proyectos.length);
				getObras(proyectos,0);
			
			}else{
				console.log("no esxisten proyectos");
			}
				});
		
		
		
	}, ROOT);
}
function inicia3(proyectos,index){
	
	

	
}
function getObras(proyectos,iProyecto){
	
	//alert('ds');
	iproyecto=iProyecto;
	gapi.client.load('communicationchannel','v1',function(){
		console.log("vamos a pedir las obras del  proyecto: "+iProyecto);
		gapi.client.communicationchannel.listObra({"idProyecto":proyectos[iProyecto].id_proyecto, "idObra":"0", "email":" "}).execute(function(resp){
			//window.alert(resp);
			obras = resp.items;
			
			if(obras.length){
				//console.log("obra" + obras[iProyecto].id_Obra);
				console.log("el tamanno del arreglo de proyecto es: "+proyectos.length+" y el elemento en curso es: "+iProyecto);
				//no entra a la funcion
				
				trabajaObra(obras, 0);
				
//				if(iProyecto<proyectos.length-1){
//					
//					getObras(proyectos, iProyecto+1);
//				}
			}
			else{
				console.log("el proyecto ["+iProyecto+"]"+"no tiene obras")
//				if(iProyecto<proyectos.length-1){
//					console.log("se mandara a llamar el proyecto "+iProyecto+1)
//					getObras(proyectos, iProyecto+1);
//					
//				}
			}
			
			
			
				});
		
		
		
	}, ROOT);
}

function trabajaObra(obras,iObras){
	
	gapi.client.load('communicationchannel','v1',function(){
	
			gapi.client.communicationchannel.listAvance_Financiero({"id_referencia":obras[iObras].id_Obra, "Tipo_Entidad":"1"}).execute(function(resp){//inicio del consumo del avance financiero
				
				var avanceFinanciero =  resp.items;
				if(avanceFinanciero.length){
					console.log("el avance financiero de la obra " + iObras + "si existe");
					//insertar en la base de datos la respuesta()
				}else{
					console.log("no tiene avance financiero la obra" +iObras );
				}
				//insertar en la base de datos la respuesta()
				
				gapi.client.communicationchannel.listAvance_Fisico({"id_referencia":obras[iObras].id_Obra, "Tipo_Entidad":"1"}).execute(function(resp){//inicio del consumo del avance fisico 
				
					var avanceFisico =  resp.items;
					if(avanceFisico.length){
						//insertar en la base de datos la respuesta()
						console.log("el avance fisico de la obra " + iObras + "si existe");
					}else{
						console.log("el avance fisico de la obra " + iObras + "no existe");
					}
					
					
					////////////consumimos el reporte diario
					
					gapi.client.communicationchannel.listReporteDiario({"idObra":obras[iObras].id_Obra, "email":" "}).execute(function(resp){//inicio del consumo del avance fisico 
						
						var reporteDiario =  resp.items;
						//insertar en la base de datos la respuesta()
						if(reporteDiario){
							//console.log("reportediario " + reporteDiario[iObras].id_ReporteDiario);
							catMaquinaria(reporteDiario, 0);

							if(iObras<obras.length-1){
								trabajaObra(obras, iObras+1);
								
							}		
						}else{
							console.log("la obra :"+iObras+" no tiene reporte diario")
							//trabajaObra(obras, iObras+1);
							
							if(iObras<obras.length-1){
								trabajaObra(obras, iObras+1);
								
							}
							
							
							
						}
					
						
						
						
						});//fin del consumo de reporte diario
					
					
					});//fin del consumo de avance fisico
				
				});//fin del consumo del avance financiero
		
		
		
	}, ROOT);
	
}
function catMaquinaria(reporteDiario,iMaquinaria){
	
	gapi.client.load('communicationchannel','v1',function(){
		gapi.client.communicationchannel.listRepMaquinariaCatMaquinaria({"idReporteDiario":reporteDiario[iMaquinaria].id_ReporteDiario,"email":" "}).execute(function(resp){
			//window.alert(resp);
			var catMaquinariarepote = resp.items;
			if(catMaquinariarepote){
		//insertar en la base de datos los valores
				for (var i = 0; i < catMaquinariarepote.length; i++) {
					console.log("id de reporte de maquinaria" + catMaquinariarepote[i].idRepMaquinaria);
				}
			console.log("catalogo maquinaria existe para el reporte diario :"+iMaquinaria);
				if(iMaquinaria<reporteDiario.length-1){
					catMaquinaria(reporteDiario[iMaquinaria].id_ReporteDiario, iMaquinaria+1);
				}else if(iproyecto<proyectos.length-1){
					
					getObras(proyectos, iproyecto+1);
				}
			}else
				{
				console.log("el catalogo de maquinaria del reporte "+iMaquinaria+" no existe")
				if(iMaquinaria<reporteDiario.length-1){
					catMaquinaria(reporteDiario[iMaquinaria].id_ReporteDiario, iMaquinaria+1);
				}else if(iproyecto<proyectos.length-1){
					
					getObras(proyectos, iproyecto+1);
				}
				
				}
			
				
		});
		
		
		
	}, ROOT);
	
}
//function inicia(){
//	
//	alert("llegue al init");
//	var ROOT = 'https://1-dot-iuyet-csgit-cao.appspot.com/_ah/api';
////
//	gapi.client.load('communicationchannel','v1',function(){
//											
//		 
//		gapi.client.communicationchannel.listAvance_Financiero(
//		{
//			id_referencia:"0",
//			Tipo_Entidad:"0"
//		}		
//		).execute(function(resp){
//		//window.alert(resp);
//		avanceFinanciero = resp.items;
//		gapi.client.communicationchannel.listAvance_Fisico	(
//				{
//					
//					id_referencia:"0",
//					Tipo_Entidad:"0"
//					
//				}		
//				).execute(function(resp){
//					//window.alert(resp);
//					avanceFisico = resp.items;
//					gapi.client.communicationchannel.listCat_Contactos().execute(function(resp){
//						//window.alert(resp);
//						catContactos= resp.items;
//						gapi.client.communicationchannel.listCat_Personal().execute(function(resp){
//							///window.alert(resp);
//							catPersonal = resp.items;
//							gapi.client.communicationchannel.listCat_Tipo_Empresa().execute(function(resp){
//								//window.alert(resp);
//								catTipoEmpresa = resp.items;
//								gapi.client.communicationchannel.listCat_Tipo_Maquinaria().execute(function(resp){
//									//window.alert(resp);
//									catTipoMaquinaria = resp.items;
//									gapi.client.communicationchannel.listCat_Tipo_Presona().execute(function(resp){
//										//window.alert(resp);
//										catTipoPersona = resp.items;
//										gapi.client.communicationchannel.listConcepto(
//												{
//													id_obra:"0"
//													
//												}		
//												).execute(function(resp){
//													//window.alert(resp);
//													concepto = resp.items;
//													gapi.client.communicationchannel.listDeviceInfo().execute(function(resp){
//														//window.alert(resp);
//														deviceInfo = resp.items;
//														gapi.client.communicationchannel.listDirectivo_Proyecto().execute(function(resp){
//															//window.alert(resp);
//															directivoProyecto = resp.items;
//															gapi.client.communicationchannel.listDirectorio(
//																	{
//																		tipoReferencia:"0",
//																		idReferencia:"0"
//																		
//																	}		
//																	).execute(function(resp){
//																		//window.alert(resp);
//																		directorio = resp.items;
//																		gapi.client.communicationchannel.listEmpresa().execute(function(resp){
//																			//window.alert(resp);
//																			empresa = resp.items;
//																			gapi.client.communicationchannel.listEstimacion().execute(function(resp){
//																				//window.alert(resp);
//																				estimacion = resp.items;
//																				gapi.client.communicationchannel.listMaquinaria().execute(function(resp){
//																					//window.alert(resp);
//																					maquinaria = resp.items;
//																					gapi.client.communicationchannel.listMessages().execute(function(resp){
//																						//window.alert(resp);
//																						message = resp.items;
//																						gapi.client.communicationchannel.listMultimedia(
//																								
//																								{
//																									
//																									tipoReferencia:"0",
//																									idReferencia:"0",
//																									tipoArchivo:"0"
//																								}
//																								).execute(function(resp){
//																									//window.alert(resp);
//																									multimedia = resp.items;
//																									gapi.client.communicationchannel.listNotas().execute(function(resp){
//																										//window.alert(resp);
//																										notas = resp.items;
//																										gapi.client.communicationchannel.listObra(
//																												{
//																													idProyecto:"0",
//																													idObra:"0"
//																													
//																												}		
//																												).execute(function(resp){
//																													//window.alert(resp);
//																													obra = resp.items;
//																													gapi.client.communicationchannel.listOrg_Goubernamental().execute(function(resp){
//																														//window.alert(resp);
//																														orgGubernamental = resp.items;
//																														gapi.client.communicationchannel.listPersona().execute(function(resp){
//																															//window.alert(resp);
//																															persona = resp.items;
//																															gapi.client.communicationchannel.listPrograma().execute(function(resp){
//																																//window.alert(resp);
//																																programa = resp.items;
//																																gapi.client.communicationchannel.listProyecto().execute(function(resp){
//																																	//window.alert(resp);
//																																	proyecto = resp.items;
//																																	gapi.client.communicationchannel.listPush().execute(function(resp){
//																																		//window.alert(resp);
//																																		push = resp.items;
//																																		gapi.client.communicationchannel.listRef_calendarizacion().execute(function(resp){
//																																			//window.alert(resp);
//																																			refCalendarizacion = resp.items;
//																																			gapi.client.communicationchannel.listRepMaquinariaCatMaquinaria(
//																																					
//																																					{
//																																						
//																																						idReporteDiario:"0",
//																																					default:"0"
//																																					}
//																																					).execute(function(resp){
//																																						//window.alert(resp);
//																																						catTipoMaquinaria = resp.items;
//																																						gapi.client.communicationchannel.listRepoPersonalCatPersonal().execute(function(resp){
//																																							//window.alert(resp);
//																																							catPersonal = resp.items;
//																																							gapi.client.communicationchannel.listReporteDiario(
//																																									{
//																																										idObra:"0"
//																																										
//																																									}		
//																																									).execute(function(resp){
//																																										//window.alert(resp);
//																																										reporteDiario = resp.items;
//																																										gapi.client.communicationchannel.listSincronizacion().execute(function(resp){
//																																											//window.alert(resp);
//																																											sincronizacion = resp.items;
//																																											gapi.client.communicationchannel.listUbicaciones().execute(function(resp){
//																																												//window.alert(resp);
//																																												ubicaciones = resp.items;
//																																												gapi.client.communicationchannel.listUsuario().execute(function(resp){
//																																													//window.alert(resp);
//																																													usuario = resp.items;
//																																													inserta();
//																																														});
//																																													});
//																																												});
//																																											});
//																																								});
//																																							});
//																																				});
//																																			});
//																																		});
//																																	});
//																																});
//																															});
//																														});
//																											});
//																										});
//																							});
//																						});
//																					});
//																				});
//																			});
//															
//																});
//															});
//														});
//											});
//										});
//								
//									});
//							
//								});
//						
//							});
//					
//						});	
//		
//		
//		});
//
//			}, ROOT);
//	console.log();
//	inserta();
//		for(var t=0;t<nombres.length;t++){
//			$.ajax({
//				url : '/respaldo',
//				type : 'post',
//				data : {'objectJson' : JSON.stringify(nombres[t])},
//				success: function(data)
//				{
//					
//					if(nombres[t]=="avanceFinanciero") avanceFinanciero=data.items;
//					else if(nombres[t]=="avanceFisico") avanceFisico=data.items;
//					else if(nombres[t]=="catContactos") catContactos=data.items;
//					else if(nombres[t]=="catPersonal") catPersonal=data.items;
//					else if(nombres[t]=="catTipoEmpresa") catTipoEmpresa=data.items;
//					else if(nombres[t]=="catTipoMaquinaria") catTipoMaquinaria=data.items;
//					else if(nombres[t]=="catTipoPersona") catTipoPersona=data.items;
//					else if(nombres[t]=="concepto") concepto=data.items;
//					else if(nombres[t]=="deviceInfo") deviceInfo=data.items;
//					else if(nombres[t]=="directivoProyecto") directivoProyecto=data.items;
//					else if(nombres[t]=="directorio") directorio=data.items;
//					else if(nombres[t]=="empresa") empresa=data.items;
//					else if(nombres[t]=="estimacion")estimacion =data.items;
//					else if(nombres[t]=="maquinaria") maquinaria=data.items;
//					else if(nombres[t]=="message") message=data.items;
//					else if(nombres[t]=="multimedia") multimedia=data.items;
//					else if(nombres[t]=="notas") notas=data.items;
//					else if(nombres[t]=="obra") obra=data.items;
//					else if(nombres[t]=="orgGubernamental") orgGubernamental=data.items;
//					else if(nombres[t]=="persona") persona=data.items;
//					else if(nombres[t]=="avanceFinaprogramanciero") programa=data.items;
//					else if(nombres[t]=="proyecto") proyecto=data.items;
//					else if(nombres[t]=="push") push=data.items;
//					else if(nombres[t]=="refCalendarizacion") refCalendarizacion=data.items;
//					else if(nombres[t]=="repMaqCatMaq") repMaqCatMaq=data.items;
//					else if(nombres[t]=="repoPersonalCatPersonal") repoPersonalCatPersonal=data.items;
//					else if(nombres[t]=="reporteDiario") reporteDiario=data.items;
//					else if(nombres[t]=="sincronizacion") sincronizacion=data.items;
//					else if(nombres[t]=="ubicaciones") ubicaciones=data.items;
//					else if(nombres[t]=="usuario") usuario=data.items;
//					
//				},
//				error: function(jHR,e,throwsError){
//					alert(e);
//				}	
//		});
//			
//		}
//		
//		
//	$.ajax({
//		url : '/respaldo',
//		type : 'post',
//		data : {'objectJson' : JSON.stringify("proyecto")},
//		success: function(data)
//		{
//			
//		},
//		error: function(jHR,e,throwsError){
//			alert(e);
//		}	
//});
//	$.ajax({
//		url : '/respaldo',
//		type : 'post',
//		data : {'objectJson' : JSON.stringify("avanceFisico")},
//		success: function(data)
//		{
//			
//		},
//		error: function(jHR,e,throwsError){
//			alert(e);
//		}	
//});
//	$.ajax({
//		url : '/respaldo',
//		type : 'post',
//		data : {'objectJson' : JSON.stringify("catContactos")},
//		success: function(data)
//		{
//			
//		},
//		error: function(jHR,e,throwsError){
//			alert(e);
//		}	
//});
//	$.ajax({
//		url : '/respaldo',
//		type : 'post',
//		data : {'objectJson' : JSON.stringify("catPersonal")},
//		success: function(data)
//		{
//			
//		},
//		error: function(jHR,e,throwsError){
//			alert(e);
//		}	
//});
//	$.ajax({
//		url : '/respaldo',
//		type : 'post',
//		data : {'objectJson' : JSON.stringify("catTipoEmpresa")},
//		success: function(data)
//		{
//			
//		},
//		error: function(jHR,e,throwsError){
//			alert(e);
//		}	
//});
//	$.ajax({
//		url : '/respaldo',
//		type : 'post',
//		data : {'objectJson' : JSON.stringify("catTipoMaquinaria")},
//		success: function(data)
//		{
//			
//		},
//		error: function(jHR,e,throwsError){
//			alert(e);
//		}	
//});
//	$.ajax({
//		url : '/respaldo',
//		type : 'post',
//		data : {'objectJson' : JSON.stringify("catTipoPersona")},
//		success: function(data)
//		{
//			
//		},
//		error: function(jHR,e,throwsError){
//			alert(e);
//		}	
//});
//	$.ajax({
//		url : '/respaldo',
//		type : 'post',
//		data : {'objectJson' : JSON.stringify("concepto")},
//		success: function(data)
//		{
//			
//		},
//		error: function(jHR,e,throwsError){
//			alert(e);
//		}	
//});
//	$.ajax({
//		url : '/respaldo',
//		type : 'post',
//		data : {'objectJson' : JSON.stringify("deviceInfo")},
//		success: function(data)
//		{
//			
//		},
//		error: function(jHR,e,throwsError){
//			alert(e);
//		}	
//});
//	$.ajax({
//		url : '/respaldo',
//		type : 'post',
//		data : {'objectJson' : JSON.stringify("directivoProyecto")},
//		success: function(data)
//		{
//			
//		},
//		error: function(jHR,e,throwsError){
//			alert(e);
//		}	
//});
//	$.ajax({
//		url : '/respaldo',
//		type : 'post',
//		data : {'objectJson' : JSON.stringify("directorio")},
//		success: function(data)
//		{
//			
//		},
//		error: function(jHR,e,throwsError){
//			alert(e);
//		}	
//});
//	$.ajax({
//		url : '/respaldo',
//		type : 'post',
//		data : {'objectJson' : JSON.stringify("empresa")},
//		success: function(data)
//		{
//			
//		},
//		error: function(jHR,e,throwsError){
//			alert(e);
//		}	
//});
//	$.ajax({
//		url : '/respaldo',
//		type : 'post',
//		data : {'objectJson' : JSON.stringify("estimacion")},
//		success: function(data)
//		{
//			
//		},
//		error: function(jHR,e,throwsError){
//			alert(e);
//		}	
//});
//	$.ajax({
//		url : '/respaldo',
//		type : 'post',
//		data : {'objectJson' : JSON.stringify("maquinaria")},
//		success: function(data)
//		{
//			
//		},
//		error: function(jHR,e,throwsError){
//			alert(e);
//		}	
//});
//	$.ajax({
//		url : '/respaldo',
//		type : 'post',
//		data : {'objectJson' : JSON.stringify("message")},
//		success: function(data)
//		{
//			
//		},
//		error: function(jHR,e,throwsError){
//			alert(e);
//		}	
//});
//	$.ajax({
//		url : '/respaldo',
//		type : 'post',
//		data : {'objectJson' : JSON.stringify("multimedia")},
//		success: function(data)
//		{
//			
//		},
//		error: function(jHR,e,throwsError){
//			alert(e);
//		}	
//});
//	$.ajax({
//		url : '/respaldo',
//		type : 'post',
//		data : {'objectJson' : JSON.stringify("notas")},
//		success: function(data)
//		{
//			
//		},
//		error: function(jHR,e,throwsError){
//			alert(e);
//		}	
//});
//	$.ajax({
//		url : '/respaldo',
//		type : 'post',
//		data : {'objectJson' : JSON.stringify("obra")},
//		success: function(data)
//		{
//			
//		},
//		error: function(jHR,e,throwsError){
//			alert(e);
//		}	
//});
//	$.ajax({
//		url : '/respaldo',
//		type : 'post',
//		data : {'objectJson' : JSON.stringify("orgGubernamental")},
//		success: function(data)
//		{
//			
//		},
//		error: function(jHR,e,throwsError){
//			alert(e);
//		}	
//});
//	$.ajax({
//		url : '/respaldo',
//		type : 'post',
//		data : {'objectJson' : JSON.stringify("persona")},
//		success: function(data)
//		{
//			
//		},
//		error: function(jHR,e,throwsError){
//			alert(e);
//		}	
//});
//	$.ajax({
//		url : '/respaldo',
//		type : 'post',
//		data : {'objectJson' : JSON.stringify("programa")},
//		success: function(data)
//		{
//			
//		},
//		error: function(jHR,e,throwsError){
//			alert(e);
//		}	
//});
//	$.ajax({
//		url : '/respaldo',
//		type : 'post',
//		data : {'objectJson' : JSON.stringify("proyecto")},
//		success: function(data)
//		{
//			
//		},
//		error: function(jHR,e,throwsError){
//			alert(e);
//		}	
//});
//	$.ajax({
//		url : '/respaldo',
//		type : 'post',
//		data : {'objectJson' : JSON.stringify("push")},
//		success: function(data)
//		{
//			
//		},
//		error: function(jHR,e,throwsError){
//			alert(e);
//		}	
//});
//	$.ajax({
//		url : '/respaldo',
//		type : 'post',
//		data : {'objectJson' : JSON.stringify("refCalendarizacion")},
//		success: function(data)
//		{
//			
//		},
//		error: function(jHR,e,throwsError){
//			alert(e);
//		}	
//});
//	$.ajax({
//		url : '/respaldo',
//		type : 'post',
//		data : {'objectJson' : JSON.stringify("repMaqCatMaq")},
//		success: function(data)
//		{
//			
//		},
//		error: function(jHR,e,throwsError){
//			alert(e);
//		}	
//});
//	$.ajax({
//		url : '/respaldo',
//		type : 'post',
//		data : {'objectJson' : JSON.stringify("repoPersonalCatPersonal")},
//		success: function(data)
//		{
//			
//		},
//		error: function(jHR,e,throwsError){
//			alert(e);
//		}	
//});
//	$.ajax({
//		url : '/respaldo',
//		type : 'post',
//		data : {'objectJson' : JSON.stringify("reporteDiario")},
//		success: function(data)
//		{
//			
//		},
//		error: function(jHR,e,throwsError){
//			alert(e);
//		}	
//});
//	$.ajax({
//		url : '/respaldo',
//		type : 'post',
//		data : {'objectJson' : JSON.stringify("sincronizacion")},
//		success: function(data)
//		{
//			
//		},
//		error: function(jHR,e,throwsError){
//			alert(e);
//		}	
//});
//	$.ajax({
//		url : '/respaldo',
//		type : 'post',
//		data : {'objectJson' : JSON.stringify("ubicaciones")},
//		success: function(data)
//		{
//			
//		},
//		error: function(jHR,e,throwsError){
//			alert(e);
//		}	
//});
//	$.ajax({
//		url : '/respaldo',
//		type : 'post',
//		data : {'objectJson' : JSON.stringify("usuario")},
//		success: function(data)
//		{
//			
//		},
//		error: function(jHR,e,throwsError){
//			alert(e);
//		}	
//});
	
	

//}