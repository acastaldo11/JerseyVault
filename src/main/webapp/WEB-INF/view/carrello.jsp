<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Carrello - JerseyVault</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/carrello.css">
</head>
<body>
    <%@ include file="/WEB-INF/view/header.jsp" %>
    
    <main>
        <h2>Il tuo Carrello</h2>

        <c:choose>
            <c:when test="${empty carrello.items}">
                <p>Il carrello è vuoto.</p>
                <a href="${pageContext.request.contextPath}/catalogo">Torna al catalogo</a>
            </c:when>
            <c:otherwise>
                <table class="tabella-carrello">
                    <thead>
                        <tr>
                            <th>Maglia</th>
                            <th>Squadra</th>
                            <th>Prezzo</th>
                            <th>Quantità</th>
                            <th>Totale Item</th>
                            <th>Azioni</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${carrello.items}" var="item">
                            <tr>
                                <td><c:out value="${item.prodotto.nome}" /></td>
                                <td><c:out value="${item.prodotto.squadra}" /></td>
                                <td>€ <c:out value="${item.prodotto.prezzo}" /></td>
                                <td>
                                    <form action="${pageContext.request.contextPath}/carrello" method="post">
                                        <input type="hidden" name="action" value="aggiorna">
                                        <input type="hidden" name="idProdotto" value="<c:out value='${item.prodotto.id}'/>">
                                        <input type="number" name="quantita" value="<c:out value='${item.quantita}'/>" min="1" class="input-quantita">
                                        <button type="submit">Aggiorna</button>
                                    </form>
                                </td>
                                <td>€ <c:out value="${item.prezzoTotale}" /></td>
                                <td>
                                    <form action="${pageContext.request.contextPath}/carrello" method="post">
                                        <input type="hidden" name="action" value="rimuovi">
                                        <input type="hidden" name="idProdotto" value="<c:out value='${item.prodotto.id}'/>">
                                        <button type="submit">Rimuovi</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <h3>Totale Carrello: € <c:out value="${carrello.totale}" /></h3>

                <form action="${pageContext.request.contextPath}/carrello" method="post">
                    <input type="hidden" name="action" value="svuota">
                    <button type="submit">Svuota Carrello</button>
                </form>

                <br>
                <a href="${pageContext.request.contextPath}/checkout">Procedi al Checkout</a>
            </c:otherwise>
        </c:choose>
    </main>

    <%@ include file="/WEB-INF/view/footer.jsp" %>
</body>
</html>