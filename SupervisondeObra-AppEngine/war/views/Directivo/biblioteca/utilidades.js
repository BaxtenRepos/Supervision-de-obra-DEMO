//1-dot-cao-iuyet-server.appspot.com
var ROOT = 'https://' + '1-dot-iuyet-csgit-cao.appspot.com' + '/_ah/api';
//var ROOT = 'https://' + '1-dot-cao-iuyet-server.appspot.com' + '/_ah/api';
     /*  Ir a la pagina de resultado de busquedas*/
function buscar(pagina){
     //code
     dato = document.getElementById("txtbuscar").value;
     location.href="resultado_busqueda.html?pagina="+pagina+"&dato="+dato;
}
     /*  Ir a la pagina de envio de email tomando el email seleccionado del directorio*/
     function enviarCorreo(pagina)
     {
          
          if (pagina!= "detalle_proyectos1" && pagina!="detalle_obras1") {
               //code
               dato = document.getElementById("mail").value;
               console.log("email ::" + dato);
          //location.href="email.html?pagina="+pagina+"&mail="+dato;
          var args = {
               toRecipients:      [dato],
               //ccRecipients:      [],
               //bccRecipients :     [],
               subject: 'Control de Avance de Obras',
               //body:    'CAO',
               // attachments: [],
               isHtml:  true
          };
         }else{
          var args = {
               toRecipients:      [],
               //ccRecipients:      [],
               //bccRecipients :     [],
               subject: 'Control de Avance de Obras',
               //body:    'CAO',
               // attachments: [],
               isHtml:  true
          };
          
         }
         
         
       Cordova.exec(null, null, "EmailComposer", "showEmailComposer", [args]);
     }
     
     
     
     /*  Obtener parametro por GET*/
     function getGET(){
        var loc = document.location.href;
        var getString = loc.split('?')[1];
        var GET = getString.split('&');
        var get = {};//this object will be filled with the key-value pairs and returned.
     
        for(var i = 0, l = GET.length; i < l; i++){
           var tmp = GET[i].split('=');
           get[tmp[0]] = unescape(decodeURI(tmp[1]));
        }
        return get;
     }
     
     function Proyectos(bq){
          console.log("bq " + bq);
       db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
                 db.transaction(function(tx) {
                     //tx.executeSql("SELECT * FROM Proyectos WHERE NombreCorto LIKE ? or NombreLargo LIKE ? or idProyecto LIKE ?",[bq, bq, bq],  function(tx, rs) {
                     tx.executeSql("SELECT LimiteDesvio, Nombre, Descripcion FROM Obras WHERE Nombre LIKE ? or Descripcion LIKE ? or idObra LIKE ?",[bq, bq, bq],  function(tx, rs) {
                         console.log("entra a la consulta");
                       for (var i = 0; i < rs.rows.length; i++) {
         // for (var i = 0; i < 4; i++) {
                         var p = rs.rows.item(i);
                         document.getElementById("resultado").innerHTML  += 
                           "<a href=\"detalle_proyectos.html?idProyecto=1\">" +
                             "<div class=\"meta name\"> "  +
                               //"<div class=\"img_wrapper\"></div>"  +
                                 "<div class=\"titles\">"  +
                                   "<table><tr><td width=\"450\"><h2>"+p.Descripcion  +"</h2></td></tr></table>"+
                                   "<p><em>" +p.Nombre+" </em></p>" +
                                 "</div>"  +
                               "</div>"  +
                             "<div class=\"meta region\">" +
                               "<p>  </p>"  +
                             "</div>" +
                             "<div class=\"meta area\">" +
                               "<p>" +p.LimiteDesvio+"</p>"  +
                             "</div>"  +
                          "</a>" ;
                       }
                     }, errorCB);
                 }, errorCB, querySuccess);
     }
     function Obras(bq){
       db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
                 db.transaction(function(tx) {
                    //SELECT Nombre, Descripcion FROM Obras WHERE Nombre LIKE '%obra%' or Descripcion LIKE '%obra%' or idObra LIKE '%obra%'
                    // tx.executeSql("SELECT * FROM Obra WHERE NombreCorto LIKE ? or NombreLargo LIKE ? or O_idObra LIKE ?",[bq, bq, bq],  function(tx, rs) {
                    tx.executeSql("SELECT Nombre, Descripcion FROM Obras WHERE Nombre LIKE ? or Descripcion LIKE ? or idObra LIKE ?",[bq, bq, bq],  function(tx, rs) {
                       for (var i = 0; i < rs.rows.length; i++) {
                         var p = rs.rows.item(i);
                         document.getElementById("resultado").innerHTML  += 
                         "<a href=\"detalle_obras.html?idProyecto=1\">" +
                              "<div class=\"meta name\"> "  +
                                   "<div class=\"img_wrapper\"> <img src=\"assets/img/others/acadia.jpg\"/></div>"  +
                                        "<div class=\"titles\">"  +
                                             "<table><tr><td width=\"450\"><h2>"+p.NombreLargo  +"</h2></td></tr></table>"+
                                             "<p><em>" +p.NombreCorto+" </em></p>" +
                                        "</div>"  +
                                   "</div>"  +
                                   "<div class=\"meta region\">" +
                                        "<p> "+p.Dependencia+"</p>"  +
                                   "</div>" +
                                   "<div class=\"meta area\">" +
                                      "<p>" +p.LimiteDesvio+"</p>"  +
                                   "</div>"  +
                         "</a>" ;
                       }
                     }, errorCB);
                 }, errorCB, querySuccess);
     }
//funcion de buscar por pagina
function searchProyecto() {

     getIdDirectivo();
     
	
     var get = getGET();
     var pagina = get.pagina;
     var dato = get.dato;
     var bq = '%' + dato + '%';

     switch (pagina) {
          case 'mapa_proyectos':
                //alert("pagina " + pagina);
               Proyectos(bq);
          break;
          case 'lista_proyectos':
                //alert("pagina " + pagina);
               Obras(bq);
          break;
          case 'detalle_proyectos':
               Obras(bq);
          break;
          case 'galeria':
               Obras(bq);
          break;
          default:
               Obras(bq);
          break;
     };
}
//fin de la funcion de buscar ---
function limiteDesvio(){
  db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
  // console.log("cosulta");
    db.transaction(function(tx) {
    // tx.executeSql("select max(AvanceFisicoObras.FechaReporte), Proyectos.Desvio, Proyectos.NombreCorto from Proyectos, AvanceFisicoObras where  Proyectos.Desvio < 80",[],  function(tx, rs) {
      tx.executeSql("SELECT MAX(AvanceFisicoProyectos.FechaReporte) as Fecha, Proyectos.Desvio, Proyectos.NombreCorto FROM Proyectos , AvanceFisicoProyectos ;",[],  function(tx, rs) {
  console.log("entro a la consulta");
      for (var i = 0; i < rs.rows.length; i++) {
        var p = rs.rows.item(i);
        console.log("p " + p);
        document.getElementById("notificacion").innerHTML  =
          "<div class=\"heading\">" +
                    p.NombreCorto +
                  "</div>" +
                  "<div class=\"description\">" +
                    "Desvio de "+ p.Desvio +
                  "</div>" +
                  "<div class=\"date pull-left\">" +
                  p.Fecha +
                  "</div>";
      }
    }, errorCB);
    }, errorCB, querySuccess);
}
// 
function updateDesvio(){
     db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
     console.log("cosulta de update");
     db.transaction(function(tx) {
     // tx.executeSql("select max(AvanceFisicoObras.FechaReporte), Proyectos.Desvio, Proyectos.NombreCorto from Proyectos, AvanceFisicoObras where  Proyectos.Desvio < 80",[],  function(tx, rs) {
     // idAlerta unique,  idProyecto, LimiteDesvio, Tipo
          tx.executeSql(    function(tx, rs) {
               //tx.executeSql("update AlertasProyectos set idAlerta,  ;",[],  function(tx, rs) {
               console.log("entro a la consulta");
               if (p.idAlerta <= 50) {
	    //code
	    document.getElementById("update").innerHTML  =
          "<div class=\"heading\">" +
                    p.idAlerta +
                  "</div>" +
                  "<div class=\"description\">" +
                    "Desvio de "+ p.LimiteDesvio +
                  "</div>" +
                  "<div class=\"date pull-left\">" +
                  p.Tipo +
                  "</div>";
	}else{
	    document.getElementById("update").innerHTML  =
          "<div class=\"heading\">" +
                  "</div>" +
                  "<div class=\"description\">" +
                    "No Hay Alertas" +
                  "</div>" +
                  "<div class=\"date pull-left\">" +
                  "</div>";
	}
          }, errorCB);
     }, errorCB, querySuccess);
}
function querySuccess(tx, results) {
   //alert("Todo OK");
}
// Funcion que manda mensaje de error en la consulta de web sql
function errorCB(err) {
    alert("Error processing SQL: "+err.code + " Message: "+err.message);
}

 function cargar (args) {
	    //code
}
      
function descargaPDF() {
    alert("Vista Previa PDF");
   
	//var ref = window.open('https://docs.google.com/a/csgit.com.mx/uc?authuser=0&id=0B0PbNiCm1xfQUDJhNDJVY2x5UDQ&export=download', '_blank', 'location=yes');
        var ref = window.open('http://1-dot-iuyet-csgit-cao.appspot.com/serve?blob-key=AMIfv94ffdVC1orZMQdPaa33pulBOY0XdxoF1XeNLQ9ELqfT8-zON-DNf4z3JA1kyZKvd5FIwAckXZfX3bf97Svo1iiOx81k6fk9kwIGc8WQWkz4ea6ZckzuxNSJVSdLvC8lIJ3DhlMvg6UB7sno5U9NqlXktY-3gsxoBlieYn7VXs9hQoL_lUw&export=download', '_blank', 'location=yes');
        ref.addEventListener('loadstart', function(event) { /*alert('start: ' + event.url); */});
        ref.addEventListener('loadstop', function(event) { /*alert('stop: ' + event.url); */});
        ref.addEventListener('loaderror', function(event) { /*alert('error: ' + event.message); */});
        ref.addEventListener('exit', function(event) { /*alert(event.type); */});
   
}
 
function descargaExcel() {
      alert("Vista Previa Excel");
   
	var ref = window.open('https://docs.google.com/a/csgit.com.mx/uc?authuser=0&id=0B0PbNiCm1xfQSW1BV0JpUzdUSUU&export=download', '_blank', 'location=yes');
        ref.addEventListener('loadstart', function(event) { /*alert('start: ' + event.url); */});
        ref.addEventListener('loadstop', function(event) { /*alert('stop: ' + event.url); */});
        ref.addEventListener('loaderror', function(event) { /*alert('error: ' + event.message); */});
        ref.addEventListener('exit', function(event) { /*alert(event.type); */});
   
}

function descargaPPT() {
     alert("Vista Previa PPT");
    var ref = window.open('http://www.acis.org.co/fileadmin/Base_de_Conocimiento/XXV_Salon_de_Informatica/ArquitecturasSoftware-RaquelAnaya.ppt', '_blank', 'location=yes');
        ref.addEventListener('loadstart', function(event) { /*alert('start: ' + event.url); */});
        ref.addEventListener('loadstop', function(event) { /*alert('stop: ' + event.url); */});
        ref.addEventListener('loaderror', function(event) { /*alert('error: ' + event.message); */});
        ref.addEventListener('exit', function(event) { /*alert(event.type); */});
}
// Funcion que hace la busqueda desde una tabla en html.
function doSearch(){
  var tableReg = document.getElementById('regTable');
  var searchText = document.getElementById('searchTerm').value.toLowerCase();
  for (var i = 1; i < tableReg.rows.length; i++) {
    var cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
    var found = false;
    for (var j = 0; j < cellsOfRow.length && !found; j++) {
      var compareWith = cellsOfRow[j].innerHTML.toLowerCase();
      if (searchText.length == 0 || (compareWith.indexOf(searchText) > -1)) {
        found = true;
      }
    }
    if (found){
      tableReg.rows[i].style.display = '';
    }else{
      tableReg.rows[i].style.display = 'none';
    }
  }
}
    var idDirectivo=0;
   
function getIdDirectivo(){
     get_proyecto();
     get_mapa();
  db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
            db.transaction(function(tx) {
             
              tx.executeSql('SELECT * FROM Usuario',[],  function(tx, rs) {
               
                      if (rs.rows.length>0) {
                        //code
                        //* console.log("Usuario en la bse de datos: " + rs.rows.length +" Usuario:"+rs.rows.item(0).idPropietario);
                         usuarioBD = rs.rows.item(0);
                        
                         idPersonaBD = rs.rows.item(0).idPersona;
                         idDirectivo = rs.rows.item(0).idPropietario;
                         Pass = rs.rows.item(0).Password;
                         UsuarioBD = rs.rows.item(0).Usuario;
                         Nombre = rs.rows.item(0).Nombre;
                         Apellido = rs.rows.item(0).APaterno;
                         ApellidoM = rs.rows.item(0).AMaterno;
                         Telefonos = rs.rows.item(0).Telefonos;
                         Correos = rs.rows.item(0).Emails;
                         
                      }
                     
              }, function (){
                console.log("Consulta usuario ERROR Return::: " );
                
              });
               
          }, function (){
                console.log("Consulta usuario ERROR Return::: " );
          
          }, function (){
               // console.log("Consulta usuario OK ::: " + idDirectivo);
                
                document.getElementById("sincro").innerHTML= '<a href="cargar.html?idDirectivo='+ idDirectivo +'" class="" >'+
            '<div class="iconset top-reload"></div>'+
            '</a>';
            
                 document.getElementById("userBarTitle").innerHTML= '<span class="badge badge-important">0</span>'+ " " +Nombre +
						        '<span class="bold">'+" "+Apellido+' </span>';
                 document.getElementById("userMenu").innerHTML= Nombre+'<br>'+Apellido+'<br>'+ApellidoM;
                 
                 if (document.getElementById("userData")) {
                    //code
                      document.getElementById("userData").innerHTML= 
                 '<h4 class="semi-bold no-margin">'+Nombre+' '+Apellido+' '+ApellidoM+'</h4>'+
                                        //'<h6 class="no-margin">Ingeniero Civil</h6>'+
                                        '<br>'+
                                        //'<p><span class="semi-bold">Dependencia:</span> CONAGUA</p>'+
                                       // '<p><span class="semi-bold">Cargo:</span> Director</p>'+
                                        '<p><span class="semi-bold">Telefono(s):</span>'+Telefonos+'</p>'+
                                        '<p><span class="semi-bold">Correo (s):</span> '+Correos+'</p>';
                                        //'<br>'+
                                        //'<p><i class="fa fa-globe"></i>www.conagua.com</p>';
                 }
          });
    //console.log("Consulta usuario OK Return::: " + idDirectivo);
    get_mapa();
}



function sincro(){
     
     if (getIdDirectivo()) {
          //code
         alert("Cargar ::::::::::: " + getIdDirectivo());
         location.href="cargar.html?idDirectivo="+ getIdDirectivo();
          
          
     }
     
}

function cancelPass(){
     
     document.getElementById("clave").value = "";
      document.getElementById("nueva").value = "";
       document.getElementById("confirmar").value = "";
     
}

function changePass(){
     
      if(checkConnection() != "No network connection" && checkConnection() != "Unknown connection"){
         // Messenger().post("Conexi&oacute;n establecida: " + checkConnection());          
          //console.log("Iniciando descarga de datos::::1");
          changePassOK();
            }else{
                      
                  alert("Para actualizar su clave de acceso es necesario estar conectado a una red con salida a internet");
               
                  
            }
     
     
}

function changePassOK(){
     
     var passclave, passnueva, passconfirmar; 
     clave = document.getElementById("clave").value;
     nueva = document.getElementById("nueva").value;
     confirmar = document.getElementById("confirmar").value;
     
     passclave = CryptoJS.SHA256(clave);
     passnueva = CryptoJS.SHA256(nueva);
     passconfirmar = CryptoJS.SHA256(confirmar);
     
     if (clave.trim()=="" || nueva.trim() == "" || confirmar.trim()=="") {
          //code
           document.getElementById("errorGeneral").innerHTML= "Favor de introducir todos los datos";
           //document.getElementById("errorConfirmar").innerHTML= "Favor de introducir datos";
          // document.getElementById("errorNueva").innerHTML= "Favor de introducir datos";
          // document.getElementById("errorClave").innerHTML= "Favor de introducir datos";

     
     }else if(!(nueva.trim() == confirmar.trim())){
           document.getElementById("errorGeneral").innerHTML= "";
           document.getElementById("errorConfirmar").innerHTML= "Los datos no coinciden";
           
     }else{
          
          //if (!(Pass==clave)) {
          if (!(Pass==passclave)) {
               //code
               document.getElementById("errorGeneral").innerHTML= "";
               document.getElementById("errorConfirmar").innerHTML= "";
               document.getElementById("errorClave").innerHTML= "La clave no es correcta";
          }else{
           
          
          document.getElementById("errorGeneral").innerHTML= "";
          document.getElementById("errorConfirmar").innerHTML= "";
          
             db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
             db.transaction(function(tx) {
                       console.log("Actualizando pass de usuario :::" );
                //UPDATE AlertasProyectos SET LimiteDesvio = ? WHERE LimiteDesvio = 0 AND Tipo = 2   
                 tx.executeSql('UPDATE Usuario SET  Password=?'+
                               ' WHERE idUsuario = 1 ',
                               [passnueva]);
                                //[nueva]);
                 
            }, errorCB, function (){
               
               //actualizarEndPointUser(nueva);
               actualizarEndPointUser(passnueva);
                /*console.log("Se actualizo correctamente el usuario:  password = "+ nueva);
                  document.getElementById("errorGeneral").innerHTML= "";
                  document.getElementById("errorClave").innerHTML= "";
                  document.getElementById("errorNueva").innerHTML= "";
                  document.getElementById("errorConfirmar").innerHTML= "";
                  document.getElementById("clave").value = "";
                  document.getElementById("nueva").value = "";
                  document.getElementById("confirmar").value = "";
                 
                document.getElementById("okPass").innerHTML= 
                '<div class="alert alert-success">'+
                 ' <button class="close" data-dismiss="alert"></button>'+
                  'Clave actualizada correctamente. </div>';
                
               */
                
                
            });
          
          }
          
          
          
     }
     
}



function actualizarEndPointUser(pass){

     //Consultar EndPoint Usuario
           gapi.client.load('usuarioendpoint', 'v1', function(){ 
               gapi.client.usuarioendpoint.updateUsuario({"id_Propietario": idDirectivo, "id_Persona": idPersonaBD, "id_Tipo_Persona": "2", "usuario": UsuarioBD, "contrasena": pass }).execute(function(resp){
                  if(resp.code && !resp) {
                      console.log("Error de conexi�n, no se ha podido descargar Usuarios, volviendo a intentar. :::" + resp.message );
                    
                      }else{
                      
                  //console.log("Se actualizo correctamente el usuario:  password = "+ nueva);
                  console.log("Se actualizo correctamente el usuario:  password = "+ passnueva);
                  document.getElementById("errorGeneral").innerHTML= "";
                  document.getElementById("errorClave").innerHTML= "";
                  document.getElementById("errorNueva").innerHTML= "";
                  document.getElementById("errorConfirmar").innerHTML= "";
                  document.getElementById("clave").value = "";
                  document.getElementById("nueva").value = "";
                  document.getElementById("confirmar").value = "";
                 
                document.getElementById("okPass").innerHTML= 
                '<div class="alert alert-success">'+
                 ' <button class="close" data-dismiss="alert"></button>'+
                  'Clave actualizada correctamente. </div>';
                      }
               });//list
           },ROOT); //Load vf
}


 function checkConnection() {
            var networkState = navigator.connection.type;

            var states = {};
            states[Connection.UNKNOWN]  = 'Unknown connection';
            states[Connection.ETHERNET] = 'Ethernet connection';
            states[Connection.WIFI]     = 'WiFi connection';
            states[Connection.CELL_2G]  = 'Cell 2G connection';
            states[Connection.CELL_3G]  = 'Cell 3G connection';
            states[Connection.CELL_4G]  = 'Cell 4G connection';
            states[Connection.CELL]     = 'Cell generic connection';
            states[Connection.NONE]     = 'No network connection';

            console.log('Connection type: ' + states[networkState]);
            
            return states[networkState];
        }

function NetworkError(){
	
        Messenger().post({
	 message: msg,
	 type: 'error',
         showCloseButton: true
	          });
}
//funciones de alerta de Desvio
function Alertas() {
     //code
     var fisico;
     var financiero;
     var get = getGET();
     var id_obra = get.idObra;
     console.log("obra: " + id_obra);
     //--------------------------------
     var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
     //code
     db.transaction(function(tx) {
          //tx.executeSql('select * from  AlertasObras cast(idObra as int) = ?',[id_obra],function(tx, rs) {
          tx.executeSql('select * from  AlertasObras where cast(idObra as int) = ?',[id_obra],function(tx, rs) {     
               
               console.log("Proyectos: " + rs.rows.length);
               for(var i = 0; i < rs.rows.length; i++){
                    var p = rs.rows.item(i);
                    if (p.Tipo == 0) {
                     //code
                         console.log("id de Alerta Fisica " + p.idAlertaObra + " Obra " + p.idObra + " limite desvio " + p.LimiteDesvio +" tipo " + p.Tipo);
                         fisico = p.LimiteDesvio;
                         console.log("desvio fisico" + fisico);
                    }else{
                         console.log("id de Alerta Financiera " + p.idAlertaObra + " Obra " + p.idObra + " limite desvio " + p.LimiteDesvio +" tipo " + p.Tipo);
                         financiero = p.LimiteDesvio;
                         console.log("desvio financiero" + financiero);
                    }
                  //idAlerta unique,  idProyecto, LimiteDesvio, Tipo
               }
          }, errorCB);            
     }, errorCB, successConsulta3());
     //funciones para el slider
     $(function() {
          $( "#slider-range-min" ).slider({
               range: "min",
               value: fisico, // que obtiene de la base de datos
               min: 0,
               max: 50,
               slide: function( event, ui ) {
                    $( "#amount" ).val( ui.value );
                    }
          });
          $( "#amount" ).val($( "#slider-range-min" ).slider( "value" ) );
     });
     //slide 2
     $(function() {
          $( "#slider-range-min-2" ).slider({
               range: "min",
               value: financiero, // que obtiene de la base de datos
               min: 0,
               max: 50,
               slide: function( event, ui ) {
                    $( "#amount-2" ).val( ui.value );
               }
          });
          $( "#amount-2" ).val($( "#slider-range-min-2" ).slider( "value" ) );
     });
}
//Alertas para los Proyectos
function Alertas_Proyectos() {
     //code
     var fisico;
     var financiero;
     var get = getGET();
     var id_proyecto = get.idProyecto;
     //--------------------------------
     var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
     //console.log("mostrando datos de proyecto id: ");
     db.transaction(function (tx) {
          tx.executeSql('select * from  AlertasProyectos where cast(idProyecto as int) = ?', [id_proyecto], function (tx, rs) {
               console.log("Proyectos: " + rs.rows.length);
               for (var i = 0; i < rs.rows.length; i++) {
                    var p = rs.rows.item(i);
                    if (p.Tipo == 0) {
                        //code
                        console.log("id de Alerta Fisica2 " + p.idAlerta + " Proyecto " + p.idProyecto + " limite desvio " + p.LimiteDesvio + " tipo " + p.Tipo);
                        fisico = p.LimiteDesvio;
                        console.log("desvio fisico" + fisico);
                    } else {
                        console.log("id de Alerta Financiera " + p.idAlerta + " Proyecto " + p.idProyecto + " limite desvio " + p.LimiteDesvio + " tipo " + p.Tipo);
                        financiero = p.LimiteDesvio;
                        console.log("desvio financiero" + financiero);
                    }
                    //idAlerta unique,  idProyecto, LimiteDesvio, Tipo
               }
          }, errorCB);
     }, errorCB, successConsulta3());
//Funciones para los slides---------------
     $(function () {
          $("#slider-range-min").slider({
               range: "min",
               value: fisico, // que obtiene de la base de datos
               min: 0,
               max: 50,
               slide: function (event, ui) {
                    $("#amount").val(ui.value);
                    }
          });
          $("#amount").val($("#slider-range-min").slider("value"));
     });
         //slide 2
     $(function () {
          $("#slider-range-min-2").slider({
               range: "min",
               value: financiero, // que obtiene de la base de datos
               min: 0,
               max: 50,
               slide: function (event, ui) {
                    $("#amount-2").val(ui.value);
                    }
          });
          $("#amount-2").val($("#slider-range-min-2").slider("value"));
     });
}
function successConsulta3() {
     console.log("consulta realizada.....");      
}
/*
function proyecto() {
     //code
     //console.log("valores de los proyectos: " + valor);
     var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
     db.transaction(function(tx) {
          tx.executeSql('select * from  Usuario ', [], function (tx, rs) {
               console.log("usuario directivo (funcion proyecto): " + rs.rows.length);
               
          }, errorCB);
     }, errorCB, querySuccess);
     
     //return valor;
}
*/
function proyecto2(pagina) {
     //code
     //console.log("pagina: " + pagina);
     var paginas = ["mapa","lista_proyectos", "ayuda", "perfil", "acerca_de", "detalle_proyectos", "detalle_obras", "galeria", "cargar"];
     var countp;
     /*for(var i = 0; i<paginas.length;i++){
          if(paginas[i] == pagina){
               console.log("pagina del for (proyecto2): "+paginas[i]);
          }
     }
     */
     var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
     db.transaction(function(tx) {
          tx.executeSql('select * from Proyectos ', [], function (tx, rs) {
               countp = rs.rows.length;
               //countp = 2;
               //console.log("(funcion proyecto2 Proyectos): " + countp);
               
          }, errorCB);
     }, errorCB, querySuccess);
     //-----------------------------------------------------------
     db.transaction(function(tx) {
          tx.executeSql('select * from Logotipos ', [], function (tx, rs) {
               //countp = rs.rows.length;
               //countp = 2;
               //console.log("entra a la consulta logo: " + countp);
               for(var i = 0; i<paginas.length;i++){
                    if(paginas[i] == pagina && countp == 1){
                         //console.log("pagina del for (proyecto2): "+paginas[i]);
                         //console.log("se muestra el logo");
                         
                    //}else if(paginas[5] == pagina && countp >= 2 || paginas[6] == pagina || paginas[7] == pagina){
                    }else if( (countp >= 2 && "detalle_proyectos" == pagina) || (countp >= 2 && "detalle_obras" == pagina) || (countp >= 2 && "galeria" == pagina)){//bien
                         //continue;
                         console.log("se muestra cuando es mayor a 2");
                         break;
                    }
               }
          }, errorCB);
     }, errorCB, querySuccess);
     
     //return valor;
}
//funcion del Directorio
function directorio_proyecto() {
     //code
     var get = getGET();
     var id_proyecto = get.idProyecto;
     var id_directorio = 0;
     //var elemento = document.querySelector('#id');
     db.transaction(function(tx) {
          //SELECT * FROM DirectorioProyecto as dp, Directorio as d WHERE dp.id_Persona == d.idPersona and dp.id_Referencia == "1"
          //tx.executeSql('SELECT * FROM DirectorioProyecto as dp, Directorio as d WHERE dp.id_Persona == d.idPersona', [], function (tx, rs) {
          tx.executeSql('SELECT * FROM DirectorioProyecto as dp, Directorio as d WHERE dp.id_Persona == d.idPersona and cast(dp.id_Referencia as int) == ?', [id_proyecto], function (tx, rs) {
               for(var i = 0; i<rs.rows.length;i++){
                    var p = rs.rows.item(i);
                    //console.log("directorio: " + p.idDirectorio + " nombre:"+p.Nombre);
                    //id_directorio = p.idDirectorio;
                    document.getElementById("regTable").innerHTML +=
                              "<tr>"+
                                   "<td>"+
                                        "<div class=\"user-details-wrapper active\" data-chat-status=\"online\" data-chat-user-pic=\"biblioteca/Imagenes/user.png\" data-chat-user-pic-retina=\"assets/img/profiles/d2x.jpg\" data-user-name=\"Jane Smith\" onclick=\"showPopup("+p.idPersona+")\">"+
                                        //"<div class=\"user-details-wrapper active\" data-chat-user-pic=\"assets/img/profiles/d.jpg\" data-chat-user-pic-retina=\"assets/img/profiles/d2x.jpg\">"+
                                             "<div class=\"user-profile\">"+
                                                  "<img src=\"https://1-dot-iuyet-csgit-cao.appspot.com/serve?blob-key="+p.Fotografia+"\" alt=\"\" data-src=\"https://1-dot-iuyet-csgit-cao.appspot.com/serve?blob-key="+p.Fotografia+"\" data-src-retina=\"assets/img/profiles/d2x.jpg\" width=\"35\" height=\"35\">"+
                                             "</div>"+
                                             "<div class=\"user-details\">"+
                                                  "<div class=\"user-name\" type=\"text\" id=\""+p.idPersona+"\">"+
                                                       p.Nombre+" "+p.ApPaterno+" "+p.ApMaterno+
                                                  "</div>"+
                                                  "<div class=\"user-more\">"+
                                                       p.Cargo+
                                                  "</div>"+
                                             "</div>"+
                                             "<div class=\"clearfix\"></div>"+
                                        "</div>"+
                                   "</td>"+
                              "</tr>";
                              //console.log("directorio: " + id_directorio);
               }
               //console.log("usuario: " +id_directorio);
               //prueba();
          }, errorCB);
     }, errorCB, querySuccess); 
}
function datos_contactos_proyecto(directorio1) {
     //code
     //alert("hola");
     //console.log("directorio prueba: " +directorio1);
     document.getElementById("divPopupContent").innerHTML = 
                            "<div class=\"grid simple\">"+
                                "<div class=\"grid-title no-border\">"+
                                    "<img src=\"assets/img/profiles/avatar_small.jpg\" alt=\"\" data-src=\"assets/img/profiles/avatar_small.jpg\" data-src-retina=\"assets/img/profiles/avatar_small2x.jpg\" width=\"35\" height=\"35\" />"+
                                    "<span class=\"semi-bold\" style=\"font-size: large\"> Maria Cardenas </span>"+
                                "</div>"+
                                "<div class=\"grid-body no-border\">"+
                                    "<div class=\"row-fluid\">"+
                                        "<div class=\"scroller\" data-height=\"220px\" data-always-visible=\"1\">"+
                                            "<p><span class=\"semi-bold\">Dependencia:</span>Prueba</p>"+
                                            "<p><span class=\"semi-bold\">Cargo:</span> Intendente</p>"+
                                            "<p><span class=\"semi-bold\">Telefono(s):</span> 55-54056519</p>"+
                                            "<p><span class=\"semi-bold\">Correo (s):</span> "+
                                                "<input readonly=\"true\" id=\"mail\" style=\"background-color: transparent; border: hidden\" size=\"30\" value=\"intendente@conagua.com\"></input>"+
                                            "</p>"+
                                            "<button class=\"btn btn-block btn-success\" onClick=\"enviarCorreo('mapa_proyectos')\">Enviar correo</button>"+
                                            "<button class=\"btn btn-block btn-success\" onClick=\"hidePopup()\">Cancelar</button>"+
                                        "</div>"+
                                    "</div>"+
                                "</div>"+
                            "</div>";
     var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
                            
     db.transaction(function(tx) {
          tx.executeSql('select * from Directorio where cast(idPersona as int) = ?', [directorio1], function (tx, rs) {
               for(var i = 0; i<rs.rows.length;i++){
                    var p = rs.rows.item(i);
                    //console.log("Personas en el directorio: " + p);
                    //console.log("directorio: " + p.idPersona +" Proyecto: " + p.idProyecto+ " Empresa: " +p.NombreEmpresa+" nommbre: "+p.Nombre+ " Apaterno:"+p.ApPaterno+" amaterno:" +p.ApMaterno+ " Cargo:"+p.Cargo+" fotografia:"+p.Fotografia+" email:" +p.Email);
                    document.getElementById("divPopupContent").innerHTML = 
                         "<div class=\"grid simple\">"+
                              "<div class=\"grid-title no-border\">"+
                                   "<img src=\"https://1-dot-iuyet-csgit-cao.appspot.com/serve?blob-key="+p.Fotografia+"\" alt=\"\" data-src=\"https://1-dot-iuyet-csgit-cao.appspot.com/serve?blob-key="+p.Fotografia+"\" data-src-retina=\"assets/img/profiles/avatar_small2x.jpg\" width=\"35\" height=\"35\" />"+
                                   "<span class=\"semi-bold\" style=\"font-size: large\"> "+p.Nombre+" "+p.ApPaterno+" "+p.ApMaterno+"</span>"+
                              "</div>"+
                              "<div class=\"grid-body no-border\">"+
                                   "<div class=\"row-fluid\">"+
                                        "<div class=\"scroller\" data-height=\"220px\" data-always-visible=\"1\">"+
                                            //"<p><span class=\"semi-bold\">Dependencia:</span> "+p.NombreEmpresa+"</p>"+
                                            "<p><span class=\"semi-bold\">Cargo:</span> "+p.Cargo+"</p>"+
                                            "<p><span class=\"semi-bold\">Telefono(s):</span> "+p.Telefono+"</p>"+
                                            "<p><span class=\"semi-bold\">Radio(s):</span> "+p.Radio+"</p>"+
                                            "<p><span class=\"semi-bold\">Skype:</span> "+p.Skype+"</p>"+
                                            "<p><span class=\"semi-bold\">RFC:</span> "+p.RFCPersonal+"</p>"+
                                            "<p><span class=\"semi-bold\">Correo (s):</span> "+
                                                "<input readonly=\"true\" id=\"mail\" style=\"background-color: transparent; border: hidden\" size=\"30\" value=\""+p.Email+"\"></input>"+
                                            "</p>"+
                                            "<button class=\"btn btn-block btn-success\" onClick=\"enviarCorreo('mapa_proyectos')\">Enviar correo</button>"+
                                            "<button class=\"btn btn-block btn-success\" onClick=\"hidePopup()\">Cancelar</button>"+
                                        "</div>"+
                                   "</div>"+
                              "</div>"+
                         "</div>";
               }
               //console.log("usuario: " +id_directorio);
               //prueba();
          }, errorCB);
     }, errorCB, querySuccess);
                            
}

function showPopup(directorio){
     console.log("directorio: " + directorio);
//Showpopup
     document.getElementById('divBackground').style.visibility='visible';
     document.getElementById('divPopup').style.visibility='visible';
     document.getElementById('divBackground').style.display='block';
     document.getElementById('divPopup').style.display='block';
     datos_contactos_proyecto(directorio);
}
function hidePopup() {
    //Hidepopup
    document.getElementById('divBackground').style.visibility='hidden';
    document.getElementById('divPopup').style.visibility='hidden';
    document.getElementById('divBackground').style.display='none';
    document.getElementById('divPopup').style.display='none';
}
//Directorio Obras

function directorio_obra(idobra) {
     //code
     var id_obra = 0;
     //var elemento = document.querySelector('#id');
     var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
     db.transaction(function(tx) {
          tx.executeSql('SELECT * FROM DirectorioObra as do, Directorio as d WHERE do.id_Persona == d.idPersona and cast(do.id_Referencia as int) == ?', [idobra], function (tx, rs) {
               for(var i = 0; i<rs.rows.length;i++){
                    var p = rs.rows.item(i);
                    //console.log("directorio: " + p.idPersona +" Obra: " + p.idObra+ " Empresa: " +p.NombreEmpresa+" nommbre: "+p.Nombre+ " Apaterno:"+p.ApPaterno+" amaterno:" +p.ApMaterno+ " Cargo:"+p.Cargo+" fotografia:"+p.Fotografia+" email:" +p.Email);
                    //id_directorio = p.idDirectorio;
                    document.getElementById("regTable").innerHTML +=
                    //document.getElementByClassName("directorio").innerHTML =
                              "<tr>"+
                                   "<td>"+
                                        "<div class=\"user-details-wrapper active\" data-chat-status=\"online\" data-chat-user-pic=\"biblioteca/Imagenes/user.png\" data-chat-user-pic-retina=\"assets/img/profiles/d2x.jpg\" data-user-name=\"Jane Smith\" onclick=\"showPopup_2("+p.idPersona+")\">"+
                                        //"<div class=\"user-details-wrapper active\" data-chat-user-pic=\"assets/img/profiles/d.jpg\" data-chat-user-pic-retina=\"assets/img/profiles/d2x.jpg\">"+
                                             "<div class=\"user-profile\">"+
                                                  //"<img src=\"biblioteca/Imagenes/user.png\" alt=\"\" data-src=\"biblioteca/Imagenes/user.png\" data-src-retina=\"assets/img/profiles/d2x.jpg\" width=\"35\" height=\"35\">"+
                                                  "<img src=\"https://1-dot-iuyet-csgit-cao.appspot.com/serve?blob-key="+p.Fotografia+"\" alt=\"\" data-src=\"https://1-dot-iuyet-csgit-cao.appspot.com/serve?blob-key="+p.Fotografia+"\" data-src-retina=\"assets/img/profiles/d2x.jpg\" width=\"35\" height=\"35\">"+
                                             "</div>"+
                                             "<div class=\"user-details\">"+
                                                  "<div class=\"user-name\" type=\"text\" id=\""+p.idPersona+"\">"+
                                                       p.Nombre+" "+p.ApPaterno+" "+p.ApMaterno+
                                                  "</div>"+
                                                  "<div class=\"user-more\">"+
                                                       p.Cargo+
                                                  "</div>"+
                                             "</div>"+
                                             "<div class=\"clearfix\"></div>"+
                                        "</div>"+
                                   "</td>"+
                              "</tr>";
                              //id_directorio = elemento.getAttribute('id');
                              //id_directorio = $("div:text[id=\""+p.idDirectorio+"\"]").val();
               }
               //prueba();
          }, errorCB);
     }, errorCB, querySuccess); 
}
function per_usuario_obra(directorio1) {
     //code
     //alert("hola");
     //*console.log("directorio prueba: " +directorio1);
     var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
                            
     db.transaction(function(tx) {
          //SELECT * FROM Directorio as d, DirectorioProyecto as dp WHERE d.idPersona == "1" group by dp.id_Referencia
          //tx.executeSql('select * from Directorio where cast(idDirectorioObra as int) = ?', [directorio1], function (tx, rs) {
          tx.executeSql('SELECT * FROM Directorio as d, DirectorioProyecto as dp WHERE cast(d.idPersona as int) == ? group by dp.id_Referencia', [directorio1], function (tx, rs) {
               for(var i = 0; i<rs.rows.length;i++){
                    var p = rs.rows.item(i);
                    //console.log("Personas en el directorio: " + p);
                    //*console.log("directorio: " + p.idDirectorioObra +" Proyecto: " + p.idObra+ " Empresa: " +p.NombreEmpresa+" nommbre: "+p.Nombre+ " Apaterno:"+p.ApPaterno+" amaterno:" +p.ApMaterno+ " Cargo:"+p.Cargo+" fotografia:"+p.Fotografia+" email:" +p.Email);
                    document.getElementById("divPopupContent").innerHTML = 
                         "<div class=\"grid simple\">"+
                              "<div class=\"grid-title no-border\">"+
                                   //"<img src=\"assets/img/profiles/avatar_small.jpg\" alt=\"\" data-src=\"assets/img/profiles/avatar_small.jpg\" data-src-retina=\"assets/img/profiles/avatar_small2x.jpg\" width=\"35\" height=\"35\" />"+
                                   "<img src=\"https://1-dot-iuyet-csgit-cao.appspot.com/serve?blob-key="+p.Fotografia+"\" alt=\"\" data-src=\"https://1-dot-iuyet-csgit-cao.appspot.com/serve?blob-key="+p.Fotografia+"\" data-src-retina=\"assets/img/profiles/avatar_small2x.jpg\" width=\"35\" height=\"35\" />"+
                                   "<span class=\"semi-bold\" style=\"font-size: large\"> "+p.Nombre+" "+p.ApPaterno+" "+p.ApMaterno+"</span>"+
                              "</div>"+
                              "<div class=\"grid-body no-border\">"+
                                   "<div class=\"row-fluid\">"+
                                        "<div class=\"scroller\" data-height=\"220px\" data-always-visible=\"1\">"+
                                            "<p><span class=\"semi-bold\">Dependencia:</span> "+p.NombreEmpresa+"</p>"+
                                            "<p><span class=\"semi-bold\">Cargo:</span> "+p.Cargo+"</p>"+
                                            "<p><span class=\"semi-bold\">Telefono(s):</span> "+p.Telefono+"</p>"+
                                            "<p><span class=\"semi-bold\">Radio(s):</span> "+p.Radio+"</p>"+
                                            "<p><span class=\"semi-bold\">Skype:</span> "+p.Skype+"</p>"+
                                            "<p><span class=\"semi-bold\">RFC:</span> "+p.RFCPersonal+"</p>"+
                                            "<p><span class=\"semi-bold\">Correo (s):</span> "+
                                                "<input readonly=\"true\" id=\"mail\" style=\"background-color: transparent; border: hidden\" size=\"30\" value=\""+p.Email+"\"></input>"+
                                            "</p>"+
                                            "<button class=\"btn btn-block btn-success\" onClick=\"enviarCorreo('mapa_proyectos')\">Enviar correo</button>"+
                                            "<button class=\"btn btn-block btn-success\" onClick=\"hidePopup()\">Cancelar</button>"+
                                        "</div>"+
                                   "</div>"+
                              "</div>"+
                         "</div>";
               }
               //console.log("usuario: " +id_directorio);
               //prueba();
          }, errorCB);
     }, errorCB, querySuccess);
}
function showPopup_2(directorio){
     //*console.log("directorio: " + directorio);
//Showpopup
     document.getElementById('divBackground').style.visibility='visible';
     document.getElementById('divPopup').style.visibility='visible';
     document.getElementById('divBackground').style.display='block';
     document.getElementById('divPopup').style.display='block';
     per_usuario_obra(directorio);
}
function hidePopup_2() {
    //Hidepopup
    document.getElementById('divBackground').style.visibility='hidden';
    document.getElementById('divPopup').style.visibility='hidden';
    document.getElementById('divBackground').style.display='none';
    document.getElementById('divPopup').style.display='none';
}
//----------
function get_proyecto(){
     var get = getGET();
     var idproyecto = get.idProyecto;
     var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
          db.transaction(function(tx) {
	     tx.executeSql('SELECT * FROM  Proyectos',[],  function(tx, rs) {
               //*console.log("proyectos: " + rs.rows.length);
               proyectos = rs.rows.length; 
               if (proyectos == 1) {
                    //code
                    document.getElementById("detalle_proyecto").innerHTML =
                    "<a href=\"detalle_proyectos.html?idProyecto="+idproyecto+"\"> <i class=\"icon-custom-form\"></i> Detalle del Proyecto</a>";
                    //document.getElementById("mapa_proyecto").innerHTML =
                    //"<a href=\"mapa_proyectos.html?idProyecto="+idproyecto+"\"> <i class=\"icon-custom-map\"></i> Mapa de Proyectos</a>";
               }else{
                    document.getElementById("detalle_proyecto").innerHTML =
                    "<a href=\"lista_proyectos.html?idProyecto="+idproyecto+"\"> <i class=\"icon-custom-form\"></i> Lista de Proyectos</a>";
               }
               /*for (var i = 0; i < rs.rows.length; i++) {
                    var p = rs.rows.item(i);
                    console.log("id de Proyecto: " + p.idProyecto);
                    console.log("Nombre Corto: " + p.NombreCorto);
                    document.getElementById("detalle_proyecto").innerHTML =
                    "<a href=\"detalle_proyectos.html?idProyecto="+p.idProyecto+"\"> <i class=\"icon-custom-form\"></i> Detalle del Proyecto</a>";
                    //"<a href=\"lista_proyectos.html\"> <i class=\"icon-custom-form\"></i>Lista Proyectos</a>";
               }*/
          }, errorCB);
        }, errorCB, successConsulta);
}
function errorCB(err) {
	console.log("Error al graficar avances processing SQL: "+err.code + " Message: "+err.message);
}
function successConsulta() {
     //code
     console.log("La consulta se realizo exitosamente: ");
}
function get_mapa() {
     //code
     var get = getGET();
     var proyecto = get.idProyecto;
     //*console.log("proyecto id del mapa: " + proyecto);
     document.getElementById("mapa_proyecto").innerHTML =
     "<a href=\"mapa_proyectos.html?idProyecto="+proyecto+"\"> <i class=\"icon-custom-map\"></i> Mapa de Proyectos</a>";
     perfil(proyecto);
}
function perfil(proyecto) {
     //code
     document.getElementById("perfil").innerHTML =
     "<a href=\"perfil.html?idProyecto="+proyecto+"\"> <i class=\"icon-custom-map\"></i>Perfil</a>";
     acerca(proyecto);
}
function acerca(proyecto) {
     //code
     document.getElementById("acerca").innerHTML =
     "<a href=\"acerca_de.html?idProyecto="+proyecto+"\"> <i class=\"icon-custom-map\"></i> Acerca de</a>";
     ayuda(proyecto);
}
function ayuda(proyecto) {
     //code
      //code
     document.getElementById("ayuda").innerHTML =
     "<a href=\"ayuda.html?idProyecto="+proyecto+"\"> <i class=\"icon-custom-map\"></i> Ayuda </a>";
}