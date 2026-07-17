package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import dao.ProdottoDAO;
import dao.ProdottoDAOImpl;
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
    
    private ProdottoDAO prodottoDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.prodottoDAO = new ProdottoDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("adminLoggato") == null) {
            response.sendRedirect(request.getContextPath() + "/admin/login");
            return;
        }

        try {
            List<Prodotto> prodotti = prodottoDAO.doRetrieveAll();
            request.setAttribute("prodotti", prodotti);
            request.getRequestDispatcher("/WEB-INF/view/admin/dashboard.jsp")
                   .forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}