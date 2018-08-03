<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="content"/>
    <title><fmt:message key="page.error.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</head>
<body>
<div class="jumbotron jumbotron-fluid" style="background-color:LightCoral;">
    <div class="container" align="center">
        <h1><fmt:message key="page.error.message.headline"/></h1>
        <p><fmt:message key="page.error.message.description"/></p>
        <form action="/authentication" method="post">
            <input name="command" type="hidden" value="empty">
            <input type="submit" class="btn btn-link" style="color: black"
                   value="<fmt:message key="page.error.button.continue"/>">
        </form>
    </div>
</div>
</body>
</html>