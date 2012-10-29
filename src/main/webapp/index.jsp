<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index</title>
    </head>
    <body>
        <a href="${pageContext.request.contextPath}/app/users">Users</a><br/>
        <a href="${pageContext.request.contextPath}/app/challenges">Challenges</a><br/>
        <a href="${pageContext.request.contextPath}/app/submissions">Submissions</a><br/>
        <a href="${pageContext.request.contextPath}/app/results">Results</a><br/><br/>
        <sec:authorize access="isAuthenticated()">
            <a href="${pageContext.request.contextPath}/j_spring_security_logout">Logout</a>
        </sec:authorize>
    </body>
</html>
