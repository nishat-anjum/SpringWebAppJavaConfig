<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: nishat
  Date: 10/28/15
  Time: 7:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Spring Demo Project</title>
    <script src="<c:url value='/resources/js/jquery-1.11.3.js' />"></script>
    <script src="<c:url value='/resources/js/jquery-ui.min.js'/>"></script>
    <script src="<c:url value='/resources/js/springDemoProject.js' />"></script>
    <link href="<c:url value='/resources/css/springDemoProject.css' />" rel="stylesheet">
    <link href="<c:url value='/resources/css/jquery-ui.min.css' />" rel="stylesheet">
</head>
<body>

<section class="container">
    <p id="successMessage"></p>
    <p id="logInError" class="alert"></p>

    <c:if test="${errorKey != null}">
        <div>
            <p class="alert"><s:message code="${errorKey}"/>:</p>
        </div>
    </c:if>

    <div class="formDiv">
        <h1><s:message code="title.login.form"/></h1>

        <form method="post" action="<c:url value='/login'/>" id="logInForm">
            <p><input type="email" name="userEmail" placeholder="Email"></p>

            <p><input type="password" name="userPassword" placeholder="Password"></p>

            <p class="remember_me">

            <p class="submit"><input type="button" value="Login" id="logIn"></p>
        </form>

        <span id="newUser"> New User?? Register Now =></span>
        <input type="button" id="signUp" value="signUp">
    </div>
</section>

<section class="container">

</section>


<div id="signUpForm">
    <form>
        <table align="center">
            <tr>
                <th><s:message code="label.user.first.name"/>:<span class="alert">*</span></th>
                <td><input type="text" name="firstName"/><span id="firstName" class="alert"></span></td>
            </tr>
            <tr>
                <th><s:message code="label.user.last.name"/>:<span class="alert">*</span></th>
                <td><input type="text" name="lastName"/><span id="lastName" class="alert"></span></td>
            </tr>
            <tr>
                <th><s:message code="label.sex"/>:</th>
                <td>
                    <input class="sex" type="radio" value="Female" name="gender">Female
                    <input class="sex" type="radio" value="Male" name="gender">Male
                </td>
            </tr>
            <tr>
                <th><s:message code="label.email"/>:<span class="alert">*</span></th>
                <td><input type="email" name="email"/><span id="email" class="alert"></span></td>
            </tr>
            <tr>
                <th><s:message code="label.password"/>:<span class="alert">*</span></th>
                <td><input type="password" name="pass"/><span id="password" class="alert"></span></td>
            </tr>
            <tr>
                <th><s:message code="label.confirm.password"/>:<span class="alert">*</span></th>
                <td><input type="password" name="confPass"></td>
            </tr>
        </table>
    </form>
    <div id="signUpError"></div>
</div>
</body>
</html>
</html>
