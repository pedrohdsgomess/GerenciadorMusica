import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import exceptions.CodigoInvalidoException;

public class Catalogo {


    private String email;
    private ArrayList<Midias> midias = new ArrayList<>();
    private ArrayList<Playlist> playLists = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);


    public void listarMidias() {
        if (midias.isEmpty()) {
            System.out.println("Erro! Nenhuma mídia cadastrada.");
        } else {
            System.out.println("Lista de mídias:");
            for (int i = 0; i < midias.size(); i++) {
                // Adicionando o índice para facilitar a seleção
                System.out.println((i + 1) + " - " + midias.get(i).toString());
            }
        }
    }


    public void adicionarMidia() {
        try {
            System.out.println("Qual tipo de mídia você quer adicionar?");
            System.out.println("1 - Música");
            System.out.println("2 - Podcast");
            System.out.println("3 - Audiobook");
            System.out.print("Escolha sua opção: ");
            int tipoMidia = Integer.parseInt(sc.nextLine());

            if (tipoMidia < 1 || tipoMidia > 3) {
                System.out.println("Tipo de mídia inválido!");
                return;
            }

            System.out.print("Título: ");
            String titulo = sc.nextLine();
            System.out.print("Artista/Autor/Apresentador: ");
            String artista = sc.nextLine();


            if (titulo.trim().isEmpty() || artista.trim().isEmpty()) {
                System.out.println("Título e Artista/Autor/Apresentador são obrigatórios.");
                return;
            }

            System.out.print("Duração (em minutos): ");
            double duracao = Double.parseDouble(sc.nextLine());

            if (duracao <= 0) {
                System.out.println("Duração não pode ser igual ou menor que zero!");
                return;
            }


            System.out.println("Selecione o gênero/categoria:");
            int i = 1;
            for (Generos g : Generos.values()) {
                System.out.println(i + " - " + g.getDescricao());
                i++;
            }
            Generos genero = selecionarGenero(sc);
            if (genero == null) return;

            Midias novaMidia = null;

            switch (tipoMidia) {
                case 1:
                    novaMidia = new Musica(titulo, artista, genero, duracao);
                    break;
                case 2:
                    novaMidia = new Podcast(titulo, artista, duracao, genero);
                    break;
                case 3:
                    novaMidia = new Audiobook(titulo, artista, duracao, genero);
                    break;
            }

            midias.add(novaMidia);
            System.out.println("Mídia adicionada com sucesso!");

        } catch (NumberFormatException e) {
            System.out.println("Erro! Formato de número inválido. Tente novamente.");
        }
    }


    public Generos selecionarGenero(Scanner sc) {
        try {
            System.out.print("Digite o número do gênero: ");
            int generoOpcao = Integer.parseInt(sc.nextLine());
            if (generoOpcao >= 1 && generoOpcao <= Generos.values().length) {
                return Generos.values()[generoOpcao - 1];
            } else {
                System.out.println("Opção de gênero inválida.");
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Digite um número.");
            return null;
        }
    }


    public void excluirMidia() {
        if (midias.isEmpty()) {
            System.out.println("Nenhuma mídia para excluir.");
            return;
        }
        listarMidias(); // Reutiliza a listagem
        System.out.print("Digite o número da mídia que deseja excluir: ");
        try {
            int index = Integer.parseInt(sc.nextLine()) - 1;
            if (index >= 0 && index < midias.size()) {
                Midias midiaRemovida = midias.remove(index);
                System.out.println("Mídia '" + midiaRemovida.getTitulo() + "' removida com sucesso.");
            } else {
                System.out.println("Índice inválido.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro! Entrada inválida. Digite um número.");
        }
    }


    public void pesquisarMidia() throws CodigoInvalidoException {
        System.out.println("Como deseja pesquisar a mídia?");
        System.out.println("1 - Por Título");
        System.out.println("2 - Por Artista/Autor/Apresentador");
        System.out.println("3 - Por Gênero");
        System.out.print("Opção: ");
        String opcao = sc.nextLine();
        List<Midias> resultados = new ArrayList<>();

        switch (opcao) {
            case "1":
                System.out.print("Digite o título: ");
                String titulo = sc.nextLine();
                for (Midias midia : midias) {
                    if (midia.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                        resultados.add(midia);
                    }
                }
                break;
            case "2":
                System.out.print("Digite o nome do artista/autor: ");
                String artista = sc.nextLine();
                for (Midias midia : midias) {
                    if (midia.getArtista().toLowerCase().contains(artista.toLowerCase())) {
                        resultados.add(midia);
                    }
                }
                break;
            case "3":
                System.out.print("Digite o gênero (ex: Pop, Rock, Outro): ");
                String generoString = sc.nextLine();

                Generos generoPesquisa = Generos.fromDescricao(generoString);

                if (generoPesquisa != null) {
                    for (Midias midia : midias) {

                        if (midia.getGenero().equals(generoPesquisa)) {
                            resultados.add(midia);
                        }
                    }
                } else {
                    System.out.println("Gênero inválido. Use um dos listados.");
                }
                break;
            default:
                System.out.println("Opção de pesquisa inválida.");
                return;
        }

        if (resultados.isEmpty()) {

            throw new CodigoInvalidoException("Nenhuma mídia encontrada com o critério informado.");
        } else {
            System.out.println("\nResultados da pesquisa:");
            for (Midias m : resultados) {
                System.out.println(m.toString());
            }
        }
    }


    public void quantidadeTotal() {
        System.out.println("Quantidade total de mídias: " + midias.size());
        System.out.println("Quantidade total de playlists: " + playLists.size());
    }


    public void criarPlaylist() {
        System.out.print("Digite o nome da nova playlist: ");
        String nomePlaylist = sc.nextLine();
        if (nomePlaylist.trim().isEmpty()) {
            System.out.println("Nome da playlist não pode ser vazio.");
            return;
        }
        for (Playlist p : playLists) {
            if (p.getNome().equalsIgnoreCase(nomePlaylist)) {
                System.out.println("Já existe uma playlist com esse nome.");
                return;
            }
        }
        Playlist novaPlaylist = new Playlist(nomePlaylist);
        playLists.add(novaPlaylist);
        System.out.println("Playlist '" + nomePlaylist + "' criada com sucesso.");
    }


    public void excluirPlaylist() {
        if (playLists.isEmpty()) {
            System.out.println("Nenhuma playlist para excluir.");
            return;
        }
        System.out.println("Playlists cadastradas:");
        for (int i = 0; i < playLists.size(); i++) {
            System.out.println((i + 1) + " - " + playLists.get(i).getNome());
        }
        System.out.print("Digite o número da playlist que deseja excluir: ");
        try {
            int index = Integer.parseInt(sc.nextLine()) - 1;
            if (index >= 0 && index < playLists.size()) {
                Playlist playlistRemovida = playLists.remove(index);
                System.out.println("Playlist '" + playlistRemovida.getNome() + "' removida com sucesso.");
            } else {
                System.out.println("Índice inválido.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro! Entrada inválida. Digite um número.");
        }
    }

    public void adicionarMidiaPlaylist() {
        if (midias.isEmpty() || playLists.isEmpty()) {
            System.out.println("É necessário ter mídias e playlists cadastradas.");
            return;
        }

        try {
            System.out.println("Selecione a playlist:");
            for (int i = 0; i < playLists.size(); i++) {
                System.out.println((i + 1) + " - " + playLists.get(i).getNome());
            }
            System.out.print("Número da playlist: ");
            int indexPlaylist = Integer.parseInt(sc.nextLine()) - 1;

            System.out.println("Selecione a mídia:");
            for (int i = 0; i < midias.size(); i++) {
                System.out.println((i + 1) + " - " + midias.get(i).getTitulo());
            }
            System.out.print("Número da mídia: ");
            int indexMidia = Integer.parseInt(sc.nextLine()) - 1;

            Playlist playlist = playLists.get(indexPlaylist);
            Midias midia = midias.get(indexMidia);

            if (playlist.getMidias().contains(midia)) {
                System.out.println("Mídia já está na playlist.");
                return;
            }
            playlist.adicionarMidia(midia);
            System.out.println("Mídia '" + midia.getTitulo() + "' adicionada à playlist '" + playlist.getNome() + "' com sucesso.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Índice de playlist ou mídia inválido.");
        } catch (NumberFormatException e) {
            System.out.println("Erro! Entrada inválida. Digite um número.");
        }
    }


    public void excluirMidiaPlaylist() {
        if (playLists.isEmpty()) {
            System.out.println("Nenhuma playlist para gerenciar.");
            return;
        }

        try {
            System.out.println("Selecione a playlist:");
            for (int i = 0; i < playLists.size(); i++) {
                System.out.println((i + 1) + " - " + playLists.get(i).getNome());
            }
            System.out.print("Número da playlist: ");
            int indexPlaylist = Integer.parseInt(sc.nextLine()) - 1;

            Playlist playlist = playLists.get(indexPlaylist);
            if (playlist.getMidias().isEmpty()) {
                System.out.println("Esta playlist não possui mídias.");
                return;
            }
            System.out.println("Selecione a mídia para remover:");
            for (int i = 0; i < playlist.getMidias().size(); i++) {
                System.out.println((i + 1) + " - " + playlist.getMidias().get(i).getTitulo());
            }
            System.out.print("Número da mídia: ");
            int indexMidia = Integer.parseInt(sc.nextLine()) - 1;

            Midias midiaRemover = playlist.getMidias().get(indexMidia);
            playlist.removerMidia(midiaRemover);
            System.out.println("Mídia '" + midiaRemover.getTitulo() + "' removida da playlist '" + playlist.getNome() + "' com sucesso.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Índice de playlist ou mídia inválido.");
        } catch (NumberFormatException e) {
            System.out.println("Erro! Entrada inválida.");
        }
    }


    public void visualizarMidiasPlaylists() {
        if (playLists.isEmpty()) {
            System.out.println("Nenhuma playlist cadastrada.");
            return;
        }
        for (Playlist playlist : playLists) {
            System.out.println("\n" + playlist.toString());
            if (playlist.getMidias().isEmpty()) {
                System.out.println("  - Playlist vazia");
            } else {
                for (Midias midia : playlist.getMidias()) {
                    System.out.println("  - " + midia.toString());

                }
            }
        }
    }
}