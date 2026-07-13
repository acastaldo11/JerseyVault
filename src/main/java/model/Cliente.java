package model;

public class Cliente extends Utente {
    
    private String via;
    private String citta;
    private String cap;
    private String telefono;

    public Cliente() {
        super();
    }

    public Cliente(int id, String nome, String cognome, String email, String passwordHash, String via, String citta, String cap, String telefono) {
        super(id, nome, cognome, email, passwordHash);
        this.via = via;
        this.citta = citta;
        this.cap = cap;
        this.telefono = telefono;
    }

    public String getVia() { return via; }
    public void setVia(String via) { this.via = via; }

    public String getCitta() { return citta; }
    public void setCitta(String citta) { this.citta = citta; }

    public String getCap() { return cap; }
    public void setCap(String cap) { this.cap = cap; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    @Override
    public boolean equals(Object anObject) {
        return super.equals(anObject);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public Cliente clone() {
        return (Cliente) super.clone();
    }
}