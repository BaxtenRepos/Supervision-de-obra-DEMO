////////////////CREAR BASE DE DATOS

var db;
var noTablas = 0;
var ROOT = 'https://' + '1-dot-cao-iuyet-server.appspot.com' + '/_ah/api';
//var ROOT = 'https://' + '1-dot-iuyet-csgit-cao.appspot.com' + '/_ah/api';
var tx;

//Entidades
var idProyecto, NombreLargo, NombreCorto, Descripcion, Dependencia, Secretaria, LimiteDesvio, Desvio, idUbicacion; //Proyectos
var IdAvanceFisico, idReferencia, TipoEntidad, PAvanceFisico, Tendencia, FechaReporte, Estado; //Avance Fisico
var IdAvanceFinanciero, F_idReferencia, F_TipoEntidad, F_PAvanceFisico, F_Tendencia, F_FechaReporte, F_Estado; //Avance Financiero
//Obra
var O_idObra,O_idProyecto,O_NoContrato,O_RFC,O_Nombre,O_Gobierno,O_Secretaria,O_Dependencia,O_Direccion,O_Subdireccion,O_Area,O_EmpresaContratista;
var O_Superintendente,O_EntidadFederativa,O_Descripcion,O_FechaContrato,O_TipoContrato,O_ImporteContratoSinIVA,O_ImporteConvenioAmpliacion,O_ImporteConvenioReduccion;
var O_ImporteAjusteCostos,O_FechaInicioContrato,O_PeriodoEjucionDias,O_PartidaPresupuestal,O_Anticipo,O_NoFianzaAnticipo,O_FechaFianzaAnticipo,O_MontoFianzaAnticipo;
var O_NoFianzaCumplimiento,O_FechaFianzaCumplimiento,O_MontoFianzaCumplimiento,O_CargoRevision1,O_NombreRevision1,O_CargoRevision2,O_NombreRevision2,O_NombreQuienAutoriza;
var O_CargoVoBo,O_NombreVoBo,O_LimiteDesvio,O_Desvio,O_FechaTerminoContrato,O_NombreEjercicioFiscal1,O_ImporteFiscal1SinIVA,O_Borrador;

//Arreglos de carga de informacion
var ProyectoItems, AvanceFisicoItems, AvanceFinancieroItems, ProyectosDirectivoItems;
//ReportesItems, RepMaquinariaItems, ListMaquinariaItems, CatTipoMaquinariaItems

if (window.location.hostname == 'localhost'
    || window.location.hostname == '127.0.0.1'
    || ((window.location.port != "") && (window.location.port > 999))) {
    // We're probably running against the DevAppServer
    //ROOT = 'http://' + '1-dot-iuyet-csgit-cao.appspot.com' + '/_ah/api';
    ROOT = 'https://' + '1-dot-cao-iuyet-server.appspot.com' + '/_ah/api';
}
  
function init(){
           console.log("init::::::::::::::" + checkConnection());
           
           /*if(checkConnection() != "No network connection" && checkConnection() != "Unknown connection"){
           Messenger().post("Conexi&oacute;n establecida: " + checkConnection());          
            console.log("Iniciando descarga de datos::::1");
            endpoints();
            }else{
                      
                  alert("Sin salida a internet, datos no actualizados");
                  location.href="lista_proyectos.html";
                  
           }*/
           
           endpoints();
 }


function endpoints(){
 
    console.log("Descargando :::::::::::::::::::");
    //idDirectivo = 1;
    valor = getGET().idDirectivo;
    console.log("directivo:" + valor);
          //Consultar EndPoint Proyectos
          gapi.client.load('communicationchannel', 'v1', function(){
                      gapi.client.communicationchannel.listDirectivo_Proyecto({"directivo": valor}).execute(function(resp){
                                 if(resp.code) {
                                 alert("Error de conexi—n, no se ha podido descargar Proyectos, volviendo a intentar. :::" + resp.message );
                                 }else{
                                  
                                  ProyectosDirectivoItems = resp.items;
                                  console.log("Directivo-Proyectos:: " + ProyectosDirectivoItems.length);
                                  document.getElementById("endpoint1").innerHTML= "Directivo-Proyectos: <img src=\" biblioteca/Imagenes/check.png\" width=\"20px\" heigth=\"20px\">";
                                  /*for(var i=0; i < ProyectosDirectivoItems.length; i++){
                                            gapi.client.communicationchannel.getProyecto({"id":ProyectosDirectivoItems[i].id_proyecto}).execute(function(resp){
            if(resp.code) {
                      alert("Error de conexi—n, no se ha podido descargar Proyectos, volviendo a intentar. :::" + resp.message );
                      }else{
                                  ProyectoItems = resp;
                                  //console.log("Proyectos: " + ProyectoItems.length);   
                           document.getElementById("endpoint2").innerHTML= "Proyectos: <img src=\" biblioteca/Imagenes/check.png\" width=\"20px\" heigth=\"20px\">";
                      }

            });//listProyectoEndPoint
                                            
                                  }*/
                                  //logos(valor);
                      }
                     
            });//listProyectoEndPoint
                      
           //Load ProyectosEndPoint
            
          //---------------------------------------------------------------------------------------
            //Load ProyectosEndPoint
            gapi.client.communicationchannel.listProyecto().execute(function(resp){
            if(resp.code) {
                      alert("Error de conexi—n, no se ha podido descargar Proyectos, volviendo a intentar. :::" + resp.message );
                      }else{
                                  ProyectoItems = resp.items;
                                  console.log("Proyectos: " + ProyectoItems.length);   
                           document.getElementById("endpoint2").innerHTML= "Proyectos: <img src=\" biblioteca/Imagenes/check.png\" width=\"20px\" heigth=\"20px\">";
                      }

            });//listProyectoEndPoint
          //---------------------------------------------------------------------------------------
           //Consultar EndPoint Obras
           gapi.client.communicationchannel.listObra({"idProyecto": "0", "idObra":"0", "email":" "}).execute(function(resp){
           if(resp.code) {
                      alert("Error de conexi—n, no se ha podido descargar Obras, volviendo a intentar. :::" + resp.message );
                      }else{
            ObrasItems = resp.items;
            console.log("Obras: " + ObrasItems.length);
            document.getElementById("endpoint3").innerHTML= "Obras: <img src=\" biblioteca/Imagenes/check.png\" width=\"20px\" heigth=\"20px\">";
            //----------------- Reporte de Maquinaria----------------------
            /*for(var i = 0; i < ObrasItems.length; i++){
                      gapi.client.communicationchannel.listReporteDiario({"idObra":ObrasItems[i].id_Obra, "email":" "}).execute(function(resp){
                                 if (resp.code) {
                                            //code
                                            alert("Error de conexi—n, no se ha podido descargar Reportes Diarios, volviendo a intentar. :::" + resp.message );
                                 }else{
                                            ReportesItems = resp.items;
                                            if (ReportesItems) {
                                                       //code
                                                       console.log("ddd");
                                                       for (var i = 0; i < ReportesItems.length; i++) {
                                                       //code
                                                                  gapi.client.communicationchannel.listRepMaquinariaCatMaquinaria({"idReporteDiario":ReportesItems[i].id_ReporteDiario, "email":" "}).execute(function(resp){
                                                                             if (resp.code) {
                                                                                        //code
                                                                                        alert("Error de conexi—n, no se ha podido descargando reporte de maquinaria catalogo, volviendo a intentar. :::" + resp.message );
                                                                             }else{
                                                                                        RepMaquinariaItems = resp.items;
                                                                                        console.log("Se descago correctamente Lista de RepMaquinariaCatMaquinaria: " + ReportesItems);
                                                                                        console.log("Id de idRepMaquinaria 0: " + RepMaquinariaItems[0].idRepMaquinaria+ " id de idCatMaquinaria 0: " + RepMaquinariaItems[0].idCatMaquinaria + " idReporteDiario 0:" + RepMaquinariaItems[0].idReporteDiario);
                                                                                        console.log("Id de idRepMaquinaria 1: " + RepMaquinariaItems[1].idRepMaquinaria+ " id de idCatMaquinaria 1: " + RepMaquinariaItems[1].idCatMaquinaria + " idReporteDiario 1:" + RepMaquinariaItems[1].idReporteDiario);
                                                                                        gapi.client.communicationchannel.listMaquinaria().execute(function(resp){
                                                                                                   if (resp.code) {
                                                                                                              //code
                                                                                                              alert("Error de conexi—n, no se ha podido descargando lista de maquinaria, volviendo a intentar. :::" + resp.message );
                                                                                                   }else{
                                                                                                              ListMaquinariaItems = resp.items;
                                                                                                              console.log("Se descargaron la lista de maquinaria: " + ListMaquinariaItems.length);
                                                                                                              gapi.client.communicationchannel.listCat_Tipo_Maquinaria().execute(function(resp){
                                                                                                                         if (resp.code) {
                                                                                                                                    //code
                                                                                                                                    alert("Error de conexi—n, no se ha podido descargar el tipo de maquinaria, volviendo a intentar. :::" + resp.message );
                                                                                                                         }else{
                                                                                                                                    CatTipoMaquinariaItems = resp.items;
                                                                                                                                    console.log("Se descargo el Catalogo tipo Maquinaria: " + CatTipoMaquinariaItems.length);
                                                                                                                         }
                                                                                                              });// fin de listCat_Tipo_Maquinaria
                                                                                                   }
                                                                                                   
                                                                                        });// fin de ListMaquinaria
                                                                             }
                                                                  });//fin de listRepMaquinariaCatMaquinaria
                                                       }
                                                       console.log("Se descago correctamente el Reporte Diario: ");
                                            }else{
                                                       console.log("No hay reportes diarios");
                                            }
                                            
                                            //console.log("Id de Obra 0: " + ReportesItems[0].id_Obra+ " id de Reporte 0: " + ReportesItems[0].id_ReporteDiario);
                                            //console.log("Id de Obra 1: " + ReportesItems[1].id_Obra+ " id de Reporte 1: " + ReportesItems[1].id_ReporteDiario);
                                            //console.log("Id de Obra 2: " + ReportesItems[2].id_Obra+ " id de Reporte 2: " + ReportesItems[2].id_ReporteDiario);
                                            
                                 }
                      });// fin de Reporte diario
            }*/
            
            //----------------- fin delReporte de Maquinaria----------------------
                      }
            });//listObra
          
           //Consultar EndPoint avance fisico
            gapi.client.communicationchannel.listAvance_Fisico({"Tipo_Entidad": "0", "id_referencia": "0"}).execute(function(resp){
            if(resp.code) {
                      alert("Error de conexi—n, no se ha podido descargar Avances Fisicos, volviendo a intentar. :::" + resp.message );
                      }else{
            AvanceFisicoItems = resp.items;
             console.log("Avance Fisico:" + AvanceFisicoItems.length);
                           document.getElementById("endpoint4").innerHTML= "Avances Fisicos: <img src=\" biblioteca/Imagenes/check.png\" width=\"20px\" heigth=\"20px\">";
                      }
            });//list
            
            gapi.client.communicationchannel.listAvance_Financiero({"Tipo_Entidad": "0", "id_referencia": "0"}).execute(function(resp){
            if(resp.code) {
                      alert("Error de conexi—n, no se ha podido descargar Avances Financieros, volviendo a intentar. :::" + resp.message );
                      }else{
            AvanceFinancieroItems = resp.items;
            console.log("AvanceFinancieroItems: " + AvanceFinancieroItems.length);
                          document.getElementById("endpoint5").innerHTML= "Avances Financieros: <img src=\" biblioteca/Imagenes/check.png\" width=\"20px\" heigth=\"20px\">";
                      }
            });//list
            
             gapi.client.communicationchannel.listUbicaciones().execute(function(resp){
           if(resp.code) {
                      alert("Error de conexi—n, no se ha podido descargar Ubicaciones, volviendo a intentar. :::" + resp.message );
                      }else{
            UbicacionItems = resp.items;
            console.log("UbicacionItems: " + UbicacionItems.length);
                          document.getElementById("endpoint6").innerHTML = "Ubicaciones: <img src=\" biblioteca/Imagenes/check.png\" width=\"20px\" heigth=\"20px\">";
                      }
            });//list
             
             gapi.client.communicationchannel.listEmpresa().execute(function(resp){
           if(resp.code) {
                      alert("Error de conexi—n, no se ha podido descargar Empresas, volviendo a intentar. :::" + resp.message );
                      }else{
            EmpresaItems = resp.items;
             console.log("EmpresaItems: " + EmpresaItems.length);
                           document.getElementById("endpoint7").innerHTML= "Empresas: <img src=\" biblioteca/Imagenes/check.png\" width=\"20px\" heigth=\"20px\">";
                      }
            });//list
            
           gapi.client.communicationchannel.listMultimedia({"tipoReferencia":"0", "idReferencia":"0","tipoArchivo":"0"}).execute(function(resp){
           if(resp.code) {
                      alert("Error de conexi—n, no se ha podido descargar Multimedia, volviendo a intentar. :::" + resp.message );
                      }else{
            MultimediaItems = resp.items;
             console.log("MultimediaItems: " + MultimediaItems.length);
                          document.getElementById("endpoint8").innerHTML= "Multimedia: <img src=\" biblioteca/Imagenes/check.png\" width=\"20px\" heigth=\"20px\">";
                      }
       });//list
           gapi.client.communicationchannel.listPersona().execute(function(resp){
           if(resp.code) {
                      alert("Error de conexi—n, no se ha podido descargar Multimedia, volviendo a intentar. :::" + resp.message );
                      }else{
            PersonaItems = resp.items;
             console.log("PersonaItems: " + PersonaItems.length);
                          document.getElementById("endpoint9").innerHTML= "Persona: <img src=\" biblioteca/Imagenes/check.png\" width=\"20px\" heigth=\"20px\">";
                      }
       });//list
            gapi.client.communicationchannel.listMaquinaria().execute(function(resp){
           if(resp.code) {
                      alert("Error de conexi—n, no se ha podido descargar Multimedia, volviendo a intentar. :::" + resp.message );
                      }else{
            MaquinariaItems = resp.items;
             //console.log("MaquinariaItems: " + MaquinariaItems.length);
                          //document.getElementById("endpoint10").innerHTML= "Maquinaria: <img src=\" biblioteca/Imagenes/check.png\" width=\"20px\" heigth=\"20px\">";
                      }
       });//list
            //Directorio
           gapi.client.communicationchannel.listDirectorio({"tipoReferencia":"0", "idReferencia":"0"}).execute(function(resp){
           if(resp.code) {
                      alert("Error de conexi—n, no se ha podido descargar Directorio, volviendo a intentar. :::" + resp.message );
                      }else{
            DirectorioItems = resp.items;
             console.log("DirectorioItems: " + DirectorioItems.length);
                          document.getElementById("endpoint11").innerHTML= "Directorio: <img src=\" biblioteca/Imagenes/check.png\" width=\"20px\" heigth=\"20px\">";
                      }
       });//list
           
    },ROOT); 
}



 

function crearBase() {
    //alert("Cargando datos espere un momento ::::..3");       
    console.log("Creando Base ::::::::::::::::::: 3");
    db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
    db.transaction(crearTablas, errorCB, successTablas);
    
}

function crearTablas(tx) {
      
  
    //Ubicaci—n
    tx.executeSql('DROP TABLE IF EXISTS Ubicacion');
    tx.executeSql('CREATE TABLE IF NOT EXISTS Ubicacion(idUbicacion unique, puntos)');
    //Proyectos
    tx.executeSql('DROP TABLE IF EXISTS Proyectos');
    tx.executeSql('CREATE TABLE IF NOT EXISTS Proyectos(idProyecto unique, NombreLargo, NombreCorto, Descripcion, Dependencia, Secretaria, LimiteDesvio, Desvio, idUbicacion)');
   
    //Documentos
    tx.executeSql('DROP TABLE IF EXISTS DocumentosProyectos');
    tx.executeSql('CREATE TABLE IF NOT EXISTS DocumentosProyectos(idDocumento unique, idProyecto, Docto, Formato, Tipo)');
    //Logotipos
    tx.executeSql('DROP TABLE IF EXISTS Logotipos');
    tx.executeSql('CREATE TABLE IF NOT EXISTS Logotipos(idLogotipo unique, idProyecto,  Logotipo, Formato, Tipo)');
    //Directorio
    tx.executeSql('DROP TABLE IF EXISTS Directorio');
    tx.executeSql('CREATE TABLE IF NOT EXISTS Directorio(idLogotipo unique, idProyecto,  Logotipo, Formato, Tipo)');
    
    //Directorio
    tx.executeSql('DROP TABLE IF EXISTS DirectorioProyectos');
    tx.executeSql('CREATE TABLE IF NOT EXISTS DirectorioProyectos(idDirectorio, idProyecto, Nombre, ApPaterno, ApMaterno, RFCPersonal, Cargo, TituloProfesional, CedulaProfesional, Fotografia, Skype, Email, Telefono, Radio)');
    //Obras
    tx.executeSql('DROP TABLE IF EXISTS Obras');
    tx.executeSql('CREATE TABLE IF NOT EXISTS Obras('+
                  'idObra unique, idProyecto, NoContrato, RFC, Nombre, Gobierno, Secretaria, Dependencia, Direccion, '+
                  'Subdireccion, Area, EmpresaContratista, Superintendente, EntidadFederativa, Descripcion, FechaContrato,'+
                  'TipoContrato, ImporteContratoSinIVA, ImporteConvenioAmpliacion, ImporteConvenioReduccion, ImporteAjusteCostos,'+
                  'FechaInicioContrato, PeriodoEjucionDias, PartidaPresupuestal, Anticipo, NoFianzaAnticipo, FechaFianzaAnticipo,'+
                  'MontoFianzaAnticipo, NoFianzaCumplimiento, FechaFianzaCumplimiento, MontoFianzaCumplimiento, CargoRevision1, NombreRevision1, '+
                  'CargoRevision2, NombreRevision2, NombreQuienAutoriza, CargoVoBo, NombreVoBo, LimiteDesvio, Desvio, fechaTerminoContrato, '+
                                              'nombreEjercicioFiscal1, importeFiscal1SinIVA, Borrador, idUbicacion)');

     //AvanceFinanciero

    tx.executeSql('DROP TABLE IF EXISTS AvanceFisicoObras');
    tx.executeSql('CREATE TABLE IF NOT EXISTS AvanceFisicoObras(IdAvanceFisico unique, idObra, PAvanceFisico, Tendencia, FechaReporte, Estado)');
    //AvanceFisico
    tx.executeSql('DROP TABLE IF EXISTS AvanceFinancieroObras');
    tx.executeSql('CREATE TABLE IF NOT EXISTS AvanceFinancieroObras(IdAvanceFinanciero unique, idObra, PAvanceFinanciero, Tendencia, FechaReporte, Estado)');
    
    //AvanceFinanciero
    tx.executeSql('DROP TABLE IF EXISTS AvanceFisicoProyectos');
    tx.executeSql('CREATE TABLE IF NOT EXISTS AvanceFisicoProyectos(IdAvanceFisico unique, idProyecto, PAvanceFisico, Tendencia, FechaReporte, Estado)');
   
    //AvanceFisico
    tx.executeSql('DROP TABLE IF EXISTS AvanceFinancieroProyectos');
    tx.executeSql('CREATE TABLE IF NOT EXISTS AvanceFinancieroProyectos(IdAvanceFinanciero unique, idProyecto, PAvanceFinanciero, Tendencia, FechaReporte, Estado)');
    
    //Alertas
    tx.executeSql('DROP TABLE IF EXISTS AlertasProyectos');
    tx.executeSql('CREATE TABLE IF NOT EXISTS AlertasProyectos(idAlerta unique,  idProyecto, LimiteDesvio, Tipo)');
    tx.executeSql('DROP TABLE IF EXISTS AlertasObras');
    tx.executeSql('CREATE TABLE IF NOT EXISTS AlertasObras(idAlertaObra unique,  idObra, LimiteDesvio, Tipo)');
   
    //Documentos
    tx.executeSql('DROP TABLE IF EXISTS DocumentosObras');
    tx.executeSql('CREATE TABLE IF NOT EXISTS DocumentosObras(idDocumento unique, idObra, Docto, Formato, Tipo)');
    //Directorio
    tx.executeSql('DROP TABLE IF EXISTS DirectorioObras');
    tx.executeSql('CREATE TABLE IF NOT EXISTS DirectorioObras(idDirectorioObra, idObra, RFCEmpresa, NombreEmpresa, TipoEmpresa,'+
                  'Nombre, ApPaterno, ApMaterno, RFCPersonal, Cargo, TituloProfesional, CedulaProfesional, Fotografia, Skype, Email, Telefono, Radio)');
    //Multimedia
    tx.executeSql('DROP TABLE IF EXISTS MultimediaProyectos');
    tx.executeSql('CREATE TABLE IF NOT EXISTS MultimediaProyectos (idMultimedia unique, idProyecto, TipoArchivo, Path, Fecha, Descripcion)');
    //Multimedia
    tx.executeSql('DROP TABLE IF EXISTS MultimediaObras');
    tx.executeSql('CREATE TABLE IF NOT EXISTS MultimediaObras (idMultimedia unique, idObra, TipoArchivo, Path, Fecha, Descripcion)');
    //Programa de Obra
    tx.executeSql('DROP TABLE IF EXISTS Programa_Obra');
    tx.executeSql('CREATE TABLE IF NOT EXISTS Programa_Obra (idPrograma unique, idObra, TipoAvance, Fecha, PorcentajeAvance)');
    //Programa de Proyecto
    tx.executeSql('DROP TABLE IF EXISTS Programa_Proyecto');
    tx.executeSql('CREATE TABLE IF NOT EXISTS Programa_Proyecto(idPrograma unique, idProyecto, TipoAvance, Fecha, PorcentajeAvance)');
    
    //Empresas
    tx.executeSql('DROP TABLE IF EXISTS Empresa');
    tx.executeSql('CREATE TABLE IF NOT EXISTS Empresa (idEmpresa unique, rfc, nombre, siglas)');
    
    /*Tablas independientes*/
    
     //AvanceProyectos
    tx.executeSql('DROP TABLE IF EXISTS TempAvanceProyectos');
    tx.executeSql('CREATE TABLE IF NOT EXISTS TempAvanceProyectos (idAvanceFinanciero, idAvanceFisico, idProyecto)');
  
    //AvanceProyectos
    tx.executeSql('DROP TABLE IF EXISTS TempAvanceObras');
    tx.executeSql('CREATE TABLE IF NOT EXISTS TempAvanceObras (idAvanceFinanciero, idAvanceFisico, idObra)');
    
    //Empresas Contratista ID 1
    tx.executeSql('DROP TABLE IF EXISTS Contratista');
    tx.executeSql('CREATE TABLE IF NOT EXISTS Contratista (idContratista, rfc, nombre, siglas, calle, num_Int, num_ext, CP, colonia, delegacion, entidad)');
    
    //Empresas Supervisora ID 2
    tx.executeSql('DROP TABLE IF EXISTS Supervisora');
    tx.executeSql('CREATE TABLE IF NOT EXISTS Supervisora (idSupervisora, rfc, nombre, siglas, calle, num_Int, num_ext, CP, colonia, delegacion, entidad)');
    
     //Empresas Dependencia ID 3
    tx.executeSql('DROP TABLE IF EXISTS Dependencia');
    tx.executeSql('CREATE TABLE IF NOT EXISTS Dependencia (idDependencia, rfc, nombre, siglas, calle, num_Int, num_ext, CP, colonia, delegacion, entidad)');
    
    //Empresas Particular ID 4
    tx.executeSql('DROP TABLE IF EXISTS Particular');
    tx.executeSql('CREATE TABLE IF NOT EXISTS Particular (idParticular, rfc, nombre, siglas, calle, num_Int, num_ext, CP, colonia, delegacion, entidad)');
    
     //Empresas Secretaria ID 5
    tx.executeSql('DROP TABLE IF EXISTS Secretaria');
    tx.executeSql('CREATE TABLE IF NOT EXISTS Secretaria (idSecretaria, rfc, nombre, siglas, calle, num_Int, num_ext, CP, colonia, delegacion, entidad)');
    
       //Empresas Gobierno ID 6
    tx.executeSql('DROP TABLE IF EXISTS Gobierno');
    tx.executeSql('CREATE TABLE IF NOT EXISTS Gobierno (idGobierno, rfc, nombre, siglas, calle, num_Int, num_ext, CP, colonia, delegacion, entidad)');
    
    //Reporte Diario
    tx.executeSql('DROP TABLE IF EXISTS ReporteDiario');
    tx.executeSql('CREATE TABLE IF NOT EXISTS ReporteDiario (idReporte unique, idObra, email, fecha)');
    
    //Catalogo Reporte de Maquinaria
    tx.executeSql('DROP TABLE IF EXISTS RepMaquinaria');
    tx.executeSql('CREATE TABLE IF NOT EXISTS RepMaquinaria (idReporteMaq unique, idReporte, idCatMaquinaria, email, cantidad)');
    
    //Lista de Maquinaria
    tx.executeSql('DROP TABLE IF EXISTS Maquinaria');
    tx.executeSql('CREATE TABLE IF NOT EXISTS Maquinaria (idMaquinaria unique, nombre, descripcion, tipo)');
    
    //Lista Tipo de Maquinaria
    tx.executeSql('DROP TABLE IF EXISTS TipoMaquinaria');
    tx.executeSql('CREATE TABLE IF NOT EXISTS TipoMaquinaria (idTipo_Maquinaria unique, Tipo_Maquinaria, Descripcion)');
    }


function errorCB(err) {
    console.log("Error processing SQL: "+err.code + " Message: "+err.message);
}

//Tablas creadas exitosamente
function successTablas() {
    
      console.log("Se han insertado las tablas");
       //var x = 0;
       var x = 0;
       insertarInformacion(x);
  
}

function insertarInformacion(x) {
            
               //alert("Validando Proyecto = " + validaProyecto(ProyectoItems[x].id_Proyecto) +" idProyecto= "+ProyectoItems[x].id_Proyecto+" idDirectivo= "+ getGET().idDirectivo);
             
           if (ProyectoItems && x >= ProyectoItems.length) {
                      //code
                      console.log("Cargando datos espere un momento vamos a insertar avances:::   X=" + x );
                      /*Verificar que se insertaron las alertas de proyectos
                      
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
      }, errorCB, function(){ console.log("SE consulto las alertas de proyectos OKKKK");});
                      
                      
                      */
                      
                     insertarAvanceFisicoProyectos(0);
           }
          
         
          
           else if (ProyectoItems && ProyectoItems.length > 0 && validaProyecto(ProyectoItems[x].id_Proyecto)) {
           
          
           console.log("Validando Proyecto = " + validaProyecto(ProyectoItems[x].id_Proyecto) +" idProyecto= "+ProyectoItems[x].id_Proyecto+" idDirectivo= "+ getGET().idDirectivo);
                      //code
            idProyecto=ProyectoItems[x].id_Proyecto;
            NombreLargo=ProyectoItems[x].nombre_largo;
            NombreCorto=ProyectoItems[x].nombre_corto;
            Descripcion=ProyectoItems[x].id_Descripcion;
            Dependencia=ProyectoItems[x].id_dependencia;
            Secretaria=ProyectoItems[x].id_secretaria;
            LimiteDesvio="15%";
            Desvio="0%";
                                                            // LimiteDesvio=items[0].nombre_corto;
                                                            // Desvio=items[0].nombre_corto;
            idUbicacion=ProyectoItems[x].id_ubicacion;
            
           
           
           //code Insertando proyectos
             db.transaction(function(tx) {
                       console.log("Cargando datos espere un momento :::   X=" + x + " Proyectos: "+ ProyectoItems.length);
                   
                 tx.executeSql('INSERT INTO Proyectos (idProyecto, NombreLargo, NombreCorto, Descripcion, Dependencia, Secretaria,LimiteDesvio, Desvio, idUbicacion)' +
                               'VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)',
                                [idProyecto, NombreLargo, NombreCorto, Descripcion, Dependencia, Secretaria, LimiteDesvio, Desvio, idUbicacion]);
                 //Insertando alerta de desvio fisico
                 tx.executeSql('INSERT INTO AlertasProyectos (idAlerta, idProyecto, LimiteDesvio, Tipo)' +
                              'VALUES (?, ?, ?, ?)',
                               [x+x+1, idProyecto, 100, 0]);
                  //Insertando alerta de desvio financiero
                 tx.executeSql('INSERT INTO AlertasProyectos (idAlerta, idProyecto, LimiteDesvio, Tipo)' +
                              'VALUES (?, ?, ?, ?)',
                               [x+x+2, idProyecto, 100, 1]);
                 
            }, errorCB, function (){
                console.log("Se inserto correctamente el proyecto: " + NombreLargo);
                //console.log("Se inserto correctamente la alerta fisica: " + cont + " idProyecto= " +idProyecto);
                //console.log("Se inserto correctamente la alerta financiera: " + cont+1 + " idProyecto= " +idProyecto);
                       x++;
                      
                       insertarInformacion(x);
                }); 
           
           } else {
                      console.log("No hay proyectos :::::::::::::::::::::::");
                      insertarAvanceFisicoProyectos(0);
           }
}


function insertarAvanceFisicoProyectos(x) {
           //code Insertando avances fisicos
           
          if (AvanceFisicoItems && x >= AvanceFisicoItems.length) {
                      //code
                     /*  console.log("VERIFICANDO AVANCES FISICOS DE OBRA 1 ::::::::::::::::::::::::::::::::::::::::::::::::::");
                        db.transaction(function(tx) {
                       tx.executeSql('select * from AvanceFisicoObras where idObra = "1"', [],  function(tx, rs) {
                                
                            console.log("Avances Fisicos Obras: " + rs.rows.length);
                            AOTI= rs.rows;
                            
                            for(var i = 0; i<rs.rows.length; i++){
                                   var resulset = rs.rows.item(i);
                                   console.log("resulset =" + resulset);
                                  // IdAvanceFisico, idReferencia, PAvanceFisico, Tendencia, FechaReporte, Estado
                                 console.log("Avances Temp Obras:::::: idObraFisico = " + resulset.IdAvanceFisico +"  idReferencia = "+resulset.idObra+" PAvanceFisico = "+resulset.PAvanceFisico+
                                             " FechaReporte = " +resulset.FechaReporte);
                                 
                            }
              }, errorCB);
                      
                       }, errorCB, function(){console.log("OKKKKKKKKK");}); 
                      
                     */ 
                      
                      console.log("Cargando datos espere un momento vamos a insertar avances financieros:::   X=" + x );
                     /* db.transaction(function(tx) {
                          tx.executeSql('select * from AvanceFisicoObras',[],  function(tx, rs) {
                            console.log("avances: " + rs.rows.length);
                          }, errorCB);
                      }, errorCB, successConsulta);
                      */
                     insertarAvanceFinancieroProyectos(0);
           }
        
           else if (AvanceFisicoItems && AvanceFisicoItems.length > 0) {
           console.log("Cargando datos espere un momento ::: 4 X=" + x + " Avances: "+ AvanceFisicoItems.length);
           
            IdAvanceFisico = AvanceFisicoItems[x].id_AvanceFisico;
            idReferencia = AvanceFisicoItems[x].id_referencia;
            PAvanceFisico = AvanceFisicoItems[x].pavanceFisico;
            /*if (PAvanceFisico == 0) {
                      //code
                      PAvanceFisico = 1;
            }*/
            //PAvanceFisico=String(AvanceFisicoItems[x].pavanceFisico);
            //Tendencia = AvanceFisicoItems[x].porcentaje_tendencia;
            if (AvanceFisicoItems[x].porcentaje_tendencia == 0) {
                      //code
                      Tendencia = 1;
            }else{
                      Tendencia = AvanceFisicoItems[x].porcentaje_tendencia;     
            }
            //Tendencia=String(AvanceFisicoItems[x].porcentaje_tendencia);
            FechaReporte = AvanceFisicoItems[x].fechaReporte;
            
            //var f = new Date();
            //document.write(f.getDate() + "/" + (f.getMonth() +1) + "/" + f.getFullYear());
            
             //FechaReporte = f.getDate() + "/" + (f.getMonth() +1) + "/" + f.getFullYear();
            
            Estado = AvanceFisicoItems[x].estado;
            
            switch (Estado) {
                      case 0:
                                 Estado = "Atrasado";
                                 break;
                      case 1:
                                 Estado = "Conforme a programa";
                                 break;
                      case 2:
                                 Estado = "Adelantado";
                                 break;
                      default:
                                 Estado = "No hay datos";
                                 break;
            }
            TipoEntidad=AvanceFisicoItems[x].tipo_Entidad;
           
              
           db.transaction(function(tx) {
                  if (TipoEntidad == 0 || TipoEntidad == "0") {
                                tx.executeSql('INSERT INTO AvanceFisicoProyectos(IdAvanceFisico, idProyecto, PAvanceFisico, Tendencia, FechaReporte, Estado)' +
                               ' VALUES (?, ?, ?, ?, ?, ?)',
                                [IdAvanceFisico, idReferencia, PAvanceFisico, Tendencia, FechaReporte, Estado]);
                                 console.log("Avance Fisico en Proyectos: " + IdAvanceFisico);
                  } if (TipoEntidad == 1 || TipoEntidad == "1") {
                                tx.executeSql('INSERT INTO AvanceFisicoObras(IdAvanceFisico, idObra, PAvanceFisico, Tendencia, FechaReporte, Estado)' +
                               ' VALUES (?, ?, ?, ?, ?, ?)',
                                [IdAvanceFisico, idReferencia, PAvanceFisico, Tendencia, FechaReporte, Estado]);
                                
                                /*tx.executeSql('INSERT INTO AvanceFisicoObras(IdAvanceFisico, idObra, PAvanceFisico, Tendencia, FechaReporte, Estado)' +
                               ' VALUES (?, ?, ?, ?, ?, ?)',
                                ["3", "1", 10, 5, "17/09/2014", "Conforme a programa"]);
                                tx.executeSql('INSERT INTO AvanceFisicoObras(IdAvanceFisico, idObra, PAvanceFisico, Tendencia, FechaReporte, Estado)' +
                               ' VALUES (?, ?, ?, ?, ?, ?)',
                                ["1", "1", "20", "5", "18/09/2014", "0"]);
                                tx.executeSql('INSERT INTO AvanceFisicoObras(IdAvanceFisico, idObra, PAvanceFisico, Tendencia, FechaReporte, Estado)' +
                               ' VALUES (?, ?, ?, ?, ?, ?)',
                                ["1", "1", "30", "5", "19/09/2014", "0"]);
                                tx.executeSql('INSERT INTO AvanceFisicoObras(IdAvanceFisico, idObra, PAvanceFisico, Tendencia, FechaReporte, Estado)' +
                               ' VALUES (?, ?, ?, ?, ?, ?)',
                                ["1", "1", "40", "5", "20/09/2014", "0"]);
                                tx.executeSql('INSERT INTO AvanceFisicoObras(IdAvanceFisico, idObra, PAvanceFisico, Tendencia, FechaReporte, Estado)' +
                               ' VALUES (?, ?, ?, ?, ?, ?)',
                                ["1", "1", "50", "5", "21/09/2014", "0"]);
                                console.log("Avance Fisico en Obras: " + IdAvanceFisico);
                                */
                  }
              
            }, errorCB, function (){
                console.log("Se inserto correctamente el avance: " + IdAvanceFisico);
               
                    x++;
                    insertarAvanceFisicoProyectos(x);
                   
                }); 
           
            } else {
                      console.log("No hay avances fisicos :::::::::::::::::::::::");
                       insertarAvanceFinancieroProyectos(0);
           }
}

function successConsulta() {
    //alert("consulta OK");
   
}

function insertarAvanceFinancieroProyectos(x) {
           
           
          if (AvanceFinancieroItems && x >= AvanceFinancieroItems.length) {
                      //code
                      console.log("Cargando datos espere un momento vamos a insertar obras:::   X=" + x );
                      insertarObras(0);
           }
        
           else if (AvanceFinancieroItems && AvanceFinancieroItems.length > 0) {
                      console.log("Cargando datos espere un momento ::: 4 X=" + x + " Avances: "+ AvanceFinancieroItems.length);       
           
            IdAvanceFinanciero =AvanceFinancieroItems[x].id_AvanceFinaciero;
            F_idReferencia=AvanceFinancieroItems[x].id_referencia;
            F_PAvanceFinanciero=AvanceFinancieroItems[x].pavanceFinanciero;
            //F_PAvanceFinanciero=String(AvanceFinancieroItems[x].pavanceFinanciero);
            /*if (F_PAvanceFinanciero == 1) {
                      //code
                      F_PAvanceFinanciero = 0;
            }*/
            //F_Tendencia=AvanceFinancieroItems[x].porcentaje_tendencia;
            F_Tendencia=String(AvanceFinancieroItems[x].porcentaje_tendencia);
            F_FechaReporte=AvanceFinancieroItems[x].fechaReporte;
             //var f = new Date();
            //document.write(f.getDate() + "/" + (f.getMonth() +1) + "/" + f.getFullYear());
            
             //FechaReporte = f.getDate() + "/" + (f.getMonth() +1) + "/" + f.getFullYear() + " " + "12:00:00";
            F_Estado=AvanceFinancieroItems[x].estado;
            switch (F_Estado) {
                      case 0:
                                 F_Estado = "Atrasado";
                                 break;
                      case 1:
                                 F_Estado = "Conforme a programa";
                                 break;
                      case 2:
                                 F_Estado = "Adelantado";
                                 break;
                      default:
                                 F_Estado = "No hay datos";
            }
            F_TipoEntidad=AvanceFinancieroItems[x].tipo_Entidad;
            
           db.transaction(function(tx) {
                   if (F_TipoEntidad == 0 || F_TipoEntidad == "0") {
                                tx.executeSql('INSERT INTO AvanceFinancieroProyectos(IdAvanceFinanciero, idProyecto, PAvanceFinanciero, Tendencia, FechaReporte, Estado)' +
                               ' VALUES (?, ?, ?, ?, ?, ?)',
                                [IdAvanceFinanciero, F_idReferencia, F_PAvanceFinanciero, F_Tendencia, F_FechaReporte, F_Estado]);
                                 console.log("Avance Financiero en Protectos: " + IdAvanceFisico);
                   }
                   if (F_TipoEntidad == 1 || F_TipoEntidad == "1") {
                                tx.executeSql('INSERT INTO AvanceFinancieroObras(IdAvanceFinanciero, idObra, PAvanceFinanciero, Tendencia, FechaReporte, Estado)' +
                               ' VALUES (?, ?, ?, ?, ?, ?)',
                                [IdAvanceFinanciero, F_idReferencia, F_PAvanceFinanciero, F_Tendencia, F_FechaReporte, F_Estado]);
                                 console.log("Avance Financiero en Obras: " + IdAvanceFisico);
                   }
            }, errorCB, function (){
                console.log("Se inserto correctamente el avance: " + IdAvanceFinanciero);
                    x++;
                    insertarAvanceFinancieroProyectos(x);
                }); 
           
           }  else {
                     console.log("No hay avances financieros :::::::::::::::::::::::");
                        insertarObras(0);
           }
}

var y = 0;
function insertarObras(x) {
           
          
          if (ObrasItems && x >= ObrasItems.length) {
                      //code
                      console.log("Obras insertadas ::::::::::::::::::: :::   X=" + x );
                     insertarUbicaciones(0);
           }
            
           else if (ObrasItems && ObrasItems.length > 0) {
           console.log("Cargando datos espere un momento ::: 4 X=" + x + " Obras: "+ ObrasItems.length);                
            O_idObra =ObrasItems[x].id_Obra;
            O_idProyecto=ObrasItems[x].id_Poyecto;
            O_NoContrato=ObrasItems[x].noContrato;
            O_RFC=ObrasItems[x].rfc;
            O_Nombre=ObrasItems[x].nombre;
            O_Gobierno=ObrasItems[x].idGobierno;
            O_Secretaria=ObrasItems[x].idSecretaria;
            O_Dependencia=ObrasItems[x].idDependencia;
            O_Direccion=ObrasItems[x].direccion;
            O_Subdireccion=ObrasItems[x].subdireccion;
            O_Area=ObrasItems[x].area;
            O_EmpresaContratista=ObrasItems[x].id_EmpresaContratista;
            O_Superintendente=ObrasItems[x].superintendente;
            O_EntidadFederativa=ObrasItems[x].entidadFederativa;
            O_Descripcion=ObrasItems[x].descripcion;
             
            O_FechaContrato=ObrasItems[x].fechaContrato;
            O_TipoContrato=ObrasItems[x].tipoContrato;
            
            O_ImporteContratoSinIVA=ObrasItems[x].importeContratoSinIVA;
            O_ImporteConvenioAmpliacion=ObrasItems[x].importeConvenioAmpliacion;
            O_ImporteConvenioReduccion=ObrasItems[x].importeConvenioReduccion;
            O_ImporteAjusteCostos=ObrasItems[x].importeAjusteCostos;
            
            O_FechaInicioContrato=ObrasItems[x].fechaInicioContrato;
            O_FechaTerminoContrato=ObrasItems[x].fechaTerminoContrato;
            
            O_PeriodoEjucionDias=ObrasItems[x].periodoEjucionDias;
            O_PartidaPresupuestal=ObrasItems[x].partidaPresupuestal;
            O_Anticipo=ObrasItems[x].anticipo;
            O_NoFianzaAnticipo=ObrasItems[x].noFianzaAnticipo;
             
            O_FechaFianzaAnticipo=ObrasItems[x].fechaFianzaAnticipo;
            O_MontoFianzaAnticipo=ObrasItems[x].montoFianzaAnticipo;
            O_NoFianzaCumplimiento=ObrasItems[x].noFianzaCumplimiento;
            O_FechaFianzaCumplimiento=ObrasItems[x].fechaFianzaCumplimiento;
            O_MontoFianzaCumplimiento=ObrasItems[x].montoFianzaCumplimiento;
            
            O_CargoRevision1=ObrasItems[x].cargoRevision1;
            O_NombreRevision1=ObrasItems[x].nombreRevision1;
            O_CargoRevision2=ObrasItems[x].cargoRevision2;
            O_NombreRevision2=ObrasItems[x].nombreRevision2;
            
            O_NombreQuienAutoriza=ObrasItems[x].nombreQuienAutoriza;
            O_CargoVoBo=ObrasItems[x].cargoVoBo;
            O_NombreVoBo=ObrasItems[x].nombreVoBo;
           
           O_NombreEjercicioFiscal1=ObrasItems[x].nombreEjercicioFiscal1;
           O_ImporteFiscal1SinIVA=ObrasItems[x].importeFiscal1SinIVA;
           O_Borrador=ObrasItems[x].borrador;
          
            O_LimiteDesvio=30;
            O_Desvio=0;
            O_Ubicacion=ObrasItems[x].id_ubicacion;
            //O_LimiteDesvio=ObrasItems[x].;
            //O_Desvio=ObrasItems[x].;
          
          
           db.transaction(function(tx) {
                                
                                tx.executeSql('INSERT INTO Obras(' +
                                              'idObra, idProyecto, NoContrato, RFC, Nombre, Gobierno, Secretaria, Dependencia, Direccion, '+
                                              'Subdireccion, Area, EmpresaContratista, Superintendente, EntidadFederativa, Descripcion, FechaContrato, '+
                                              'TipoContrato, ImporteContratoSinIVA, ImporteConvenioAmpliacion, ImporteConvenioReduccion, ImporteAjusteCostos, '+
                                              'FechaInicioContrato, PeriodoEjucionDias, PartidaPresupuestal, Anticipo, NoFianzaAnticipo, FechaFianzaAnticipo, '+
                                              'MontoFianzaAnticipo, NoFianzaCumplimiento, FechaFianzaCumplimiento, MontoFianzaCumplimiento, CargoRevision1, NombreRevision1, '+
                                              'CargoRevision2, NombreRevision2, NombreQuienAutoriza, CargoVoBo, NombreVoBo, LimiteDesvio, Desvio, fechaTerminoContrato, '+
                                              'nombreEjercicioFiscal1, importeFiscal1SinIVA, Borrador, idUbicacion)' +
                               ' VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)',
                                [ O_idObra,
                                   O_idProyecto,
                                   O_NoContrato,
                                   O_RFC,
                                   O_Nombre,
                                   O_Gobierno,
                                   O_Secretaria,
                                   O_Dependencia,
                                   O_Direccion,
                                   O_Subdireccion,
                                   O_Area,
                                   O_EmpresaContratista,
                                   O_Superintendente,
                                   O_EntidadFederativa,
                                   O_Descripcion,
                                   O_FechaContrato,
                                   O_TipoContrato,
                                   O_ImporteContratoSinIVA,
                                   O_ImporteConvenioAmpliacion,
                                   O_ImporteConvenioReduccion,
                                   O_ImporteAjusteCostos,
                                   O_FechaInicioContrato,
                                   O_PeriodoEjucionDias,
                                   O_PartidaPresupuestal,
                                   O_Anticipo,
                                   O_NoFianzaAnticipo,
                                   O_FechaFianzaAnticipo,
                                   O_MontoFianzaAnticipo,
                                   O_NoFianzaCumplimiento,
                                   O_FechaFianzaCumplimiento,
                                   O_MontoFianzaCumplimiento,
                                   O_CargoRevision1,
                                   O_NombreRevision1,
                                   O_CargoRevision2,
                                   O_NombreRevision2,
                                   O_NombreQuienAutoriza,
                                   O_CargoVoBo,
                                   O_NombreVoBo,
                                   O_LimiteDesvio,
                                   O_Desvio,
                                   O_FechaTerminoContrato,
                                   O_NombreEjercicioFiscal1,
                                   O_ImporteFiscal1SinIVA,
                                   O_Borrador,
                                   O_Ubicacion
                                 ]);
                            
                  //Insertando alerta de desvio fisico
                 tx.executeSql('INSERT INTO AlertasObras (idAlertaObra, idObra, LimiteDesvio, Tipo)' +
                              'VALUES (?, ?, ?, ?)',
                               [x+x+1, O_idObra, 100, 0]);
                  //Insertando alerta de desvio financiero
                 tx.executeSql('INSERT INTO AlertasObras (idAlertaObra, idObra, LimiteDesvio, Tipo)' +
                              'VALUES (?, ?, ?, ?)',
                               [x+x+2, O_idObra, 100, 1]);
                 //console.log("AlertasObras: ");
                   
            }, errorCB, function (){
                console.log("Se inserto correctamente la obra: " + O_idObra);
                    x++;
                    //contObra++;
                    insertarObras(x);
                }); 
           
           } else {
                      console.log("No hay obras :::::::::::::::::::::::");
                      insertarUbicaciones(0);
           }
}

function insertarUbicaciones(x) {
           
           console.log("Cargando datos espere un momento ::: 4 X=" + x + " Ubicaciones: "+ UbicacionItems.length);       
          if (UbicacionItems && x >= UbicacionItems.length) {
                      //code
                      console.log("Cargando datos espere un momento vamos a actualizar empresas:::   X=" + x );
                      insertarMaquinaria(0);
           }
        
           else if (UbicacionItems && UbicacionItems.length > 0) {
                     
             var IdUbicacion = UbicacionItems[x].id_Ubicacion;
             var U_Ubicacion = ""; //= UbicacionItems[x].ubicacion; //Arreglo de ubicaciones
             
             console.log("IdUbicacion= " +IdUbicacion+" Puntos= " + UbicacionItems[x].ubicacion.length);
             for(var i=0; i< UbicacionItems[x].ubicacion.length; i++){
                      
                      if (UbicacionItems[x].ubicacion.length==1) {
                       U_Ubicacion = UbicacionItems[x].ubicacion[i];
                       console.log("Solo es un punto de Ubicacion " + U_Ubicacion);       //code
                      }else if(i == UbicacionItems[x].ubicacion.length-1){
                              U_Ubicacion = U_Ubicacion + UbicacionItems[x].ubicacion[i];
                      console.log("Ultimo Punto de Ubicacion " + U_Ubicacion);    
                                 
                      }else{
                              U_Ubicacion = U_Ubicacion + UbicacionItems[x].ubicacion[i] + "|";
                      console.log("Puntos de Ubicacion " + U_Ubicacion);    
                                 
                      }
            
             }
          
            console.log("Puntos de Ubicacion " + " idUbicacion: "+IdUbicacion + " Ubicacion: " +U_Ubicacion);    
            db.transaction(function(tx) {
                                tx.executeSql('INSERT INTO Ubicacion(idUbicacion, puntos)' +
                               'VALUES (?, ?)',
                                [IdUbicacion, U_Ubicacion]);
                  
            }, errorCB, function (){
                console.log("::::::::::::::::: Se inserto correctamente la ubicacion: " + IdUbicacion);
                    x++;
                    insertarUbicaciones(x);
                }); 
           
           } else {
                      console.log("No hay ubicaciones :::::::::::::::::::::::");
                      insertarMaquinaria(0);
           }
}

function insertarMaquinaria(x) {
      
           if (MaquinariaItems && x >= MaquinariaItems.length) {
                      //code
                      console.log("Cargando datos espere un momento vamos a insertar multimedia:::   X=" + x );
                      insertarMultimedia(0);
           } else if (MaquinariaItems && MaquinariaItems.length > 0) {
           
                     //code
            idMaquinaria=MaquinariaItems[x].id_Maquinaria;
            NombreMaquinaria=MaquinariaItems[x].nombre;
            DescripcionMaquinaria=MaquinariaItems[x].descripcion;
            TipoMaquinaria=MaquinariaItems[x].id_tipo_Maquinaria;
            switch (TipoMaquinaria) {
                      //case
                      case 1:
                                 Tipo_Maq = "Pesada";
                                 break;
                      case 2:
                                 Tipo_Maq = "Ligera";
                                 break;
                      case 3:
                                 Tipo_Maq = "Equipo";
                                 break;
                      default:
                                 Tipo_Maq = "NA";
                                 break;
            }
            
           
           
           //code Insertando proyectos
             db.transaction(function(tx) {
                       console.log("Cargando datos espere un momento :::   X=" + x + " Maquinaria: "+ MaquinariaItems.length);
                   
                 tx.executeSql('INSERT INTO Maquinaria (idMaquinaria, nombre, descripcion, tipo)' +
                               'VALUES (?, ?, ?, ?)',
                                [idMaquinaria, NombreMaquinaria, DescripcionMaquinaria, Tipo_Maq]);
                
                 
            }, errorCB, function (){
                console.log("Se inserto correctamente la maquinaria: " + NombreMaquinaria);
                //console.log("Se inserto correctamente la alerta fisica: " + cont + " idProyecto= " +idProyecto);
                //console.log("Se inserto correctamente la alerta financiera: " + cont+1 + " idProyecto= " +idProyecto);
                       x++;
                      
                       insertarMaquinaria(x);
                }); 
           
           } else {
                      console.log("No hay proyectos :::::::::::::::::::::::");
                      insertarMultimedia(0);
           }
}
function insertarMultimedia(x) {
           
           if (MultimediaItems && x >= MultimediaItems.length) {
                      //code
                      console.log("Cargando datos espere un momento vamos a insertar empresas:::   X=" + x );
                      insertarEmpresas(0);
                      //insertarProgramaProyecto(0);
           }
  
           else if (MultimediaItems && MultimediaItems.length > 0) {
                      console.log("Cargando datos espere un momento ::: 4 X=" + x + " Multimedia: "+ MultimediaItems.length);       
            idMultimedia =MultimediaItems[x].idMultimedia;
            M_idReferencia=MultimediaItems[x].idReferencia;
            M_TipoEntidad=MultimediaItems[x].tipoReferencia;
            M_Path=MultimediaItems[x].path;
            //M_TipoArchivo=MultimediaItems[x].tipoArchivo;
            M_TipoArchivo=String(MultimediaItems[x].tipoArchivo);
            //M_TipoArchivo="1";
            M_Fecha=MultimediaItems[x].fecha;
            //M_Descripcion="Descripcion";
            M_Descripcion=MultimediaItems[x].descripcion;
           db.transaction(function(tx) {
                   if (M_TipoEntidad == 0 || M_TipoEntidad == "0") {
                       console.log("Insertando multimedia Proyectos: " + idMultimedia + " idProyecto : "+M_idReferencia); 
                                tx.executeSql('INSERT INTO MultimediaProyectos(idMultimedia, idProyecto, TipoArchivo, Path, Fecha, Descripcion)' +
                               ' VALUES (?, ?, ?, ?, ?, ?)',
                                [idMultimedia, M_idReferencia, M_TipoArchivo, M_Path, M_Fecha, M_Descripcion]);
                   }
                   if (M_TipoEntidad == 1 || M_TipoEntidad == "1") {
                                console.log("Insertando multimedia Obras: " + idMultimedia + " idObra : "+M_idReferencia); 
                                tx.executeSql('INSERT INTO MultimediaObras(idMultimedia, idObra, TipoArchivo, Path, Fecha, Descripcion)' +
                               ' VALUES (?, ?, ?, ?, ?, ?)',
                                [idMultimedia, M_idReferencia, M_TipoArchivo, M_Path, M_Fecha, M_Descripcion]);
                   }
            }, errorCB, function (){
                console.log("Se inserto correctamente el multimedia: " + idMultimedia);
                    x++;
                    insertarMultimedia(x);
                }); 
           
           } else {
                      console.log("No hay avances multimedia :::::::::::::::::::::::");
                      insertarEmpresas(0);
                      //insertarProgramaProyecto(0);
           }
           
           /*
          if (MultimediaItems && x >= MultimediaItems.length) {
                      //code
                      console.log("Cargando datos espere un momento vamos a insertar empresas:::   X=" + x );
                      //insertarEmpresas(0);
                      insertarProgramaProyecto(0);
           }
  
           else if (MultimediaItems && MultimediaItems.length > 0) {
                      console.log("Cargando datos espere un momento ::: 4 X=" + x + " Multimedia: "+ MultimediaItems.length);       
            idMultimedia =MultimediaItems[x].idMultimedia;
            M_idReferencia=MultimediaItems[x].idReferencia;
            M_TipoEntidad=MultimediaItems[x].tipoReferencia;
            M_Path=MultimediaItems[x].path;
            M_TipoArchivo=MultimediaItems[x].tipoArchivo;
            M_Fecha=MultimediaItems[x].fecha;
            //M_Descripcion="Descripcion";
            M_Descripcion=MultimediaItems[x].descripcion;
            //console.log("Antes de entrar al file path:" + );
           db.transaction(function(tx) {
                      //Al insertar Multimedia
                      //---------
                     window.requestFileSystem(
                     LocalFileSystem.PERSISTENT, 0, 
                     function onFileSystemSuccess(fileSystem) {
                     fileSystem.root.getFile(
                                 "dummy.html", {create: true, exclusive: false}, 
                                 function gotFileEntry(fileEntry){
                                 var sPath = fileEntry.fullPath.replace("dummy.html","");
                                 var fileTransfer = new FileTransfer();
                                 fileEntry.remove();
                                 
                                       fileTransfer.download(
                                           "http://1-dot-focal-furnace-615.appspot.com/serve?blob-key="+MultimediaItems[x].path, /////path
                                           sPath + "img_"+M_Descripcion+"_"+M_idReferencia+"_"+x+".jpg",
                                           function(theFile) {
                                           console.log("download complete: " + theFile.toURL());
                                           //alert("MPATH==="+ theFile.toURL() + "X==="+x);
                                           M_Path = theFile.toURL();
                                           console.log("Se inserto correctamente el path: " + M_Path);
                      if (M_TipoEntidad == 0 || M_TipoEntidad == "0") {
                      
                       console.log("Insertando multimedia Proyectos: " + idMultimedia + " idProyecto : "+M_idReferencia + "path: "+ M_Path); 
                                tx.executeSql('INSERT INTO MultimediaProyectos(idMultimedia, idProyecto, TipoArchivo, Path, Fecha, Descripcion)' +
                               ' VALUES (?, ?, ?, ?, ?, ?)',
                                [idMultimedia, M_idReferencia, M_TipoArchivo, M_Path, M_Fecha, M_Descripcion]);
                   }
                   if (M_TipoEntidad == 1 || M_TipoEntidad == "1") {
                      //alert("img ==" + M_Path);
                                console.log("Insertando multimedia Obras: " + idMultimedia + " idObra : "+M_idReferencia + "path: "+ M_Path); 
                                tx.executeSql('INSERT INTO MultimediaObras(idMultimedia, idObra, TipoArchivo, Path, Fecha, Descripcion)' +
                               ' VALUES (?, ?, ?, ?, ?, ?)',
                                [idMultimedia, M_idReferencia, M_TipoArchivo, M_Path, M_Fecha, M_Descripcion]);
                   }
                                           },
                                           function(error) {
                                           console.log("download error source " + error.source);
                                           console.log("download error target " + error.target);
                                           console.log("upload error code: " + error.code);
                                           }
                                           );
                                 }, 
                                 fail);
                     }, 
                     fail);
                     //--------------
                   
            }, errorCB, function (){
                console.log("Se inserto correctamente el multimedia: " + idMultimedia);
                
                    x++;
                    insertarMultimedia(x);
                }); 
           
           } else {
                      console.log("No hay avances multimedia :::::::::::::::::::::::");
                       //insertarEmpresas(0);
                       insertarProgramaProyecto(0);
           }
           */
}







function insertarEmpresas(x) {
           
          if (EmpresaItems && x >= EmpresaItems.length) {
                      //code
                      console.log("Todas las inserciones hasta el momento terminadas::: ");
                     // otra insercicion
                     insertarProgramaProyecto(0);
                      //location.href="mapa_proyectos.html";
           }

        else if (EmpresaItems && EmpresaItems.length > 0) {
                     console.log("Cargando datos espere un momento ::: 4 X= " + x + " Empresas: "+ EmpresaItems.length);
           var IdEmpresa = EmpresaItems[x].id_Empresa;
           var E_RFC = EmpresaItems[x].rfc;
           var E_Nombre = EmpresaItems[x].nombre;
           var E_Siglas = EmpresaItems[x].siglas;
           var E_Calle = EmpresaItems[x].calle;
           var E_Num_int = EmpresaItems[x].num_Int;
           var E_Num_ext = EmpresaItems[x].num_Ext;
           var E_CP = EmpresaItems[x].codi_Postal;
           var E_Colonia = EmpresaItems[x].colonia;
           var E_Deleg = EmpresaItems[x].del_o_Mun;
           var E_entidad = EmpresaItems[x].entidad;
           var tipoEmpresa = EmpresaItems[x].id_tipo_empresa;
    
         
                      console.log("tipo empresa =" +tipoEmpresa);

           // tipoEmpresa = "1";
           switch (tipoEmpresa){
                      //case
                      case '1':
                                 db.transaction(function(tx) {
                                     tx.executeSql('INSERT INTO Contratista(idContratista, rfc, nombre, siglas, calle, num_Int, num_ext, CP, colonia, delegacion, entidad)' +  'VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)', [IdEmpresa, E_RFC, E_Nombre, E_Siglas, E_Calle, E_Num_int, E_Num_ext, E_CP, E_Colonia, E_Deleg, E_entidad]);
                                     //tx.executeSql('INSERT INTO Contratista(idContratista, rfc, nombre, siglas)' +  'VALUES (?, ?, ?, ?)', [1, RFC123456ABC, CONAGUA, CONAGUA]);
                                 }, errorCB, function (){
                                         console.log("::::::::::::::::: Se inserto correctamente la empresa: " + IdEmpresa);
                                         x++;
                                         insertarEmpresas(x);
                                 }); 
                                 break;
                      case '2':
                                 db.transaction(function(tx) {
                                     tx.executeSql('INSERT INTO Supervisora(idSupervisora, rfc, nombre, siglas, calle, num_Int, num_ext, CP, colonia, delegacion, entidad)' +  'VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)', [IdEmpresa, E_RFC, E_Nombre, E_Siglas, E_Calle, E_Num_int, E_Num_ext, E_CP, E_Colonia, E_Deleg, E_entidad]);
                                     //tx.executeSql('INSERT INTO Supervisora(idSupervisora, rfc, nombre, siglas)' +  'VALUES (?, ?, ?, ?)', [IdEmpresa, E_RFC, E_Nombre, E_Siglas]);
                                     //tx.executeSql('INSERT INTO Contratista(idContratista, rfc, nombre, siglas)' +  'VALUES (?, ?, ?, ?)', [2, RFC123456ABC, CONAGUA, CONAGUA]);
                                 }, errorCB, function (){
                                         console.log("::::::::::::::::: Se inserto correctamente la empresa: " + IdEmpresa);
                                         x++;
                                         insertarEmpresas(x);
                                 }); 
                                 break;
                      case '3':
                                 db.transaction(function(tx) {
                                     tx.executeSql('INSERT INTO Dependencia(idDependencia, rfc, nombre, siglas, calle, num_Int, num_ext, CP, colonia, delegacion, entidad)' +  'VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)', [IdEmpresa, E_RFC, E_Nombre, E_Siglas, E_Calle, E_Num_int, E_Num_ext, E_CP, E_Colonia, E_Deleg, E_entidad]);
                                     //tx.executeSql('INSERT INTO Dependencia(idDependencia, rfc, nombre, siglas)' +  'VALUES (?, ?, ?, ?)', [IdEmpresa, E_RFC, E_Nombre, E_Siglas]);
                                     //tx.executeSql('INSERT INTO Contratista(idContratista, rfc, nombre, siglas)' +  'VALUES (?, ?, ?, ?)', [3, RFC123456ABC, CONAGUA, CONAGUA]);
                                 }, errorCB, function (){
                                         console.log("::::::::::::::::: Se inserto correctamente la empresa: " + IdEmpresa);
                                         x++;
                                         insertarEmpresas(x);
                                 }); 
                                 break;
                      case '4':
                                 db.transaction(function(tx) {
                                     tx.executeSql('INSERT INTO Particular(idParticular, rfc, nombre, siglas, calle, num_Int, num_ext, CP, colonia, delegacion, entidad)' +  'VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)', [IdEmpresa, E_RFC, E_Nombre, E_Siglas, E_Calle, E_Num_int, E_Num_ext, E_CP, E_Colonia, E_Deleg, E_entidad]);
                                     //tx.executeSql('INSERT INTO Particular(idParticular, rfc, nombre, siglas)' +  'VALUES (?, ?, ?, ?)', [IdEmpresa, E_RFC, E_Nombre, E_Siglas]);
                                    // tx.executeSql('INSERT INTO Contratista(idContratista, rfc, nombre, siglas)' +  'VALUES (?, ?, ?, ?)', [4, RFC123456ABC, CONAGUA, CONAGUA]);
                                 }, errorCB, function (){
                                         console.log("::::::::::::::::: Se inserto correctamente la empresa: " + IdEmpresa);
                                         x++;
                                         insertarEmpresas(x);
                                 }); 
                                 break;
                      case '5':
                                 db.transaction(function(tx) {
                                     tx.executeSql('INSERT INTO Secretaria(idSecretaria, rfc, nombre, siglas, calle, num_Int, num_ext, CP, colonia, delegacion, entidad)' +  'VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)', [IdEmpresa, E_RFC, E_Nombre, E_Siglas, E_Calle, E_Num_int, E_Num_ext, E_CP, E_Colonia, E_Deleg, E_entidad]);
                                     //tx.executeSql('INSERT INTO Secretaria(idSecretaria, rfc, nombre, siglas)' +  'VALUES (?, ?, ?, ?)', [IdEmpresa, E_RFC, E_Nombre, E_Siglas]);
                                     //tx.executeSql('INSERT INTO Contratista(idContratista, rfc, nombre, siglas)' +  'VALUES (?, ?, ?, ?)', [5, RFC123456ABC, CONAGUA, CONAGUA]);
                                 }, errorCB, function (){
                                         console.log("::::::::::::::::: Se inserto correctamente la empresa: " + IdEmpresa);
                                         x++;
                                         insertarEmpresas(x);
                                 }); 
                                 break;
                      case '6':
                                 db.transaction(function(tx) {
                                     tx.executeSql('INSERT INTO Gobierno(idGobierno, rfc, nombre, siglas, calle, num_Int, num_ext, CP, colonia, delegacion, entidad)' +  'VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)', [IdEmpresa, E_RFC, E_Nombre, E_Siglas, E_Calle, E_Num_int, E_Num_ext, E_CP, E_Colonia, E_Deleg, E_entidad]);
                                     //tx.executeSql('INSERT INTO Gobierno(idGobierno, rfc, nombre, siglas)' +  'VALUES (?, ?, ?, ?)', [IdEmpresa, E_RFC, E_Nombre, E_Siglas]);
                                     //tx.executeSql('INSERT INTO Contratista(idContratista, rfc, nombre, siglas)' +  'VALUES (?, ?, ?, ?)', [6, RFC123456ABC, CONAGUA, CONAGUA]);
                                 }, errorCB, function (){
                                         console.log("::::::::::::::::: Se inserto correctamente la empresa: " + IdEmpresa);
                                         x++;
                                         insertarEmpresas(x);
                                 }); 
                                 break;
                      default:
                                 console.log("Tipo de empresa no encontrado");
                                 break;
           } //switch
           
          
           } else {
                      console.log("No hay empresas :::::::::::::::::::::::");
                      insertarProgramaProyecto(0);
           }
} 
//Funcion de progrma
function insertarProgramaProyecto(x) {
           //code
           /*idPrograma = "1";
           idProyecto = "1";
           TipoAvance = "Fisico";
           Fecha = "18/09/2014";
           PorcentajeAvance = "12";
           */
           db.transaction(function(tx) {
                      console.log("Cargando datos ::: Programa: ");
                   
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [1, 1, 0, "26/10/2014", "0"]);    
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [2, 1, 0, "18/11/2014", "5"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [3, 1, 0, "1/12/2014", "9"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [4, 1, 0, "3/01/2015", "13"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [5, 1, 0, "30/01/2015", "17"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [6, 1, 0, "20/02/2015", "21"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [7, 1, 0, "08/03/2015", "25"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [8, 1, 0, "02/04/2015", "29"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [9, 1, 0, "28/04/2015", "33"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [10, 1, 0, "20/05/2015", "37"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [11, 1, 0, "15/06/2015", "41"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [12, 1, 0, "10/07/2015", "45"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [13, 1, 0, "30/08/2015", "49"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [14, 1, 0, "26/09/2015", "53"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [15, 1, 0, "18/10/2015", "57"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [16, 1, 0, "06/11/2015", "61"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [17, 1, 0, "30/11/2015", "66"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [18, 1, 0, "20/12/2015", "70"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [19, 1, 0, "10/01/2016", "74"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [20, 1, 0, "02/02/2016", "78"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [21, 1, 0, "22/02/2016", "82"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [22, 1, 0, "14/03/2016", "86"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [23, 1, 0, "02/03/2016", "90"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [24, 1, 0, "24/03/2016", "95"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [25, 1, 0, "10/04/2016", "100"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [26, 1, 1, "26/10/2014", "0"]);    
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [27, 1, 1, "18/11/2014", "5"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [28, 1, 1, "1/12/2014", "9"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [29, 1, 1, "3/01/2015", "13"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [30, 1, 1, "30/01/2015", "17"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [31, 1, 1, "20/02/2015", "21"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [32, 1, 1, "08/03/2015", "25"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [33, 1, 1, "02/04/2015", "29"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [34, 1, 1, "28/04/2015", "33"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [35, 1, 1, "20/05/2015", "37"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [36, 1, 1, "15/06/2015", "41"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [37, 1, 1, "10/07/2015", "45"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [38, 1, 1, "30/08/2015", "49"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [39, 1, 1, "26/09/2015", "53"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [40, 1, 1, "18/10/2015", "57"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [41, 1, 1, "06/11/2015", "61"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [42, 1, 1, "30/11/2015", "66"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [43, 1, 1, "20/12/2015", "70"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [44, 1, 1, "10/01/2016", "74"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [45, 1, 1, "02/02/2016", "78"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [46, 1, 1, "22/02/2016", "82"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [47, 1, 1, "14/03/2016", "86"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [48, 1, 1, "02/03/2016", "90"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [49, 1, 1, "24/03/2016", "95"]);
                 tx.executeSql('INSERT INTO Programa_Proyecto (idPrograma, idProyecto, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [50, 1, 1, "10/04/2016", "100"]);
                 
            }, errorCB, function (){
                console.log("Se inserto correctamente el programa de Proyectos: ");
                       //x++; 
                       insertarProgramaObra(0);
                       //location.href="mapa_proyectos.html";
                }); 
           
}

function insertarProgramaObra(x) {
           //code
           /*idPrograma = "1";
           idObra = "1";
           TipoAvance = "Fisico";
           Fecha = "18/09/2014";
           PorcentajeAvance = "12";
           */
           db.transaction(function(tx) {
                      console.log("Cargando datos ::: Programa de obra: ");
                   
                 tx.executeSql('INSERT INTO Programa_Obra (idPrograma, idObra, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [1, 1, 0, "20/10/2014", "0"]);
                 tx.executeSql('INSERT INTO Programa_Obra (idPrograma, idObra, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [2, 1, 0, "31/10/2014", "12"]);
                 tx.executeSql('INSERT INTO Programa_Obra (idPrograma, idObra, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [3, 1, 0, "05/11/2014", "18"]);
                 tx.executeSql('INSERT INTO Programa_Obra (idPrograma, idObra, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [4, 1, 0, "18/11/2014", "24"]);
                 tx.executeSql('INSERT INTO Programa_Obra (idPrograma, idObra, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [5, 1, 0, "25/11/2014", "34"]);
                 tx.executeSql('INSERT INTO Programa_Obra (idPrograma, idObra, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [6, 1, 0, "04/12/2014", "41"]);
                 tx.executeSql('INSERT INTO Programa_Obra (idPrograma, idObra, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [7, 1, 0, "13/12/2014", "55"]);
                 tx.executeSql('INSERT INTO Programa_Obra (idPrograma, idObra, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [8, 1, 0, "26/12/2014", "62"]);
                 tx.executeSql('INSERT INTO Programa_Obra (idPrograma, idObra, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [9, 1, 0, "03/01/2015", "68"]);
                 tx.executeSql('INSERT INTO Programa_Obra (idPrograma, idObra, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [10, 1, 0, "15/01/2015", "70"]);
                 tx.executeSql('INSERT INTO Programa_Obra (idPrograma, idObra, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [11, 1, 1, "18/09/2014", "0"]);//0
                 tx.executeSql('INSERT INTO Programa_Obra (idPrograma, idObra, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [12, 1, 1, "30/09/2014", "12"]);//12
                 tx.executeSql('INSERT INTO Programa_Obra (idPrograma, idObra, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [13, 1, 1, "10/10/2014", "18"]);//18
                 tx.executeSql('INSERT INTO Programa_Obra (idPrograma, idObra, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [14, 1, 1, "24/10/2014", "27"]);//27
                 tx.executeSql('INSERT INTO Programa_Obra (idPrograma, idObra, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [15, 1, 1, "4/11/2014", "36"]);//36
                 tx.executeSql('INSERT INTO Programa_Obra (idPrograma, idObra, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [16, 1, 1, "19/11/2014", "42"]);//42
                 tx.executeSql('INSERT INTO Programa_Obra (idPrograma, idObra, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [17, 1, 1, "2/12/2014", "54"]);//54
                 tx.executeSql('INSERT INTO Programa_Obra (idPrograma, idObra, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [18, 1, 1, "12/12/2014", "62"]);//62, 70, 78
                 tx.executeSql('INSERT INTO Programa_Obra (idPrograma, idObra, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [19, 2, 0, "18/09/2014", "0"]);
                 tx.executeSql('INSERT INTO Programa_Obra (idPrograma, idObra, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [20, 2, 0, "20/09/2014", "12"]);//12
                 tx.executeSql('INSERT INTO Programa_Obra (idPrograma, idObra, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [21, 2, 0, "30/09/2014", "18"]);//18
                 tx.executeSql('INSERT INTO Programa_Obra (idPrograma, idObra, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [22, 2, 0, "10/10/2014", "26"]);//26
                 tx.executeSql('INSERT INTO Programa_Obra (idPrograma, idObra, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [23, 2, 0, "24/10/2014", "34"]);//34
                 tx.executeSql('INSERT INTO Programa_Obra (idPrograma, idObra, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [24, 2, 0, "4/11/2014", "41"]);//41
                 tx.executeSql('INSERT INTO Programa_Obra (idPrograma, idObra, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [25, 2, 0, "19/11/2014", "55"]);//55
                 tx.executeSql('INSERT INTO Programa_Obra (idPrograma, idObra, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [26, 2, 0, "2/12/2014", "62"]);//62
                 tx.executeSql('INSERT INTO Programa_Obra (idPrograma, idObra, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [27, 2, 0, "12/12/2014", "68"]);//68
                 tx.executeSql('INSERT INTO Programa_Obra (idPrograma, idObra, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [28, 2, 1, "18/09/2014", "0"]);
                 tx.executeSql('INSERT INTO Programa_Obra (idPrograma, idObra, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [29, 2, 1, "24/09/2014", "10"]);//10
                 tx.executeSql('INSERT INTO Programa_Obra (idPrograma, idObra, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [30, 2, 1, "30/09/2014", "18"]);//18
                 tx.executeSql('INSERT INTO Programa_Obra (idPrograma, idObra, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [31, 2, 1, "10/10/2014", "27"]);//27
                 tx.executeSql('INSERT INTO Programa_Obra (idPrograma, idObra, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [32, 2, 1, "24/10/2014", "36"]);//36
                 tx.executeSql('INSERT INTO Programa_Obra (idPrograma, idObra, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [33, 2, 1, "4/11/2014", "42"]);//42
                 tx.executeSql('INSERT INTO Programa_Obra (idPrograma, idObra, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [34, 2, 1, "19/11/2014", "54"]);//54
                 tx.executeSql('INSERT INTO Programa_Obra (idPrograma, idObra, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [35, 2, 1, "2/12/2014", "62"]);//62
                 tx.executeSql('INSERT INTO Programa_Obra (idPrograma, idObra, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [36, 2, 1, "12/12/2014", "70"]);//70
                 tx.executeSql('INSERT INTO Programa_Obra (idPrograma, idObra, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [37, 1, 1, "20/12/2014", "70"]);
                 tx.executeSql('INSERT INTO Programa_Obra (idPrograma, idObra, TipoAvance, Fecha, PorcentajeAvance)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [38, 1, 1, "3/01/2015", "78"]);
                 
            }, errorCB, function (){
                console.log("Se inserto correctamente el programa de Obra: ");
                       //x++; 
                       //insertarInformacion(x);
                      // location.href="mapa_proyectos.html";
                  Directorio(x);
                });           
}
//--------------------------------------------------------------------------------------------------
function Directorio(x) {
           //code
           if (DirectorioItems && x >= DirectorioItems.length) {
                      //code
                      console.log("cargando los datos del directorio.");
                      insertarDirectorioProyectos(0);
                      //EmpresaItems && EmpresaItems.length > 0
           } else if (DirectorioItems && DirectorioItems.length > 0) {
                      //code
                      console.log("Directorio: " + DirectorioItems.length);
                      console.log("tipo de Referencia: " + DirectorioItems[x].tipoReferencia);
                      switch (DirectorioItems[x].tipoReferencia){
                                 case '0':
                                            console.log("Directorio Proyecto:");
                                            for (var i = 0; i < DirectorioItems.length; i++) {
                                                       //code
                                                       //console.log("Persona" + PersonaItems[x].id_Persona);
                                                       console.log("arreglo personas: " + DirectorioItems[x].arregloPersonasId[i]);
                                            }
                                            break;
                                 case '1':
                                            console.log("Directorio Obra:");
                                            for (var i = 0; i < DirectorioItems.length; i++) {
                                                       //code
                                                       console.log("arreglo personas: " + DirectorioItems[x].arregloPersonasId[i]);
                                            }
                                            break;
                                 default:
                                            console.log("Directorio invalido:");
                                            break;
                      };
                      
                      //console.log("arreglo personas: " + DirectorioItems.arregloPersonasId[0]);
                      x++;
                      Directorio(x);
           }else{
                      insertarDirectorioProyectos(0);
           }
}
//--------------------------------------------------------------------------------------------------
//x = 0;
function insertarDirectorioProyectos(x) {
           //code
           if (PersonaItems && x >= PersonaItems.length) {
                      //code
                      console.log("cargando los datos a la base de datos.");
                      insertarDirectorioObras(0);
                      //EmpresaItems && EmpresaItems.length > 0
           } else if (PersonaItems && PersonaItems.length > 0) {
                      //code     
                      console.log("Cargando Directorio: " + PersonaItems.length);
                      console.log("Cargando Directorio: X= " + x + " Empresas: "+ PersonaItems.length);
                      //bdatos
                      //idDirectorio, Nombre, ApPaterno, ApMaterno, RFCPersonal, Cargo, TituloProfesional, CedulaProfesional, Fotografia, Skype, Email, Telefono, Radio
                      //endpoint
                      //fecha_Nacimiento
                      //direccion
                      D_idpersona = PersonaItems[x].id_Persona;
                      D_proyecto = 1;
                      D_nombre = PersonaItems[x].nombre;
                      D_apPaterno = PersonaItems[x].ap_Paterno;
                      D_apMaterno = PersonaItems[x].ap_Materno;
                      D_rfc = PersonaItems[x].rfc;
                      D_cargo = PersonaItems[x].cargo;
                      D_tProfesional = PersonaItems[x].tituloProfesional;
                      D_cProfesional = PersonaItems[x].cedulaProfesional;
                      D_fotografia = PersonaItems[x].fotografia;
                      D_skype = PersonaItems[x].usuarioSkype;
                      D_email = PersonaItems[x].emails;
                      D_telefono = PersonaItems[x].telefonos;
                      D_radio = PersonaItems[x].radios;
                      
                      db.transaction(function(tx){
                                 tx.executeSql('INSERT INTO DirectorioProyectos(idDirectorio, idProyecto, Nombre, ApPaterno, ApMaterno, RFCPersonal, Cargo, TituloProfesional, CedulaProfesional, Fotografia, Skype, Email, Telefono, Radio)'+
                                               'VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)',
                                               [D_idpersona, D_proyecto, D_nombre, D_apPaterno, D_apMaterno, D_rfc, D_cargo, D_tProfesional, D_cProfesional, D_fotografia, D_skype, D_email, D_telefono, D_radio]);
                      }, errorCB, function(){
                                 console.log("se inserto bien el directorio:");
                                 x++;
                                 insertarDirectorioProyectos(x);
                                 });
                      
           }else{
                      console.log("no hay directorio");
                      insertarDirectorioObras(0);
           }
           /*if (DirectorioItems && x >= DirectorioItems.length) {
                      //code
                      console.log("Todas las inserciones hasta el momento terminadas::: ");
                     // otra insercicion
                     //insertarProgramaProyecto(0);
                      //location.href="mapa_proyectos.html";
           }else if (DirectorioItems && DirectorioItems.length > 0) {
                      console.log("Arreglo Personas: " + DirectorioItems[x].arregloPersonasId[x]);
           }else {
                      console.log("No hay proyectos :::::::::::::::::::::::");
                      //insertarAvanceFisicoProyectos(0);
           }
           */
           /*
           db.transaction(function(tx) {
                      console.log("Cargando datos ::: Directorio de Proyectos: ");
                   
                 tx.executeSql('INSERT INTO DirectorioProyectos(idDirectorio, idProyecto, RFCEmpresa, NombreEmpresa, TipoEmpresa,'+
                  'Nombre, ApPaterno, ApMaterno, RFCPersonal, Cargo, TituloProfesional, CedulaProfesional, Fotografia, Skype, Email, Telefono, Radio)' +
                               'VALUES (?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?,?)',
                                [1, 1, 'YHJU8977', 'CONAGUA', 'Gobierno',
                  'Juan', 'Perez', 'Perez', 'JPP8927872', 'Director', 'Ingeniero Civil', 'CJH8968', 'hi', 'juan.perez', 'juan@conagua.com', '987-98-67', '788-9877']);
                 tx.executeSql('INSERT INTO DirectorioProyectos(idDirectorio, idProyecto, RFCEmpresa, NombreEmpresa, TipoEmpresa,'+
                  'Nombre, ApPaterno, ApMaterno, RFCPersonal, Cargo, TituloProfesional, CedulaProfesional, Fotografia, Skype, Email, Telefono, Radio)' +
                                'VALUES (?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?,?)',
                                [2, 1, 'YHJU8977', 'CONAGUA', 'Gobierno',
                  'Pedro', 'Lopez', 'Perez', 'JPP8927872', 'Gerente', 'Ingeniero Civil', 'CJH8968', 'hi', 'pedro.lopez', 'pedro@conagua.com', '566-98-67', '788-4545']);
                 tx.executeSql('INSERT INTO DirectorioProyectos(idDirectorio, idProyecto, RFCEmpresa, NombreEmpresa, TipoEmpresa,'+
                  'Nombre, ApPaterno, ApMaterno, RFCPersonal, Cargo, TituloProfesional, CedulaProfesional, Fotografia, Skype, Email, Telefono, Radio)' +
                               'VALUES (?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?,?)',
                                [3, 1, 'YHJU8977', 'CONAGUA', 'Gobierno',
                  'Maria', 'Flores', 'Perez', 'JPP8927872', 'Gerente', 'Ingeniero Civil', 'CJH8968', 'hi', 'maria.flores', 'maria@conagua.com', '342-98-67', '678-8978']);
            
                 
            }, errorCB, function (){
                console.log("Se inserto correctamente el directorio del proyecto: ");
                       x++; 
                       //insertarInformacion(x);
                      // location.href="mapa_proyectos.html";
                   insertarDirectorioObras(0);
                });
                */
}

function insertarDirectorioObras(x) {
           //code
        
           db.transaction(function(tx) {
                      console.log("Cargando datos ::: Directorio de obra: ");
                   
                 tx.executeSql('INSERT INTO DirectorioObras(idDirectorioObra, idObra, RFCEmpresa, NombreEmpresa, TipoEmpresa,'+
                  'Nombre, ApPaterno, ApMaterno, RFCPersonal, Cargo, TituloProfesional, CedulaProfesional, Fotografia, Skype, Email, Telefono, Radio)' +
                                'VALUES (?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?,?)',
                                [1, 1, 'YHJU8977', 'CONAGUA', 'Gobierno',
                  'Mario', 'Duarte', 'Perez', 'JPP8927872', 'Director', 'Ingeniero Civil', 'CJH8968', 'hi', 'juan.perez', 'mario@conagua.com', '987-98-67', '788-9877']);
                
                 tx.executeSql('INSERT INTO DirectorioObras(idDirectorioObra, idObra, RFCEmpresa, NombreEmpresa, TipoEmpresa,'+
                  'Nombre, ApPaterno, ApMaterno, RFCPersonal, Cargo, TituloProfesional, CedulaProfesional, Fotografia, Skype, Email, Telefono, Radio)' +
                               'VALUES (?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?,?)',
                                [2, 1, 'YHJU8977', 'CONAGUA', 'Gobierno',
                  'Ester', 'Suarez', 'Perez', 'JPP8927872', 'Gerente', 'Ingeniero Civil', 'CJH8968', 'hi', 'pedro.lopez', 'ester@conagua.com', '566-98-67', '788-4545']);
                 
                 tx.executeSql('INSERT INTO DirectorioObras(idDirectorioObra, idObra, RFCEmpresa, NombreEmpresa, TipoEmpresa,'+
                  'Nombre, ApPaterno, ApMaterno, RFCPersonal, Cargo, TituloProfesional, CedulaProfesional, Fotografia, Skype, Email, Telefono, Radio)' +
                               'VALUES (?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?,?)',
                                [3, 1, 'YHJU8977', 'CONAGUA', 'Gobierno',
                  'Ricardo', 'Pascual', 'Perez', 'JPP8927872', 'Gerente', 'Ingeniero Civil', 'CJH8968', 'hi', 'maria.flores', 'ricardo@conagua.com', '342-98-67', '678-8978']);
            
                 
            }, errorCB, function (){
                console.log("Se inserto correctamente el directorio de Obra: ");
                       x++; 
                       //insertarInformacion(x);
                      // location.href="mapa_proyectos.html";
                   insertarDocumentosProyectos(0);
                   //insertarReporte(0);
                });           
}
/*
tx.executeSql('DROP TABLE IF EXISTS ReporteDiario');
    tx.executeSql('CREATE TABLE IF NOT EXISTS ReporteDiario (idReporte, idObra, email, fecha)');
    
    //Catalogo Reporte de Maquinaria
    tx.executeSql('DROP TABLE IF EXISTS RepMaquinaria');
    tx.executeSql('CREATE TABLE IF NOT EXISTS RepMaquinaria (idReporteMaq, idReporte, idCatMaquinaria, email, cantidad)');
    
    //Lista de Maquinaria
    tx.executeSql('DROP TABLE IF EXISTS Maquinaria');
    tx.executeSql('CREATE TABLE IF NOT EXISTS Maquinaria (idMaquinaria unique, nombre, descripcion, tipo)');
    
    //Lista Tipo de Maquinaria
    tx.executeSql('DROP TABLE IF EXISTS TipoMaquinaria');
    tx.executeSql('CREATE TABLE IF NOT EXISTS TipoMaquinaria (idTipo_Maquinaria unique, Tipo_Maquinaria, Descripcion)');
    ReportesItems, RepMaquinariaItems, ListMaquinariaItems, CatTipoMaquinariaItems
    */
//--------------------Insercion de Reporte Diario------------------------------------
/*function insertarReporte(x) {
           //code
           
           if (ReportesItems) {
                      //code
                      //console.log("Reportes Diarios: " + ReportesItems.lenght);
                      console.log("cargando los datos a la base de datos.");
                      R_idreporte = ReportesItems[x].id_ReporteDiario;
                      R_idobra = ReportesItems[x].id_Obra;
                      R_email = ReportesItems[x].email;
                      R_fecha = ReportesItems[x].fechaReporteDiario;
                      
                      db.db.transaction(function(tx) {
                                 tx.executeSql('INSERT INTO DocumentosProyectos(idReporte, idObra, email, fecha)' +
                                          'VALUES (?, ?, ?, ?)',
                                           [R_idreporte, R_idobra, R_email, R_fecha]);
                      }, errorCB,function (){
                                 console.log("Se inserto correctamente documentos de proyectos: ");
                                 x++;
                                 insertarReporte(x);
                                 }
                      );
                      //EmpresaItems && EmpresaItems.length > 0
           } else if (ReportesItems && ReportesItems.length > 0) {
                      //code     
                      console.log("Cargando Reporte: " + ReportesItems.length);
                       
           }else{
                      console.log("No hay Reportes Diarios");
                      insertarDocumentosProyectos(0);
           }
           
           
}
*/
//--------------------Insercion de Reporte Diario------------------------------------
function insertarDocumentosProyectos(x) {
           //code
       // DocumentosProyectos(idDocumento unique, idProyecto, Docto, Formato, Tipo)');
           db.transaction(function(tx) {
                      console.log("Cargando datos ::: Programa de obra: ");
                   
                 tx.executeSql('INSERT INTO DocumentosProyectos(idDocumento, idProyecto, Docto, Formato, Tipo)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [1, 1, 'https://docs.google.com/a/csgit.com.mx/uc?authuser=0&id=0B0PbNiCm1xfQUDJhNDJVY2x5UDQ&export=download', 'pdf', 1]);
                 tx.executeSql('INSERT INTO DocumentosProyectos(idDocumento, idProyecto, Docto, Formato, Tipo)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [2, 1, 'https://docs.google.com/a/csgit.com.mx/uc?authuser=0&id=0B0PbNiCm1xfQSW1BV0JpUzdUSUU&export=download', 'excel', 1]);
                 tx.executeSql('INSERT INTO DocumentosProyectos(idDocumento, idProyecto, Docto, Formato, Tipo)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [3, 1, 'http://www.acis.org.co/fileadmin/Base_de_Conocimiento/XXV_Salon_de_Informatica/ArquitecturasSoftware-RaquelAnaya.ppt', 'ppt', 1]);
                
                 
            }, errorCB, function (){
                console.log("Se inserto correctamente documentos de proyectos: ");
                       x++; 
                       //insertarInformacion(x);
                      // location.href="mapa_proyectos.html";
                   insertarDocumentosObras(0);
                });           
}

function insertarDocumentosObras(x) {
           //code
       // DocumentosProyectos(idDocumento unique, idProyecto, Docto, Formato, Tipo)');
           db.transaction(function(tx) {
                      console.log("Cargando datos ::: Programa de obra: ");
                   
                 tx.executeSql('INSERT INTO DocumentosObras(idDocumento, idObra, Docto, Formato, Tipo)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [1, 1, 'https://docs.google.com/a/csgit.com.mx/uc?authuser=0&id=0B0PbNiCm1xfQUDJhNDJVY2x5UDQ&export=download', 'pdf', 1]);
                 tx.executeSql('INSERT INTO DocumentosObras(idDocumento, idObra, Docto, Formato, Tipo)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [2, 1, 'https://docs.google.com/a/csgit.com.mx/uc?authuser=0&id=0B0PbNiCm1xfQSW1BV0JpUzdUSUU&export=download', 'excel', 1]);
                 tx.executeSql('INSERT INTO DocumentosObras(idDocumento, idObra, Docto, Formato, Tipo)' +
                               'VALUES (?, ?, ?, ?, ?)',
                                [3, 1, 'http://www.acis.org.co/fileadmin/Base_de_Conocimiento/XXV_Salon_de_Informatica/ArquitecturasSoftware-RaquelAnaya.ppt', 'ppt', 1]);
                
                 
            }, errorCB, function (){
                console.log("Se inserto correctamente documentos de obras: ");
                       x++; 
                       //insertarInformacion(x);
                      // location.href="mapa_proyectos.html";
                   insertarTempAvancesProyectos();
                });           
}

var APTI; //avancesProyectosTemporalesItems 
function insertarTempAvancesProyectos(x) {
           
           console.log(" Insertando Avances Temporales de Proyectos");
           
           var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
    
           db.transaction(function(tx) {
        
              tx.executeSql('select MAX(CAST(av.IdAvanceFisico as int)) as idAvanceFisico, MAX(CAST(af.IdAvanceFinanciero as int)) as idAvanceFinanciero, av.idProyecto as idProyectoFisico, af.idProyecto as idProyectoFinanciero' +
                            ' from AvanceFisicoProyectos av, AvanceFinancieroProyectos af' +
                            '  where CAST(av.idProyecto as int) = CAST(af.idProyecto as int) group by av.idProyecto, af.idProyecto', //where av.idProyecto = af.idProyecto
                            [],  function(tx, rs) {
                                
                            console.log("Avances Temp Proyectos: " + rs.rows.length);
                            APTI= rs.rows;
                            
                            for(var i = 0; i<rs.rows.length; i++){
                                   var resulset = rs.rows.item(i);
                                   console.log("resulset =" + resulset);
                                 console.log("Avances Temp Proyectos:::::: idProyectoFisico = " + resulset.idProyectoFisico +"  idProyectoFinanciero = "+resulset.idProyectoFinanciero+" idAvanceFisico = "+resulset.idAvanceFisico+
                                             " idAvanceFinanciero = " +resulset.idAvanceFinanciero);
                                 
                            }
                            
                             insertTemp(0);
                   
              }, errorCB);
               
          }, errorCB, successTemp());
}

function successTemp() {
    console.log("Consulta de avances temporales Proyectos OK");
    //insertTemp(0);
}

/*
  
    //AvanceProyectos
    tx.executeSql('DROP TABLE IF EXISTS TempAvanceObras');
    tx.executeSql('CREATE TABLE IF NOT EXISTS TempAvanceObras (idAvanceTempObra, idAvanceFinanciero, idAvanceFisico, idObra)');
 */
 function insertTemp(x) {
           //code Insertando avances fisicos
           
      
           
          if (APTI && x >= APTI.length) {
                      //code
                      console.log("Cargando datos espere un momento vamos a insertar avances temporales de Obra:::   X=" + x );
                  
                     insertarTempAvancesObras();
           }
        
           else if (APTI && APTI.length > 0) {
           console.log("Cargando datos espere un momento ::: 4 X=" + x + " Avances: "+ APTI.length);
           
         
            
           db.transaction(function(tx) {
                  
                                tx.executeSql('INSERT INTO TempAvanceProyectos(idAvanceFinanciero, idAvanceFisico, idProyecto)' +
                               ' VALUES (?, ?, ?)',
                                [APTI.item(x).idAvanceFinanciero, APTI.item(x).idAvanceFisico, APTI.item(x).idProyectoFisico]);
                                 console.log("Avance Temp en Proyectos: " + APTI.item(x).idProyectoFisico);
                  
              
            }, errorCB, function (){
                console.log("Se inserto correctamente el avance temporal: " + APTI.item(x).idProyectoFisico);
               
                    x++;
                    insertTemp(x);
                   
                }); 
           
            } else {
                      console.log("No hay avances temporales de proyecto :::::::::::::::::::::::");
                      // insertarAvanceFinancieroProyectos(0);
           }
}

var AOTI; //avancesObrasTemporalesItems 
function insertarTempAvancesObras() {
           
           console.log(" Insertando Avances Temporales de Obras");
           
           var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
    
           db.transaction(function(tx) {
                      
        
        
              tx.executeSql('select MAX(CAST(av.IdAvanceFisico as int)) as idAvanceFisico, MAX(CAST(af.IdAvanceFinanciero as int)) as idAvanceFinanciero, av.idObra as idObraFisico, af.idObra as idObraFinanciero' +
                            ' from AvanceFisicoObras av, AvanceFinancieroObras af' +
                            ' where CAST(av.idObra as int) = CAST(af.idObra as int)'+
                            ' group by av.idObra, af.idObra', //where av.idProyecto = af.idProyecto where CAST(av.idObra as int) = CAST(af.idObra as int)
                            [],  function(tx, rs) {
                                
                            console.log("Avances Temp Obras: " + rs.rows.length);
                            AOTI= rs.rows;
                            
                            for(var i = 0; i<rs.rows.length; i++){
                                   var resulset = rs.rows.item(i);
                                   console.log("resulset =" + resulset);
                                 console.log("Avances Temp Obras:::::: idObraFisico = " + resulset.idObraFisico +"  idObraFinanciero = "+resulset.idObraFinanciero+" idAvanceFisico = "+resulset.idAvanceFisico+
                                             " idAvanceFinanciero = " +resulset.idAvanceFinanciero);
                                 
                            }
                            
                             insertTempObra(0);
                   
              }, errorCB);
               
          }, errorCB, successTemp());
}

function successTemp() {
    console.log("Consulta de avances temporales Proyectos OK");
    //insertTemp(0);
}

/*//AvanceProyectos
    tx.executeSql('DROP TABLE IF EXISTS TempAvanceObras');
    tx.executeSql('CREATE TABLE IF NOT EXISTS TempAvanceObras (idAvanceTempObra, idAvanceFinanciero, idAvanceFisico, idObra)');
 */


 function insertTempObra(x) {
           //code Insertando avances fisicos
           
          if (AOTI && x >= AOTI.length) {
                      //code
                      console.log("Todas las inserciones terminadas:::::::::::::::::::::::::::::::::");
                      location.href="mapa_proyectos.html";
                    // insertarAvanceFinancieroProyectos(0);
           }
        
           else if (AOTI && AOTI.length > 0) {
           console.log("Cargando datos espere un momento ::: 4 X=" + x + " Avances: "+ AOTI.length);
           
         
            
           db.transaction(function(tx) {
                  
                                tx.executeSql('INSERT INTO TempAvanceObras(idAvanceFinanciero, idAvanceFisico, idObra)' +
                               ' VALUES (?, ?, ?)',
                                [AOTI.item(x).idAvanceFinanciero, AOTI.item(x).idAvanceFisico, AOTI.item(x).idObraFisico]);
                                 console.log("Avance Temp en Proyectos: " + AOTI.item(x).idObraFisico);
                  
              
            }, errorCB, function (){
                console.log("Se inserto correctamente el avance temporal: " + AOTI.item(x).idObraFisico);
               
                    x++;
                    insertTempObra(x);
                   
                }); 
           
            } else {
                      console.log("No hay avances temporales de obra :::::::::::::::::::::::");
                      // insertarAvanceFinancieroProyectos(0);
           }
}


function validaProyecto(idProyecto){
     var valida = false;
     if(ProyectosDirectivoItems){
     for(var i = 0; i< ProyectosDirectivoItems.length; i++){
        p = ProyectosDirectivoItems[i].id_proyecto;
       // alert(" idProyectoEndPoint= "+ p +" idProyecto= "+ idProyecto); 
        if(p==idProyecto){
          valida = true;
        }
     }
     return valida;
     }else return valida;
}
function fail(evt) {
        console.log("Error file transfer to device:::: "+evt.target.error.code);
}



