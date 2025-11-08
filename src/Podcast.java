public class Podcast extends Midias {
    private String apresentador;

    public Podcast(String titulo, String apresentador, double duracao, Generos genero) {
        super(titulo, apresentador, duracao, genero);
        this.apresentador = apresentador;
    }

    @Override
    public void reproduzir() {
        System.out.println(" Reproduzindo podcast: " + getTitulo() + " com " + apresentador);
    }
}