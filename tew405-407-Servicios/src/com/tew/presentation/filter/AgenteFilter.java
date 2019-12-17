package com.tew.presentation.filter;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tew.model.User;

/**
 * Servlet Filter implementation class AgenteFilter
 */
@WebFilter(dispatcherTypes = { DispatcherType.REQUEST }, description = "Filtro para los agentes", urlPatterns = {
		"/faces/agente/*", "/agente/*" }, initParams = {
				@WebInitParam(name = "LoginParam", value = "/faces/index.xhtml", description = "Página de logeo"),
				@WebInitParam(name = "HomeParam", value = "/faces/cliente/opcionesCliente.xhtml", description = "Página principal") })
public class AgenteFilter implements Filter {

	// Necesitamos acceder a los parámetros de inicialización en
	// el método doFilter por lo que necesitamos la variable
	// config que se inicializará en init()
	FilterConfig config = null;

	/**
	 * Default constructor.
	 */
	public AgenteFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// Si no es petición HTTP nada que hacer
		if (!(request instanceof HttpServletRequest)) {
			chain.doFilter(request, response);
			return;
		}
		// En el resto de casos se verifica que se haya hecho login previamente
		// y que el rol de usuario sea agente
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		if (session.getAttribute("LOGGEDIN_USER") == null) {
			String loginForm = config.getInitParameter("LoginParam");
			// Si no hay login, redirección al formulario de login
			res.sendRedirect(req.getContextPath() + loginForm);
			return;
		}
		if (!((User) session.getAttribute("LOGGEDIN_USER")).getRole().equals("agente")) {
			String homePage = config.getInitParameter("HomeParam");
			// Si el rol es de cliente, se redirecciona a la página de opciones
			res.sendRedirect(req.getContextPath() + homePage);
			return;
		}

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		// Iniciamos la variable de instancia config
		config = fConfig;
	}

}
