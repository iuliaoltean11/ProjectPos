<%--
  Created by IntelliJ IDEA.
  User: iulia
  Date: 1/28/2023
  Time: 6:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Cart">
    <div>
        <p class="titlu-produs">Cart</p>
    </div>
    <div class="container">
    <div class="row">
        <div class="container-fluid cards">
            <c:forEach var="product" items="${products}">
                <div class="card">
                    <img class="card__image" src="${pageContext.request.contextPath}/ProductPhotos?id=${product.id}"
                         alt="photo">
                    <div class="card__content">
                        <p class="product-name">${product.name}</p>
                        <p class="product-price">${product.price}&nbsp;lei</p>
                        <form method="get" action="${pageContext.request.contextPath}/DeleteFromCart">
                            <input type="hidden" value="${product.id}" name="prod_id">
                            <button type="submit" class="product-button">Delete from cart</button>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <div class="row">
        <div class="col-sm">
            <p class="product-name">Total: ${total}</p>
            <c:if test="${products.size() > 0}">
                <form action="${pageContext.request.contextPath}/Cart" method="post">
                    <button type="submit" class="product-button">Comanda</button>
                </form>
            </c:if>

        </div>
    </div>
    <div>
</t:pageTemplate>
