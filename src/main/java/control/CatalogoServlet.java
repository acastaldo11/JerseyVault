package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import dao.CategoriaDAO;
import dao.ProdottoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Categoria;
import model.Prodotto;

@WebServlet("/catalogo")
public class CatalogoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            ProdottoDAO prodottoDAO = new ProdottoDAO();
            CategoriaDAO categoriaDAO = new CategoriaDAO();

            List<Categoria> categorie = categoriaDAO.doRetrieveAll();
            request.setAttribute("categorie", categorie);

            // Controlla se c'è un filtro per categoria
            String categoriaParam = request.getParameter("categoria");
            List<Prodotto> prodotti;

            if (categoriaParam != null && !categoriaParam.trim().isEmpty()) {
                int idCategoria = Integer.parseInt(categoriaParam);
                prodotti = prodottoDAO.doRetrieveByCategoria(idCategoria);
            } else {
                prodotti = prodottoDAO.doRetrieveAll();
            }

            request.setAttribute("prodotti", prodotti);
            request.getRequestDispatcher("/WEB-INF/view/catalogo.jsp")
                   .forward(request, response);

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}