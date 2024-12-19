<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cerca Appuntamento</title>
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

	<form class="form-ins-mod-eli" id="cerca"
		action="${pageContext.request.contextPath}/cercaAppuntamento?vengo=cercaAppuntamento"
		method="post">
		<h1 align="center">Cerca Appuntamento</h1>
		<input type="hidden" id="azione" name="azione" value="">
		<!-- Campo nascosto per l'azione -->
		<label>Data</label> <input type="date" id="data" name="data"><br>
		<label for="nomeCliente">Cerca Cliente</label> <input type="text"
			id="nomeCliente" name="nomeCliente" onkeyup="cercaClienti()"
			autocomplete="off">
		<div id="risultatiClienti"
			style="border: 1px solid #ccc; display: none;">
			<input type="hidden" id="ClienteId" name="clienteId">
		</div>
		<br>
		<button type="submit" onclick="return validateForm()">Cerca</button>
	</form>

<jsp:include page="funzioniJS.jsp"></jsp:include>

</body>
</html>