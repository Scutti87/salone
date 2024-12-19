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
							<tr style="font-size: 2rem;">
								<th>Opzioni</th>
								<th>Cliente</th>
								<th>Data</th>
								<th>Ora</th>
								<th>Servizi</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="appuntamento" items="${listaAppuntamenti}">
								<tr style="font-size: 1.25rem;">
									<td><a type="button"
										href="${pageContext.request.contextPath}/inserisciRicevuta?vengo=btnListaApp&data=${appuntamento.data}&clienteId=${appuntamento.cliente.id}&cliente=${appuntamento.cliente.nome} ${appuntamento.cliente.cognome}&servizi=${fn:replace(fn:replace(appuntamento.listaServizi, '[', ''), ']', '')}"
										class="btn btn-warning">Ricevuta</a> | <a type="button"
										href="${pageContext.request.contextPath}/modificaEliminaAppuntamento?vengo=btnListaApp&data=${appuntamento.data}&ora=${fn:substring(appuntamento.ora, 0, 2)}&minuti=${fn:substring(appuntamento.ora, 3, 5)}&clienteId=${appuntamento.cliente.id}&cliente=${appuntamento.cliente.nome} ${appuntamento.cliente.cognome}&servizi=${fn:replace(fn:replace(appuntamento.listaServizi, '[', ''), ']', '')}"
										class="btn btn-success">Modifica</a> | <a type="button"
										href="${pageContext.request.contextPath}/modificaEliminaAppuntamento?vengo=elimina&clienteId=${appuntamento.cliente.id}&dataVecchia=${appuntamento.data}"
										class="btn btn-danger" onclick="return canc()">Elimina</a></td>

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

	<script defer type="text/javascript">
		function canc() {
			var result = confirm("Sei sicuro di voler cancellare l'appuntamento?");
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