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
        return String.format("%s %s, %s tip motora.", TipVozilaEnum.MOTOCIKL.getString(), super.prikaziPodatke(), tipMotora);
    }

    @Override
    public String ucitajPodatke() {
        return String.format("%s/%s/%s", TipVozilaEnum.MOTOCIKL.getString(), super.ucitajPodatke(), tipMotora);
    }
}
