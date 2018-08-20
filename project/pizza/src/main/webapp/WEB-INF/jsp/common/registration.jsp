<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <c:set var="titleKey" scope="page" value="page.registration.title"/>
    <%@ include file="/WEB-INF/jsp/fragment/common/resource.jspf" %>
</head>
<body>

<%@ include file="/WEB-INF/jsp/fragment/common/header.jspf" %>

<div class="container">
    <div class="row" align="center">
        <div class="col-sm-1"></div>
        <div class="col-sm-10" style="background-color:AliceBlue;">
            <form action="/registration" method="post">

                <fmt:message key="page.registration.message.error" var="messageText"/>
                <%@ include file="/WEB-INF/jsp/fragment/common/error_message.jspf" %>

                <table class="table">
                    <thead>
                    <tr style="background-color:PowderBlue;">
                        <th colspan="2">
                            <h4 align="center"><b><fmt:message key="page.registration.headline"/></b></h4>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            <h4><b><fmt:message key="page.registration.table.attribute.email"/></b></h4>
                        </td>
                        <td>
                            <input name="login" type="email" class="form-control" size="1"
                                   placeholder="<fmt:message key="page.registration.table.placeholder.email"/>"
                                   maxlength="255" required>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h4><b><fmt:message key="page.registration.table.attribute.password"/></b></h4>
                        </td>
                        <td>
                            <input name="password" type="password" class="form-control" size="1"
                                   placeholder="<fmt:message key="page.registration.table.placeholder.password"/>"
                                   minlength="6" required>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h4><b><fmt:message key="page.registration.table.attribute.name"/></b></h4>
                        </td>
                        <td>
                            <input name="name" type="text" class="form-control" size="1"
                                   placeholder="<fmt:message key="page.registration.table.placeholder.name"/>"
                                   maxlength="100" required>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h4><b><fmt:message key="page.registration.table.attribute.surname"/></b></h4>
                        </td>
                        <td>
                            <input name="surname" type="text" class="form-control" size="1"
                                   placeholder="<fmt:message key="page.registration.table.placeholder.surname"/>"
                                   maxlength="100" required>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h4><b><fmt:message key="page.registration.table.attribute.phone"/></b></h4>
                        </td>
                        <td>
                            <input name="phone" type="tel" pattern="375[0-9]{9}" class="form-control" size="1"
                                   placeholder="<fmt:message key="page.registration.table.placeholder.phone"/>"
                                   minlength="12" maxlength="12" required>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h4><b><fmt:message key="page.registration.table.attribute.city"/></b></h4>
                        </td>
                        <td>
                            <input name="city" type="text" class="form-control" size="1"
                                   placeholder="<fmt:message key="page.registration.table.placeholder.city"/>"
                                   maxlength="25" required>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h4><b><fmt:message key="page.registration.table.attribute.street"/></b></h4>
                        </td>
                        <td>
                            <input name="street" type="text" class="form-control" size="1"
                                   placeholder="<fmt:message key="page.registration.table.placeholder.street"/>"
                                   maxlength="25" required>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h4><b><fmt:message key="page.registration.table.attribute.building"/></b></h4>
                        </td>
                        <td>
                            <input name="building" type="text" class="form-control" size="1"
                                   placeholder="<fmt:message key="page.registration.table.placeholder.building"/>"
                                   maxlength="5" required>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h4><b><fmt:message key="page.registration.table.attribute.housing"/></b></h4>
                        </td>
                        <td>
                            <input name="housing" type="text" class="form-control" size="1"
                                   placeholder="<fmt:message key="page.registration.table.placeholder.housing"/>"
                                   maxlength="5">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h4><b><fmt:message key="page.registration.table.attribute.apartment"/></b></h4>
                        </td>
                        <td>
                            <input name="apartment" type="text" class="form-control" size="1"
                                   placeholder="<fmt:message key="page.registration.table.placeholder.apartment"/>"
                                   maxlength="5">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h4><b><fmt:message key="page.registration.table.attribute.note"/></b></h4>
                        </td>
                        <td>
                            <textarea name="note" class="form-control" rows="3" cols="1"
                                      placeholder="<fmt:message key="page.registration.table.placeholder.note"/>"
                                      maxlength="255"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input name="command" type="hidden" value="create_account">
                            <input type="submit" class="btn btn-primary"
                                   value="<fmt:message key="page.registration.button.registration"/>"/>
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