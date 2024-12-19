<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modifica Fich</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/gestioneClienteStyle.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/loader.css">
<link
	href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
<style>
/* Stili per la finestra modale */
#popupSconto {
	display: none;
	/* Rende il pop-up invisibile fino a quando non viene mostrato */
	position: fixed; /* Posizione fissa sulla finestra */
	left: 50%; /* Sposta il popup al centro orizzontale */
	top: 50%; /* Sposta il popup al centro verticale */
	transform: translate(-50%, -50%); /* Centra esattamente */
	height: 20%;
	width: 20%; /* Larghezza fissa del pop-up */
	padding: 20px; /* Aggiunge uno spazio interno */
	background-color: white; /* Sfondo del pop-up */
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
	/* Ombra per dare profondità */
	border-radius: 8px;
	/* Bordo arrotondato per un aspetto più piacevole */
	z-index: 1000; /* Porta il pop-up sopra altri elementi */
}

#popupSconto input {
	width: 100%; /* Rende l'input allineato alla larghezza del popup */
	padding: 8px; /* Spazio interno agli input */
	margin-top: 10px; /* Margine sopra gli input */
}

#popupSconto button {
	margin-top: 20px; /* Spazio sopra al pulsante */
	padding: 10px 20px; /* Spazio interno al pulsante */
	width: 100%; /* Larghezza completa del pulsante */
}
</style>
	<jsp:include page="intestazioneRicevuta.jsp"></jsp:include>

	<form class="form-ins-mod-eli" id="modifica"
		action="${pageContext.request.contextPath}/modificaEliminaRicevuta?vengo=modifica&clienteId=${param.clienteId}&dataVecchia=${param.data}"
		method="post" onsubmit="return mostraPopup()">
		<h1 align="center">Modifica Ricevuta</h1>

		<div
			style="display: flex; justify-content: flex-start; align-items: center;">

			<div style="margin-right: 5%;">
				<label for="data">Data</label> <input value="${param.data}"
					type="date" id="data" name="data" required>
			</div>

		</div>
		<label>Servizi</label>
		<div class="servizi-container">
			<label class="container"> <input type="checkbox"
				name="servizi[]" value="PIEGA" onchange="calcolaTotale()"
				<c:if test="${servizi != null && servizi.contains('PIEGA')}">checked</c:if>>
				Piega
				<div class="checkmark"></div>
			</label><label class="container"> <input type="checkbox"
				name="servizi[]" value="TAGLIO" onchange="calcolaTotale()"
				<c:if test="${servizi != null && servizi.contains('TAGLIO')}">checked</c:if>>
				Taglio
				<div class="checkmark"></div>
			</label> <label class="container"> <input type="checkbox"
				name="servizi[]" value="COLORERIT" onchange="calcolaTotale()"
				<c:if test="${servizi != null && servizi.contains('COLORERIT')}">checked</c:if>>
				Colore Rit
				<div class="checkmark"></div>
			</label> <label class="container"> <input type="checkbox"
				name="servizi[]" value="COLORECOMPL" onchange="calcolaTotale()"
				<c:if test="${servizi != null && servizi.contains('COLORECOMPL')}">checked</c:if>>
				Colore Compl
				<div class="checkmark"></div>
			</label> <label class="container"> <input type="checkbox"
				name="servizi[]" value="TONER" onchange="calcolaTotale()"
				<c:if test="${servizi != null && servizi.contains('TONER')}">checked</c:if>>
				Toner
				<div class="checkmark"></div>
			</label> <label class="container"> <input type="checkbox"
				name="servizi[]" value="MECHES" onchange="calcolaTotale()"
				<c:if test="${servizi != null && servizi.contains('MECHES')}">checked</c:if>>
				Meches
				<div class="checkmark"></div>
			</label> <label class="container"> <input type="checkbox"
				name="servizi[]" value="BAYALAGE" onchange="calcolaTotale()"
				<c:if test="${servizi != null && servizi.contains('BAYALAGE')}">checked</c:if>>
				Bayalage
				<div class="checkmark"></div>
			</label> <label class="container"> <input type="checkbox"
				name="servizi[]" value="SHATUSH" onchange="calcolaTotale()"
				<c:if test="${servizi != null && servizi.contains('SHATUSH')}">checked</c:if>>
				Shatush
				<div class="checkmark"></div>
			</label> <label class="container"> <input type="checkbox"
				name="servizi[]" value="PERMAN" onchange="calcolaTotale()"
				<c:if test="${servizi != null && servizi.contains('PERMAN')}">checked</c:if>>
				Perman
				<div class="checkmark"></div>
			</label> <label class="container"> <input type="checkbox"
				name="servizi[]" value="LISCIANTE" onchange="calcolaTotale()"
				<c:if test="${servizi != null && servizi.contains('LISCIANTE')}">checked</c:if>>
				Lisciante
				<div class="checkmark"></div>
			</label> <label class="container"> <input type="checkbox"
				name="servizi[]" value="TRATT" onchange="calcolaTotale()"
				<c:if test="${servizi != null && servizi.contains('TRATT')}">checked</c:if>>
				Tratt
				<div class="checkmark"></div>
			</label> <label class="container"> <input type="checkbox"
				name="servizi[]" value="TAGLIOU" onchange="calcolaTotale()"
				<c:if test="${servizi != null && servizi.contains('TAGLIOU')}">checked</c:if>>
				Taglio Uomo
				<div class="checkmark"></div>
			</label>
		</div>
		<br> <label for="nomeCliente">Cerca Cliente</label> <input
			type="text" id="nomeCliente" name="nomeCliente"
			value="<c:out value='${param.cliente != null ? param.cliente : nomeCliente}' />">
		<div id="risultatiClienti"
			style="border: 1px solid #ccc; display: none;">
			<input type="hidden" id="modificaClienteId" name="clienteId">
		</div>
		<br> <label
			for="totale">Totale (€)</label> <input type="text" id="totale"
			name="totale" value="${param.totale}" pattern="[0-9]+" readonly>

		<button type="submit" onclick="return validateFormCliente()">Modifica</button>
	</form>
	
	<!-- Finestra modale -->
	<div id="popupSconto" class="modal">
		<div class="modal-content">
			<span class="close" onclick="chiudiPopup()">&times;</span>
			<h2>Inserisci lo Sconto</h2>
			<label for="sconto">Sconto (€)</label> <input type="text"
				id="scontoPopup" value="" pattern="[0-9]+" oninput="calcolaTotale()">
			<br> <br>
			<button onclick="confermaSconto()">Conferma</button>
		</div>
	</div>
	
	
	

	<jsp:include page="funzioniJS.jsp"></jsp:include>
	<script>
	// Variabile globale per mantenere il valore iniziale del totale
	let totaleIniziale = 0;

	// Mostra il pop-up e memorizza il valore iniziale del totale
	function mostraPopup() {
	    totaleIniziale = parseFloat(document.getElementById('totale').value) || 0;
	    document.getElementById("popupSconto").style.display = "block";
	    document.getElementById('scontoPopup').focus();
	    return false; // Impedisce l'invio del form
	}

	// Nasconde il pop-up
	function chiudiPopup() {
	    document.getElementById("popupSconto").style.display = "none";
	}
	
	const serviziPrezzi = {
	    "PIEGA": 15,
	    "TAGLIO": 25,
	    "COLORERIT": 25,
	    "COLORECOMPL": 35,
	    "TONER": 15,
	    "MECHES": 100,
	    "BAYALAGE": 100,
	    "SHATUSH": 100,
	    "PERMAN": 40,
	    "LISCIANTE": 150,
	    "TRATT": 15,
	    "MASCHERA": 10,
	    "CREMALAV": 5,
	    "TAGLIOU": 15, 
	};

	// Calcola il totale in base ai servizi selezionati e applica lo sconto
	function calcolaTotale() {
	    let totale = 0;

	    // Ottieni tutti i checkbox dei servizi
	    const checkboxes = document.querySelectorAll('input[name="servizi[]"]:checked');

	    // Somma i prezzi dei servizi selezionati
	    checkboxes.forEach((checkbox) => {
	        const servizio = checkbox.value;
	        if (serviziPrezzi[servizio]) {
	            totale += serviziPrezzi[servizio];
	        }
	    });

	    // Ottieni lo sconto e calcola il nuovo totale
	    let sconto = parseFloat(document.getElementById('scontoPopup').value) || 0;
	    let nuovoTotale = totale - sconto;

	    // Assicura che il nuovo totale non sia negativo
	    if (nuovoTotale < 0) {
	        nuovoTotale = 0;
	    }

	    // Aggiorna il campo 'totale' con il nuovo valore
	    document.getElementById('totale').value = nuovoTotale.toFixed(2);
	}
	
	// Conferma lo sconto e invia il form
	function confermaSconto() {
	    calcolaTotale();
	    chiudiPopup();
	    document.getElementById('modifica').submit(); // Invia il form
	}	
	</script>
</body>
</html>
