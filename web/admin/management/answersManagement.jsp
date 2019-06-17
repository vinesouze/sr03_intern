<%-- 
    Document   : answersManagement
    Created on : 29 mai 2019, 18:15:34
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

        <title>Gestion des Réponses</title>
    </head>
    <body>
        <div class="container">
            <div class="row justify-content-md-center">
                <h1>Gestion des Réponses</h1>
            </div>

            <div class="row">
                <s:form action="questionManagement" method="post">
                    <div class="form-group">
                        <input type="hidden" name="modifyIdQuestion" value="${modifyIdQuestion}"/>
                        <input type="hidden" value=${modifyIdForm}  name="modifyIdForm" />
                        <s:submit cssClass="btn btn-light" value="Back"></s:submit>
                    </div>
                </s:form>
            </div>

            <div class="row">
                <form action="addAnswer" method="post">
                    <div class="form-group">
                        <input type="hidden" name="modifyIdQuestion" value="${modifyIdQuestion}"/>
                        <input type="hidden" value="${modifyIdForm}"  name="modifyIdForm" />
                        <input type="submit" class="btn btn-primary" value="Ajouter une Réponse"/>
                    </div>
                </form>
            </div>

            <div class="row justify-content-md-center"">
                <h2>Liste des Réponses</h2>
            </div>

            <div class="row">
                <table class="table table-hover">
                    <thead class="thead-dark">
                        <tr>
                            <th>Contenu</th>
                            <th>Statut</th>
                            <th>Rang</th>
                            <th>Juste</th>
                            <th>Modification</th>
                            <th>Activer/Désactiver</th>
                            <th>Up</th>
                            <th>Down</th>
                        </tr> 
                    </thead>
                    <tbody>
                        <s:iterator value = "answers">
                            <s:if test="is_right()" >
                                <tr class="text-success">
                            </s:if>
                            <s:else>
                                <tr class="text-danger">
                            </s:else>
                                <td><s:property value = "statement"/></td>
                                <td><s:property value = "status"/></td>
                                <td><s:property value = "rank"/></td>
                                <td><s:property value = "is_right"/></td>
                                <td>
                                    <form action="modifyAnswer" method="post">
                                        <input type="hidden" value=<s:property value="id"/>  name="modifyIdAnswer" />
                                        <input type="hidden" value=<s:property value="question_id"/>  name="modifyIdQuestion" />
                                        <input type="hidden" value=${modifyIdForm}  name="modifyIdForm" />
                                        <input type="submit" class="btn btn-outline-primary" value="Modifier"/>
                                    </form>
                                </td>
                                <td>
                                    <form action="activateAnswer" method="post">
                                        <input type="hidden" value=<s:property value="id"/>  name="modifyIdAnswer" />
                                        <input type="hidden" value=<s:property value="question_id"/>  name="modifyIdQuestion" />
                                        <input type="hidden" value=${modifyIdForm}  name="modifyIdForm" />
                                        <c:choose>
                                            <c:when test="${status.equals('actif')}" >
                                               <input type="submit" class="btn btn-danger" value="X"/>
                                            </c:when>
                                            <c:otherwise>
                                                <input type="submit" class="btn btn-success" value="A"/>
                                            </c:otherwise>
                                        </c:choose>
                                    </form>
                                </td>
                                <td>
                                    <form action="upAnswer" method="post">
                                        <input type="hidden" value=<s:property value="id"/>  name="modifyIdAnswer" />
                                        <input type="hidden" value=<s:property value="question_id"/>  name="modifyIdQuestion" />
                                        <input type="hidden" value=${modifyIdForm}  name="modifyIdForm" />
                                        <input type="submit" class="btn btn-outline-primary" value="<"/>
                                    </form>
                                </td>
                                <td>
                                    <form action="downAnswer" method="post">
                                        <input type="hidden" value=<s:property value="id"/>  name="modifyIdAnswer" />
                                        <input type="hidden" value=<s:property value="question_id"/>  name="modifyIdQuestion" />
                                        <input type="hidden" value=${modifyIdForm}  name="modifyIdForm" />
                                        <input type="submit" class="btn btn-outline-primary" value=">"/>
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