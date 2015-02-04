var ROOT = 'https://' + '1-dot-iuyet-csgit-cao.appspot.com' + '/_ah/api';
var datosUsuario;
var usuarioBD;
if (window.location.hostname == 'localhost'
   || window.location.hostname == '127.0.0.1'
   || ((window.location.port != "") && (window.location.port > 999))) {
   ROOT = 'https://' + '1-dot-iuyet-csgit-cao.appspot.com' + '/_ah/api';
}
function proyectos() {
   //code
   db.transaction(function(tx) {
      tx.executeSql('SELECT * FROM  Proyectos',[],  function(tx, rs) {
         console.log("proyectos: " + rs.rows.length);
         for (var i = 0; i < rs.rows.length; i++) {
            //code
            proyectos = rs.rows.item(i);
         }
         console.log("proyecto id: " + proyectos.idProyecto);
         location.href="mapa_proyectos.html?idProyecto="+proyectos.idProyecto;
      }, errorCB);
   }, errorCB, successConsulta);
}

function initializeUsuario(){
   //checa si tiene
   console.log("se inicia la descarga");
   db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
   db.transaction(guardarUsuario, errorCBU, successTablasU);
   //console.log("entra a la funcion");
   /*if(checkConnection() != "No network connection" && checkConnection() != "Unknown connection"){
   }else{
      console.log("Sin salida a internet, datos no actualizados");
      db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
      db.transaction(function(tx) {
         tx.executeSql('SELECT * FROM  Usuario',[],  function(tx, rs) {
            console.log("proyectos: " + rs.rows.length);
            u = rs.rows.item(0);
            console.log("usuario de la base de datos: " + u.Usuario);
            console.log("Contrase–a del usuario: " + u.Password);
            //funcion que se pasa los 2 parametros;
            login_2(u.Usuario, u.Password);
            //location.href="lista_proyectos.html?idProyecto="+proyectos.idProyecto;
          }, errorCB);
      }, errorCB, successConsulta);
      //location.href="lista_proyectos.html";
   }*/
   
}
function  successConsulta () {
   //code
   console.log("se hizo existosamente la consulta");
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

function guardarUsuario(tx){
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
function errorCB(err) {
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
   var passencriptada;
   usuario = usuario.trim();
   password = password.trim();
   password_2 = secret + password;
   passencriptada = CryptoJS.MD5(password_2);
   //----------------------------------------------------------------------------------------------------------------------------------
   if (usuario == "" || password == "") {
      //code
      alert("usuario y/o password vacio, ingrese datos");
   }else{
      if(checkConnection() != "No network connection" && checkConnection() != "Unknown connection"){
         alert("con salida a internet");
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
         }, function(){console.log("Error en la consulta de usuarios");},
         function (){
            console.log("Consulta usuario OK");
            if (usuarioBD && usuario == usuarioBD.Usuario && passencriptada == usuarioBD.Password) {
               console.log(" Usuario registrado en la base de datos");
               location.href="cargar.html?idDirectivo="+usuarioBD.idPropietario;
            }else{
               console.log(" Usuario no registrado en la base de datos, vamos a cargar endpoint");
               if (confirm("No tenemos registro de sus datos, para hacer la descarga es necesario estar conectado a Internet") == true) {
                  console.log( "You pressed OK!" );
                  consultarEndPointUsuario(usuario, passencriptada );
               } else {
                  console.log( "You pressed Cancel!" );
               }
            }
         });
      }else{
         console.log("Sin salida a internet, datos no actualizados");
         db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
            db.transaction(function(tx) {
            tx.executeSql('SELECT * FROM  Usuario',[],  function(tx, rs) {
               console.log("proyectos: " + rs.rows.length);
               u = rs.rows.item(0);
               console.log("usuario de la base de datos: " + u.Usuario);
               console.log("Contrase–a del usuario: " + u.Password);
               if (usuario == u.Usuario && password == u.Password) {
                  //code
                  console.log("entra al sistema");
               }else{
                  alert("el usuario no existe");
               }
            //funcion que se pasa los 2 parametros;
            //login_2(u.Usuario, u.Password);
            //location.href="lista_proyectos.html?idProyecto="+proyectos.idProyecto;
            }, errorCB);
         }, errorCB, successConsulta);
      //location.href="lista_proyectos.html";
      }
   }
   //----------------------------------------------------------------------------------------------------------------------------------
   console.log("contrase–a antes de encriptar: " + secret + password);
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
            };
         }else{
            alert("usuario y/o password incorrecto");
         }
      });//list
   },ROOT); //end endpoint
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
            console.log("Se ha creado la tabla usuario");
            idUsuario = 1;
            idPropietario = datosUsuario.id_Propietario;
            idPersona = datosUsuario.id_Persona;
            Usuario = datosUsuario.usuario;
            Password = datosUsuario.contrasena;
            Nombre = resp.nombre;
            APaterno = resp.ap_Paterno;
            AMaterno = resp.ap_Materno;
            FechaNac = resp.fecha_Nacimiento;
            RFC = "resp";
            Titulo = "resp";
            Cargo = "resp";
            Cedula = "resp";
            Fotografia = "resp";
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
                 
            }, function(){console.log("Error en la consulta de personas");},
            function (){
                console.log("Se inserto correctamente el usuario: " + Usuario);
                console.log("id de persona:" + idPropietario);
                location.href="cargar.html?idDirectivo="+datosUsuario.id_Propietario;
                //location.href="cargar.html?idDirectivo="+idPropietario +"&No_proyectos="+noproyectos;
                //logos(noproyectos);
                //logos_2();
            }); 
         }//else
      });//list
   },ROOT); //Load vf
}
function login_2(p_usuario, p_pass){
   alert("usuario: " + p_usuario);
   alert("contrase–a: " + p_pass);
   //--------------------------------------------------------------------------------------------------------------------------------------------
   /*var usuario = document.getElementById("txtusername").value;
   var password = document.getElementById("txtpassword").value;
   var secret = "rkY_MKxASXvD959Y6YUyq6Bh";
   var passencriptada, password_2;
   usuario = usuario.trim();
   password = password.trim();
   password_2 = secret + password;
   passencriptada = CryptoJS.MD5(password_2);
   console.log("contrase–a antes de encriptar: " + secret + password);
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
      }, function(){console.log("Error en la consulta de usuarios");},
      function (){
         console.log("Consulta usuario OK");
         if (usuarioBD && usuario == usuarioBD.Usuario && passencriptada == usuarioBD.Password) {
            console.log(" Usuario registrado en la base de datos");
            location.href="cargar.html?idDirectivo="+usuarioBD.idPropietario;
         }else{
            console.log(" Usuario no registrado en la base de datos, vamos a cargar endpoint");
            if (confirm("No tenemos registro de sus datos, para hacer la descarga es necesario estar conectado a Internet") == true) {
               console.log( "You pressed OK!" );
               consultarEndPointUsuario(usuario, passencriptada );
            } else {
               console.log( "You pressed Cancel!" );
            }
         }
      });
   }*/
   //--------------------------------------------------------------------------------------------------------------------------------------------
}