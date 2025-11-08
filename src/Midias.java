public abstract class Midias {
    private String titulo;
    private String artista;
    private double duracao;
    private Generos genero;

    public Midias(String titulo, String artista, double duracao, Generos genero) {
        this.titulo = titulo;
        this.artista = artista;
        this.genero = genero;
        this.duracao = duracao;
    }

    public String getTitulo() { return titulo; }
    public String getArtista() { return artista; }
    public double getDuracao() { return duracao; }
    public Generos getGenero() { return genero; }


    public abstract void reproduzir();

    @Override
    public String toString() {
        return "Título: " + titulo + " / Artista: " + artista +
                " / Duração: " + duracao + " min / Gênero: " + genero.getDescricao();
    }
}