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
import java.util.List;
import it.salone.Enum.RicercaBy;
import it.salone.business.AppuntamentoEjb;
import it.salone.business.ClienteEjb;
import it.salone.model.Appuntamento;

@WebServlet("/cercaAppuntamento")
public class CercaAppuntamentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	AppuntamentoEjb appuntamentoEjb;
	@EJB
	ClienteEjb clienteEjb;

	public CercaAppuntamentoServlet() {
	}

	public void init(ServletConfig config) throws ServletException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Appuntamento> listaAppuntamenti = null;
		String vengo = request.getParameter("vengo");

		if (vengo.equals("storico")) {
			String nome = request.getParameter("nome");
			listaAppuntamenti = appuntamentoEjb.getAppuntamentiStorico(nome);
			request.setAttribute("listaAppuntamenti", listaAppuntamenti);

			request.getRequestDispatcher("html/stampaAppuntamentiStorico.jsp").forward(request, response);

		} else {

			if (vengo.equals("cerca")) {
				String nome = request.getParameter("nome");
				listaAppuntamenti = appuntamentoEjb.getAppuntamentiBy(RicercaBy.NOME, nome, "");
			} else {

				String data = request.getParameter("data");
				HttpSession session = request.getSession();
				String clienteId = (String) session.getAttribute("clienteId");

				if (!data.isBlank() && clienteId != null) {
					listaAppuntamenti = appuntamentoEjb.getAppuntamentiBy(RicercaBy.DATACLIENTE, data, clienteId);
				} else if (data.isBlank() && clienteId != null) {
					listaAppuntamenti = appuntamentoEjb.getAppuntamentiBy(RicercaBy.IDCLIENTE, "", clienteId);
				} else if (clienteId == null && !data.isBlank()) {
					listaAppuntamenti = appuntamentoEjb.getAppuntamentiBy(RicercaBy.DATA, data, "");
				} else {
					listaAppuntamenti = appuntamentoEjb.getAppuntamentiBy(RicercaBy.ALL, "", "");
				}
			}
			request.setAttribute("listaAppuntamenti", listaAppuntamenti);

			request.getRequestDispatcher("html/stampaAppuntamenti.jsp").forward(request, response);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
