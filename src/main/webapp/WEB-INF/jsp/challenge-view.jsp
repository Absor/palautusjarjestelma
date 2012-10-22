<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Challenge ${user.id}</title>
    </head>
    <body>
        <h1>Challenge</h1>
        ${challenge.id}<br/>
        <a href="${challenge.id}/downloadTemplate">Download template</a><br/><br/>
        <a href="${pageContext.request.contextPath}/app/challenges">Back to challenges</a>
    </body>
</html>
