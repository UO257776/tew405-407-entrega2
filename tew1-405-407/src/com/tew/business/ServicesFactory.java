package com.tew.business;

public interface ServicesFactory {
	
	AgentesService createAgentesService();
	ClientesService createClientesService();
	PisosService createPisosService();
	PisosParaVisitarService createPisosParaVisitarService();
	LoginService createLoginService();
	ResetService createResetService();
}
