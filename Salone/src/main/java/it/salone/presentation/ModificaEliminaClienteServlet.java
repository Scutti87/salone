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

import it.salone.Enum.RicercaBy;
import it.salone.business.ClienteEjb;
import it.salone.model.Cliente;

@WebServlet("/modificaEliminaCliente")
public class ModificaEliminaClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	ClienteEjb clienteEjb;

	public ModificaEliminaClienteServlet() {

	}

	public void init(ServletConfig config) throws ServletException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String vengo = request.getParameter("vengo");

		if (vengo.equals("btnModifica") || vengo.equals("btnElimina")) {
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String cognome = request.getParameter("cognome");
			String numeroTelefono = request.getParameter("numeroTelefono");
			String schedaTecnica = request.getParameter("schedaTecnica");

			if (vengo.equals("btnModifica")) {
				request.setAttribute("cliente",
						new Cliente(Integer.parseInt(id), nome, cognome, numeroTelefono, schedaTecnica));
				request.getRequestDispatcher("html/modificaCliente.jsp").forward(request, response);
			}

			if (vengo.equals("btnElimina")) {
				Cliente c = new Cliente(Integer.parseInt(id), nome, cognome, numeroTelefono, schedaTecnica);
				boolean eliminato = clienteEjb.eliminaClienteInArchivio(c);

				if (!eliminato) {
					request.setAttribute("errore", "Qualcosa è andato storto.");
					request.getRequestDispatcher("/cercaCliente?vengo=archivio").forward(request, response);
				} else {
					request.setAttribute("successo", "Il cliente è stato eliminato con successo.");
					request.getRequestDispatcher("html/gestioneCliente.jsp").forward(request, response);
				}
			}
		}

		if (vengo.equals("modifica")) {
			String id = request.getParameter("modificaId");
			String nome = request.getParameter("modificaNome");
			String cognome = request.getParameter("modificaCognome");
			String numeroTelefono = request.getParameter("modificaNumeroTelefono");
			String schedaTecnica = request.getParameter("modificaSchedaTecnica");

			Cliente c = clienteEjb.getClientiBy(RicercaBy.ID, id).get(0);
			//Cliente c = new Cliente(Integer.parseInt(id), nome, cognome, numeroTelefono, schedaTecnica);
			c.setCognome(cognome);
			c.setNome(nome);
			c.setNumeroTelefono(numeroTelefono);
			c.setSchedaTecnica(schedaTecnica);
			boolean modificato = clienteEjb.modificaClienteInArchivio(c);

			if (!modificato) {
				request.setAttribute("errore", "Il telefono o il cliente sono gia presenti in archivio.");
				request.setAttribute("cliente", c);
				request.getRequestDispatcher("html/modificaCliente.jsp").forward(request, response);
			} else {
				HttpSession sess = request.getSession();
				sess.setAttribute("clienteId", id);
				request.setAttribute("successo", "Il cliente è stato modificato con successo.");
				request.setAttribute("cercaId", id);
				request.getRequestDispatcher("/cercaCliente?vengo=cerca&cercaNome=''&cercaCognome=''&cercaTelefono=" + numeroTelefono + "&schedaTecnica=" + schedaTecnica).forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
