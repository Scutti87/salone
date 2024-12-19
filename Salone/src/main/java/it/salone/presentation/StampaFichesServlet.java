package it.salone.presentation;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import it.salone.Enum.RicercaBy;
import it.salone.business.RicevutaEjb;
import it.salone.model.Ricevuta;

@WebServlet("/stampaRicevute")
public class StampaFichesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	RicevutaEjb ricevutaEjb;
	
	public StampaFichesServlet() {
	}

	public void init(ServletConfig config) throws ServletException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Ricevuta> listaRicevute = ricevutaEjb.getRicevuteBy(RicercaBy.ALL, "", "");
		request.setAttribute("listaRicevute", listaRicevute);
		request.getRequestDispatcher("html/stampaRicevute.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
