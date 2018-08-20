<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <c:set var="titleKey" scope="page" value="page.error.title"/>
    <%@ include file="/WEB-INF/jsp/fragment/common/resource.jspf" %>
</head>
<body>

<%@ include file="/WEB-INF/jsp/fragment/common/header.jspf" %>
<c:if test="${not empty sessionScope.user.userRole}">
    <%@ include file="/WEB-INF/jsp/fragment/common/menu.jspf" %>
</c:if>

<div class="container">
    <div class="row" align="center">
        <div class="col-sm-1"></div>
        <div class="col-sm-10" style="background-color:LightCoral;">
            <div class="jumbotron jumbotron-fluid" style="background-color:LightCoral;">
                <div class="container" align="center">
                    <h1><fmt:message key="page.error.message.headline"/></h1>
                    <p><fmt:message key="page.error.message.description"/></p>

                    <c:if test="${empty sessionScope.user.userRole}">
                        <form action="/authentication" method="post">
                            <input name="command" type="hidden" value="empty">
                            <input type="submit" class="btn btn-link" style="color: black"
                                   value="<fmt:message key="page.error.button.continue"/>">
                        </form>
                    </c:if>

                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>