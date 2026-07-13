package model;

public class Prodotto implements Cloneable {

    private int id;
    private String nome;
    private String descrizione;
    private double prezzo;
    private String squadra;
    private String taglia;
    private int giacenza;
    private boolean cancellato;
    private int categoriaId;
    private String immagineUrl;

    public Prodotto() {}

    public Prodotto(int id, String nome, String descrizione, double prezzo,
                    String squadra, String taglia, int giacenza,
                    boolean cancellato, int categoriaId, String immagineUrl) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.squadra = squadra;
        this.taglia = taglia;
        this.giacenza = giacenza;
        this.cancellato = cancellato;
        this.categoriaId = categoriaId;
        this.immagineUrl = immagineUrl;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescrizione() { return descrizione; }
    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }

    public double getPrezzo() { return prezzo; }
    public void setPrezzo(double prezzo) { this.prezzo = prezzo; }

    public String getSquadra() { return squadra; }
    public void setSquadra(String squadra) { this.squadra = squadra; }

    public String getTaglia() { return taglia; }
    public void setTaglia(String taglia) { this.taglia = taglia; }

    public int getGiacenza() { return giacenza; }
    public void setGiacenza(int giacenza) { this.giacenza = giacenza; }

    public boolean isCancellato() { return cancellato; }
    public void setCancellato(boolean cancellato) { this.cancellato = cancellato; }

    public int getCategoriaId() { return categoriaId; }
    public void setCategoriaId(int categoriaId) { this.categoriaId = categoriaId; }

    public String getImmagineUrl() { return immagineUrl; }
    public void setImmagineUrl(String immagineUrl) { this.immagineUrl = immagineUrl; }

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) return true;
        if (anObject == null || getClass() != anObject.getClass()) return false;
        Prodotto prodotto = (Prodotto) anObject;
        return this.id == prodotto.id;
    }

    @Override
    public int hashCode() {
        return 31 + id;
    }

    @Override
    public Prodotto clone() {
        try {
            return (Prodotto) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}