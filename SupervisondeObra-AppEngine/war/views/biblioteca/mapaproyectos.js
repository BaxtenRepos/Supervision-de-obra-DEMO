

function mapa() {
   //alert("mapa");
  var mapOptions = {
    //zoom: 12,
    zoom: 4 ,
    center: new google.maps.LatLng(21.397962, -100.182170),
    mapTypeId: google.maps.MapTypeId.ROADMAP
  };

  var bermudaTriangle;

  map = new google.maps.Map(document.getElementById('map'),
      mapOptions);
  
  /*
  
  var db = window.openDatabase("Database", "1.0", "PhoneGap Demo", 200000);
    console.log("mostrando datos de proyecto id: " +idProyecto);
    
  db.transaction(function(tx) {
             
        
              tx.executeSql('select * from ubicacion as u, proyecto as p where u.idProyecto = p.idProyecto',
                            [],  function(tx, rs) {
                                
                            console.log("Proyectos: " + rs.rows.length);
                      var p = rs.rows;
                     
              }, errorCB);
               
          }, errorCB, successConsulta);
  
  
  */
  
  
  // Define the LatLng coordinates for the polygon.
  var triangleCoordsProyecto = [
      new google.maps.LatLng(19.446347, -99.085426),
      new google.maps.LatLng(19.477100, -99.058303),
      new google.maps.LatLng(19.492474, -99.015903),
      new google.maps.LatLng(19.502506, -98.976592),
      new google.maps.LatLng(19.468846, -98.920459),
      new google.maps.LatLng(19.426275, -98.951015),
      new google.maps.LatLng(19.410085, -98.985862),
      new google.maps.LatLng(19.410247, -99.034957),
      new google.maps.LatLng(19.414780, -99.082851),
      new google.maps.LatLng(19.446347, -99.085426)
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

  bermudaTriangleProyecto.setMap(map);
  // var area = google.maps.geometry.spherical.computeArea(triangleCoords);
  var areaProyecto = google.maps.geometry.spherical.computeArea(triangleCoordsProyecto);
  areaProyecto = (areaProyecto * 0.01)/100;
  areaProyecto = areaProyecto.toFixed(2);
  var contentStringProyecto = '<div id="content">'+
      '<div id="siteNotice">'+
      '</div>'+
      '<h1 id="firstHeading" class="firstHeading">Proyecto CONAGUA</h1>'+
      '<div id="bodyContent">'+
      '<p><b>Lago</b> de <b>Texcoco</b> ' +
      'Tratamiento de aguas </p>'+
      '<p>Area del proyecto: '+areaProyecto +' hectareas <br>'+
      '<p>12 Obras <br><br><a href="detalle_proyectos.html"> '+
      '<button>Ir al detalle del Protecto</button> '+
      '</a> '+
      '</div>'+
      '</div>';

  var infowindowProyecto = new google.maps.InfoWindow({
      content: contentStringProyecto
  });
var myLatlngProyecto = new google.maps.LatLng(19.446347, -99.085426);
var myIconProyecto = new google.maps.MarkerImage("assets/img/icono.png", null, null, null, new google.maps.Size(90,60));
  
  var markerProyecto = new google.maps.Marker({
      position: myLatlngProyecto,
      map: map,
      icon: myIconProyecto,
      title: 'Proyecto Areopuerto'
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
      '<h1 id="firstHeading" class="firstHeading">Obra Texcoco</h1>'+
      '<div id="bodyContent">'+
      '<p><b>Lago</b> de <b>Texcoco</b> ' +
      'Tratamiento de aguas </p>'+
      '<p>Proyecto CONAGUA'+
      '<p>Area de la Obra: '+areaObra +' m2 <br><br>'+
      '<a href="detalle_obras.html"> '+
      '<button>Ir al detalle de la Obra</button> '+
      '</a> '+
      '</div>'+
      '</div>';

  var infowindowObra = new google.maps.InfoWindow({
      content: contentStringObra
  });
var myLatlngObra = new google.maps.LatLng(19.467183, -98.968967);
 //var makerObra = new Array();
//for(var ii = 0; ii<=5; ii++){
     var myIconObra = new google.maps.MarkerImage("assets/img/obra.png", null, null, null, new google.maps.Size(60,40));
     
    
     //var markerObra
  var markerObra = new google.maps.Marker({
      position:myLatlngObra,
      map: map,
      icon: myIconObra,
      title: 'Obra Lago de Texcoco'
  });
  google.maps.event.addListener(markerObra, 'click', function() {
    infowindowObra.open(map,markerObra);
  });
//}

}