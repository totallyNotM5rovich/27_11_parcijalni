import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        EvidencijaVozila listaVozila = new EvidencijaVozila();

        //TODO akcije za implementaciju: dohvacanje  podataka pojedinog vozila (logika kao za uklanjanje, ali poziv prikazPodataka() objekta liste), azuriranje pojedinih svojstava vozila (logika kao za uklanjanje, ali pozivanje setter metoda (koje je potrebno definirati))
        //TODO pametnija validacija podataka i exception handling
        //TODO pametnije preraditi algoritme za unos pojedinih podataka
        String[] akcije = {" 1. Ispis evidentiranih vozila", " 2. Dodaj novo vozilo u evidenciju", " 3. Ukloni vozilo iz evidencije", " 4. Zatvaranje aplikacije"};

        beskonacnaPetlja:
        while(true) {

            System.out.println("Aplikacija za evidenciju vozila");
            for (String akcija : akcije) {
                System.out.println(akcija);
            }
            System.out.println("Odaberite jednu od ponudjenih akcija (1-4):");
            int akcija = ObradaAkcija.odabirAkcije(4);

            switch (akcija) {
                case 1:
                    listaVozila.ispisTabliceVozila();
                    break;
                case 2:
                    ObradaAkcija.dodavanjeVozila(listaVozila);
                    break;
                case 3:
                    ObradaAkcija.uklanjanjeVozila(listaVozila);
                    break;
                case 4:
                    break beskonacnaPetlja;
                default:
            }

        }

    }
}
