<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	if (request.getAttribute("clientePresenteCli") != null) {
	%>
	<script>
        alert("<%=request.getAttribute("clientePresenteCli")%>");
    </script>
	<%
	}
	%>

	<%
	if (request.getAttribute("clientePresente") != null) {
	%>
	<script>
        alert("<%=request.getAttribute("clientePresente")%>");
    </script>
	<%
	}
	%>

	<%
	if (request.getAttribute("ora0") != null) {
	%>
	<script>
        alert("<%=request.getAttribute("ora0")%>");
    </script>
	<%
	}
	%>

	<%
	if (request.getAttribute("errore") != null) {
	%>
	<script>
        alert("<%=request.getAttribute("errore")%>");
    </script>
	<%
	}
	%>

	<%
	if (request.getAttribute("successo") != null) {
	%>
	<script>
        alert("<%=request.getAttribute("successo")%>");
    </script>
	<%
	}
	%>


	<script type="text/javascript"> // per il form inserisci
function cercaClienti() {
    let nome = document.getElementById("nomeCliente").value;
    if (nome.length > 0) {  // Esegui la ricerca solo se il nome ha più di 2 caratteri
        let xhr = new XMLHttpRequest();
        xhr.open("post", "<%=request.getContextPath()%>/cercaClienti?&nome=" + nome, true);
        xhr.onreadystatechange = function() {
            if (xhr.readyState == 4 && xhr.status == 200) {
                document.getElementById("risultatiClienti").innerHTML = xhr.responseText;
                document.getElementById("risultatiClienti").style.display = "block";
            }
        };
        xhr.send();
    } else {
        document.getElementById("risultatiClienti").style.display = "none";
    }
}

function selezionaCliente(id, nomeCompleto) {
    // Imposta il nome completo del cliente nel campo di testo
    document.getElementById("nomeCliente").value = nomeCompleto;

    // Invia l'ID del cliente alla servlet per aggiornare la sessione
    let xhr = new XMLHttpRequest();
    xhr.open("post", "<%=request.getContextPath()%>/aggiornaClienteId?clienteId=" + encodeURIComponent(id), true);
    xhr.send();

    // Nascondi la tendina
    document.getElementById("risultatiClienti").style.display = "none";
}

</script>

<script>
		function validateFormCliente() {
			const nomeCliente = document.getElementById('nomeCliente').value;
			if (nomeCliente === "") {
				alert("Inserire cliente."); // Messaggio di avviso
				return false; // Previene l'invio del form
			}
			return true; // Permette l'invio del form
		}
	</script>

</body>
</html>