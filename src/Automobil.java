public class Automobil extends Vozilo {
    private int brojVrata;

    public Automobil(String regOznaka, String marka, int godProizvodnje, int brojVrata) {
        super(regOznaka, marka, godProizvodnje);
        this.brojVrata = brojVrata;
    }

    public int getBrojVrata() {
        return brojVrata;
    }

    public void setBrojVrata(int brojVrata) {
        this.brojVrata = brojVrata;
    }

    @Override
    public String prikaziPodatke() {
        return "Automobil" + super.prikaziPodatke() + ", " + brojVrata + " vrata.";
    }

    @Override
    public String ucitajPodatke() {
        return "Automobil/" + super.ucitajPodatke() + "/" + brojVrata;
    }
}
