<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="content"/>
    <title><fmt:message key="page.feedback.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/jsp/fragment/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragment/menu.jspf" %>

<div class="container">
    <div class="row" align="center">
        <div class="col-sm-1"></div>
        <div class="col-sm-10" style="background-color:AliceBlue;">
            <table class="table">
                <thead>
                <tr style="background-color:PowderBlue;">
                    <th>
                        <h4 align="center"><b><fmt:message key="page.feedback.table.attribute.title"/></b></h4>
                    </th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="feedbackList" items="${feedbackList}">
                    <tr>
                        <td>
                            <b><c:out value="${feedbackList.user.userInfo.name}"/> <i><c:out
                                value="${feedbackList.date}"/></i> <fmt:message key="page.feedback.rating"/> <c:out
                                value="${feedbackList.rating}"/><fmt:message key="page.feedback.maxRating"/></b>
                            <p><c:out value="${feedbackList.comment}"/>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>