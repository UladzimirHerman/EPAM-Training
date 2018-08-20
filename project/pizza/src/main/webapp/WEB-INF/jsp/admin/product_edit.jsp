<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <c:set var="titleKey" scope="page" value="page.productEdit.title"/>
    <%@ include file="/WEB-INF/jsp/fragment/common/resource.jspf" %>
</head>
<body>

<%@ include file="/WEB-INF/jsp/fragment/common/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragment/common/menu.jspf" %>

<div class="container">
    <div class="row" align="center">
        <div class="col-sm-1"></div>
        <div class="col-sm-10" style="background-color:AliceBlue;">
            <form action="/admin/product/edit?productId=${product.id}" method="post">

                <fmt:message key="page.productEdit.message.error" var="messageText"/>
                <%@ include file="/WEB-INF/jsp/fragment/common/error_message.jspf" %>

                <table class="table">
                    <tbody>
                    <tr>
                        <td>
                            <h4><b><fmt:message key="page.productEdit.table.attribute.name"/></b></h4>
                        </td>
                        <td>
                            <input name="name" type="text" class="form-control" size="1"
                                   value="<c:out value="${product.name}"/>" minlength="5" maxlength="50" required>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h4><b><fmt:message key="page.productEdit.table.attribute.description"/></b></h4>
                        </td>
                        <td>
                            <textarea name="description" class="form-control" rows="3" minlength="10"
                                      maxlength="150" required><c:out value="${product.description}"/></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h4><b><fmt:message key="page.productEdit.table.attribute.price"/></b></h4>
                        </td>
                        <td>
                            <input name="price" type="number" class="form-control"
                                   value="<c:out value="${product.price}"/>" min="0" max="50" step=".01" required>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h4><b><fmt:message key="page.productEdit.table.attribute.photo"/></b></h4>
                        </td>
                        <td>
                            <input name="photo" type="file" class="form-control-file border" size="1"
                                   maxlength="100" required>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h4><b><fmt:message key="page.productEdit.table.attribute.sale"/></b></h4>
                        </td>
                        <td>
                            <select name="sale" class="form-control" required>
                                <option value="true"><fmt:message
                                        key="page.productEdit.table.placeholder.sale.true"/></option>
                                <option value="false"><fmt:message
                                        key="page.productEdit.table.placeholder.sale.false"/></option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input name="productId" type="hidden" value="<c:out value="${product.id}"/>"/>
                            <input name="command" type="hidden" value="admin_update_product">
                            <input type="submit" class="btn btn-primary"
                                   value="<fmt:message key="page.productEdit.button.edit"/>"/>
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