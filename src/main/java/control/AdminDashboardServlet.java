package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import dao.ProdottoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Prodotto;

@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Controllo e solo l'admin può accedere
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("adminLoggato") == null) {
            response.sendRedirect(request.getContextPath() + "/admin/login");
            return;
        }

        // Recupera  i prodotti e li manda alla JSP
        try {
            ProdottoDAO prodottoDAO = new ProdottoDAO();
            List<Prodotto> prodotti = prodottoDAO.doRetrieveAll();
            request.setAttribute("prodotti", prodotti);
            request.getRequestDispatcher("/WEB-INF/view/admin/dashboard.jsp")
                   .forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}