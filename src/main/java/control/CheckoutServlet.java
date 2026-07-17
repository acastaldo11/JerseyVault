package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dao.IndirizzoDAO;
import dao.IndirizzoDAOImpl;
import dao.MetodoPagamentoDAO;
import dao.MetodoPagamentoDAOImpl;
import dao.OrdineDAO;
import dao.OrdineDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Carrello;
import model.CarrelloItem;
import model.Cliente;
import model.DettaglioOrdine;
import model.Indirizzo;
import model.MetodoPagamento;
import model.Ordine;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private IndirizzoDAO indirizzoDAO;
    private MetodoPagamentoDAO mpDAO;
    private OrdineDAO ordineDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.indirizzoDAO = new IndirizzoDAOImpl();
        this.mpDAO = new MetodoPagamentoDAOImpl();
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
        request.getRequestDispatcher("/WEB-INF/view/checkout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        
        if (session == null || session.getAttribute("token") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Cliente cliente = (Cliente) session.getAttribute("utenteLoggato");
        Carrello carrello = (Carrello) session.getAttribute("carrello");

        if (cliente == null || carrello == null || carrello.getItems().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/catalogo");
            return;
        }

        String via = request.getParameter("via");
        String citta = request.getParameter("citta");
        String cap = request.getParameter("cap");
        String tipoMetodo = request.getParameter("tipoMetodo");
        String ultimeCifre = request.getParameter("ultimeCifre");

        if (via == null || via.trim().isEmpty() ||
            citta == null || citta.trim().isEmpty() ||
            cap == null || cap.trim().isEmpty() ||
            tipoMetodo == null || tipoMetodo.trim().isEmpty()) {
            request.setAttribute("error", "Tutti i campi sono obbligatori.");
            request.getRequestDispatcher("/WEB-INF/view/checkout.jsp").forward(request, response);
            return;
        }

        try {
            Indirizzo indirizzo = new Indirizzo();
            indirizzo.setIdCliente(cliente.getId());
            indirizzo.setVia(via.trim());
            indirizzo.setCitta(citta.trim());
            indirizzo.setCap(cap.trim());
            indirizzoDAO.doSave(indirizzo);

            MetodoPagamento mp = new MetodoPagamento();
            mp.setIdCliente(cliente.getId());
            mp.setTipo(tipoMetodo.trim());
            mp.setUltimeCifre(ultimeCifre != null ? ultimeCifre.trim() : "");
            mpDAO.doSave(mp);

            List<DettaglioOrdine> dettagli = new ArrayList<>();
            for (CarrelloItem item : carrello.getItems()) {
                DettaglioOrdine d = new DettaglioOrdine();
                d.setIdProdotto(item.getProdotto().getId());
                d.setQuantita(item.getQuantita());
                d.setPrezzoAcquistoStorico(item.getProdotto().getPrezzo());
                dettagli.add(d);
            }

            Ordine ordine = new Ordine();
            ordine.setIdCliente(cliente.getId());
            ordine.setIdIndirizzoSpedizione(indirizzo.getId());
            ordine.setIdMetodoPagamento(mp.getId());
            ordine.setTotale(carrello.getTotale());
            ordine.setStato("In lavorazione");

            ordineDAO.doSave(ordine, dettagli);

            carrello.svuota();

            response.sendRedirect(request.getContextPath() + "/conferma");

        } catch (SQLException e) {
            request.setAttribute("error", "Errore durante il checkout. Riprovare.");
            request.getRequestDispatcher("/WEB-INF/view/checkout.jsp").forward(request, response);
        }
    }
}