<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modifica Appuntamento</title>
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

	<form class="form-ins-mod-eli" id="modifica"
		action="${pageContext.request.contextPath}/modificaEliminaAppuntamento?vengo=modifica&clienteId=${param.clienteId}&dataVecchia=${param.data}"
		method="post">
		<h1 align="center">Modifica Appuntamento</h1>

		<div
			style="display: flex; justify-content: flex-start; align-items: center;">

			<div style="margin-right: 5%;">
				<label for="data">Data</label> <input value="${param.data}"
					type="date" id="data" name="data" required>
			</div>

			<div style="margin-right: 2%;">
				<label for="ora">Ora</label> <select id="ora" name="ora" required>
					<option value="0" ${ora == '0' ? 'selected' : ''}>00</option>
					<option value="9" ${ora == '9' ? 'selected' : ''}>09</option>
					<option value="10" ${ora == '10' ? 'selected' : ''}>10</option>
					<option value="11" ${ora == '11' ? 'selected' : ''}>11</option>
					<option value="12" ${ora == '12' ? 'selected' : ''}>12</option>
					<option value="13" ${ora == '13' ? 'selected' : ''}>13</option>
					<option value="14" ${ora == '14' ? 'selected' : ''}>14</option>
					<option value="15" ${ora == '15' ? 'selected' : ''}>15</option>
					<option value="16" ${ora == '16' ? 'selected' : ''}>16</option>
					<option value="17" ${ora == '17' ? 'selected' : ''}>17</option>
					<option value="18" ${ora == '18' ? 'selected' : ''}>18</option>
				</select>
			</div>

			<div style="margin-right: 5%;">
				<label for="minuti">Minuti</label> <select id="minuti" name="minuti"
					required>
					<option value="0" ${minuti == '0' ? 'selected' : ''}>00</option>
					<option value="15" ${minuti == '15' ? 'selected' : ''}>15</option>
					<option value="30" ${minuti == '30' ? 'selected' : ''}>30</option>
					<option value="45" ${minuti == '45' ? 'selected' : ''}>45</option>
				</select>
			</div>
		</div>
		<label>Servizi</label>
		<div class="servizi-container">
			<label class="container"> <input type="checkbox"
				name="servizi[]" value="PIEGA"
				<c:if test="${servizi != null && servizi.contains('PIEGA')}">checked</c:if>>
				Piega
				<div class="checkmark"></div>
			</label><label class="container"> <input type="checkbox"
				name="servizi[]" value="TAGLIO"
				<c:if test="${servizi != null && servizi.contains('TAGLIO')}">checked</c:if>>
				Taglio
				<div class="checkmark"></div>
			</label> <label class="container"> <input type="checkbox"
				name="servizi[]" value="COLORERIT"
				<c:if test="${servizi != null && servizi.contains('COLORERIT')}">checked</c:if>>
				Colore Rit
				<div class="checkmark"></div>
			</label> <label class="container"> <input type="checkbox"
				name="servizi[]" value="COLORECOMPL"
				<c:if test="${servizi != null && servizi.contains('COLORECOMPL')}">checked</c:if>>
				Colore Compl
				<div class="checkmark"></div>
			</label> <label class="container"> <input type="checkbox"
				name="servizi[]" value="TONER"
				<c:if test="${servizi != null && servizi.contains('TONER')}">checked</c:if>>
				Toner
				<div class="checkmark"></div>
			</label> <label class="container"> <input type="checkbox"
				name="servizi[]" value="MECHES"
				<c:if test="${servizi != null && servizi.contains('MECHES')}">checked</c:if>>
				Meches
				<div class="checkmark"></div>
			</label> <label class="container"> <input type="checkbox"
				name="servizi[]" value="BAYALAGE"
				<c:if test="${servizi != null && servizi.contains('BAYALAGE')}">checked</c:if>>
				Bayalage
				<div class="checkmark"></div>
			</label> <label class="container"> <input type="checkbox"
				name="servizi[]" value="SHATUSH"
				<c:if test="${servizi != null && servizi.contains('SHATUSH')}">checked</c:if>>
				Shatush
				<div class="checkmark"></div>
			</label> <label class="container"> <input type="checkbox"
				name="servizi[]" value="PERMAN"
				<c:if test="${servizi != null && servizi.contains('PERMAN')}">checked</c:if>>
				Perman
				<div class="checkmark"></div>
			</label> <label class="container"> <input type="checkbox"
				name="servizi[]" value="LISCIANTE"
				<c:if test="${servizi != null && servizi.contains('LISCIANTE')}">checked</c:if>>
				Lisciante
				<div class="checkmark"></div>
			</label> <label class="container"> <input type="checkbox"
				name="servizi[]" value="TRATT"
				<c:if test="${servizi != null && servizi.contains('TRATT')}">checked</c:if>>
				Tratt
				<div class="checkmark"></div>
			</label> <label class="container"> <input type="checkbox"
				name="servizi[]" value="TAGLIOU"
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
		<br>

		<button type="submit" onclick="return validateForm()">Modifica</button>
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
