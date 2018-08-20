<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <c:set var="titleKey" scope="page" value="page.feedbackEdit.title"/>
    <%@ include file="/WEB-INF/jsp/fragment/common/resource.jspf" %>
</head>
<body>

<%@ include file="/WEB-INF/jsp/fragment/common/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragment/common/menu.jspf" %>

<div class="container">
    <div class="row" align="center">
        <div class="col-sm-1"></div>
        <div class="col-sm-10" style="background-color:AliceBlue;">
            <form action="/user/feedback/edit" method="post">

                <fmt:message key="page.feedbackEdit.message.error" var="messageText"/>
                <%@ include file="/WEB-INF/jsp/fragment/common/error_message.jspf" %>

                <table class="table">
                    <tbody>
                    <tr>
                        <td>
                            <h4><b><fmt:message key="page.feedbackEdit.table.attribute.rating"/></b></h4>
                        </td>
                        <td>
                            <input name="rating" type="number" class="form-control"
                                   value="<c:out value="${feedback.rating}"/>"
                                   min="1" max="5" step="1" required>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h4><b><fmt:message key="page.feedbackEdit.table.attribute.comment"/></b></h4>
                        </td>
                        <td>
                            <textarea name="comment" class="form-control" rows="3" minlength="10" maxlength="255"
                                      required><c:out value="${feedback.comment}"/></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input name="feedbackId" type="hidden" value="${feedback.id}">
                            <input name="command" type="hidden" value="user_update_feedback">
                            <input type="submit" class="btn btn-primary"
                                   value="<fmt:message key="page.feedbackEdit.button.edit"/>"/>
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