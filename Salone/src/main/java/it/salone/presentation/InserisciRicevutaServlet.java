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
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import it.salone.Enum.RicercaBy;
import it.salone.Enum.Servizi;
import it.salone.business.ClienteEjb;
import it.salone.business.RicevutaEjb;
import it.salone.model.Cliente;
import it.salone.model.Ricevuta;

@WebServlet("/inserisciRicevuta")
public class InserisciRicevutaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	RicevutaEjb ricevutaEjb;
	@EJB
	ClienteEjb clienteEjb;

	public InserisciRicevutaServlet() {

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

		if (vengo.equals("btnListaApp")) {
			HttpSession session = request.getSession();
			request.setAttribute("data", request.getParameter("data"));
			String id = request.getParameter("clienteId");
			session.setAttribute("clienteId", id);
			request.setAttribute("cliente", request.getParameter("cliente"));
			request.setAttribute("servizi", getServizi(request));
			request.setAttribute("nomeCliente", request.getParameter("nomeCliente"));
			request.getRequestDispatcher("html/inserisciRicevuta.jsp").forward(request, response);
		}

		if (vengo.equals("aggiungi")) {

			Date data = Date.valueOf(request.getParameter("data"));
			String[] servizi = request.getParameterValues("servizi[]");
			List<Servizi> listaServizi = new ArrayList<Servizi>();
			Arrays.stream(servizi).forEach(s -> listaServizi.add(Servizi.valueOf(s)));
			double totale = Double.parseDouble(request.getParameter("totale"));
			HttpSession session = request.getSession();
			String clienteId = (String) session.getAttribute("clienteId");
			Cliente c = clienteEjb.getClientiBy(RicercaBy.ID, clienteId).get(0);
			Ricevuta r = new Ricevuta(data, listaServizi, totale, c);

			boolean inserito = ricevutaEjb.inserisciRicevutaInArchivio(r);

			if (!inserito) {
				request.setAttribute("errore", "La Ricevuta esiste già nel nostro archivio.");
				request.getRequestDispatcher("html/gestioneRicevuta.jsp").forward(request, response);
			} else {
				List<Ricevuta> listaRicevute = new ArrayList<Ricevuta>();
				listaRicevute.add(r);
				request.setAttribute("listaRicevute", listaRicevute);
				request.setAttribute("successo", "La Ricevuta è stata inserita con successo.");
				request.getRequestDispatcher("html/stampaRicevute.jsp").forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
