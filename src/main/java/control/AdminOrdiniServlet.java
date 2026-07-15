package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import dao.OrdineDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Ordine;

@WebServlet("/admin/ordini")
public class AdminOrdiniServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("adminLoggato") == null) {
            response.sendRedirect(request.getContextPath() + "/admin/login");
            return;
        }

        // Recupera i filtri dalla request
        String idCliente = request.getParameter("idCliente");
        String dataInizio = request.getParameter("dataInizio");
        String dataFine = request.getParameter("dataFine");

        try {
            OrdineDAO ordineDAO = new OrdineDAO();
            List<Ordine> ordini = ordineDAO.doRetrieveByFiltri(idCliente, dataInizio, dataFine);
            request.setAttribute("ordini", ordini);
            request.setAttribute("idCliente", idCliente);
            request.setAttribute("dataInizio", dataInizio);
            request.setAttribute("dataFine", dataFine);
            request.getRequestDispatcher("/WEB-INF/view/admin/ordini.jsp")
                   .forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}