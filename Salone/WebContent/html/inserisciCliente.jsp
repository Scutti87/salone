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

		<!-- Form per Inserisci Cliente -->
		<form class="form-ins-mod-eli" id="inserisci"
			action="${pageContext.request.contextPath}/inserisciCliente"
			method="post">
			<h1 align="center">Inserisci Cliente</h1>
			<label>Nome</label> <input autofocus type="text" id="inserisciNome"
				name="inserisciNome" placeholder="Nome"
				title="Caratteri speciali non ammessi" pattern="[a-zA-Z0-9 ]+" value="${cliente.nome}"
				required><br> <label>Cognome</label> <input type="text"
				id="inserisciCognome" name="inserisciCognome" placeholder="Cognome"
				title="Caratteri speciali non ammessi" pattern="[a-zA-Z0-9 ]+" value="${cliente.cognome}"
				required><br> <label>Telefono</label> <input
				type="text" id="inserisciNumeroTelefono"
				name="inserisciNumeroTelefono" value="0000"
				title="Caratteri speciali non ammessi" pattern="[0-9]+" value="${cliente.numeroTelefono}" required><br>
			<label>Scheda</label> <input type="text" id="inserisciSchedaTecnica"
				name="inserisciSchedaTecnica" placeholder="Inserisci scheda"
				title="Caratteri speciali non ammessi" pattern="[a-zA-Z0-9 .]+" value="${cliente.schedaTecnica}"><br>
			<button type="submit">Inserisci</button>
		</form>
		
<jsp:include page="funzioniJS.jsp"></jsp:include>
</body>
</html>