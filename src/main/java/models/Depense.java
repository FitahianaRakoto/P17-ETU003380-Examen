package models;

import java.sql.Date;
import java.sql.ResultSet;

public class Depense{

    public int id;
    public String libelle;
    public int montant;
    public Date daty;

    public Depense(int rs,int idDepartemen,String nom,Date daty){
        this.id =rs;
        this.libelle = nom;
        this.montant = idDepartemen;
        this.daty = daty;
    }


}