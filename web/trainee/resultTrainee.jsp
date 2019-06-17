<%-- 
    Document   : resultTrainee
    Created on : 2 juin 2019, 15:31:18
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

        <title>Résultats Stagiaire</title>
    </head>
    <body>
        <div class="container">
            <div class="row justify-content-md-center">
                <h1>Voici vos Résultats <s:property value="#session.userMail"/></h1>
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
                            <th>Thème</th>
                            <th>Sujet</th>
                            <th>Score</th>
                            <th>Durée</th>
                            <th>Détail</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="results">
                            <tr>
                                <td><s:property value="theme"/></td>
                                <td><s:property value="subject"/></td>
                                <td><s:property value="score"/></td>
                                <td><s:property value="hours()"/>:<s:property value="minutes()"/>:<s:property value="seconds()"/></td>
                                <td>
                                    <form action="DetailResult" method="post">
                                        <input type="hidden" value=<s:property value="#session.userMail"/> name="traineeMail">
                                        <input type="hidden" value=<s:property value="test_id"/> name="testId"/>
                                        <input type="submit" class="btn btn-outline-primary" value="0"/> 
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
