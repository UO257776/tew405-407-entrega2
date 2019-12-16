//Clase que contiene el Modelo de la aplicación.
function Model() {
	// Lista de pisos.
	this.tbPisos = null;

	// Carga los datos del servicio sobreescribiendo el dato this.tbPisos.
	this.load = function() {
		this.tbPisos = PisosServicesRs.getPisos();
	}

	// Llamamos al servicio de alta de piso
	this.add = function(id, idagente, precio, direccion, ciudad, año, estado, foto) {
		// Llamamos al servicio de alta de piso
		PisosServicesRs.savePiso(new Piso(id, idagente, precio, direccion, ciudad, año, estado, foto));
		};
		// Recargamos la lista de pisos.
		this.load();
	
};
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


		$("#frmAlta").on("submit", function(event) {	
			var id = $("#id-piso").val();
			var idagente = $("#id-agente").val();
			var precio = $("#precio").val();
			var direccion = $("#direccion").val();
			var ciudad = $("#ciudad").val();
			var año = $("#año").val();
			var estado = $("#estado").val();
			var foto = $("#foto").val();
			that.model.add(id, idagente, precio, direccion, ciudad, año, estado, foto);
			
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
