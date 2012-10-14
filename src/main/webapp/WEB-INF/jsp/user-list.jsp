<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User list</title>
    </head>
    <body>
        <h1>Users</h1>
        <c:forEach var="user" items="${users}">
            <a href="users/${user.id}">${user.username}</a>
            <form:form action="${pageContext.request.contextPath}/app/users/${user.id}" method="DELETE">
                <input type="submit" value="Poista">
            </form:form>
        </c:forEach>
        <h1>Add user</h1>
        <form:form commandName="user" action="${pageContext.request.contextPath}/app/users" method="POST">
            <form:input path="username" /><form:errors path="username" /><br/>
            <input type="submit">
        </form:form>
    </body>
</html>
