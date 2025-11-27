public class Motocikl extends Vozilo {
    private String tipMotora;

    public Motocikl(String regOznaka, String marka, int godProizvodnje, String tipMotora) {
        super(regOznaka, marka, godProizvodnje);
        this.tipMotora = tipMotora;
    }

    public String getTipMotora() {
        return tipMotora;
    }

    public void setTipMotora(String tipMotora) {
        this.tipMotora = tipMotora;
    }

    @Override
    public String prikaziPodatke() {
        return "Motocikl" + super.prikaziPodatke() + ", " + tipMotora + " tip motora.";
    }

    @Override
    public String ucitajPodatke() {
        return "Motocikl/" + super.ucitajPodatke() + "/" + tipMotora;
    }
}
