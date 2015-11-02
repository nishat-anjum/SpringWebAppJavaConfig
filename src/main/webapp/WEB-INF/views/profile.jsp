<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: nishat
  Date: 10/28/15
  Time: 8:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Profile</title>
    <link href="<c:url value='/resources/css/springDemoProject.css' />" rel="stylesheet">
</head>
<body>
<section class="container">
    <div class="formDiv">
        <h1><s:message code="title.login.profile"/></h1>
        <table>
            <tr>
                <th><s:message code="label.name"/>:</th>
                <td><c:out value="${user.firstName} ${user.lastName}"/></td>
            </tr>
            <tr>
                <th><s:message code="label.sex"/>:</th>
                <td>
                    <c:out value="${user.gender}"/>
                </td>
            </tr>
            <tr>
                <th><s:message code="label.email"/>:</th>
                <td><c:out value="${user.email}"/></td>
            </tr>
            <tr><td colspan="2"><a href="<c:url value='/logout'/>">logout </a></td></tr>
        </table>
    </div>
</section>

</body>
</html>
