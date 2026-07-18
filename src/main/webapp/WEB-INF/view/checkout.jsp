<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Checkout - JerseyVault</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/checkout.css">
</head>
<body>
    <%@ include file="/WEB-INF/view/header.jsp" %>
    
    <main class="checkout-container">
        <h2>Dettagli Spedizione e Pagamento</h2>

        <c:if test="${not empty error}">
            <div class="alert-error">
                <c:out value="${error}" />
            </div>
        </c:if>

        <div id="erroriCheckout" class="alert-error"></div>

        <form id="formCheckout" action="${pageContext.request.contextPath}/checkout" method="post">
            <div>
                <label for="via">Via e Numero Civico:</label>
                <input type="text" id="via" name="via" value="<c:out value='${param.via}'/>" required>
            </div>
            <div>
                <label for="citta">Città:</label>
                <input type="text" id="citta" name="citta" value="<c:out value='${param.citta}'/>" required>
            </div>
            <div>
                <label for="cap">CAP:</label>
                <input type="text" id="cap" name="cap" value="<c:out value='${param.cap}'/>" required>
            </div>
            
            <div>
                <label for="tipoMetodo">Metodo di Pagamento:</label>
                <select id="tipoMetodo" name="tipoMetodo" required>
                    <option value="">-- Seleziona --</option>
                    <option value="Carta" ${param.tipoMetodo == 'Carta' ? 'selected' : ''}>Carta di Credito/Debito</option>
                    <option value="PayPal" ${param.tipoMetodo == 'PayPal' ? 'selected' : ''}>PayPal</option>
                </select>
            </div>
            <div>
                <label for="ultimeCifre">Ultime 4 cifre (o account PayPal):</label>
                <input type="text" id="ultimeCifre" name="ultimeCifre" value="<c:out value='${param.ultimeCifre}'/>" required>
            </div>
            <br>
            
            <button type="submit" class="btn-checkout">Conferma Ordine</button>
        </form>
        <br>
        <a href="${pageContext.request.contextPath}/carrello" class="link-back">← Torna al carrello</a>
    </main>
    
    <%@ include file="/WEB-INF/view/footer.jsp" %>
    <script src="${pageContext.request.contextPath}/scripts/validazione.js"></script>
</body>
</html>