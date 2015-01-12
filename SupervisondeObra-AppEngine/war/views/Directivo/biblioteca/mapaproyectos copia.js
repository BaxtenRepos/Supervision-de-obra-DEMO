var ubicacionProyectos, ubicacionObras;

function ubicacionesSql(){
  var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
  console.log("consultando ubicaciones de proyectos");
    
    db.transaction(function(tx) {
    tx.executeSql('select u.idUbicacion, p.idProyecto, p.NombreCorto, u.puntos from Ubicacion as u, Proyecto as p where u.idUbicacion = p.idUbicacion',
                            [],  function(tx, rs) {
                                
                    console.log("Ubicacion Proyectos: " + rs.rows.length);
                    ubicacionProyectos = rs.rows;
                     
              }, errorCB);
               
          }, errorCB, successConsulta);
    
    db.transaction(function(tx) {
    tx.executeSql('select u.idUbicacion, o.idObra, o.Nombre, u.puntos from Ubicacion as u, Obras as o where u.idUbicacion = o.idUbicacion',
                            [],  function(tx, rs) {
                                
                    console.log("Ubicacion Proyectos: " + rs.rows.length);
                    ubicacionObras = rs.rows;
                     
              }, errorCB);
               
          }, errorCB, successConsulta); 
    
}



function errorCB(err) {
    console.log("Error processing SQL: "+err.code + " Message: "+err.message);
}

//Tablas creadas exitosamente
function successConsulta() {
    
      console.log("Todo OK obteniendo las ubicaciones de proyectos y obras");
    
}



function mapa() {
   //alert("mapa");
  var mapOptions = {
    //zoom: 12,
    zoom: 12 ,
    center: new google.maps.LatLng(16.795341, -99.732537),
    mapTypeId: google.maps.MapTypeId.ROADMAP
  };

  var bermudaTriangle;

  map = new google.maps.Map(document.getElementById('map'),
      mapOptions);
  
  ubicacionesSql(); //Traer ubicaciones de la base de datos
  
  
  // Define the LatLng coordinates for the polygon.
  var triangleCoordsProyecto = [
      new google.maps.LatLng(16.897856, -99.809178),
      new google.maps.LatLng(16.893422, -99.816559),
      new google.maps.LatLng(16.886359, -99.819134),
      new google.maps.LatLng(16.875024, -99.816559),
      new google.maps.LatLng(16.869111, -99.808663),
      new google.maps.LatLng(16.862868, -99.801796),
      new google.maps.LatLng(16.855968, -99.793900),
      new google.maps.LatLng(16.839703, -99.793213),
      new google.maps.LatLng(16.822286, -99.792698),
      new google.maps.LatLng(16.814563, -99.789780),
      new google.maps.LatLng(16.800267, -99.791840),
      new google.maps.LatLng(16.788763, -99.772442)
  ];

  var flightPath = new google.maps.Polyline({
    path: triangleCoordsProyecto,
    geodesic: true,
    strokeColor: '#FF0000',
    strokeOpacity: 1.0,
    strokeWeight: 2
  });

  flightPath.setMap(map);
  /*
  // Construct the polygon.
  bermudaTriangleProyecto = new google.maps.Polygon({
    paths: triangleCoordsProyecto,
    strokeColor: '#FF0000',
    strokeOpacity: 0.8,
    strokeWeight: 3,
    fillColor: '#FF0000',
    fillOpacity: 0.35
  });

  bermudaTriangleProyecto.setMap(map);
  
  */
  // var area = google.maps.geometry.spherical.computeArea(triangleCoords);
  var areaProyecto = google.maps.geometry.spherical.computeArea(triangleCoordsProyecto);
  areaProyecto = (areaProyecto * 0.01)/100;
  areaProyecto = areaProyecto.toFixed(2);
  var contentStringProyecto = '<div id="content">'+
      '<div id="siteNotice">'+
      '</div>'+
      '<h1 id="firstHeading" class="firstHeading">PROTECCIÓN DE TALUDES EN BORDOS DEL RÍO SABANA Y SUS AFLUENTES, EN EL MUNICIPIO DE ACAPULCO DE JUAREZ, ESTADO DE GUERRERO.</h1>'+
      '<div id="bodyContent">'+
     
      '<p>Area del proyecto: '+areaProyecto +' hectareas <br>'+
      '<p><br><br><a href="detalle_proyectos.html?idProyecto=1"> '+
      '<button>Ir al detalle del Protecto</button> '+
      '</a> '+
      '</div>'+
      '</div>';

  var infowindowProyecto = new google.maps.InfoWindow({
      content: contentStringProyecto
  });
var myLatlngProyecto = new google.maps.LatLng(16.897856, -99.809178);
var myIconProyecto = new google.maps.MarkerImage("assets/img/icono.png", null, null, null, new google.maps.Size(90,60));
  
  var markerProyecto = new google.maps.Marker({
      position: myLatlngProyecto,
      map: map,
      icon: myIconProyecto,
      title: 'Proyecto Acapulco'
  });

 
  google.maps.event.addListener(markerProyecto, 'click', function() {
    infowindowProyecto.open(map,markerProyecto);
  });
  
  //Obra---------------------------------------------------------------------------------------------
   // Define the LatLng coordinates for the polygon.
  /*var triangleCoordsProyecto = [
      new google.maps.LatLng(19.446347, -98.085426),
      new google.maps.LatLng(19.477100, -98.058303),
      new google.maps.LatLng(19.492474, -98.015903),
      new google.maps.LatLng(19.502506, -98.976592),
      new google.maps.LatLng(19.468846, -97.920459),
      new google.maps.LatLng(19.426275, -97.951015),
      new google.maps.LatLng(19.410085, -97.985862),
      new google.maps.LatLng(19.410247, -97.034957),
      new google.maps.LatLng(19.414780, -97.082851),
      new google.maps.LatLng(19.446347, -97.085426)
  ];

  // Construct the polygon.
  bermudaTriangleProyecto = new google.maps.Polygon({
    paths: triangleCoordsProyecto,
    strokeColor: '#FF0000',
    strokeOpacity: 0.8,
    strokeWeight: 3,
    fillColor: '#FF0000',
    fillOpacity: 0.35
  });

  bermudaTriangleProyecto.setMap(map);*/
  var areaObra = google.maps.geometry.spherical.computeArea(triangleCoordsProyecto);
  var contentStringObra = '<div id="content">'+
      '<div id="siteNotice">'+
      '</div>'+
      '<h1 id="firstHeading" class="firstHeading">TRAMO 3 </strong><br> </center></h1>'+
      '<div id="bodyContent">'+
      '<p><b></b> <b>PROTECCION DE TALUDES ACAPULCO</b> ' +
     
      '<p>Area de la Obra: '+areaObra +' m2 <br><br>'+
      '<a href="detalle_obras.html?idObra=1&&idProyecto=1"> '+
      '<button>Ir al detalle de la Obra</button> '+
      '</a> '+
      '</div>'+
      '</div>';

  var infowindowObra = new google.maps.InfoWindow({
      content: contentStringObra
  });
var myLatlngObra = new google.maps.LatLng(16.839481, -99.793247);
 //var makerObra = new Array();
//for(var ii = 0; ii<=5; ii++){
     var myIconObra = new google.maps.MarkerImage("assets/img/obra.png", null, null, null, new google.maps.Size(60,40));
     
    
     //var markerObra
  var markerObra = new google.maps.Marker({
      position:myLatlngObra,
      map: map,
      icon: myIconObra,
      title: 'Obra Acapulco'
  });
  google.maps.event.addListener(markerObra, 'click', function() {
    infowindowObra.open(map,markerObra);
  });
    
    
    var areaObra = google.maps.geometry.spherical.computeArea(triangleCoordsProyecto);
  var contentStringObra2 = '<div id="content">'+
      '<div id="siteNotice">'+
      '</div>'+
      '<h1 id="firstHeading" class="firstHeading">TRAMO 8 </strong><br> </center></h1>'+
      '<div id="bodyContent">'+
      '<p><b></b> <b>PROTECCION DE TALUDES ACAPULCO</b> ' +
     
      '<p>Area de la Obra: '+areaObra +' m2 <br><br>'+
      '<a href="detalle_obras.html?idObra=2&&idProyecto=1"> '+
      '<button>Ir al detalle de la Obra</button> '+
      '</a> '+
      '</div>'+
      '</div>';

  var infowindowObra2 = new google.maps.InfoWindow({
      content: contentStringObra2
  });
var myLatlngObra = new google.maps.LatLng(16.798073, -99.788098);
 //var makerObra = new Array();
//for(var ii = 0; ii<=5; ii++){
     var myIconObra = new google.maps.MarkerImage("assets/img/obra.png", null, null, null, new google.maps.Size(60,40));
     
    
     //var markerObra
  var markerObra2 = new google.maps.Marker({
      position:myLatlngObra,
      map: map,
      icon: myIconObra,
      title: 'Obra Acapulco'
  });
  google.maps.event.addListener(markerObra2, 'click', function() {
    infowindowObra2.open(map,markerObra2);
  });
//}

}