<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <c:set var="titleKey" scope="page" value="page.productView.title"/>
    <%@ include file="/WEB-INF/jsp/fragment/common/resource.jspf" %>
</head>
<body>

<%@ include file="/WEB-INF/jsp/fragment/common/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragment/common/menu.jspf" %>

<div class="container">
    <div class="row" align="center">
        <div class="col-sm-1"></div>
        <div class="col-sm-10" style="background-color:AliceBlue;"><br>
            <div class="row" align="center">

                <c:forEach var="productList" items="${productList}">
                    <div class="col-sm-3 py-2">
                        <div class="card h-100" align="center">
                            <img class="card-img-top"
                                 src="${pageContext.request.contextPath}/resources/images/<c:out value="${productList.photo}"/>"
                                 alt="<c:out value="${productList.photo}"/>.jpg" style="width:100%">
                            <div class="card-body" align="center">
                                <h4 class="card-title"><c:out value="${productList.name}"/></h4>
                                <p class="card-text"><c:out value="${productList.description}"/></p>
                                <p class="card-text"><fmt:message key="page.productView.card.price"/><c:out
                                        value="${productList.price}"/></p>
                                <form action="/user/product" method="post">
                                    <input name="quantity" type="number" class="form-control" value="1"
                                           min="1" max="5" step="1" required>
                                    <input name="productId" type="hidden" value="<c:out value="${productList.id}"/>"/>
                                    <input name="command" type="hidden" value="user_add_to_basket">
                                    <input type="submit" class="btn btn-primary"
                                           value="<fmt:message key="page.productView.button.ToBasket"/>"/>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:forEach>

                <c:set var="action" scope="page" value="/user/product"/>
                <%@ include file="/WEB-INF/jsp/fragment/common/paginaton.jspf" %>

            </div>
        </div>
    </div>
</div>

</body>
</html>