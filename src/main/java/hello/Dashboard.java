package hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Code.Insertion;
import models.Depense;

public class Dashboard extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        res.setContentType("text/html; charset=UTF-8");
        PrintWriter out = res.getWriter();
        
        ArrayList<Depense> Depansa = null;
        Insertion Insert = new Insertion();
        Depansa = Insert.Get_by_libelle();
        
        out.println("<html><body>");
        out.println("<h1>Liste des depenses</h1>");
        
        // Create a table for the list of depenses
        out.println("<table border ='1'>");
        out.println("<th>Libelle</th>");
        out.println("<th>Montant</th>");
        out.println("<th>Date</th>");
        
        for (Depense vaovao : Depansa) {
            out.println("<tr>");
            out.println("<td>" + vaovao.libelle + "</td>");
            out.println("<td>" + vaovao.montant + "</td>");
            out.println("<td>" + vaovao.daty + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Not implemented, but you can handle POST requests here.
    }
}
