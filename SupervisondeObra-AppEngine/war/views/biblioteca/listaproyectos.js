var x=0;
var AvanceFisicoItems, AvanceFinancieroItems;
var IdAvanceFisico, idReferencia, TipoEntidad, PAvanceFisico, Tendencia, FechaReporte, Estado;
var IdAvanceFinanciero, F_idReferencia, F_TipoEntidad, F_PAvanceFisico, F_Tendencia, F_FechaReporte, F_Estado;


// Insertar Avance
function insertarAvances(x) {
  // alert("Avances: Fisicos: "+AvanceFisicoItems.length +" Financieros: "+AvanceFinancieroItems.length);
            
   
   if(AvanceFisicoItems || AvanceFinancieroItems){ //Si hay items
           
            
             if (x < AvanceFisicoItems.length) {
            IdAvanceFisico =AvanceFisicoItems[x].id_AvanceFisico;
            idReferencia=AvanceFisicoItems[x].id_referencia;
            PAvanceFisico=AvanceFisicoItems[x].pavanceFisico;
            Tendencia=AvanceFisicoItems[x].porcentaje_tendencia;
            FechaReporte=AvanceFisicoItems[x].fechaReporte;
            Estado=AvanceFisicoItems[x].estado;
            TipoEntidad=AvanceFisicoItems[x].tipo_Entidad;
             }
             
             if (x < AvanceFinancieroItems.length) {
             IdAvanceFinanciero =AvanceFinancieroItems[x].id_AvanceFinaciero;
            F_idReferencia=AvanceFinancieroItems[x].id_referencia;
            F_PAvanceFinanciero=AvanceFinancieroItems[x].pavanceFinanciero;
            F_Tendencia=AvanceFinancieroItems[x].porcentaje_tendencia;
            F_FechaReporte=AvanceFinancieroItems[x].fechaReporte;
            F_Estado=AvanceFinancieroItems[x].estado;
            F_TipoEntidad=AvanceFinancieroItems[x].tipo_Entidad;
             }
            
            var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
             db.transaction(function(tx) {
                
            if (x < AvanceFisicoItems.length) {
                // alert("Avances: Fisicos: "+IdAvanceFisico +" "+ idReferencia+" "+TipoEntidad+" "+PAvanceFisico+" "+Tendencia+" "+FechaReporte+" "+Estado);
            
            
                // tx.executeSql('DELETE FROM AvanceFisico');
                 tx.executeSql('INSERT INTO AvanceFisico(IdAvanceFisico, idReferencia, TipoEntidad, PAvanceFisico, Tendencia, FechaReporte, Estado)' +
                               'VALUES (?, ?, ?, ?, ?, ?, ?)',
                                [IdAvanceFisico, idReferencia, TipoEntidad, PAvanceFisico, Tendencia, FechaReporte, Estado]);
             }
             
             if (x < AvanceFinancieroItems.length) {
                    //code
                   // alert("Avances: Financiero: "+IdAvanceFinanciero +" "+ F_idReferencia+" "+F_TipoEntidad+" "+F_PAvanceFinanciero+" "+F_Tendencia+" "+F_FechaReporte+" "+F_Estado);
                    // tx.executeSql('DELETE FROM AvanceFinanciero');
                     tx.executeSql('INSERT INTO AvanceFinanciero(IdAvanceFinanciero, idReferencia, TipoEntidad, PAvanceFinanciero, Tendencia, FechaReporte, Estado)' +
                               'VALUES (?, ?, ?, ?, ?, ?, ?)',
                                [IdAvanceFinanciero, F_idReferencia, F_TipoEntidad,  F_PAvanceFinanciero, F_Tendencia, F_FechaReporte, F_Estado]);
                 }
                
            }, errorCB, function (){
             //   alert("Se inserto correctamente el proyecto: " + x);
                if (x < AvanceFisicoItems.length || x < AvanceFinancieroItems.length){
                    x++;
                    insertarAvances(x);
                    }else{alert("x es mayor se va a consultar proyectos"); consultarProyectos();}
                });
        }//if
}

function errorCB(err) {
    console.log("Error processing SQL: "+err.code + " Message: "+err.message);
}


function EndPoints(){
    alert('::::::::::::::::: en iniciar');
     var ROOT = 'https://' + 'focal-furnace-615.appspot.com' + '/_ah/api';
    //Consultar EndPoint
    gapi.client.load('avance_fisicoendpoint', 'v1', function(){
        gapi.client.avance_fisicoendpoint.listAvance_Fisico().execute(function(resp){
            if(resp.code) alert("Error de conexi&oacute;n, verificar su salida a internet :::" + resp.message );
            AvanceFisicoItems = resp.items;            
       });//listProyectoEndPoint
    },ROOT); //Load ProyectosEndPoint
         
    //Consultar EndPoint
    gapi.client.load('avance_financieroendpoint', 'v1', function(){
        gapi.client.avance_financieroendpoint.listAvance_Financiero().execute(function(resp){
            if(resp.code) alert("Error de conexi&oacute;n, verificar su salida a internet :::" + resp.message );
            AvanceFinancieroItems = resp.items;
       });//listProyectoEndPoint
    },ROOT); //Load ProyectosEndPoint
    
      alert('::::::::::::::::: termino cargar' + AvanceFisicoItems + " " + AvanceFinancieroItems);
}


/*
function iniciar() {
    //code
     console.log('::::::::::::::::: en iniciar');
    EndPoints(function(){
       console.log('termin— de cargar los endpoints');
    insertarAvances(x); 
});

 
}*/   
