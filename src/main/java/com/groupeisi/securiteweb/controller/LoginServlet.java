package com.groupeisi.securiteweb.controller;

import com.groupeisi.securiteweb.dao.ICompte;
import com.groupeisi.securiteweb.dao.CompteImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/Login", name = "login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/login/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ICompte cdao = new CompteImpl();
        if(cdao.login(req.getParameter("username"), req.getParameter("pass"))!=null) {
            req.setAttribute("success", "Bienvenue "+req.getParameter("username"));
            HttpSession session=req.getSession();
            session.setAttribute("user",cdao.login(req.getParameter("username"), req.getParameter("pass")));
            req.getRequestDispatcher("views/home/index.jsp").forward(req, resp);
        }else {
            //resp.getWriter().println("Login et ou mot de passe incorrect!");
            // req.setAttribute();
            req.setAttribute("error", "Login et/ou mot de pass incorrect!");
            doGet(req, resp);
        }
        //System.out.println(cdao.login(req.getParameter("username"), req.getParameter("pass")));

    }


}
