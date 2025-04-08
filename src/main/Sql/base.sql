DROP DATABASE ETU003380;

CREATE DATABASE ETU003380;

use ETU003380;

Create table ETU003380_Prevision (
    Id_prevision int Primary Key Auto_increment,
    Libelle_prevision Varchar(50),
    Montant_prevision int
);

Create table ETU003380_Depense (
    Id_Depense int Primary Key Auto_increment,
    Libelle_Depense Varchar(50),
    Montant_depense int,
    Date_depense Date
);
