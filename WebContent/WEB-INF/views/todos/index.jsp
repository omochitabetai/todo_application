<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>メイン画面</title>
        <link rel="stylesheet" href="<c:url value='/css/style.css' />">
    </head>
    <c:if test="${flush != null}">
            <div id="flush_message">
                <c:out value="${flush}"></c:out>
            </div>
    </c:if>
    <body>
        <div id="wrapper">
            <div id="header">
                <h1>TODO List 共有アプリケーション</h1>
            </div>
            <div id="content">
                <ol>
                    <c:forEach var="todo" items="${todos}">
                        <li>
                            内容：<c:out value="${todo.content}"></c:out>
                            期限：<c:out value="${todo.deadline_at}"></c:out>
                            作成者：<c:out value="${todo.creator}"></c:out>
                        </li>
                    </c:forEach>
               </ol>
                <button onclick="location.href='${pageContext.request.contextPath}/createTodoForm'">Todo作成</button>
            </div>
            <div id="footer">
                by T.Shimizu and R.Inoue

            </div>
        </div>
    </body>
</html>
