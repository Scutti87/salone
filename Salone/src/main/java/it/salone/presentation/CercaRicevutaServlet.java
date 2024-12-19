package it.salone.presentation;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.salone.Enum.RicercaBy;
import it.salone.business.RicevutaEjb;
import it.salone.model.Ricevuta;

@WebServlet("/cercaRicevuta")
public class CercaRicevutaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	RicevutaEjb ricevutaEjb;
    
    public CercaRicevutaServlet() {
      
    }

	
	public void init(ServletConfig config) throws ServletException {

	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String vengo = request.getParameter("vengo");
		List<Ricevuta> listaRicevute = null;
		
		if (vengo.equals("cercaRicevuta")) {
			
			String data = request.getParameter("data");
			HttpSession session = request.getSession();
			String clienteId = (String) session.getAttribute("clienteId");

			if (!data.isBlank() && clienteId != null) {
				listaRicevute = ricevutaEjb.getRicevuteBy(RicercaBy.DATACLIENTE, data, clienteId);
			} else if (data.isBlank() && clienteId != null) {
				listaRicevute = ricevutaEjb.getRicevuteBy(RicercaBy.IDCLIENTE, "", clienteId);
			} else if (clienteId == null && !data.isBlank()) {
				listaRicevute = ricevutaEjb.getRicevuteBy(RicercaBy.DATA, data, "");
			} else {
				listaRicevute = ricevutaEjb.getRicevuteBy(RicercaBy.ALL, "", "");
			}
			
		}
		
		if (vengo.equals("btnCerca")) {
			String nome = request.getParameter("nome");	
			listaRicevute = ricevutaEjb.getRicevuteBy(RicercaBy.NOME, nome, "");
			
		}
		
		request.setAttribute("listaRicevute", listaRicevute);
		request.getRequestDispatcher("html/stampaRicevute.jsp").forward(request, response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
