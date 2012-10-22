<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Submission list</title>
    </head>
    <body>
        <h1>Submissions</h1>
        <c:forEach var="submission" items="${submissions}">
            <a href="submissions/${submission.id}">${submission.id}</a>
            <form:form action="${pageContext.request.contextPath}/app/submissions/${submission.id}" method="DELETE">
                <input type="submit" value="Poista">
            </form:form>
        </c:forEach>
        <h1>Add submission</h1>
        <form:form commandName="submission" action="${pageContext.request.contextPath}/app/submissions" method="POST">
            <input type="submit">
        </form:form><br/>
        <a href="${pageContext.request.contextPath}">Main</a>
    </body>
</html>
