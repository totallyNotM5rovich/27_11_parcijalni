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
        return String.format("marke: %s (reg. oznaka: %s), %d god.", marka, regOznaka, godProizvodnje);
    };

    public String getRegOznaka() {
        return regOznaka;
    }

    public void setRegOznaka(String regOznaka) {
        this.regOznaka = regOznaka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public void setGodProizvodnje(int godProizvodnje) {
        this.godProizvodnje = godProizvodnje;
    }

    public String getMarka() {
        return marka;
    }

    public int getGodProizvodnje() {
        return godProizvodnje;
    }
}
