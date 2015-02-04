
var ROOT = 'https://' + 'focal-furnace-615.appspot.com' + '/_ah/api';
    var datosUsuario;
    var usuarioBD;

    
    if (window.location.hostname == 'localhost'
    || window.location.hostname == '127.0.0.1'
    || ((window.location.port != "") && (window.location.port > 999))) {
    // We're probably running against the DevAppServer
    ROOT = 'http://' + 'focal-furnace-615.appspot.com' + '/_ah/api';
    }
            
        
            function initializeUsuario(){
              
               console.log("Creando Base initializeUsuario::::::::::::::::::: ");
                                         db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
                                         db.transaction(guardarUsuario, errorCBU, successTablasU);
            }
           
        
            function guardarUsuario(tx){
                   //Usuario
                    tx.executeSql('DROP TABLE IF EXISTS Usuario');
                    tx.executeSql('CREATE TABLE IF NOT EXISTS Usuario(idUsuario unique, idPropietario, Usuario, Password,'+
                                     ' Nombre, APaterno, AMaterno, FechaNac,'+
                                     ' RFC, Titulo, Cargo, Cedula, Fotografia, Skype,'+
                                     ' Direccion, Telefonos, Radios, Emails)');
                    
                     tx.executeSql('INSERT INTO Usuario(idUsuario, idPropietario, Usuario, Password,'+
                                     ' Nombre, APaterno, AMaterno, FechaNac,'+
                                     ' RFC, Titulo, Cargo, Cedula, Fotografia, Skype, '+
                                     ' Direccion, Telefonos, Radios, Emails)' +
                               'VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)',
                                [1, 1, 'NA', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA', 'NA']);
                 
       
             }
     
     
             function errorCBU(err) {
                     console.log("Error processing SQL: "+err.code + " Message: "+err.message);
              }

             //Tablas creadas exitosamente
              function successTablasU() {
    
                console.log("Se ha creado la tabla usuario");
               //Insertar datos
               
    
              }
              
              
              function login(){
           //code
             usuario = document.getElementById("txtusername").value;
             password = document.getElementById("txtpassword").value;
             
             usuario = usuario.trim();
             password = password.trim();
            
          
          if (usuario == "" || password == "") {
               //code
                alert("usuario y/o password vacio, ingrese datos");
          }else{
          
        console.log(" Usuario="+usuario+" Pass="+password);
        
           db.transaction(function(tx) {
             
              tx.executeSql('SELECT * FROM Usuario',[],  function(tx, rs) {
                console.log("Usuario en la bse de datos: " + rs.rows.length +" Usuario:"+rs.rows.item(0).Usuario);
                      if (rs.rows.length>0) {
                        //code
                         usuarioBD = rs.rows.item(0);
                      }
                     
              }, errorCB);
               
          }, errorCB, function (){
                console.log("Consulta usuario OK");
                
                if (usuarioBD && usuario == usuarioBD.Usuario && password == usuarioBD.Password) {
                    //code
                    console.log(" Usuario registrado en la base de datos");
                    location.href="cargar.html?idDirectivo="+usuarioBD.idPropietario;
                }else{
                    console.log(" Usuario no registrado en la base de datos, vamos a cargar endpoint");
                   // alert("No tenemos registro de sus datos, para hacer la descarga es necesario estar conectado a Internet");
                   
                      if (confirm("No tenemos registro de sus datos, para hacer la descarga es necesario estar conectado a Internet") == true) {
                                      console.log( "You pressed OK!" );
                                         consultarEndPointUsuario(usuario, password );
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
           gapi.client.load('usuarioendpoint', 'v1', function(){ 
               gapi.client.usuarioendpoint.getUsuario({"id": "0", "user": usuario, "contrasena": password}).execute(function(resp){
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
           gapi.client.load('personaendpoint', 'v1', function(){ 
               gapi.client.personaendpoint.getPersona({"id": datosUsuario.id_Persona,}).execute(function(resp){
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
                 tx.executeSql('UPDATE Usuario SET idUsuario=?, idPropietario=?, Usuario=?, Password=?,'+
                                     ' Nombre=?, APaterno=?, AMaterno=?, FechaNac=?,'+
                                     ' RFC=?, Titulo=?, Cargo=?, Cedula=?, Fotografia=?, Skype=?, '+
                                     ' Direccion=?, Telefonos=?, Radios=?, Emails=?' +
                               ' WHERE idUsuario = 1 ',
                                [idUsuario, idPropietario, Usuario, Password, Nombre, APaterno, AMaterno, FechaNac, RFC, Titulo, Cargo, Cedula, Fotografia, Skype, Direccion, Telefonos, Radios, Emails]);
                 
            }, errorCB, function (){
                console.log("Se inserto correctamente el usuario: " + Usuario);
                location.href="cargar.html?idDirectivo="+datosUsuario.id_Propietario;
            }); 
                        
                        
                      }//else
                
               });//list
           },ROOT); //Load vf
   
     }