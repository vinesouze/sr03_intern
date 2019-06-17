<%-- 
    Document   : testTraineeResult
    Created on : 1 juin 2019, 23:59:59
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
                <h2>Voici votre score pour ce Test : ${score}</h2>
            </div>

            <div class="row justify-content-md-center">
                <form action="LoginTrainee" method="post">
                    <button type="submit" class="btn btn-light">Back</button>
                </form>
            </div>
        </div>
    </body>
</html>