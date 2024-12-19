<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ricevute</title>
<link
	href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<jsp:include page="intestazioneRicevuta.jsp"></jsp:include>

	
		<div class="table-container">
			<div class="row">
				<div class="col">
					<table class="table table-striped table-hover">
						<thead>
							<tr style="font-size: 2rem;">
								<th>Opzioni</th>
								<th>Cliente</th>
								<th>Data</th>
								<th>Servizi</th>
								<th>Totale</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="ricevuta" items="${listaRicevute}">
								<tr style="font-size: 1.25rem;">
									<td><a type="button"
										href="${pageContext.request.contextPath}/modificaEliminaRicevuta?vengo=btnListaRicevute&data=${ricevuta.data}&totale=${ricevuta.totale}&clienteId=${ricevuta.cliente.id}&cliente=${ricevuta.cliente.nome} ${ricevuta.cliente.cognome}&servizi=${fn:replace(fn:replace(ricevuta.listaServizi, '[', ''), ']', '')}"
										class="btn btn-success">Modifica</a> | <a type="button"
										href="${pageContext.request.contextPath}/modificaEliminaRicevuta?vengo=elimina&clienteId=${ricevuta.cliente.id}&data=${ricevuta.data}"
										class="btn btn-danger" onclick="return canc()">Elimina</a></td>

									<td><c:out
											value="${ricevuta.cliente.nome} ${ricevuta.cliente.cognome}"></c:out></td>
									<td><fmt:formatDate value="${ricevuta.data}" pattern="dd-MM-yy"/></td>
									<td><c:out value="${ricevuta.listaServizi}"></c:out></td>
									<td><c:out value="${ricevuta.totale}"></c:out> €</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

	<script defer type="text/javascript">
		function canc() {
			var result = confirm("Sei sicuro di voler cancellare la ricevuta?");
			if (result) {
				// Se confermato, permette il click sul link
				return true;
			}
			// Se non confermato, previene la navigazione
			return false;
		}
	</script>
	<jsp:include page="funzioniJS.jsp"></jsp:include>
</body>
</html>