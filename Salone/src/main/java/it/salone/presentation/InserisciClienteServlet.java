package it.salone.presentation;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import it.salone.business.ClienteEjb;
import it.salone.model.Cliente;

@WebServlet("/inserisciCliente")
public class InserisciClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	ClienteEjb clienteEjb;

	public InserisciClienteServlet() {
	}

	public void init(ServletConfig config) throws ServletException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nome = request.getParameter("inserisciNome").trim();
		String cognome = request.getParameter("inserisciCognome").trim();
		String telefono = request.getParameter("inserisciNumeroTelefono").trim();
		String scheda = request.getParameter("inserisciSchedaTecnica").trim();

		Cliente c = new Cliente(nome, cognome, telefono, scheda);

		boolean inserito = clienteEjb.inserisciClienteInArchivio(c);
		if (!inserito) {
			request.setAttribute("errore", "Il cliente o il telefono sono gia presenti in archivio.");
			request.setAttribute("cliente", c);
			request.getRequestDispatcher("html/inserisciCliente.jsp").forward(request, response);
		} else {
			request.setAttribute("successo", "Il cliente è stato inserito con successo.");
			request.getRequestDispatcher("html/gestioneCliente.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
