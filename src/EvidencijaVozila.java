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
}
