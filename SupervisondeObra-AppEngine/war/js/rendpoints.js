var ROOT = 'https://' + '1-dot-iuyet-csgit-cao.appspot.com' + '/_ah/api';

if (window.location.hostname == 'localhost'
    || window.location.hostname == '127.0.0.1'
    || ((window.location.port != "") && (window.location.port > 999))) {
    ROOT = 'http://' + '1-dot-iuyet-csgit-cao.appspot.com' + '/_ah/api';
}
var i = 0;
function init(){
    enpoints();

}
//Funcion que obtiene el numero de proyectos por directivo
function enpoints() {
    var directivo = getGET().idDirectivo;
    //code
    gapi.client.load('communicationchannel', 'v1', function(){
        gapi.client.communicationchannel.listDirectivo_Proyecto({"directivo":directivo}).execute(function(resp){
            if (resp.code) {
                //code
            }else{
                ProyectoItems = resp.items;
                console.log("Proyectos Directivo: " + ProyectoItems.length);
            }
        });
    },ROOT);
}
//Funcion para obtener el Proyecto
function get_proyecto(id_proyecto) {
    
    gapi.client.communicationchannel.getProyecto({"id":function get_proyecto(id_proyecto) {
}).execute(function(resp){
        if (resp.code) {
        }else{
            ProyectoItems = resp.items;
            obras(ProyectoItems[i].id_Proyecto);
        }
    });
}
function obras(id_proyecto) {
    //code
    gapi.client.communicationchannel.listObra({"idProyecto": id_proyecto, "idObra":"0", "email":" "}).execute(function(resp){
        if (resp.code) {
            //code
        }else{
            if (i < ObrasItems.length) {
                //code
                ObrasItems = resp.items;
                console.log("Obras: " + ObrasItems.length);
                reporte_diario(ObrasItems[i].id_Obra);
            }
        }
    });
}
function reporte_diario(id_obra) {
    //code
    gapi.client.communicationchannel.listReporteDiario({"idObra":id_obra, "email":" "}).execute(function(resp){
        if (resp.code) {
            alert("Error de conexi—n, no se ha podido descargar Reportes Diarios, volviendo a intentar. :::" + resp.message );
        }else{
            ReportesItems = resp.items;
            if (ReportesItems) {
                if (i < ReportesItems.length) {
                    document.getElementById("endpoint12").innerHTML= "Reporte diario: <img src=\" biblioteca/Imagenes/check.png\" width=\"20px\" heigth=\"20px\">";
                    reporte_maquinaria(ReportesItems[i].id_ReporteDiario);
                }
            }else{
                console.log("No hay reportes diarios");
                i++;
                reporte_diario(ObrasItems[i].id_Obra);
            }
        }
    });// fin de Reporte diario
}
function reporte_maquinaria(id_reporte) {
    //code
    gapi.client.communicationchannel.listRepMaquinariaCatMaquinaria({"idReporteDiario":id_reporte, "email":" "}).execute(function(resp){
        if (resp.code) {
            alert("Error de conexi—n, no se ha podido descargando reporte de maquinaria catalogo, volviendo a intentar. :::" + resp.message );
        }else{
            RepMaquinariaItems = resp.items;
            console.log("Se descago correctamente Lista de RepMaquinariaCatMaquinaria: " + ReportesItems);
            enpoints();
            i++;
        }
    });//fin de listRepMaquinariaCatMaquinaria 
}