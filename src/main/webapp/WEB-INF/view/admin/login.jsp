<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Admin Login - JerseyVault</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/admin-login.css">
</head>
<body>
    <main class="login-container">
        <h2>Area Amministratore</h2>
        
        <c:if test="${not empty error}">
            <div class="alert-error">
                <c:out value="${error}" />
            </div>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/admin/login" method="post">
            <div>
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div>
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <br>
            <button type="submit">Accedi</button>
        </form>
    </main>
</body>
</html>