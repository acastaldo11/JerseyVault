package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Indirizzo;

public class IndirizzoDAO {

    public synchronized void doSave(Indirizzo indirizzo) throws SQLException {
        String query = "INSERT INTO indirizzo (id_cliente, via, citta, cap) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, indirizzo.getIdCliente());
            ps.setString(2, indirizzo.getVia());
            ps.setString(3, indirizzo.getCitta());
            ps.setString(4, indirizzo.getCap());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    indirizzo.setId(rs.getInt(1));
                }
            }
        }
    }

    public synchronized List<Indirizzo> doRetrieveByCliente(int idCliente) throws SQLException {
        String query = "SELECT * FROM indirizzo WHERE id_cliente = ?";
        List<Indirizzo> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Indirizzo i = new Indirizzo();
                    i.setId(rs.getInt("id"));
                    i.setIdCliente(rs.getInt("id_cliente"));
                    i.setVia(rs.getString("via"));
                    i.setCitta(rs.getString("citta"));
                    i.setCap(rs.getString("cap"));
                    list.add(i);
                }
            }
        }
        return list;
    }
}