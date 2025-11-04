public abstract class Mídias {
    private String titulo;
    private String artista;
    private double duracao;
    private String genero;

    public Mídias(String titulo, String artista, double duracao, String genero) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracao = duracao;
        this.genero = genero;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    public double getDuracao() {
        return duracao;
    }

    public String getGenero() {
        return genero;
    }

    public void reproduzir(){
        System.out.println("tocando...");
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%.2f min) [%s]", artista, titulo, duracao, genero);
    }
}
