<%-- 
    Document   : modifyView
    Created on : 20 mai 2019, 18:42:00
    Author     : Junior
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "s" uri = "/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

        <title>Modification d'un user </title>
    </head>
    <body>
        <div class="container">
            <div class="row justify-content-md-center">
                <h1>Modification de ${user.firstname} ${user.lastname}</h1>
            </div>

            <div class="row">
                <form action="usersManagement" method="post">
                    <div class="form-group">
                        <button type="submit" class="btn btn-light">Back</button>
                    </div>
                </form>
            </div>

            <div class="row justify-content-md-center">
                <form action="modifyUserSubmit" method="post">
                    <div class="form-group">
                        <label for="user_email">Email</label>
                        <input type="text" class="form-control" id="user_email" name="User.email" value="${user.email}" readonly/>
                    </div>
                    <div class="form-group">
                        <label for="user_status">Statut</label>
                        <input type="text" class="form-control" id="user_status" name="User.status" value="${user.status}" readonly/>
                    </div>
                    <div class="form-group">
                        <label for="user_firstname">Prénom</label>
                        <input type="text" class="form-control" id="user_firstname" name="User.firstname" value="${user.firstname}"/>
                    </div>
                    <div class="form-group">
                        <label for="user_lastname">Nom</label>
                        <input type="text" class="form-control" id="user_lastname" name="User.lastname" value="${user.lastname}"/>
                    </div>
                    <div class="form-group">
                        <label for="user_pwd">Mot de Passe</label>
                        <input type="text" class="form-control" id="user_pwd" name="User.password" value="${user.password}"/>
                    </div>
                    <div class="form-group">
                        <label for="user_company">Société</label>
                        <input type="text" class="form-control" id="user_company" name="User.company" value="${user.company}"/>
                    </div>
                    <div class="form-group">
                        <label for="user_tel">Téléphone</label>
                        <input type="text" class="form-control" id="user_tel" name="User.tel" value="${user.tel}"/>
                    </div>
                    <div class="form-group">
                        Date création : ${user.date_creat}
                    </div>
                    <button type="submit" class="btn btn-primary">Modifier</button>
                </form>
            </div>
        </div>
    </body>
</html>
