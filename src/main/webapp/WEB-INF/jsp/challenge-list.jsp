<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Challenge list</title>
    </head>
    <body>
        <h1>Challenges</h1>
        <c:forEach var="challenge" items="${challenges}">
            <a href="challenges/${challenge.id}">${challenge.id}</a>
            <form:form action="${pageContext.request.contextPath}/app/challenges/${challenge.id}" method="DELETE">
                <input type="submit" value="Poista">
            </form:form>
        </c:forEach>
        <h1>Add challenge</h1>
        <form:form commandName="challenge" action="${pageContext.request.contextPath}/app/challenges" method="POST">
            <input type="submit">
        </form:form>
    </body>
</html>
