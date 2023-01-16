package com.groupeisi.securiteweb.dao;


import com.groupeisi.securiteweb.dto.CompteDto;
import com.groupeisi.securiteweb.dto.DroitDto;
import com.groupeisi.securiteweb.entities.Compte;
import com.groupeisi.securiteweb.entities.Droit;

import java.util.ArrayList;
import java.util.List;

public class CompteImpl extends RepositoryImpl<Compte> implements ICompte {

    @Override
    public Compte getByUsername(String comptename) {

        //return (ComPte) session.get(ComPte.class, comptename); //A revoir
        return (Compte) session.createQuery("FROM Compte compte WHERE compte.username = :compte_comptename")
                .setParameter("compte_comptename", comptename).uniqueResult();
    }

    @Override
    public Compte login(String comptename, String password) {
        List<Compte> comptes = new ArrayList<>();
        Compte i = new Compte();
        comptes = this.list(i);
        for (Compte ing : comptes) {
            if(ing.getUsername().equals(comptename) && ing.getPassword().equals(password)) {
                return ing;
            }
        }
        return null;
    }
    public Compte CompteDtoToCompteEntity (CompteDto compteDto) {
        Compte compte = new Compte();
        compte.setId(compteDto.getId());
        compte.setUsername(compteDto.getUsername());
        if (compteDto.getDroits() != null) {
            List<Droit> droits = new ArrayList<Droit>();
            compteDto.getDroits().forEach(name->{
                Droit role = new DroitImpl().getByNom(name.getName());
                droits.add(role);
            });
            compte.setDroits(droits);
        }
        compte.setPassword(compteDto.getPassword());
        return compte;
    }
    public CompteDto CompteEntityToCompteDto (Compte compte) {
        CompteDto comptedto = new CompteDto();
        comptedto.setId(compte.getId());
        comptedto.setUsername(compte.getUsername());
        if (compte.getDroits() != null) {
            List<DroitDto> nomDroits = new ArrayList<DroitDto>();
            compte.getDroits().forEach(droits->{
                nomDroits.add(new DroitDto(droits.getId(), droits.getName()) );
            });
            comptedto.setDroits(nomDroits);
        }

        return comptedto;
    }
}
