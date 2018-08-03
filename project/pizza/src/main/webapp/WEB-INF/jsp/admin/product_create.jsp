<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="content"/>
    <title><fmt:message key="page.productCreate.title"/></title>
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

            <form action="/admin/product/create" method="post">
                <table class="table">
                    <tbody>
                    <c:if test="${message}">
                        <tr>
                            <td colspan="2">
                                <div class="alert alert-danger" align="center">
                                    <fmt:message key="page.productCreate.message.error"/>
                                </div>
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td>
                            <h4><b><fmt:message key="page.productCreate.table.attribute.name"/></b></h4>
                        </td>
                        <td>
                            <input name="name" type="text" class="form-control" size="1"
                                   placeholder="<fmt:message key="page.productCreate.table.placeholder.name"/>"
                                   minlength="5" maxlength="50" required>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h4><b><fmt:message key="page.productCreate.table.attribute.description"/></b></h4>
                        </td>
                        <td>
                            <textarea name="description" class="form-control" rows="3"
                                      placeholder="<fmt:message key="page.productCreate.table.placeholder.description"/>"
                                      minlength="10" maxlength="150" required></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h4><b><fmt:message key="page.productCreate.table.attribute.price"/></b></h4>
                        </td>
                        <td>
                            <input name="price" type="number" class="form-control"
                                   placeholder="<fmt:message key="page.productCreate.table.placeholder.price"/>"
                                   min="0" max="50" step=".01" required>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h4><b><fmt:message key="page.productCreate.table.attribute.photo"/></b></h4>
                        </td>
                        <td>
                            <input name="photo" type="file" class="form-control-file border" size="1"
                                   maxlength="100" required>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h4><b><fmt:message key="page.productCreate.table.attribute.sale"/></b></h4>
                        </td>
                        <td>
                            <select name="sale" class="form-control" required>
                                <option value="true"><fmt:message
                                        key="page.productCreate.table.placeholder.sale.true"/></option>
                                <option value="false"><fmt:message
                                        key="page.productCreate.table.placeholder.sale.false"/></option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input name="command" type="hidden" value="admin_add_product">
                            <input type="submit" class="btn btn-primary"
                                   value="<fmt:message key="page.productCreate.button.create"/>"/>
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