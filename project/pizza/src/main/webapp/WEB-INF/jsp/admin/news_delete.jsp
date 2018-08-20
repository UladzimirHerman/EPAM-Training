<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <c:set var="titleKey" scope="page" value="page.newsDelete.title"/>
    <%@ include file="/WEB-INF/jsp/fragment/common/resource.jspf" %>
</head>
<body>

<%@ include file="/WEB-INF/jsp/fragment/common/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragment/common/menu.jspf" %>

<div class="container">
    <div class="row" align="center">
        <div class="col-sm-1"></div>
        <div class="col-sm-10" style="background-color:AliceBlue;">
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