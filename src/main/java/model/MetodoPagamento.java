package model;

public class MetodoPagamento implements Cloneable {

    private int id;
    private int idCliente;
    private String tipo;
    private String ultimeCifre;

    public MetodoPagamento() {}

    public MetodoPagamento(int id, int idCliente, String tipo, String ultimeCifre) {
        this.id = id;
        this.idCliente = idCliente;
        this.tipo = tipo;
        this.ultimeCifre = ultimeCifre;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getUltimeCifre() { return ultimeCifre; }
    public void setUltimeCifre(String ultimeCifre) { this.ultimeCifre = ultimeCifre; }

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) return true;
        if (anObject == null || getClass() != anObject.getClass()) return false;
        MetodoPagamento metodo = (MetodoPagamento) anObject;
        return this.id == metodo.id;
    }

    @Override
    public int hashCode() {
        return 31 + id;
    }

    @Override
    public MetodoPagamento clone() {
        try {
            return (MetodoPagamento) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}