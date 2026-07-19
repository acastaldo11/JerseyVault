<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/header.css">

<header>
    <h1><a href="${pageContext.request.contextPath}/catalogo">JerseyVault</a></h1>
    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/catalogo">Catalogo</a></li>
            <c:if test="${not empty sessionScope.utenteLoggato}">
                <li><a href="${pageContext.request.contextPath}/carrello">Carrello</a></li>
                <li><a href="${pageContext.request.contextPath}/ordini">I miei ordini</a></li>
                <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
            </c:if>
            <c:if test="${empty sessionScope.utenteLoggato}">
                <li><a href="${pageContext.request.contextPath}/login">Login</a></li>
                <li><a href="${pageContext.request.contextPath}/registrazione">Registrati</a></li>
            </c:if>
        </ul>
    </nav>
</header>