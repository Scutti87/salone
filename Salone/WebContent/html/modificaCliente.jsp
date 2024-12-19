<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Clienti</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/gestioneClienteStyle.css">
<link
	href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<jsp:include page="intestazioneCliente.jsp"></jsp:include>

	<!-- Form per Modifica Cliente -->
		<form class="form-ins-mod-eli" id="modifica"
			action="${pageContext.request.contextPath}/modificaEliminaCliente?vengo=modifica"
			method="post">
			<h1 align="center">Modifica Cliente</h1>
			<label>Id</label> <input type="number" id="modificaId"
				name="modificaId" value="${cliente.id}" readonly><br> <label>Nome</label>
			<input type="text" id="modificaNome" name="modificaNome"
				value="${cliente.nome}" title="Caratteri speciali non ammessi" pattern="[a-zA-Z0-9 ]+"><br> <label>Cognome</label>
			<input type="text" id="modificaCognome" name="modificaCognome"
				value="${cliente.cognome}" title="Caratteri speciali non ammessi" pattern="[a-zA-Z0-9 ]+"><br> <label>Telefono</label>
			<input type="text" id="modificaNumeroTelefono"
				name="modificaNumeroTelefono" value="${cliente.numeroTelefono}" pattern="[0-9]+"><br>
			<label>Scheda</label> <input type="text" id="modificaSchedaTecnica"
				name="modificaSchedaTecnica" value="${cliente.schedaTecnica}" title="Caratteri speciali non ammessi" pattern="[a-zA-Z0-9 .]+"><br>
			<button type="submit">Modifica</button>
		</form>
		
<jsp:include page="funzioniJS.jsp"></jsp:include>
</body>
</html>