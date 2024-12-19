package it.salone.presentation;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import it.salone.Enum.RicercaBy;
import it.salone.business.ClienteEjb;
import it.salone.model.Cliente;

@WebServlet("/cercaClienti")
public class CercaClientiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	ClienteEjb clienteEjb;

	public CercaClientiServlet() {

	}

	public void init(ServletConfig config) throws ServletException {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nome = request.getParameter("nome");

		List<Cliente> listaClienti = clienteEjb.getClientiBy(RicercaBy.NOME, nome);

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// Genera l'HTML per la tendina con i risultati
		String vengo = request.getParameter("vengo");

		for (Cliente cliente : listaClienti) {
			String nomeCompleto = cliente.getNome() + " " + cliente.getCognome();
			String id = String.valueOf(cliente.getId());
			out.println("<div onclick=\"selezionaCliente('" + id + "', '" + nomeCompleto + "')\">" + cliente.getNome()
					+ " " + cliente.getCognome() + " - " + cliente.getNumeroTelefono() + "</div>");

		}

		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
