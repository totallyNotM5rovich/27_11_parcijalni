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

                if (argumenti[0].equals("Automobil")) {
                    vozila.add(new Automobil(argumenti[1], argumenti[2], Integer.parseInt(argumenti[3]), Integer.parseInt(argumenti[4])));
                }
                if (argumenti[0].equals("Motocikl")) {
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
        if (tipVozila.equals("Automobil")) {
            vozila.add(new Automobil(regOznaka, marka, godProizvodnje, Integer.parseInt(dodatnoSvojstvo)));
        }
        if (tipVozila.equals("Motocikl")) {
            vozila.add(new Motocikl(regOznaka, marka, godProizvodnje, dodatnoSvojstvo));
        }
        spremiPodatke();
    }

    public void ispisTabliceVozila() {
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

        System.out.println("\r\nLista evidentiranih vozila:");

        StringBuilder tablica = new StringBuilder();
        for (int i=0; i<4; i++) {
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


        tablica.append("\r\n #  " + okomitaLinija + " TIP VOZILA");
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

        for (int i=0; i<4; i++) {
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
            String tipVozila = (vozila.get(i) instanceof Automobil) ? "Automobil" : "Motocikl";
            tablica.append(redniBroj);
            for (int j = redniBroj.length(); j<4; j++) {
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
        System.out.println(tablica);
    }
}
