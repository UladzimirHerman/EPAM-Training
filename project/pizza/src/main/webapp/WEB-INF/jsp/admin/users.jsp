<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="content"/>
    <title><fmt:message key="page.usersView.title"/></title>
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
            <table class="table text-center">
                <thead>
                <tr style="background-color:PowderBlue;">
                    <th><fmt:message key="page.usersView.table.attribute.id"/></th>
                    <th><fmt:message key="page.usersView.table.attribute.login"/></th>
                    <th><fmt:message key="page.usersView.table.attribute.person"/></th>
                    <th><fmt:message key="page.usersView.table.attribute.phone"/></th>
                    <th><fmt:message key="page.usersView.table.attribute.address"/></th>
                    <th><fmt:message key="page.usersView.table.attribute.note"/></th>
                    <th><fmt:message key="page.usersView.table.attribute.role"/></th>
                    <th><fmt:message key="page.usersView.table.attribute.action"/></th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="users" items="${users}">
                    <tr>
                        <td>
                            <small><c:out value="${users.id}"/></small>
                        </td>
                        <td>
                            <small><c:out value="${users.login}"/></small>
                        </td>
                        <td>
                            <small><c:out value="${users.userInfo.name}"/> <c:out
                                    value="${users.userInfo.surname}"/></small>
                        </td>
                        <td>
                            <small><c:out value="${users.userInfo.phone}"/></small>
                        </td>
                        <td>
                            <small>
                                <fmt:message key="page.usersView.table.value.city"/>
                                <c:out value="${users.userInfo.city}"/>
                                <fmt:message key="page.usersView.table.value.street"/>
                                <c:out value="${users.userInfo.street}"/>
                                <fmt:message key="page.usersView.table.value.building"/>
                                <c:out value="${users.userInfo.building}"/>
                                <c:if test="${not empty users.userInfo.housing}">
                                    <fmt:message key="page.usersView.table.value.housing"/>
                                    <c:out value="${users.userInfo.housing}"/>
                                </c:if>
                                <c:if test="${not empty users.userInfo.apartment}">
                                    <fmt:message key="page.usersView.table.value.apartment"/>
                                    <c:out value="${users.userInfo.apartment}"/>
                                </c:if>
                            </small>
                        </td>
                        <td>
                            <small><c:out value="${users.userInfo.note}"/></small>
                        </td>
                        <td>
                            <small><c:out value="${users.userRole}"/></small>
                        </td>
                        <td>
                            <form action="/admin/users/edit" method="get">
                                <input name="userId" type="hidden" value="<c:out value="${users.id}"/>">
                                <input type="submit" class="btn btn-primary"
                                       value="<fmt:message key="page.usersView.button.edit"/>"/>
                            </form>
                            <form action="/admin/users/delete" method="get">
                                <input name="userId" type="hidden" value="<c:out value="${users.id}"/>">
                                <input type="submit" class="btn btn-primary"
                                       value="<fmt:message key="page.usersView.button.delete"/>"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>

                <c:set var="pageCount" scope="page" value="${1}"/>
                <tr>
                    <td colspan="8">
                        <form action="/admin/users" method="get" class="form-inline">
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

</body>
</html>