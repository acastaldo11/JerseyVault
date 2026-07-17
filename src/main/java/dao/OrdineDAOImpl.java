package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.DettaglioOrdine;
import model.Ordine;

public class OrdineDAOImpl implements OrdineDAO {

    @Override
    public synchronized int doSave(Ordine ordine, List<DettaglioOrdine> dettagli) throws SQLException {
        String queryOrdine = "INSERT INTO ordine (id_cliente, id_indirizzo, id_metodo_pagamento, data_acquisto, totale_fattura, stato) VALUES (?, ?, ?, CURDATE(), ?, ?)";
        String queryDettaglio = "INSERT INTO dettaglio_ordine (id_ordine, id_prodotto, quantita, prezzo_acquisto_storico) VALUES (?, ?, ?, ?)";

        Connection con = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            int idOrdine;
            try (PreparedStatement ps = con.prepareStatement(queryOrdine, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, ordine.getIdCliente());
                ps.setInt(2, ordine.getIdIndirizzoSpedizione());
                ps.setInt(3, ordine.getIdMetodoPagamento());
                ps.setDouble(4, ordine.getTotale());
                ps.setString(5, ordine.getStato());
                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        idOrdine = rs.getInt(1);
                    } else {
                        throw new SQLException("Errore: nessun ID generato per ordine.");
                    }
                }
            }

            if (dettagli != null && !dettagli.isEmpty()) {
                try (PreparedStatement psDet = con.prepareStatement(queryDettaglio)) {
                    for (DettaglioOrdine d : dettagli) {
                        psDet.setInt(1, idOrdine);
                        psDet.setInt(2, d.getIdProdotto());
                        psDet.setInt(3, d.getQuantita());
                        psDet.setDouble(4, d.getPrezzoAcquistoStorico());
                        psDet.addBatch();
                    }
                    psDet.executeBatch();
                }
            }

            con.commit();
            return idOrdine;

        } catch (SQLException e) {
            if (con != null) con.rollback();
            throw e;
        } finally {
            if (con != null) {
                con.setAutoCommit(true);
                con.close();
            }
        }
    }

    @Override
    public synchronized List<Ordine> doRetrieveByCliente(int idCliente) throws SQLException {
        String query = "SELECT * FROM ordine WHERE id_cliente = ? ORDER BY data_acquisto DESC";
        List<Ordine> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRowToOrdine(rs));
                }
            }
        }
        return list;
    }

    @Override
    public synchronized List<Ordine> doRetrieveAll() throws SQLException {
        String query = "SELECT * FROM ordine ORDER BY data_acquisto DESC";
        List<Ordine> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapRowToOrdine(rs));
            }
        }
        return list;
    }

    @Override
    public synchronized List<Ordine> doRetrieveByFiltri(String idCliente, String dataInizio, String dataFine) throws SQLException {
        StringBuilder query = new StringBuilder("SELECT * FROM ordine WHERE 1=1");

        if (idCliente != null && !idCliente.trim().isEmpty()) {
            query.append(" AND id_cliente = ?");
        }
        if (dataInizio != null && !dataInizio.trim().isEmpty()) {
            query.append(" AND data_acquisto >= ?");
        }
        if (dataFine != null && !dataFine.trim().isEmpty()) {
            query.append(" AND data_acquisto <= ?");
        }
        query.append(" ORDER BY data_acquisto DESC");

        List<Ordine> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query.toString())) {

            int index = 1;
            if (idCliente != null && !idCliente.trim().isEmpty()) {
                ps.setInt(index++, Integer.parseInt(idCliente.trim()));
            }
            if (dataInizio != null && !dataInizio.trim().isEmpty()) {
                ps.setString(index++, dataInizio.trim());
            }
            if (dataFine != null && !dataFine.trim().isEmpty()) {
                ps.setString(index++, dataFine.trim());
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRowToOrdine(rs));
                }
            }
        }
        return list;
    }

    @Override
    public synchronized List<DettaglioOrdine> doRetrieveDettagli(int idOrdine) throws SQLException {
        String query = "SELECT * FROM dettaglio_ordine WHERE id_ordine = ?";
        List<DettaglioOrdine> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, idOrdine);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DettaglioOrdine d = new DettaglioOrdine();
                    d.setId(rs.getInt("id"));
                    d.setIdOrdine(rs.getInt("id_ordine"));
                    d.setIdProdotto(rs.getInt("id_prodotto"));
                    d.setQuantita(rs.getInt("quantita"));
                    d.setPrezzoAcquistoStorico(rs.getDouble("prezzo_acquisto_storico"));
                    list.add(d);
                }
            }
        }
        return list;
    }

    private Ordine mapRowToOrdine(ResultSet rs) throws SQLException {
        Ordine o = new Ordine();
        o.setId(rs.getInt("id"));
        o.setIdCliente(rs.getInt("id_cliente"));
        o.setIdIndirizzoSpedizione(rs.getInt("id_indirizzo"));
        o.setIdMetodoPagamento(rs.getInt("id_metodo_pagamento"));
        o.setData(rs.getString("data_acquisto"));
        o.setTotale(rs.getDouble("totale_fattura"));
        o.setStato(rs.getString("stato"));
        return o;
    }
}