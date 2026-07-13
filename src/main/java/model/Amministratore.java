package model;

public class Amministratore extends Utente {

    public Amministratore() {
        super();
    }

    public Amministratore(int id, String nome, String cognome, String email, String passwordHash) {
        super(id, nome, cognome, email, passwordHash);
    }

    @Override
    public boolean equals(Object anObject) {
        return super.equals(anObject);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public Amministratore clone() {
        return (Amministratore) super.clone();
    }
}