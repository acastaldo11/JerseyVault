package dao;

import java.sql.SQLException;
import java.util.List;
import model.Categoria;

public interface CategoriaDAO {
    void doSave(Categoria categoria) throws SQLException;
    Categoria doRetrieveById(int id) throws SQLException;
    List<Categoria> doRetrieveAll() throws SQLException;
}