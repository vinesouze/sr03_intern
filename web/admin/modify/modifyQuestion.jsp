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

        <title>Modification d'une Question</title>
    </head>
    <body>
        <div class="container">
            <div class="row justify-content-md-center">
                <h1>Modification d'une Question</h1>
            </div>

            <div class="row justify-content-md-center">
                <form action="modifyQuestionSubmit" method="post">
                    <div class="form-group">
                        <label for="question_statement">Intitulé</label>
                        <input type="text" class="form-control" id="question_statement" name="Question.statement" value="${question.statement}"/>
                    </div>
                    <input type="hidden" name="Question.id" value="${question.id}" />
                    <input type="hidden" name="Question.status" value="${question.status}" />
                    <input type="hidden" name="Question.rank" value="${question.rank}" />
                    <input type="hidden" name="Question.id_form" value="${question.id_form}" />
                    <input type="submit" class="btn btn-success" value="Modifier"/>
                </form>
            </div>
        </div>
    </body>
</html>
