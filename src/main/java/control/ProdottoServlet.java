package control;

import java.io.IOException;
import java.sql.SQLException;
import dao.ProdottoDAO;
import dao.ProdottoDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServlet;
import model.Prodotto;

@WebServlet("/prodotto")
public class ProdottoServlet extends HttpServlet {

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

        String idParam = request.getParameter("id");
        if (idParam == null || idParam.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/catalogo");
            return;
        }

        try {
            int id = Integer.parseInt(idParam);
            Prodotto prodotto = prodottoDAO.doRetrieveById(id);

            if (prodotto == null) {
                response.sendRedirect(request.getContextPath() + "/catalogo");
                return;
            }

            request.setAttribute("prodotto", prodotto);
            request.getRequestDispatcher("/WEB-INF/view/prodotto.jsp")
                   .forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/catalogo");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}