package model;

public class Utente implements Cloneable {

    private int id;
    private String nome;
    private String cognome;
    private String email;
    private String passwordHash;

    public Utente() {}

    public Utente(int id, String nome, String cognome, 
                  String email, String passwordHash) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    // Getter e Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { 
        this.passwordHash = passwordHash; 
    }

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) return true;
        if (anObject == null || getClass() != anObject.getClass()) return false;
        Utente utente = (Utente) anObject;
        if (email == null) return utente.email == null;
        return email.equals(utente.email);
    }

    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }

    @Override
    public Utente clone() {
        try {
            return (Utente) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}