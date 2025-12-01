import java.awt.desktop.SystemSleepEvent;
import java.io.*;

public class ObradaAkcija {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int odabirAkcije(int max) {
        boolean validnaAkcija = false;
        int akcija = 1;
        do {
            try {
                String unos = br.readLine().trim();
                akcija = Integer.parseInt(unos);
                if (akcija < 1 || akcija > max) {
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

    public static void ispisEvidentiranihVozila(EvidencijaVozila lista) {
        if(lista.getSize() == 0) {
            System.out.println("Evidencijska lista vozila je prazna!");
            return;
        }
        System.out.println(lista.ispisTabliceVozila());
    }

    public static void dodavanjeVozila(EvidencijaVozila lista) throws IOException {
        String tipVozila = null;
        String regOznaka = null;
        String marka = null;
        int godina = 0;
        String dodatniPodatak = null;
        dodajVoziloLoop:
        while(true) {


            String ispisTipVozila = (tipVozila == null) ? "NEDEFINIRAN" : tipVozila;
            System.out.printf(" 1. Odaberi tip vozila: %s\r\n", ispisTipVozila);
            String ispisRegOznake = (regOznaka == null) ? "NEDEFINIRANA" : regOznaka;
            System.out.printf(" 2. Definiraj registracijsku oznaku: %s\r\n", ispisRegOznake);
            String ispisMarke = (marka == null) ? "NEDEFINIRANA" : marka;
            System.out.printf(" 3. Definiraj marku vozila: %s\r\n", ispisMarke);
            String ispisGodine = (godina == 0) ? "NEDEFINIRANA" : String.format("%d", godina);
            System.out.printf(" 4. Definiraj godinu proizvodnje: %s\r\n", ispisGodine);
            if(tipVozila != null) {
                String ispisDodatnogPodatka = (dodatniPodatak == null) ? "NEDEFINIRAN" : dodatniPodatak;
                if(tipVozila.equals("Automobil")) {
                    System.out.printf(" 5. Definiraj broj vrata: %s\r\n", ispisDodatnogPodatka);
                }
                if(tipVozila.equals("Motocikl")) {
                    System.out.printf(" 5. Definiraj tip motora: %s\r\n", ispisDodatnogPodatka);
                }
            }
            System.out.println(" 6. Odustani");
            if(tipVozila != null && regOznaka != null && marka != null && godina != 0 && dodatniPodatak != null) {
                System.out.println(" 7. Dodaj vozilo");
            }

            System.out.println("Odaberite jednu od ponudjenih akcija (1-7):");
            int akcijaDodavanje = ObradaAkcija.odabirAkcije(7);

            switch (akcijaDodavanje) {
                case 1:
                    tipVozila = odabirTipaVozila();
                    dodatniPodatak = null;
                    break;
                case 2:
                    regOznaka = definicijaRegOznake();
                    break;
                case 3:
                    marka = definicijaMarke();
                    break;
                case 4:
                    godina = definicijaGodine();
                    break;
                case 5:
                    if(tipVozila != null) {
                        dodatniPodatak = tipVozila.equals("Automobil") ? unosBrojaVrata() : unosTipaMotora();
                    }
                    break;
                case 6:
                    break dodajVoziloLoop;
                case 7:
                    if(tipVozila != null && regOznaka != null && marka != null && godina != 0 && dodatniPodatak != null) {
                        lista.dodajVozilo(tipVozila, regOznaka, marka, godina, dodatniPodatak);
                        System.out.println("Vozilo uspjesno dodano u evidenciju!");
                    }
                    break dodajVoziloLoop;
                default:
            }

        }
    }

    public static void uklanjanjeVozila(EvidencijaVozila lista) throws IOException {
        if(lista.getSize() == 0) {
            System.out.println("Evidencijska lista vozila je prazna!");
            return;
        }

        System.out.println(lista.ispisTabliceVozila());

        System.out.printf("Odaberite vozilo koje zelite ukloniti iz evidencije upisivanjem pridruzenog rednog broja (1-%d):\r\n", lista.getSize());

        int index = odabirVozilaIzListe(lista);

        lista.ukliniVozilo(index);

        System.out.println("Vozilo uspjesno uklonjeno iz evidencije!");
    }

    public static void azuriranjePodataka(EvidencijaVozila lista) throws IOException {
        if(lista.getSize() == 0) {
            System.out.println("Evidencijska lista vozila je prazna!");
            return;
        }

        System.out.println(lista.ispisTabliceVozila());

        System.out.printf("Odaberite vozilo koje zelite azurirati upisivanjem pridruzenog rednog broja (1-%d):\r\n", lista.getSize());

        int index = odabirVozilaIzListe(lista);

        lista.azurirajVozilo(index);
    }

    public static void prikazPodatakaVozila(EvidencijaVozila lista) {
        if(lista.getSize() == 0) {
            System.out.println("Evidencijska lista vozila je prazna!");
            return;
        }

        System.out.println(lista.ispisTabliceVozila());

        System.out.printf("Odaberite vozilo cije podatke zelite prikazati upisivanjem pridruzenog rednog broja (1-%d):\r\n", lista.getSize());

        int index = odabirVozilaIzListe(lista);

        lista.prikazPodatakaVozila(index);
    }

    public static void izvozPodataka(EvidencijaVozila lista) {
        if (lista.getSize() == 0) {
            System.out.println("Evidencijska lista vozila je prazna!");
            return;
        }
        String defaultDir = System.getProperty("user.home") + "\\EV_output";
        String destinacijskiDir = null;
        String nazivDatoteke = null;

        izvozPodatakaLoop:
        while(true) {
            System.out.printf(" 1. Definiraj apsolutnu adresu destinacijskog direktorija: %s\r\n", (destinacijskiDir == null) ? (defaultDir + " (predefinirano)") : destinacijskiDir);
            System.out.printf(" 2. Definiraj naziv izlazne datoteke: %s\r\n", (nazivDatoteke == null) ? "NEDEFINIRAN (opcionalno)" : nazivDatoteke);
            System.out.println(" 3. Spremi datoteku");
            System.out.println(" 4. Povratak");

            System.out.println("Odaberite jednu od ponudjenih akcija (1-4):");
            int akcijaIzvoz = ObradaAkcija.odabirAkcije(4);

            switch (akcijaIzvoz) {
                case 1:
                    destinacijskiDir = definicijaDestinacijskogDir();
                    break;
                case 2:
                    nazivDatoteke = definicijaOpcionalnogNaziva();
                    break;
                case 3:
                    izvozPodatakaAlg(destinacijskiDir, nazivDatoteke, lista);
                    break izvozPodatakaLoop;
                case 4:
                    break izvozPodatakaLoop;
                default:
            }
        }
    }

    public static String odabirTipaVozila() {
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
        return tipVozila;
    }

    public static String definicijaRegOznake() {
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

        return regOznaka;
    }

    public static String definicijaMarke() {
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

        return marka;
    }

    public static int definicijaGodine() {
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

        return godina;
    }

    public static String unosBrojaVrata() {
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

    public static String unosTipaMotora() {
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

    private static String definicijaDestinacijskogDir() {
        String destinacijskiDir = null;
        boolean validanDir = false;
        System.out.println("Upisi apsolutnu apsolutnu adresu destinacijskog direktorija:");
        do {
            try {
                String unos = br.readLine().trim();
                if(unos.isEmpty()) {
                    return null;
                }
                if(!new File(unos).exists()) {
                    throw new NeispravniPodaciException("Ne postoji direktorij sa definiranom adresom, unesite ispravanu adresu ili potvrdite sa praznim unosom:");
                }
                if(!new File(unos).isDirectory()) {
                    throw new NeispravniPodaciException("Unesena adresa nije direktorij, unesite adresu destinacijskog direktorija:");
                }
                destinacijskiDir = unos;
                validanDir = true;
            } catch (NeispravniPodaciException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println("Greska: " + e.getMessage());
            }
        } while (!validanDir);

        return destinacijskiDir;
    }

    private static String definicijaOpcionalnogNaziva() {
        String naziv = null;
        boolean validanNaziv = false;
        System.out.println("Upisi naziv izlazne datoteke (bez ekstenzije):");
        do {
            try {
                String unos = br.readLine().trim();
                if(unos.isEmpty()) {
                    return null;
                }
                if(!unos.matches("^[A-Za-z0-9._-]")) {
                    throw new NeispravniPodaciException("Upisani naziv sadrzi nedozvoljene znakove (u imenovanju datoteka dozvoljeni su alfanumericki znakovi, te znakovi \".\", \"_\" i \"-\").\r\nUpisite validan naziv ili potvrdite sa praznim unosom:");
                }
                naziv = unos;
                validanNaziv = true;
            } catch (NeispravniPodaciException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println("Greska: " + e.getMessage());
            }
        } while(!validanNaziv);
        return naziv;
    }

    private static void izvozPodatakaAlg(String destinacijskiDir, String naziv, EvidencijaVozila lista) {
        String defaultDir = System.getProperty("user.home") + "\\EV_output";
        if(destinacijskiDir == null) {
            if(!new File(defaultDir).exists()) {
                new File(defaultDir).mkdir();
            }
        }

        String defaultNaziv = "EV_podaci";
        if(naziv == null) {
            int i = 1;
            while(new File(String.format("%s\\%s.txt", defaultDir, defaultNaziv)).exists()) {
                defaultNaziv = "EV_podaci" + i++;
            }
        }

        String destinacijskiPath = String.format("%s\\%s.txt", ((destinacijskiDir == null) ? defaultDir : destinacijskiDir), ((naziv == null) ? defaultNaziv : naziv));

        try (Reader tablica = new StringReader(lista.ispisTabliceVozila());
            Writer fw = new FileWriter(destinacijskiPath)) {
            int c;
            while ((c = tablica.read()) != -1) {
                fw.write(c);
            }
        } catch (IOException e) {
            System.out.println("Greska: " + e.getMessage());
        }
        System.out.printf("Kreirana datoteka: %s\r\nNa lokaciji: %s\r\n", new File(destinacijskiPath).getName(), new File(destinacijskiPath).getAbsolutePath());
    }

    private static int odabirVozilaIzListe(EvidencijaVozila lista) {
        int index = 0;
        boolean validanIndex = false;
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
        return index;
    }

}
