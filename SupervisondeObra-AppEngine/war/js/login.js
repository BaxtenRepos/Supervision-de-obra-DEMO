/**
 * 
 */
var aToken;
function valida(){
	var usuario = document.getElementById("user").value.trim();
	var password = document.getElementById("password").value.trim();
	var peticion = 'peticion';
	var object = {
			'user' :usuario,
			'password':password,
		};
		$.ajax({
			url : '/login',
			type : 'post',
			data : {'objectJson' : JSON.stringify(object)},
			success: function(data){
				
				if (!isNaN(data)) 
					location.href="/views/PruebaProyecto.html";
					
				//if(data=="ok")
				else alert(data);
			

			},
			error: function(jHR,e,throwsError){
		alert('error');
			}					
	});
	
}

function signinCallback(authResult) 
{
  if (authResult) 
  {
	  console.log('Valor= '+authResult['error']);
  	if (authResult['error'] == undefined) //if (authResult['access_token'])
  	{
  		//confirm("Token recuperado");
  		console.log('Token recuperado');
  		console.log('Valor= '+authResult['error']);
  		//console.log(authResult);
        gapi.auth.setToken(authResult); // Guarda el token recuperado.
        aToken = authResult['access_token']
        getEmail();
    }
  	
  	if (authResult['error'] == 'user_signed_out') //if (authResult['access_token'])
  	{
  		//alert("user_signed_out");
  		console.log('authResult= user_signed_out');
  	}	
  	
  	else 
    {
  		//confirm("Intentalo nuevamente");
	   	console.log('Intentalo nuevamente');
    }
  } 
  else
  {
	  //alert("Empty authResult");
	  console.log('Empty authResult');  // Se ha producido algun error
  }
}

function getEmail()
{
	gapi.client.load('oauth2', 'v2', function()
	{
		var request = gapi.client.oauth2.userinfo.get();
		request.execute(getEmailCallback);
	});
}

function redireccionar(obj) 
{
	//location.href="https://www.google.com.mx/"
	//location.href="PruebaProyecto.html"
	var peticion = 'loginPlus';
	var object = {			
			'nombre' 		:obj['given_name'],
			'apellidos'		:obj['family_name'],
			'email'			:obj['email'],
			'fotografia'	:obj['picture']
		};

		$.ajax({
			url : '/login',
			type : 'post',
			data : {
				'objectJson' : JSON.stringify(peticion),
				'objectDatos' : JSON.stringify(object),
			},
			success: function(data){
				if(data=="ok")location.href="PruebaProyecto.html"
			},
			error: function(jHR,e,throwsError){
				alert('error loginPlus: '+e);
			}		
	});	
} 

function getEmailCallback(obj)
{
	console.log('getEmailCallback');
	var el = document.getElementById('signinButton');
    //console.log(obj);   // Sin comentario para inspeccionar el objeto completo.
    //el.innerHTML = email;
    //toggleElement('signinButton');
    
    /*if (obj['email']) 
    {
    	email = 'Email: ' + obj['email'];
    	//alert("Email: "+email);
    }*/
    if (obj['hd']=="adquem.com"||obj['hd']=="gmail.com")
    {
    	//confirm("DOMINIO CSGIT");
    	console.log('DOMINIO ADQUEM');
    	redireccionar(obj);
    }
    else
    {
    	confirm("Se requiere cuenta del dominio CSGIT");
    	disconnectUser(aToken);
    	aToken=null;
    	//crear liga logout
    	/*
    	 * UserService userService = UserServiceFactory.getUserService();
    	 * User user = userService.getCurrentUser();
    	 * resp.setContentType("text/html");
    	 * resp.getWriter().println("<a href='"+ userService.createLogoutURL(req.getRequestURI())+"'>  LogOut </a>");
    	 * */
    } 
}

function toggleElement(id) 
{
	  console.log("toggleElement "+id);
	  var el = document.getElementById(id);
	  if (el.getAttribute('class') == 'hide') 
	  {
		  //alert("is hided "+id);
		  el.setAttribute('class', 'show');
	  }
	  else 
	  {
		  //alert("is showed "+id);
	      el.setAttribute('class', 'hide');
	  }
} 

$(function(){
	
	$('#password').keyup(function (e) {
	    if (e.keyCode == 13) {
	        valida();
	    }
	});
});
function disconnectUser(access_token) 
{
	//alert("Elimina autorizaciondel usuario "+access_token);
	gapi.auth.signOut();
	//alert("Entra a signOut el usuario ");
	
	/*
	var revokeUrl = 'https://accounts.google.com/o/oauth2/revoke?token=' +access_token;
	
	// Realiza una solicitud GET asíncrona.
	$.ajax({
	    type: 'GET',
	    url: revokeUrl,
	    async: false,
	    contentType: "application/json",
	    dataType: 'jsonp',
	    success: function(nullResponse) {
	      //confirm("Usuario Desconectado");
	      location.href="https://mail.google.com/mail/u/0/?logout&hl=en"
	      //https://mail.google.com/mail/u/0/?logout&hl=en
	    },
	    error: function(e) {
	      console.log(e);
	      // https://plus.google.com/apps
	    }
	});*/
	
}

