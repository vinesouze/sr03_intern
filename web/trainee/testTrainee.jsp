<%-- 
    Document   : testTrainee
    Created on : 1 juin 2019, 21:02:00
    Author     : Junior
--%>

<%@ page contentType = "text/html; charset=UTF-8" %>
<%@ taglib prefix = "s" uri = "/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

        <title>Parcours Stagiaire</title>
    </head>
    <body>
        <div class="container">
            <div class="row justify-content-md-center">
                <h1>Questionnaire</h1>
            </div>

            <div class="row justify-content-md-center">
                <form action="FormTraineeNext" method="post">
                    <h4>Question n°${questionRank} : ${Question.statement}</h4>
                    <input type="hidden" name="test_id" value="${test_id}">
                    <input type="hidden" name="questionRank" value="${questionRank}"/>
                    <div class="form-group">
                        <c:forEach items="${answers}" var="answer">
                            <div class="form-check">
                                <input type="radio" class="form-check-input" value=${answer.id} name="answer_id"/>
                                <label class="form-check-label">${answer.statement}</label>
                            </div>
                        </c:forEach>
                    </div>
                    <input type="submit" class="btn btn-primary" value="Suivant">
                <form>
            </div>
        </div>
    </body>
</html>
