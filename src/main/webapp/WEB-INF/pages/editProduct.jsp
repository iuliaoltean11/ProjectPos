<%--
  Created by IntelliJ IDEA.
  User: Raysa
  Date: 1/17/2023
  Time: 10:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageFormular pageTitle="EditProduct">
  <h1>EditProduct</h1>
  <form class="needs-validation" novalidate method="post" action="${pageContext.request.contextPath}/EditProduct">

    <div class="row">
      <div class="col-md-6 mb-3">
        <label for="NAME">Name</label>
        <input type="text" class="form-control" id="NAME" name="NAME" placeholder="" value="${product.name}" required>
        <div class="invalid-feedback">
          Name is required
        </div>
      </div>
    </div>

    <div class="row">
      <div class="col-md-6 mb-3">
        <label for="CATEGORY">Category</label>
        <input type="text" class="form-control" id="CATEGORY" name="CATEGORY" placeholder="" value="${product.category}" required>
        <div class="invalid-feedback">
          Category is required
        </div>
      </div>
    </div>

    <div class="row">
      <div class="col-md-6 mb-3">
        <label for="PRICE">Price</label>
        <input type="text" class="form-control" id="PRICE" name="PRICE" placeholder="" value="${product.price}" required>
        <div class="invalid-feedback">
          Price is required
        </div>
      </div>
    </div>

    <div class="row">
      <div class="col-md-6 mb-3">
        <label for="QUANTITY">Quantity</label>
        <input type="text" class="form-control" id="QUANTITY" name="QUANTITY" placeholder="" value="${product.quantity}" required>
        <div class="invalid-feedback">
          Quantity is required
        </div>
      </div>
    </div>

    <hr class="nb-4">
    <input  type="hidden" name="product_id" value="${product.id}" />
    <hr class="nb-4">
    <input  class="btn btn-primary btn-lg" type="submit" value="Submit">
  </form>
</t:pageFormular>




