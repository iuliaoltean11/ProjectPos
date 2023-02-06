<%--
  Created by IntelliJ IDEA.
  User: Raysa
  Date: 1/17/2023
  Time: 10:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Users">
    <h1>Users</h1>


    <%--    <form method="POST" action="${pageContext.request.contextPath}/Users">--%>
    <c:if test="${pageContext.request.isUserInRole('WRITE_USERS')}">
        <div class="row">
            <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/AddUser">Add User</a>
        </div>
    </c:if>
    <div class="container text-center">
        <c:forEach var="user" items="${user}">
            <div class="row">
                <div class="col">
                    <input type="checkbox" name="user_ids" value="${user.id}"/>
                </div>
                <div class="col">
                        ${user.email}
                </div>
                <div class="col">
                        ${user.username}
                </div>
                <div class="col">
                        ${user.money}
                </div>
                <div class="col">
                    <form method="post" action="${pageContext.request.contextPath}/Users">
                        <input type="hidden" name="user_id" value="${user.id}">
                        <button type="submit" class="btn btn-danger">Delete</button>
                    </form>
                </div>
            </div>
        </c:forEach>
    </div>
    <%--    </form>--%>


</t:pageTemplate>
