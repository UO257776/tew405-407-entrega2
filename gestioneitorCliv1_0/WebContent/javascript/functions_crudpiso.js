//Clase que contiene el Modelo de la aplicación.
function Model() {
	// Lista de pisos.
	this.tbPisos = null;

	// Carga los datos del servicio sobreescribiendo el dato this.tbPisos.
	this.load = function() {
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

	// Actualización de un piso existente
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

// Clase que contiene la gestión de la capa Vista
function View() {
	this.list = function(lista) {
		$("#tblList").html("");
		$("#tblList").html(
				"<thead>" + "<tr>" + "<th>ID</th>" + "<th>Precio</th>"
						+ "<th>Direccion</th>" + "<th>Ciudad</th>"
						+ "<th>Estado</th>" + "</tr>" + "</thead>" + "<tbody>"
						+ "</tbody>");
		for ( var i in lista) {
			var piso = lista[i];
			$("#tblList tbody").append(
					"<tr> <td>" + piso.id + "</td>" + "<td>" + piso.precio
							+ "</td>" + "<td>" + piso.direccion + "</td>"
							+ "<td>" + piso.ciudad + "</td>" + "<td>"
							+ piso.estado + "</td></tr>");
		}
	}

	this.getIdPiso = function(celda) {
		// Accedemos a la fila que está por encima de esta celda
		// (closest('tr'))y despues obtenemos todas las celdas de esa fila
		// (find('tr')) y
		// nos quedamos con la segunda (get(1)) que es la contiene el "id" del
		// alumno.
		var id_piso = parseInt(celda.closest('tr').find('td').get(0).innerHTML);
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
		//Vinculamos el controlador para movimiento de ratón sobre fila de tabla
		this.bindHover();
		
		// Controlador del botón de filtrado por ciudad
		$("#frmFiltrado").on("submit", function(event) {
			that.model.load();
			var lista = that.model.tbPisos;
			var cadena = $("#filtroCiudad").val();
			if (cadena != "") {
				var i = lista.length - 1;
				while (i >= 0) {
					if (!lista[i].ciudad.includes(cadena)) {
						lista.splice(i, 1);
					}
					i--;
				}
				that.model.tbPisos = lista;
			}
			that.view.list(that.model.tbPisos);
			that.bindHover();
		});

		// Controlador del botón de ordenamiento por precio
		$("#frmOrden").on("submit", function(event) {
			var ascendente = $("#precioAscendente").is(':checked');
			var lista = that.model.tbPisos;
			if (ascendente) {
				for ( var i in lista) {
					var j = i;
					j++;
					while (j < lista.length) {
						if (lista[i].precio > lista[j].precio) {
							var temp = lista[i];
							lista[i] = lista[j];
							lista[j] = temp;
						}
						j++;
					}
				}
			} else {
				for ( var i in lista) {
					var j = i;
					j++;
					while (j < lista.length) {
						if (lista[i].precio < lista[j].precio) {
							var temp = lista[i];
							lista[i] = lista[j];
							lista[j] = temp;
						}
						j++;
					}
				}
			}
			that.model.tbPisos = lista;
			that.view.list(that.model.tbPisos);
			that.bindHover();
		});

	}
	
	this.bindHover = function() {
		//Controlador del ratón para mostrar fotos de pisos
		//Tiene su propia función porque hay que llamarlo después de pulsar cada botón
		$("tr").not(':first').hover(function() {
			var nombreFoto = that.model.find(that.view.getIdPiso($(this))).foto;
			console.log(foto);
			var foto = new Image();
			foto.src = nombreFoto
			var canvas = $("#foto");
			var ctx = canvas[0].getContext("2d");
			ctx.drawImage(foto, 0, 0, 250, 250);
			
			$(this).css("background", "grey");
		}, function() {
			$(this).css("background", "");
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