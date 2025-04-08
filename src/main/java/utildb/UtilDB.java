package utildb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UtilDB {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/db_s2_ETU003380"; // Chaîne de connexion
        String user = "ETU003380"; // Nom d'utilisateur
        String password = "ZRADExqZ"; // Mot de passe

        // Charger explicitement le driver (utile dans certaines configurations)
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Charger le driver
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC MySQL introuvable !", e);
        }

        // Obtenir la connexion
        return DriverManager.getConnection(url, user, password);
    }
}
