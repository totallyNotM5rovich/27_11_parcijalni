import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        EvidencijaVozila listaVozila = new EvidencijaVozila();

        String[] akcije = {" 1. Ispis evidentiranih vozila", " 2. Dodaj novo vozilo u evidenciju", " 3. Zatvaranje aplikacije"};

        beskonacnaPetlja:
        while(true) {

            System.out.println("Aplikacija za evidenciju vozila");
            for (String akcija : akcije) {
                System.out.println(akcija);
            }
            System.out.println("Odaberite jednu od ponudjenih akcija (1-3):");
            int akcija = ObradaAkcija.odabirAkcije();

            switch (akcija) {
                case 1:
                    listaVozila.ispisVozila();
                    break;
                case 2:
                    ObradaAkcija.dodavanjeVozila(listaVozila);
                    break;
                case 3:
                    break beskonacnaPetlja;
                default:
            }

        }
    }
}
