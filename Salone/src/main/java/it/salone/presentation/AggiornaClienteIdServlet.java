package it.salone.presentation;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/aggiornaClienteId")
public class AggiornaClienteIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AggiornaClienteIdServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String clienteId = request.getParameter("clienteId");

		// Aggiorna la sessione con l'ID del cliente selezionato
		HttpSession session = request.getSession();
		session.setAttribute("clienteId", clienteId);

		// Puoi rispondere con un messaggio o semplicemente chiudere la risposta
		response.getWriter().write("ID cliente aggiornato");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
