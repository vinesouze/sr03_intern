<%-- 
    Document   : addAnswer
    Created on : 30 mai 2019, 12:58:14
    Author     : Junior
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <link type="text/css" rel="stylesheet" href="add.css" />
        <title>Nouvelle Réponse</title>
    </head>
    <body>
        <h1>Nouvelle Réponse</h1>
        <div class="form-style-6">
            <form action="addAnswerSubmit">
                <input type="text" name="Answer.statement" placeholder="Réponse">
                <p>
                    <input type="radio" id="answer.active" name="Answer.status" value="actif">
                    <label for="answer.active">Active</label>
                    <input type="radio" id="answer.inactive" name="Answer.status" value="inactif">
                    <label for="answer.inactive">Inactive</label>
                </p>
                <input type="hidden" value="Answer.is_right" value="false">
                <input type="hidden" value="${modifyIdQuestion}" name="modifyIdQuestion"/>
                <input type="hidden" name="Answer.question_id" value="${modifyIdQuestion}"/>
                <input type="hidden" value=${modifyIdForm}  name="modifyIdForm" />
                <input type="submit" value="Créer Réponse"/>
            </form>
        </div>
    </body>
</html>
