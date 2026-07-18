<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Login - JerseyVault</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/login.css">
</head>
<body>
    <%@ include file="/WEB-INF/view/header.jsp" %>
    <main class="login-client-container">
        <h2>Accedi al tuo account</h2>

        <c:if test="${not empty error}">
            <div class="alert-error">
                <c:out value="${error}" />
            </div>
        </c:if>

        <div id="erroriLogin" class="alert-error"></div>

        <form id="formLogin" action="${pageContext.request.contextPath}/login" method="post">
            <div>
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="<c:out value='${param.email}'/>" required>
            </div>
            <div>
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <br>
            <button type="submit" class="btn-login">Accedi</button>
        </form>
        <p class="testo-registrazione">Non hai un account? <a href="${pageContext.request.contextPath}/registrazione">Registrati qui</a></p>
    </main>
    <%@ include file="/WEB-INF/view/footer.jsp" %>
    <script src="${pageContext.request.contextPath}/scripts/validazione.js"></script>
</body>
</html>