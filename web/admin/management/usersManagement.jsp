<%@ page contentType = "text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "s" uri = "/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>Admin</title>
    </head>
    <body>
        <div class="container">
            <div class="row justify-content-md-center">
                <h1>Gestion des Utilisateurs : <s:property value="#session.userMail"/></h1>
            </div>
            <div class="row">
                    <form action="LoginAdmin" method="post">
                        <div class="form-group">
                            <input type="hidden" name="userEmail" value=<s:property value="#session.userMail" />>
                            <button type="submit" class="btn btn-light">Back</button>
                        </div>
                    </form>
                    <div class="form-group">
                        <button type="submit" class="btn btn-danger">Logout</button>     
                    </div>
            </div>

            <div class="row">
                <div>
                    <form action="addUserForm" method="post">
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary">Créer un utilisateur</button>
                        </div>
                    </form>   
                </div>
            </div>

            <div class="row justify-content-md-center">   
                <h2> Liste des utilisateurs actifs</h2>
            </div>

            <div class="row">
                <table class="table table-hover">
                    <thead class="thead-dark">
                        <tr>
                            <th>Nom</th>
                            <th>Prénom</th>
                            <th>Email</th>
                            <th>Société</th>
                            <th>Téléphone</th>
                            <th>Status</th>
                            <th>Admin</th>
                            <th>Gender</th>
                            <th>Modifier</th>
                            <th>Désactiver</th>
                            <th>Détail</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value = "users_actif" var="user">
                            <tr>
                                <td><s:property value = "lastname"/></td>
                                <td><s:property value = "firstname"/></td>
                                <td><s:property value = "email"/></td>
                                <td><s:property value = "company"/></td>
                                <td><s:property value = "tel"/></td>
                                <td><s:property value = "status"/></td>
                                <td><s:property value = "is_admin"/></td>
                                <td><s:property value= "gender"/></td>
                                <td>
                                    <s:form action="modifyUser" method="post">
                                        <input type="hidden" value=<s:property value="email"/>  name="modifyEmail" />
                                        <s:submit cssClass="btn btn-outline-primary" value="Modifier"/> 
                                    </s:form>
                                </td>
                                <td>
                                    <s:form action="activateUser" method="post">
                                        <input type="hidden" value=<s:property value="email"/>  name="modifyEmail" />
                                        <s:if test="status == 'actif'" >
                                           <s:submit cssClass="btn btn-danger" value="X"/>
                                        </s:if>
                                        <s:else>
                                            <s:submit cssClass="btn btn-success" value="A"/>
                                        </s:else>
                                    </s:form>
                                </td>
                                <td>
                                    <s:form action="showUser" method="post">
                                        <input type="hidden" value=<s:property value="email"/>  name="traineeMail" />
                                        <s:if test="is_admin()" >
                                           <s:submit cssClass="btn btn-outline-primary" disabled="true" value="0"/>
                                        </s:if>
                                        <s:else>
                                            <s:submit cssClass="btn btn-outline-primary" value="0"/>
                                        </s:else>
                                    </s:form>
                                </td>
                            </tr>  
                        </s:iterator>
                    </tbody>
                </table>         
            </div>
            <div class="row justify-content-md-center">
                <h2> Liste des Utilisateurs inactifs </h2>
            </div>
            <div class="row">
                <table class="table table-hover">
                    <thead class="thead-dark">
                        <tr>
                            <th>Nom</th>
                            <th>Prénom</th>
                            <th>Email</th>
                            <th>Société</th>
                            <th>Téléphone</th>
                            <th>Status</th>
                            <th>Admin</th>
                            <th>Gender</th>
                            <th>Modifier</th>
                            <th>Activer</th>
                            <th>Détail</th>
                        </tr>        
                    </thead>
                    <tbody>
                        <s:iterator value = "users_inactif" status="user">
                            <tr>
                                <td><s:property value = "lastname"/></td>
                                <td><s:property value = "firstname"/></td>
                                <td><s:property value = "email"/></td>
                                <td><s:property value = "company"/></td>
                                <td><s:property value = "tel"/></td>
                                <td><s:property value = "status"/></td>
                                <td><s:property value = "is_admin"/></td>
                                <td><s:property value= "gender"/></td>
                                <td>
                                    <s:form action="modifyUser" method="post">
                                        <input type="hidden" value=<s:property value="email"/>  name="modifyEmail" />
                                        <s:submit cssClass="btn btn-outline-primary" value="Modifier"/>
                                    </s:form>
                                </td>
                                <td>
                                    <s:form action="activateUser" method="post">
                                        <input type="hidden" value=<s:property value="email"/>  name="modifyEmail" />
                                        <s:if test="status == 'actif'" >
                                           <s:submit cssClass="btn btn-danger" value="X"/>
                                        </s:if>
                                        <s:else>
                                            <s:submit cssClass="btn btn-success" value="A"/>
                                        </s:else>
                                    </s:form>
                                </td>
                                <td>
                                    <s:form action="showUser" method="post">
                                        <input type="hidden" value=<s:property value="email"/>  name="traineeMail" />
                                        <s:if test="is_admin()" >
                                           <s:submit cssClass="btn btn-outline-primary" disabled="true" value="0"/>
                                        </s:if>
                                        <s:else>
                                            <s:submit cssClass="btn btn-outline-primary" value="0"/>
                                        </s:else>
                                    </s:form>
                                </td>
                            </tr>
                        </s:iterator>                   
                    </tbody>
                </table>
            </div>
        </div>

        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
