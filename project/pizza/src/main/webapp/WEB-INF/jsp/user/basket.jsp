<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="content"/>
    <title><fmt:message key="page.basket.title"/></title>
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
            <div class="row" align="center">

                <table class="table text-center">
                    <thead>
                    <tr style="background-color:PowderBlue;">
                        <th><fmt:message key="page.basket.table.attribute.product"/></th>
                        <th><fmt:message key="page.basket.table.attribute.price"/></th>
                        <th><fmt:message key="page.basket.table.attribute.quantity"/></th>
                        <th><fmt:message key="page.basket.table.attribute.cost"/></th>
                        <th><fmt:message key="page.basket.table.attribute.delete"/></th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:set var="total" scope="page" value="${0}"/>
                    <c:forEach var="orderInfo" items="${orderInfoList}">
                        <tr>
                            <td>
                                <c:out value="${orderInfo.product.name}"/>
                            </td>
                            <td>
                                <c:out value="${orderInfo.product.price}"/>
                            </td>
                            <td>
                                <form action="/user/basket" method="post" class="form-inline">
                                    <input name="quantity" type="number" class="form-control"
                                           value="<c:out value="${orderInfo.quantity}"/>" min="1" max="5" step="1"
                                           required>
                                    <input name="orderInfoId" type="hidden" value="<c:out value="${orderInfo.id}"/>">
                                    <input name="command" type="hidden" value="user_update_order_info">
                                    <input type="submit" class="btn btn-primary"
                                           value="<fmt:message key="page.basket.button.edit"/>"/>
                                </form>
                            </td>
                            <td>
                                <ctg:cost-definition price="${orderInfo.product.price}"
                                                     quantity="${orderInfo.quantity}"/>
                            </td>
                            <td>
                                <form action="/user/basket" method="post">
                                    <input name="orderInfoId" type="hidden" value="<c:out value="${orderInfo.id}"/>">
                                    <input name="command" type="hidden" value="user_delete_order_info">
                                    <input type="submit" class="btn btn-primary"
                                           value="<fmt:message key="page.basket.button.delete"/>"/>
                                </form>
                            </td>
                            <c:set var="total" scope="page"
                                   value="${total + orderInfo.product.price*orderInfo.quantity}"/>
                            <c:set var="orderId" scope="page" value="${orderInfo.order.id}"/>
                        </tr>
                    </c:forEach>

                    <tr>
                        <td colspan="4" align="right">
                            <b><fmt:message key="page.basket.table.attribute.totalCost"/></b>
                        </td>
                        <td>
                            <b><c:out value="${total}"/></b>
                        </td>
                    </tr>

                    <c:if test="${total > 0}">
                        <tr>
                            <td colspan="5" align="right">
                                <form action="/user/basket" method="post">
                                    <input name="orderId" type="hidden" value="<c:out value="${orderId}"/>">
                                    <input name="command" type="hidden" value="user_create_order">
                                    <input type="submit" class="btn btn-primary"
                                           value="<fmt:message key="page.basket.button.confirm"/>"/>
                                </form>
                            </td>
                        </tr>
                    </c:if>

                    </tbody>
                </table>

            </div>
        </div>
    </div>
</div>

</body>
</html>