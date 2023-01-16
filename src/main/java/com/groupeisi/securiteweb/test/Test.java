package com.groupeisi.securiteweb.test;

import com.groupeisi.securiteweb.dao.IDroit;
import com.groupeisi.securiteweb.dao.DroitImpl;
import com.groupeisi.securiteweb.entities.Droit;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class Test {
    private static Logger logger = Logger.getLogger(Test.class);
    public static void main(String[] args){
        Droit r = new Droit();
        r.setName("Patissier");
        IDroit ddao = new DroitImpl();
        int result = ddao.add(r);
        BasicConfigurator.configure();
        logger.debug(result);
    }
}
