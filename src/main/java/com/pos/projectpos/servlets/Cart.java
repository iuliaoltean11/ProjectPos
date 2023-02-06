package com.pos.projectpos.servlets;

import com.pos.projectpos.PdfUtils;
import com.pos.projectpos.common.ProductDto;
import com.pos.projectpos.ejb.ProductBean;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"WRITE_USERS"}))
@WebServlet(name = "Cart", value = "/Cart")
public class Cart extends HttpServlet {

    @Inject
    ProductBean productBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<ProductDto> productList = null;

        if (session.getAttribute("cartList") != null) {
            productList = (List<ProductDto>) session.getAttribute("cartList");
        }
        if (productList != null) {
            double total = 0;
            for (ProductDto product : productList) {
                total += product.getPrice();
            }
            request.setAttribute("total", total);
        }
        request.setAttribute("products", productList);
        request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<ProductDto> productList = (List<ProductDto>) session.getAttribute("cartList");

        double total = 0;
        for (ProductDto product : productList) {
            total += product.getPrice();
        }

        PdfUtils pdf = new PdfUtils(productList, total);

        for (ProductDto productDto : productList) {
            productBean.updateProduct(productDto.getId(), productDto.getName(), productDto.getCategory(), productDto.getPrice(), productDto.getQuantity() - 1);
        }
        List<ProductDto> list = new ArrayList<>();
        session.setAttribute("cartList", list);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String filename = "Factura.pdf";
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\iulia\\Downloads\\ProjectPos\\ProjectPos\\receipt.pdf");

        int i;
        while ((i = fileInputStream.read()) != -1) {
            out.write(i);
        }

        fileInputStream.close();
        out.close();
        response.sendRedirect(request.getContextPath()+"/Products");
    }

}


