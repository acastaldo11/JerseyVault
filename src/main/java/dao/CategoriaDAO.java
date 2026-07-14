package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Categoria;

public class CategoriaDAO {

    private static final String TABLE_NAME = "categoria";

    public synchronized void doSave(Categoria categoria) throws SQLException {
        String query = "INSERT INTO " + TABLE_NAME + " (nome_categoria, descrizione) VALUES (?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, categoria.getNome());
            ps.setString(2, categoria.getDescrizione());
            ps.executeUpdate();
        }
    }

    public synchronized Categoria doRetrieveById(int id) throws SQLException {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
        Categoria bean = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    bean = mapRowToCategoria(rs);
                }
            }
        }
        return bean;
    }

    public synchronized List<Categoria> doRetrieveAll() throws SQLException {
        String query = "SELECT * FROM " + TABLE_NAME;
        List<Categoria> categorie = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                categorie.add(mapRowToCategoria(rs));
            }
        }
        return categorie;
    }

    private Categoria mapRowToCategoria(ResultSet rs) throws SQLException {
        Categoria c = new Categoria();
        c.setId(rs.getInt("id"));
        c.setNome(rs.getString("nome_categoria"));
        c.setDescrizione(rs.getString("descrizione"));
        return c;
    }
}