package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import dao.OrdineDAO;
import dao.OrdineDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cliente;
import model.Ordine;

@WebServlet("/ordini")
public class OrdiniClienteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private OrdineDAO ordineDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.ordineDAO = new OrdineDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("token") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Cliente cliente = (Cliente) session.getAttribute("utenteLoggato");
        if (cliente == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            List<Ordine> ordini = ordineDAO.doRetrieveByCliente(cliente.getId());
            request.setAttribute("ordini", ordini);
            request.getRequestDispatcher("/WEB-INF/view/ordini.jsp")
                   .forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}