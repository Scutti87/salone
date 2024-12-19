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
import java.util.List;
import it.salone.Enum.RicercaBy;
import it.salone.Enum.Servizi;
import it.salone.business.AppuntamentoEjb;
import it.salone.business.ClienteEjb;
import it.salone.model.Appuntamento;
import it.salone.model.Cliente;

@WebServlet("/modificaEliminaAppuntamento")
public class ModificaAppuntamentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	AppuntamentoEjb appuntamentoEjb;
	@EJB
	ClienteEjb clienteEjb;

	public ModificaAppuntamentoServlet() {

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
			request.setAttribute("ora", request.getParameter("ora"));
			request.setAttribute("minuti", request.getParameter("minuti"));
			request.setAttribute("data", request.getParameter("data"));
			request.setAttribute("clienteId", request.getParameter("clienteId"));
			request.setAttribute("cliente", request.getParameter("cliente"));
			request.setAttribute("servizi", getServizi(request));
			request.getRequestDispatcher("html/modificaAppuntamento.jsp").forward(request, response);
		}

		if (vengo.equals("modifica"))

		{
			String ora = request.getParameter("ora");

			String dataVecchia = request.getParameter("dataVecchia");
			Date data = Date.valueOf(request.getParameter("data"));
			String clienteId = request.getParameter("clienteId");
			Cliente c = clienteEjb.getClientiBy(RicercaBy.ID, clienteId).get(0);
			boolean clientePresente = appuntamentoEjb.getAppuntamentiBy(RicercaBy.ALL, "", "").stream()
					.anyMatch(a -> a.getData().equals(data) && a.getCliente().equals(c));
			if (clientePresente && !dataVecchia.equals(request.getParameter("data"))) {
				request.setAttribute("clientePresente", "Cliente già presente nel giorno inserito");
				request.setAttribute("nomeCliente", request.getParameter("nomeCliente"));
				request.getRequestDispatcher("html/modificaAppuntamento.jsp").forward(request, response);
			} else {
				ArrayList<Servizi> listaServizi = new ArrayList<Servizi>();
				String[] array = request.getParameterValues("servizi[]");
				for (String stringa : array) {
					listaServizi.add(Servizi.valueOf(stringa.toUpperCase()));
				}
				String minuti = request.getParameter("minuti");
				Time orario = Time.valueOf(ora.concat(":").concat(minuti).concat(":").concat("00"));
				Appuntamento a = appuntamentoEjb.getAppuntamentiBy(RicercaBy.DATACLIENTE, dataVecchia, clienteId)
						.get(0);
				Appuntamento app = new Appuntamento(a.getId(), data, orario, listaServizi, c);

				boolean modificato = appuntamentoEjb.modificaAppuntamentoInArchivio(app);

				if (modificato) {
					request.setAttribute("listaAppuntamenti",
							appuntamentoEjb.getAppuntamentiBy(RicercaBy.ID, String.valueOf(a.getId()), ""));
					request.setAttribute("successo", "L'appuntamento è stato modificato con successo.");
					request.getRequestDispatcher("html/stampaAppuntamenti.jsp").forward(request, response);
				}
			}
		}

		if (vengo.equals("elimina")) {

			String dataVecchia = request.getParameter("dataVecchia");
			String clienteId = request.getParameter("clienteId");
			Appuntamento a = appuntamentoEjb.getAppuntamentiBy(RicercaBy.DATACLIENTE, dataVecchia, clienteId).get(0);

			boolean eliminato = appuntamentoEjb.eliminaAppuntamentoInArchivio(a);

			if (eliminato)
				request.setAttribute("successo", "L'appuntamento è stato eliminato con successo.");
			request.getRequestDispatcher("html/gestioneAppuntamento.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
