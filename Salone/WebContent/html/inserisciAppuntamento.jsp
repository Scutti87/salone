<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Aggiungi Appuntamento</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/gestioneClienteStyle.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/loader.css">
<link
	href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>

	<jsp:include page="intestazioneAppuntamenti.jsp"></jsp:include>

	<form class="form-ins-mod-eli" id="inserisci"
		action="${pageContext.request.contextPath}/inserisciAppuntamento"
		method="post">
		<h1 align="center">Inserisci Appuntamento</h1>

		<div
			style="display: flex; justify-content: flex-start; align-items: center;">

			<div style="margin-right: 5%;">
				<label for="data">Data</label> <input type="date" id="data"
					name="data" value='${data != null ? data : "0000-00-00"}' required>
			</div>

			<div style="margin-right: 2%;">
				<label for="ora">Ora</label> <select id="ora" name="ora" required>
					<option value="0">00</option>
					<option value="9">09</option>
					<option value="10">10</option>
					<option value="11">11</option>
					<option value="12">12</option>
					<option value="13">13</option>
					<option value="14">14</option>
					<option value="15">15</option>
					<option value="16">16</option>
					<option value="17">17</option>
					<option value="18">18</option>
				</select>
			</div>

			<div style="margin-right: 5%;">
				<label for="minuti">Minuti</label> <select id="minuti" name="minuti"
					required>
					<option value="0">00</option>
					<option value="15">15</option>
					<option value="30">30</option>
					<option value="45">45</option>
				</select>
			</div>

		</div>
		<label>Servizi</label>
		<div class="servizi-container">
			<label class="container"> <input type="checkbox"
				name="servizi[]" value="PIEGA"> Piega
				<div class="checkmark"></div>
			</label> <label class="container"> <input type="checkbox"
				name="servizi[]" value="TAGLIO"> Taglio
				<div class="checkmark"></div>
			</label><label class="container"> <input type="checkbox"
				name="servizi[]" value="COLORERIT"> Colore Rit
				<div class="checkmark"></div>
			</label> <label class="container"> <input type="checkbox"
				name="servizi[]" value="COLORECOMPL"> Colore Compl
				<div class="checkmark"></div>
			</label> <label class="container"> <input type="checkbox"
				name="servizi[]" value="TONER"> Toner
				<div class="checkmark"></div>
			</label> <label class="container"> <input type="checkbox"
				name="servizi[]" value="MECHES"> Meches
				<div class="checkmark"></div>
			</label> <label class="container"> <input type="checkbox"
				name="servizi[]" value="BAYALAGE"> Bayalage
				<div class="checkmark"></div>
			</label> <label class="container"> <input type="checkbox"
				name="servizi[]" value="SHATUSH"> Shatush
				<div class="checkmark"></div>
			</label> <label class="container"> <input type="checkbox"
				name="servizi[]" value="PERMAN"> Perman
				<div class="checkmark"></div>
			</label> <label class="container"> <input type="checkbox"
				name="servizi[]" value="LISCIANTE"> Lisciante
				<div class="checkmark"></div>
			</label> <label class="container"> <input type="checkbox"
				name="servizi[]" value="TRATT"> Tratt
				<div class="checkmark"></div>
			</label> <label class="container"> <input type="checkbox"
				name="servizi[]" value="TAGLIOU"> Taglio Uomo
				<div class="checkmark"></div>
			</label>
		</div>


		<br> <label for="nomeCliente">Cerca Cliente</label> <input
			type="text" id="nomeCliente" name="nomeCliente"
			onkeyup="cercaClienti()" autocomplete="off"
			value="<c:out value='${nomeCliente != null ? nomeCliente : ""}' />">
		<div id="risultatiClienti"
			style="border: 1px solid #ccc; display: none;">
			<input type="hidden" id="clienteId" name="clienteId">
		</div>
		<br>
		<label for="nuovoCliente">Nuovo Cliente</label> <input type="text"
			id="nome" name="nome" placeholder="Nome" value='${nome != null ? nome : ""}'/><br>
		<input type="text" id="cognome" name="cognome" placeholder="Cognome" value='${cognome != null ? cognome : ""}'/><br>
		<input type="text" id="telefono" name="telefono" pattern="[0-9]+" value='${telefono != null ? telefono : "0000"}'/>

		<button type="submit" onclick="return validateForm()">Inserisci</button>
	</form>

	<jsp:include page="funzioniJS.jsp"></jsp:include>
	
	<script>
		function validateForm() {
			const ora = document.getElementById('ora').value;
			if (ora === "0") {
				alert("Seleziona un'ora valida."); // Messaggio di avviso
				return false; // Previene l'invio del form
			}
			return true; // Permette l'invio del form
		}
	</script>
</body>
</html>