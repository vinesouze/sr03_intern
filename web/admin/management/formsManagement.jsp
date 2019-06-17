<%-- 
    Document   : formManagement
    Created on : 24 mai 2019, 15:39:51
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

        <title>Gestion des Formulaires</title>
    </head>
    <body>
        <div class="container">
            <div class="row justify-content-md-center">
                <h1>Gestion des formulaires</h1>
            </div>

            <div class="row">
                <form action="LoginAdmin" method="post">
                    <div class="form-group">
                        <input type="hidden" name="userEmail" value=<s:property value="#session.userMail"/> />
                        <input type="submit" class="btn btn-light" value="Back"/>
                    </div>
                </form>
            </div>

            <div class="row">
                <form action="addForm" method="post">
                    <input type="submit" class="btn btn-primary" value="Nouveau Formulaire"/>
                </form>        
            </div>

            <div class="row justify-content-md-center"">
                <h2>Liste des formulaires</h2>
            </div>
            <div class="row">
                <table class="table table-hover">
                    <thead class="thead-dark">
                        <tr>
                            <th>Thème</th>
                            <th>Sujet</th>
                            <th>Statut</th>
                            <th>Questions</th>
                            <th>Activer/Désactiver</th>
                            <th>Modifier</th>
                        </tr> 
                    </thead>
                    <tbody>
                        <c:forEach items = "${forms}" var="form">
                            <tr>
                                <td>${form.theme}</td>
                                <td>${form.subject}</td>
                                <td>${form.status}</td>
                                <td>
                                    <form action="questionManagement" method="post">
                                        <input type="hidden" value=${form.id}  name="modifyIdForm" />
                                        <input type="submit" class="btn btn-outline-primary" value="Questions"/>
                                    </form>
                                </td>
                                <td>
                                    <form action="activateForm" method="post">
                                        <input type="hidden" value=${form.id}  name="modifyId" />
                                        <c:choose>
                                            <c:when test="${form.status.equals('actif')}" >
                                               <input type="submit" class="btn btn-danger" value="X"/>
                                            </c:when>
                                            <c:otherwise>
                                                <input type="submit" class="btn btn-success" value="A"/>
                                            </c:otherwise>
                                        </c:choose>
                                    </form>
                                </td>
                                <td>
                                    <form action="modifyForm" method="post">
                                        <input type="hidden" value=${form.id}  name="modifyId" />
                                        <input type="submit" class="btn btn-outline-primary" value="Modifier"/>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>    
            </div>
        </div>
    </body>
</html>
