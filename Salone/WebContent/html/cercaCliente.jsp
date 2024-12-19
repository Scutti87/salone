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


	<form class="form-ins-mod-eli" id="inserisci"
		action="${pageContext.request.contextPath}/cercaCliente?vengo=cerca"
		method="post">


		<div
			style="display: flex; justify-content: flex-start; align-items: center;">
		</div>
		<label>Telefono</label> <input type="text" id="cercaTelefono"
			name="cercaTelefono" title="Caratteri speciali non ammessi"
			pattern="[0-9]+" value="0000"> <br> <label
			for="nomeCliente">Cerca Cliente</label> <input type="text"
			id="nomeCliente" name="nomeCliente" onkeyup="cercaClienti()"
			autocomplete="off"
			value="<c:out value='${nomeCliente != null ? nomeCliente : ""}' />">
		<div id="risultatiClienti"
			style="border: 1px solid #ccc; display: none;">
			<input type="hidden" id="clienteId" name="clienteId">
		</div>
		<button type="submit">Cerca</button>
	</form>
	<jsp:include page="funzioniJS.jsp"></jsp:include>
</body>
</html>