<%-- 
    Document   : loginTrainee
    Created on : 30 mai 2019, 14:16:18
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

        <title>Stagiaire</title>
    </head>
    <body>
        <div class="container">
            <div class="row justify-content-md-center">
                <h1>Bienvenue Stagiaire : <s:property value="#session.userMail"/></h1>
            </div>

            <div class="row justify-content-md-center">
                <div class="form-group">
                    <button type="submit" class="btn btn-danger">Logout</button>
                </div>
            </div>

            <div class="row justify-content-md-center">
                <form action="listFormTrainee" method="post">
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Liste des questionnnaires</button>
                    </div>
                </form>
            </div>

            <div class="row justify-content-md-center">
                <form action="ResultTrainee" method="post">
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Voir les Résultats</button>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
