package control;

import java.io.IOException;
import java.sql.SQLException;
import dao.UtenteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cliente;

@WebServlet("/checkEmail")
public class CheckEmailServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            UtenteDAO utenteDAO = new UtenteDAO();
            Cliente cliente = utenteDAO.doRetrieveByEmail(email);

            if (cliente != null) {
                response.getWriter().write("{\"disponibile\": false}");
            } else {
                response.getWriter().write("{\"disponibile\": true}");
            }
        } catch (SQLException e) {
            response.getWriter().write("{\"disponibile\": true}");
        }
    }
}