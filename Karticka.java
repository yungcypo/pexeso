import fri.shapesge.Obrazok;
import fri.shapesge.Stvorec;
/**
 * Write a description of class Karticka here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Karticka {
    private Obrazok obrazok;
    private Stvorec stvorec;
    private int x;
    private int y;
    private Stav stav;
    private String cestaKObrazku;
    /**
     * Constructor for objects of class Karticka
     */
    public Karticka(int x, int y, String cesta) {
        this.x = x;
        this.y = y;
        this.cestaKObrazku = cesta;

        this.obrazok = new Obrazok(this.cestaKObrazku);
        this.obrazok.zmenPolohu(this.x, this.y);

        this.stvorec = new Stvorec();
        this.stvorec.zmenFarbu("#00FF00");
        this.stvorec.zmenStranu(64);
        this.stvorec.zmenPolohu(this.x, this.y);

        this.aktualizuj(Stav.ZAKRYTE);
    }

    public void aktualizuj(Stav stav) {
        switch (stav) {
            case ZAKRYTE:
                this.obrazok.skry();
                this.stvorec.zobraz();
                break;
            case ODOKRYTE:
                this.obrazok.zobraz();
                this.stvorec.zobraz();
                this.dajNavrch();
                break;
            case UHADNUTE:
                this.obrazok.zobraz();
                this.stvorec.skry();
                this.dajNavrch();
                break;
        
            default:
                break;
        }
    }

    public void dajNavrch() {
        this.obrazok.skry();
        this.obrazok.zobraz();
    }

    public Stav getStav() {
        return this.stav;
    }

    public String getObrazok() {
        return this.cestaKObrazku;
    }
}
