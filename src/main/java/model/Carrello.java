package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Carrello {
    private List<CarrelloItem> items;

    public Carrello() {
        this.items = new ArrayList<>();
    }

    public List<CarrelloItem> getItems() {
        return items;
    }

    public void aggiungi(Prodotto p, int quantita) {
        for (CarrelloItem item : items) {
            if (item.getProdotto().getId() == p.getId()) {
                item.setQuantita(item.getQuantita() + quantita);
                return;
            }
        }
        items.add(new CarrelloItem(p, quantita));
    }

    public void rimuovi(int idProdotto) {
        Iterator<CarrelloItem> it = items.iterator();
        while (it.hasNext()) {
            if (it.next().getProdotto().getId() == idProdotto) {
                it.remove();
            }
        }
    }

    public void aggiorna(int idProdotto, int nuovaQuantita) {
        for (CarrelloItem item : items) {
            if (item.getProdotto().getId() == idProdotto) {
                item.setQuantita(nuovaQuantita);
                return;
            }
        }
    }

    public void svuota() {
        items.clear();
    }

    public double getTotale() {
        double totale = 0;
        for (CarrelloItem item : items) {
            totale += item.getPrezzoTotale();
        }
        return totale;
    }
}