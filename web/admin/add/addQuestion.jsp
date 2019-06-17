<%-- 
    Document   : addQuestion
    Created on : 27 mai 2019, 18:28:52
    Author     : Junior
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

        <title>Nouvelle Question</title>
    </head>
    <body>
        <div class="container">
             <div class="row justify-content-md-center">
                <h1>Nouvelle Question</h1>
            </div>

            <div class="row">
                <form action="addQuestionSubmit" method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" name="Question.statement" placeholder="Sujet">
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="Question.status" id="question_active" value="actif">
                        <label class="form-check-label" for="user_actif">Actif</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="Question.status" id="question.inactive" value="inactif">
                        <label class="form-check-label" for="user_trainee">Inactif</label>
                    </div>
                    <input type="hidden" value="${modifyIdForm}" name="modifyIdForm"/>
                    <input type="hidden" name="Question.id_form" value="${modifyIdForm}"/>

                    <h4> Réponse bonne <h4>
                    <div class="form-group">
                        <input type="text" class="form-control" name="Answer.statement" placeholder="Sujet">
                    </div>
                    <input type="hidden" name="Answer.status" value="actif"/>
                    <input type="hidden" name="Answer.rank" value="1"/>
                    <input type="hidden" name="Answer.is_right" value="true"/>
                    
                    <input type="submit" class="btn btn-primary" value="Créer Question"/>
                </form>
            </div>
        </div>
    </body>
</html>