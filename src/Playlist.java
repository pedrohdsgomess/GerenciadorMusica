
import java.util.ArrayList;
import java.util.List;

public class Playlist {

    private String nome;
    private List<Midias> midias = new ArrayList<>();

    public Playlist(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Midias> getMidias() {
        return midias;
    }

    public void setMidias(List<Midias> midias) {
        this.midias = midias;
    }

    public void adicionarMidia(Midias midia) {
        this.midias.add(midia);
    }

    public void removerMidia(Midias midia) {
        this.midias.remove(midia);
    }
}