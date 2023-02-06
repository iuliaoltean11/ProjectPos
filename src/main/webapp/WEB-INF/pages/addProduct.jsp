<%--
  Created by IntelliJ IDEA.
  User: Raysa
  Date: 1/7/2023
  Time: 5:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageFormular pageTitle="AddProduct">
    <h1>AddProduct</h1>
    <form class="needs-validation" novalidate method="post" action="${pageContext.request.contextPath}/AddProduct">

        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="Name">Product Name</label>
                <input type="text" class="form-control" id="Name" name="Name" placeholder="" value="" required>
                <div class="invalid-feedback">
                    Name is required
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="Category">Category</label>
                <input type="text" class="form-control" id="Category" name="Category" placeholder="" value="" required>
                <div class="invalid-feedback">
                    Category is required
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="Price">Price</label>
                <input type="text" class="form-control" id="Price" name="Price" placeholder="" value="" required>
                <div class="invalid-feedback">
                    Price is required
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="Quantity">Quantity</label>
                <input type="text" class="form-control" id="Quantity" name="Quantity" placeholder="" value="" required>
                <div class="invalid-feedback">
                    Quantity is required
                </div>
            </div>
        </div>

        <hr class="nb-4">
        <input  class="btn btn-primary btn-lg" type="submit" value="Submit">
    </form>
</t:pageFormular>
