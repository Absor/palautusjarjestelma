<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Submission ${user.id}</title>
    </head>
    <body>
        <h1>Submission</h1>
        Submission id: ${submission.id}<br/>
        Submission number: ${submission.submissionNumber}<br/>
        <c:set var="challenge" value="${submission.challenge}" />
        Challenge id: ${challenge.id}<br/>
        <c:set var="user" value="${submission.user}" />
        User id: ${user.id}<br/>
        <c:if test="${!empty result}">
            <a href="${pageContext.request.contextPath}/app/results/${result.id}">Result</a><br>
        </c:if>
        <a href="${pageContext.request.contextPath}/app/submissions/${submission.id}/download">Download submission file</a><br/><br/>
        <a href="${pageContext.request.contextPath}/app/challenges/${challenge.id}/submissions">Back to submissions</a>
    </body>
</html>
