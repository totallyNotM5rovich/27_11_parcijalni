public class Vozilo {
    private String regOznaka;
    private String marka;
    private int godProizvodnje;

    public Vozilo(String regOznaka, String marka, int godProizvodnje) {
        this.regOznaka = regOznaka;
        this.marka = marka;
        this.godProizvodnje = godProizvodnje;
    }

    public String ucitajPodatke() {
        return String.format("%s/%s/%d", regOznaka, marka, godProizvodnje);
    };

    public String prikaziPodatke() {
        return " marke: " + marka + " (reg. oznaka: " + regOznaka + "), " + godProizvodnje + "god.";
    };

    public String getRegOznaka() {
        return regOznaka;
    }

    public String getMarka() {
        return marka;
    }

    public int getGodProizvodnje() {
        return godProizvodnje;
    }
}
