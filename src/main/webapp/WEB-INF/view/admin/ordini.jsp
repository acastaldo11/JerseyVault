<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Gestione Ordini - JerseyVault Admin</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/admin-ordini.css">
</head>
<body>
    <main class="admin-ordini-container">
        <header class="ordini-header">
            <h1>Lista Ordini Sistema</h1>
            <a href="${pageContext.request.contextPath}/admin/dashboard" class="btn-back">← Torna alla Dashboard</a>
        </header>

        <form action="${pageContext.request.contextPath}/admin/ordini" method="get" class="filtri-form">
            <div class="filtri-group">
                <label>ID Cliente:</label>
                <input type="text" name="idCliente" value="<c:out value='${idCliente}'/>">
            </div>
            <div class="filtri-group">
                <label>Dal:</label>
                <input type="date" name="dataInizio" value="<c:out value='${dataInizio}'/>">
            </div>
            <div class="filtri-group">
                <label>Al:</label>
                <input type="date" name="dataFine" value="<c:out value='${dataFine}'/>">
            </div>
            <div class="filtri-actions">
                <button type="submit" class="btn-filtra">Filtra</button>
                <a href="${pageContext.request.contextPath}/admin/ordini" class="btn-reset">Reset</a>
            </div>
        </form>

        <c:choose>
            <c:when test="${empty ordini}">
                <div class="alert-info">
                    <p><strong>Nessun ordine trovato per i filtri selezionati.</strong></p>
                </div>
            </c:when>
            <c:otherwise>
                <table class="tabella-ordini">
                    <thead>
                        <tr>
                            <th>ID Ordine</th>
                            <th>ID Cliente</th>
                            <th>Data</th>
                            <th>Totale</th>
                            <th>Stato</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${ordini}" var="o">
                            <tr>
                                <td><c:out value="${o.id}" /></td>
                                <td><c:out value="${o.idCliente}" /></td>
                                <td><c:out value="${o.data}" /></td>
                                <td>€ <c:out value="${o.totale}" /></td>
                                <td><c:out value="${o.stato}" /></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </main>
</body>
</html>