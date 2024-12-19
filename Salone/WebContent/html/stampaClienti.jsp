<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Clienti</title>
<link
	href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<jsp:include page="intestazioneCliente.jsp"></jsp:include>

			
		<div class="table-container">
			<div class="row">
				<div class="col">
					<table class="table table-striped table-hover">
						<thead>
							<tr style="font-size: 2rem;">
								<th>Opzioni</th>
								<th>Cliente</th>
								<th>Telefono</th>
								<th>Scheda</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach var="cliente" items="${listaClienti}">
								<tr style="font-size: 1.25rem;">
									<td><a type="button"
										href="${pageContext.request.contextPath}/modificaEliminaCliente?vengo=btnModifica&id=${cliente.id}&nome=${cliente.nome}&cognome=${cliente.cognome}&numeroTelefono=${cliente.numeroTelefono}&schedaTecnica=${cliente.schedaTecnica}"
										class="btn btn-success">Modifica</a> | <a type="button"
										href="${pageContext.request.contextPath}/modificaEliminaCliente?vengo=btnElimina&id=${cliente.id}&nome=${cliente.nome}&cognome=${cliente.cognome}&numeroTelefono=${cliente.numeroTelefono}&schedaTecnica=${cliente.schedaTecnica}""
										class="btn btn-danger"
										onclick="return canc('${cliente.nome}', '${cliente.cognome}')">Elimina</a></td>
									<td><c:out value="${cliente.nome} ${cliente.cognome}"></c:out></td>
									<td><c:out value="${cliente.numeroTelefono}"></c:out></td>
									<td><c:out value="${cliente.schedaTecnica}"></c:out></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	

	<jsp:include page="funzioniJS.jsp"></jsp:include>

	<script defer type="text/javascript">
		function canc(nome, cognome) {
			var result = confirm("Sei sicuro di voler cancellare " + nome + " "
					+ cognome + " dall'archivio?");
			if (result) {
				// If confirmed, submit the form
				document.getElementById('elimina').submit();
				return true;
			}
			// If not confirmed, return false to prevent form submission
			return false;
		}
	</script>

</body>
</html>