<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form:form commandName="teacher" action="${pageContext.request.contextPath}/teachers" method="POST">
            <form:input path="name" /><form:errors path="name" /><br/>
            <input type="submit">
        </form:form>
    </body>
</html>
