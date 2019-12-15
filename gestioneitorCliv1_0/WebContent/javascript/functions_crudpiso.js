//Clase que contiene el Modelo de la aplicación.
function Model() {
	// Lista de pisos.
	this.tbPisos = null;

	// Carga los datos del servicio sobreescribiendo el dato this.tbPisos.
	this.load = function() {
		console.log("b")
		this.tbPisos = PisosServicesRs.getPisos();
	}

	// Llamamos al servicio de alta de piso
	this.add = function(piso) {
		// Llamamos al servicio de alta de piso
		PisosServicesRs.savePiso({
			$entity : piso,
			$contentType : "application/json"
		});
		// Recargamos la lista de pisos.
		this.load();
	}

	// Actualización de un piso existente: PENDIENTE DE IMPLEMENTAR
	this.edit = function(piso) {
		// Llamamos al servicio de actualización de piso
		PisosServicesRs.updatePiso({
			$entity : piso,
			$contentType : "application/json"
		});
		// Recargamos la lista de pisos.
		this.load();
	}

	// Eliminación un piso existente
	this.remove = function(id_piso) {
		// Llamamos al servicio de borrado de piso
		PisoServicesRs.deletePiso({
			id : id_piso
		});
		// Recargamos la lista de pisos.
		this.load();
	}

	this.find = function(id_piso) {
		function checkPiso(obj) {
			return obj.id == id_piso;
		}
		// Buscamos los datos del piso cuyo id_piso sea el mismo que el
		// seleccionado
		var piso = this.tbPisos.find(checkPiso);
		return piso;
	}
};

//Clase que contiene la gestión de la capa Vista
function View() {
	this.list = function(lista) {
		$("#tblList").html("");
		$("#tblList").html(
				"<thead>" + "<tr>" + "<th></th>" + "<th>ID</th>"
						+ "<th>Precio</th>" + "<th>Dirección</th>"
						+ "<th>Ciudad</th>" + "<th>Estado</th>" + "</tr>"
						+ "</thead>" + "<tbody>" + "</tbody>");
		for ( var i in lista) {
			var piso = lista[i];
			$("#tblList tbody")
					.append(
							"<tr> <td>"
									+ "<img src='icons/edit.png' class='btnEdit'/>"
									+ "<img src='icons/delete.png' class='btnDelete'/> </td>"
									+ "<td>" + piso.id + "</td>" + "<td>"
									+ piso.precio + "</td>" + "<td>"
									+ piso.direccion + "</td>" + "<td>"
									+ piso.ciudad + "</td>" + "<td>"
									+ piso.estado + "</td></tr>");
		}
	}

	this.getIdPiso = function(celda) {
		// Accedemos a la fila que está por encima de esta celda
		// (closest('tr'))y despues obtenemos todas las celdas de esa fila
		// (find('tr')) y
		// nos quedamos con la segunda (get(1)) que es la contiene el "id" del
		// alumno.
		var id_piso = parseInt(celda.closest('tr').find('td').get(1).innerHTML);
		return id_piso;
	}

};

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
		// Cargamos la lista de alumnos del servicio
		this.model.load();
		// Repintamos la lista de alumnos.
		this.view.list(this.model.tbPisos);

		// Manejador del enlace de edición de un alumno en la tabla
		$("#tblList").on("click", ".btnEdit",
		// Método que gestiona el evento de clickar en el evento
		function(event) {
			// Obtenemos el id del alumno seleccionado mediante el icono de
			// edición
			var id_piso = that.view.getIdPiso($(this));
			// Obtenemos el alumno con el id_alumno
			var piso = that.model.find(id_piso);
			// Cargamos el formulario con los datos del alumno seleccionado
			// para
			// editar
			that.view.loadPisoInForm(piso);
		});

		$("#tblList").on("click", ".btnReset", function(event) {
			that.view.list();
		});
	}
};

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