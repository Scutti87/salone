<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestione Appuntamenti</title>
<link
	href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>

	<jsp:include page="intestazioneAppuntamenti.jsp"></jsp:include>

	
		<div class="table-container">
			<div class="row">
				<div class="col">
					<table class="table table-striped table-hover">
						<thead>
							<tr style="font-size: 1.5rem;">
								<th>Cliente</th>
								<th>Data</th>
								<th>Ora</th>
								<th>Servizi</th>			
							</tr>
						</thead>
						<tbody>
							<c:forEach var="appuntamento" items="${listaAppuntamenti}">
								<tr>
									<td><c:out
											value="${appuntamento.cliente.nome} ${appuntamento.cliente.cognome}"></c:out></td>
									<td><fmt:formatDate value="${appuntamento.data}"
											pattern="dd-MM-yy" /></td>
									<td><c:out value="${appuntamento.ora}"></c:out></td>
									<td><c:out value="${appuntamento.listaServizi}"></c:out></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	
	
	<jsp:include page="funzioniJS.jsp"></jsp:include>

</body>
</html>