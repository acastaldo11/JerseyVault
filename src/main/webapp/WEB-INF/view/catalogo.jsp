<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Catalogo - JerseyVault</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/catalogo.css">
</head>
<body>
    <%@ include file="/WEB-INF/view/header.jsp" %>

    <main>
        <nav class="filtri-categoria">
            <ul>
                <li><a href="${pageContext.request.contextPath}/catalogo">Tutti i Prodotti</a></li>
                <c:forEach items="${categorie}" var="cat">
                    <li>
                        <a href="${pageContext.request.contextPath}/catalogo?categoria=<c:out value='${cat.id}'/>">
                            <c:out value="${cat.nome}" />
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </nav>
        <hr>

        <c:choose>
            <c:when test="${empty prodotti}">
                <p>Nessun prodotto disponibile al momento.</p>
            </c:when>
            <c:otherwise>
                <div class="grid-container">
                    <c:forEach items="${prodotti}" var="prod">
                        <div class="card-prodotto">
                            <img src="${pageContext.request.contextPath}/images/<c:out value='${prod.immagineUrl}'/>"
                                 alt="<c:out value='${prod.nome}'/>">
                            <h2><c:out value="${prod.nome}" /></h2>
                            <p class="squadra"><c:out value="${prod.squadra}" /></p>
                            <p class="prezzo">€ <c:out value="${prod.prezzo}" /></p>
                            <a href="${pageContext.request.contextPath}/prodotto?id=<c:out value='${prod.id}'/>">Vedi Dettagli</a>
                            <br><br>
                            <form action="${pageContext.request.contextPath}/carrello" method="post">
                                <input type="hidden" name="action" value="aggiungi">
                                <input type="hidden" name="idProdotto" value="<c:out value='${prod.id}'/>">
                                <button type="submit">Aggiungi al Carrello</button>
                            </form>
                        </div>
                    </c:forEach>
                </div>
            </c:otherwise>
        </c:choose>
    </main>

    <%@ include file="/WEB-INF/view/footer.jsp" %>
    <script src="${pageContext.request.contextPath}/scripts/validazione.js"></script>
</body>
</html>