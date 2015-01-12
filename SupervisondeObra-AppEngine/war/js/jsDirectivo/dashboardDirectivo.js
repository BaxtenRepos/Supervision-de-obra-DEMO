/**
 * 
 */
var idDirectivo;
function init(){
	var loc = document.location.href;
	var getString = loc.split('?')[1];
	var GET = getString.split('&');
	var get = {};// this object will be filled with the key-value pairs and
					// returned.

	for (var i = 0, l = GET.length; i < l; i++) {
		var tmp = GET[i].split('=');
	   idDirectivo = tmp[1];
		get[tmp[0]] = unescape(decodeURI(tmp[1]));
	}

	$.ajax({
		url : '/proyectosdirectivo',
		type : 'post',
		data : {'objectJson' : JSON.stringify('directivo-'+idDirectivo+'-')},
		success: function(data){
			if(data.length>1){
				
			}
			puntos=data;
			pintapuntos();
			
		},
		error: function(jHR,e,throwsError){
			alert(e);
		}

		
			
});
	
}
function iniciadirectorio(){}