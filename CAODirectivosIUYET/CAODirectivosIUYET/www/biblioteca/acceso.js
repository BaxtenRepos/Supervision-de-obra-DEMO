
//var ROOT = 'https://' + '1-dot-cao-iuyet-server.appspot.com' + '/_ah/api';
//var ROOT = 'https://' + '1-dot-iuyet-csgit-cao.appspot.com' + '/_ah/api';//desarrollo
var ROOT = 'https://' + '1-dot-superobra-adquem.appspot.com' + '/_ah/api';//adquem
//var ROOT = 'https://' + '1-dot-crypto-plane-782.appspot.com' + '/_ah/api';
//var ROOT = 'https://' + '1-dot-cao-iuyet-pruebas.appspot.com' + '/_ah/api';//pruebas
var datosUsuario;
var usuarioBD;
//var noproyectos = 2;
if (window.location.hostname == 'localhost'
   || window.location.hostname == '127.0.0.1'
   || ((window.location.port != "") && (window.location.port > 999))) {
   // We're probably running against the DevAppServer
   //ROOT = 'https://' + '1-dot-crypto-plane-782.appspot.com' + '/_ah/api';
   //ROOT = 'https://' + '1-dot-iuyet-csgit-cao.appspot.com' + '/_ah/api';//desarrollo
   ROOT = 'https://' + '1-dot-superobra-adquem.appspot.com' + '/_ah/api';//adquem
   //ROOT = 'https://' + '1-dot-cao-iuyet-pruebas.appspot.com' + '/_ah/api';//pruebas
   //ROOT = 'https://' + '1-dot-cao-iuyet-server.appspot.com' + '/_ah/api';
}

function initializeUsuario(){
   
   console.log("Creando Base initializeUsuario::::::::::::::::::: ");
   db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
   db.transaction(guardarUsuario, errorCBU, successTablasU);
}
           
        
function guardarUsuario(tx){
   /*gapi.client.load('communicationchannel', 'v1', function(){ 
      gapi.client.communicationchannel.listUsuario().execute(function(resp){
         if (resp.code) {
            //code
            alert("Error de conexi—n, no se ha podido descargar Proyectos, volviendo a intentar. :::" + resp.message );
         }else{
            Usuarioitems = resp;
         }
         
      });//list
   },ROOT); //Load vf*/
   //Usuario
   tx.executeSql('DROP TABLE IF EXISTS Usuario');
   tx.executeSql('CREATE TABLE IF NOT EXISTS Usuario(idUsuario unique, idPropietario, idPersona, Usuario, Password,'+
                 ' Nombre, APaterno, AMaterno, FechaNac,'+
                 ' RFC, Titulo, Cargo, Cedula, Fotografia, Skype,'+
                 ' Direccion, Telefonos, Radios, Emails)');
   
   tx.executeSql('INSERT INTO Usuario(idUsuario, idPropietario, idPersona, Usuario, Password,'+
                  ' Nombre, APaterno, AMaterno, FechaNac,'+
                  ' RFC, Titulo, Cargo, Cedula, Fotografia, Skype, '+
                  ' Direccion, Telefonos, Radios, Emails)' +
                  'VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)',
                  [1, 1, 1,'NA', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA']);
}

function errorCBU(err) {
   console.log("Error processing SQL: "+err.code + " Message: "+err.message);
}

//Tablas creadas exitosamente
function successTablasU() {
   console.log("Se ha creado la tabla usuario");
   //Insertar datos
}
var noproyectos = 3;
function login(){
   //code
   usuario = document.getElementById("txtusername").value;
   password = document.getElementById("txtpassword").value;
   var secret = "rkY_MKxASXvD959Y6YUyq6Bh";
   var password_2;
   usuario = usuario.trim();
   password = password.trim();
   /*
    *aqui va la funcion de la encriptacion
    */
   var passencriptada;
   password_2 = secret + password;
   passencriptada = CryptoJS.MD5(password_2);
   console.log("contrase–a antes de encriptar: " + secret + password);
   //------------------------------------------------------------------------------------------------------------------
   db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
   db.transaction(function(tx){
      
   });
   //------------------------------------------------------------------------------------------------------------------
   if (usuario == "" || password == "") {
      //code
      alert("usuario y/o password vacio, ingrese datos");
   }else{
      console.log(" Usuario="+usuario+" Pass="+password);
      console.log(" Usuario="+usuario+" Secret="+secret);
      //console.log(" Usuario="+usuario+" Pass="+passencriptada);
      console.log(" Usuario="+usuario+" Pass encriptada=" +password_2 );
      db.transaction(function(tx) {
         tx.executeSql('SELECT * FROM Usuario',[],  function(tx, rs) {
            console.log("Usuario en la bse de datos: " + rs.rows.length +" Usuario:"+rs.rows.item(0).Usuario);
            if (rs.rows.length>0) {
               //code
               usuarioBD = rs.rows.item(0);
            }
         }, function(){console.log("Error en la consulta de usuarios");});
            //funcion de los logos
            //logos();
      }, function(){console.log("Error en la consulta de usuarios");},
      function (){
         console.log("Consulta usuario OK");
         //logos();
         //if (usuarioBD && usuario == usuarioBD.Usuario && password == usuarioBD.Password) {
         if (usuarioBD && usuario == usuarioBD.Usuario && passencriptada == usuarioBD.Password) {
            //code
            //logos();
            
            console.log(" Usuario registrado en la base de datos");
            //location.href="cargar.html?idDirectivo="+usuarioBD.idPropietario;
            location.href="cargar.html?idDirectivo="+usuarioBD.idPropietario +"&No_proyectos="+noproyectos;
            
            //logos(noproyectos);
            //logos_2();
         }else{
            console.log(" Usuario no registrado en la base de datos, vamos a cargar endpoint");
            // alert("No tenemos registro de sus datos, para hacer la descarga es necesario estar conectado a Internet");
            if (confirm("No tenemos registro de sus datos, para hacer la descarga es necesario estar conectado a Internet") == true) {
               console.log( "You pressed OK!" );
               //consultarEndPointUsuario(usuario, password );
               consultarEndPointUsuario(usuario, passencriptada );
            } else {
               console.log( "You pressed Cancel!" );
            }
         }
      });
/*
          //Consultar EndPoint Usuario
           gapi.client.load('usuarioendpoint', 'v1', function(){ 
               gapi.client.usuarioendpoint.getUsuario({"id": "0", "user": usuario, "contrasena": password}).execute(function(resp){
                  if(resp.code && !resp) {
                      console.log("Error de conexi—n, no se ha podido descargar Usuarios, volviendo a intentar. :::" + resp.message );
                      alert("usuario y/o password incorrecto");
                      //endpoints();
                      }else if(resp.id_Tipo_Persona){
                          console.log(" Usuario encontrado " + resp);
                          console.log(" Usuario: " + resp.id_Tipo_Persona);
                          switch (resp.id_Tipo_Persona) {
                              //case
                              case '1':
                                   console.log("Es un usuario administrador");
                                   alert("Solo usuarios directivos pueden acceder a la aplicaci—n");
                                   break;
                              case '2':
                                    console.log("Es un usuario directivo");
                                    datosUsuario = resp;
                                    insertarUsuario();
                                   break;
                              case '3':
                                    console.log("Es un usuario supervisor");
                                     alert("Solo usuarios directivos pueden acceder a la aplicaci—n");
                                   break;
                              default:
                                    console.log("Es un usuario X");
                                     alert("Solo usuarios directivos pueden acceder a la aplicaci—n");
                                   break;
                          }
                          
                          
                      }else  alert("usuario y/o password incorrecto");
               });//list
           },ROOT); //Load vf
    */
          }
     }
     
     
     function consultarEndPointUsuario(usuario, password ){
        console.log(" Usuario="+usuario+" Pass="+password);
          //Consultar EndPoint Usuario
          //communicationchannel
           //gapi.client.load('usuarioendpoint', 'v1', function(){
            gapi.client.load('communicationchannel', 'v1', function(){ 
               gapi.client.communicationchannel.getUsuario({"id": "0", "user": usuario, "contrasena": password,}).execute(function(resp){
               //gapi.client.usuarioendpoint.getUsuario({"id": "0", "user": usuario, "contrasena": password,}).execute(function(resp){
                  if(resp.code && !resp) {
                      console.log("Error de conexi—n, no se ha podido descargar Usuarios, volviendo a intentar. :::" + resp.message );
                      alert("usuario y/o password incorrecto");
                      //endpoints();
                      }else if(resp.id_Tipo_Persona){
                          console.log(" Usuario encontrado " + resp);
                          console.log(" Usuario tipo persona: " + resp.id_Tipo_Persona);
                          switch (resp.id_Tipo_Persona) {
                              //case 
                              case '1':
                                   console.log("Es un usuario administrador");
                                   alert("Solo usuarios directivos pueden acceder a la aplicaci—n");
                                   break;
                              case '2':
                                    console.log("Es un usuario directivo");
                                    datosUsuario = resp;
                                    insertarUsuario();
                                   break;
                              case '3':
                                    console.log("Es un usuario supervisor");
                                     alert("Solo usuarios directivos pueden acceder a la aplicaci—n");
                                   break;
                              default:
                                    console.log("Es un usuario X");
                                     alert("Solo usuarios directivos pueden acceder a la aplicaci—n");
                                   break;
                          }
                          
                          
                      }else  alert("usuario y/o password incorrecto");
               });//list
           },ROOT); //Load vf
  
     }
     
     
       function insertarUsuario() {
         
         //Consultar EndPoint Usuario
           gapi.client.load('communicationchannel', 'v1', function(){
           //gapi.client.load('personaendpoint', 'v1', function(){ 
               gapi.client.communicationchannel.getPersona({"id": datosUsuario.id_Persona,}).execute(function(resp){
                  //gapi.client.personaendpoint.getPersona({"id": datosUsuario.id_Persona,}).execute(function(resp){
                  if(resp.code && !resp) {
                      console.log("Error de conexi—n, no se ha podido descargar Usuarios, volviendo a intentar. :::" + resp.message );
                      }else{
                        
                        /*     "id_Persona": "1",
   "id_Tipo_Persona": "4",
   "direccion": "Viana No. 983",
   "nombre": "Carlos",
   "ap_Paterno": "Morales",
   "ap_Materno": "Palacios",
   "fecha_Nacimiento": "29/09/2014",
   "telefonos": "983479832",
   "radios": "esto no se que es",
   "emails": "carlos.mp@gmail.com",   */
    
      console.log("Se ha creado la tabla usuario");
       //Insertar datos
    idUsuario = 1;
    idPropietario = datosUsuario.id_Propietario;
    idPersona = datosUsuario.id_Persona;
    Usuario = datosUsuario.usuario;
    
    Password = datosUsuario.contrasena;
    Nombre = resp.nombre;
    APaterno = resp.ap_Paterno;
    AMaterno = resp.ap_Materno;
    FechaNac = resp.fecha_Nacimiento;
    RFC = resp.rfc;
    Titulo = resp.tituloProfesional;
    Cargo = resp.cargo;
    Cedula = "resp";
    Fotografia = resp.fotografia;
    Skype = "resp";
    Direccion = resp.direccion;
    Telefonos = resp.telefonos;
    Radios = resp.radios;
    Emails = resp.emails;
   db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
             db.transaction(function(tx) {
                       console.log("Insertando usuario :::" );
                //UPDATE AlertasProyectos SET LimiteDesvio = ? WHERE LimiteDesvio = 0 AND Tipo = 2   
                 tx.executeSql('UPDATE Usuario SET idUsuario=?, idPropietario=?, idPersona=?, Usuario=?, Password=?,'+
                                     ' Nombre=?, APaterno=?, AMaterno=?, FechaNac=?,'+
                                     ' RFC=?, Titulo=?, Cargo=?, Cedula=?, Fotografia=?, Skype=?, '+
                                     ' Direccion=?, Telefonos=?, Radios=?, Emails=?' +
                               ' WHERE idUsuario = 1 ',
                                [idUsuario, idPropietario, idPersona, Usuario, Password, Nombre, APaterno, AMaterno, FechaNac, RFC, Titulo, Cargo, Cedula, Fotografia, Skype, Direccion, Telefonos, Radios, Emails]);
                 
            }, function(){console.log("Error en la consulta de personas");}, function (){
                console.log("Se inserto correctamente el usuario: " + Usuario);
                console.log("id de persona:" + idPropietario);
                location.href="cargar.html?idDirectivo="+datosUsuario.id_Propietario;
                //location.href="cargar.html?idDirectivo="+idPropietario +"&No_proyectos="+noproyectos;
                logos(noproyectos);
                logos_2();
            }); 
                        
                        
                      }//else
                
               });//list
           },ROOT); //Load vf
   
     }