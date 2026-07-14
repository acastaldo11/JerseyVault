package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Amministratore;

public class AmministratoreDAO {

    public synchronized Amministratore doRetrieveByEmail(String email) throws SQLException {
        String query = "SELECT u.id, u.nome, u.cognome, u.email, u.password_hash "
                     + "FROM utente u JOIN amministratore a ON u.id = a.id "
                     + "WHERE u.email = ?";
        Amministratore admin = null;
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    admin = new Amministratore();
                    admin.setId(rs.getInt("id"));
                    admin.setNome(rs.getString("nome"));
                    admin.setCognome(rs.getString("cognome"));
                    admin.setEmail(rs.getString("email"));
                    admin.setPasswordHash(rs.getString("password_hash"));
                }
            }
        }
        return admin;
    }

    public synchronized boolean isAdmin(int idUtente) throws SQLException {
        String query = "SELECT id FROM amministratore WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, idUtente);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public synchronized void doSave(int idUtente) throws SQLException {
        String query = "INSERT INTO amministratore (id) VALUES (?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, idUtente);
            ps.executeUpdate();
        }
    }
}