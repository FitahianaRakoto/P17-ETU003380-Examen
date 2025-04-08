package hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import Code.Insertion;

public class Insert_depense extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Laissez cette méthode vide si vous ne l'utilisez pas
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html; charset=UTF-8");
        PrintWriter out = res.getWriter();

        // Récupérer les paramètres
        String libelle = req.getParameter("libelle");
        String montant = req.getParameter("montant");
        String dateStr = req.getParameter("date");

        // Initialiser la variable pour le montant
        int montantInt = 0;
        
        // Validation du montant
        try {
            if (montant != null && !montant.trim().isEmpty()) {
                montantInt = Integer.parseInt(montant);
            }
        } catch (NumberFormatException e) {
            out.println("<html><body>");
            out.println("<h1>Le montant n'est pas valide !</h1>");
            out.println("</body></html>");
            return; // Si le montant n'est pas valide, on arrête l'exécution
        }

        // Validation de la date
        LocalDate date = null;
        if (dateStr != null && !dateStr.isEmpty()) {
            try {
                // Définir le format attendu de la chaîne
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                // Convertir la chaîne en LocalDate
                date = LocalDate.parse(dateStr, formatter);
                System.out.println(date);  // Affiche la date au format LocalDate
            } catch (Exception e) {
                out.println("<html><body>");
                out.println("<h1>Erreur de format de date : " + e.getMessage() + "</h1>");
                out.println("</body></html>");
                return; // Si la date est invalide, on arrête l'exécution
            }
        }

        // Vérifier si les paramètres sont valides
        if (libelle != null && !libelle.trim().isEmpty() && date != null) {
            Insertion insert = new Insertion();

            try {
                // Convertir LocalDate en java.sql.Date
                Date sqlDate = Date.valueOf(date);
                insert.Ligne_depense(libelle, montantInt, sqlDate);
                
                // Réponse de succès
                out.println("<html><body>");
                out.println("<h1>Insertion réussie : " + libelle + " est de montant " + montantInt + " pour la date " + date + "</h1>");
                out.println("</body></html>");
            } catch (SQLException e) {
                out.println("<html><body>");
                out.println("<h1>Erreur lors de l'insertion dans la base de données : " + e.getMessage() + "</h1>");
                out.println("</body></html>");
                e.printStackTrace(); // Afficher l'erreur dans la console du serveur
            }
        } else {
            // Si le libelle ou la date est invalide
            out.println("<html><body>");
            out.println("<h1>Nom de la dépense invalide ou date incorrecte !</h1>");
            out.println("</body></html>");
        }
    }
}
