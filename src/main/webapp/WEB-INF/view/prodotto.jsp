<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title><c:out value="${prodotto.nome}" /> - JerseyVault</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/prodotto.css">
</head>
<body>
    <%@ include file="/WEB-INF/view/header.jsp" %>
    <main>
        <a href="${pageContext.request.contextPath}/catalogo">← Torna al catalogo</a>
        <br><br>
        <div class="dettaglio-container">
            <div class="dettaglio-immagine">
                <img src="${pageContext.request.contextPath}/images/<c:out value='${prodotto.immagineUrl}'/>"
                     alt="<c:out value='${prodotto.nome}'/>">
            </div>
            <div class="dettaglio-info">
                <h2><c:out value="${prodotto.nome}" /></h2>
                <p class="squadra"><c:out value="${prodotto.squadra}" /></p>
                <p class="prezzo">€ <c:out value="${prodotto.prezzo}" /></p>
                <p class="taglia">Taglia: <strong><c:out value="${prodotto.taglia}" /></strong></p>
                <p class="descrizione"><c:out value="${prodotto.descrizione}" /></p>
                <form action="${pageContext.request.contextPath}/carrello" method="post">
                    <input type="hidden" name="action" value="aggiungi">
                    <input type="hidden" name="idProdotto" value="<c:out value='${prodotto.id}'/>">
                    <button type="submit">Aggiungi al Carrello</button>
                </form>
            </div>
        </div>
    </main>
    <%@ include file="/WEB-INF/view/footer.jsp" %>
</body>
</html>