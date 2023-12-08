import fri.shapesge.Manazer;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 * Write a description of class Hra here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Hra {
    private Manazer manazer;
    private Random random;
    private Karticka[][] poleKarticiek;
    private ArrayList<String> zoznamDostupnychKarticiek;
    private ArrayList<Karticka> odokryteKarticky;
    private ArrayList<Karticka> uhadnuteKarticky;
    private int rozmerObrazku;
    private boolean hraSkoncila;

    /**
     * Constructor for objects of class Hra
     */
    public Hra() {
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
        this.random = new Random();
        this.poleKarticiek = new Karticka[4][4];
        this.zoznamDostupnychKarticiek = new ArrayList<String>();
        this.odokryteKarticky = new ArrayList<Karticka>();
        this.uhadnuteKarticky = new ArrayList<Karticka>();
        this.rozmerObrazku = 64;
        this.hraSkoncila = false;

        // prida kazdy obrazok (cestu k obrazku) do zoznamu dostupnych obrazkov 2 krat - vytvorenie parov
        for (int i = 1; i <= 8; i++) {
            this.zoznamDostupnychKarticiek.add("pics/" + i + ".png");
            this.zoznamDostupnychKarticiek.add("pics/" + i + ".png");
        }

        // prechadza pole karticiek a do kazdej bunky da nahodny obrazok z dostupnych. obrazok je potom odstraneny z dostupnych
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int nahodnyObrazok = this.random.nextInt(this.zoznamDostupnychKarticiek.size());
                this.poleKarticiek[i][j] = new Karticka(i * (this.rozmerObrazku + 1), j * (this.rozmerObrazku + 1),
                        this.zoznamDostupnychKarticiek.get(nahodnyObrazok));
                this.zoznamDostupnychKarticiek.remove(nahodnyObrazok);
            }
        }
    }

    public void vyberSuradnice(int x, int y) {
        // ak sa hra neskoncila a hrac klikol do jednej z karticiek
        if (!this.hraSkoncila && x / 64 < this.poleKarticiek.length && y / 64 < this.poleKarticiek.length) {
            // karticka, do ktorej klikol hrac
            Karticka hladanaKarticka = this.poleKarticiek[x / 64][y / 64];
            // ak este karticka nie je uhadnuta
            if (!this.uhadnuteKarticky.contains(hladanaKarticka)) {
                // odokry karticku a pridaj do zoznamu odokrytych
                hladanaKarticka.aktualizuj(Stav.ODOKRYTE);
                this.odokryteKarticky.add(hladanaKarticka);
                // ak su 2 karticky odokryte
                if (this.odokryteKarticky.size() == 2) {
                    // ak su 2 odokryte karticky rovnake zmen stav na oboch uhadnute, pridaj do zoznamu uhadnutych a vymaz obsah zoznamu odokrytych
                    if (this.odokryteKarticky.get(0).getObrazok().equals(this.odokryteKarticky.get(1).getObrazok())) {
                        for (Karticka k : this.odokryteKarticky) {
                            k.aktualizuj(Stav.UHADNUTE);
                            this.uhadnuteKarticky.add(k);
                        }
                        this.odokryteKarticky.clear();
                    // ak nie su rovnake, odokry karticku
                    } else {
                        for (Karticka k : this.odokryteKarticky) {
                            k.aktualizuj(Stav.ODOKRYTE);
                        }
                    }
                }

                // ak su 2 odokryte a hrac klikne este raz - zakry vsetky karticky a vymaz zoznam odokrytych
                if (this.odokryteKarticky.size() == 3) {
                    for (Karticka k : this.odokryteKarticky) {
                        k.aktualizuj(Stav.ZAKRYTE);
                    }
                    this.odokryteKarticky.clear();
                }
            }
            // ak je uhadnutych 16 karticiek - vsetky - skonci hru
            if (this.uhadnuteKarticky.size() == 16) {
                this.hraSkoncila = true;
                JOptionPane.showMessageDialog(null, "Vyhrali ste!");
            }
        }
    }
}
