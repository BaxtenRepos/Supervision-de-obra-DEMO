/**
 * 
 */


function init(){
	
	
//	var loc = document.location.href;
//	var getString = loc.split('?')[1];
//	var GET = getString.split('&');
//	var get = {};// this object will be filled with the key-value pairs and
//					// returned.
//
//	for (var i = 0, l = GET.length; i < l; i++) {
//		var tmp = GET[i].split('=');
//		tipoArchivo = tmp[1];
//		get[tmp[0]] = unescape(decodeURI(tmp[1]));
//		
// 	}
	document.getElementById("tiporeferencia").value = 6;

 $("input[type=file]").filestyle({ 
     image: "/assets/img/conagua.jpg",
     imageheight : 22,
     imagewidth : 82,
     width : 2500
 });
 
}
 
