<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Registrazione - JerseyVault</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/registrazione.css">
</head>
<body>
    <%@ include file="/WEB-INF/view/header.jsp" %>
    <main class="registrazione-container">
        <h2>Crea un nuovo account</h2>

        <c:if test="${not empty error}">
            <div class="alert-error">
                <c:out value="${error}" />
            </div>
        </c:if>

        <div id="erroriRegistrazione" class="alert-error"></div>

        <form id="formRegistrazione" action="${pageContext.request.contextPath}/registrazione" method="post">
            <div>
                <label for="nome">Nome:</label>
                <input type="text" id="nome" name="nome" value="<c:out value='${param.nome}'/>" required>
            </div>
            <div>
                <label for="cognome">Cognome:</label>
                <input type="text" id="cognome" name="cognome" value="<c:out value='${param.cognome}'/>" required>
            </div>
            <div>
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="<c:out value='${param.email}'/>" required>
            </div>
            <div>
                <label for="telefono">Telefono:</label>
                <input type="tel" id="telefono" name="telefono" value="<c:out value='${param.telefono}'/>" required>
            </div>
            <div>
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <br>
            <button type="submit" class="btn-registrazione">Registrati</button>
        </form>
        <p class="testo-login">Hai già un account? <a href="${pageContext.request.contextPath}/login">Accedi qui</a></p>
    </main>
    <%@ include file="/WEB-INF/view/footer.jsp" %>
    <script src="${pageContext.request.contextPath}/scripts/validazione.js"></script>
</body>
</html>