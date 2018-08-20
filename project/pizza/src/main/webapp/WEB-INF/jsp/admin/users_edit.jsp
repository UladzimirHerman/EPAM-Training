<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <c:set var="titleKey" scope="page" value="page.usersEdit.title"/>
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
                    <th><fmt:message key="page.usersEdit.table.attribute.id"/></th>
                    <th><fmt:message key="page.usersEdit.table.attribute.login"/></th>
                    <th><fmt:message key="page.usersEdit.table.attribute.person"/></th>
                    <th><fmt:message key="page.usersEdit.table.attribute.role"/></th>
                    <th colspan="2"><fmt:message key="page.usersEdit.table.attribute.action"/></th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                        <c:out value="${user.id}"/>
                    </td>
                    <td>
                        <c:out value="${user.login}"/>
                    </td>
                    <td>
                        <c:out value="${user.userInfo.name}"/> <c:out value="${user.userInfo.surname}"/>
                    </td>
                    <td>
                        <c:out value="${user.userRole}"/>
                    </td>
                    <form action="/admin/users/edit?userId=${user.id}" method="post">
                        <td>
                            <select name="userRole" class="form-control" required>
                                <option value="USER"><fmt:message
                                        key="page.usersEdit.table.placeholder.role.user"/></option>
                                <option value="ADMIN"><fmt:message
                                        key="page.usersEdit.table.placeholder.role.admin"/></option>
                            </select>
                        </td>
                        <td>
                            <input name="userId" type="hidden" value="<c:out value="${user.id}"/>">
                            <input name="command" type="hidden" value="admin_update_role">
                            <input type="submit" class="btn btn-primary"
                                   value="<fmt:message key="page.usersEdit.button.edit"/>"/>
                        </td>
                    </form>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>