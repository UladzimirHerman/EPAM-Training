<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="content"/>
    <title><fmt:message key="page.newsDelete.title"/></title>
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
            <form action="/admin/news/delete?newsId=${news.id}" method="post">
                <table class="table">
                    <tbody>
                    <tr>
                        <td>
                            <h4><b><fmt:message key="page.newsDelete.phrase"/><c:out value="${news.title}"/></b></h4>
                        </td>
                        <td>
                            <input name="newsId" type="hidden" value="<c:out value="${news.id}"/>"/>
                            <input name="command" type="hidden" value="admin_delete_news">
                            <input type="submit" class="btn btn-primary"
                                   value="<fmt:message key="page.newsDelete.button.delete"/>"/>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>
</div>

</body>
</html>