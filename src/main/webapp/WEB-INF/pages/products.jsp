<%--
  Created by IntelliJ IDEA.
  User: Raysa
  Date: 1/17/2023
  Time: 10:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Products">
    <div>
        <p class="titlu-produs">Products</p>
    </div>
    <form method="POST" action="${pageContext.request.contextPath}/Products">
        <c:if test="${pageContext.request.isUserInRole('WRITE_PRODUCTS')}">
        <div style="padding: 10px 0px 20px 2%">
            <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/AddProduct">Add Product</a>
            <button class="btn btn-danger" type="submit">Delete Product</button>
        </div>
        </c:if>
     <div class="container-fluid cards">
         <c:forEach var="product" items="${products}">
             <div class="card">
                 <img class="card__image" src="${pageContext.request.contextPath}/ProductPhotos?id=${product.id}" alt="photo">
                 <div class="card__content">
                     <p class="product-name">${product.name}</p><c:if test="${pageContext.request.isUserInRole('WRITE_PRODUCTS')}"><input type="checkbox" name="product_ids" value="${product.id}"/></c:if>
                     <p class="product-price">${product.price}&nbsp;lei</p>
                     <p class="product-name"> ${product.quantity}&nbsp;left</p>

                     <a class="product-button" href="${pageContext.request.contextPath}/AddToCart?id=${product.id}">ADD TO CART</a>

                     <c:if test="${pageContext.request.isUserInRole('WRITE_PRODUCTS')}">
                     <div>
                         <a class="btn btn-secondary" href="${pageContext.request.contextPath}/AddProductPhoto?id=${product.id}" role="button">Add photo</a>
                         <a class="btn btn-secondary" href="${pageContext.request.contextPath}/EditProduct?id=${product.id}">Edit Product</a>
                     </div>
                     </c:if>

                 </div>
             </div>
            </c:forEach>
        </div>
    </form>
</t:pageTemplate>


