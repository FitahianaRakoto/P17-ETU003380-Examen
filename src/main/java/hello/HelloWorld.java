package hello;

import java.io.*;
import java.sql.SQLException;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import Code.Insertion;

public class HelloWorld extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Implémentation de doGet si nécessaire
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html; charset=UTF-8");
        PrintWriter out = res.getWriter();

        String libelle = req.getParameter("libelle");
        String montant = req.getParameter("montant");

        // Conversion du montant en entier
        int montantInt = Integer.parseInt(montant);

        if (libelle != null && !libelle.trim().isEmpty()) {
            Insertion insert = new Insertion();

            try {
                insert.Ajoutez_ligne_de_credit(libelle, montantInt);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            out.println("<html><body>");
            out.println("<h1>Insertion réussie : " + libelle + " est de montant " + montantInt + "</h1>");
            out.println("</body></html>");
        } else {
            out.println("<html><body>");
            out.println("<h1>Nom de l'employé invalide !</h1>");
            out.println("</body></html>");
        }
    }
}
