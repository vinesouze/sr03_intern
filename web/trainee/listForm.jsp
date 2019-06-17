<%-- 
    Document   : listForm
    Created on : 1 juin 2019, 17:14:22
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

        <title>Liste Questionnaires</title>
    </head>
    <body>
        <div class="container">
            <div class="row justify-content-md-center">
                <h1>Liste des Sujets </h1>
            </div>

            <div class="row">
                <form action="LoginTrainee" method="post">
                    <div class="form-group">
                        <button type="submit" class="btn btn-light">Back</button>
                    </div>
                </form>
                <div class="form-group">
                    <button type="submit" class="btn btn-danger">Logout</button>
                </div>
            </div>

            <div class="row">
                <table class="table table-hover">
                    <thead class="thead-dark">
                        <tr>
                            <th>Sujet</th>
                            <th>Start</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="themes">
                            <tr>
                                <td><s:property value = "subject"/></td>
                                <td>
                                    <form action="FormTrainee" method="post">
                                        <div class="form-group">
                                            <input type="hidden" name="theme" value="<s:property value = "subject"/>" />
                                            <input type="hidden" name="Test.user_id" value="<s:property value="#session.userMail"/>"/>
                                            <button type="submit" class="btn btn-success">Lancer</button>
                                        </div>
                                    </form>
                                </td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
