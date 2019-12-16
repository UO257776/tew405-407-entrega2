//Clase que contiene el Modelo de la aplicación.
function Model() {
	this.lAgentes = null;

	this.load = function() {
		if(typeof(Storage) !== "undefined") {
			if(sessionStorage.getItem("user") == null) {
				console.log("No user");
				document.getElementById("login-link").style.visibility = "visible";
				document.getElementById("logout-link").style.visibility = "hidden";
			}
			else {
				console.log("With user");
				document.getElementById("login-link").style.visibility = "hidden";
				document.getElementById("logout-link").style.visibility = "visible";
			}
		}
	}

}

function View() {
	
}

function Controller(varmodel, varview) {
	// Definimos una copia de this para evitar el conflicto con el this (del
	// objeto que recibe el evento)
	// en los manejadores de eventos
	var that = this;
	// referencia al modelo
	this.model = varmodel;
	// refefencia a la vista
	this.view = varview;
	// Funcion de inicialización para cargar modelo y vista, definición de
	// manejadores
	this.init = function() {
		this.model.load();

		$("#logout-link").click(function(event) {	
			if(typeof(Storage) !== "undefined") {
					sessionStorage.clear();
					console.log("Datos de usuario borrados del navegador");
			}
		});
		
		$("#altaPiso").click(function (event) {	
			if(typeof(Storage) !== "undefined") {
					console.log("Acceso alta - user");
					console.log(sessionStorage.getItem("user"));
					if(sessionStorage.getItem("user") == null) {
						console.log("Acceso alta denegado");
						alert("Se requiere ser un agente registrado");
						window.location.href = "index.html";
						
					} else {
						console.log("Acceso alta permitido");
						window.location.href = "altaPiso.html";
					}
			}
		});
		
	}
}


$(function() {
	// Creamos el modelo con los datos y la conexión al servicio web.
	var model = new Model();
	// Creamos la vista que incluye acceso al modelo.
	var view = new View();
	// Creamos el controlador
	var control = new Controller(model, view);
	// Iniciamos la aplicación
	control.init();
});
