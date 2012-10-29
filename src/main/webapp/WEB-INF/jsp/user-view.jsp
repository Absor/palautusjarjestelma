<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User ${user.id}</title>
    </head>
    <body>
        <h1>User</h1>
        Username: ${user.username}<br/>
        Enabled: ${user.enabled}<br/>
        Role: ${user.role}<br/>
        Student number: ${user.studentNumber}<br/><br/>
        <a href="${pageContext.request.contextPath}/app/users">Back to users</a>
    </body>
</html>
