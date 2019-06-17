<%-- 
    Document   : showResult
    Created on : 17 juin 2019, 13:13:37
    Author     : Junior
--%>

<%@ page contentType = "text/html; charset=UTF-8" %>
<%@ taglib prefix = "s" uri = "/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

        <title>Questionnaire Résultat</title>
    </head>
    <body>
        <div class="container">
            <div class="row justify-content-md-center">
                <h1>Voici ses réponses à ce questionnaire</h1>
            </div>
            
            <div class="row">
                <table class="table">
                    <thead>
                        <tr>
                            <td>Intitulé</td>
                            <td>Réponse donné</td>
                            <td>Réponse bonne</td>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="questions_trainee">
                            <s:if test="answer.is_right()" >
                                <tr class="text-success">
                            </s:if>
                            <s:else>
                                <tr class="text-danger">
                            </s:else>
                                <td><s:property value="question.statement"/></td>
                                <td><s:property value="answer.statement"/></td>
                                <td><s:property value="answer_good.statement"/></td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
            </div>

            <div class="row justify-content-md-center">
                <form action="LoginAdmin" method="post">
                    <button type="submit" class="btn btn-light">Back</button>
                </form>
            </div>
        </div>
    </body>
</html>


