package model;

public class CarrelloItem implements Cloneable {

    private Prodotto prodotto;
    private int quantita;

    public CarrelloItem() {}

    public CarrelloItem(Prodotto prodotto, int quantita) {
        this.prodotto = prodotto;
        this.quantita = quantita;
    }

    public Prodotto getProdotto() { return prodotto; }
    public void setProdotto(Prodotto prodotto) { this.prodotto = prodotto; }

    public int getQuantita() { return quantita; }
    public void setQuantita(int quantita) { this.quantita = quantita; }
    
    public double getPrezzoTotale() {
        if (prodotto != null) {
            return prodotto.getPrezzo() * quantita;
        }
        return 0.0;
    }

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) return true;
        if (anObject == null || getClass() != anObject.getClass()) return false;
        CarrelloItem item = (CarrelloItem) anObject;
        if (this.prodotto == null) return item.prodotto == null;
        return this.prodotto.equals(item.prodotto);
    }

    @Override
    public int hashCode() {
        return prodotto != null ? prodotto.hashCode() : 0;
    }

    @Override
    public CarrelloItem clone() {
        try {
            CarrelloItem cloned = (CarrelloItem) super.clone();
            if (this.prodotto != null) {
                cloned.prodotto = this.prodotto.clone();
            }
            return cloned;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}