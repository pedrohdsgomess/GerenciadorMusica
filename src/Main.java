import exceptions.CodigoInvalidoException;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Catalogo catalogo = new Catalogo();
        String nomepessoa;
        String email;


        System.out.println("=== Bem-vindo ao Mini Spotify ===");
        while (true) {
            System.out.print("Digite seu nome: ");
            nomepessoa = scan.nextLine();
            System.out.print("Digite seu e-mail: ");
            email = scan.nextLine();
            if (nomepessoa.trim().isEmpty() || email.trim().isEmpty()) {
                System.out.println("\nNome e e-mail são obrigatórios.");
            } else if (!email.contains("@") || !email.contains(".com")) {
                System.out.println("\nE-mail inválido! Confira se contém '@' e '.com'");
            } else {
                System.out.println("Cadastro realizado com sucesso!");
                System.out.println("Olá, seja bem-vindo, " + nomepessoa + "!");
                break;
            }
        }


        int opcao = -1;

        do {
            System.out.println("\nSelecione uma das opções abaixo:");
            System.out.println("1 - Listagem de mídias");
            System.out.println("2 - Adicionar uma nova mídia");
            System.out.println("3 - Excluir mídia");
            System.out.println("4 - Exibir quantidade total de mídias");
            System.out.println("5 - Pesquisar uma mídia");
            System.out.println("6 - Criar playlist");
            System.out.println("7 - Excluir playlist");
            System.out.println("8 - Exibir playlists e mídias");
            System.out.println("9 - Sair");
            System.out.print("Digite sua opção: ");

            try {
                opcao = scan.nextInt();

                switch (opcao) {
                    case 1:


                        catalogo.listarMidias();
                        break;
                    case 2:

                        catalogo.adicionarMidia();
                        break;

                    case 3:

                        catalogo.excluirMidia();
                        break;
                    case 4:

                        catalogo.quantidadeTotal();
                        break;
                    case 5:

                        catalogo.pesquisarMidia();
                        break;
                    case 6:

                        catalogo.criarPlaylist();
                        break;
                    case 7:

                        catalogo.excluirPlaylist();
                        break;
                    case 8:
                        catalogo.adicionarMidiaPlaylist();
                        break;
                    case 9:
                        catalogo.excluirMidiaPlaylist();
                    case 10:
                        catalogo.visualizarMidiasPlaylists();
                        break;
                    case 11:
                        System.out.println("Saindo do sistema...");

                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (InputMismatchException | CodigoInvalidoException e) {
                System.out.println("Por favor, digite um número válido!");
                scan.nextLine();
            }

        } while (opcao != 9);


    }
}