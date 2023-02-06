package com.pos.projectpos.servlets;

import com.pos.projectpos.common.ProductDto;
import com.pos.projectpos.ejb.ProductBean;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@DeclareRoles({"READ_PRODUCTS", "WRITE_PRODUCTS", "ADMIN"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"READ_PRODUCTS","WRITE_USERS", "ADMIN"}),
        httpMethodConstraints = {@HttpMethodConstraint(value = "POST", rolesAllowed =
                {"WRITE_PRODUCTS"})})
@WebServlet(name = "Products", value = "/Products")
public class Products extends HttpServlet {

    @Inject
    ProductBean productsBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ProductDto> products=productsBean.findAllProducts();
        request.setAttribute("products",products);

        request.getRequestDispatcher("/WEB-INF/pages/products.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] productIdsAsString=request.getParameterValues("product_ids");
        if(productIdsAsString != null){
            List<Long> productIds = new ArrayList<>();
            for(String  productIdAsString : productIdsAsString){
                productIds.add(Long.parseLong(productIdAsString));
            }
            productsBean.deleteProductsByIds(productIds);
        }
        response.sendRedirect(request.getContextPath()+"/Products");
    }

}