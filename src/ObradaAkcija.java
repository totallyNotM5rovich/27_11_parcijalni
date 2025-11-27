import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ObradaAkcija {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int odabirAkcije() {
        boolean validnaAkcija = false;
        int akcija = 1;
        do {
            try {
                String unos = br.readLine().trim();
                akcija = Integer.parseInt(unos);
                if (akcija < 1 || akcija > 4) {
                    throw new IllegalArgumentException("Neispravan unos! Odaberite jednu od ponudjenih akcija (1-4)");
                }
                validnaAkcija = true;
            } catch (NumberFormatException e) {
                System.out.println("Neispravan unos! Ocekivani unos je broj (1-4):");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Neispravan unos!");
            }
        } while (!validnaAkcija);
        return akcija;
    }

    public static void dodavanjeVozila(EvidencijaVozila lista) throws IOException {
        String tipVozila = null;
        boolean validanOdabir = false;
        System.out.println("\r\nOdaberite (upisivanjem pridruzenog rednog broja) tip vozila koje dodajete (1 ili 2):\r\n 1. Automobil\r\n 2. Motocikl");
        do {
            try {
                String unos = br.readLine().trim();
                if (unos.endsWith(".")) {
                    unos = unos.substring(0,1);
                }
                if (!(unos.equals("1") || unos.equals("2"))) {
                    throw new NeispravniPodaciException("Odaberite jedan od ponudjenih tipova vozila (1 ili 2):\r\n 1. Automobil\r\n 2. Motocikl");
                }
                tipVozila = unos.equals("1") ? "Automobil" : "Motocikl";
                validanOdabir = true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NeispravniPodaciException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Greska!");
            }
        } while (!validanOdabir);

        String regOznaka = null;
        boolean validnaRegOznaka = false;
        System.out.println("\r\nUpisite registracijsku oznaku vozila koje dodajete: ");
        do {
            try {
                String unos = br.readLine().trim();
                if (unos.isEmpty()) {
                    throw new NeispravniPodaciException("Registracijska oznaka vozila je obavezan podatak. Unesite marku vozila: ");
                }
                regOznaka = unos;
                validnaRegOznaka = true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NeispravniPodaciException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Greska!");
            }
        } while (!validnaRegOznaka);

        String marka = null;
        boolean validnaMarka = false;
        System.out.println("\r\nUpisite marku vozila koje dodajete: ");
        do {
            try {
                String unos = br.readLine().trim();
                if (unos.isEmpty()) {
                    throw new NeispravniPodaciException("Marka vozila je obavezan podatak. Upisite marku vozila: ");
                }
                marka = unos;
                validnaMarka = true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NeispravniPodaciException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Greska!");
            }
        } while (!validnaMarka);

        int godina = 0;
        boolean validnaGodina = false;
        System.out.println("\r\nUpisite godinu proizvodnje vozila:");
        do {
            try {
                String unos = br.readLine().trim();
                int unosInt = Integer.parseInt(unos);
                if (unosInt < 0) {
                    throw new NeispravniPodaciException("Godina proizvodnje ne moze biti negativan broj.\r\nUpisite godineu proizvodnje:");
                }
                godina = unosInt;
                validnaGodina = true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NeispravniPodaciException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Greska!");
            }
        } while (!validnaGodina);

        String dodatniPodatak = tipVozila.equals("Automobil") ? unosBrojaVrata() : unosTipaMotora();

        lista.dodajVozilo(tipVozila, regOznaka, marka, godina, dodatniPodatak);

        System.out.println("Vozilo uspjesno dodano u evidenciju!");

    }

    private static String unosBrojaVrata() {
        String brojVrata = null;
        boolean validanUnos = false;
        System.out.println("\r\nUpisite broj vrata automobila:");
        do {
            try {
                String unos = br.readLine().trim();
                if (unos.isEmpty()) {
                    throw new NeispravniPodaciException("Broj vrata automobila je obavezan podatak:");
                }
                int unosInt = Integer.parseInt(unos);
                if (unosInt < 1) {
                    throw new NeispravniPodaciException("Unesite broj vrata automobila:");
                }
                brojVrata = unos;
                validanUnos = true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NeispravniPodaciException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Greska!");
            }
        } while (!validanUnos);

        return brojVrata;
    }

    private static String unosTipaMotora() {
        String tipMotora = null;
        boolean validanUnos = false;
        System.out.println("\r\nUpisite tip motora:");
        do {
            try {
                String unos = br.readLine().trim();
                if (unos.isEmpty()) {
                    throw new NeispravniPodaciException("Tip motora je obavezan podatak:");
                }
                tipMotora = unos;
                validanUnos = true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NeispravniPodaciException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Greska!");
            }
        } while (!validanUnos);

        return tipMotora;
    }

    public static void uklanjanjeVozila(EvidencijaVozila lista) throws IOException {
        lista.ispisTabliceVozila();

        int index = 0;
        boolean validanIndex = false;
        System.out.printf("Odaberite vozilo koje zelite ukloniti iz evidencije upisivanjem pridruzenog rednog broja (1-%d):\r\n", lista.getSize());
        do {
            try {
                String unos = br.readLine().trim();
                if (unos.isEmpty()) {
                    throw new NeispravniPodaciException("Ocekivani unos je broj pridruzen vozilu:");
                }
                if (unos.endsWith(".")) {
                    unos = unos.substring(0, unos.length()-2);
                }
                index = Integer.parseInt(unos)-1;
                if (index < 0 || index > lista.getSize()-1) {
                    throw new NeispravniPodaciException("Unesite broj pridruzen vozilu:");
                }
                validanIndex = true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NeispravniPodaciException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Greska!");
            }
        } while (!validanIndex);

        lista.ukliniVozilo(index);

        System.out.println("Vozilo uspjesno uklonjeno iz evidencije!");

    }


}
