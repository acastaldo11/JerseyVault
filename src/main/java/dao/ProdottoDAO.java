package dao;

import java.sql.SQLException;
import java.util.List;
import model.Prodotto;

public interface ProdottoDAO {
    void doSave(Prodotto product) throws SQLException;
    Prodotto doRetrieveById(int id) throws SQLException;
    List<Prodotto> doRetrieveAll() throws SQLException;
    List<Prodotto> doRetrieveByCategoria(int idCategoria) throws SQLException;
    void doUpdate(Prodotto product) throws SQLException;
    void doDelete(int id) throws SQLException;
}