<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <c:set var="titleKey" scope="page" value="page.feedback.title"/>
    <%@ include file="/WEB-INF/jsp/fragment/common/resource.jspf" %>
</head>
<body>

<%@ include file="/WEB-INF/jsp/fragment/common/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragment/common/menu.jspf" %>

<div class="container">
    <div class="row" align="center">
        <div class="col-sm-1"></div>
        <div class="col-sm-10" style="background-color:AliceBlue;">

            <c:if test="${sessionScope.user.userRole eq 'USER'}">
                <%@ include file="/WEB-INF/jsp/fragment/user/feedback_create_button.jspf" %>
            </c:if>

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
                             <c:if test="${sessionScope.user.id == feedbackList.user.id}">
                                <%@ include file="/WEB-INF/jsp/fragment/user/feedback_edit_button.jspf" %>
                             </c:if>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>

            <c:set var="action" scope="page" value="/${fn:toLowerCase(sessionScope.user.userRole)}/feedback"/>
            <%@ include file="/WEB-INF/jsp/fragment/common/paginaton.jspf" %>

        </div>
    </div>
</div>

</body>
</html>