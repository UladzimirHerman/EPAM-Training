<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <c:set var="titleKey" scope="page" value="page.upload.title"/>
    <%@ include file="/WEB-INF/jsp/fragment/common/resource.jspf" %>
</head>
<body>

<%@ include file="/WEB-INF/jsp/fragment/common/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragment/common/menu.jspf" %>

<div class="container">
    <div class="row" align="center">
        <div class="col-sm-1"></div>
        <div class="col-sm-10" style="background-color:AliceBlue;">
            <form action="/admin/upload" method="post" enctype="multipart/form-data">
                <table class="table">
                    <tbody>
                    <tr>
                        <td>
                            <h4><b><fmt:message key="page.upload.table.attribute.upload"/></b></h4>
                        </td>
                        <td>
                            <input name="photo" type="file" class="form-control-file border" size="1"
                                   maxlength="100" required>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input name="command" type="hidden" value="admin_upload_photo">
                            <input type="submit" class="btn btn-primary"
                                   value="<fmt:message key="page.upload.button.upload"/>"/>
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