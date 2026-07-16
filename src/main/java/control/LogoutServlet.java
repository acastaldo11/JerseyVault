package control;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recupero la sessione senza crearne una nuova (false)
        HttpSession session = request.getSession(false);
        
        if (session != null) {
            session.invalidate(); // Distrugge la sessione e libera gli attributi (es. utenteLoggato)
        }
        
        // Redirect al catalogo
        response.sendRedirect(request.getContextPath() + "/catalogo");
    }
}