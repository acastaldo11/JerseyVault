package control;

import java.io.IOException;
import java.sql.SQLException;
import dao.AmministratoreDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Amministratore;

@WebServlet("/admin/login")
public class AdminLoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/admin/login.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || password == null ||
            email.trim().isEmpty() || password.trim().isEmpty()) {
            request.setAttribute("error", "Email e password obbligatorie.");
            request.getRequestDispatcher("/WEB-INF/view/admin/login.jsp")
                   .forward(request, response);
            return;
        }

        try {
            AmministratoreDAO adminDAO = new AmministratoreDAO();
            Amministratore admin = adminDAO.doRetrieveByEmail(email.trim());

            if (admin != null && admin.getPasswordHash().equals(password.trim())) {
                HttpSession session = request.getSession(true);
                session.setAttribute("adminLoggato", admin);
                response.sendRedirect(request.getContextPath() + "/admin/dashboard");
            } else {
                request.setAttribute("error", "Credenziali errate.");
                request.getRequestDispatcher("/WEB-INF/view/admin/login.jsp")
                       .forward(request, response);
            }
        } catch (SQLException e) {
            request.setAttribute("error", "Errore di sistema.");
            request.getRequestDispatcher("/WEB-INF/view/admin/login.jsp")
                   .forward(request, response);
        }
    }
}