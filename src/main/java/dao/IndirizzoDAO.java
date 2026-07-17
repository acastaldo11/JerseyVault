package dao;

import java.sql.SQLException;
import java.util.List;
import model.Indirizzo;

public interface IndirizzoDAO {
    void doSave(Indirizzo indirizzo) throws SQLException;
    List<Indirizzo> doRetrieveByCliente(int idCliente) throws SQLException;
}