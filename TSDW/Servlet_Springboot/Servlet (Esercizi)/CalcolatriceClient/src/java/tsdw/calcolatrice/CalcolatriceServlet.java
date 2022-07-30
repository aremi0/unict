/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsdw.calcolatrice;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gabriele
 */
public class CalcolatriceServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>CalcolatriceServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h3>Servlet CalcolatriceServlet</h3>");
            out.println("<form action='CalcolatriceServlet' method='POST'>");
            out.println("<input type='number' name='x' placeholder='x'>");
            out.println("<input type='number' name='y' placeholder='y'>");
            out.println("<input type='submit' name='op' value='Somma'>");
            out.println("<input type='submit' name='op' value='Differenza'>");
            out.println("<input type='submit' name='op' value='Prodotto'>");
            out.println("<input type='submit' name='op' value='Divisione'>");
            out.println("<input type='submit' name='op' value='Potenza'>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        
        double x = Double.parseDouble(request.getParameter("x"));
        double y = Double.parseDouble(request.getParameter("y"));
        String op = (request.getParameter("op"));
        
        if(op.equals("Somma"))
            out.println("La somma equivale a " + somma(x, y));
        if(op.equals("Differenza"))
            out.println("La differenza equivale a " + differenza(x, y));
        if(op.equals("Prodotto"))
            out.println("Il prodotto equivale a " + prodotto(x, y));
        if(op.equals("Divisione"))
            out.println("Il quoziente equivale a " + divisione(x, y));
       if(op.equals("Potenza"))
            out.println("La potenza equivale a " + potenza(x, y));
        
        out.println("<br><a href=/CalcolatriceClient/CalcolatriceServlet>Indietro</>");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private static double differenza(double x, double y) {
        tsdw.calcolatrice.CalcolatriceService_Service service = new tsdw.calcolatrice.CalcolatriceService_Service();
        tsdw.calcolatrice.CalcolatriceService port = service.getCalcolatriceServicePort();
        return port.differenza(x, y);
    }

    private static double divisione(double x, double y) {
        tsdw.calcolatrice.CalcolatriceService_Service service = new tsdw.calcolatrice.CalcolatriceService_Service();
        tsdw.calcolatrice.CalcolatriceService port = service.getCalcolatriceServicePort();
        return port.divisione(x, y);
    }

    private static double potenza(double x, double y) {
        tsdw.calcolatrice.CalcolatriceService_Service service = new tsdw.calcolatrice.CalcolatriceService_Service();
        tsdw.calcolatrice.CalcolatriceService port = service.getCalcolatriceServicePort();
        return port.potenza(x, y);
    }

    private static double prodotto(double x, double y) {
        tsdw.calcolatrice.CalcolatriceService_Service service = new tsdw.calcolatrice.CalcolatriceService_Service();
        tsdw.calcolatrice.CalcolatriceService port = service.getCalcolatriceServicePort();
        return port.prodotto(x, y);
    }

    private static double somma(double x, double y) {
        tsdw.calcolatrice.CalcolatriceService_Service service = new tsdw.calcolatrice.CalcolatriceService_Service();
        tsdw.calcolatrice.CalcolatriceService port = service.getCalcolatriceServicePort();
        return port.somma(x, y);
    }

}
