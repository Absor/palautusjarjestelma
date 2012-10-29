<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Challenge list</title>
    </head>
    <body>
        <h1>Current challenges:</h1>
        <c:forEach var="challenge" items="${currentChallenges}">
            <a href="${pageContext.request.contextPath}/app/challenges/${challenge.id}">${challenge.id}</a>
            <form:form action="${pageContext.request.contextPath}/app/challenges/${challenge.id}" method="DELETE">
                <input type="submit" value="Delete">
            </form:form>
        </c:forEach>
        <br/>
        <sec:authorize access="hasRole('teacher')">
            <h1>All challenges:</h1>
            <c:forEach var="challenge" items="${allChallenges}">
                <a href="${pageContext.request.contextPath}/app/challenges/${challenge.id}">${challenge.id}</a>
                <form:form action="${pageContext.request.contextPath}/app/challenges/${challenge.id}" method="DELETE">
                    <input type="submit" value="Delete">
                </form:form>
            </c:forEach>
            <br/>
            <h1>Add challenge:</h1>
            <form:form commandName="challenge" action="${pageContext.request.contextPath}/app/challenges" method="POST" enctype="multipart/form-data">
                Name: <form:input path="name" /><form:errors path="name" /><br/>
                Name: <form:textarea path="description" /><form:errors path="description" /><br/>
                Publish date: <form:input path="publishDate" /><form:errors path="publishDate" /><br/>
                Deadline: <form:input path="deadlineDate" /><form:errors path="deadlineDate" /><br/>
                Maximum number of submissions allowed: <form:input path="maxSubmissions" /><form:errors path="maxSubmissions" /><br/>
                Template file: <form:input type="file" path="formTemplateFile" /><form:errors path="formTemplateFile" /><br/>
                MQ address: <form:input path="messageQueueAddress" /><form:errors path="messageQueueAddress" /><br/>
                MQ name: <form:input path="messageQueueName" /><form:errors path="messageQueueName" /><br/>
                <input type="submit">
            </form:form><br/>
        </sec:authorize>
        <a href="${pageContext.request.contextPath}">Main</a>
    </body>
</html>
