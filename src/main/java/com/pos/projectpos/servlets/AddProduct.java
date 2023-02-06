package com.pos.projectpos.servlets;

import com.pos.projectpos.ejb.ProductBean;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@DeclareRoles({"READ_PRODUCTS", "WRITE_PRODUCTS", "ADMIN"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"WRITE_PRODUCTS", "ADMIN"}))
@WebServlet(name="AddProduct", value ="/AddProduct")
public class AddProduct extends HttpServlet {

    @Inject
    ProductBean productBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/addProduct.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("Name");
        String category=request.getParameter("Category");
        String price=request.getParameter("Price");
        String quantity=request.getParameter("Quantity");

        productBean.createProduct(name,category,price, quantity);

        response.sendRedirect(request.getContextPath() + "/Products");

    }
}
