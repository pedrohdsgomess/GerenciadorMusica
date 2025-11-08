public enum Generos {
    POP("Pop"),
    ROCK("Rock"),
    JAZZ("Jazz"),
    CLASSICO("Clássico"),
    RAP("Rap"),
    SERTANEJO("Sertanejo"),
    FUNK("Funk");

    private final String descricao;

    Generos(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }


    public static Generos fromDescricao(String texto) {
        for (Generos g : Generos.values()) {
            if (g.getDescricao().equalsIgnoreCase(texto)) {
                return g;
            }
        }
        return null;
    }
}
