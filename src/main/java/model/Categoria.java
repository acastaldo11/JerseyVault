package model;

public class Categoria implements Cloneable {

    private int id;
    private String nome;
    private String descrizione;

    public Categoria() {}

    public Categoria(int id, String nome, String descrizione) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescrizione() { return descrizione; }
    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) return true;
        if (anObject == null || getClass() != anObject.getClass()) return false;
        Categoria categoria = (Categoria) anObject;
        return this.id == categoria.id;
    }

    @Override
    public int hashCode() {
        return 31 + id;
    }

    @Override
    public Categoria clone() {
        try {
            return (Categoria) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}