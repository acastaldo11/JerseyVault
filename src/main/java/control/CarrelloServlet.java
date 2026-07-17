package control;

import java.io.IOException;
import java.sql.SQLException;
import dao.ProdottoDAO;
import dao.ProdottoDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Carrello;
import model.Prodotto;

@WebServlet("/carrello")
public class CarrelloServlet extends HttpServlet {

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
        request.getRequestDispatcher("/WEB-INF/view/carrello.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("token") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getParameter("action");

        Carrello carrello = (Carrello) session.getAttribute("carrello");
        if (carrello == null) {
            carrello = new Carrello();
            session.setAttribute("carrello", carrello);
        }

        try {
            if ("aggiungi".equals(action)) {
                int idProdotto = Integer.parseInt(request.getParameter("idProdotto"));
                Prodotto p = prodottoDAO.doRetrieveById(idProdotto);
                if (p != null) {
                    carrello.aggiungi(p, 1);
                }
            } else if ("rimuovi".equals(action)) {
                int idProdotto = Integer.parseInt(request.getParameter("idProdotto"));
                carrello.rimuovi(idProdotto);
            } else if ("aggiorna".equals(action)) {
                int idProdotto = Integer.parseInt(request.getParameter("idProdotto"));
                int quantita = Integer.parseInt(request.getParameter("quantita"));
                carrello.aggiorna(idProdotto, quantita);
            } else if ("svuota".equals(action)) {
                carrello.svuota();
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/carrello");
            return;
        }

        response.sendRedirect(request.getContextPath() + "/carrello");
    }
}