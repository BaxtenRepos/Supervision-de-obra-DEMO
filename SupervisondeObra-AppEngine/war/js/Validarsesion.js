
function valida(){
	var entra;
		$.ajax({
			url : '/validarsesion',
			type : 'post',
			data : {'objectJson' : JSON.stringify('validasesion')},
			success: function(data){
				if(data)
				{
					document.getElementById("fotoUsuario").src = data.fotografia;
					document.getElementById("fotoUsuario2").src = data.fotografia;
					document.getElementById("username").innerHTML = data.nombre+"<br>"+data.apellidos+"<br>";
					document.getElementById("emailUsr").innerHTML = "<span class='badge badge-important'>"+2+"</span> <span class='bold'>"+data.email+"</span>";					
					init();
					iniciadirectorio();
				}else{ 
					location.href="./index.html";		
				}
			},
			error: function(jHR,e,throwsError){
				alert(e);
			}		
	});
}

function cerrarsesion(){
	var entra;
		$.ajax({
			url : '/validarsesion',
			type : 'post',
			data : {'objectJson' : JSON.stringify('cerrarsesion')},
			success: function(data){
				if(data){
					location.href="./index.html";	
				}
			},
			error: function(jHR,e,throwsError){
				alert(e);
			}		
	});
}