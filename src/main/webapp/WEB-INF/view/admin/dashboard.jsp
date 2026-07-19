<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Dashboard Admin - JerseyVault</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/admin-dashboard.css">
</head>
<body>
    <header class="admin-header">
        <h1>Pannello Amministratore</h1>
        <nav>
            <a href="${pageContext.request.contextPath}/admin/logout" class="btn-logout">Logout</a>
        </nav>
    </header>
    
    <main>
        <div class="dashboard-actions">
            <a href="${pageContext.request.contextPath}/admin/prodotto/nuovo" class="btn-action">Aggiungi Nuovo Prodotto</a>
            <a href="${pageContext.request.contextPath}/admin/ordini" class="btn-action">Visualizza Ordini</a>
        </div>
        
        <h2>Lista Prodotti in Catalogo</h2>
        <table class="tabella-admin">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Squadra</th>
                    <th>Prezzo</th>
                    <th>Giacenza</th>
                    <th>Azioni</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${prodotti}" var="p">
                    <tr>
                        <td><c:out value="${p.id}" /></td>
                        <td><c:out value="${p.nome}" /></td>
                        <td><c:out value="${p.squadra}" /></td>
                        <td>€ <c:out value="${p.prezzo}" /></td>
                        <td><c:out value="${p.giacenza}" /></td>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/prodotto/modifica?id=<c:out value='${p.id}'/>">Modifica</a>
                            |
                            <a href="${pageContext.request.contextPath}/admin/prodotto/elimina?id=<c:out value='${p.id}'/>" onclick="return confirm('Confermi l\'eliminazione irreversibile del prodotto?');">Elimina</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </main>
</body>
</html>