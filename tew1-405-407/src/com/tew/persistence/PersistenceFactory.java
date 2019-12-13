package com.tew.persistence;


/**
 * Interfaz de la factoria que suministra implementaciones reales de la fachada 
 * de persistencia para cada Entidad del Modelo (en este caso solo hay 
 * una: Alumno; pero en futuras versiones habr�� m��s)
 *  
 * @author alb
 *
 */
public interface PersistenceFactory {
	
	AgenteDao createAgenteDao();
	ClienteDao createClienteDao();
	PisoDao createPisoDao();
	PisoParaVisitarDao createPisoParaVisitarDao();
	
	// ... otros m��todos factoria para Daos de otras entidades del modelo ...
}

