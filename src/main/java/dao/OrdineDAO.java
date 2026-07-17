package dao;

import java.sql.SQLException;
import java.util.List;
import model.DettaglioOrdine;
import model.Ordine;

public interface OrdineDAO {
    int doSave(Ordine ordine, List<DettaglioOrdine> dettagli) throws SQLException;
    List<Ordine> doRetrieveByCliente(int idCliente) throws SQLException;
    List<Ordine> doRetrieveAll() throws SQLException;
    List<Ordine> doRetrieveByFiltri(String idCliente, String dataInizio, String dataFine) throws SQLException;
    List<DettaglioOrdine> doRetrieveDettagli(int idOrdine) throws SQLException;
}