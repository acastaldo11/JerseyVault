package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import dao.CategoriaDAO;
import dao.CategoriaDAOImpl;
import dao.ProdottoDAO;
import dao.ProdottoDAOImpl;
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

    private ProdottoDAO prodottoDAO;
    private CategoriaDAO categoriaDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.prodottoDAO = new ProdottoDAOImpl();
        this.categoriaDAO = new CategoriaDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<Categoria> categorie = categoriaDAO.doRetrieveAll();
            request.setAttribute("categorie", categorie);

            String categoriaParam = request.getParameter("categoria");
            List<Prodotto> prodotti;

            if (categoriaParam != null && !categoriaParam.trim().isEmpty()) {
                try {
                    int idCategoria = Integer.parseInt(categoriaParam);
                    prodotti = prodottoDAO.doRetrieveByCategoria(idCategoria);
                } catch (NumberFormatException e) {
                    prodotti = prodottoDAO.doRetrieveAll();
                }
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