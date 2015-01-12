/**
 * 
 */


function OrdenaEmpresas(lista){  //verificar que tengan los mismos elementos todos los arreglos de empresas
	
	var ordenado = new Array();
	var nombres =  new Array();
	
	

	var by = function (atributo, menor){
		return function (o, p){
		var a, b;
		if (typeof o === "object" && typeof p === "object" && o && p ){
		a = o[atributo];
		b = p[atributo];
		if (a === b ){
		return typeof menor === 'function' ? menor(o, p) : 0;
		}
		if (typeof a === typeof b){
		return a < b ? -1 : 1;
		}
		return typeof a < typeof b ? -1 : 1;
		}else{
		throw{
		name : "Error",
		message : "Esto no es un objeto, al menos no tiene la propiedad " + atributo;
		};
		}
		};
		};
		lista.sort(by("nombre"));
	
	
}