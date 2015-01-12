
  var infowindow = null;
  var myIconObra = new google.maps.MarkerImage("assets/img/obra.png", null, null, null, new google.maps.Size(60,30));
  var myIconProyecto = new google.maps.MarkerImage("assets/img/icono.png", null, null, null, new google.maps.Size(90,60));
        
var get = getGET();
var idproyecto = get.idProyecto;
var ubicacion = get.idUbicacion;
function mapa() {
  //proyecto();
  get_proyecto();
  proyecto2(pagina);
   
  var sites = new Array();
   
  var resulset;
  var triangleCoordsProyecto = new Array();
  var triangleCoordsObra = new Array();
  var makersProyecto = new Array();

 
       infowindow = new google.maps.InfoWindow({
                     content: "loading..."
       });
                     
   
   
  var mapOptions = {
    //zoom: 12,
    zoom: 4,
    center: new google.maps.LatLng(16.795341, -99.732537),
    mapTypeId: google.maps.MapTypeId.ROADMAP
  };

  var bermudaTriangle;

  map = new google.maps.Map(document.getElementById('map'),
      mapOptions);
  
 
  var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
 
  db.transaction(function(tx) {
             
    //SELECT * FROM Proyectos as p, Ubicacion as u WHERE p.idUbicacion = u.idUbicacion and p.idProyecto = "2"
//              tx.executeSql('SELECT * FROM Ubicacion, Proyectos WHERE Ubicacion.idUbicacion = Proyectos.idUbicacion',
	      tx.executeSql('SELECT * FROM Proyectos as p, Ubicacion as u WHERE p.idUbicacion = u.idUbicacion and p.idProyecto = ?',
                            [idproyecto],  function(tx, rs) {
                                
                    //*console.log("proyectos con ubicaciones: " + rs.rows.length);
                     for(var p=0; p<rs.rows.length; p++){ //Por proyecto
         
                       triangleCoordsProyecto = new Array();
                       resulset = rs.rows.item(p);
                       
                        var contentString = '<div id="content">'+
                                    '<div id="siteNotice">'+
                                    '</div>'+
                                    '<h1 id="firstHeading" class="firstHeading">'+resulset.NombreCorto+' </strong><br> </center></h1>'+
                                    '<div id="bodyContent">'+
                                    '<p><br> <a href="detalle_proyectos.html?idProyecto='+resulset.idProyecto+'"> '+
                                    '<button type="button" class=\"btn btn-large btn-white btn-demo-space\">Ir al detalle del proyecto</button> '+
                                    '</a> '+
                                    '</div>'+
                                    '</div>';
                       
                       splitp = resulset.puntos.split("|");
                      for(var u=0; u<splitp.length; u++){
                          //code
                           slatlong = splitp[u].split(",");
                           
                           
                           if (splitp.length == 1) {
                          //code
                          latlongo= new google.maps.LatLng(slatlong[0], slatlong[1]);
                         
                         }else{
                          triangleCoordsProyecto[u] = new google.maps.LatLng(slatlong[0], slatlong[1]);
                         }
                         
                      }//for ubicaciones
              
                      
                      if (splitp.length == 1) {
                          //code
                          //console.log("la ubicacion del  proyecto es un punto: " +resulset.NombreCorto);
                         
                          sites[p]=[resulset.NombreCorto, latlongo, contentString]; 
                         
                         }else if (splitp[0] == splitp[splitp.length -1]) {
                          //code
                            /*console.log("la ubicacion del  proyecto es un poligono: " +resulset.NombreCorto);//
                             console.log("la ubicacion del  proyecto es un poligono: " +triangleCoordsProyecto);//
                             console.log("la ubicacion del  proyecto es un poligono split: " +splitp);/*/
                              //sites[p]={titulo:resulset.NombreCorto, latlong:triangleCoordsProyecto[0], info:resulset.NombreCorto};
                               var areaProyecto = google.maps.geometry.spherical.computeArea(triangleCoordsProyecto);
                                                  areaProyecto = (areaProyecto * 0.01)/100;
                                                  areaProyecto = areaProyecto.toFixed(2);
                               var contentString = '<div id="content">'+
                                                       '<div id="siteNotice">'+
                                                                      '</div>'+
                                                       '<h1 id="firstHeading" class="firstHeading">'+resulset.NombreCorto+' </strong><br> </center></h1>'+
                                                       '<div id="bodyContent">'+
                                                       '<p>Area del proyecto: '+areaProyecto +' hectáreas <br><br>'+
                                                       '<p><br> <a href="detalle_proyectos.html?idProyecto='+resulset.idProyecto+'"> '+
                                                       '<button type="button" class=\"btn btn-large btn-white btn-demo-space\">Ir al detalle del proyecto</button> '+
                                                       '</a> '+
                                                       '</div>'+
                                                       '</div>';
  
                           sites[p]=[resulset.NombreCorto, triangleCoordsProyecto[0] , contentString];
                              
                           bermudaTriangleProyecto = new google.maps.Polygon({
                                  paths: triangleCoordsProyecto,
                                  strokeColor: '#FF0000',
                                  strokeOpacity: 0.8,
                                  strokeWeight: 3,
                                  fillColor: '#FF0000',
                                  fillOpacity: 0.35
                           });

                           bermudaTriangleProyecto.setMap(map);
                         }else{
                            //*console.log("la ubicacion del  proyecto es una linea: " +resulset.NombreCorto);
			    //*console.log("la ubicacion del  proyecto es una linea: " +triangleCoordsProyecto);
                            sites[p]=[resulset.NombreCorto, triangleCoordsProyecto[0], contentString];
                             
                          var flightPath = new google.maps.Polyline({
                                           path: triangleCoordsProyecto,
                                           geodesic: true,
                                           strokeColor: '#FF0000',
                                           strokeOpacity: 1.0,
                                           strokeWeight: 2
                                           });

                                flightPath.setMap(map);
                         }//if
                        
                     }// for proyectos
                     
                     console.log("Sites: " +sites);
                     setMarkers(map, sites);
	             
                     
                     
              }, errorCB);
               
          }, errorCB, successConsulta);
  
  }

 

function successConsulta() {
  console.log("succes consulta proyecto ubicaciones, se pintaran obras");
   var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
    var sitesObra = new Array();
  db.transaction(function(tx) {
       //SELECT o.Nombre, o.idProyecto, o.idObra, u.puntos FROM Obras as o, Ubicacion as u WHERE cast(o.idProyecto as int) = ? group by o.idUbicacion
              //tx.executeSql('SELECT * FROM Ubicacion, Obras WHERE Ubicacion.idUbicacion = Obras.idUbicacion',
	      tx.executeSql('SELECT * FROM Ubicacion as u, Obras as o WHERE u.idUbicacion = o.idUbicacion and cast(o.idProyecto as int) = ?',
                            [idproyecto],  function(tx, rs) {
			    //[],  function(tx, rs) {
                                
                    //*console.log("Obras con ubicacion:" + rs.rows.length);
                     for(var p=0; p<rs.rows.length; p++){ //Por obra
                        triangleCoordsObra = new Array();
                        myIconObra = new google.maps.MarkerImage("assets/img/obra.png", null, null, null, new google.maps.Size(40,20));
                        resulset = rs.rows.item(p);
                        var contentString = '<div id="content">'+
                                    '<div id="siteNotice">'+
                                    '</div>'+
                                    '<h1 id="firstHeading" class="firstHeading">'+resulset.Nombre+' </strong><br> </center></h1>'+
                                    '<div id="bodyContent">'+
                                    '<p align=\"center\"><br> <a href="detalle_obras_reporte.html?idProyecto='+resulset.idProyecto+'&&idObra='+resulset.idObra+'"> '+
                                    '<button type="button" class=\"btn btn-large btn-white btn-demo-space\">Ir al detalle de la obra</button> '+
                                    '</a> </p>'+
                                    '</div>'+
                                    '</div>';
                       splitp = resulset.puntos.split("|");
                    
                      for(var u=0; u<splitp.length; u++){
                     
                          //code
                           slatlong = splitp[u].split(",");
                       
                         if (splitp.length == 1) {
                          //code
                          latlongo= new google.maps.LatLng(slatlong[0], slatlong[1]);
                          
                         }else{
                          triangleCoordsObra[u] = new google.maps.LatLng(slatlong[0], slatlong[1]);
                          
                         }
                          
                      }//for ubicaciones
                      
                      if (splitp.length == 1) {
                          
                          //*console.log("la ubicacion de la obra es un punto: " +resulset.Nombre);
                          //code
                           sitesObra[p]=[resulset.Nombre, latlongo, contentString]; 
                            
                         /*  markerProyecto = new google.maps.Marker({
                           position: latlongo,
                           map: map,
                           icon: myIconObra,
                          title: resulset.Nombre
                            });*/
                           //markerProyecto.setMap(map);
                         }else if (splitp[0] == splitp[splitp.length -1]) {
                             //*console.log("la ubicacion la obra es un poligono: " +resulset.Nombre);
                             //*console.log("la ubicacion la obra  es un poligono: " +triangleCoordsObra);
                             //*console.log("la ubicacion la obra  es un poligono split: " +splitp);
                             var areaObra = google.maps.geometry.spherical.computeArea(triangleCoordsObra);
                                                  areaObra = (areaObra * 0.01)/100;
                                                  areaObra = areaObra.toFixed(2);
                               var contentString = '<div id="content">'+
                                                       '<div id="siteNotice">'+
                                                                      '</div>'+
                                                       '<h1 id="firstHeading" class="firstHeading">'+resulset.Nombre+' </strong><br> </center></h1>'+
                                                       '<div id="bodyContent">'+
                                                       '<p>Area del proyecto: '+ areaObra +' Hectáreas <br><br></p>'+
                                                       '<p align=\"center\"><br> <a href="detalle_obras_reporte.html?idProyecto='+resulset.idProyecto+'&&idObra='+resulset.idObra+'"> '+
                                                       '<button type="button" class=\"btn btn-large btn-white btn-demo-space\">Ir al detalle de la obra</button> '+
                                                       '</a> </p>'+
                                                       '</div>'+
                                                       '</div>';
                             sitesObra[p]=[resulset.Nombre, triangleCoordsObra[0], contentString]; 
                             
                          //code
                         /* markerProyecto = new google.maps.Marker({
                           position: triangleCoordsObra[0],
                           map: map,
                           icon: myIconObra,
                          title: resulset.Nombre
                            });*/
                           bermudaTriangleProyecto = new google.maps.Polygon({
                                  paths: triangleCoordsObra,
                                  strokeColor: '#5FB404',
                                  strokeOpacity: 0.8,
                                  strokeWeight: 3,
                                  fillColor: '#5FB404',
                                  fillOpacity: 0.1
                           });

                           bermudaTriangleProyecto.setMap(map);
                         }else{
                        //*console.log("la ubicacion de la obra  es una linea: " +resulset.Nombre);
                        //*console.log("la ubicacion de la obra  es una linea: " +triangleCoordsObra);
                        //*console.log("la ubicacion de la obra  es una linea split: " +splitp);
                              sitesObra[p]=[resulset.Nombre, triangleCoordsObra[0], contentString]; 
                             /* markerProyecto = new google.maps.Marker({
                           position: triangleCoordsObra[0],
                           map: map,
                           icon: myIconObra,
                          title: resulset.Nombre
                            });*/
                          var flightPath = new google.maps.Polyline({
                                           path: triangleCoordsObra,
                                           geodesic: true,
                                           strokeColor: '#5FB404',
                                           strokeOpacity: 1.0,
                                           strokeWeight: 2
                                           });

                                flightPath.setMap(map);
                         }//if
                       
                         
                     }// for obras
                     
                     //*console.log("Sites: " +sitesObra);
                     setMarkersObra(map, sitesObra);
                     
              }, errorCB);
               
          }, errorCB, successConsultaObras);
   
}

function successConsultaObras() {
   //*console.log("succes consulta obras ubicaciones");
}

function errorCB(err) {
    console.log("Error processing SQL: "+err.code + " Message: "+err.message);
}
  
  function setMarkers(map, markers) {
//console.log("markers: " + markers.length);
        for (var i = 0; i < markers.length; i++) {
            var sites = markers[i];
            //*console.log("markers[i]: " + markers[i] +" LATLONG: "+sites[1]) ;
            //*console.log("position: " + sites[1] +" title: "+sites[0]+" html: "+sites[2]+" map: "+map);
            var marker = new google.maps.Marker({
                position: sites[1],
                map: map,
                title: sites[0],
                icon: myIconProyecto,
                html: sites[2]
            });

            var contentString = "Some content";

            google.maps.event.addListener(marker, "click", function () {
               // alert(this.html);
                infowindow.setContent(this.html);
                infowindow.open(map, this);
            });
        }
    }

  function setMarkersObra(map, markers) {
//console.log("markers: " + markers.length);
        for (var i = 0; i < markers.length; i++) {
            var sites = markers[i];
            //*console.log("markers[i]: " + markers[i] +" LATLONG: "+sites[1]) ;
            //*console.log("position: " + sites[1] +" title: "+sites[0]+" html: "+sites[2]+" map: "+map);
            var marker = new google.maps.Marker({
                position: sites[1],
                map: map,
                title: sites[0],
                icon: myIconObra,
                html: sites[2]
            });

            var contentString = "Some content";

            google.maps.event.addListener(marker, "click", function () {
               // alert(this.html);
                infowindow.setContent(this.html);
                infowindow.open(map, this);
            });
        }
    }
//-----------