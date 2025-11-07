public class Musica extends Midias {
    public Musica(String titulo, String artista, double duracao, Generos genero) {
        super(titulo, artista, duracao, genero);
    }

    @Override
    public String toString() {
        return "Música - Título: " + getTitulo() + " / Artista: " + getArtista() + " / Gênero: (" + getGenero() + ") " + "/ Duração: "  + getDuracao() + " Minutos";
    }
}
