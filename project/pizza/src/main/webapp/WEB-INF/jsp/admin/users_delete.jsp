<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <c:set var="titleKey" scope="page" value="page.usersDelete.title"/>
    <%@ include file="/WEB-INF/jsp/fragment/common/resource.jspf" %>
</head>
<body>

<%@ include file="/WEB-INF/jsp/fragment/common/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragment/common/menu.jspf" %>

<div class="container">
    <div class="row" align="center">
        <div class="col-sm-1"></div>
        <div class="col-sm-10" style="background-color:AliceBlue;">
            <table class="table text-center">
                <thead>
                <tr style="background-color:PowderBlue;">
                    <th><fmt:message key="page.usersDelete.table.attribute.id"/></th>
                    <th><fmt:message key="page.usersDelete.table.attribute.login"/></th>
                    <th><fmt:message key="page.usersDelete.table.attribute.person"/></th>
                    <th><fmt:message key="page.usersDelete.table.attribute.phone"/></th>
                    <th><fmt:message key="page.usersDelete.table.attribute.address"/></th>
                    <th><fmt:message key="page.usersDelete.table.attribute.note"/></th>
                    <th><fmt:message key="page.usersDelete.table.attribute.role"/></th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                        <small><c:out value="${user.id}"/></small>
                    </td>
                    <td>
                        <small><c:out value="${user.login}"/></small>
                    </td>
                    <td>
                        <small><c:out value="${user.userInfo.name}"/> <c:out value="${user.userInfo.surname}"/></small>
                    </td>
                    <td>
                        <small><c:out value="${user.userInfo.phone}"/></small>
                    </td>
                    <td>
                        <small>
                            <fmt:message key="page.usersDelete.table.value.city"/>
                            <c:out value="${user.userInfo.city}"/>
                            <fmt:message key="page.usersDelete.table.value.street"/>
                            <c:out value="${user.userInfo.street}"/>
                            <fmt:message key="page.usersDelete.table.value.building"/>
                            <c:out value="${user.userInfo.building}"/>
                            <c:if test="${not empty user.userInfo.housing}">
                                <fmt:message key="page.usersDelete.table.value.housing"/>
                                <c:out value="${user.userInfo.housing}"/>
                            </c:if>
                            <c:if test="${not empty user.userInfo.apartment}">
                                <fmt:message key="page.usersDelete.table.value.apartment"/>
                                <c:out value="${user.userInfo.apartment}"/>
                            </c:if>
                        </small>
                    </td>
                    <td>
                        <small><c:out value="${user.userInfo.note}"/></small>
                    </td>
                    <td>
                        <small><c:out value="${user.userRole}"/></small>
                    </td>
                </tr>
                <tr>
                    <td colspan="5" align="right">
                        <b><fmt:message key="page.usersDelete.table.attribute.question"/></b>
                    </td>
                    <td colspan="2">
                        <form action="/admin/users/delete?userId=${user.id}" method="post">
                            <input name="userId" type="hidden" value="<c:out value="${user.id}"/>">
                            <input name="command" type="hidden" value="admin_delete_user">
                            <input type="submit" class="btn btn-primary"
                                   value="<fmt:message key="page.usersDelete.button.delete"/>"/>
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