import java.util.Scanner;

public class Usuario {
    private String nome;
    private String email;
    private String senha;

    Scanner sc = new Scanner(System.in);

    public Usuario(String email, String nome, String senha) {
        this.email = email;
        this.nome = nome;
        this.senha = senha;
    }

    public void cadastrarUsuario() {
        try {
            System.out.println("Bem-vindo(a) ao ClashMusic\n");
            System.out.println("Faça seu cadastro:");

            System.out.print("Digite o seu nome: ");
            this.nome = sc.nextLine();

            System.out.print("Digite o seu email: ");
            this.email = sc.nextLine();

            System.out.print("Digite sua senha: ");
            this.senha = sc.nextLine();

            System.out.println("\nCadastro realizado com sucesso!");
        }

        catch (Exception e) {
            System.out.println("Não foi possível concluir o cadastro, tente novamente");

        }

    }


    public void mostrarDados() {
        System.out.println("\nNome: " + nome);
        System.out.println("Email: " + email);
        System.out.println("Senha:" + senha);
    }

