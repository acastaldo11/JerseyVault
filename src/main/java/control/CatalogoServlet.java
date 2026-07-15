package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.ProdottoDAO;
import dao.CategoriaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Prodotto;
import model.Categoria;

@WebServlet("/catalogo")
public class CatalogoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            ProdottoDAO prodottoDAO = new ProdottoDAO();
            CategoriaDAO categoriaDAO = new CategoriaDAO();

            // Recupera tutti i prodotti non cancellati
            List<Prodotto> prodotti = prodottoDAO.doRetrieveAll();

            // Recupera tutte le categorie (per i filtri)
            List<Categoria> categorie = categoriaDAO.doRetrieveAll();

            // Metti i dati nella request
            request.setAttribute("prodotti", prodotti);
            request.setAttribute("categorie", categorie);

            // Forward alla JSP
            request.getRequestDispatcher("/WEB-INF/view/catalogo.jsp")
                   .forward(request, response);

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}