package com.groupeisi.securiteweb.controller;

import com.groupeisi.securiteweb.dto.CompteDto;
import com.groupeisi.securiteweb.service.ICompteDto;
import com.groupeisi.securiteweb.service.CompteDtoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/List/Compte")
public class CompteListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ICompteDto cdto = new CompteDtoImpl();
        List<CompteDto> comptes = cdto.list();

        req.setAttribute("comptes", comptes);
        HttpSession session=req.getSession();
        if(session.getAttribute("user")==null) {
            req.setAttribute("error", "Vous devez vous connecter d'abord!");
            req.getRequestDispatcher("/views/login/login.jsp").forward(req, resp);
        }else
            req.getRequestDispatcher("/views/compte/list.jsp").forward(req, resp);
    }
}
