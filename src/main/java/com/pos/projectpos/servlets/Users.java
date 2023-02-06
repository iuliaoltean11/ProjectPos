package com.pos.projectpos.servlets;

import com.pos.projectpos.common.UserDto;
import com.pos.projectpos.ejb.UserBean;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@DeclareRoles({"READ_USERS", "WRITE_USERS", "ADMIN"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"READ_USERS","WRITE_USERS"}),
        httpMethodConstraints = {@HttpMethodConstraint(value = "POST", rolesAllowed =
                {"WRITE_USERS"})})
@WebServlet(name = "Users", value = "/Users")
public class Users extends HttpServlet {

    @Inject
    UserBean usersBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserDto> users = usersBean.findAllUsers();
        request.setAttribute("user", users);

        request.getRequestDispatcher("/WEB-INF/pages/users.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


//        String[] userIdsAsString=request.getParameterValues("user_ids");
//        if(userIdsAsString!=null){
//            List<Long>  userIds=new ArrayList<>();
//            for (String userIdAsString:userIdsAsString){
//                userIds.add(Long.parseLong(userIdAsString));
//            }
//
//        }
        Long id = Long.valueOf(request.getParameter("user_id"));
        usersBean.deleteUserById(id);

        response.sendRedirect(request.getContextPath()+"/Users");

    }
}
