//Clase que contiene el Modelo de la aplicación.
function Model() {
	this.lAgentes = null;

	this.load = function() {
		if(typeof(Storage) !== "undefined") {
			if(sesssionStorage.getItem("user") == null) {
				this.getElementById("logout-link").style.visibility = "hidden";
			}
			else {
				this.getElementById("login-link").style.visibility = "hidden";
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
					sesssionStorage.clear();
					console.log("Datos de usuario borrados del navegador");
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