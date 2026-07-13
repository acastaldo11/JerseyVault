package model;

public class DettaglioOrdine implements Cloneable {

    private int id;
    private int idOrdine;
    private int idProdotto;
    private int quantita;
    private double prezzoAcquistoStorico;

    public DettaglioOrdine() {}

    public DettaglioOrdine(int id, int idOrdine, int idProdotto, int quantita, double prezzoAcquistoStorico) {
        this.id = id;
        this.idOrdine = idOrdine;
        this.idProdotto = idProdotto;
        this.quantita = quantita;
        this.prezzoAcquistoStorico = prezzoAcquistoStorico;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdOrdine() { return idOrdine; }
    public void setIdOrdine(int idOrdine) { this.idOrdine = idOrdine; }

    public int getIdProdotto() { return idProdotto; }
    public void setIdProdotto(int idProdotto) { this.idProdotto = idProdotto; }

    public int getQuantita() { return quantita; }
    public void setQuantita(int quantita) { this.quantita = quantita; }

    public double getPrezzoAcquistoStorico() { return prezzoAcquistoStorico; }
    public void setPrezzoAcquistoStorico(double prezzoAcquistoStorico) { this.prezzoAcquistoStorico = prezzoAcquistoStorico; }

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) return true;
        if (anObject == null || getClass() != anObject.getClass()) return false;
        DettaglioOrdine dettaglio = (DettaglioOrdine) anObject;
        return this.id == dettaglio.id;
    }

    @Override
    public int hashCode() {
        return 31 + id;
    }

    @Override
    public DettaglioOrdine clone() {
        try {
            return (DettaglioOrdine) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}