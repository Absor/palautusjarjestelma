<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Submission ${user.id}</title>
    </head>
    <body>
        <h1>Submission</h1>
        ${submission.id}<br/><br/>
        <a href="${pageContext.request.contextPath}/app/submissions">Back to submissions</a>
    </body>
</html>
