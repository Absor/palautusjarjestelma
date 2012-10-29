<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users</title>
    </head>
    <body>
        <sec:authorize access="hasRole('teacher')">
            <h1>Users</h1>
            <c:forEach var="user" items="${users}">
                <a href="${pageContext.request.contextPath}/app/users/${user.id}">${user.username}</a>
                <form:form action="users/${user.id}" method="DELETE">
                    <input type="submit" value="Delete">
                </form:form><br/>
            </c:forEach>
        </sec:authorize>
        <h1>Register new user:</h1>
        <form:form commandName="user" action="${pageContext.request.contextPath}/app/users" method="POST">
            Username: <form:input path="username" /><form:errors path="username" /><br/>
            Password: <form:password path="password" /><form:errors path="password" /><br/>
            Enabled: <form:checkbox path="enabled" /><form:errors path="enabled" /><br/>
            Role: <form:input path="role" /><form:errors path="role" /><br/>
            Student number: <form:input path="studentNumber" /><form:errors path="studentNumber" /><br/>
            <input type="submit">
        </form:form><br/>
        <a href="${pageContext.request.contextPath}">Main</a>
    </body>
</html>
