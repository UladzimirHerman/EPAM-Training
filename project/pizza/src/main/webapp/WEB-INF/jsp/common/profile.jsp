<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <c:set var="titleKey" scope="page" value="page.profile.title"/>
    <%@ include file="/WEB-INF/jsp/fragment/common/resource.jspf" %>
</head>
<body>

<%@ include file="/WEB-INF/jsp/fragment/common/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragment/common/menu.jspf" %>

<div class="container">
    <div class="row" align="center">
        <div class="col-sm-1"></div>
        <div class="col-sm-10" style="background-color:AliceBlue;">
            <form action="/${fn:toLowerCase(sessionScope.user.userRole)}/profile" method="post">

                <fmt:message key="page.profile.message.error" var="messageText"/>
                <%@ include file="/WEB-INF/jsp/fragment/common/error_message.jspf" %>

                <table class="table">
                    <thead>
                    <tr style="background-color:PowderBlue;">
                        <th colspan="2">
                            <h4 align="center"><b><fmt:message key="page.profile.headline.editProfile"/></b></h4>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            <h4><b><fmt:message key="page.profile.table.attribute.email"/></b></h4>
                        </td>
                        <td>
                            <input name="login" type="email" class="form-control" size="1"
                                   value="<c:out value="${user.login}"/>" maxlength="255" disabled>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h4><b><fmt:message key="page.profile.table.attribute.name"/></b></h4>
                        </td>
                        <td>
                            <input name="name" type="text" class="form-control" size="1"
                                   value="<c:out value="${userInfo.name}"/>" maxlength="100" required>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h4><b><fmt:message key="page.profile.table.attribute.surname"/></b></h4>
                        </td>
                        <td>
                            <input name="surname" type="text" class="form-control" size="1"
                                   value="<c:out value="${userInfo.surname}"/>" maxlength="100" required>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h4><b><fmt:message key="page.profile.table.attribute.phone"/></b></h4>
                        </td>
                        <td>
                            <input name="phone" type="tel" pattern="375[0-9]{9}" class="form-control" size="1"
                                   value="<c:out value="${userInfo.phone}"/>" minlength="12" maxlength="12" required>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h4><b><fmt:message key="page.profile.table.attribute.city"/></b></h4>
                        </td>
                        <td>
                            <input name="city" type="text" class="form-control" size="1"
                                   value="<c:out value="${userInfo.city}"/>" maxlength="25" required>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h4><b><fmt:message key="page.profile.table.attribute.street"/></b></h4>
                        </td>
                        <td>
                            <input name="street" type="text" class="form-control" size="1"
                                   value="<c:out value="${userInfo.street}"/>" maxlength="25" required>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h4><b><fmt:message key="page.profile.table.attribute.building"/></b></h4>
                        </td>
                        <td>
                            <input name="building" type="text" class="form-control" size="1"
                                   value="<c:out value="${userInfo.building}"/>" maxlength="5" required>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h4><b><fmt:message key="page.profile.table.attribute.housing"/></b></h4>
                        </td>
                        <td>
                            <input name="housing" type="text" class="form-control" size="1"
                                   value="<c:out value="${userInfo.housing}"/>" maxlength="5">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h4><b><fmt:message key="page.profile.table.attribute.apartment"/></b></h4>
                        </td>
                        <td>
                            <input name="apartment" type="text" class="form-control" size="1"
                                   value="<c:out value="${userInfo.apartment}"/>" maxlength="5">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h4><b><fmt:message key="page.profile.table.attribute.note"/></b></h4>
                        </td>
                        <td>
                            <textarea name="note" class="form-control" rows="3" cols="1" maxlength="255"><c:out
                                    value="${userInfo.note}"/></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input name="command" type="hidden" value="profile_edit">
                            <input type="submit" class="btn btn-primary"
                                   value="<fmt:message key="page.profile.button.editProfile"/>"/>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </form>

            <form action="/${fn:toLowerCase(sessionScope.user.userRole)}/profile" method="post">
                <table class="table">
                    <thead>
                    <tr style="background-color:PowderBlue;">
                        <th colspan="2">
                            <h4 align="center"><b><fmt:message key="page.profile.headline.editPassword"/></b></h4>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            <h4><b><fmt:message key="page.profile.table.attribute.password"/></b></h4>
                        </td>
                        <td>
                            <input name="password" type="password" class="form-control" size="1"
                                   placeholder="<fmt:message key="page.profile.table.placeholder.password"/>"
                                   minlength="6" required>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h4><b><fmt:message key="page.profile.table.attribute.newPassword"/></b></h4>
                        </td>
                        <td>
                            <input name="newPassword" type="password" class="form-control" size="1"
                                   placeholder="<fmt:message key="page.profile.table.placeholder.newPassword"/>"
                                   minlength="6" required>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h4><b><fmt:message key="page.profile.table.attribute.newPasswordRepeat"/></b></h4>
                        </td>
                        <td>
                            <input name="newPasswordRepeat" type="password" class="form-control" size="1"
                                   placeholder="<fmt:message key="page.profile.table.placeholder.newPasswordRepeat"/>"
                                   minlength="6" required>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input name="command" type="hidden" value="password_edit">
                            <input type="submit" class="btn btn-primary"
                                   value="<fmt:message key="page.profile.button.editPassword"/>"/>
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