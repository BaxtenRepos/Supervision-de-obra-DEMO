<!DOCTYPE html>

<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>

<%
    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>MasterPage</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />


<link href="assets/plugins/pace/pace-theme-flash.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="assets/plugins/jquery-slider/css/jquery.sidr.light.css" rel="stylesheet" type="text/css" media="screen"/>
<!-- BEGIN CORE CSS FRAMEWORK -->
<link href="assets/plugins/boostrapv3/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="assets/plugins/boostrapv3/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css"/>
<link href="assets/plugins/font-awesome/css/font-awesome.css" rel="stylesheet" type="text/css"/>
<link href="assets/css/animate.min.css" rel="stylesheet" type="text/css"/>
<!-- END CORE CSS FRAMEWORK -->

<!-- BEGIN CSS TEMPLATE -->
<link href="assets/css/style.css" rel="stylesheet" type="text/css"/>
<link href="assets/css/responsive.css" rel="stylesheet" type="text/css"/>
<link href="assets/css/custom-icon-set.css" rel="stylesheet" type="text/css"/>

<!-- END CSS TEMPLATE -->
<script src="biblioteca/utilidades.js" type="text/javascript"></script>

  <link rel="stylesheet" href="assets/css/dialog.css" type="text/css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
  <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
 <script src="/js/Empresaalta.js"></script>
 <script src="/js/Validarsesion.js"></script>
 <script src="/js/Directoriocontactos.js"></script>

</head>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body class="" onload = "valida()">
        
	
<!-- BEGIN HEADER -->
<div class="header navbar navbar-inverse "> 
  <!-- BEGIN TOP NAVIGATION BAR -->
  <div class="navbar-inner">
	<div class="header-seperation"> 
		<ul class="nav pull-left notifcation-center" id="main-menu-toggle-wrapper" style="display:none">	
		 <li class="dropdown"> <a id="main-menu-toggle" href="#main-menu"  class="" > <div class="iconset top-menu-toggle-white"></div> </a> </li>		 
		</ul>
      <!-- BEGIN LOGO -->
				<img src="assets/img/logo.png" class="logo" alt=""
					data-src="assets/img/logo.png"
					data-src-retina="assets/img/logo2x.png" width="106" height="60" />


				<!-- END LOGO -->
      <ul class="nav pull-right notifcation-center">	
        
		<li class="dropdown" id="portrait-chat-toggler" style="display:none"> <a href="#sidr" class="chat-menu-toggle"> <div class="iconset top-chat-white "></div> </a> </li>        
      </ul>
      </div>
      <!-- END RESPONSIVE MENU TOGGLER --> 
      <div class="header-quick-nav" id = "header">
	    
	    <!-- BEGIN TOP NAVIGATION MENU -->
	  <div class="pull-left"> 
        <ul class="nav quick-section">
          <li class="quicklinks"> <a href="#" class="" id="layout-condensed-toggle" >
            <div class="iconset top-menu-toggle-dark"></div>
            </a> </li>
        </ul>
       
	  </div>
	 <!-- END TOP NAVIGATION MENU -->
	 <!-- BEGIN CHAT TOGGLER -->
      <div class="pull-right">
					<div class="chat-toggler">
						<a href="#" class="dropdown-toggle" id="my-task-list"
							data-placement="bottom" data-content='' data-toggle="dropdown"
							data-original-title="Notificaciones">
							<div class="user-details">
								<div class="username" id="emailUsr">
		<!--  							<span class="badge badge-important">2</span> Juan Carlos <span
										class="bold">Gonzalez </span>-->
								</div>
							</div>
							<div class="iconset top-down-arrow"></div>
						</a>
						<div id="notification-list" style="display: none">
							<div style="width: 300px">

								<div class="notification-messages danger">
									<div class="iconholder">
										<i class="icon-warning-sign"></i>
									</div>
									<div class="message-wrapper">
										<div class="heading">Proyecto CONAGUA</div>
										<div class="description">Desvio de 30%</div>
										<div class="date pull-left">6 Agosto 2014</div>
									</div>
									<div class="clearfix"></div>
								</div>
								<div class="notification-messages danger">
									<div class="iconholder">
										<i class="icon-warning-sign"></i>
									</div>
									<div class="message-wrapper">
										<div class="heading">Obra Lago de Texcoco</div>
										<div class="description">Desvio de 40%</div>
										<div class="date pull-left">6 Agosto 2014</div>
									</div>
									<div class="clearfix"></div>
								</div>

							</div>
						</div>
						<div class="profile-pic">
							<img alt=""
								id="fotoUsuario2"
								data-src="assets/img/profiles/avatar_small.png"
								data-src-retina="assets/img/profiles/avatar_small2x.jpg"
								width="35" height="35" />
						</div>
					</div>
					<ul class="nav quick-section ">

						<li class="quicklinks"><span class="h-seperate"></span></li>
						<li class="quicklinks"><a id="chat-menu-toggle" href="#sidr"
							class="chat-menu-toggle"><div class="iconset top-chat-dark "></div>
						</a></li>
					</ul>
				</div>
	   <!-- END CHAT TOGGLER -->
      </div> 
      <!-- END TOP NAVIGATION MENU --> 
   
  </div>
  <!-- END TOP NAVIGATION BAR --> 
      </div> 
<!-- END HEADER -->
<!-- BEGIN CONTAINER -->
<div class="page-container row-fluid">
  <!-- BEGIN SIDEBAR -->
  <div class="page-sidebar" id="main-menu"> 
  <!-- BEGIN MINI-PROFILE -->
   <div class="page-sidebar-wrapper" id="main-menu-wrapper">
      <div class="user-info-wrapper">
     
       <div style="color: white">Bienvenido</div>

					<div class="profile-wrapper">
						<img alt=""
							id="fotoUsuario"
							data-src="assets/img/profiles/avatar1.jpg "
							data-src-retina="assets/img/profiles/avatar2x.jpg" width="69"
							height="69" />
					</div>
					<div class="user-info">

						<div class="username" id="username">
			<!--  				Juan Carlos <br> Gonzalez <br> Solorzano-->
						</div>

						<div class="status">
							Status<a href="#"><div class="status-icon green"></div>Online</a>
						</div>
					</div>
   </div>
  <!-- END MINI-PROFILE -->
   
   <!-- BEGIN SIDEBAR MENU -->	
    <ul id="menuUserOptions">
      <br><br>
      <li class=""><a href="#"><i class="fa fa-folder-open"></i><span class="title">Biblioteca</span><span
							class="arrow"></span></a>
						<ul class="sub-menu">
							<li><a href="../biblioteca.jsp">Lista de Archivos </a></li>
							<li><a href="./multimedia.jsp">Subir archivo </a></li> 
							<!-- <li><a href="#"> Editar Proyectos </a></li> -->
						</ul></li>
   <li class=""><a href="#"><i
			class="fa fa-folder-open"></i><span class="title">Proyectos</span><span
			class="arrow"></span></a>
		<ul class="sub-menu">
			<li><a href="./PruebaProyecto.html"> Lista de Proyectos </a></li>
			<li><a href="./Proyecto2.html">	Alta de Proyectos </a></li>
							<li><a href="../bibliotecaProyectos.jsp">Lista de Archivos de Proyectos </a></li>
							<li><a href="./multimediaProyecto.jsp">Subir archivo </a></li>
							
			<!-- <li><a href="#"> Editar Proyectos </a></li> -->
		</ul></li>
	<li class=""><a href="#"> <i class="fa fa-road"></i><span
			class="title">Obras</span><span class="arrow"></span></a>
		<ul class="sub-menu">
			<li><a href="./ListaObras.html"> Lista de Obras</a></li>
			<li><a href="./Obra2.html">	Alta de Obras</a></li>
							<li><a href="../bibliotecaObras.jsp">Lista de Archivos de Obra </a></li>
							<li><a href="./multimediaObra.jsp">Subir archivo </a></li>
							<li><a href="./generaReporte.jsp">Generar reportes </a></li>
			<!-- <li><a href="#">Editar Obras</a></li> -->
		</ul></li>
	<li class=""><a href="#"> <i class="fa fa-tasks"></i><span
			class="title">Conceptos</span><span class="arrow"></span></a>
		<ul class="sub-menu">
			<li><a href="./ListaConceptos.html"> Lista de Conceptos</a></li>
			<li><a href="./Conceptoalta.jsp">Alta de Conceptos</a></li>
			<!-- <li><a href="#">Editar Conceptos</a></li>  -->
		</ul></li>
	<li class=""><a href="#"> <i class="fa fa-sitemap"></i> <span
			class="title">Empresas</span><span class="arrow "></span></a>
		<ul class="sub-menu">
			<li><a href="./ListaEmpresas.html"> Lista de Empresas</a></li>
			<li><a href="./Empresaalta.jsp">Alta de Empresas</a></li>
			<!-- <li><a href="#"> Editar Empresas</a></li> -->
		</ul></li>
	<li class=""><a href="#"> <i class="fa fa-users"></i> <span
			class="title">Usuarios</span><span class="arrow"></span></a>
		<ul class="sub-menu">
			<li><a href="./ListaUsuarios.html"> Lista de Usuarios</a></li>
			<li><a href="./AltaUsuario.jsp">Alta de Usuarios</a></li>
			<!-- <li><a href="#">Editar Usuarios</a></li> -->
		</ul></li>
	<li class=""><a href="#"> <i class="fa fa-edit"></i> <span
			class="title">Cat&aacute;logos</span><span class="arrow"></span></a>
		<ul class="sub-menu">
			<li><a href="./ListaMaquinaria.html">Lista de Maquinaria y Equipo</a></li>
			<li><a href="./AltaMaquinaria.jsp">Alta de Maquinaria y Equipo</a></li>
			<li><a href="./ListaPersonas.html">Lista de Personas</a></li>
			<li><a href="./AltaPersona.jsp">Alta de Personal</a></li>
			<li><a href="./ListaCatPeronas.html">Lista Categoria de Personal</a></li>
			<li><a href="./AltaCatTipoPersona.jsp">Alta Categoria de Personal</a></li>
		</ul></li>
	
	  <li class=""><a href="iandex.html"> <i
			class="fa fa-power-off"></i> <span class="title">Cerrar
				Sesion</span></a>
		<ul class="sub-menu">
			<li><a href="javascript:cerrarsesion();"> <i class="fa fa-power-off"></i>
					Cerrar Sesion
			</a></li>
		</ul></li> 
   </ul>
	
	<div class="clearfix">
   </div>
    <!-- END SIDEBAR MENU --> 
  </div>
  </div>
  <a href="#" class="scrollup">Scroll</a>
   <div class="footer-widget">		
	Control de Avance de Obras 
  </div>
  <!-- END SIDEBAR --> 
  <!-- BEGIN PAGE CONTAINER-->
  <div class="page-content"> 
    <!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
    <div id="portlet-config" class="modal hide">
      <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button"></button>
        <h3>Widget Settings</h3>
      </div>
      <div class="modal-body"> Widget settings form goes here </div>
    </div>
    <div class="clearfix"></div>
    <div class="content"> 
        <div class="grid-body no-border"> <br>
                  <div class="row">
                    <div class="col-md-8 col-sm-8 col-xs-8">
			<br>
			<div class="page-title">
			<center><h3><b> Alta de Empresas</b></h3></center>	
		        </div>
			<br>
			
			<form action="<%= blobstoreService.createUploadUrl("/altaempresa") %>" method="post" enctype="multipart/form-data" id="formulario1" >
		
		       <div class="form-group">
                        <label class="form-label">RFC</label>
                    <!--    <span class="help">e.j. "RFC"</span> -->
		
                        <div class="controls">
                          <input type="text" class="form-control" id="rfc" name="rfc" onchange="validarRFC()" maxlength="13" >
                        </div>
                        <div id="errorRFC" style="color:red;"></div>
                      </div>
		   
		        <div class="form-group">
                        <label class="form-label">Nombre</label>
                      <!--  <span class="help">e.j. "M&eacute;xico - Puebla"</span>-->
                        <div class="controls">
                          <input type="text" class="form-control" id="nombre" name="nombre" maxlength="500">
                        </div>
                      </div>
			<div class="form-group">
                        <label class="form-label">Siglas</label>
                        <div class="controls">
                          <input type="text" class="form-control" id="siglas" name="siglas" maxlength="20">
                        </div>
                      </div>
             <div class="form-group">
                        <label class="form-label">IMSS</label>
                        <div class="controls">
                          <input type="text" class="form-control" id="imss" name="imss" maxlength="20">
                        </div>
                      </div>
			
			<div class="form-group">
                        <label class="form-label">Calle</label>
                        <div class="controls">
                          <input type="text" class="form-control" id="calle" name="calle" maxlength="180">
                        </div>
                      </div>
			<div class="form-group">
                        <label class="form-label">Num. Exterior</label>
                        <div class="controls">
                          <input type="text" class="form-control" id="num_ext" name="num_ext" maxlength="5">
                        </div>
                      </div>
			<div class="form-group">
                        <label class="form-label">Num. Interior</label>
                        <div class="controls">
                          <input type="text" class="form-control" id="num_int" name="num_int" maxlength="5">
                        </div>
                      </div>
			<div class="form-group">
                        <label class="form-label">Colonia</label>
                        <div class="controls">
                          <input type="text" class="form-control" id="colonia" name="colonia" maxlength="100">
                        </div>
                      </div>
			<div class="form-group">
                        <label class="form-label">Delegaci&oacute;n o municipio</label>
                        <div class="controls">
                          <input type="text" class="form-control" id="del_mun" name="del_mun" maxlength="100">
                        </div>
                      </div>
			<div class="form-group">
                        <label class="form-label">Entidad</label>
                        <div class="controls">
                          <select id="entidad" name="entidad" style="width: 100%">
										<option value="1">Aguascalientes</option>
										<option value="2">Baja California</option>
										<option value="3">Baja California Sur</option>
										<option value="4">Campeche</option>
										<option value="5">Chiapas</option>
										<option value="6">Chihuahua</option>
										<option value="7">Coahuila</option>
										<option value="8">Colima</option>
										<option value="9">Distrito Federal</option>
										<option value="10">Durango</option>
										<option value="11">Estado de M&eacute;xico</option>
										<option value="12">Guanajuato</option>
										<option value="13">Guerrero</option>
										<option value="14">Hidalgo</option>
										<option value="15">Jalisco</option>
										<option value="16">Michoac&aacute;n</option>
										<option value="17">Morelos</option>
										<option value="18">Nayarit</option>
										<option value="19">Nuevo Le&oacute;n</option>
										<option value="20">Oaxaca</option>
										<option value="21">Puebla</option>
										<option value="22">Quer&eacute;taro</option>
										<option value="23">Quintana Roo</option>
										<option value="24">San Luis Potos&iacute;</option>
										<option value="25">Sinaloa</option>
										<option value="26">Sonora</option>
										<option value="27">Tabasco</option>
										<option value="28">Tamaulipas</option>
										<option value="29">Tlaxcala</option>
										<option value="30">Veracruz</option>
										<option value="31">Yucat&aacute;n</option>
										<option value="32">Zacatecas</option>
									</select>
                        </div>
                      </div>
			<div class="form-group">
                        <label class="form-label">C&oacute;digo postal</label>
                        <div class="controls">
                          <input type="text" class="form-control" id="codigo_postal" name="codigo_postal" maxlength="5">
                        </div>
                      </div>
			<div class="form-group">
                        <label class="form-label">Tipo de empresa</label>                   
                        <div class="controls">
		    				<select id="empresa" name="empresa" style="width:100%"></select>
                        </div>
            </div>
		   <div class="fileUpload btn btn-primary" >
         				<label class="form-label">Subir imagen de Logotipo</label>
                       	<input class="upload" type="file"  name="myFile_altaempresa" accept="image/png, image/jpeg" id="myFile_altaempresa">
						<img id="blah" src="#" alt="your image" style="max-width: 160px; max-height: 120px; border: none;" />				        					
            </div> 
	
		
<!--  	   <div class="form-group">
                        <div class="controls">
                          <button class="btn btn-block btn-success "  id="mapaenviar"  OnClick="enviar();">Guardar</button><br>
                        </div>
                      </div>-->	
                      
           <div class="form-group" >
                        <div class="controls">
                          <input class="btn btn-block btn-success " type="submit" value="Guardar">
                        </div>
            </div> 
                     
           </form>         
  <!--                      
		   <div class="form-group">
			
                        <div class="controls">
                       
                        </div>
                      </div>-->
		   
		   
		  
		<!--   <img 
		   src="http://1-dot-focal-furnace-615.appspot.com/serve?blob-key=AMIfv96hHN_Kbirtn6gWVgAs-Anzddj5iuyX4BIXTCOsilVhbaop1XS9BHtXeDxw_nPXCzL_78AnO-LaNAXx2YMfB_wjk-iq3NYjIEzKafmO9IEOre5v20-M8yix1sS8gpj8yWHH7o4G8P2az_3xPAH0PkZA4cKoqgudUmdhXgT-QgvAmmfsJ4Y"
		   />
		-->
		
		   <br><br>
		    </div>
		    
		
		  </div>
		  
	    </div>
	 
    </div>
    <br><br><br><br>
  	<div class="footer-page">

					<img
						src="assets/img/conagua.jpg" width="15%" height="85%"
						style="margin-left:0%"></img>
					<img src="assets/img/semarnat.png" width="25%" height="100%"
						style="margin-left:10%;"></img> 
						<img
						src="assets/img/segob.jpeg" width="20%" height="85%"
						style="margin-left:10%;"></img> 
						

				</div>
  </div>

 
<!-- END CONTAINER --> 
<!-- BEGIN CHAT --> 
		<div id="sidr" class="chat-window-wrapper">
			<div id="main-chat-wrapper">
				<div class="chat-window-wrapper fadeIn" id="chat-users">
					<div class="chat-header">
						<div class="pull-left">
							<input type="text" placeholder="Buscar" id="searchTerm" onkeyup="doSearch()">
						</div>
						<div class="pull-right" style="margin: 5px">

							<button class="btn btn-block btn-success"
								style="margin-top: 10px">ok</button>
						</div>
					</div>
					<div class="side-widget">
						<div class="side-widget-title">Proyectos</div>
						<div class="side-widget-content">
							<div id="groups-list">
								<ul class="groups" id="proyectos_list">
								<!--	<li><a href="#"><div class="status-icon green"></div>CONAGUA</a></li>
									<li><a href="#"><div class="status-icon green"></div>SEGOB</a></li>  -->
								</ul>
							</div>
						</div>
					</div>
					<div class="side-widget">
						<div class="side-widget-title">Obras</div>
						<div class="side-widget-content">
							<div id="groups-list">
								<ul class="groups" id="obras_list">
								</ul>
							</div>
						</div>
					</div>
					<div class="side-widget fadeIn">
						<div class="side-widget-title">Contactos</div>
						<div id="favourites-list">
							<div class="side-widget-content">
								 <table id="regTable">
                               
                            	</table>
							
							</div>
						</div>
					</div>

				</div>

				<div class="chat-window-wrapper fadeIn" id="messages-wrapper"
					style="display: none">

					<div class="clearfix"></div>
					<div class="chat-messages-header">

						<a href="#" class="chat-back"><i class="icon-custom-cross"></i></a>
					</div>
					<div></div>
					<div class="chat-messages">

						<div class="user-details-wrapper " id="divPopupContent">
							<br> <br>
							
						</div>


						<div class="clearfix"></div>

					</div>
				</div>

			</div>
		</div>
<!-- END CHAT --> 
<!-- END CONTAINER -->
<div id="dialog-message" title="Mapa" > 
<div id="map-canvas"style="width:00px; height:00px" ></div>
</div>
<!-- BEGIN CORE JS FRAMEWORK--> 
<script src="assets/plugins/jquery-1.8.3.min.js" type="text/javascript"></script> 
<script src="assets/plugins/jquery-ui/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script> 
<script src="assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script> 
<script src="assets/plugins/breakpoints.js" type="text/javascript"></script> 
<script src="assets/plugins/jquery-unveil/jquery.unveil.min.js" type="text/javascript"></script> 
<script src="assets/plugins/jquery-block-ui/jqueryblockui.js" type="text/javascript"></script> 
<!-- END CORE JS FRAMEWORK --> 
<!-- BEGIN PAGE LEVEL JS --> 	
<script src="assets/plugins/jquery-slider/jquery.sidr.min.js" type="text/javascript"></script> 	
<script src="assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script> 
<script src="assets/plugins/pace/pace.min.js" type="text/javascript"></script>  
<script src="assets/plugins/jquery-numberAnimate/jquery.animateNumbers.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS --> 	

<!-- BEGIN CORE TEMPLATE JS --> 
<script src="assets/js/core.js" type="text/javascript"></script> 
<script src="assets/js/chat.js" type="text/javascript"></script> 
<script src="assets/js/demo.js" type="text/javascript"></script> 
<!-- END CORE TEMPLATE JS --> 
</body>

</html>