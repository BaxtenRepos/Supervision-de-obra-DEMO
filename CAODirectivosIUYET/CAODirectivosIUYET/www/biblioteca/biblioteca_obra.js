//var ROOT = 'https://' + '1-dot-iuyet-csgit-cao.appspot.com' + '/_ah/api';//desarrollo
var ROOT = 'https://' + '1-dot-superobra-adquem.appspot.com' + '/_ah/api';//adquem
//var ROOT = 'https://' + '1-dot-cao-iuyet-pruebas.appspot.com' + '/_ah/api';//pruebas
var ruta = '1-dot-superobra-adquem.appspot.com';//adquem
function init(){
    var get = getGET();
    //getIdDirectivo();
    var idobra = get.idObra;

    console.log("id del obra" + idobra);
    //getIdDirectivo();
    document.getElementById("returnObra").innerHTML =
    "<a href=\"detalle_obras_reporte.html?idProyecto="+get.idProyecto+"&&idObra="+idobra+"\"><i class=\"icon-custom-left\"></i></a>";
    //endpoint(idobra);
    gapi.client.load('communicationchannel', 'v1', function () {
	gapi.client.communicationchannel.listMultimedia({"tipoReferencia": "1","idReferencia":String(idobra),"tipoArchivo":"2",}).execute(function (resp) { //alert("2");
	    if (resp.code){
		alert("No hay imagenes de la obra :::");
            }else{
                DocumentosItems = resp.items;
	        if (DocumentosItems) {
			//code
        	    console.log("Documentos del proyecto: " + DocumentosItems.length);
			//verimagenes(MultimediaItems, fecha01, fecha02);
                    verdocumentos(DocumentosItems);
	        }else{
			console.log("no existen documentos");
			document.getElementById("documentos").innerHTML =
			    "<div class=\"superbox-list\">"+
				"No hay Documentos de la Obra que mostrar."+
			    "</div>";
		}
	    }
        });
    }, ROOT);
  getIdDirectivo();
}
function endpoint(obra) {
    //code
    gapi.client.load('communicationchannel', 'v1', function () {
	gapi.client.communicationchannel.listMultimedia({"tipoReferencia": "1","idReferencia":String(idobra),"tipoArchivo":"2",}).execute(function (resp) { //alert("2");
	    if (resp.code){
		alert("No hay imagenes de la obra :::");
            }else{
                DocumentosItems = resp.items;
	        if (DocumentosItems) {
			//code
        	    console.log("Documentos del proyecto: " + DocumentosItems.length);
			//verimagenes(MultimediaItems, fecha01, fecha02);
                    verdocumentos(DocumentosItems);
	        }else{
			console.log("no existen documentos");
			/*document.getElementById("galeria").innerHTML =
			    "<div class=\"superbox-list\">"+
				"No hay Imagenes y Videos que mostrar."+
			    "</div>";*/
		}
	    }
        });
    }, ROOT);
}
function verdocumentos(DocumentosItems) {
    //code
    var contador = 0;
    //ref = window.open('http://1-dot-iuyet-csgit-cao.appspot.com/serve?blob-key=\"+path+"&export=download', '_blank', 'location=yes');
    for (var i = 0; i < DocumentosItems.length; i++) {
        //code
        switch (DocumentosItems[i].formato) {
            //case
            case '5':
                document.getElementById("documentos").innerHTML +=
		    "<div class=\"superbox-list\" id=\"img\">"+
			"<img src= \"iconos_png/pdf.png\" width=\"100\" height=\"100\"></a>" +
			"<span>"+DocumentosItems[i].descripcion+"</span><br>" +
                        "<input type=\"button\" name=\"boton\" value=\"Ver\" class=\"btn btn-block btn-white\" onclick=\"ver('"+DocumentosItems[i].path+"')\"/>"+
		    "<div>";
                break;
            case '6':
                document.getElementById("documentos").innerHTML +=
		    "<div class=\"superbox-list\" id=\"img\">"+
			"<img src= \"iconos_png/excel_alt_1.png\" width=\"100\" height=\"100\"></a>" +
			"<span>"+DocumentosItems[i].descripcion+"</span><br>" +
                        "<input type=\"button\" name=\"boton\" value=\"Ver\" class=\"btn btn-block btn-white\" onclick=\"ver('"+DocumentosItems[i].path+"')\"/>"+
		    "<div>";
                break;
            case '7':
                document.getElementById("documentos").innerHTML +=
		    "<div class=\"superbox-list\" id=\"img\">"+
			"<img src= \"iconos_png/word_alt_1.png\" width=\"100\" height=\"100\"></a>" +
			"<span>"+DocumentosItems[i].descripcion+"</span><br>" +
                        "<input type=\"button\" name=\"boton\" value=\"Ver\" class=\"btn btn-block btn-white\" onclick=\"ver('"+DocumentosItems[i].path+"')\"/>"+
		    "<div>";
                break;
            case '8':
                document.getElementById("documentos").innerHTML +=
		    "<div class=\"superbox-list\" id=\"img\">"+
			"<img src= \"iconos_png/powerpoint_alt_1.png\" width=\"100\" height=\"100\"></a>" +
			"<span>"+DocumentosItems[i].descripcion+"</span><br>" +
                        "<input type=\"button\" name=\"boton\" value=\"Ver\" class=\"btn btn-block btn-white\" onclick=\"ver('"+DocumentosItems[i].path+"')\"/>"+
		    "<div>";
                break;
            default:
                console.log("otros");
                break;
        }
    }
}
function ver(path) {
    var ref = window.open('http://'+ruta+'/serve?blob-key='+path+'&export=download', '_blank', 'location=yes');
    //var ref = window.open('http://1-dot-iuyet-csgit-cao.appspot.com/serve?blob-key='+path+'&export=download', '_blank', 'location=yes');//desarrollo
    //var ref = window.open('http://1-dot-superobra-adquem.appspot.com/serve?blob-key='+path+'&export=download', '_blank', 'location=yes');//adquem
    ref.addEventListener('loadstart', function(event) { /*alert('start: ' + event.url); */});
    ref.addEventListener('loadstop', function(event) { /*alert('stop: ' + event.url); */});
    ref.addEventListener('loaderror', function(event) { /*alert('error: ' + event.message); */});
    ref.addEventListener('exit', function(event) { /*alert(event.type); */});
   
}