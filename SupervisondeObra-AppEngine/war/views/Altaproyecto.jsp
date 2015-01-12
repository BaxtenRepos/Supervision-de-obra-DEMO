 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <style>
      html, body, #map-canvas {
        height: 90%;
        margin: 0px;
        padding: 0px;
      }
            #panel {
        position: absolute;
        top: 10px;
        left: 50%;
        margin-left: -180px;
        z-index: 5;
        background-color: #fff;
        padding: 5px;
        border: 1px solid #999;
      }
    </style>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>
    <script src="/js/jquery-1.9.0.min.js"></script>
    <script src="/js/Altaproyecto.js"></script>

<title>Alta de Proyectos</title>
</head>
<body onload = "init()">
       <div id="map-canvas"></div>
       <div align="center" style="margin-top: 50px;">
       <div id="panel" style="margin-left: -52px">  
     </div>
<div>

    <div>Descripcion:<textarea rows="4" cols="50" id="id_Descripcion">

</textarea>
    <!--<input rows="4" cols="50" type="text"  id="id_Descripcion"/></div>-->
    
    <br>
     <div id="divv">
	dependencia: <select id="empresa"></select> 
   </div> 
    <!--<button id="altaempresa" onclick="'<a href=/Altaempresa.jsp</a>'">Nueva Dependencia </button>-->
       <a href="./Altaempresa.jsp">
<button id="altaempresa">Nueva Empresa</button>
</a>
 
     <div id="divv2">
	Secretaria: <select id="secretaria"></select> 
   </div>
   <a href="./Altaempresa.jsp">
<button id="altasecretaria">Nueva Secretaria</button>
</a>
   
     <!--<button id="altasecretaria" onclick="'<a href=/Altaempresa.jsp</a>'">Nueva Secretaria </button>-->
    <div>nombre_corto: <input type="text"   name="nombre_corto" id="nombre_corto"/></div>
    <div>nombre_largo: <input type="text"   name="nombre_largo" id="nombre_largo"/></div>
    <button id="enviar" >Enviar</button>
</body>
</html>