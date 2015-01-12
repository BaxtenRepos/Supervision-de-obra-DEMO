////////////////CREAR BASE DE DATOS

var db;
var noTablas = 0;
var ROOT = 'https://' + 'focal-furnace-615.appspot.com' + '/_ah/api';
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
var ProyectoItems, AvanceFisicoItems, AvanceFinancieroItems;

if (window.location.hostname == 'localhost'
    || window.location.hostname == '127.0.0.1'
    || ((window.location.port != "") && (window.location.port > 999))) {
    // We're probably running against the DevAppServer
    ROOT = 'http://' + 'focal-furnace-615.appspot.com' + '/_ah/api';
}

function init(){
           
           
            if(checkConnection() != "No network connection" && checkConnection() != "Unknown connection"){
            Messenger().post("Conexi&oacute;n establecida: " + checkConnection());          
            console.log("Iniciando descarga de datos::::1");
            endpoints();
            }else{
                      
                  alert("Sin salida a internet, datos no actualizados");
                  location.href="lista_proyectos.html";
                  
            }
           
 }

function endpoints(){
    //alert("Descargando datos espere un momento ::: 2");
    console.log("Descargando :::::::::::::::::::2");
     
    //Consultar EndPoint Proyectos

           gapi.client.load('proyectoendpoint', 'v1', function(){
          gapi.client.proyectoendpoint.listProyecto().execute(function(resp){
            if(resp.code) alert("Error de conexi&oacute;n :::" + resp.message );
            ProyectoItems = resp.items;
            console.log("Proyectos" + ProyectoItems.length);
            });//listProyectoEndPoint
    },ROOT); //Load ProyectosEndPoint
          
            
    //Consultar EndPoint Obras
  
        gapi.client.load('obraendpoint', 'v1', function(){
        gapi.client.obraendpoint.listObra().execute(function(resp){
            if(resp.code) alert("Error de conexi&oacute;n :::" + resp.message );
            ObrasItems = resp.items;
            });//listObra
    },ROOT); //Load Obra
   
      //Consultar EndPoint avance fisico

    gapi.client.load('avance_fisicoendpoint', 'v1', function(){
        gapi.client.avance_fisicoendpoint.listAvance_Fisico().execute(function(resp){
            if(resp.code) alert("Error de conexi&oacute;n, verificar su salida a internet :::" + resp.message );
            AvanceFisicoItems = resp.items;            
       });//list
    },ROOT); //Load
     
    //Consultar EndPoint avance financiero
  
    gapi.client.load('avance_financieroendpoint', 'v1', function(){
        gapi.client.avance_financieroendpoint.listAvance_Financiero().execute(function(resp){
            if(resp.code) alert("Error de conexi&oacute;n, verificar su salida a internet :::" + resp.message );
            AvanceFinancieroItems = resp.items;
       });//list
    },ROOT); //Load
    
    //Consultar EndPoint Ubicacion 
    gapi.client.load('ubicacionesendpoint', 'v1', function(){
        gapi.client.ubicacionesendpoint.listUbicaciones().execute(function(resp){
            if(resp.code) alert("Error de conexi&oacute;n, verificar su salida a internet :::" + resp.message );
            UbicacionItems = resp.items;
       });//list
    },ROOT); //Load
    
    //Consultar EndPoint Empresa
    gapi.client.load('empresaendpoint', 'v1', function(){
        gapi.client.empresaendpoint.listEmpresa().execute(function(resp){
            if(resp.code) alert("Error de conexi&oacute;n, verificar su salida a internet :::" + resp.message );
            EmpresaItems = resp.items;
       });//list
    },ROOT); //Load
    
     //Consultar EndPoint Empresa
    gapi.client.load('cat_tipo_empresaendpoint', 'v1', function(){
        gapi.client.cat_tipo_empresaendpoint.listCat_Tipo_Empresa().execute(function(resp){
            if(resp.code) alert("Error de conexi&oacute;n, verificar su salida a internet :::" + resp.message );
            EmpresaTipoItems = resp.items;
       });//list
    },ROOT); //Load 
    
    //Consultar EndPoint Usuario
    gapi.client.load('usuarioendpoint', 'v1', function(){
        gapi.client.usuarioendpoint.listUsuario().execute(function(resp){
            if(resp.code) alert("Error de conexi&oacute;n, verificar su salida a internet :::" + resp.message );
            UsuarioItems = resp.items;
       });//list
    },ROOT); //Load
    
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
    //Alertas
    tx.executeSql('DROP TABLE IF EXISTS AlertasProyectos');
    tx.executeSql('CREATE TABLE IF NOT EXISTS AlertasProyectos(idAlerta unique,  idProyecto, LimiteDesvio, Tipo)');
    //Documentos
    tx.executeSql('DROP TABLE IF EXISTS DocumentosProyectos');
    tx.executeSql('CREATE TABLE IF NOT EXISTS DocumentosProyectos(idDocumento unique, idProyecto, Docto, Formato, Tipo)');
    //Logotipos
    tx.executeSql('DROP TABLE IF EXISTS Logotipos');
    tx.executeSql('CREATE TABLE IF NOT EXISTS Logotipos(idLogotipo unique, idProyecto,  Logotipo, Formato, Tipo)');
    //Directorio
    tx.executeSql('DROP TABLE IF EXISTS DirectorioProyectos');
    tx.executeSql('CREATE TABLE IF NOT EXISTS DirectorioProyectos(idDirectorio unique, idProyecto, RFCEmpresa, NombreEmpresa, TipoEmpresa,'+
                  'Nombre, ApPaterno, ApMaterno, RFCPersonal, Cargo, TituloProfesional, CedulaProfesional, Fotografia, Skype, Email, Telefono, Radio)');
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
    tx.executeSql('DROP TABLE IF EXISTS AlertasObras');
    tx.executeSql('CREATE TABLE IF NOT EXISTS AlertasObras(idAlertaObra unique,  idObra, LimiteDesvio, Tipo)');
    //Documentos
    tx.executeSql('DROP TABLE IF EXISTS DocumentosObras');
    tx.executeSql('CREATE TABLE IF NOT EXISTS DocumentosObras(idDocumento unique, idObra, Docto, Formato, Tipo)');
    //Directorio
    tx.executeSql('DROP TABLE IF EXISTS DirectorioObras');
    tx.executeSql('CREATE TABLE IF NOT EXISTS DirectorioObras(idDirectorioObra unique, idProyecto, RFCEmpresa, NombreEmpresa, TipoEmpresa,'+
                  'Nombre, ApPaterno, ApMaterno, RFCPersonal, Cargo, TituloProfesional, CedulaProfesional, Fotografia, Skype, Email, Telefono, Radio)');
    //Multimedia
    tx.executeSql('DROP TABLE IF EXISTS Multimedia');
    tx.executeSql('CREATE TABLE IF NOT EXISTS Multimedia (idMultimedia unique, idObra, Tipo, Formato, Multimedia)');
    //Programa
    tx.executeSql('DROP TABLE IF EXISTS Programa');
    tx.executeSql('CREATE TABLE IF NOT EXISTS Programa (idPrograma unique, idObra, TipoAvance, Fecha, PorcentajeAvance)');
    
    //Empresas
    tx.executeSql('DROP TABLE IF EXISTS Empresa');
    tx.executeSql('CREATE TABLE IF NOT EXISTS Empresa (idEmpresa, rfc, nombre, siglas)');
 
 
    }


function errorCB(err) {
    console.log("Error processing SQL: "+err.code + " Message: "+err.message);
}

//Tablas creadas exitosamente
function successTablas() {
    
      console.log("Se han insertado las tablas");
       var x = 0;
       insertarInformacion(x);
  
}



function insertarInformacion(x) {
                  
           if (x >= ProyectoItems.length) {
                      //code
                      console.log("Cargando datos espere un momento vamos a insertar avances:::   X=" + x );
                     insertarAvanceFisicoProyectos(0);
           }
          
           else if (ProyectoItems && ProyectoItems.length > 0) {
           
          
                      //code
            idProyecto=ProyectoItems[x].id_Proyecto;
            NombreLargo=ProyectoItems[x].nombre_largo;
            NombreCorto=ProyectoItems[x].nombre_corto;
            Descripcion=ProyectoItems[x].id_Descripcion;
            Dependencia=ProyectoItems[x].id_dependencia;
            Secretaria=ProyectoItems[x].id_sercretaria;
            LimiteDesvio="15%";
            Desvio="0%";
                                                            // LimiteDesvio=items[0].nombre_corto;
                                                            // Desvio=items[0].nombre_corto;
            idUbicacion=ProyectoItems[x].id_hubicacion;
            
           
           
           //code Insertando proyectos
             db.transaction(function(tx) {
                       console.log("Cargando datos espere un momento :::   X=" + x + " Proyectos: "+ ProyectoItems.length);
                   
                 tx.executeSql('INSERT INTO Proyectos (idProyecto, NombreLargo, NombreCorto, Descripcion, Dependencia, Secretaria,LimiteDesvio, Desvio, idUbicacion)' +
                               'VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)',
                                [idProyecto, NombreLargo, NombreCorto, Descripcion, Dependencia, Secretaria, LimiteDesvio, Desvio, idUbicacion]);
                 
            }, errorCB, function (){
                console.log("Se inserto correctamente el proyecto: " + NombreLargo);
                       x++; 
                       insertarInformacion(x);
                }); 
           
           } else  console.log("No hay proyectos :::::::::::::::::::::::");
}


function insertarAvanceFisicoProyectos(x) {
           //code Insertando avances fisicos
           console.log("Cargando datos espere un momento ::: 4 X=" + x + " Avances: "+ AvanceFisicoItems.length);       
          if (AvanceFisicoItems && x >= AvanceFisicoItems.length) {
                      //code
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
                      
            IdAvanceFisico =AvanceFisicoItems[x].id_AvanceFisico;
            idReferencia=AvanceFisicoItems[x].id_referencia;
            PAvanceFisico=AvanceFisicoItems[x].pavanceFisico;
            Tendencia=AvanceFisicoItems[x].porcentaje_tendencia;
            FechaReporte=AvanceFisicoItems[x].fechaReporte;
            Estado=AvanceFisicoItems[x].estado;
            switch (Estado) {
                      case '0':
                                 Estado = "Atrasado";
                                 break;
                      case '1':
                                 Estado = "Conforme a programa";
                                 break;
                      case '2':
                                 Estado = "Adelantado";
                                 break;
                      default:
                                 Estado = "No hay datos";
            }
            TipoEntidad=AvanceFisicoItems[x].tipo_Entidad;
           
              
           db.transaction(function(tx) {
                  if (TipoEntidad == 0 || TipoEntidad == "0") {
                                tx.executeSql('INSERT INTO AvanceFisicoProyectos(IdAvanceFisico, idProyecto, PAvanceFisico, Tendencia, FechaReporte, Estado)' +
                               ' VALUES (?, ?, ?, ?, ?, ?)',
                                [IdAvanceFisico, idReferencia, PAvanceFisico, Tendencia, FechaReporte, Estado]);
                  } if (TipoEntidad == 1 || TipoEntidad == "1") {
                                tx.executeSql('INSERT INTO AvanceFisicoObras(IdAvanceFisico, idObra, PAvanceFisico, Tendencia, FechaReporte, Estado)' +
                               ' VALUES (?, ?, ?, ?, ?, ?)',
                                [IdAvanceFisico, idReferencia, PAvanceFisico, Tendencia, FechaReporte, Estado]);
                  }
              
            }, errorCB, function (){
                console.log("Se inserto correctamente el avance: " + IdAvanceFisico);
               
                    x++;
                    insertarAvanceFisicoProyectos(x);
                   
                }); 
           
            } else  console.log("No hay avances fisicos :::::::::::::::::::::::");
}

function successConsulta() {
    //alert("consulta OK");
   
}

function insertarAvanceFinancieroProyectos(x) {
           
           console.log("Cargando datos espere un momento ::: 4 X=" + x + " Avances: "+ AvanceFinancieroItems.length);       
          if (AvanceFinancieroItems && x >= AvanceFinancieroItems.length) {
                      //code
                      console.log("Cargando datos espere un momento vamos a insertar obras:::   X=" + x );
                      insertarObras(0);
           }
        
           else if (AvanceFinancieroItems && AvanceFinancieroItems.length > 0) {
             IdAvanceFinanciero =AvanceFinancieroItems[x].id_AvanceFinaciero;
            F_idReferencia=AvanceFinancieroItems[x].id_referencia;
            F_PAvanceFinanciero=AvanceFinancieroItems[x].pavanceFinanciero;
            F_Tendencia=AvanceFinancieroItems[x].porcentaje_tendencia;
            F_FechaReporte=AvanceFinancieroItems[x].fechaReporte;
            F_Estado=AvanceFinancieroItems[x].estado;
            F_TipoEntidad=AvanceFinancieroItems[x].tipo_Entidad;
            
           db.transaction(function(tx) {
                   if (F_TipoEntidad == 0 || F_TipoEntidad == "0") {
                                tx.executeSql('INSERT INTO AvanceFinancieroProyectos(IdAvanceFinanciero, idProyecto, PAvanceFinanciero, Tendencia, FechaReporte, Estado)' +
                               ' VALUES (?, ?, ?, ?, ?, ?)',
                                [IdAvanceFinanciero, F_idReferencia, F_PAvanceFinanciero, F_Tendencia, F_FechaReporte, F_Estado]);
                   }
                   if (F_TipoEntidad == 1 || F_TipoEntidad == "1") {
                                tx.executeSql('INSERT INTO AvanceFinancieroObras(IdAvanceFinanciero, idObra, PAvanceFinanciero, Tendencia, FechaReporte, Estado)' +
                               ' VALUES (?, ?, ?, ?, ?, ?)',
                                [IdAvanceFinanciero, F_idReferencia, F_PAvanceFinanciero, F_Tendencia, F_FechaReporte, F_Estado]);
                   }
            }, errorCB, function (){
                console.log("Se inserto correctamente el avance: " + IdAvanceFinanciero);
                    x++;
                    insertarAvanceFinancieroProyectos(x);
                }); 
           
           } else console.log("No hay avances financieros :::::::::::::::::::::::");
}


function insertarObras(x) {
           
           console.log("Cargando datos espere un momento ::: 4 X=" + x + " Obras: "+ ObrasItems.length);       
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
            O_Gobierno=ObrasItems[x].gobierno;
            O_Secretaria=ObrasItems[x].secretaria;
            O_Dependencia=ObrasItems[x].dependencia;
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
                 
                   
            }, errorCB, function (){
                console.log("Se inserto correctamente la obra: " + O_idObra);
                    x++;
                    insertarObras(x);
                }); 
           
           } else console.log("No hay obras :::::::::::::::::::::::");
}

function insertarUbicaciones(x) {
           
           console.log("Cargando datos espere un momento ::: 4 X=" + x + " Ubicaciones: "+ UbicacionItems.length);       
          if (UbicacionItems && x >= UbicacionItems.length) {
                      //code
                      console.log("Cargando datos espere un momento vamos a actualizar empresas:::   X=" + x );
                      insertarEmpresas(0);
           }
        
           else if (UbicacionItems && UbicacionItems.length > 0) {
                     
             var IdUbicacion = UbicacionItems[x].id_Ubicacion;
             var U_Ubicacion = ""; //= UbicacionItems[x].ubicacion; //Arreglo de ubicaciones
             
             console.log("IdUbicacion= " +IdUbicacion+" Puntos= " + UbicacionItems[x].ubicacion.length);
             for(var i=0; i< UbicacionItems[x].ubicacion.length; i++){
                      U_Ubicacion = U_Ubicacion + UbicacionItems[x].ubicacion[i] + "|";
                       console.log("Puntos de Ubicacion " + U_Ubicacion);
             }
          
            
           db.transaction(function(tx) {
                                tx.executeSql('INSERT INTO Ubicacion(idUbicacion, puntos)' +
                               'VALUES (?, ?)',
                                [IdUbicacion, U_Ubicacion]);
                  
            }, errorCB, function (){
                console.log("::::::::::::::::: Se inserto correctamente la ubicacion: " + IdUbicacion);
                    x++;
                    insertarUbicaciones(x);
                }); 
           
           } else console.log("No hay ubicaciones :::::::::::::::::::::::");
}


function insertarEmpresas(x) {
           
          console.log("Cargando datos espere un momento ::: 4 X=" + x + " Empresas: "+ EmpresaItems.length);       
          if (EmpresaItems && x >= EmpresaItems.length) {
                      //code
                      console.log("Todas las inserciones hasta el momento terminadas::: ");
                     // otra insercicion
                      location.href="mapa_proyectos.html";
           }
        
           else if (EmpresaItems && EmpresaItems.length > 0) {
                     
           var IdEmpresa = EmpresaItems[x].id_Empresa;
           var E_RFC = EmpresaItems[x].rfc;
           var E_Nombre = EmpresaItems[x].nombre;
           var E_Siglas = EmpresaItems[x].siglas;
             
            
           db.transaction(function(tx) {
                                tx.executeSql('INSERT INTO Empresa(idEmpresa, rfc, nombre, siglas)' +
                               'VALUES (?, ?, ?, ?)',
                                [IdEmpresa, E_RFC, E_Nombre, E_Siglas]);
                                
                             /*    tx.executeSql('UPDATE Proyectos set Empresa = ? where' +
                               'VALUES (?, ?, ?, ?)',
                                [IdEmpresa, E_RFC, E_Nombre, E_Siglas]);
                                
                                UPDATE page_settings SET fname=?, bgcolor=?, font=?, favcar=? WHERE id = 1
                             */
            }, errorCB, function (){
                console.log("::::::::::::::::: Se inserto correctamente la empresa: " + IdEmpresa);
                    x++;
                    insertarEmpresas(x);
                }); 
           
           } else console.log("No hay empresas :::::::::::::::::::::::");
}




