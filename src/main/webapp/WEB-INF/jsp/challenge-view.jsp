<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Challenge ${user.id}</title>
    </head>
    <body>
        <h1>Challenge</h1>
        Name: ${challenge.name}<br/>
        Description: ${challenge.description}<br/>
        Publish date: ${challenge.publishDate}<br/>
        Deadline date: ${challenge.deadlineDate}<br/>
        Maximum number of submissions allowed: ${challenge.maxSubmissions}<br/>
        <sec:authorize access="hasRole('teacher')">
        Message queue address: ${challenge.messageQueueAddress}<br/>
        Message queue name: ${challenge.messageQueueName}<br/>
        </sec:authorize>
        <c:if test="${!empty challenge.templateFile}">
            <a href="${pageContext.request.contextPath}/app/challenges/${challenge.id}/downloadTemplate">Download template</a><br/>
        </c:if>
        <br/><br/>
        <a href="${pageContext.request.contextPath}/app/challenges/${challenge.id}/submissions">Submissions</a>
        <br/><br/>
        <a href="${pageContext.request.contextPath}/app/challenges">Back to challenges</a>
    </body>
</html>
