package dao;

import java.sql.SQLException;
import java.util.List;
import model.MetodoPagamento;

public interface MetodoPagamentoDAO {
    void doSave(MetodoPagamento mp) throws SQLException;
    List<MetodoPagamento> doRetrieveByCliente(int idCliente) throws SQLException;
}