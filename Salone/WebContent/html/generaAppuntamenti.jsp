<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" content="width=device-width, initial-scale=1.0">
<title>Genera Appuntamenti</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/intestazione.css">
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bulma.min.css"
	rel="stylesheet">
</head>
<body>

	<div class="container mx-auto relative">
		<div class="grid grid-cols-8 gap-8">
			<a href="${pageContext.request.contextPath}/index.jsp"
				class="transform hover:scale-125 transition-all duration-300 bg-gradient-button text-black hover:text-white border-2 border-black rounded-lg px-4 py-2 shadow-lg focus:ring-4 focus:ring-red-300 focus:outline-none text-2xl font-bold bg-white special-hover text-center w-auto min-w-[150px]">HOME</a>
			<a href="${pageContext.request.contextPath}/html/gestioneCliente.jsp"
				class="transform hover:scale-125 transition-all duration-300 bg-gradient-button text-black hover:text-white border-2 border-black rounded-lg px-4 py-2 shadow-lg focus:ring-4 focus:ring-red-300 focus:outline-none text-2xl font-bold bg-white special-hover text-center w-auto min-w-[150px]">CLIENTI</a>
			<a
				href="${pageContext.request.contextPath}/html/gestioneAppuntamento.jsp"
				class="transform hover:scale-125 transition-all duration-300 bg-gradient-button text-black hover:text-white border-2 border-black rounded-lg px-4 py-2 shadow-lg focus:ring-4 focus:ring-red-300 focus:outline-none text-2xl font-bold bg-white special-hover text-center w-auto min-w-[150px]">APPUNTAMENTI</a>
			<a
				href="${pageContext.request.contextPath}/html/gestioneRicevuta.jsp"
				class="transform hover:scale-125 transition-all duration-300 bg-gradient-button text-black hover:text-white border-2 border-black rounded-lg px-4 py-2 shadow-lg focus:ring-4 focus:ring-red-300 focus:outline-none text-2xl font-bold bg-white special-hover text-center w-auto min-w-[150px]">RICEVUTE</a>
			<a
				href="${pageContext.request.contextPath}/stampaAppuntamenti?vengo=genera"
				class="transform hover:scale-125 transition-all duration-300 bg-gradient-button text-black hover:text-white border-2 border-black rounded-lg px-4 py-2 shadow-lg focus:ring-4 focus:ring-red-300 focus:outline-none text-2xl font-bold bg-white special-hover text-center w-auto min-w-[150px]">AGENDA</a>
			<a
				href="${pageContext.request.contextPath}/stampaAppuntamenti?vengo=genera3"
				class="transform hover:scale-125 transition-all duration-300 bg-gradient-button text-black hover:text-white border-2 border-black rounded-lg px-4 py-2 shadow-lg focus:ring-4 focus:ring-red-300 focus:outline-none text-2xl font-bold bg-white special-hover text-center w-auto min-w-[150px]">AGENDA
				+3</a> <a
				href="${pageContext.request.contextPath}/stampaAppuntamenti?vengo=genera6"
				class="transform hover:scale-125 transition-all duration-300 bg-gradient-button text-black hover:text-white border-2 border-black rounded-lg px-4 py-2 shadow-lg focus:ring-4 focus:ring-red-300 focus:outline-none text-2xl font-bold bg-white special-hover text-center w-auto min-w-[150px]">AGENDA
				+6</a> <a
				href="${pageContext.request.contextPath}/stampaAppuntamenti?vengo=genera9"
				class="transform hover:scale-125 transition-all duration-300 bg-gradient-button text-black hover:text-white border-2 border-black rounded-lg px-4 py-2 shadow-lg focus:ring-4 focus:ring-red-300 focus:outline-none text-2xl font-bold bg-white special-hover text-center w-auto min-w-[150px]">AGENDA
				+9</a>
		</div>

		<div class="columns is-multiline is-mobile">
			<div class="column is-one-third" style="height: 300%">
				<div class="is-size-3"
					style="text-align: center; color: #7b1506; font-weight: bold;">
					<c:out value="${dataConc}" />
				</div>
				<br> <br>
				<table>
					<c:forEach var="entry" items="${mappaAppuntamenti}">
						<tr>
							<td class="is-size-4" style="color: black; text-align: left;">
								<c:if test="${entry.key == dataOdierna}">
									<c:forEach var="app" items="${entry.value}">
										<div style="margin-left: 10px; margin-bottom: 2%;">
											<!-- Aggiungi un margine per l'allineamento -->
											<span style="font-weight: bold;"><fmt:formatDate
													value="${app.ora}" pattern="HH:mm" /></span>
											<c:out value="${app.cliente.nome} ${app.cliente.cognome}" />
											-
											<c:out value="${app.listaServizi}" />
										</div>
									</c:forEach>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="column is-one-third" style="height: 300%">
				<div class="is-size-3"
					style="text-align: center; color: #7b1506; font-weight: bold;">
					<c:out value="${dataConc1}" />
				</div>
				<br> <br>
				<table>
					<c:forEach var="entry" items="${mappaAppuntamenti}">
						<tr>
							<td class="is-size-4" style="color: black; text-align: left;">
								<c:if test="${entry.key == dataOdierna1}">
									<c:forEach var="app" items="${entry.value}">
										<div style="margin-left: 10px; margin-bottom: 2%;">
											<!-- Aggiungi un margine per l'allineamento -->
											<span style="font-weight: bold;"><fmt:formatDate
													value="${app.ora}" pattern="HH:mm" /></span>
											<c:out value="${app.cliente.nome} ${app.cliente.cognome}" />
											-
											<c:out value="${app.listaServizi}" />
										</div>
									</c:forEach>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="column is-one-third" style="height: 300%">
				<div class="is-size-3"
					style="text-align: center; color: #7b1506; font-weight: bold;">
					<c:out value="${dataConc2}" />
				</div>
				<br> <br>
				<table>
					<c:forEach var="entry" items="${mappaAppuntamenti}">
						<tr>
							<td class="is-size-4" style="color: black; text-align: left;">
								<c:if test="${entry.key == dataOdierna2}">
									<c:forEach var="app" items="${entry.value}">
										<div style="margin-left: 10px; margin-bottom: 2%;">
											<!-- Aggiungi un margine per l'allineamento -->
											<span style="font-weight: bold;"><fmt:formatDate
													value="${app.ora}" pattern="HH:mm" /></span>
											<c:out value="${app.cliente.nome} ${app.cliente.cognome}" />
											-
											<c:out value="${app.listaServizi}" />
										</div>
									</c:forEach>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
