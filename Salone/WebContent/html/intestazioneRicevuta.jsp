<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Intestazione Ricevute</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://fonts.googleapis.com/css2?family=Georgia:wght@400;700&display=swap" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/intestazione.css" rel="stylesheet">
</head>
<body class="min-h-screen bg-gray-50 p-8" style="font-family: Georgia, serif;">
    <div class="container mx-auto relative">
        <div class="flex flex-col items-center justify-center mb-16 relative">
            <a href="${pageContext.request.contextPath}/index.jsp" aria-label="Home" class="absolute left-0 transform hover:scale-125 transition-all duration-300 bg-gradient-button text-black hover:text-white border-2 border-black rounded-lg px-2 py-2 shadow-lg focus:ring-4 focus:ring-red-300 focus:outline-none text-2xl font-bold w-40 bg-white special-hover inline-block text-center">HOME 🏠</a>
            <h1 class="text-8xl font-bold text-center bright-red-gradient animate-slide shadow-title" style="font-family: Georgia, serif;">RICEVUTE</h1>
        </div>
        
       <div class="grid grid-cols-5 gap-8">
    <a href="${pageContext.request.contextPath}/html/inserisciRicevuta.jsp" class="transform hover:scale-125 transition-all duration-300 bg-gradient-button text-black hover:text-white border-2 border-black rounded-lg px-4 py-2 shadow-lg focus:ring-4 focus:ring-red-300 focus:outline-none text-2xl font-bold bg-white special-hover text-center w-auto min-w-[150px]">Nuova Ricevuta</a>
    <a href="${pageContext.request.contextPath}/html/cercaRicevuta.jsp" class="transform hover:scale-125 transition-all duration-300 bg-gradient-button text-black hover:text-white border-2 border-black rounded-lg px-4 py-2 shadow-lg focus:ring-4 focus:ring-red-300 focus:outline-none text-2xl font-bold bg-white special-hover text-center w-auto min-w-[150px]">Trova Ricevuta</a>
    <a href="${pageContext.request.contextPath}/stampaRicevute" class="transform hover:scale-125 transition-all duration-300 bg-gradient-button text-black hover:text-white border-2 border-black rounded-lg px-4 py-2 shadow-lg focus:ring-4 focus:ring-red-300 focus:outline-none text-2xl font-bold bg-white special-hover text-center w-auto min-w-[150px]">Lista Ricevute</a>
    
    <form action="${pageContext.request.contextPath}/cercaRicevuta?vengo=btnCerca" method="post" class="flex items-center gap-4">
        <input placeholder="Nome Cliente"
				name="nome" type="search" class="px-4 py-2 text-2xl border-2 border-black rounded-lg focus:outline-none focus:ring-4 focus:ring-red-300 transition-all duration-300 w-auto min-w-[200px]">
        <button type="submit" class="transform hover:scale-125 transition-all duration-300 bg-gradient-button text-black hover:text-white border-2 border-black rounded-lg px-8 py-2 shadow-lg focus:ring-4 focus:ring-red-300 focus:outline-none text-2xl font-bold bg-white special-hover text-center w-auto min-w-[250px]">Cerca 🔍</button>
    </form>
	</div>
	</div>
<br><br>
</body>
</html>