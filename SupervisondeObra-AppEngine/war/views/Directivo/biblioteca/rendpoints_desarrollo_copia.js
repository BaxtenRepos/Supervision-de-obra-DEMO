var ROOT = 'https://' + '1-dot-iuyet-csgit-cao.appspot.com' + '/_ah/api';
//var ROOT = 'https://' + '1-dot-cao-iuyet-pruebas.appspot.com' + '/_ah/api';
//1-dot-crypto-plane-782.appspot.com
// http://1-dot-cao-iuyet-pruebas.appspot.com
//var ROOT = 'https://' + '1-dot-crypto-plane-782.appspot.com' + '/_ah/api';
if (window.location.hostname == 'localhost'
    || window.location.hostname == '127.0.0.1'
    || ((window.location.port != "") && (window.location.port > 999))) {
    //ROOT = 'http://' + '1-dot-crypto-plane-782.appspot.com' + '/_ah/api';
    ROOT = 'http://' + '1-dot-iuyet-csgit-cao.appspot.com' + '/_ah/api';
    //ROOT = 'https://' + '1-dot-cao-iuyet-pruebas.appspot.com' + '/_ah/api';
}
var i = 0;
function init(){
    enpoints();

}
function enpoints() {
    //code
    var directivo = getGET().idDirectivo;
    gapi.client.load('communicationchannel', 'v1', function(){
        gapi.client.communicationchannel.listProyectoDelDirectivo({"directivo":directivo}).execute(function(resp){
            if (resp.code) {
                //code
                alert("Error de conexi—n, no se ha podido descargar Informacion del Directivo, volviendo a intentar. :::" + resp.message );
            }else{
                ProyectoDirectivo = resp.items;
                document.getElementById("endpoint1").innerHTML= "Directivo-Proyectos: <img src=\" biblioteca/Imagenes/check.png\" width=\"20px\" heigth=\"20px\">";
                /*console.log("Nombre del Proyecto: " + ProyectoDirectivo[0].proyecto.nombre_corto);
                console.log("Numero de Proyectos: " + ProyectoDirectivo.length);
                console.log("Numero de Obras: " + ProyectoDirectivo[0].obras.length);
                console.log("Numero de Avances Fisicos Proyecto: " + ProyectoDirectivo[0].avanceFisico.length);
                console.log("Reporte Diario: " + ProyectoDirectivo[0].obras[1].reportesDiarios.length);
                console.log("Reporte Diario: " + ProyectoDirectivo[0].obras[0].reportesDiarios);
                console.log("id de reporte diario: " + ProyectoDirectivo[0].obras[1].reportesDiarios[0].reporteDiario.id_ReporteDiario);
                console.log("id de reporte de maquinaria: " + ProyectoDirectivo[0].obras[1].reportesDiarios[0].repMaquinariaCatMaquinaria[1].idRepMaquinaria);
                console.log("reportes de maquinaria: " + ProyectoDirectivo[0].obras[1].reportesDiarios[0].repMaquinariaCatMaquinaria.length);*/
            }
        });
        gapi.client.communicationchannel.listEmpresa().execute(function(resp){
            if (resp.code) {
                //code
                alert("Error de conexi—n, no se ha podido descargar Informacion del Directivo, volviendo a intentar. :::" + resp.message );
            }else{
                EmpresaItems = resp.items;
                console.log("Empresas: " + EmpresaItems.length);
                document.getElementById("endpoint7").innerHTML= "Empresas: <img src=\" biblioteca/Imagenes/check.png\" width=\"20px\" heigth=\"20px\">";
            }
        });
        //List Endpoint Ubicaciones
        gapi.client.communicationchannel.listUbicaciones().execute(function(resp){
            if(resp.code) {
                alert("Error de conexi—n, no se ha podido descargar Ubicaciones, volviendo a intentar. :::" + resp.message );
            }else{
                UbicacionItems = resp.items;
                console.log("UbicacionItems: " + UbicacionItems.length);
                document.getElementById("endpoint6").innerHTML = "Ubicaciones: <img src=\" biblioteca/Imagenes/check.png\" width=\"20px\" heigth=\"20px\">";
            }
        });//list
        //List Multimedia
        gapi.client.communicationchannel.listMultimedia({"tipoReferencia":"0", "idReferencia":"0","tipoArchivo":"0"}).execute(function(resp){
            if(resp.code) {
                alert("Error de conexi—n, no se ha podido descargar Multimedia, volviendo a intentar. :::" + resp.message );
            }else{
                MultimediaItems = resp.items;
                console.log("MultimediaItems: " + MultimediaItems.length);
                document.getElementById("endpoint8").innerHTML= "Multimedia: <img src=\" biblioteca/Imagenes/check.png\" width=\"20px\" heigth=\"20px\">";
            }
        });
        //List Directorio
        gapi.client.communicationchannel.listDirectorio({"tipoReferencia":"0", "idReferencia":"0"}).execute(function(resp){
            if(resp.code) {
                alert("Error de conexi—n, no se ha podido descargar Directorio, volviendo a intentar. :::" + resp.message );
            }else{
                DirectorioItems = resp.items;
                console.log("DirectorioItems: " + DirectorioItems.length);
                //document.getElementById("endpoint11").innerHTML= "Directorio: <img src=\" biblioteca/Imagenes/check.png\" width=\"20px\" heigth=\"20px\">";
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
        //Lista Maquinaria
        gapi.client.communicationchannel.listMaquinaria().execute(function(resp){
            if(resp.code) {
                console.log("Error de conexi—n, no se ha podido descargar Multimedia, volviendo a intentar. :::" + resp.message );
            }else{
                MaquinariaItems = resp.items;
                console.log("MaquinariaItems: " + MaquinariaItems.length);
            }
        });//list
        //List tipo de Maquinaria
        gapi.client.communicationchannel.listCat_Tipo_Maquinaria().execute(function(resp){
            if (resp) {
                console.log("Error de conexi—n, no se ha podido descargar el tipo de maquinaria, volviendo a intentar. :::" + resp.message );
                CatTipoMaquinariaItems = resp;
            }else{
                CatTipoMaquinariaItems = resp.items;
                console.log("Se descargo el Catalogo tipo Maquinaria: " + CatTipoMaquinariaItems.length);
            }
        });// fin de listCat_Tipo_Maquinaria
        //List cat_tipo_persona
        gapi.client.communicationchannel.listCat_Personal().execute(function(resp){
            if (resp) {
                console.log("Error de conexi—n, no se ha podido descargar el tipo de maquinaria, volviendo a intentar. :::" + resp.message );
                CatPersonalItems = resp;
            }else{
                CatPersonalItems = resp.items;
                console.log("Se descargo el Catalogo de Personal: " + CatPersonalItems.length);
            }
        });// fin de cat_tipo_persona
        //List RepoPersonalCatPersonal
        
        gapi.client.communicationchannel.listRepoPersonalCatPersonal({"email": " ", "def": "0"}).execute(function(resp){
            if (resp) {
                console.log("Error de conexi—n, no se ha podido descargar el tipo de maquinaria, volviendo a intentar. :::" + resp.message );
                RepCatPersonalItems = resp;
            }else{
                RepCatPersonalItems = resp.items;
                console.log("Se descargo el Reporte Catalogo de Personal: " + RepCatPersonalItems.length);
            }
        });// fin de repcat_tipo_persona
        
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
    tx.executeSql('CREATE TABLE IF NOT EXISTS MultimediaProyectos (idMultimedia unique, idProyecto, TipoArchivo, Path, Fecha, Descripcion, Formato)');
    //Multimedia
    tx.executeSql('DROP TABLE IF EXISTS MultimediaObras');
    tx.executeSql('CREATE TABLE IF NOT EXISTS MultimediaObras (idMultimedia unique, idObra, TipoArchivo, Path, Fecha, Descripcion, Formato)');
    //Multimedia Maquinaria
    tx.executeSql('DROP TABLE IF EXISTS MultimediaMaquinaria');
    tx.executeSql('CREATE TABLE IF NOT EXISTS MultimediaMaquinaria (idMultimedia unique, idMaquinaria, TipoArchivo, Path, Fecha, Descripcion, Formato)');
    //Multimedia Minutas
    tx.executeSql('DROP TABLE IF EXISTS MultimediaMinuta');
    tx.executeSql('CREATE TABLE IF NOT EXISTS MultimediaMinuta (idMultimedia unique, idObra, TipoArchivo, Path, Fecha, Descripcion, Formato)');
    //Programa de Obra
    tx.executeSql('DROP TABLE IF EXISTS Programa_Obra');
    tx.executeSql('CREATE TABLE IF NOT EXISTS Programa_Obra (idPrograma unique, idObra, TipoAvance, Fecha, PorcentajeAvance)');
    //Programa de Proyecto
    tx.executeSql('DROP TABLE IF EXISTS Programa_Proyecto');
    tx.executeSql('CREATE TABLE IF NOT EXISTS Programa_Proyecto(idPrograma unique, idProyecto, TipoAvance, Fecha, PorcentajeAvance)');
    
    //Empresas
    /*tx.executeSql('DROP TABLE IF EXISTS Empresa');
    tx.executeSql('CREATE TABLE IF NOT EXISTS Empresa (idEmpresa unique, rfc, nombre, siglas)');*/
    
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
    
    //Lista Tipo de Personal
    tx.executeSql('DROP TABLE IF EXISTS CatPersonal');
    tx.executeSql('CREATE TABLE IF NOT EXISTS CatPersonal (idCatPersonal unique, Tipo_Persona, Descripcion)');
    
    //Lista Tipo de Maquinaria
    tx.executeSql('DROP TABLE IF EXISTS RepCatPersonal');
    tx.executeSql('CREATE TABLE IF NOT EXISTS RepCatPersonal (idPropietario unique, idReporte, idCatPersonal, Cantidad, id_dispositivo, email)');
    }


function errorCB(err) {
    console.log("Error processing SQL: "+err.code + " Message: "+err.message);
}
function successTablas() {
    
      console.log("Se han insertado las tablas");
       //var x = 0;
       var x = 0;
       insertarInformacion(x);
  
}
var j = 0;
function insertarInformacion(x) {
    //code
    if (ProyectoDirectivo.length > x) {
        //code
        idProyecto = ProyectoDirectivo[x].proyecto.id_Proyecto;
        idUbicacion = ProyectoDirectivo[x].proyecto.id_ubicacion;
        Secretaria = ProyectoDirectivo[x].proyecto.id_secretaria;
        Dependencia = ProyectoDirectivo[x].proyecto.id_dependencia;
        Descripcion = ProyectoDirectivo[x].proyecto.descripcion;
        NombreLargo = ProyectoDirectivo[x].proyecto.nombre_largo;
        NombreCorto = ProyectoDirectivo[x].proyecto.nombre_corto;
        LimiteDesvio="15%";
        Desvio="0%";
        //insercion de los datos en la DB
        db.transaction(function(tx) {
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
                //x++;
                //insertarInformacion(x);
                AvancesFisicoProyecto(x, j);
            });
    }else{
        console.log("no hay Proyectos");
        //location.href="mapa_proyectos.html";
        //AvancesFisicoProyecto(0, 0);
    }
}
function AvancesFisicoProyecto(x, j) {
    //code
    if (j < ProyectoDirectivo[x].avanceFisico.length) {
        //code
        console.log("Avance Fisico "+ j +" :");
            IdAvanceFisico = ProyectoDirectivo[x].avanceFisico[j].id_AvanceFisico;
            idReferencia = ProyectoDirectivo[x].avanceFisico[j].id_referencia;
            PAvanceFisico = ProyectoDirectivo[x].avanceFisico[j].pavanceFisico;
            if (ProyectoDirectivo[x].avanceFisico[j].porcentaje_tendencia == 0) {
                Tendencia = 1;
            }else{
                Tendencia = ProyectoDirectivo[x].avanceFisico[j].porcentaje_tendencia;
            }
            FechaReporte = ProyectoDirectivo[x].avanceFisico[j].fechaReporte;
            Estado = ProyectoDirectivo[x].avanceFisico[j].estado;
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
            }
            db.transaction(function(tx) {
                //insercion ce los datos
                tx.executeSql('INSERT INTO AvanceFisicoProyectos(IdAvanceFisico, idProyecto, PAvanceFisico, Tendencia, FechaReporte, Estado)' +
                          ' VALUES (?, ?, ?, ?, ?, ?)',
                          [IdAvanceFisico, idReferencia, PAvanceFisico, Tendencia, FechaReporte, Estado]);
                 
            }, errorCB, function (){
                console.log("Se inserto correctamente el Avance Fisico: " + IdAvanceFisico);
                j++;
                //insertarInformacion(x);
                AvancesFisicoProyecto(x, j);
            });
    }else{
        console.log("no hay avances Fisicos");
        AvancesFinancieroProyecto(x, 0);
    }
}
var a = 0;
function AvancesFinancieroProyecto(x, a) {
    //code
    if (ProyectoDirectivo[x].avanceFinanciero.length > a) {
        //code
        IdAvanceFinanciero =ProyectoDirectivo[x].avanceFinanciero[a].id_AvanceFinaciero;
        F_idReferencia=ProyectoDirectivo[x].avanceFinanciero[a].id_referencia;
        F_PAvanceFinanciero=ProyectoDirectivo[x].avanceFinanciero[a].pavanceFinanciero;
        //F_Tendencia=String(AvanceFinancieroItems[x].porcentaje_tendencia);
        F_Tendencia=ProyectoDirectivo[x].avanceFinanciero[a].porcentaje_tendencia;
        F_FechaReporte=ProyectoDirectivo[x].avanceFinanciero[a].fechaReporte;
        F_Estado=ProyectoDirectivo[x].avanceFinanciero[a].estado;
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
        db.transaction(function(tx) {
            tx.executeSql('INSERT INTO AvanceFinancieroProyectos(IdAvanceFinanciero, idProyecto, PAvanceFinanciero, Tendencia, FechaReporte, Estado)' +
                          ' VALUES (?, ?, ?, ?, ?, ?)',
                          [IdAvanceFinanciero, F_idReferencia, F_PAvanceFinanciero, F_Tendencia, F_FechaReporte, F_Estado]);
            }, errorCB, function (){
                console.log("Se inserto correctamente el avance: " + IdAvanceFinanciero);
                a++;
                AvancesFinancieroProyecto(x, a);
                
            }); 
    }else{
        console.log("no hay Avance Financiero");
        //location.href="mapa_proyectos.html";
        obras(x, 0);
    }
}
var o = 0;
function obras(x, o) {
    //code
    if (ProyectoDirectivo[x].obras.length > o) {
        //code
        console.log("Obra "+ o +": " + ProyectoDirectivo[x].obras[o].obra.id_Obra);
        O_idObra =ProyectoDirectivo[x].obras[o].obra.id_Obra;
        O_idProyecto=ProyectoDirectivo[x].obras[o].obra.id_Poyecto;
        O_NoContrato=ProyectoDirectivo[x].obras[o].obra.noContrato;
        O_RFC=ProyectoDirectivo[x].obras[o].obra.rfc;
        O_Nombre=ProyectoDirectivo[x].obras[o].obra.nombre;
        O_Gobierno=ProyectoDirectivo[x].obras[o].obra.idGobierno;
        O_Secretaria=ProyectoDirectivo[x].obras[o].obra.idSecretaria;
        O_Dependencia=ProyectoDirectivo[x].obras[o].obra.idDependencia;
        O_Direccion=ProyectoDirectivo[x].obras[o].obra.direccion;
        O_Subdireccion=ProyectoDirectivo[x].obras[o].obra.subdireccion;
        O_Area=ProyectoDirectivo[x].obras[o].obra.area;
        O_EmpresaContratista=ProyectoDirectivo[x].obras[o].obra.id_EmpresaContratista;
        O_Superintendente=ProyectoDirectivo[x].obras[o].obra.superintendente;
        O_EntidadFederativa=ProyectoDirectivo[x].obras[o].obra.entidadFederativa;
        O_Descripcion=ProyectoDirectivo[x].obras[o].obra.descripcion;
        
        O_FechaContrato=ProyectoDirectivo[x].obras[o].obra.fechaContrato;
        O_TipoContrato=ProyectoDirectivo[x].obras[o].obra.tipoContrato;
        O_ImporteContratoSinIVA=ProyectoDirectivo[x].obras[o].obra.importeContratoSinIVA;
        O_ImporteConvenioAmpliacion=ProyectoDirectivo[x].obras[o].obra.importeConvenioAmpliacion;
        O_ImporteConvenioReduccion=ProyectoDirectivo[x].obras[o].obra.importeConvenioReduccion;
        O_ImporteAjusteCostos=ProyectoDirectivo[x].obras[o].obra.importeAjusteCostos;
            
        O_FechaInicioContrato=ProyectoDirectivo[x].obras[o].obra.fechaInicioContrato;
        O_FechaTerminoContrato=ProyectoDirectivo[x].obras[o].obra.fechaTerminoContrato;
            
        O_PeriodoEjucionDias=ProyectoDirectivo[x].obras[o].obra.periodoEjucionDias;
        O_PartidaPresupuestal=ProyectoDirectivo[x].obras[o].obra.partidaPresupuestal;
        O_Anticipo=ProyectoDirectivo[x].obras[o].obra.anticipo;
        O_NoFianzaAnticipo=ProyectoDirectivo[x].obras[o].obra.noFianzaAnticipo;
             
        O_FechaFianzaAnticipo=ProyectoDirectivo[x].obras[o].obra.fechaFianzaAnticipo;
        O_MontoFianzaAnticipo=ProyectoDirectivo[x].obras[o].obra.montoFianzaAnticipo;
        O_NoFianzaCumplimiento=ProyectoDirectivo[x].obras[o].obra.noFianzaCumplimiento;
        O_FechaFianzaCumplimiento=ProyectoDirectivo[x].obras[o].obra.fechaFianzaCumplimiento;
        O_MontoFianzaCumplimiento=ProyectoDirectivo[x].obras[o].obra.montoFianzaCumplimiento;
            
        O_CargoRevision1=ProyectoDirectivo[x].obras[o].obra.cargoRevision1;
        O_NombreRevision1=ProyectoDirectivo[x].obras[o].obra.nombreRevision1;
        O_CargoRevision2=ProyectoDirectivo[x].obras[o].obra.cargoRevision2;
        O_NombreRevision2=ProyectoDirectivo[x].obras[o].obra.nombreRevision2;
            
        O_NombreQuienAutoriza=ProyectoDirectivo[x].obras[o].obra.nombreQuienAutoriza;
        O_CargoVoBo=ProyectoDirectivo[x].obras[o].obra.cargoVoBo;
        O_NombreVoBo=ProyectoDirectivo[x].obras[o].obra.nombreVoBo;
           
        O_NombreEjercicioFiscal1=ProyectoDirectivo[x].obras[o].obra.nombreEjercicioFiscal1;
        O_ImporteFiscal1SinIVA=ProyectoDirectivo[x].obras[o].obra.importeFiscal1SinIVA;
        O_Borrador=ProyectoDirectivo[x].obras[o].obra.borrador;
          
        O_LimiteDesvio=30;
        O_Desvio=0;
        O_Ubicacion=ProyectoDirectivo[x].obras[o].obra.id_ubicacion;
        
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
                        [ O_idObra, O_idProyecto, O_NoContrato, O_RFC, O_Nombre, O_Gobierno, O_Secretaria, O_Dependencia, O_Direccion, O_Subdireccion, O_Area, O_EmpresaContratista,
                        O_Superintendente, O_EntidadFederativa, O_Descripcion, O_FechaContrato, O_TipoContrato, O_ImporteContratoSinIVA, O_ImporteConvenioAmpliacion, O_ImporteConvenioReduccion,
                        O_ImporteAjusteCostos, O_FechaInicioContrato, O_PeriodoEjucionDias, O_PartidaPresupuestal, O_Anticipo, O_NoFianzaAnticipo, O_FechaFianzaAnticipo, O_MontoFianzaAnticipo,
                        O_NoFianzaCumplimiento, O_FechaFianzaCumplimiento, O_MontoFianzaCumplimiento, O_CargoRevision1, O_NombreRevision1, O_CargoRevision2, O_NombreRevision2, O_NombreQuienAutoriza,
                        O_CargoVoBo, O_NombreVoBo, O_LimiteDesvio, O_Desvio, O_FechaTerminoContrato, O_NombreEjercicioFiscal1, O_ImporteFiscal1SinIVA, O_Borrador, O_Ubicacion]);
                            
                  //Insertando alerta de desvio fisico
                 tx.executeSql('INSERT INTO AlertasObras (idAlertaObra, idObra, LimiteDesvio, Tipo)' +
                              'VALUES (?, ?, ?, ?)',
                               [o+o+1, O_idObra, 100, 0]);
                  //Insertando alerta de desvio financiero
                 tx.executeSql('INSERT INTO AlertasObras (idAlertaObra, idObra, LimiteDesvio, Tipo)' +
                              'VALUES (?, ?, ?, ?)',
                               [o+o+2, O_idObra, 100, 1]);
                 //console.log("AlertasObras: ");
                   
            }, errorCB, function (){
                console.log("Se inserto correctamente la obra: " + O_idObra);
                //if (o == 0) {
                    //code
                    AvanceFisicoObra(x, o, 0);
                //}else{
                    //contObra++;
                    //obras(x, o);
                  //  AvanceFisicoObra(x, o, 0);
                //}
                });
        
    }else{
        console.log("no hay Obras");
        //AvanceFisicoObra(x, 0);
        InsertarEmpresas(0);
    }
}
//var f = 0;
var af = 0;
function AvanceFisicoObra(x, f, af) {
    //code
    if (ProyectoDirectivo[x].obras[f].avanceFisico.length > af) {
        //code
        console.log("id del avance fisico de obra "+ f +": " + ProyectoDirectivo[x].obras[f].avanceFisico[af].id_AvanceFisico);
        //console.log("id del avance fisico de obra: " + ProyectoDirectivo[x].obras[f].avanceFisico[af].id_AvanceFisico);
        IdAvanceFisico = ProyectoDirectivo[x].obras[f].avanceFisico[af].id_AvanceFisico;
        idReferencia = ProyectoDirectivo[x].obras[f].avanceFisico[af].id_referencia;
        PAvanceFisico = ProyectoDirectivo[x].obras[f].avanceFisico[af].pavanceFisico;
        if (ProyectoDirectivo[x].obras[f].avanceFisico[af].porcentaje_tendencia == 0) {
            Tendencia = 1;
        }else{
            Tendencia = ProyectoDirectivo[x].obras[f].avanceFisico[af].porcentaje_tendencia;     
        }
        FechaReporte = ProyectoDirectivo[x].obras[f].avanceFisico[af].fechaReporte;
        Estado = ProyectoDirectivo[x].obras[f].avanceFisico[af].estado;
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
        TipoEntidad=ProyectoDirectivo[x].obras[f].avanceFisico[af].tipo_Entidad;
    
        db.transaction(function(tx) {
            tx.executeSql('INSERT INTO AvanceFisicoObras(IdAvanceFisico, idObra, PAvanceFisico, Tendencia, FechaReporte, Estado)' +
                        ' VALUES (?, ?, ?, ?, ?, ?)',
                        [IdAvanceFisico, idReferencia, PAvanceFisico, Tendencia, FechaReporte, Estado]);
                    }, errorCB, function (){
                    console.log("Se inserto correctamente el avance: " + IdAvanceFisico);
                        af++;
                        AvanceFisicoObra(x, f, af);
                });
    }else{
        //o++;
        console.log("no hay Avance fisico de Obras");
        //obras(x, o);
        AvanceFinancieroObra(x, f, 0);
    }
}
var afinan = 0;
function AvanceFinancieroObra(x, f, afinan) {
    //code
    if (ProyectoDirectivo[x].obras[f].avanceFinanciero.length > afinan) {
        //code
        IdAvanceFinanciero =ProyectoDirectivo[x].obras[f].avanceFinanciero[afinan].id_AvanceFinaciero;
        F_idReferencia=ProyectoDirectivo[x].obras[f].avanceFinanciero[afinan].id_referencia;
        F_PAvanceFinanciero=ProyectoDirectivo[x].obras[f].avanceFinanciero[afinan].pavanceFinanciero;
        //F_Tendencia=String(AvanceFinancieroItems[x].porcentaje_tendencia);
        F_Tendencia=ProyectoDirectivo[x].obras[f].avanceFinanciero[afinan].porcentaje_tendencia;
        F_FechaReporte=ProyectoDirectivo[x].obras[f].avanceFinanciero[afinan].fechaReporte;
        F_Estado=ProyectoDirectivo[x].obras[f].avanceFinanciero[afinan].estado;
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
        db.transaction(function(tx) {
            
            tx.executeSql('INSERT INTO AvanceFinancieroObras(IdAvanceFinanciero, idObra, PAvanceFinanciero, Tendencia, FechaReporte, Estado)' +
                          ' VALUES (?, ?, ?, ?, ?, ?)',
                          [IdAvanceFinanciero, F_idReferencia, F_PAvanceFinanciero, F_Tendencia, F_FechaReporte, F_Estado]);
            }, errorCB, function (){
                console.log("Se inserto correctamente el avance: " + IdAvanceFinanciero);
                afinan++;
                AvanceFinancieroObra(x, f, afinan);
                
            });
    }else{
        //o++;
        console.log("no hay Avance fisico de Obras");
        ReporteD(x, f, 0);
        //obras(x, o);
    }
}
var RDiario = 0;
function ReporteD(x, o, RDiario) {
    //code
    //console.log("Reportes Diarios:" + ProyectoDirectivo[x].obras[o].reporteDiario.length);
    if (ProyectoDirectivo[x].obras[o].reportesDiarios) {
        /*console.log("no hay Reporte Diarios en la Obra: "+ o);
        o++;
        //ReporteD(x, o, 0);
        console.log("no hay Avance fisico de Obras");
        obras(x, o);*/
        if (ProyectoDirectivo[x].obras[o].reportesDiarios.length > RDiario){
            //code ReporteDiario (idReporte unique, idObra, email, fecha)
            console.log("Reportes Diarios:" + ProyectoDirectivo[x].obras[o].reportesDiarios.length);
            //console.log("id de Reporte Diario:" + ProyectoDirectivo[0].obras[1].reportesDiarios[0].reporteDiario.id_Obra);
            R_idReporte = ProyectoDirectivo[x].obras[o].reportesDiarios[RDiario].reporteDiario.id_ReporteDiario;
            R_idObra = ProyectoDirectivo[x].obras[o].reportesDiarios[RDiario].reporteDiario.id_Obra;
            R_email = ProyectoDirectivo[x].obras[o].reportesDiarios[RDiario].reporteDiario.email;
            R_fecha = ProyectoDirectivo[x].obras[o].reportesDiarios[RDiario].reporteDiario.fechaReporteDiario;
            console.log("fecha de reporte diario:" + R_fecha);
            R_Fecha = convertir(R_fecha);
            console.log("fecha de reporte diario:" + R_Fecha);
        
            db.transaction(function(tx) {
            
                tx.executeSql('INSERT INTO ReporteDiario(idReporte, idObra, email, fecha)' +
                              ' VALUES (?, ?, ?, ?)',
                              [R_idReporte, R_idObra, R_email, R_Fecha]);
                }, errorCB, function (){
                    console.log("Se inserto correctamente el reporte: " + R_idReporte + " de la obra " + o);
                //afinan++;
                //AvanceFinancieroObra(x, f, afinan);RDiario
                    //CatMaquinaria(x, o, Cmaquinaria, 0);
                    CatMaquinaria(x, o, RDiario, 0);
            });
        }else{
            console.log("no hay Reporte Diarios en la Obra: "+ o);
            o++;
        //ReporteD(x, o, 0);
        //console.log("no hay Avance fisico de Obras");
            obras(x, o);
        }
    }else{
        console.log("no hay Reporte Diarios en la Obra: "+ o);
        o++;
        //ReporteD(x, o, 0);
        //console.log("no hay Avance fisico de Obras");
        obras(x, o);
        
    }
}
var Cmaquinaria = 0;
function CatMaquinaria(x, o, rd, Cmaquinaria) {
    //code RepMaquinaria (idReporteMaq unique, idReporte, idCatMaquinaria, email, cantidad
    //console.log("id de reporte de maquinaria: " + ProyectoDirectivo[0].obras[1].reportesDiarios[0].repMaquinariaCatMaquinaria[1].idRepMaquinaria);
    if (ProyectoDirectivo[x].obras[o].reportesDiarios[rd].repMaquinariaCatMaquinaria) {
        //code
        if (ProyectoDirectivo[x].obras[o].reportesDiarios[rd].repMaquinariaCatMaquinaria.length > Cmaquinaria) {
        //code
        
            C_idReporteMaq = ProyectoDirectivo[x].obras[o].reportesDiarios[rd].repMaquinariaCatMaquinaria[Cmaquinaria].idRepMaquinaria;
            C_idReporte = ProyectoDirectivo[x].obras[o].reportesDiarios[rd].repMaquinariaCatMaquinaria[Cmaquinaria].idReporteDiario;
            C_idCatMaquinaria = ProyectoDirectivo[x].obras[o].reportesDiarios[rd].repMaquinariaCatMaquinaria[Cmaquinaria].idCatMaquinaria;
            C_email = ProyectoDirectivo[x].obras[o].reportesDiarios[rd].repMaquinariaCatMaquinaria[Cmaquinaria].email;
            C_cantidad = ProyectoDirectivo[x].obras[o].reportesDiarios[rd].repMaquinariaCatMaquinaria[Cmaquinaria].cantidad;
            db.transaction(function(tx) {
                tx.executeSql('INSERT INTO RepMaquinaria(idReporteMaq, idReporte, idCatMaquinaria, email, cantidad)' +
                              ' VALUES (?, ?, ?, ?, ?)',
                              [C_idReporteMaq, C_idReporte, C_idCatMaquinaria, C_email, C_cantidad]);
                }, errorCB, function (){
                    console.log("Se inserto correctamente el reporte: " + C_idReporteMaq);
                    Cmaquinaria++;
                //AvanceFinancieroObra(x, f, afinan);
                //CatMaquinaria(x, o, Cmaquinaria);
                //ReporteD(x, o, RDiario);
                    CatMaquinaria(x, o, rd, Cmaquinaria);
            });
        }else{
            console.log("entra aqui");
            RDiario++;
            ReporteD(x, o, RDiario);
        }
    }else{
        console.log("no hay Reporte de maquinaria en el reporte Diario: "+ rd+ " de la obra "+ o);
        RDiario++;
        ReporteD(x, o, RDiario);
        //console.log("no hay Avance fisico de Obras");
        //obras(x, o);
    }
}
function InsertarEmpresas(x) {
    //code
    if (EmpresaItems.length) {
        //code
        if (EmpresaItems.length > x) {
            //code
            IdEmpresa = EmpresaItems[x].id_Empresa;
        E_RFC = EmpresaItems[x].rfc;
        E_Nombre = EmpresaItems[x].nombre;
        E_Siglas = EmpresaItems[x].siglas;
        E_Calle = EmpresaItems[x].calle;
        E_Num_int = EmpresaItems[x].num_Int;
        E_Num_ext = EmpresaItems[x].num_Ext;
        E_CP = EmpresaItems[x].codi_Postal;
        E_Colonia = EmpresaItems[x].colonia;
        E_Deleg = EmpresaItems[x].del_o_Mun;
        E_entidad = EmpresaItems[x].entidad;
        tipoEmpresa = EmpresaItems[x].id_tipo_empresa;
        switch (tipoEmpresa){
            //case
            case '1':
                db.transaction(function(tx) {
                    tx.executeSql('INSERT INTO Contratista(idContratista, rfc, nombre, siglas, calle, num_Int, num_ext, CP, colonia, delegacion, entidad)' +  'VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)', [IdEmpresa, E_RFC, E_Nombre, E_Siglas, E_Calle, E_Num_int, E_Num_ext, E_CP, E_Colonia, E_Deleg, E_entidad]);
                        }, errorCB, function (){
                            console.log("::::::::::::::::: Se inserto correctamente la empresa: " + IdEmpresa);
                            x++;
                            InsertarEmpresas(x);
                    }); 
                break;
            case '2':
                db.transaction(function(tx) {
                    tx.executeSql('INSERT INTO Supervisora(idSupervisora, rfc, nombre, siglas, calle, num_Int, num_ext, CP, colonia, delegacion, entidad)' +  'VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)', [IdEmpresa, E_RFC, E_Nombre, E_Siglas, E_Calle, E_Num_int, E_Num_ext, E_CP, E_Colonia, E_Deleg, E_entidad]);
                        }, errorCB, function (){
                            console.log("::::::::::::::::: Se inserto correctamente la empresa: " + IdEmpresa);
                            x++;
                                         InsertarEmpresas(x);
                                 }); 
                break;
            case '3':
                                 db.transaction(function(tx) {
                                     tx.executeSql('INSERT INTO Dependencia(idDependencia, rfc, nombre, siglas, calle, num_Int, num_ext, CP, colonia, delegacion, entidad)' +  'VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)', [IdEmpresa, E_RFC, E_Nombre, E_Siglas, E_Calle, E_Num_int, E_Num_ext, E_CP, E_Colonia, E_Deleg, E_entidad]);
                                 }, errorCB, function (){
                                         console.log("::::::::::::::::: Se inserto correctamente la empresa: " + IdEmpresa);
                                         x++;
                                         InsertarEmpresas(x);
                                 }); 
                break;
            case '4':
                                 db.transaction(function(tx) {
                                     tx.executeSql('INSERT INTO Particular(idParticular, rfc, nombre, siglas, calle, num_Int, num_ext, CP, colonia, delegacion, entidad)' +  'VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)', [IdEmpresa, E_RFC, E_Nombre, E_Siglas, E_Calle, E_Num_int, E_Num_ext, E_CP, E_Colonia, E_Deleg, E_entidad]);
                                 }, errorCB, function (){
                                         console.log("::::::::::::::::: Se inserto correctamente la empresa: " + IdEmpresa);
                                         x++;
                                         InsertarEmpresas(x);
                                 }); 
                break;
            case '5':
                                 db.transaction(function(tx) {
                                     tx.executeSql('INSERT INTO Secretaria(idSecretaria, rfc, nombre, siglas, calle, num_Int, num_ext, CP, colonia, delegacion, entidad)' +  'VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)', [IdEmpresa, E_RFC, E_Nombre, E_Siglas, E_Calle, E_Num_int, E_Num_ext, E_CP, E_Colonia, E_Deleg, E_entidad]);
                                 }, errorCB, function (){
                                         console.log("::::::::::::::::: Se inserto correctamente la empresa: " + IdEmpresa);
                                         x++;
                                         InsertarEmpresas(x);
                                 }); 
                break;
            case '6':
                                 db.transaction(function(tx) {
                                     tx.executeSql('INSERT INTO Gobierno(idGobierno, rfc, nombre, siglas, calle, num_Int, num_ext, CP, colonia, delegacion, entidad)' +  'VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)', [IdEmpresa, E_RFC, E_Nombre, E_Siglas, E_Calle, E_Num_int, E_Num_ext, E_CP, E_Colonia, E_Deleg, E_entidad]);
                                 }, errorCB, function (){
                                         console.log("::::::::::::::::: Se inserto correctamente la empresa: " + IdEmpresa);
                                         x++;
                                         InsertarEmpresas(x);
                                 }); 
                break;
            default:
                console.log("Tipo de empresa no encontrado");
                break;
           } //switch
        }else{
            console.log("no existen empresas");
            InsertarUbicaciones(0);
        }
    }else{
        console.log("no existen empresas");
        InsertarUbicaciones(0);
    }
}
function InsertarUbicaciones(x) {
    //cod
    if (UbicacionItems.length) {
        //code
        if (UbicacionItems.length > x) {
        //code
            IdUbicacion = UbicacionItems[x].id_Ubicacion;
            U_Ubicacion = ""; //= UbicacionItems[x].ubicacion; //Arreglo de ubicaciones
             
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
            db.transaction(function(tx) {
                tx.executeSql('INSERT INTO Ubicacion(idUbicacion, puntos)' +
                            'VALUES (?, ?)',
                            [IdUbicacion, U_Ubicacion]);
                }, errorCB, function (){
                    console.log("::::::::::::::::: Se inserto correctamente la ubicacion: " + IdUbicacion);
                    x++;
                    InsertarUbicaciones(x);
            });
        }else{
            console.log("no hay ubicaciones");
            InsertarMultimedia(0);
        }
    }else{
        console.log("no hay ubicaciones");
        InsertarMultimedia(0);
    }
}
function InsertarMultimedia(x) {
    //code
    if (MultimediaItems.length) {
        //code
        if (MultimediaItems.length > x) {
            //code
            console.log("Cargando datos espere un momento ::: 4 X=" + x + " Multimedia: "+ MultimediaItems.length);       
            idMultimedia =MultimediaItems[x].idMultimedia;
            M_idReferencia=MultimediaItems[x].idReferencia;
            M_TipoEntidad=MultimediaItems[x].tipoReferencia;
            M_Path=MultimediaItems[x].path;
            M_TipoArchivo=String(MultimediaItems[x].tipoArchivo);
            M_Fecha=MultimediaItems[x].fecha;
            M_Descripcion=MultimediaItems[x].descripcion;
            M_Formato = MultimediaItems[x].formato;
            //-------------------------------------------------------------------------------------
             /*if (M_TipoEntidad == 1 || M_TipoEntidad == "1" && M_Formato == "0" || M_Formato == 0) {
                    console.log("Insertando multimedia Obras: " + idMultimedia + " idObra : "+M_idReferencia); 
                        tx.executeSql('INSERT INTO MultimediaObras(idMultimedia, idObra, TipoArchivo, Path, Fecha, Descripcion, Formato)' +
                                   ' VALUES (?, ?, ?, ?, ?, ?, ?)',
                                    [idMultimedia, M_idReferencia, M_TipoArchivo, M_Path, M_Fecha, M_Descripcion, M_Formato]);
                    
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
                                           "http://1-dot-iuyet-csgit-cao.appspot.com/serve?blob-key="+MultimediaItems[x].path, /////path
                                           sPath + "img_"+M_Descripcion+"_"+M_idReferencia+"_"+x+".jpg",
                                           function(theFile) {
                                           console.log("download complete: " + theFile.toURL());
                                           //alert("MPATH==="+ theFile.toURL() + "X==="+x);
                                           M_Path = theFile.toURL();
                                           console.log("Se inserto correctamente el path: " + M_Path);
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
                    
                }
                x++;
                InsertarMultimedia(x);
            */
            //-------------------------------------------------------------------------------------
            db.transaction(function(tx) {
                if (M_TipoEntidad == 0 || M_TipoEntidad == "0") {
                    console.log("Insertando multimedia Proyectos: " + idMultimedia + " idProyecto : "+M_idReferencia); 
                        tx.executeSql('INSERT INTO MultimediaProyectos(idMultimedia, idProyecto, TipoArchivo, Path, Fecha, Descripcion, Formato)' +
                                    ' VALUES (?, ?, ?, ?, ?, ?, ?)',
                                    [idMultimedia, M_idReferencia, M_TipoArchivo, M_Path, M_Fecha, M_Descripcion, M_Formato]);
                }
                //if (M_TipoEntidad == 1 || M_TipoEntidad == "1" && M_Formato == 0 || M_Formato == "0") {
                if (M_TipoEntidad == 1 || M_TipoEntidad == "1") {
                    console.log("Insertando multimedia Obras: " + idMultimedia + " idObra : "+M_idReferencia); 
                        tx.executeSql('INSERT INTO MultimediaObras(idMultimedia, idObra, TipoArchivo, Path, Fecha, Descripcion, Formato)' +
                                   ' VALUES (?, ?, ?, ?, ?, ?, ?)',
                                    [idMultimedia, M_idReferencia, M_TipoArchivo, M_Path, M_Fecha, M_Descripcion, M_Formato]);
                }
                if (M_TipoEntidad == 6 || M_TipoEntidad == "6") {
                    console.log("Insertando multimedia Obras: " + idMultimedia + " idObra : "+M_idReferencia); 
                        tx.executeSql('INSERT INTO MultimediaObras(idMultimedia, idObra, TipoArchivo, Path, Fecha, Descripcion, Formato)' +
                                   ' VALUES (?, ?, ?, ?, ?, ?, ?)',
                                    [idMultimedia, M_idReferencia, M_TipoArchivo, M_Path, M_Fecha, M_Descripcion, M_Formato]);
                }
                if (M_TipoEntidad == 2 || M_TipoEntidad == "2") {
                    console.log("Insertando multimedia Maquinaria: " + idMultimedia + " idObra : "+M_idReferencia); 
                        tx.executeSql('INSERT INTO MultimediaMaquinaria(idMultimedia, idMaquinaria, TipoArchivo, Path, Fecha, Descripcion, Formato)' +
                                   ' VALUES (?, ?, ?, ?, ?, ?, ?)',
                                    [idMultimedia, M_idReferencia, M_TipoArchivo, M_Path, M_Fecha, M_Descripcion, M_Formato]);
                }
                if (M_TipoEntidad == 7 || M_TipoEntidad == "7") {
                    console.log("Insertando multimedia Minuta: " + idMultimedia + " idObra : "+M_idReferencia);
                    tx.executeSql('INSERT INTO MultimediaMinuta(idMultimedia, idObra, TipoArchivo, Path, Fecha, Descripcion, Formato)' +
                                   ' VALUES (?, ?, ?, ?, ?, ?, ?)',
                                    [idMultimedia, M_idReferencia, M_TipoArchivo, M_Path, M_Fecha, M_Descripcion, M_Formato]);
                }
                }, errorCB, function (){
                    console.log("Se inserto correctamente el multimedia: " + idMultimedia);
                    x++;
                    InsertarMultimedia(x);
            });
        }else{
            console.log("no hay multimedias");
            InsertarMaquinaria(0);
            //insertarProgramaProyecto(0); 
        }
    }else{
        console.log("no hay multimedias");
        InsertarMaquinaria(0);
        //insertarProgramaProyecto(0); 
    }
}
//MaquinariaItems, CatTipoMaquinariaItems
/*
    tx.executeSql('DROP TABLE IF EXISTS Maquinaria');
    tx.executeSql('CREATE TABLE IF NOT EXISTS Maquinaria (idMaquinaria unique, nombre, descripcion, tipo)');
    
    //Lista Tipo de Maquinaria
    tx.executeSql('DROP TABLE IF EXISTS TipoMaquinaria');
    tx.executeSql('CREATE TABLE IF NOT EXISTS TipoMaquinaria (idTipo_Maquinaria unique, Tipo_Maquinaria, Descripcion)');
*/
function InsertarMaquinaria(x) {
    //code
    if(MaquinariaItems.length) {
        if (MaquinariaItems.length > x) {
            //code
            M_idMaquinaria = MaquinariaItems[x].id_Maquinaria;
            M_idtipo = MaquinariaItems[x].id_tipo_Maquinaria;
            M_nombre = MaquinariaItems[x].nombre;
            M_descripcion = MaquinariaItems[x].descripcion;
        
            db.transaction(function(tx) {
                tx.executeSql('INSERT INTO Maquinaria(idMaquinaria, nombre, descripcion, tipo)' +
                            'VALUES (?, ?, ?, ?)',
                            [M_idMaquinaria, M_nombre, M_descripcion, M_idtipo]);
                }, errorCB, function (){
                    console.log("::::::::::::::::: Se inserto correctamente la Maquinaria: " + M_idMaquinaria);
                    x++;
                    InsertarMaquinaria(x);
            });
        }else{
            console.log("no hay maquinaria");
            insertarTipo_maquinaria(0);
        }
    }else{
        console.log("no hay maquinaria");
        insertarTipo_maquinaria(0);
    }
}
//lista tipo de maquinaria
function insertarTipo_maquinaria(x) {
    //code
    if (CatTipoMaquinariaItems) {
        console.log("cargando los datos a la base de datos.");
                      //code
        if (x < CatTipoMaquinariaItems.length ) {
                                 //code
            C_Tidtipomaquinaria = CatTipoMaquinariaItems[x].id_tipo_Maquinaria;
            C_Ttipomaquinaria = CatTipoMaquinariaItems[x].tipo_Maquinaria;
            C_Tdescripcion = CatTipoMaquinariaItems[x].descripcion;
                      
            db.transaction(function(tx) {
                tx.executeSql('INSERT INTO TipoMaquinaria(idTipo_Maquinaria, Tipo_Maquinaria, Descripcion)' +
                            'VALUES (?, ?, ?)',
                            [C_Tidtipomaquinaria, C_Ttipomaquinaria, C_Tdescripcion]);
                }, errorCB,function (){
                    console.log("Se inserto correctamente documentos de proyectos: ");
                    x++;
                    insertarTipo_maquinaria(x);
            });
        }else{
            console.log("No hay Tipo Maquinaria");
            insertarCatPersonal(0);    
        }
        insertarCatPersonal(0);
    }
}
function insertarCatPersonal(x) {
    //code
    if (CatPersonalItems) {
        console.log("cargando los datos a la base de datos.");
                      //code
        if (x < CatTipoMaquinariaItems.length ) {
                                 //code
            C_Pidtipomaquinaria = CatPersonalItems[x].id_Tipo_Personal;
            C_Ptipomaquinaria = CatPersonalItems[x].tipo_Personal;
            C_Pdescripcion = CatPersonalItems[x].descipcion;
                      
            db.transaction(function(tx) {
                tx.executeSql('INSERT INTO CatPersonal(idCatPersonal, Tipo_Persona, Descripcion)' +
                            'VALUES (?, ?, ?)',
                            [C_Pidtipomaquinaria, C_Ptipomaquinaria, C_Pdescripcion]);
                }, errorCB,function (){
                    console.log("Se inserto correctamente Catalogo de Personal: ");
                    x++;
                    insertarCatPersonal(x);
            });
        }else{
            console.log("No hay Catalogo de Personal");
            insertarRepCatPersonal(0);
            //insertarProgramaProyecto(0);
        }
        insertarRepCatPersonal(0);
    }
}
function insertarRepCatPersonal(x) {
    //code
    if (RepCatPersonalItems) {
        console.log("cargando los datos a la base de datos.");
                      //code
        if (x < RepCatPersonalItems.length ) {
                                 //code
            C_RPropietario = RepCatPersonalItems[x].idPropietario;
            C_RidReporteDiario = RepCatPersonalItems[x].idReporteDiario;
            C_RidCatPersonal = RepCatPersonalItems[x].idCatPersonal;
            C_Rcantidad = RepCatPersonalItems[x].cantidad;
            C_RidDispo = RepCatPersonalItems[x].idDispo;
            C_Pemail = RepCatPersonalItems[x].email;
                      
            db.transaction(function(tx) {
                tx.executeSql('INSERT INTO RepCatPersonal(idPropietario, idReporte, idCatPersonal, Cantidad, id_dispositivo, email)' +
                            'VALUES (?, ?, ?, ?, ?, ?)',
                            [C_RPropietario, C_RidReporteDiario, C_RidCatPersonal, C_Rcantidad, C_RidDispo, C_Pemail]);
                }, errorCB,function (){
                    console.log("Se inserto correctamente Reporte Catalogo de Personal: ");
                    x++;
                    insertarRepCatPersonal(x);
            });
        }else{
            console.log("No hay Catalogo de Personal");
            insertarProgramaProyecto(0);    
        }
        insertarProgramaProyecto(0);
    }
}
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
                     //location.href="mapa_proyectos.html";
                  Directorio(x);
                  //insertarTempAvancesProyectos(0);
                });           
}
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
function insertarDirectorioProyectos(x) {
           //code
           if (PersonaItems && x >= PersonaItems.length) {
                      
                      console.log("cargando los datos a la base de datos.");
                      //insertarDirectorioObras(0);
                      insertarTempAvancesProyectos(0);
                      
           } else if (PersonaItems && PersonaItems.length > 0) {
                      
                      console.log("Cargando Directorio: " + PersonaItems.length);
                      console.log("Cargando Directorio: X= " + x + " Personas: "+ PersonaItems.length);
                      
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
                      //insertarDirectorioObras(0);
                      insertarTempAvancesProyectos(0);
           }
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
                                             " idAvanceFinanciero = " +resulset.idAvanceFinanciero)
            }
            insertTemp(0);
                   
        }, errorCB);
    }, errorCB, successTemp());
}
function successTemp() {
    console.log("Consulta de avances temporales Proyectos OK");
    //insertTemp(0);
}
function insertTemp(x) {
    if (APTI && x >= APTI.length) {
        console.log("Cargando datos espere un momento vamos a insertar avances temporales de Obra:::   X=" + x );
        insertarTempAvancesObras();
    }else if (APTI && APTI.length > 0) {
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
function insertTempObra(x) {
    if (AOTI && x >= AOTI.length) {
        console.log("Todas las inserciones terminadas:::::::::::::::::::::::::::::::::");
        location.href="mapa_proyectos.html";
        // insertarAvanceFinancieroProyectos(0);
    }else if (AOTI && AOTI.length > 0) {
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
    }
}
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
 function fail(evt) {
        console.log("Error file transfer to device:::: "+evt.target.error.code);
}