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
import jakarta.servlet.http.HttpSession;
import model.Categoria;
import model.Prodotto;

@WebServlet("/admin/prodotto/*")
public class AdminProdottoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Controllo accesso admin
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("adminLoggato") == null) {
            response.sendRedirect(request.getContextPath() + "/admin/login");
            return;
        }

        String pathInfo = request.getPathInfo();

        try {
            ProdottoDAO prodottoDAO = new ProdottoDAO();
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            List<Categoria> categorie = categoriaDAO.doRetrieveAll();
            request.setAttribute("categorie", categorie);

            if ("/nuovo".equals(pathInfo)) {
                // Mostra form per nuovo prodotto
                request.getRequestDispatcher("/WEB-INF/view/admin/prodotto_form.jsp")
                       .forward(request, response);

            } else if ("/modifica".equals(pathInfo)) {
                // Mostra form per modifica prodotto
                int id = Integer.parseInt(request.getParameter("id"));
                Prodotto p = prodottoDAO.doRetrieveById(id);
                request.setAttribute("prodotto", p);
                request.getRequestDispatcher("/WEB-INF/view/admin/prodotto_form.jsp")
                       .forward(request, response);

            } else if ("/elimina".equals(pathInfo)) {
                // Soft delete prodotto
                int id = Integer.parseInt(request.getParameter("id"));
                prodottoDAO.doDelete(id);
                response.sendRedirect(request.getContextPath() + "/admin/dashboard");
            }

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("adminLoggato") == null) {
            response.sendRedirect(request.getContextPath() + "/admin/login");
            return;
        }

        String pathInfo = request.getPathInfo();

        try {
            ProdottoDAO prodottoDAO = new ProdottoDAO();

            String nome = request.getParameter("nome");
            String descrizione = request.getParameter("descrizione");
            String squadra = request.getParameter("squadra");
            String taglia = request.getParameter("taglia");
            double prezzo = Double.parseDouble(request.getParameter("prezzo"));
            int giacenza = Integer.parseInt(request.getParameter("giacenza"));
            int categoriaId = Integer.parseInt(request.getParameter("categoriaId"));
            String immagineUrl = request.getParameter("immagineUrl");

            Prodotto p = new Prodotto();
            p.setNome(nome);
            p.setDescrizione(descrizione);
            p.setSquadra(squadra);
            p.setTaglia(taglia);
            p.setPrezzo(prezzo);
            p.setGiacenza(giacenza);
            p.setCategoriaId(categoriaId);
            p.setImmagineUrl(immagineUrl);

            if ("/nuovo".equals(pathInfo)) {
                prodottoDAO.doSave(p);
            } else if ("/modifica".equals(pathInfo)) {
                int id = Integer.parseInt(request.getParameter("id"));
                p.setId(id);
                prodottoDAO.doUpdate(p);
            }

            response.sendRedirect(request.getContextPath() + "/admin/dashboard");

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}