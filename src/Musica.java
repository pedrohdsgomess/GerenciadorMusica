public class Musica extends Midias {



    private String artista;
    private Generos genero;
    private double duracao;

    public Musica(String titulo, String artista, Generos genero, double duracao) {
        super(titulo, artista, duracao, genero);
        this.artista = artista;
        this.genero = genero;
        this.duracao = duracao;
    }


    @Override
    public void reproduzir() {

    }

    @Override
    public String toString() {
        return "Música - Título: " + getTitulo() + " / Artista: " + getArtista() + " / Gênero: (" + getGenero() + ") " + "/ Duração: "  + getDuracao() + " Minutos";
    }
}