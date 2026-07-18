<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Ordine Confermato - JerseyVault</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/conferma.css">
</head>
<body>
    <%@ include file="/WEB-INF/view/header.jsp" %>
    
    <main class="conferma-wrapper">
        <div class="conferma-container">
            <h2>Ordine confermato!</h2>
            <p>Grazie per il tuo acquisto su JerseyVault.</p>
            <p>Il tuo ordine è stato ricevuto ed è in lavorazione.</p>
            <br>
            <a href="${pageContext.request.contextPath}/catalogo" class="btn-catalogo">Torna al catalogo</a>
        </div>
    </main>

    <%@ include file="/WEB-INF/view/footer.jsp" %>
</body>
</html>