//Clase que contiene el Modelo de la aplicación.
function Model() {
	console.log("Test");
	// Lista de agentes.
	this.lAgentes = null;

	this.load = function() {
		console.log("getAgentes");
		this.lAgentes = AgentesServicesRs.getAgentes();
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
		console.log("test_preloading");
		this.model.load();
		console.log("test_postload");

		$("#frmlogin").on("submit", function(event) {	
			var user = $("#username").val();
			var password = $("#passwd").val();
			console.log("Submit");
			//that.model.mLogin(user, password);
			console.log("Metodo");

			var lista = that.model.lAgentes;
			console.log("Lista");
			var i=0;
			var notfound = true;
			console.log("Inicio While");
			while ( i < lista.length && notfound) {
				console.log(user);
				console.log(lista[i].login);
				console.log(lista[i].passwd);
				if (lista[i].login == user && lista[i].passwd == password) {
					console.log("ENCONTRADO");
					notfound = false;
					if(typeof(Storage) !== "undefined") {
						sessionStorage.setItem("user", user);
						sessionStorage.setItem("passwd", password);
						window.location.href = "index.html";
					}
					
					
				}
				i++;
			}
			console.log("Fin While");
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
	console.log("lambda");
	control.init();
});
