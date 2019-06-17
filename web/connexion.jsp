<%-- 
    Document   : connexion
    Created on : 19 mai 2019, 15:27:30
    Author     : Junior
--%>

<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Connexion</title>
        <link type="text/css" rel="stylesheet" href="form.css" />
    </head>
    <body>
        <s:form method="post" action="connect">
                <p>Vous pouvez vous connecter via ce formulaire.</p>
                <s:textfield label="email" name="user.email"></s:textfield>
                <s:textfield label="password" name="user.password"></s:textfield>
                <s:submit value="Connexion"></s:submit>
        </s:form>
    </body>s
</html>
