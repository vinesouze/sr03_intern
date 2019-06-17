<%-- 
    Document   : modifyForm
    Created on : 27 mai 2019, 13:19:35
    Author     : Junior
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "s" uri = "/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

        <title>Modification d'un formulaire</title>
    </head>
    <body>
        <div class="container">
            <div class="row justify-content-md-center">
                <h1>Modification d'un formulaire</h1>
            </div>

            <div class="row justify-content-md-center">
                <form action="modifyFormSubmit" method="post">
                    <div class="form-group">
                        <label for="static_form_theme">Ancien Thème :</label>
                        <input type="text" class="form-control-plaintext" id="static_form_theme" value="${form.theme}" disabled="true">
                    </div>
                    <div class="form-group">
                        <label for="form_theme">Nouveau Thème</label>
                        <select class="custom-select" name="form.theme" value="${form.theme}">
                            <c:forEach items="${themes}" var="Theme">
                                <option><c:out value="${Theme.subject}"/></option> 
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="form_subject">Sujet</label>
                        <input type="text" class="form-control" id="form_subject" name="Form.subject" value="${form.subject}"/>
                    </div>
                    <input type="hidden" name="Form.id" value="${form.id}" />
                    <input type="hidden" name="Form.status" value="${form.status}" />
                    <input type="submit" class="btn btn-success" value="Modifier"/>
                </form>
            </div>
        </div>
    </body>
</html>
