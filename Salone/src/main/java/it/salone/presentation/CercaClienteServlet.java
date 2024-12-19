package it.salone.presentation;

import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
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
import it.salone.business.ClienteEjb;
import it.salone.model.Cliente;

@WebServlet("/cercaCliente")
public class CercaClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	ClienteEjb clienteEjb;

	public CercaClienteServlet() {
	}

	public void init(ServletConfig config) throws ServletException {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Cliente> listaClienti = null;
		String vengo = request.getParameter("vengo");

		if (vengo.equals("cercaInput")) {
			String nome = request.getParameter("cercaNome").trim();
			listaClienti = clienteEjb.getClientiBy(RicercaBy.NOME, nome);
		}

		if (vengo.equals("cerca")) {
			String cercaTelefono = request.getParameter("cercaTelefono");

			if (!cercaTelefono.equals("0000") && !cercaTelefono.equals("") ) {
				listaClienti = clienteEjb.getClientiBy(RicercaBy.NUMEROTELEFONO, cercaTelefono);
			} else {
				HttpSession session = request.getSession();
				String clienteId = (String) session.getAttribute("clienteId");

				listaClienti = clienteEjb.getClientiBy(RicercaBy.ID, clienteId);
			}
		}

		if (vengo.equals("archivio")) {
			listaClienti = clienteEjb.getClientiBy(RicercaBy.ALL, "all");
		}

		request.setAttribute("listaClienti", listaClienti);

		request.getRequestDispatcher("html/stampaClienti.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
