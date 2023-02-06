package com.pos.projectpos.servlets;

import com.pos.projectpos.common.ProductDto;
import com.pos.projectpos.common.UserDto;
import com.pos.projectpos.ejb.ProductBean;
import com.pos.projectpos.ejb.UserBean;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"WRITE_PRODUCTS"}))
@WebServlet(name="EditProduct", value ="/EditProduct")
public class EditProduct  extends HttpServlet {
    @Inject
    UserBean usersBean;
    @Inject
    ProductBean productBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserDto> users=usersBean.findAllUsers();
        request.setAttribute("users",users);

        Long productId=Long.parseLong(request.getParameter("id"));
        ProductDto product=productBean.findById(productId);
        request.setAttribute("product",product);


        request.getRequestDispatcher("/WEB-INF/pages/editProduct.jsp").forward(request,response);

    }
    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        Long productId= Long.valueOf(request.getParameter("product_id"));
        String name=request.getParameter("NAME");
        String category=request.getParameter("CATEGORY");
        Double price= Double.valueOf(request.getParameter("PRICE"));
        Integer quantity= Integer.valueOf(request.getParameter("QUANTITY"));

        productBean.updateProduct(productId,name,category,price,quantity);

        response.sendRedirect(request.getContextPath()+"/Products");
    }

}


