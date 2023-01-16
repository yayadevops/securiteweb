package com.groupeisi.securiteweb.controller;

import com.groupeisi.securiteweb.dto.DroitDto;
import com.groupeisi.securiteweb.service.IDroitDto;
import com.groupeisi.securiteweb.service.DroitDtoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/Droit", name = "droit")
public class DroitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        if(session.getAttribute("user")==null) {
            req.setAttribute("error", "Vous devez vous connecter d'abord!");
            req.getRequestDispatcher("/views/login/login.jsp").forward(req, resp);
        }else
            req.getRequestDispatcher("views/droit/add.jsp").forward(req, resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IDroitDto rdto = new DroitDtoImpl();
        DroitDto r = new DroitDto();
        r.setName(req.getParameter("name"));
        if (rdto.add(r)==1) {
            req.setAttribute("success", "Droit ajouté avec succes!");
            //req.getRequestDispatcher("views/droit/list.jsp").forward(req, resp);
            doGet(req, resp);
        }
    }
}
