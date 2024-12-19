<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Index</title>
<script src="https://cdn.tailwindcss.com"></script>
<link href="${pageContext.request.contextPath}/css/index.css"
	rel="stylesheet">
</head>
<body
	class="min-h-screen bg-gray-50 flex items-center justify-center p-8">
	
	<!-- Audio element, starts paused -->
    <audio id="background-audio" loop>
        <source src="${pageContext.request.contextPath}/audio/canzone.mp3" type="audio/mpeg">
    </audio>

    <!-- Button to mute/unmute and start the audio -->
    <button id="mute-button" class="absolute top-4 right-4 bg-gray-200 p-2 rounded shadow-lg">
        Attiva Audio
    </button>


	
	<div class="container mx-auto relative">
		<div class="grid grid-cols-3 gap-12 items-center">
			<img src="${pageContext.request.contextPath}/img/20170823_150123.jpg"
				alt="Dynamic showcase image 1"
				class="w-full rounded-2xl shadow-xl hover:shadow-2xl transition-all duration-500 mirror-float-left">
		
			<div class="flex flex-col gap-12 items-center mt-[-200px]">
				<a style="text-align: center;"
					href="${pageContext.request.contextPath}/html/gestioneCliente.jsp"
					aria-label="Primary Action"
					class="transform hover:scale-125 transition-all duration-300 bg-gradient-button text-black hover:text-white border-2 border-black rounded-lg px-6 py-4 shadow-lg focus:ring-4 focus:ring-red-300 focus:outline-none text-2xl font-bold w-80 bg-white special-hover">CLIENTI 👩</a> <a style="text-align: center;"
					href="${pageContext.request.contextPath}/html/gestioneAppuntamento.jsp"
					aria-label="Secondary
					Action"
					class="transform hover:scale-125 transition-all duration-300 bg-gradient-button text-black hover:text-white border-2 border-black rounded-lg px-6 py-4 shadow-lg focus:ring-4 focus:ring-red-300 focus:outline-none text-2xl font-bold w-80 bg-white special-hover">APPUNTAMENTI 📚</a>
				<a style="text-align: center;"
					href="${pageContext.request.contextPath}/html/gestioneRicevuta.jsp"
					aria-label="Tertiary
					Action"
					class="transform hover:scale-125 transition-all duration-300 bg-gradient-button text-black hover:text-white border-2 border-black rounded-lg px-6 py-4 shadow-lg focus:ring-4 focus:ring-red-300 focus:outline-none text-2xl font-bold w-80 bg-white special-hover">RICEVUTE 💵</a> <a style="text-align: center;"
					href="${pageContext.request.contextPath}/stampaAppuntamenti?vengo=genera"
					aria-label="Quaternary
					Action"
					class="transform hover:scale-125 transition-all duration-300 bg-gradient-button text-black hover:text-white border-2 border-black rounded-lg px-6 py-4 shadow-lg focus:ring-4 focus:ring-red-300 focus:outline-none text-2xl font-bold w-80 bg-white special-hover">AGENDA 📅</a>
			</div>

			<img src="${pageContext.request.contextPath}/img/20170823_132635.jpg"
				alt="Dynamic showcase image 2"
				class="w-full rounded-2xl shadow-xl hover:shadow-2xl transition-all duration-500 mirror-float-right">
		</div>

		<div class="mt-8 flex justify-center">
			<img src="${pageContext.request.contextPath}/img/20170629_193205.jpg"
				alt="Dynamic showcase image 3"
				class="w-1/3 rounded-2xl shadow-xl hover:shadow-2xl transition-all duration-500 alternate-zoom">
		</div>
	</div>
	
    <script>
        const audio = document.getElementById('background-audio');
        const muteButton = document.getElementById('mute-button');
        
        muteButton.addEventListener('click', () => {
            if (audio.paused) {
                audio.play();
                muteButton.textContent = 'Disattiva Audio';
            } else if (audio.muted) {
                audio.muted = false;
                muteButton.textContent = 'Disattiva Audio';
            } else {
                audio.muted = true;
                muteButton.textContent = 'Attiva Audio';
            }
        });
    </script>
	
</body>
</html>
