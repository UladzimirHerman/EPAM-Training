<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <c:set var="titleKey" scope="page" value="page.newsView.title"/>
    <%@ include file="/WEB-INF/jsp/fragment/common/resource.jspf" %>
</head>
<body>

<%@ include file="/WEB-INF/jsp/fragment/common/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragment/common/menu.jspf" %>

<div class="container">
    <div class="row" align="center">
        <div class="col-sm-1"></div>
        <div class="col-sm-10" style="background-color:AliceBlue;"><br>
            <div class="row" align="center">

                <c:if test="${sessionScope.user.userRole eq 'ADMIN'}">
                    <%@ include file="/WEB-INF/jsp/fragment/admin/news_create_button.jspf" %>
                </c:if>

                <c:forEach var="newsList" items="${newsList}">
                    <div class="col-sm-6 py-2">
                        <div class="card h-100" align="center">
                            <img class="card-img-top"
                                 src="${pageContext.request.contextPath}/resources/images/<c:out value="${newsList.photo}"/>"
                                 alt="<c:out value="${newsList.photo}"/>" style="width:100%">
                            <div class="card-body" align="center">
                                <h4 class="card-title"><c:out value="${newsList.title}"/></h4>
                                <p class="card-text"><c:out value="${newsList.content}"/></p>
                                <p class="card-text" align="right"><c:out value="${newsList.date}"/></p>

                                <c:if test="${sessionScope.user.userRole eq 'ADMIN'}">
                                    <%@ include file="/WEB-INF/jsp/fragment/admin/news_edit_button.jspf" %>
                                </c:if>

                            </div>
                        </div>
                    </div>
                </c:forEach>

                <c:set var="action" scope="page" value="/${fn:toLowerCase(sessionScope.user.userRole)}/news"/>
                <%@ include file="/WEB-INF/jsp/fragment/common/paginaton.jspf" %>

            </div>
        </div>
    </div>
</div>

</body>
</html>