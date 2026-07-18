<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>I miei Ordini - JerseyVault</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/ordini.css">
</head>
<body>
    <%@ include file="/WEB-INF/view/header.jsp" %>
    <main class="client-ordini-container">
        <h2>I miei Ordini</h2>
        <c:choose>
            <c:when test="${empty ordini}">
                <div class="alert-info">
                    <p>Non hai ancora effettuato nessun ordine.</p>
                </div>
            </c:when>
            <c:otherwise>
                <table class="tabella-ordini">
                    <thead>
                        <tr>
                            <th>ID Ordine</th>
                            <th>Data</th>
                            <th>Totale</th>
                            <th>Stato</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${ordini}" var="o">
                            <tr>
                                <td><c:out value="${o.id}" /></td>
                                <td><c:out value="${o.data}" /></td>
                                <td>€ <c:out value="${o.totale}" /></td>
                                <td><c:out value="${o.stato}" /></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
        <br>
        <a href="${pageContext.request.contextPath}/catalogo" class="link-back">← Torna al catalogo</a>
    </main>
    <%@ include file="/WEB-INF/view/footer.jsp" %>
</body>
</html>