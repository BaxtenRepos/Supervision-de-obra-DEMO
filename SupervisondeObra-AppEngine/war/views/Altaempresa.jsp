<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
   
    <script src="/js/jquery-1.9.0.min.js"></script>
    <script src="/js/Altaempresa.js"></script>
  
<title>Registro de Empresas</title>
</head>
<body onload = "init()">


<div>


  <!--  <div>id_Descripcion: <input type="text"    id="id_Descripcion"/></div>-->
 <div> Tipo de Empresa:<select id="empresa"></select> </div> 
    <div>RFC: <input type="text"            id="rfc"/></div>
    <div>Nombre: <input type="text"         id="nombre"/></div>
    <div>Siglas: <input type="text"         id="siglas"/></div>
    <div>IMSS: <input type="text"           id="imss"/></div>
    <div>Calle: <input type="text"          id="calle"/></div>
    <div>Num_Ext: <input type="text"        id="num_ext"/></div>
    <div>Num_Int: <input type="text"        id="num_int"/></div>
    <div>Colonia: <input type="text"        id="colonia"/></div>
    <div>Del_o_Mun: <input type="text"      id="del_mun"/></div>
    <div>Entidad: <input type="text"        id="entidad"/></div>
    <div>Codi_Postal: <input type="text"    id="codigo_postal"/></div>
    <button id="guardar" OnClick="enviar();">Guardar</button>



 
</body>
</html>

