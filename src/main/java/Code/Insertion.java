package Code;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utildb.UtilDB;
import models.*;

public class Insertion {

    public Insertion() {
        // Constructeur par défaut (peut être supprimé s'il est inutile)
    }

    public void Ajoutez_ligne_de_credit(String libelle , int montant) throws SQLException {
        String query = "INSERT INTO ETU003380_Prevision (Libelle_prevision,Montant_prevision) VALUES (?,?)";

        try (Connection conn = UtilDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            if (conn == null) {
                throw new SQLException("Erreur : Connexion à la base de données échouée !");
            }

            stmt.setString(1, libelle);
            stmt.setInt(2, montant);

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Insertion réussie !");
            } else {
                throw new SQLException("Échec de l'insertion.");
            }

        }
    }

    
    public String[] Get_libelle() throws SQLException {
    String requete = "SELECT Libelle_prevision FROM ETU003380_Prevision";
    
    // Initialize an ArrayList to store the results (since we don't know how many rows we will get)
    List<String> libelleList = new ArrayList<>();
    
    try (Connection conn = UtilDB.getConnection();
         PreparedStatement stmt = conn.prepareStatement(requete);
         ResultSet rs = stmt.executeQuery()) {

        // Loop through the ResultSet and collect the libelle values
        while (rs.next()) {
            libelleList.add(rs.getString("Libelle_prevision"));
        }

    } catch (SQLException e) {
        System.err.println("Erreur lors de la récupération des libelles : " + e.getMessage());
        throw e;
    }
    
    // Convert the ArrayList to a String array and return it
    return libelleList.toArray(new String[0]);
}

    public void Ligne_depense(String libelle , int montant , Date Daty)  throws SQLException {

        String query = "INSERT INTO ETU003380_Depense (Libelle_Depense,Montant_depense , Date_depense) VALUES (?,?,?)";

        try (Connection conn = UtilDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            if (conn == null) {
                throw new SQLException("Erreur : Connexion à la base de données échouée !");
            }

            stmt.setString(1, libelle);
            stmt.setInt(2, montant);
            
            stmt.setDate(3, Daty);

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Insertion Depense réussie !");
            } else {
                throw new SQLException("Échec de l'insertion.");
            }

        }

    }

    public ArrayList<Depense> Get_by_libelle() {
        ArrayList<Depense> liste_depense = new ArrayList<>();  // Initialize the list
        String requete = "SELECT * FROM ETU003380_Depense";  // Filter by libelle
    
        try (Connection conn = UtilDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(requete)) {
           
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Retrieve data using appropriate get methods
                    int idDepense = rs.getInt("Id_Depense");
                    int montantDepense = rs.getInt("Montant_depense");
                    String libelleDepense = rs.getString("Libelle_Depense");
                    Date dateDepense = rs.getDate("Date_depense");
    
                    // Add the result to the list
                    liste_depense.add(new Depense(idDepense, montantDepense, libelleDepense, dateDepense));
                }
            }
    
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des libelles : " + e.getMessage());
            throw new RuntimeException("Error retrieving data", e);  // Throw a runtime exception or handle accordingly
        }
    
        return liste_depense;
    }

    

}
