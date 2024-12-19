<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestione Ricevute</title>

<link
	href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<jsp:include page="intestazioneRicevuta.jsp"></jsp:include>
	
	<img id="immagine"
			src="${pageContext.request.contextPath}/img/ricevuta.jpg" width="25%"
			height="auto" style="display: block; margin: auto;">

	<jsp:include page="funzioniJS.jsp"></jsp:include>
</body>
</html>