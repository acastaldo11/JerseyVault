package control;

import java.io.IOException;
import java.sql.SQLException;
import dao.UtenteDAO;
import dao.UtenteDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cliente;

@WebServlet(name = "RegistrazioneServlet", urlPatterns = {"/registrazione"})
public class RegistrazioneServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UtenteDAO utenteDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.utenteDAO = new UtenteDAOImpl();
    }

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

        if (nome == null || cognome == null || email == null || password == null || telefono == null ||
            nome.trim().isEmpty() || cognome.trim().isEmpty() || email.trim().isEmpty() || 
            password.trim().isEmpty() || telefono.trim().isEmpty()) {
            
            request.setAttribute("error", "Tutti i campi sono obbligatori.");
            request.getRequestDispatcher("/WEB-INF/view/registrazione.jsp").forward(request, response);
            return;
        }

        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setCognome(cognome);
        cliente.setEmail(email);
        cliente.setPasswordHash(password);
        cliente.setTelefono(telefono);

        try {
            utenteDAO.doSave(cliente);
            response.sendRedirect(request.getContextPath() + "/login");
        } catch (SQLException e) {
            request.setAttribute("error", "Errore: Email già in uso o problema al database.");
            request.getRequestDispatcher("/WEB-INF/view/registrazione.jsp").forward(request, response);
        }
    }
}