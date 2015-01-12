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
    <script src="/js/Altaobra.js"></script>

<title>Alta de Obras</title>
</head>
<body onload = "init()">
       <div id="map-canvas"></div>
       <div align="center" style="margin-top: 50px;">
       <div id="panel" style="margin-left: -52px">
      <!-- <button id="guardar" onclick="getConfirmation();">Guardar Puntos</button>--> 
     </div>
<div>


<div>NoContrato: <input type="text"  placeholder="numero "id="NoContrato"/></div>
  <div id="proyectodiv">
	Proyecto: <select id="proyectoselect"></select> 
   </div>
    <a href="./Altaproyecto.jsp"><button id="newproyect">Nuevo Proyecto</button></a>
<div>RFC: <input type="text"id="RFC"/></div>
<div>Nombre: <input type="text"id="Nombre"/></div>
<div id="gobiernodiv">
	Gobierno: <select id="gobiernoselect"></select> 
   </div>
   <a href="./Altaempresa.jsp"><button id="newgobierno">Nuevo Gobierno</button></a>

 <div id="secretariadiv">
	Secretaria: <select id="secretariaselect"></select> 
   </div>
     <a href="./Altaempresa.jsp"><button id="newsecretaria">Nueva Secretaria</button></a>

 <div id="dependenciadiv">
	Dependencia: <select id="dependenciaselect"></select> 
   </div>
    <a href="./Altaempresa.jsp"><button id="newdependencia">Nueva Dependencia</button></a>
<div>Direccion: <input type="text"id="Direccion"/></div>
<div>Subdireccion: <input type="text"id="Subdireccion"/></div>
<div>Area: <input type="text"id="Area"/></div>
<div id="contratistadiv">
	Contratista: <select id="contratistaselect"></select> 
   </div>
    <a href="./Altaempresa.jsp"><button id="newcontratista">Nueva Contratista</button></a>
<div>Superintendente: <input type="text"id="Superintendente"/></div>
<div>EntidadFederativa: <input type="text"id="EntidadFederativa"/></div>
<div>Descripcion: <input type="text"id="Descripcion"/></div>
<div>FechaContrato: <input type="text"id="FechaContrato"/></div>
<div>TipoContrato: <input type="text"id="TipoContrato"/></div>
<div>ImporteContratoSinIVA: <input type="text" placeholder="numero "id="ImporteContratoSinIVA"/></div>
<div>NombreEjercicioFiscal1: <input type="text"id="NombreEjercicioFiscal1"/></div>
<div>ImporteFiscal1SinIVA: <input type="text" placeholder="numero "id="ImporteFiscal1SinIVA"/></div>
<div>ImporteConvenioAmpliacion: <input placeholder="numero " type="text"id="ImporteConvenioAmpliacion"/></div>
<div>ImporteConvenioReduccion: <input type="text" placeholder="numero "id="ImporteConvenioReduccion"/></div>
<div>ImporteAjusteCostos: <input type="text" placeholder="numero "id="ImporteAjusteCostos"/></div>
<div>FechaInicioContrato: <input type="text"id="FechaInicioContrato"/></div>
<div>FechaTerminoContrato: <input type="text"id="FechaTerminoContrato"/></div>
<div>PeriodoEjucionDias: <input  placeholder="numero "type="text" id="PeriodoEjucionDias"/></div>
<div>PartidaPresupuestal: <input  placeholder="numero "type="text" id="PartidaPresupuestal"/></div>
<div>Anticipo: <input type="text"placeholder="numero "id="Anticipo"/></div>
<div>NoFianzaAnticipo: <input  placeholder="numero "type="text"  id="NoFianzaAnticipo"/></div>
<div>FechaFianzaAnticipo : <input type="text"  id="FechaFianzaAnticipo"/></div>
<div>MontoFianzaAnticipo: <input type="text" placeholder="numero "  id="MontoFianzaAnticipo"/></div>
<div>NoFianzaCumplimiento: <input type="text" placeholder="numero "  id="NoFianzaCumplimiento"/></div>
<div>FechaFianzaCumplimiento: <input type="text"  id="FechaFianzaCumplimiento"/></div>
<div>MontoFianzaCumplimiento: <input  placeholder="numero "type="text" id="MontoFianzaCumplimiento"/></div>
<div>CargoRevision1: <input type="text" id="CargoRevision1"/></div>
<div>NombreRevision1: <input type="text"  id="NombreRevision1"/></div>
<div>CargoRevision2: <input type="text" id="CargoRevision2"/></div>
<div>NombreRevision2: <input type="text" id="NombreRevision2"/></div>
<div>NombreQuienAutoriza: <input type="text" id="NombreQuienAutoriza"/></div>
<div>CargoVoBo: <input type="text" id="CargoVoBo"/></div>
<div>NombreVoBo: <input type="text" id="NombreVoBo"/></div>
<div>Borrador: <input type="text" id="Borrador"/></div>
<div>Limite de Desvio: <input type="text" id="limiteDesvio"/></div>
<button id="enviar" >Enviar</button>

</body>

</html>
