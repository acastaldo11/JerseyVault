package control;

import java.io.IOException;
import java.sql.SQLException;
import dao.UtenteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cliente;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || password == null ||
            email.trim().isEmpty() || password.trim().isEmpty()) {
            request.setAttribute("error", "Email e password sono obbligatorie.");
            request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
            return;
        }

        UtenteDAO utenteDAO = new UtenteDAO();
        try {
            Cliente cliente = utenteDAO.doRetrieveByEmail(email.trim());

            if (cliente != null && cliente.getPasswordHash().equals(password.trim())) {
                HttpSession session = request.getSession(true);
                session.setAttribute("utenteLoggato", cliente);
                
                // Generazione token di sessione
                String token = java.util.UUID.randomUUID().toString();
                session.setAttribute("token", token);

                response.sendRedirect(request.getContextPath() + "/catalogo");
            } else {
                request.setAttribute("error", "Email o password errate.");
                request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            request.setAttribute("error", "Errore di sistema. Riprovare più tardi.");
            request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
        }
    }
}