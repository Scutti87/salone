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


	<img id="immagine"
		src="${pageContext.request.contextPath}/img/Img1.jpg" width="25%"
		height="auto" style="display: block; margin: auto;">

<jsp:include page="funzioniJS.jsp"></jsp:include>

</body>
</html>