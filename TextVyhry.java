import fri.shapesge.BlokTextu;
import fri.shapesge.Obdlznik;
import fri.shapesge.StylFontu;
/**
 * Write a description of class TextVyhry here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TextVyhry {
    private BlokTextu text;
    private Obdlznik obdlznik;
    /**
     * Constructor for objects of class TextVyhry
     */
    public TextVyhry() {
        // suradnice su spravene tak, aby bol text zarovnany cca na stred 
        this.text = new BlokTextu("Vyhrali ste!", 54, 138);
        this.text.zmenFont("Arial", StylFontu.PLAIN, 32);
        this.text.zmenFarbu("#ffffff");

        this.obdlznik = new Obdlznik();
        this.obdlznik.zmenPolohu(34, 98);
        this.obdlznik.zmenStrany(196, 60);
        this.obdlznik.zmenFarbu("#000000");
    }

    public void zobraz() {
        this.obdlznik.zobraz();
        this.text.zobraz();
    }

    public void skry() {
        this.obdlznik.skry();
        this.text.skry();
    }

    public void dajNavrch() {
        this.skry();
        this.zobraz();
    }
}
