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
		PisosServicesRs.deletePiso({
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
	this.list = function(lista, idAgente) {
		$("#tblList").html("");
		$("#tblList").html(
				"<thead>" + "<tr>" + "<th>ID</th>" + "<th>ID Agente</th>"
						+ "<th>Precio</th>" + "<th>Direccion</th>"
						+ "<th>Ciudad</th>" + "<th>Estado</th>" + "</tr>"
						+ "</thead>" + "<tbody>" + "</tbody>");
		for ( var i in lista) {
			var piso = lista[i];
			if (idAgente == piso.idagente) {
				$("#tblList tbody").append(
						"<tr> <td>" + piso.id + "</td>" + "<td>"
								+ piso.idagente + "</td>" + "<td>"
								+ piso.precio + "</td>" + "<td>"
								+ piso.direccion + "</td>" + "<td>"
								+ piso.ciudad + "</td>" + "<td>" + piso.estado
								+ "</td></tr>");
			}
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
		var lista = that.model.tbPisos;
		var usuario = sessionStorage.getItem("user");
		var idAgente = parseInt(sessionStorage.getItem("id"));
		console.log(idAgente);

		// Repintamos la lista de alumnos.
		this.view.list(this.model.tbPisos, idAgente);
		// Vinculamos el controlador para movimiento de ratón sobre fila de
		// tabla
		this.bindHover();

		$("#frmImport")
				.on(
						"submit",
						function(event) {
							var URL = $("#urlImport").val();

							$
									.ajax({
										url : URL,
										type : "GET",
										dataType : "json",
										success : function(pisos) {
											tbPisos = that.model.tbPisos;
											alert("Respuesta recibida con exito. Espera unos instantes mientras se cargan los datos...");

											for ( var i in pisos) {
												var piso = JSON
														.stringify({
															id : pisos[i].ID,
															idagente : parseInt(sessionStorage.getItem("id")),
															precio : pisos[i].Precio,
															direccion : pisos[i].Direccion,
															ciudad : pisos[i].Ciudad,
															anyo : pisos[i].Anyo,
															estado : pisos[i].Estado,
															foto : pisos[i].Foto
																	.substr(1)
														});
												piso = JSON.parse(piso);
												var existe = false;
												for ( var i in tbPisos) {
													if (tbPisos[i].id == piso.id)
														existe = true;
												}
												if (existe)
													that.model.edit(piso);
												else
													that.model.add(piso);

											}
											that.model.load();
											that.view.list(that.model.tbPisos, parseInt(sessionStorage.getItem("id")));
											that.bindHover();
										}
									});
						});

		$("#btnReset").on("click", function(event) {
			var pisos = that.model.tbPisos;
			var i = pisos.length;
			i--;
			for (i; i >= 0; i--) {
				that.model.remove(pisos[i].id)
			}
			var agentes = AgentesServicesRs.getAgentes();
			var i = agentes.length;
			i--;
			for (i; i >= 0; i--) {
				AgentesServicesRs.deleteAgente({
					id : agentes[i].id
				});
			}

			var agente1 = new Object();
			agente1.login = "agente1";
			agente1.passwd = "clave1";

			var agente2 = new Object();
			agente2.login = "agente2";
			agente2.passwd = "clave2";

			var stringAgente1 = JSON.stringify(agente1);
			var stringAgente2 = JSON.stringify(agente2);

			AgentesServicesRs.saveAgente({
				$entity : stringAgente1,
				$contentType : "application/json"
			});

			AgentesServicesRs.saveAgente({
				$entity : stringAgente2,
				$contentType : "application/json"
			});
			
			var user = sessionStorage.getItem("user");
			var passwd = sessionStorage.getItem("passwd");
			agentes = AgentesServicesRs.getAgentes();
			var id;
			for (i in agentes) {
				if (agentes[i].user = user) id = agentes[i].id;
			}
			
			console.log(user + " " + passwd + " " + id);
			
			if (typeof (Storage) !== "undefined") {
				sessionStorage.clear();
				console.log("Datos de usuario borrados del navegador");
			}

			sessionStorage.setItem("user", user);
			sessionStorage.setItem("passwd", passwd);
			sessionStorage.setItem("id", id);
			
			that.model.load();
			that.view.list(that.model.tbPisos, parseInt(sessionStorage.getItem("id")));
			that.bindHover();
		});

	}

	this.bindHover = function() {
		// Controlador del ratón para mostrar fotos de pisos
		// Tiene su propia función porque hay que llamarlo después de pulsar
		// cada botón
		$("tr").not(':first').hover(
				function() {
					var nombreFoto = that.model.find(that.view
							.getIdPiso($(this))).foto;
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