import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        EvidencijaVozila listaVozila = new EvidencijaVozila();

        //TODO pametnija validacija podataka i exception handling
        //TODO pametnije preraditi algoritme za unos pojedinih podataka
        String[] akcije = {" 1. Ispis evidentiranih vozila", " 2. Dodaj novo vozilo u evidenciju", " 3. Ukloni vozilo iz evidencije", " 4. Azuriraj podatke vozila", " 5. Prikaz podataka vozila", " 6. Izvoz podataka u tekstualnu datoteku", " 7. Zatvaranje aplikacije"};

        beskonacnaPetlja:
        while(true) {

            System.out.println("Aplikacija za evidenciju vozila");
            for (String akcija : akcije) {
                System.out.println(akcija);
            }
            System.out.println("Odaberite jednu od ponudjenih akcija (1-7):");
            int akcija = ObradaAkcija.odabirAkcije(7);

            switch (akcija) {
                case 1:
                    ObradaAkcija.ispisEvidentiranihVozila(listaVozila);
                    break;
                case 2:
                    ObradaAkcija.dodavanjeVozila(listaVozila);
                    break;
                case 3:
                    ObradaAkcija.uklanjanjeVozila(listaVozila);
                    break;
                case 4:
                    ObradaAkcija.azuriranjePodataka(listaVozila);
                    break;
                case 5:
                    ObradaAkcija.prikazPodatakaVozila(listaVozila);
                    break;
                case 6:
                    ObradaAkcija.izvozPodataka(listaVozila);
                    break;
                case 7:
                    break beskonacnaPetlja;
                default:
            }

        }

    }
}
