package com.groupeisi.securiteweb.controller;

import com.groupeisi.securiteweb.dto.DroitDto;
import com.groupeisi.securiteweb.dto.CompteDto;
import com.groupeisi.securiteweb.service.IDroitDto;
import com.groupeisi.securiteweb.service.ICompteDto;
import com.groupeisi.securiteweb.service.DroitDtoImpl;
import com.groupeisi.securiteweb.service.CompteDtoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/Compte", name = "compte")
public class CompteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IDroitDto ddto = new DroitDtoImpl();
        DroitDto r = new DroitDto();
        List<DroitDto> list = ddto.list();
        req.setAttribute("droits", list);
        HttpSession session=req.getSession();
        if(session.getAttribute("user")==null) {
            req.setAttribute("error", "Vous devez vous connecter d'abord!");
            req.getRequestDispatcher("/views/login/login.jsp").forward(req, resp);
        }else
            req.getRequestDispatcher("views/compte/add.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ICompteDto cdto = new CompteDtoImpl();
        CompteDto u = new CompteDto();
        u.setUsername(req.getParameter("username"));
        u.setPassword(req.getParameter("pass"));
        List<DroitDto> droitsList = new ArrayList<>();
        IDroitDto ddto = new DroitDtoImpl();
        DroitDto r = new DroitDto();
        List<DroitDto> droits = ddto.list();
        String result  = req.getParameter("droits");
        for (DroitDto newd: droits) {
            if (newd.getId() == Integer.parseInt(result)){
                droitsList.add(newd);
                break;
            }
        }
        /*String result  = req.getParameter("droits");
        String[] tab = result.split(",");
        System.out.println(result);
        System.exit(0);
        for (String s: tab) {
            IDroit ddao = new DroitImpl();
            Droit r = new Droit();
            List<Droit> droits = ddao.list(r);
            for (Droit newd: droits) {
                if (newd.getId() == Integer.parseInt(result)){
                    droitsList.add(newd);
                    break;
                }
            }
        //}
        u.setDroits(droitsList);*/
        u.setDroits(droitsList);

        if (cdto.add(u)==1){
            req.setAttribute("success", "Compte ajouté avec succes!");
            //req.getRequestDispatcher("vue/user/add.jsp").forward(req, resp);
            doGet(req, resp);
        }

    }

}

