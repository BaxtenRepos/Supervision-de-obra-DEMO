$(document).on('ready', function(){

	function moveSlider(e){
		e.preventDefault();

		var pos 	= $(e.currentTarget).offset()
		,	posX	= e.pageX - pos.left
		,	value	= posX*52/$(e.currentTarget).outerWidth();

		if(posX >= 0 && posX <= $(e.currentTarget).outerWidth()){

			$('.slider > .progress').css('width', posX+'px');
			$('.slider > .indicator').css('left', posX+'px');

			$('#valueSlider').val(Math.round(value ));

		}else{

		}
	}

	$('.slider').on('mousedown', function(e){

		moveSlider(e);

		$(this).on('mousemove', function(e){
			moveSlider(e);
		});

	}).on('mouseup', function(){
		$(this).off('mousemove');
	});

});
// -----------------------------------------------------------------
$(document).on('ready', function(){

	function moveSlider(e){
		e.preventDefault();

		var pos 	= $(e.currentTarget).offset()
		,	posX	= e.pageX - pos.left
		,	value	= posX*51/$(e.currentTarget).outerWidth();

		if(posX >= 0 && posX <= $(e.currentTarget).outerWidth()){

			$('.slider2 > .progress2').css('width', posX+'px');
			$('.slider2 > .indicator2').css('left', posX+'px');

			$('#valueSlider2').val(Math.round(value));

		}
	}

	$('.slider2').on('mousedown', function(e){

		moveSlider(e);

		$(this).on('mousemove', function(e){
			moveSlider(e);
		});

	}).on('mouseup', function(){
		$(this).off('mousemove');
	});

});
function desvioFisico(){
	var alertaFisica = document.getElementById("valueSlider").value;
	return alertaFisica;
}
function desvioFinanciero(){
	var alertaFinanciero = document.getElementById("valueSlider2").value;
	return alertaFinanciero;
}