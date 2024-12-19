package it.salone.presentation;

import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import it.salone.Enum.RicercaBy;
import it.salone.Enum.Servizi;
import it.salone.business.AppuntamentoEjb;
import it.salone.model.Appuntamento;
import it.salone.model.Cliente;

@WebServlet("/stampaAppuntamenti")
public class StampaAppuntamentiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Date dataOdierna;
	private Date dataOdierna4;
	private Date dataOdierna8;
	private Date dataOdierna1;
	private Date dataOdierna5;
	private Date dataOdierna9;
	private Date dataOdierna2;
	private Date dataOdierna6;
	private Date dataOdierna10;
	private Date dataOdierna3;
	private Date dataOdierna7;
	private Date dataOdierna11;
	Map<String, Date> dateMap;
	Map<String, String> mappaFormattata;
	Map<Object, Object> mappaFormattata2;
	@EJB
	AppuntamentoEjb appuntamentoEjb;

	public StampaAppuntamentiServlet() {
	}

	public void init(ServletConfig config) throws ServletException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String vengo = request.getParameter("vengo");
		List<Appuntamento> listaAppuntamenti = appuntamentoEjb.getAppuntamentiBy(RicercaBy.ALL, "", "");

		// Calcolo tutte le date in anticipo
		calcolaTutteLeDate();

		if (vengo.equals("stampa")) {
			request.setAttribute("listaAppuntamenti", listaAppuntamenti);
			request.getRequestDispatcher("html/stampaAppuntamenti.jsp").forward(request, response);
		}
		
		if (!listaAppuntamenti.isEmpty()) {
			Map<Object, List<Appuntamento>> mappaAppuntamenti = listaAppuntamenti.stream()
					.collect(Collectors.groupingBy(a -> a.getData()));
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
			 mappaFormattata2 = mappaAppuntamenti.entrySet().stream()
		            .collect(Collectors.toMap(
		                entry -> sdf.format(entry.getKey()), // Converte la chiave Date in String
		                Map.Entry::getValue // Mantiene il valore originale
		            ));
			request.setAttribute("mappaAppuntamenti", mappaFormattata2);

			if (vengo.equals("genera")) {
				genera(request, response);
			} else if (vengo.equals("genera3")) {
				genera3(request, response);
			} else if (vengo.equals("genera6")) {
				genera6(request, response);
			} else if (vengo.equals("genera9")) {
				genera9(request, response);
			}
		}
	}

	private String associaGiorno(String data) {
        Map<Integer, String> mappa = new HashMap<>();
        mappa.put(0, "Dom");
        mappa.put(1, "Lun");
        mappa.put(2, "Mar");
        mappa.put(3, "Mer");
        mappa.put(4, "Gio");
        mappa.put(5, "Ven");
        mappa.put(6, "Sab");
        
        // Specifica il formatter per il formato "dd-MM-yy"
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate dataLocal = LocalDate.parse(data, inputFormatter); // Converte la stringa in LocalDate
        
        // Ottiene il giorno della settimana (0 = lunedì, 6 = domenica)
        int dayOfWeek = dataLocal.getDayOfWeek().getValue() % 7; // Converte per mappa (domenica 0)
        
        // Restituisce il nome del giorno dalla mappa
        String giorno = mappa.get(dayOfWeek);
        return giorno;
    }

	private void calcolaTutteLeDate() {
		// Inizializza la mappa delle date
		dateMap = new HashMap<>();

		// Calcolo la data odierna
		Date dataOdierna = Date.valueOf(LocalDate.now());
		dateMap.put("dataOdierna", dataOdierna);

		// Calcola le date successive con controllo per il weekend
		Date data1 = (dataOdierna.getDay() == 6) ? Date.valueOf(dataOdierna.toLocalDate().plusDays(3))
				: Date.valueOf(dataOdierna.toLocalDate().plusDays(1));
		Date data2 = (data1.getDay() == 6) ? Date.valueOf(data1.toLocalDate().plusDays(3))
				: Date.valueOf(data1.toLocalDate().plusDays(1));

		Date data3 = (data2.getDay() == 6) ? Date.valueOf(data2.toLocalDate().plusDays(3))
				: Date.valueOf(data2.toLocalDate().plusDays(1));
		Date data4 = (data3.getDay() == 6) ? Date.valueOf(data3.toLocalDate().plusDays(3))
				: Date.valueOf(data3.toLocalDate().plusDays(1));
		Date data5 = (data4.getDay() == 6) ? Date.valueOf(data4.toLocalDate().plusDays(3))
				: Date.valueOf(data4.toLocalDate().plusDays(1));

		Date data6 = (data5.getDay() == 6) ? Date.valueOf(data5.toLocalDate().plusDays(3))
				: Date.valueOf(data5.toLocalDate().plusDays(1));
		Date data7 = (data6.getDay() == 6) ? Date.valueOf(data6.toLocalDate().plusDays(3))
				: Date.valueOf(data6.toLocalDate().plusDays(1));
		Date data8 = (data7.getDay() == 6) ? Date.valueOf(data7.toLocalDate().plusDays(3))
				: Date.valueOf(data7.toLocalDate().plusDays(1));

		Date data9 = (data8.getDay() == 6) ? Date.valueOf(data8.toLocalDate().plusDays(3))
				: Date.valueOf(data8.toLocalDate().plusDays(1));
		Date data10 = (data9.getDay() == 6) ? Date.valueOf(data9.toLocalDate().plusDays(3))
				: Date.valueOf(data9.toLocalDate().plusDays(1));
		Date data11 = (data10.getDay() == 6) ? Date.valueOf(data10.toLocalDate().plusDays(3))
				: Date.valueOf(data10.toLocalDate().plusDays(1));

		// Inserisci tutte le date nella mappa
		dateMap.put("dataOdierna1", data1);
		dateMap.put("dataOdierna2", data2);
		dateMap.put("dataOdierna3", data3);
		dateMap.put("dataOdierna4", data4);
		dateMap.put("dataOdierna5", data5);
		dateMap.put("dataOdierna6", data6);
		dateMap.put("dataOdierna7", data7);
		dateMap.put("dataOdierna8", data8);
		dateMap.put("dataOdierna9", data9);
		dateMap.put("dataOdierna10", data10);
		dateMap.put("dataOdierna11", data11);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
		mappaFormattata = dateMap.entrySet().stream()
				.collect(Collectors.toMap(Map.Entry::getKey, entry -> sdf.format(entry.getValue()) // Format the date
				));
	}

	private void genera(HttpServletRequest richiesta, HttpServletResponse risposta)
			throws ServletException, IOException {
		LocalDate data = LocalDate.now(); // ottieni la data corrente
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy"); // specifica il formato
		String dataFormattata = data.format(formatter);
		richiesta.setAttribute("dataOdierna", mappaFormattata.get("dataOdierna"));
		richiesta.setAttribute("dataOdierna1", mappaFormattata.get("dataOdierna1"));
		richiesta.setAttribute("dataOdierna2", mappaFormattata.get("dataOdierna2"));
		richiesta.setAttribute("dataConc", String.format("%s   %s", associaGiorno(mappaFormattata.get("dataOdierna")), mappaFormattata.get("dataOdierna")));
		richiesta.setAttribute("dataConc1",
				String.format("%s   %s", associaGiorno(mappaFormattata.get("dataOdierna1")), mappaFormattata.get("dataOdierna1")));
		richiesta.setAttribute("dataConc2",
				String.format("%s   %s", associaGiorno(mappaFormattata.get("dataOdierna2")), mappaFormattata.get("dataOdierna2")));

		richiesta.getRequestDispatcher("html/generaAppuntamenti.jsp").forward(richiesta, risposta);
	}

	private void genera3(HttpServletRequest richiesta, HttpServletResponse risposta)
			throws ServletException, IOException {
		richiesta.setAttribute("dataOdierna", mappaFormattata.get("dataOdierna3"));
		richiesta.setAttribute("dataOdierna1", mappaFormattata.get("dataOdierna4"));
		richiesta.setAttribute("dataOdierna2", mappaFormattata.get("dataOdierna5"));
		richiesta.setAttribute("dataConc",
				String.format("%s   %s", associaGiorno(mappaFormattata.get("dataOdierna3")), mappaFormattata.get("dataOdierna3")));
		richiesta.setAttribute("dataConc1",
				String.format("%s   %s", associaGiorno(mappaFormattata.get("dataOdierna4")), mappaFormattata.get("dataOdierna4")));
		richiesta.setAttribute("dataConc2",
				String.format("%s   %s", associaGiorno(mappaFormattata.get("dataOdierna5")), mappaFormattata.get("dataOdierna5")));

		richiesta.getRequestDispatcher("html/generaAppuntamenti.jsp").forward(richiesta, risposta);
	}

	private void genera6(HttpServletRequest richiesta, HttpServletResponse risposta)
			throws ServletException, IOException {
		richiesta.setAttribute("dataOdierna", mappaFormattata.get("dataOdierna6"));
		richiesta.setAttribute("dataOdierna1", mappaFormattata.get("dataOdierna7"));
		richiesta.setAttribute("dataOdierna2", mappaFormattata.get("dataOdierna8"));
		richiesta.setAttribute("dataConc",
				String.format("%s   %s", associaGiorno(mappaFormattata.get("dataOdierna6")), mappaFormattata.get("dataOdierna6")));
		richiesta.setAttribute("dataConc1",
				String.format("%s   %s", associaGiorno(mappaFormattata.get("dataOdierna7")), mappaFormattata.get("dataOdierna7")));
		richiesta.setAttribute("dataConc2",
				String.format("%s   %s", associaGiorno(mappaFormattata.get("dataOdierna8")), mappaFormattata.get("dataOdierna8")));

		richiesta.getRequestDispatcher("html/generaAppuntamenti.jsp").forward(richiesta, risposta);
	}

	private void genera9(HttpServletRequest richiesta, HttpServletResponse risposta)
			throws ServletException, IOException {
		richiesta.setAttribute("dataOdierna", mappaFormattata.get("dataOdierna9"));
		richiesta.setAttribute("dataOdierna1", mappaFormattata.get("dataOdierna10"));
		richiesta.setAttribute("dataOdierna2", mappaFormattata.get("dataOdierna11"));
		richiesta.setAttribute("dataConc",
				String.format("%s   %s", associaGiorno(mappaFormattata.get("dataOdierna9")), mappaFormattata.get("dataOdierna9")));
		richiesta.setAttribute("dataConc1",
				String.format("%s   %s", associaGiorno(mappaFormattata.get("dataOdierna10")), mappaFormattata.get("dataOdierna10")));
		richiesta.setAttribute("dataConc2",
				String.format("%s   %s", associaGiorno(mappaFormattata.get("dataOdierna11")), mappaFormattata.get("dataOdierna11")));

		richiesta.getRequestDispatcher("html/generaAppuntamenti.jsp").forward(richiesta, risposta);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
