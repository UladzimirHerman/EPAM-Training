<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="content"/>
    <title><fmt:message key="page.login.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/jsp/fragment/header.jspf" %>
<div class="container">
    <div class="row" align="center">
        <div class="col-sm-4"></div>
        <div class="col-sm-4" style="background-color:AliceBlue;">
            <form action="/authentication" method="post">
                <h2><b><fmt:message key="page.login.headline"/></b></h2>
                <c:if test="${message}">
                    <div class="alert alert-danger" align="center">
                        <fmt:message key="page.login.message.error"/>
                    </div>
                </c:if>
                <input name="login" type="email" class="form-control"
                       placeholder="<fmt:message key="page.login.placeholder.email"/>" required>
                <input name="password" type="password" class="form-control"
                       placeholder="<fmt:message key="page.login.placeholder.password"/>" required>
                <input name="command" type="hidden" value="login">
                <input type="submit" class="btn btn-primary" value="<fmt:message key="page.login.button.login"/>">
            </form>
            <form action="/registration" method="post">
                <input type="submit" class="btn btn-link" value="<fmt:message key="page.login.button.registration"/>">
            </form>
        </div>
    </div>
</div>
</body>
</html>