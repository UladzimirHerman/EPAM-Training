<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="content"/>
    <title><fmt:message key="page.newsView.title"/></title>
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
        <div class="col-sm-10" style="background-color:AliceBlue;"><br>
            <div class="row" align="center">

                <table class="table table-borderless">
                    <tbody>
                    <tr>
                        <td align="right">
                            <form action="/admin/news/create" method="post">
                                <b><fmt:message key="page.newsView.phrase.create"/></b>
                                <input type="submit" class="btn btn-primary"
                                       value="<fmt:message key="page.newsView.button.create"/>"/>
                            </form>
                        </td>
                    </tr>
                    </tbody>
                </table>

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
                                <div class="btn-group">
                                    <form action="/admin/news/edit" method="get">
                                        <input name="newsId" type="hidden" value="<c:out value="${newsList.id}"/>"/>
                                        <input type="submit" class="btn btn-primary"
                                               value="<fmt:message key="page.newsView.button.edit"/>"/>
                                    </form>
                                    <form action="/admin/news/delete" method="get">
                                        <input name="newsId" type="hidden" value="<c:out value="${newsList.id}"/>"/>
                                        <input type="submit" class="btn btn-primary"
                                               value="<fmt:message key="page.newsView.button.delete"/>"/>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>

                <table class="table">
                    <tbody>
                    <tr>
                        <td>
                            <c:set var="pageCount" scope="page" value="${1}"/>
                            <form action="/admin/news" method="get" class="form-inline">
                                <b><fmt:message key="page.common.pagination.page"/></b>
                                <select name="page" class="form-control" required>
                                    <c:forEach begin="${pageCount}" end="${pageQuantity}">
                                        <option value="${pageCount}" <c:if
                                                test="${pageCount == param.page}"> selected="selected" </c:if> >${pageCount}</option>
                                        <c:set var="pageCount" scope="page" value="${pageCount+1}"/>
                                    </c:forEach>
                                </select>
                                <input type="submit" class="btn btn-primary"
                                       value="<fmt:message key="page.common.pagination.button"/>"/>
                            </form>
                        </td>
                    </tr>
                    </tbody>
                </table>

            </div>
        </div>
    </div>
</div>

</body>
</html>