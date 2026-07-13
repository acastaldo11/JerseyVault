package model;

public class Ordine implements Cloneable {

    private int id;
    private int idCliente;
    private int idIndirizzoSpedizione;
    private int idMetodoPagamento;
    private String data;
    private double totale;
    private String stato;

    public Ordine() {}

    public Ordine(int id, int idCliente, int idIndirizzoSpedizione,
                  int idMetodoPagamento, String data, double totale, String stato) {
        this.id = id;
        this.idCliente = idCliente;
        this.idIndirizzoSpedizione = idIndirizzoSpedizione;
        this.idMetodoPagamento = idMetodoPagamento;
        this.data = data;
        this.totale = totale;
        this.stato = stato;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public int getIdIndirizzoSpedizione() { return idIndirizzoSpedizione; }
    public void setIdIndirizzoSpedizione(int idIndirizzoSpedizione) { 
        this.idIndirizzoSpedizione = idIndirizzoSpedizione; 
    }

    public int getIdMetodoPagamento() { return idMetodoPagamento; }
    public void setIdMetodoPagamento(int idMetodoPagamento) { 
        this.idMetodoPagamento = idMetodoPagamento; 
    }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public double getTotale() { return totale; }
    public void setTotale(double totale) { this.totale = totale; }

    public String getStato() { return stato; }
    public void setStato(String stato) { this.stato = stato; }

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) return true;
        if (anObject == null || getClass() != anObject.getClass()) return false;
        Ordine ordine = (Ordine) anObject;
        return this.id == ordine.id;
    }

    @Override
    public int hashCode() {
        return 31 + id;
    }

    @Override
    public Ordine clone() {
        try {
            return (Ordine) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}