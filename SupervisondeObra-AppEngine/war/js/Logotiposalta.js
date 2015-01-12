

function init() {
	var x = document.getElementById("altaLogotipo_proyectoselect");
	var peticion = 'peticion';
	
	$.ajax({
		url : '/altalogotipo',
		type : 'post',
		data : {
			'objectJson' : JSON.stringify(peticion)
		},
		success : function(data) {
			// alert("todo bn");
			var option = document.createElement("option");
			option.text = "Seleccione un proyecto";
			option.value = 0;
			x.add(option);
			for (var i = 0; i < data.length; i++) {
				var option = document.createElement("option");
				option.text = data[i].nombre;
				option.value = data[i].id;
				x.add(option);
			}
		},
		error : function(jHR, e, throwsError) {
			alert(e);
		}
	});
}


function readURL_secretaria(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#img_secretaria').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}
function readURL_dependencia(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#img_dependencia').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}
function readURL_iuyet(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#img_iuyet').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}
function readURL_adicional(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#img_adicional').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}
$(function () {
	$("#myFile_secretaria").change(function(){
		readURL_secretaria(this);
	});
});
$(function () {
	$("#myFile_dependencia").change(function(){
	    readURL_dependencia(this);
	});
});
$(function () {
	$("#myFile_iuyet").change(function(){
	    readURL_iuyet(this);
	});
});
$(function () {
	$("#myFile_adicional").change(function(){
	    readURL_adicional(this);
	});
});