package it.salone.presentation;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import it.salone.business.AppuntamentoEjb;

@WebServlet("/aggiornaStorico")
public class AggiornaStoricoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	AppuntamentoEjb appuntamentoEjb;

	public AggiornaStoricoServlet() {

	}

	public void init(ServletConfig config) throws ServletException {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		appuntamentoEjb.aggiornaStorico();

		request.getRequestDispatcher("/stampaAppuntamenti?vengo=stampa").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
