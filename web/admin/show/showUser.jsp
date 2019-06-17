<%-- 
    Document   : showUser
    Created on : 12 juin 2019, 20:03:31
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

        <title>Détail User</title>
    </head>
    <body>
        <div class="container">
            <div class="row justify-content-md-center">
                <h1>Détail de l'Utilisateur : ${traineeMail} </h1>
            </div>

            <div class="row justify-content-md-center">
                <h2>Liste des parcours effectués</h2>
            </div>
            
            <div class="row">
                <table class="table table-hover">
                    <thead class="thead-dark">
                        <tr>
                            <th>Thème</th>
                            <th>Sujet</th>
                            <th>Score</th>
                            <th>Durée</th>
                            <th>Meilleur Score</th>
                            <th>Meilleure durée</th>
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
                                <td>0</td>
                                <td>0</td>
                                <td>
                                    <form action="showResult" method="post">
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
