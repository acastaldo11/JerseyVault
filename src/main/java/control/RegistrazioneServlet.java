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

@WebServlet(name = "RegistrazioneServlet", urlPatterns = {"/registrazione"})
public class RegistrazioneServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/registrazione.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String telefono = request.getParameter("telefono");

        // Validazione server-side
        if (nome == null || cognome == null || email == null || password == null || telefono == null ||
            nome.trim().isEmpty() || cognome.trim().isEmpty() || email.trim().isEmpty() || 
            password.trim().isEmpty() || telefono.trim().isEmpty()) {
            
            request.setAttribute("error", "Tutti i campi sono obbligatori.");
            request.getRequestDispatcher("/WEB-INF/view/registrazione.jsp").forward(request, response);
            return;
        }

        // Istanza dell'entità target
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setCognome(cognome);
        cliente.setEmail(email);
        cliente.setPasswordHash(password);
        cliente.setTelefono(telefono);

        // Invocazione del DAO corretto presente nel progetto
        UtenteDAO utenteDAO = new UtenteDAO();
        try {
            utenteDAO.doSave(cliente);
            // PRG Pattern
            response.sendRedirect(request.getContextPath() + "/login");
        } catch (SQLException e) {
            request.setAttribute("error", "Errore: Email già in uso o problema al database.");
            request.getRequestDispatcher("/WEB-INF/view/registrazione.jsp").forward(request, response);
        }
    }
}