package view;

import gerenciador.Gerenciador;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Gerenciador gerenciador = new Gerenciador();
        gerenciador.preCadastro();

        System.out.println("\nBEM-VINDO AO ORGANIZADOR DE CURSOS E METAS");

        boolean loopMain = true;
        while(loopMain) {
            System.out.println("\nEscolha uma das opções a seguir:\n");

            System.out.println(
                      "1-Cadastrar aluno;\n"
                    + "2-Fazer login;\n"
                    + "3-Consultar disciplinas atuais;\n"
                    + "4-Atualizar status de disciplina;\n"
                    + "5-Consultar disciplinas aconselhadas;\n"
                    + "6-Matricular-se em disciplina;\n"
                    + "7-Desmatricular-se em disciplina;\n"
                    + "8-Trocar disciplina;\n"
                    + "9-Sair\n");

            Scanner entrada = new Scanner(System.in);
            int escolhaDoUsuario = entrada.nextInt();

            switch (escolhaDoUsuario) {
                case 1 -> gerenciador.cadastrarAluno();
                case 2 -> gerenciador.fazerLogin();
                case 3 -> gerenciador.consultarDisciplinasAtuais();
                case 4 -> gerenciador.atualizarDisciplina();
                case 5 -> gerenciador.consultarDisciplinasAconselhadas();
                case 6 -> gerenciador.matricularEmDisciplina();
                case 7 -> gerenciador.desmatricularEmDisciplina();
                case 8 -> gerenciador.trocarDisciplina();
                case 9 -> loopMain = false;
                default -> System.out.println("Insira um valor válido");
            }
        }
    }
}
