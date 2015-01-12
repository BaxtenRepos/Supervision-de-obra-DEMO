
/* Validar usuario y contrase–a*/
function login()
{
     alert("login");
      //code
     usuario = document.getElementById("txtusername").value;
     password = document.getElementById("txtpassword").value;
     console.log("Acceso: " + usuario +" "+password);
     if (usuario == "Directivo" && password == "123") {
          //code
          location.href="mapa_proyectos.html";
     } else if (usuario == "Admin" && password == "admin123"){
         location.href="admin.html";
     }else{
           alert("usuario y/o password incorrecto");
     }
}


/*  Ir a la pagina de resultado de busquedas*/
function buscar(pagina)
{
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



/*  Obtener parametrso por GET*/
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
  db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
            db.transaction(function(tx) {
                tx.executeSql("SELECT * FROM Proyectos WHERE NombreLargo LIKE ?",[bq],  function(tx, rs) {
                  for (var i = 0; i < rs.rows.length; i++) {
    // for (var i = 0; i < 4; i++) {
                    var p = rs.rows.item(i);
                    document.getElementById("resultado").innerHTML  += 
                      "<li><a href=\"detalle_proyectos.html?idProyecto=1\">" +
                        "<div class=\"meta name\"> "  +
                          "<div class=\"img_wrapper\"> <img src=\"assets/img/others/acadia.jpg\"/></div>"  +
                            "<div class=\"titles\">"  +
                              "<h2> "+ p.NombreLargo+"</h2>"  +
                              "<p><em>" +p.NombreCorto+" </em></p>" +
                            "</div>"  +
                          "</div>"  +
                        "<div class=\"meta region\">" +
                          "<p> "+p.Dependencia+"</p>"  +
                        "</div>" +
                        "<div class=\"meta area\">" +
                          "<p>" +p.LimiteDesvio+"</p>"  +
                        "</div>"  +
                     "</a></li>" ;
                  }
                }, errorCB);
            }, errorCB, querySuccess);
}
function Obras(bq){
  db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
            db.transaction(function(tx) {
                tx.executeSql("SELECT * FROM Obra WHERE Nombre LIKE ?",[bq],  function(tx, rs) {
                  for (var i = 0; i < rs.rows.length; i++) {
                    document.getElementById("resultado").innerHTML  += 
                      "<li><a href=\"detalle_obras.html?idProyecto=1\">" +
                        "<div class=\"meta name\"> "  +
                          "<div class=\"img_wrapper\"> <img src=\"assets/img/others/acadia.jpg\"/></div>"  +
                            "<div class=\"titles\">"  +
                              "<h2> TITULO 1 </h2>"  +
                              "<p><em> titulo </em></p>" +
                            "</div>"  +
                          "</div>"  +
                        "<div class=\"meta region\">" +
                          "<p> Dependencia </p> "  +
                        "</div>" +
                        "<div class=\"meta area\">" +
                          "<p> Avance </p>"  +
                        "</div>"  +
                     "</a></li>" ;
                  }
                }, errorCB);
            }, errorCB, querySuccess);
}
function querySuccess(tx, results) {
   alert("Todo OK");
}


function errorCB(err) {
    alert("Error processing SQL: "+err.code + " Message: "+err.message);
}

 function cargar (args) {
	    //code
	   
      }
      
function descargaPDF() {
    alert("Vista Previa PDF");
   
	var ref = window.open('https://docs.google.com/a/csgit.com.mx/uc?authuser=0&id=0B0PbNiCm1xfQUDJhNDJVY2x5UDQ&export=download', '_blank', 'location=yes');
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

function headerUser(){
       
     var notificaciones = 2;
     var usuario = "Administrador";
  
     document.getElementById("headerUser").innerHTML = 
                                                    "<span class=\"badge badge-important\">"+notificaciones+"</span>" +
                                                    "<span class=\"bold\">"+"  "+usuario+"</span>";	
     
     document.getElementById("notificationsUser").innerHTML =
                                                                "<div class=\"notification-messages danger\">"+
								"<div class=\"iconholder\">"+
									"<i class=\"icon-warning-sign\"></i>"+
								"</div>"+
								"<div class=\"message-wrapper\">"+
									"<div class=\"heading\">"+
										"Proyecto CONAGUA"+
									"</div>"+
									"<div class=\"description\">"+
										"Desvio de 30%"+
									"</div>"+
									"<div class=\"date pull-left\">"+
									"6 Agosto 2014"+
									"</div>"+
								"</div>"+
								"<div class=\"clearfix\"></div>"+
							"</div>";
                                                        
     document.getElementById("pictureUser").innerHTML =                                                   
     
     "<img src=\"assets/img/profiles/avatar_small.png\"   data-src=\"assets/img/profiles/avatar_small.png\" data-src-retina=\"assets/img/profiles/avatar_small2x.png\" width=\"35\" height=\"35\" /> ";                                                   
                                                        
     document.getElementById("hiUser").innerHTML = "Bienvenido";                                                   
                                                        
     document.getElementById("pictureMenuUser").innerHTML = "<img src=\"assets/img/profiles/avatar.png\"   data-src=\"assets/img/profiles/avatar.png\" data-src-retina=\"assets/img/profiles/avatar2x.png\" width=\"69\" height=\"69\" />";                                                   
                                                        
     document.getElementById("menuUser").innerHTML =
     "<div class=\"username\" >Administrador </div>"+
     "<div class=\"status\">Status<a href=\"#\"><div class=\"status-icon green\"></div>Online</a></div><br>";
     
    
                                                        
   /* document.getElementById("menuUserOptions").innerHTML =                                                   
      
      "<li class=\"start\"><a href=\"javascript:;\"><span class=\"title\">Proyectos</span><span class=\"selected\"></span><span class=\"arrow open\"></span></a>"+
	 "<ul class=\"sub-menu\">"+
	         "<li > <a href=\"#\">  Lista de Proyectos </a> </li>"+
		 "<li > <a href=\"http://1-dot-focal-furnace-615.appspot.com/views/AltaProyectos.html\">  Alta de Proyectos </a> </li>"+
	         "<li > <a href=\"http://1-dot-focal-furnace-615.appspot.com/views/EditProyectos.jsp\"> Editar Proyectos </a> </li>"+
        "</ul>"+
     "</li>";
	    "<li class=\"\"> <a href=\"#\"> <span class=\"title\">Obras</span><span class=\"arrow \"></span></a>"+
	       "<ul class=\"sub-menu\">"+
		   "<li > <a href=\"#\"> Lista de Obras</a> </li>"+
		   "<li > <a href=\"http://1-dot-focal-furnace-615.appspot.com/views/AltaObras.jsp\"> Alta de Obras</a> </li>"+
		   "<li > <a href=\"http://1-dot-focal-furnace-615.appspot.com/views/EditObra.jsp\"> Editar Obras</a> </li>"+
               "</ul>"+
	  "</li>";
        "<li class=\"\"> <a href=\"#\">  <span class=\"title\">Conceptos</span><span class=\"arrow \"></span></a>"+
	   "<ul class=\"sub-menu\">"+
	           "<li > <a href=\"#\"> Lista de Conceptos</a> </li>"+
		   "<li > <a href=\"http://1-dot-focal-furnace-615.appspot.com/views/AltaConcepto.jsp\"> Alta de Conceptos</a> </li>"+
		   "<li > <a href=\"#\"> Editar Conceptos</a> </li>"+
               "</ul>"+
	  "</li>";
	  "<li class=""> <a href=\"#\">  <span class=\"title\">Empresas</span><span class=\"arrow \"></span></a>"+
	   "<ul class=\"sub-menu\">"+
	           "<li > <a href=\"#\"> Lista de Empresas</a> </li>"+
		   "<li > <a href=\"#\"> Alta de Empresas</a> </li>"+
		   "<li > <a href=\"#\"> Editar Empresas</a> </li>"+
               "</ul>"+
	  "</li>"+
	  "<li class=\"\"> <a href=\"#\">  <span class=\"title\">Usuarios</span><span class=\"arrow \"></span></a>"+
	   "<ul class=\"sub-menu\">"+
	           "<li > <a href=\"#\"> Lista de Usuarios</a> </li>"+
		   "<li > <a href=\"#\"> Alta de Usuarios</a> </li>"+
		   "<li > <a href=\"#\"> Editar Usuarios</a> </li>"+
               "</ul>"+
	  "</li>"+
	   "<li class=\"\"> <a href=\"#\">  <span class=\"title\">Cat&aacute;logos</span><span class=\"arrow \"></span></a>"+
	   "<ul class=\"sub-menu\">"+
	           "<li > <a href=\"#\"> Maquinaria y Equipo</a> </li>"+
		   "<li > <a href=\"#\"> Tipos de Empresas</a> </li>"+
		   "<li > <a href=\"#\"> Tipos de Usuarios</a> </li>"+
               "</ul>"+
	  "</li>"+
	   "<li class=\"\"> <a href=\"#\">  <span class=\"title\">Configuraci&oacute;n</span></a>"+
	   "<ul class=\"sub-menu\">"+
	           "<li > <a href=\"#\"> Configuraci&oacute;n</a> </li>"+
               "</ul>"+
	  "</li>"+
	    "<li class=\"\"> <a href=\"#\">  <span class=\"title\">Ayuda</span></a>"+
	   "<ul class=\"sub-menu\">"+
	           "<li > <a href=\"#\"> Ayuda</a> </li>"+
               "</ul>"+
	  "</li>"+
	     "<li class=\"\"> <a href=\"#\">  <span class=\"title\">Acerca de</span></a>"+
	   "<ul class=\"sub-menu\">"+
	           "<li > <a href=\"#\"> Acerca de  </a> </li>"+
               "</ul>"+
	  "</li>";
	
                                                
                                                        
                                                        
         */                                               
                                                        
                                                        
                                                        
                                                        
                                                        
                                                        
                                                        
                                                        
                                                        
                                                        
                                                        
                                                        
                                                        
}
