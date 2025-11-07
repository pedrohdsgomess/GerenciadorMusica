public class Audiobook extends Midias {
    private String narrador;

    public Audiobook(String titulo, String narrador, double duracao, String genero) {
        super(titulo, narrador, duracao, genero);
        this.narrador = narrador;
    }

    @Override
    public void reproduzir() {
        System.out.println(" Tocando audiobook: " + getTitulo() + " narrado por " + narrador);
    }
}