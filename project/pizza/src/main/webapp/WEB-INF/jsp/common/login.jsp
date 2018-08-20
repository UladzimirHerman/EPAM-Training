<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <c:set var="titleKey" scope="page" value="page.login.title"/>
    <%@ include file="/WEB-INF/jsp/fragment/common/resource.jspf" %>
</head>
<body>

<%@ include file="/WEB-INF/jsp/fragment/common/header.jspf" %>

<div class="container">
    <div class="row" align="center">
        <div class="col-sm-4"></div>
        <div class="col-sm-4" style="background-color:AliceBlue;">
            <form action="/authentication" method="post">
                <h2><b><fmt:message key="page.login.headline"/></b></h2>

                <fmt:message key="page.login.message.error" var="messageText"/>
                <%@ include file="/WEB-INF/jsp/fragment/common/error_message.jspf" %>

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