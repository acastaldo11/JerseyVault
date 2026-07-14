package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;

public class UtenteDAO {

    public synchronized void doSave(Cliente cliente) throws SQLException {
        String queryUtente = "INSERT INTO utente (nome, cognome, email, password_hash) VALUES (?, ?, ?, ?)";
        String queryCliente = "INSERT INTO cliente (id, telefono) VALUES (?, ?)";

        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            connection.setAutoCommit(false);

            int idGenerato;
            try (PreparedStatement ps = connection.prepareStatement(
                    queryUtente, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, cliente.getNome());
                ps.setString(2, cliente.getCognome());
                ps.setString(3, cliente.getEmail());
                ps.setString(4, cliente.getPasswordHash());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        idGenerato = rs.getInt(1);
                    } else {
                        throw new SQLException("Errore: nessun ID generato per utente.");
                    }
                }
            }

            try (PreparedStatement ps = connection.prepareStatement(queryCliente)) {
                ps.setInt(1, idGenerato);
                ps.setString(2, cliente.getTelefono());
                ps.executeUpdate();
            }

            connection.commit();
            cliente.setId(idGenerato);

        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }
    }

    public synchronized Cliente doRetrieveByEmail(String email) throws SQLException {
        String query = "SELECT u.id, u.nome, u.cognome, u.email, u.password_hash, c.telefono "
                     + "FROM utente u JOIN cliente c ON u.id = c.id "
                     + "WHERE u.email = ?";
        Cliente cliente = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cliente = mapRowToCliente(rs);
                }
            }
        }
        return cliente;
    }

    public synchronized Cliente doRetrieveById(int id) throws SQLException {
        String query = "SELECT u.id, u.nome, u.cognome, u.email, u.password_hash, c.telefono "
                     + "FROM utente u JOIN cliente c ON u.id = c.id "
                     + "WHERE u.id = ?";
        Cliente cliente = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cliente = mapRowToCliente(rs);
                }
            }
        }
        return cliente;
    }

    public synchronized List<Cliente> doRetrieveAll() throws SQLException {
        String query = "SELECT u.id, u.nome, u.cognome, u.email, u.password_hash, c.telefono "
                     + "FROM utente u JOIN cliente c ON u.id = c.id";
        List<Cliente> clienti = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                clienti.add(mapRowToCliente(rs));
            }
        }
        return clienti;
    }

    private Cliente mapRowToCliente(ResultSet rs) throws SQLException {
        Cliente c = new Cliente();
        c.setId(rs.getInt("id"));
        c.setNome(rs.getString("nome"));
        c.setCognome(rs.getString("cognome"));
        c.setEmail(rs.getString("email"));
        c.setPasswordHash(rs.getString("password_hash"));
        c.setTelefono(rs.getString("telefono"));
        return c;
    }
}