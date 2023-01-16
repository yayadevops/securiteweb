package com.groupeisi.securiteweb.dao;

import com.groupeisi.securiteweb.entities.Compte;

public interface ICompte extends IRepository<Compte> {
    public Compte login(String username, String password);
    public Compte getByUsername(String username);
}
