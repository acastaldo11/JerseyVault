package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Prodotto; 

public class ProdottoDAO {

    private static final String TABLE_NAME = "prodotto";

    public synchronized void doSave(Prodotto product) throws SQLException {
        String query = "INSERT INTO " + TABLE_NAME + " (nome_maglia, descrizione, squadra, taglia, prezzo_attuale, giacenza, cancellato, immagine_url, id_categoria) VALUES (?, ?, ?, ?, ?, ?, FALSE, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, product.getNome());
            ps.setString(2, product.getDescrizione());
            ps.setString(3, product.getSquadra());
            ps.setString(4, product.getTaglia());
            ps.setDouble(5, product.getPrezzo());
            ps.setInt(6, product.getGiacenza());
            ps.setString(7, product.getImmagineUrl());
            ps.setInt(8, product.getCategoriaId());
            ps.executeUpdate();
        }
    }

    public synchronized Prodotto doRetrieveById(int id) throws SQLException {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE id = ? AND cancellato = FALSE";
        Prodotto bean = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    bean = mapRowToProduct(rs);
                }
            }
        }
        return bean;
    }

    public synchronized List<Prodotto> doRetrieveAll() throws SQLException {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE cancellato = FALSE";
        List<Prodotto> products = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                products.add(mapRowToProduct(rs));
            }
        }
        return products;
    }

    public synchronized void doUpdate(Prodotto product) throws SQLException {
        String query = "UPDATE " + TABLE_NAME + " SET nome_maglia = ?, descrizione = ?, squadra = ?, taglia = ?, prezzo_attuale = ?, giacenza = ?, immagine_url = ?, id_categoria = ? WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, product.getNome());
            ps.setString(2, product.getDescrizione());
            ps.setString(3, product.getSquadra());
            ps.setString(4, product.getTaglia());
            ps.setDouble(5, product.getPrezzo());
            ps.setInt(6, product.getGiacenza());
            ps.setString(7, product.getImmagineUrl());
            ps.setInt(8, product.getCategoriaId());
            ps.setInt(9, product.getId());
            ps.executeUpdate();
        }
    }

    public synchronized void doDelete(int id) throws SQLException {
        String query = "UPDATE " + TABLE_NAME + " SET cancellato = TRUE WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Prodotto mapRowToProduct(ResultSet rs) throws SQLException {
        Prodotto p = new Prodotto();
        p.setId(rs.getInt("id"));
        p.setNome(rs.getString("nome_maglia"));
        p.setDescrizione(rs.getString("descrizione"));
        p.setSquadra(rs.getString("squadra"));
        p.setTaglia(rs.getString("taglia"));
        p.setPrezzo(rs.getDouble("prezzo_attuale"));
        p.setGiacenza(rs.getInt("giacenza"));
        p.setCancellato(rs.getBoolean("cancellato"));
        p.setImmagineUrl(rs.getString("immagine_url"));
        p.setCategoriaId(rs.getInt("id_categoria"));
        return p;
    }
}