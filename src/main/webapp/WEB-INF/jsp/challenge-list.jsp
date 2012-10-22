<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
        <form:form commandName="challenge" action="${pageContext.request.contextPath}/app/challenges" method="POST" enctype="multipart/form-data">
            Publish date: <form:input path="publish" /><form:errors path="publish" /><br/>
            Deadline: <form:input path="deadline" /><form:errors path="deadline" /><br/>
            Maximum tries: <form:input path="maxSubmissions" /><form:errors path="maxSubmissions" /><br/>
            Template file: <form:input type="file" path="formTemplateFile" /><form:errors path="formTemplateFile" /><br/>
            MQ address: <form:input path="messageQueueAddress" /><form:errors path="messageQueueAddress" /><br/>
            MQ name: <form:input path="messageQueueName" /><form:errors path="messageQueueName" /><br/>
            <input type="submit">
        </form:form><br/>
        <a href="${pageContext.request.contextPath}">Main</a>
    </body>
</html>
