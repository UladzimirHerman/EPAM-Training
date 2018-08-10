<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="content"/>
    <title><fmt:message key="page.ordersOpen.title"/></title>
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

            <c:forEach var="orders" items="${orders}">
                <div id="accordion<c:out value="${orders.id}"/>">
                    <div class="card">
                        <div class="card-header">
                            <div class="d-flex">
                                <div class="p-2 mr-auto" align="left">
                                    <a class="card-link" data-toggle="collapse"
                                       href="#<c:out value="${orders.id}"/>">
                                        <fmt:message key="page.ordersOpen.table.attribute.id"/>
                                        <c:out value="${orders.id}"/></a>
                                    <c:out value="${orders.date}"/>
                                    <fmt:message key="page.ordersOpen.table.attribute.customer"/>
                                    <c:out value="${orders.user.userInfo.name}"/>
                                    <c:out value="${orders.user.userInfo.surname}"/>
                                    (<c:out value="${orders.user.login}"/>)
                                </div>
                                <div class="p-2">
                                    <fmt:message key="page.ordersOpen.table.attribute.status"/>
                                    <c:out value="${orders.orderStatus}"/>
                                </div>
                            </div>
                        </div>
                        <div id="<c:out value="${orders.id}"/>" class="collapse"
                             data-parent="#accordion<c:out value="${orders.id}"/>">
                            <div class="card-body" style="background-color:AliceBlue;">
                                <table class="table text-center">
                                    <thead>
                                    <tr style="background-color:PowderBlue;">
                                        <th><fmt:message key="page.ordersOpen.table.attribute.product"/></th>
                                        <th><fmt:message key="page.ordersOpen.table.attribute.price"/></th>
                                        <th><fmt:message key="page.ordersOpen.table.attribute.quantity"/></th>
                                        <th><fmt:message key="page.ordersOpen.table.attribute.cost"/></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:set var="total" scope="page" value="${0}"/>
                                    <c:forEach var="orderInfo" items="${orders.orderInfoList}">
                                        <tr>
                                            <td><c:out value="${orderInfo.product.name}"/></td>
                                            <td><c:out value="${orderInfo.product.price}"/></td>
                                            <td><c:out value="${orderInfo.quantity}"/></td>
                                            <td><ctg:cost-definition price="${orderInfo.product.price}"
                                                                     quantity="${orderInfo.quantity}"/></td>
                                            <c:set var="total" scope="page"
                                                   value="${total + orderInfo.product.price*orderInfo.quantity}"/>
                                        </tr>
                                    </c:forEach>
                                    <tr>
                                        <td colspan="3" align="right">
                                            <b><fmt:message key="page.ordersOpen.table.attribute.totalCost"/></b>
                                        </td>
                                        <td>
                                            <b><c:out value="${total}"/></b>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>

            <c:set var="pageCount" scope="page" value="${1}"/><p>
            <form action="/user/orders/open" method="get" class="form-inline">
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
            <p>

        </div>
    </div>
</div>

</body>
</html>