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
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import it.salone.Enum.RicercaBy;
import it.salone.Enum.Servizi;
import it.salone.business.AppuntamentoEjb;
import it.salone.business.ClienteEjb;
import it.salone.model.Appuntamento;
import it.salone.model.Cliente;

@WebServlet("/inserisciAppuntamento")
public class InserisciAppuntamentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	AppuntamentoEjb appuntamentoEjb;
	@EJB
	ClienteEjb clienteEjb;

	public InserisciAppuntamentoServlet() {

	}

	public void init(ServletConfig config) throws ServletException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Date data = Date.valueOf(request.getParameter("data"));
		String ora = request.getParameter("ora");
		String nomeClienteEsistente = request.getParameter("nomeCliente");
		String nome = request.getParameter("nome").trim();
		String cognome = request.getParameter("cognome").trim();
		String telefono = request.getParameter("telefono").trim();
		Cliente cli = null;

		HttpSession session = request.getSession();
		String clienteId = (String) session.getAttribute("clienteId");
		cli = (nomeClienteEsistente.isBlank()) ? new Cliente(clienteEjb.getMaxId() + 1, nome, cognome, telefono, null)
				: clienteEjb.getClientiBy(RicercaBy.ID, clienteId).get(0);
		Cliente c = cli;
		boolean clientePresenteInCli = clienteEjb.getClientiBy(RicercaBy.ALL, "all").stream().anyMatch(
				cliente -> cliente.getNome().equals(c.getNome()) && cliente.getCognome().equals(c.getCognome()));
		boolean reindirizzato = false;
		if (nomeClienteEsistente.isBlank() && clientePresenteInCli) {
			request.setAttribute("clientePresenteCli", "Cliente già presente in archivio");
			request.getRequestDispatcher("html/inserisciAppuntamento.jsp").forward(request, response);
			reindirizzato = true;
		}
		if (nomeClienteEsistente.isBlank() && !clientePresenteInCli) {
			clienteEjb.inserisciClienteInArchivio(cli);
		}

		if (!reindirizzato) {

			boolean clientePresenteInApp = (appuntamentoEjb.getAppuntamentiBy(RicercaBy.DATACLIENTE, String.valueOf(data),
					String.valueOf(c.getId())).isEmpty()) ? false : true;

			if (clientePresenteInApp) {
				request.setAttribute("clientePresente", "Cliente già presente nel giorno inserito");
				request.setAttribute("nomeCliente", request.getParameter("nomeCliente"));
				request.getRequestDispatcher("html/inserisciAppuntamento.jsp").forward(request, response);
			} else {
				String minuti = request.getParameter("minuti");
				Time orario = Time.valueOf(ora.concat(":").concat(minuti).concat(":").concat("00"));
				List<Servizi> listaServizi = new ArrayList<Servizi>();
				String[] array = request.getParameterValues("servizi[]");
				for (String stringa : array) {
					listaServizi.add(Servizi.valueOf(stringa.toUpperCase()));
				}

				Appuntamento a = new Appuntamento(data, orario, listaServizi, c);

				boolean inserito = appuntamentoEjb.inserisciAppuntamentoInArchivio(a);

				if (inserito) {
					List<Appuntamento> listaAppuntamenti = new ArrayList<Appuntamento>();
					listaAppuntamenti.add(appuntamentoEjb.getAppuntamentiBy(RicercaBy.DATACLIENTE, String.valueOf(data), String.valueOf(c.getId())).get(0));
					request.setAttribute("listaAppuntamenti", listaAppuntamenti);
					request.setAttribute("successo", "L'appuntamento è stato inserito con successo.");
					request.getRequestDispatcher("html/stampaAppuntamenti.jsp").forward(request, response);
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
