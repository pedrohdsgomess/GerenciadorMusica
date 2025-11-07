import exceptions.CodigoInvalidoExecption;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Catalogo {

    private String email;
    private ArrayList<Midias> midias = new ArrayList<>();
    private ArrayList<Playlist> playLists = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    // 1 - Listar todas as mídias
    public void listarMidias() {
        if (midias.isEmpty()) {
            System.out.println("Erro! Nenhuma mídia cadastrada.");
        } else {
            System.out.println("Lista de mídias:");
            for (Midias m : midias) {
                System.out.println(m.toString());
            }
        }
    }

    // 2 - Adicionar uma nova mídia
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
            System.out.print("Artista: ");
            String artista = sc.nextLine();
            System.out.print("Duração (em minutos): ");
            double duracao = Double.parseDouble(sc.nextLine());

            if (duracao <= 0) {
                System.out.println("Duração não pode ser igual ou menor que zero!");
                return;
            }

            Midias novaMidia = null;

            switch (tipoMidia) {
                case 1:
                    System.out.println("Selecione o gênero:");
                    int i = 1;
                    for (Generos g : Generos.values()) {
                        System.out.println(i + " - " + g.getDescricao());
                        i++;
                    }
                    Generos genero = selecionarGenero();
                    if (genero == null) return;
                    novaMidia = new Musica(titulo, artista, duracao, genero);
                    break;

                case 2:
                    novaMidia = new Podcast(titulo, artista, duracao, null);
                    break;

                case 3:
                    novaMidia = new Audiobook(titulo, artista, duracao, null);
                    break;
            }

            midias.add(novaMidia);
            System.out.println("Mídia adicionada com sucesso!");

        } catch (NumberFormatException e) {
            System.out.println("Erro! Formato de número inválido. Tente novamente.");
        }
    }


    public Generos selecionarGenero() {
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

    // 3 - Excluir uma mídia
    public void excluirMidia() {
        if (midias.isEmpty()) {
            System.out.println("Nenhuma mídia para excluir.");
            return;
        }
        System.out.println("Mídias cadastradas:");
        for (int i = 0; i < midias.size(); i++) {
            System.out.println((i + 1) + " - " + midias.get(i).getTitulo());
        }
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

    // 4 - Pesquisar mídia
    public void pesquisarMidia() throws CodigoInvalidoExecption {
        System.out.println("Como deseja pesquisar a mídia?");
        System.out.println("1 - Por Título");
        System.out.println("2 - Por Artista");
        System.out.println("3 - Por Gênero");
        System.out.print("Opção: ");
        String opcao = sc.nextLine();
        List<Midias> resultados = new ArrayList<>();

        switch (opcao) {
            case "1":
                System.out.print("Digite o título: ");
                String titulo = sc.nextLine();
                for (Midias midia : midias) {
                    if (midia.getTitulo().equalsIgnoreCase(titulo)) {
                        resultados.add(midia);
                    }
                }
                break;
            case "2":
                System.out.print("Digite o nome do artista: ");
                String artista = sc.nextLine();
                for (Midias midia : midias) {
                    if (midia.getArtista().equalsIgnoreCase(artista)) {
                        resultados.add(midia);
                    }
                }
                break;
            case "3":
                System.out.print("Digite o gênero: ");
                String generoString = sc.nextLine();
                Generos[] genero = Generos.values();
                if (genero != null) {
                    for (Midias midia : midias) {
                        if (midia.getGenero().equals(genero)) {
                            resultados.add(midia);
                        }
                    }
                } else {
                    System.out.println("Gênero inválido.");
                }
                break;
            default:
                System.out.println("Opção de pesquisa inválida.");
                return;
        }

        if (resultados.isEmpty()) {
            throw new CodigoInvalidoExecption("Nenhuma mídia encontrada com o critério informado.");
        } else {
            System.out.println("Resultados da pesquisa:");
            for (Midias m : resultados) {
                System.out.println(m.toString());
            }
        }
    }

    // 5 - Exibir quantidade total
    public void quantidadeTotal() {
        System.out.println("Quantidade total de mídias: " + midias.size());
        System.out.println("Quantidade total de playlists: " + playLists.size());
    }

    // 6 - Criar playlist
    public void criarPlaylist() {
        System.out.print("Digite o nome da nova playlist: ");
        String nomePlaylist = sc.nextLine();
        if (nomePlaylist.isEmpty()) {
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

    // 7 - Excluir playlist
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

    // 8 - Adicionar mídia em playlist
    public void adicionarMidiaPlaylist() {
        if (midias.isEmpty() || playLists.isEmpty()) {
            System.out.println("É necessário ter mídias e playlists cadastradas.");
            return;
        }

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

        try {
            Playlist playlist = playLists.get(indexPlaylist);
            Midias midia = midias.get(indexMidia);
            if (playlist.getMidias().contains(midia)) {
                System.out.println("Mídia já está na playlist.");
                return;
            }
            playlist.adicionarMidia(midia);
            System.out.println("Mídia '" + midia.getTitulo() + "' adicionada à playlist '" + playlist.getNome() + "' com sucesso.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Índice inválido.");
        }
    }

    // 9 - Excluir mídia de uma playlist
    public void excluirMidiaPlaylist() {
        if (playLists.isEmpty()) {
            System.out.println("Nenhuma playlist para gerenciar.");
            return;
        }
        System.out.println("Selecione a playlist:");
        for (int i = 0; i < playLists.size(); i++) {
            System.out.println((i + 1) + " - " + playLists.get(i).getNome());
        }
        System.out.print("Número da playlist: ");
        int indexPlaylist = Integer.parseInt(sc.nextLine()) - 1;

        try {
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

    // 10 - Visualizar playlists
    public void visualizarMidiasPlaylists() {
        if (playLists.isEmpty()) {
            System.out.println("Nenhuma playlist cadastrada.");
            return;
        }
        for (Playlist playlist : playLists) {
            System.out.println("\nPlaylist: " + playlist.getNome());
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
