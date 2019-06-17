<%-- 
    Document   : addForm
    Created on : 24 mai 2019, 16:21:08
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

        <title>Nouveau Formulaire</title>
    </head>
    <body>
        <div class="container">
            <div class="row justify-content-md-center">
                <h1>Nouveau Formulaire</h1>
            </div>

            <div class="row">
                <div class="form-group">
                    <form action="addTheme" method="post">
                        <div class="form-group">
                            <div class="input-group">
                                <input type="text" placeholder="Nouveau Thème" class="form-control" name="Theme.subject" value="${theme.subject}"/>
                                <div class="input-group-append">
                                    <input type="submit" class="btn btn-primary" value="+"/>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <div class="row">
                <form action="addFormSubmit" method="action">
                    <div class="form-group">
                        <label for="form_theme">Thème</label>
                        <select class="custom-select" name="form.theme" value="${form.theme}">
                            <c:forEach items="${themes}" var="Theme">
                                <option><c:out value="${Theme.subject}"/></option> 
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="form.subject" placeholder="Sujet"/>
                    </div>

                    <h4> Question <h4>
                    <div class="form-group">
                        <input type="text" class="form-control" name="Question.statement" placeholder="Intitulé de la Question">
                    </div>
                    <input type="hidden" name="Question.status" value="actif"/>
                    <input type="hidden" name="Question.rank" value="1"/>

                    <h4> Réponse bonne <h4>
                    <div class="form-group">
                        <input type="text" class="form-control" name="Answer.statement" placeholder="Intitulé de la Réponse">
                    </div>
                    <input type="hidden" name="Answer.status" value="actif"/>
                    <input type="hidden" name="Answer.rank" value="1"/>
                    <input type="hidden" name="Answer.is_right" value="true"/>
                    <input type="submit" class="btn btn-primary" value="Créer Formulaire"/>
                </form>
            </div>
        </div>
    </body>
</html>
