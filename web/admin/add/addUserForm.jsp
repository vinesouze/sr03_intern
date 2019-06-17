<%-- 
    Document   : index.jsp
    Created on : 13 mai 2019, 17:05:56
    Author     : Junior
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
       <link type="text/css" rel="stylesheet" href="add.css" />
       <title>Users</title>
    </head>
    <body>
    <div class="form-style-6">
        <form action="addUser">
            <input type="text" name="user.lastname" placeholder="Nom">
            <input type="text" name="user.firstname" placeholder="Prénom">
            <input type="email" name="user.email" placeholder="Mail">
            <input type="text" name="user.company" placeholder="Société">
            <input type="text" name="user.tel" placeholder="Téléphone">
            <p>
            <input type="radio" id="user.male" name="user.gender" value="male">
            <label for="user.male">Homme</label>
            <input type="radio" id="user.female" name="user.gender" value="female">
            <label for="user.female">Femme</label>
            </p>
            <p>
            
            <input type="radio" id="user.admin" name="user.is_admin" value="true">
            <label for="user.admin">Admin</label>
            
            <input type="radio" id="user.stagiaire" name="user.is_admin" value="false">
            <label for="user.stagiaire">Stagiaire</label>
            </p>
            <input type="submit" value="Ajouter"/>
        </form>
    </div>
    </body>
</html>
