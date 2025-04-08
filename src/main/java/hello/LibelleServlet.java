package hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import Code.Insertion;

public class LibelleServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html; charset=UTF-8");
        PrintWriter out = res.getWriter();
        
        // Retrieve libelle values from database
        String[] libelles = null;
        try {
            Insertion insert = new Insertion();
            libelles = insert.Get_libelle(); // Assuming Get_libelle is part of Insertion class
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<html><body>");
            out.println("<h1>Erreur lors de la récupération des libelles de la base de données.</h1>");
            out.println("</body></html>");
            return; // Stop execution if there is an error retrieving data
        }
        
        out.println("<html><body>");
        out.println("<h1>Formulaire d'Insertion</h1>");
        out.println("<form action='/ETU003380/Insertion_Depense' method='POST'>");
        
        // Create a dropdown for libelles
        out.println("<label for='libelle'>Sélectionner un Libelle:</label>");
        out.println("<select name='libelle' id='libelle'>");
        if (libelles != null) {
            for (String libelle : libelles) {
                out.println("<option value='" + libelle + "'>" + libelle + "</option>");
            }
        }
        out.println("</select><br><br>");
        
        // Input field for montant
        out.println("<label for='montant'>Montant:</label>");
        out.println("<input type='number' name='montant' id='montant' required><br><br>");
        
        // Input field for date
        out.println("<label for='date'>Date:</label>");
        out.println("<input type='date' name='date' id='date' required><br><br>");
        
        // Submit button
        out.println("<input type='submit' value='Soumettre'>");
        out.println("</form>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Handle POST request here if needed
    }
}
