<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="content"/>
    <title><fmt:message key="page.productView.title"/></title>
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

                <div class="col-sm-3 py-2">
                    <div class="card h-100" align="center">
                        <img class="card-img-top"
                             src="${pageContext.request.contextPath}/resources/images/<fmt:message key="page.productView.example.photo"/>"
                             alt="<fmt:message key="page.productView.example.photo"/>">
                        <div class="card-body">
                            <h4 class="card-title"><fmt:message key="page.productView.example.name"/></h4>
                            <p class="card-text"><fmt:message key="page.productView.example.description"/></p>
                            <p class="card-text"><fmt:message key="page.productView.example.price"/></p>
                            <p class="card-text"><fmt:message key="page.productView.example.sale"/></p>
                            <form action="/admin/product/create" method="post">
                                <input type="submit" class="btn btn-primary"
                                       value="<fmt:message key="page.productView.button.create"/>"/>
                            </form>
                        </div>
                    </div>
                </div>

                <c:forEach var="productList" items="${productList}">
                    <div class="col-sm-3 py-2">
                        <div class="card h-100" align="center">
                            <img class="card-img-top"
                                 src="${pageContext.request.contextPath}/resources/images/<c:out value="${productList.photo}"/>"
                                 alt="<c:out value="${productList.photo}"/>.jpg" style="width:100%">
                            <div class="card-body" align="center">
                                <h4 class="card-title"><c:out value="${productList.name}"/></h4>
                                <p class="card-text"><c:out value="${productList.description}"/></p>
                                <p class="card-text"><fmt:message key="page.productView.example.price"/><c:out
                                        value="${productList.price}"/></p>
                                <p class="card-text"><fmt:message key="page.productView.example.sale"/><c:out
                                        value="${productList.sale}"/></p>
                                <form action="/admin/product/edit" method="get">
                                    <input name="productId" type="hidden" value="<c:out value="${productList.id}"/>"/>
                                    <input type="submit" class="btn btn-primary"
                                           value="<fmt:message key="page.productView.button.edit"/>"/>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:forEach>

            </div>
        </div>
    </div>
</div>

</body>
</html>