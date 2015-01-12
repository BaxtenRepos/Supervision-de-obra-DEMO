/**
 * 
 */


		
var tipwidth='150px' //default tooltip width
var tipbgcolor='lightyellow'  //tooltip bgcolor
var disappeardelay=250  //tooltip disappear speed onMouseout (in miliseconds)
var vertical_offset="0px" //horizontal offset of tooltip from anchor link
var horizontal_offset="-3px" //horizontal offset of tooltip from anchor link

/////No further editting needed

var ie4=document.all
var ns6=document.getElementById&&!document.all

if (ie4||ns6)
document.write('<div id="fixedtipdiv" style="visibility:hidden;width:'+tipwidth+';background-color:'+tipbgcolor+'" ></div>')

function getposOffset(what, offsettype){
var totaloffset=(offsettype=="left")? what.offsetLeft : what.offsetTop;
var parentEl=what.offsetParent;
while (parentEl!=null){
totaloffset=(offsettype=="left")? totaloffset+parentEl.offsetLeft : totaloffset+parentEl.offsetTop;
parentEl=parentEl.offsetParent;
}
return totaloffset;
}


function showhide(obj, e, visible, hidden, tipwidth){
if (ie4||ns6)
dropmenuobj.style.left=dropmenuobj.style.top=-500
if (tipwidth!=""){
dropmenuobj.widthobj=dropmenuobj.style
dropmenuobj.widthobj.width=tipwidth
}
if (e.type=="click" && obj.visibility==hidden || e.type=="mouseover")
obj.visibility=visible
else if (e.type=="click")
obj.visibility=hidden
}

function iecompattest(){
return (document.compatMode && document.compatMode!="BackCompat")? document.documentElement : document.body
}

function clearbrowseredge(obj, whichedge){
var edgeoffset=(whichedge=="rightedge")? parseInt(horizontal_offset)*-1 : parseInt(vertical_offset)*-1
if (whichedge=="rightedge"){
var windowedge=ie4 && !window.opera? iecompattest().scrollLeft+iecompattest().clientWidth-15 : window.pageXOffset+window.innerWidth-15
dropmenuobj.contentmeasure=dropmenuobj.offsetWidth
if (windowedge-dropmenuobj.x < dropmenuobj.contentmeasure)
edgeoffset=dropmenuobj.contentmeasure-obj.offsetWidth
}
else{
var windowedge=ie4 && !window.opera? iecompattest().scrollTop+iecompattest().clientHeight-15 : window.pageYOffset+window.innerHeight-18
dropmenuobj.contentmeasure=dropmenuobj.offsetHeight
if (windowedge-dropmenuobj.y < dropmenuobj.contentmeasure)
edgeoffset=dropmenuobj.contentmeasure+obj.offsetHeight
}
return edgeoffset
}

function fixedtooltip(menucontents, obj, e, tipwidth){
if (window.event) event.cancelBubble=true
else if (e.stopPropagation) e.stopPropagation()
clearhidetip()
dropmenuobj=document.getElementById? document.getElementById("fixedtipdiv") : fixedtipdiv
dropmenuobj.innerHTML=menucontents

if (ie4||ns6){
showhide(dropmenuobj.style, e, "visible", "hidden", tipwidth)
dropmenuobj.x=getposOffset(obj, "left")
dropmenuobj.y=getposOffset(obj, "top")
dropmenuobj.style.left=dropmenuobj.x-clearbrowseredge(obj, "rightedge")+"px"
dropmenuobj.style.top=dropmenuobj.y-clearbrowseredge(obj, "bottomedge")+obj.offsetHeight+"px"
}
}

function hidetip(e){
if (typeof dropmenuobj!="undefined"){
if (ie4||ns6)
dropmenuobj.style.visibility="hidden"
}
}

function delayhidetip(){
if (ie4||ns6)
delayhide=setTimeout("hidetip()",disappeardelay)
}

function clearhidetip(){
if (typeof delayhide!="undefined")
clearTimeout(delayhide)
}



//<a href="http://www.javascriptkit.com" onMouseover="fixedtooltip('Comprehensive JavaScript tutorials and over 400+ <b>free</b> scripts.', this, event, '150px')" onMouseout="delayhidetip()">JavaScript Kit</a> |

//<a href="http://www.codingforums.com" onMouseover="fixedtooltip('Web coding and development forums. This tooltip has an apostrophe- I\'m here.', this, event, '200px')" onMouseout="delayhidetip()">CodingForums.co//m</a>





function init(){
		var x = document.getElementById("bodyTable");
		var peticion = 'peticion';
		var linkCelda = "./Conceptoedit.html";
			$.ajax({
				url : '/listaconceptos',
				type : 'post',
				data : {'objectJson' : JSON.stringify(peticion)},
				success: function(data)
				{
					for (var i = 0; i < data.length; i++) 
					{
						var checkbox = document.createElement("input");
						checkbox.type = "checkbox";    // make the element a checkbox
						checkbox.name = "checkbox";      // give it a name we can check on the server side
						checkbox.value = data[i].id_Concepto;
						
						var tr = document.createElement("tr");
						//var td1 = document.createElement("td");
						var td2 = document.createElement("td");
						var td3 = document.createElement("td");
						var td4 = document.createElement("td");
						var td5 = document.createElement("td");
						var td6 = document.createElement("td");
						var td7 = document.createElement("td");
						var td8 = document.createElement("td");
						var td9 = document.createElement("td");
						var td10 = document.createElement("td");
						var td11 = document.createElement("td");
						td11.value = data[i].padre;
						
					//	if(data[i].id_Concepto) td1.innerHTML = data[i].id_Concepto;
						//var description = data[i].descripcion;
						var res = data[i].descripcion.slice(0, 100);
						var re=data[i].descripcion;
						if(re.indexOf('"') > -1)
							var descripcion = data[i].descripcion.replace(/["']/g, "");
							else
								var descripcion = data[i].descripcion;
						
//var res = str.replace("Microsoft", "W3Schools");
							//
						if(data[i].id_Concepto && data[i].descripcion)td2.innerHTML ="<a href="+linkCelda+"?idConcepto="+data[i].id_Concepto+" onMouseover=\"fixedtooltip('"+descripcion+"', this, event, '500px')\" onMouseout=\"delayhidetip()\">"+res+"..."+"</a>";
							//"<a href="+linkCelda+"?idConcepto="+data[i].id_Concepto+">"+res+"..."+"</a>";
						if(data[i].clave)td3.innerHTML = data[i].clave;
						if(data[i].obra)td4.innerHTML = data[i].obra;
						if(data[i].medida)td5.innerHTML = data[i].medida;
						if(data[i].cantidadtotal)td6.innerHTML = data[i].cantidadtotal;
						if(data[i].preciounitario)td7.innerHTML = accounting.formatMoney(data[i].preciounitario);
						
						
						if( data[i].importe)td8.innerHTML = accounting.formatMoney(data[i].importe);
		
						if(data[i].fechainicio)td9.innerHTML = data[i].fechainicio;
						if(data[i].fechafin)td10.innerHTML = data[i].fechafin;
						td11.appendChild(checkbox);
						
						tr.appendChild(td11);
					//	tr.appendChild(td1);
						tr.appendChild(td2);
						tr.appendChild(td3);
						tr.appendChild(td4);
						tr.appendChild(td5);
						tr.appendChild(td6);
						tr.appendChild(td7);
						tr.appendChild(td8);
						tr.appendChild(td9);
						tr.appendChild(td10);
						
						x.appendChild(tr);
					}
					$(document).ready(function() {
						var table = $('#exampleTable').DataTable();
						$('#exampleTable tbody').on( 'click', 'tr', function () {
							if ( $(this).hasClass('selected') ) {
								$(this).removeClass('selected');
							}
							else {
								table.$('tr.selected').removeClass('selected');
								$(this).addClass('selected');
							}
						} );
						
						$('#exampleTable tbody').on( 'click', 'tr', function () {
//							var row = $(this);
							 if ($(this).find('td').val() == 0) {
								 if($(this).find('input[type="checkbox"]').is(':checked')){
									 var idpadre = $(this).find('input[type="checkbox"]').val();
									 $('#exampleTable').find('tr').each(function () {
										 var row2 = $(this);
										 if(row2.find('td').val() == idpadre){
											 row2.find('input[type="checkbox"]').attr ( 'checked' , true );
										 }
									 });
								 }else{
									 var idpadre = $(this).find('input[type="checkbox"]').val();
									 $('#exampleTable').find('tr').each(function () {
										 var row2 = $(this);
										 if(row2.find('td').val() == idpadre){
											 row2.find('input[type="checkbox"]').attr ( 'checked' , false );
										 }
									 });
								 }
							 }
						});

					} );
				},
				error: function(jHR,e,throwsError){
					alert(e);
				}		
		});
}
 
 var conceptosborrar= new Array();

 $(function () {
	 conceptosborrar=[];
 	$('#borrar').click(function () {
 	    $('#exampleTable').find('tr').each(function () {
 	        var row = $(this);
 	        if (row.find('input[type="checkbox"]').is(':checked')) {
 	        	var object = {
 	        	 		'id' : row.find('input[type="checkbox"]').val(),
 	        	 		'index' : row.find('td').val()
 	        	 	};
 	        	conceptosborrar.push(object);
 	        }
 	    });
 	    borrar();
 	});
 });

 function borrar() {
 	
	
 	var object = {
 		'borrar' : conceptosborrar
 	};
 	$.ajax({
 		url : '/listaconceptos',
 		type : 'post',
 		data : {
 			'objectJson' : JSON.stringify(object)
 		},
 		success : function(data) {
 			
 			if(data.length != 0){
 				$('#exampleTable').find('tr').each(function () {
						var table = $('#exampleTable').DataTable();
	 		 	        var fila = $(this);
	 		 	        if (fila.find('input[type="checkbox"]').is(':checked') && data.contains(fila.find('input[type="checkbox"]').val())) {
		 	        		table.row(fila.index()).remove().draw(false);
	 		 	        }
	 		 	    });
 				
// 				var cont = 0;
// 				for (var i = 0; i < data.length; i++) {
// 					table.row(data[i].index-cont).remove().draw( false );
// 					cont++;
//				}
 				conceptosborrar.length = 0;
 			}else{
 				conceptosborrar.length = 0;
 				alert("Lo conceptos no se pueden eliminar por que contienen avances.");
 			}
 		},
 		error : function(jHR, e, throwsError) {
 			conceptosborrar.length = 0;
 			alert(e);
// 			window.location.reload();
 		}

 	});
 }
 

 
 Array.prototype.contains = function(obj) {
	    var i = this.length;
	    while (i--) {
	        if (this[i] == obj) {
	            return true;
	        }
	    }
	    return false;
	}
 

