/**
 * 
 */
function init(){
	 var loc = document.location.href;
     var getString = loc.split('?')[1];
     var GET = getString.split('&');
     var get = {};//this object will be filled with the key-value pairs and returned.
  
     for(var i = 0, l = GET.length; i < l; i++){
        var tmp = GET[i].split('=');
        get[tmp[0]] = unescape(decodeURI(tmp[1]));
     }

	
	$.ajax({
		url : '/cargar',
		type : 'post',
		data : {'objectJson' : JSON.stringify("proyectos:"+tmp[1]+":")},
		success: function(data){
			var x = document.getElementById("endpoint1");
			x.settext("hola");

		},
		error: function(jHR,e,throwsError){
			alert(e);
		}
			
});
}