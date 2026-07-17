package dao;

import java.sql.SQLException;
import model.Amministratore;

public interface AmministratoreDAO {
    Amministratore doRetrieveByEmail(String email) throws SQLException;
    boolean isAdmin(int idUtente) throws SQLException;
    void doSave(int idUtente) throws SQLException;
}