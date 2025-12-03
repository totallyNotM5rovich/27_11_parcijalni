public enum TipVozilaEnum {
    AUTOMOBIL, MOTOCIKL;

    public String getString() {
        return switch (this) {
            case AUTOMOBIL -> "Automobil";
            case MOTOCIKL -> "Motocikl";
        };
    }
}
