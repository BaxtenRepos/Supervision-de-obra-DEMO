/**
 * 

 */
function envia(){
	
	$.ajax({
		url : '/envia',
		type : 'post',
		data : {'objectJson' : JSON.stringify('')},
		success: function(data){
			//alert("todo bn");
			var elementos = [];
			
			for (var i = 0; i < data.length; i++) {
				var option = document.createElement("option");
				option.text = data[i];
				x.add(option);
				
			}

		},
		error: function(jHR,e,throwsError){
			alert(e);
		}
			
});
}