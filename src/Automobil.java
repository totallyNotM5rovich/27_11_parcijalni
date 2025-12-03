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
        return String.format("%s %s, %s vrata.", TipVozilaEnum.AUTOMOBIL.getString(), super.prikaziPodatke(), brojVrata);
    }

    @Override
    public String ucitajPodatke() {
        return String.format("%s/%s/%d", TipVozilaEnum.AUTOMOBIL.getString(), super.ucitajPodatke(), brojVrata);
    }
}
