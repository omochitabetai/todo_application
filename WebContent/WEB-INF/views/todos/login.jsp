<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>ログイン画面</title>
        <link rel="stylesheet" href="<c:url value='/css/style.css' />">
    </head>
    <c:if test="${flush != null}">
            <div id="flush_message">
                <c:out value="${flush}"></c:out>
            </div>
    </c:if>
    <body>
        <div id="loginPage">
            <div id="header">
                <h1>ログイン画面</h1>
            </div>
            <div id="content">
                <form action="${pageContext.request.contextPath}/userLoginConfirm" method="post" class="login-form">
                    <label for="user_id">UserID</label>
                    <input type="text" id="user_id" name="user_id" value="${user.user_id}"><br>

                    <label for="user_password">Password</label>
                    <input type="password" id="user_password" name="user_password" value="${user.user_password}"><br>

                    <input type="hidden" name="_token" value="${_token}" />
                    <input type="submit" value="ログイン" class="login-button">
                </form>
            </div>
            <div id="footer">
                by T.Shimizu and R.Inoue
            </div>
        </div>
    </body>
</html>
