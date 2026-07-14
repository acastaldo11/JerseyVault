package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.MetodoPagamento;

public class MetodoPagamentoDAO {

    public synchronized void doSave(MetodoPagamento mp) throws SQLException {
        String query = "INSERT INTO metodo_pagamento (id_cliente, tipo, ultime_cifre) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, mp.getIdCliente());
            ps.setString(2, mp.getTipo());
            ps.setString(3, mp.getUltimeCifre());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    mp.setId(rs.getInt(1));
                }
            }
        }
    }

    public synchronized List<MetodoPagamento> doRetrieveByCliente(int idCliente) throws SQLException {
        String query = "SELECT * FROM metodo_pagamento WHERE id_cliente = ?";
        List<MetodoPagamento> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    MetodoPagamento m = new MetodoPagamento();
                    m.setId(rs.getInt("id"));
                    m.setIdCliente(rs.getInt("id_cliente"));
                    m.setTipo(rs.getString("tipo"));
                    m.setUltimeCifre(rs.getString("ultime_cifre"));
                    list.add(m);
                }
            }
        }
        return list;
    }
}