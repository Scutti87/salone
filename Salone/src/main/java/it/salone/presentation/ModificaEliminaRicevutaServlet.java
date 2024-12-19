package it.salone.presentation;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import it.salone.Enum.RicercaBy;
import it.salone.Enum.Servizi;
import it.salone.business.ClienteEjb;
import it.salone.business.RicevutaEjb;
import it.salone.model.Cliente;
import it.salone.model.Ricevuta;

@WebServlet("/modificaEliminaRicevuta")
public class ModificaEliminaRicevutaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	RicevutaEjb ricevutaEjb;
	@EJB
	ClienteEjb clienteEjb;

	public ModificaEliminaRicevutaServlet() {

	}

	public void init(ServletConfig config) throws ServletException {

	}

	public static List<String> getServizi(HttpServletRequest request) {
		String[] servizi = request.getParameter("servizi").split(",");
		String[] serviziFissi = { "PIEGA", "TAGLIO", "COLORERIT", "COLORECOMPL", "TONER", "MECHES", "BAYALAGE",
				"SHATUSH", "PERMAN", "LISCIANTE", "TRATT", "TAGLIOU" };

		ArrayList<String> serv = new ArrayList<String>();

		for (String servizioFisso : serviziFissi) {
			boolean trovato = false; // Variabile per controllare se è stato trovato il servizio
			for (String s : servizi) {
				if (servizioFisso.trim().equals(s.trim())) {
					serv.add(s.trim());
					trovato = true; // Imposta trovato a true se trovi una corrispondenza
					break; // Esci dal ciclo interno
				}
			}
			if (!trovato) {
				serv.add("vuoto"); // Aggiungi "vuoto" se non trovi una corrispondenza
			}
		}
		return serv; // Restituisce la lista di servizi
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String vengo = request.getParameter("vengo");

		if (vengo.equals("btnListaRicevute")) {
			request.setAttribute("data", request.getParameter("data"));
			request.setAttribute("clienteId", request.getParameter("clienteId"));
			request.setAttribute("cliente", request.getParameter("cliente"));
			request.setAttribute("servizi", getServizi(request));
			request.getRequestDispatcher("html/modificaRicevuta.jsp").forward(request, response);
		}

		if (vengo.equals("modifica")){

			String dataVecchia = request.getParameter("dataVecchia");
			Date data = Date.valueOf(request.getParameter("data"));
			String clienteId = request.getParameter("clienteId");
			Cliente c = clienteEjb.getClientiBy(RicercaBy.ID, clienteId).get(0);
			boolean clientePresente = ricevutaEjb.getRicevuteBy(RicercaBy.ALL, "", "").stream()
					.anyMatch(a -> a.getData().equals(data) && a.getCliente().equals(c));
			if (clientePresente && !dataVecchia.equals(request.getParameter("data"))) {
				request.setAttribute("clientePresente", "Cliente già presente nel giorno inserito");
				request.setAttribute("nomeCliente", request.getParameter("nomeCliente"));
				request.getRequestDispatcher("html/modificaEliminaRicevuta.jsp").forward(request, response);
				request.setAttribute("servizi", getServizi(request));
			} else {
				Ricevuta r = ricevutaEjb.getRicevuteBy(RicercaBy.DATACLIENTE,dataVecchia, clienteId).get(0);
				ArrayList<Servizi> listaServizi = new ArrayList<Servizi>();
				String[] array = request.getParameterValues("servizi[]");
				for (String stringa : array) {
					listaServizi.add(Servizi.valueOf(stringa.toUpperCase()));
				}
				double totale = Double.parseDouble(request.getParameter("totale"));
				r.setData(data);
				r.setListaServizi(listaServizi);
				r.setTotale(totale);
				boolean modificato = ricevutaEjb.modificaRicevutaInArchivio(r);

				if (modificato) {
					List<Ricevuta> listaRicevute = new ArrayList<Ricevuta>();
					listaRicevute.add(r);
					request.setAttribute("listaRicevute", listaRicevute);
					request.setAttribute("successo", "La Ricevuta è stata modificata con successo.");
					request.getRequestDispatcher("html/stampaRicevute.jsp").forward(request, response);
				}
			}
		}

		if (vengo.equals("elimina")) {

			String dataVecchia = request.getParameter("data");
			String clienteId = request.getParameter("clienteId");
			Ricevuta r =  ricevutaEjb.getRicevuteBy(RicercaBy.DATACLIENTE,dataVecchia, clienteId).get(0);

			boolean eliminato = ricevutaEjb.eliminaRicevutaInArchivio(r);

			if (!eliminato) 
				request.setAttribute("successo", "La Ricevuta è stata eliminata con successo.");
				request.getRequestDispatcher("html/gestioneRicevuta.jsp").forward(request, response);
			}
		}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
