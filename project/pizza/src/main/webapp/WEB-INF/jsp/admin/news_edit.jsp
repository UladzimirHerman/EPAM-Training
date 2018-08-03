<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="content"/>
    <title><fmt:message key="page.newsEdit.title"/></title>
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

            <form action="/admin/news/edit?newsId=${news.id}" method="post">
                <table class="table">
                    <tbody>
                    <c:if test="${message}">
                        <tr>
                            <td colspan="2">
                                <div class="alert alert-danger" align="center">
                                    <fmt:message key="page.newsEdit.message.error"/>
                                </div>
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td>
                            <h4><b><fmt:message key="page.newsEdit.table.attribute.title"/></b></h4>
                        </td>
                        <td>
                            <input name="title" type="text" class="form-control" size="1"
                                   value="<c:out value="${news.title}"/>"
                                   minlength="5" maxlength="50" required>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h4><b><fmt:message key="page.newsEdit.table.attribute.content"/></b></h4>
                        </td>
                        <td>
                            <textarea name="content" class="form-control" rows="4"
                                      minlength="10" maxlength="255" required><c:out
                                    value="${news.content}"/></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h4><b><fmt:message key="page.newsEdit.table.attribute.photo"/></b></h4>
                        </td>
                        <td>
                            <input name="photo" type="file" class="form-control-file border" size="1"
                                   value="<c:out value="${news.photo}"/>"
                                   maxlength="100" required>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input name="newsId" type="hidden" value="<c:out value="${news.id}"/>"/>
                            <input name="command" type="hidden" value="admin_update_news">
                            <input type="submit" class="btn btn-primary"
                                   value="<fmt:message key="page.newsEdit.button.edit"/>"/>
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