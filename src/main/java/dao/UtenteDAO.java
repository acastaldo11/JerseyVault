package dao;

import java.sql.SQLException;
import java.util.List;
import model.Cliente;

public interface UtenteDAO {
    void doSave(Cliente cliente) throws SQLException;
    Cliente doRetrieveByEmail(String email) throws SQLException;
    Cliente doRetrieveById(int id) throws SQLException;
    List<Cliente> doRetrieveAll() throws SQLException;
}