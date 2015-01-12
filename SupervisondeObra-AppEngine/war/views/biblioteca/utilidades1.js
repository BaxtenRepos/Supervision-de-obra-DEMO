


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
      //code
     dato = document.getElementById("mail").value;
     location.href="email.html?pagina="+pagina+"&mail="+dato;
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

