function init(){
    var get = getGET();
    var obra = get.idObra;
    var path = get.path;
    var proyecto = get.idProyecto;
    //alert("proyecto:" + proyecto);
    //function atras(){
        document.getElementById("returnObra").innerHTML =
        "<a href=\"galeria_3.html?idProyecto="+proyecto+"&&idObra="+obra+"\"><i class=\"icon-custom-left\"></i></a>";
    //}
    video(path);
}
function video(path) {
    //code
    console.log("path recibida:" + path);
    document.getElementById("video").innerHTML =
        /*"<iframe width=\"570\" height=\"315\"" +
            "src=\"https://www.youtube.com/watch?v=51iquRYKPbs\">"+
        "</iframe>";*/
    "<video width=\"570\" height=\"315\" controls>" +
	"<source src=\"https://1-dot-iuyet-csgit-cao.appspot.com/serve?blob-key="+path+"\" webkit-playsinline preload=\"auto\" type=\"video/mp4\">"+ // ,mime type=\"video/mp4\" y video/mpeg, (H.264/AAC), video/3gp ///webkit-playsinline preload=\"auto\"
    "</video>";
}