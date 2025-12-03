import java.io.*;
import java.util.ArrayList;

public class EvidencijaVozila {
    private final File podaci = new File("./src/data.txt");
    private final ArrayList<Vozilo> vozila;

    public EvidencijaVozila() {
        this.vozila = new ArrayList<>();

        if(podaci.exists()) {
            ucitajPodatke();
        }
    }

    private void ucitajPodatke() {
        try (BufferedReader br = new BufferedReader(new FileReader(podaci))) {
            String vozilo;

            while ((vozilo = br.readLine()) != null) {
                String[] argumenti = vozilo.split("/");

                if (argumenti[0].equals(TipVozilaEnum.AUTOMOBIL.getString())) {
                    vozila.add(new Automobil(argumenti[1], argumenti[2], Integer.parseInt(argumenti[3]), Integer.parseInt(argumenti[4])));
                }
                if (argumenti[0].equals(TipVozilaEnum.MOTOCIKL.getString())) {
                    vozila.add(new Motocikl(argumenti[1], argumenti[2], Integer.parseInt(argumenti[3]), argumenti[4]));
                }
            }
        } catch (IOException e) {
            System.out.println("Greska prilikom dohvacanja podataka: " + e.getMessage());
        }
    }

    private void spremiPodatke() throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(podaci))) {
            for (Vozilo vozilo : vozila) {
                pw.println(vozilo.ucitajPodatke());
            }
        }
    }

    public void ispisVozila() {
        System.out.println("\r\nLista evidentiranih vozila:");
        for (int i=0; i<vozila.size(); i++) {
            System.out.printf("%d. %s\r\n", (i+1), vozila.get(i).prikaziPodatke());
        }
        System.out.println();
    }

    public void dodajVozilo(String tipVozila, String regOznaka, String marka, int godProizvodnje, String dodatnoSvojstvo) throws IOException {
        if (tipVozila.equals(TipVozilaEnum.AUTOMOBIL.getString())) {
            vozila.add(new Automobil(regOznaka, marka, godProizvodnje, Integer.parseInt(dodatnoSvojstvo)));
        }
        if (tipVozila.equals(TipVozilaEnum.MOTOCIKL.getString())) {
            vozila.add(new Motocikl(regOznaka, marka, godProizvodnje, dodatnoSvojstvo));
        }
        spremiPodatke();
    }

    public void ukliniVozilo(int index) throws IOException {
        vozila.remove(index);
        spremiPodatke();
    }

    public void azurirajVozilo(int index) throws IOException {
        while(true) {
            System.out.println("NAPOMENA! Nije moguce promjeniti tip vozila!");
            System.out.printf(" 1. Azuriraj registracijsku oznaku: %s\r\n", vozila.get(index).getRegOznaka());
            System.out.printf(" 2. Azuriraj marku vozila: %s\r\n", vozila.get(index).getMarka());
            System.out.printf(" 3. Azuriraj godinu proizvodenje: %d\r\n", vozila.get(index).getGodProizvodnje());
            if(vozila.get(index) instanceof Automobil) {
                System.out.printf(" 4. Azuriraj broj vrata: %d\r\n", ((Automobil) vozila.get(index)).getBrojVrata());
            } else {
                System.out.printf(" 4. Azuriraj tip motora: %s\r\n", ((Motocikl) vozila.get(index)).getTipMotora());
            }
            System.out.println(" 5. Povratak");

            System.out.println("Odaberite jednu od ponudjenih akcija (1-5):");
            int akcijaAzuriranje = ObradaAkcija.odabirAkcije(5);

            switch (akcijaAzuriranje) {
                case 1:
                    vozila.get(index).setRegOznaka(ObradaAkcija.definicijaRegOznake());
                    spremiPodatke();
                    break;
                case 2:
                    vozila.get(index).setMarka(ObradaAkcija.definicijaMarke());
                    spremiPodatke();
                    break;
                case 3:
                    vozila.get(index).setGodProizvodnje(ObradaAkcija.definicijaGodine());
                    spremiPodatke();
                    break;
                case 4:
                    if(vozila.get(index) instanceof Automobil) {
                        ((Automobil) vozila.get(index)).setBrojVrata(Integer.parseInt(ObradaAkcija.unosBrojaVrata()));
                        spremiPodatke();
                    } else {
                        ((Motocikl) vozila.get(index)).setTipMotora(ObradaAkcija.unosTipaMotora());
                        spremiPodatke();
                    }
                    break;
                case 5:
                    return;
                default:
            }
        }
    }

    public void prikazPodatakaVozila(int index) {
        System.out.println(vozila.get(index).prikaziPodatke());
    }

    public int getSize() {
        return vozila.size();
    }

    public String ispisTabliceVozila() {
        int najduziRedniBroj = Integer.toString(vozila.size()).length();
        int najduziTip = 10;
        int najduzaRegOznaka = 10;
        int najduzaMarka = 5;
        int najduzaGodina = 6;

        for (Vozilo vozilo : vozila) {
            if(vozilo.getMarka().length() > najduzaMarka) {
                najduzaMarka = vozilo.getMarka().length();
            }
        }

        char vodoravnaLinija = '\u2501';
        char okomitaLinija = '\u2503';
        char tSpojnica = '\u2533';
        char krizanje = '\u254b';


        StringBuilder tablica = new StringBuilder();
        for (int i=0; i<najduziRedniBroj + 3; i++) {
            tablica.append(vodoravnaLinija);
        }
        tablica.append(tSpojnica);
        for(int i=0; i<najduziTip + 2; i++) {
            tablica.append(vodoravnaLinija);
        }
        tablica.append(tSpojnica);
        for(int i=0; i<najduzaRegOznaka + 2; i++) {
            tablica.append(vodoravnaLinija);
        }
        tablica.append(tSpojnica);
        for(int i=0; i<najduzaMarka + 2; i++) {
            tablica.append(vodoravnaLinija);
        }
        tablica.append(tSpojnica);
        for(int i=0; i<najduzaGodina + 2; i++) {
            tablica.append(vodoravnaLinija);
        }


        tablica.append("\r\n # ");
        for (int i=1; i<najduziRedniBroj+1; i++) {
            tablica.append(' ');
        }
        tablica.append(okomitaLinija + " TIP VOZILA");
        for (int i=10; i<najduziTip + 1; i++) {
            tablica.append(' ');
        }
        tablica.append(okomitaLinija + " REG OZNAKA");
        for (int i=10; i<najduzaRegOznaka + 1; i++) {
            tablica.append(' ');
        }
        tablica.append(okomitaLinija + " MARKA");
        for (int i=5; i<najduzaMarka + 1; i++) {
            tablica.append(' ');
        }
        tablica.append(okomitaLinija + " GODINA\r\n");

        for (int i=0; i<najduziRedniBroj + 3; i++) {
            tablica.append(vodoravnaLinija);
        }
        tablica.append(krizanje);
        for(int i=0; i<najduziTip + 2; i++) {
            tablica.append(vodoravnaLinija);
        }
        tablica.append(krizanje);
        for(int i=0; i<najduzaRegOznaka + 2; i++) {
            tablica.append(vodoravnaLinija);
        }
        tablica.append(krizanje);
        for(int i=0; i<najduzaMarka + 2; i++) {
            tablica.append(vodoravnaLinija);
        }
        tablica.append(krizanje);
        for(int i=0; i<najduzaGodina + 2; i++) {
            tablica.append(vodoravnaLinija);
        }
        tablica.append("\r\n");

        for (int i = 0; i < vozila.size(); i++) {
            String redniBroj = String.format(" %d.", (i+1));
            String tipVozila = (vozila.get(i) instanceof Automobil) ? TipVozilaEnum.AUTOMOBIL.getString() : TipVozilaEnum.MOTOCIKL.getString();
            tablica.append(redniBroj);
            for (int j = redniBroj.length(); j<najduziRedniBroj + 3; j++) {
                tablica.append(' ');
            }
            tablica.append(okomitaLinija + " " + tipVozila);
            for (int j = tipVozila.length(); j<najduziTip + 1; j++) {
                tablica.append(' ');
            }
            tablica.append(okomitaLinija + " " + vozila.get(i).getRegOznaka());
            for (int j = vozila.get(i).getRegOznaka().length(); j<najduzaRegOznaka + 1; j++) {
                tablica.append(' ');
            }
            tablica.append(okomitaLinija + " " + vozila.get(i).getMarka());
            for (int j = vozila.get(i).getMarka().length(); j<najduzaMarka + 1; j++) {
                tablica.append(' ');
            }
            tablica.append(okomitaLinija + " " + vozila.get(i).getGodProizvodnje() + "\r\n");
        }

        return tablica.toString();
    }
}
