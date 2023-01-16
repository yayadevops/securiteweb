package com.groupeisi.securiteweb.dao;


import com.groupeisi.securiteweb.entities.Droit;

public interface IDroit extends IRepository<Droit> {
    public Droit getByNom(String name);
}
