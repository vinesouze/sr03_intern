<%-- 
    Document   : modifyAnswer
    Created on : 14 juin 2019, 13:00:53
    Author     : Junior
--%>

<%-- 
    Document   : modifyForm
    Created on : 27 mai 2019, 13:19:35
    Author     : Junior
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "s" uri = "/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

        <title>Modification d'une Réponse</title>
    </head>
    <body>
        <div class="container">
            <div class="row justify-content-md-center">
                <h1>Modification d'une Réponse</h1>
            </div>

            <div class="row justify-content-md-center">
                <form action="modifyAnswerSubmit" method="post">
                    <div class="form-group">
                        <label for="answer_statement">Intitulé</label>
                        <input type="text" class="form-control" id="answer_statement" name="Answer.statement" value="${answer.statement}"/>
                    </div>
                    <input type="hidden" name="Answer.id" value="${answer.id}" />
                    <input type="hidden" name="Answer.status" value="${answer.status}" />
                    <input type="hidden" name="Answer.is_right" value="${answer.is_right}"/>
                    <input type="hidden" name="Answer.rank" value="${answer.rank}" />
                    <input type="hidden" name="Answer.question_id" value="${answer.question_id}" />
                    <input type="hidden" value=${modifyIdForm}  name="modifyIdForm" />
                    <input type="submit" class="btn btn-success" value="Modifier"/>
                </form>
            </div>
        </div>
    </body>
</html>

