package model;

public class Indirizzo implements Cloneable {

    private int id;
    private int idCliente;
    private String via;
    private String citta;
    private String cap;

    public Indirizzo() {}

    public Indirizzo(int id, int idCliente, String via, String citta, String cap) {
        this.id = id;
        this.idCliente = idCliente;
        this.via = via;
        this.citta = citta;
        this.cap = cap;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public String getVia() { return via; }
    public void setVia(String via) { this.via = via; }

    public String getCitta() { return citta; }
    public void setCitta(String citta) { this.citta = citta; }

    public String getCap() { return cap; }
    public void setCap(String cap) { this.cap = cap; }

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) return true;
        if (anObject == null || getClass() != anObject.getClass()) return false;
        Indirizzo indirizzo = (Indirizzo) anObject;
        return this.id == indirizzo.id;
    }

    @Override
    public int hashCode() {
        return 31 + id;
    }

    @Override
    public Indirizzo clone() {
        try {
            return (Indirizzo) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
