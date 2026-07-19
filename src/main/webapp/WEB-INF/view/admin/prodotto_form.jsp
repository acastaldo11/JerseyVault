<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Prodotto - JerseyVault Admin</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/admin-form.css">
</head>
<body>
    <main class="admin-form-container">
        <h1>
            <c:choose>
                <c:when test="${not empty prodotto}">Modifica Prodotto</c:when>
                <c:otherwise>Nuovo Prodotto</c:otherwise>
            </c:choose>
        </h1>

        <form action="${pageContext.request.contextPath}/admin/prodotto/${not empty prodotto ? 'modifica' : 'nuovo'}" method="post">
            
            <c:if test="${not empty prodotto}">
                <input type="hidden" name="id" value="<c:out value='${prodotto.id}'/>">
            </c:if>

            <div>
                <label>Nome:</label>
                <input type="text" name="nome" value="<c:out value='${prodotto.nome}'/>" required>
            </div>
            <div>
                <label>Descrizione:</label>
                <textarea name="descrizione" rows="4"><c:out value='${prodotto.descrizione}'/></textarea>
            </div>
            <div>
                <label>Squadra:</label>
                <input type="text" name="squadra" value="<c:out value='${prodotto.squadra}'/>" required>
            </div>
            <div>
                <label>Taglia:</label>
                <input type="text" name="taglia" value="<c:out value='${prodotto.taglia}'/>" required>
            </div>
            <div>
                <label>Prezzo:</label>
                <input type="number" step="0.01" name="prezzo" value="<c:out value='${prodotto.prezzo}'/>" required>
            </div>
            <div>
                <label>Giacenza:</label>
                <input type="number" name="giacenza" value="<c:out value='${prodotto.giacenza}'/>" required>
            </div>
            <div>
                <label>Categoria:</label>
                <select name="categoriaId">
                    <c:forEach items="${categorie}" var="cat">
                        <option value="<c:out value='${cat.id}'/>" 
                            ${prodotto.categoriaId == cat.id ? 'selected' : ''}>
                            <c:out value="${cat.nome}"/>
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <label>Immagine URL:</label>
                <input type="text" name="immagineUrl" value="<c:out value='${prodotto.immagineUrl}'/>">
            </div>
            
            <div class="form-actions">
                <button type="submit" class="btn-salva">Salva</button>
                <a href="${pageContext.request.contextPath}/admin/dashboard" class="btn-annulla">Annulla</a>
            </div>
        </form>
    </main>
</body>
</html>