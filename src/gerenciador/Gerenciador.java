package gerenciador;
import entidades.*;

import entidades.Disciplina;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Gerenciador {
    private Aluno alunoLogado;
    private ArrayList<Aluno> alunos;
    private ArrayList<Disciplina> disciplinas;

    public Gerenciador() {
        alunos = new ArrayList<>();
        disciplinas = new ArrayList<>();
    }

    public void preCadastro() {
        try {
            BufferedReader leitor = new BufferedReader(new FileReader("gradeCurricular.tsv"));

            String linha;

            while ((linha = leitor.readLine()) != null) {
                //System.out.println(linha);
                String[] separado = linha.split("\t");

                if (separado.length > 7) {
                    Disciplina d = new Disciplina(separado[0], separado[1], separado[2],
                            separado[3], separado[4], separado[5], separado[6], separado[7]);

                    disciplinas.add(d);
                }
            }
            leitor.close();
            System.out.println("Pre-cadastro realizado com sucesso!\n");
        } catch (
                FileNotFoundException e) {
            System.out.println("Pre-cadastro não realizado");
        } catch (
                IOException e) {
            System.out.println("Não foi possível ler o arquivo");
        }
    }

    public void cadastrarAluno() {
        Scanner entrada = new Scanner(System.in);

        System.out.println("Informe o nome: ");
        String nome = entrada.nextLine();

        System.out.println("\nInforme a area de interesse: ");
        System.out.println(
                """
                         1-Engenharia de software\s
                         2-Inteligência artificial\s
                         3-Desenvolvimento de Jogos\s
                         4-Sistemas de Informação\s
                         5-Engenharia de Redes\s
                         6-Segurança de Informação\s
                        """);

        String areaInteresse = "";
        int escolhaDoUsuario = entrada.nextInt();

        boolean loop = true;
        while (loop) {
            switch (escolhaDoUsuario) {
                case 1 -> {
                    areaInteresse = "Engenharia de software";
                    loop = false;
                }
                case 2 -> {
                    areaInteresse = "Inteligência artificial";
                    loop = false;
                }
                case 3 -> {
                    areaInteresse = "Desenvolvimento de Jogos";
                    loop = false;
                }
                case 4 -> {
                    areaInteresse = "Sistemas de Informação";
                    loop = false;
                }
                case 5 -> {
                    areaInteresse = "Engenharia de Redes";
                    loop = false;
                }
                case 6 -> {
                    areaInteresse = "Segurança de Informação";
                    loop = false;
                }
                default -> System.out.println("\nInsira um valor válido.");
            }
        }

        System.out.println("\nInforme o período: ");
        int periodo = entrada.nextInt();
        boolean definirDisciplinas = false;

        Aluno aluno = new Aluno(nome, areaInteresse, periodo);

        if (periodo != 1) {
            System.out.println("Desejas definir as disciplinas dos períodos anteriores como concluídas?(1-sim/2-não) \n ");

            int i = entrada.nextInt();
            if (i == 1) {
                definirDisciplinas = true;
            }
        } else {
            for (Disciplina disciplina : disciplinas) {
                if (disciplina.getPeriodo() == 1) {
                    aluno.matricularEmDisciplina(disciplina);
                }
            }
        }

        if (definirDisciplinas) {
            for (Disciplina disciplinaPreCadastrada : disciplinas) {
                if (disciplinaPreCadastrada.getPeriodo() < aluno.getPeriodo()) {
                    aluno.adicionarDisciplinaConcluida(disciplinaPreCadastrada);
                }
            }
        }

        this.alunos.add(aluno);
        System.out.println("\nAluno '" + aluno.getNome() + "' cadastrado com sucesso!\n ");
    }

    public void fazerLogin() {
        if (alunos.isEmpty()) {
            System.out.println("Não há alunos cadastrados");
        } else {
            Scanner entrada = new Scanner(System.in);
            System.out.println("Escolha o aluno para fazer login: ");
            int i = 1;

            for (Aluno aluno : this.alunos) {
                System.out.println(" " + i + " - " + aluno.toString());
                i++;
            }

            int resposta = entrada.nextInt();
            this.alunoLogado = alunos.get(resposta - 1);
            System.out.println("\nLogin do aluno '" + this.alunoLogado.getNome() + "' realizado com sucesso!");
        }
    }

    public void consultarDisciplinasAtuais() {
        if (alunos.isEmpty() || alunoLogado == null) {
            System.out.println("Não há aluno logado");
        } else {
            this.alunoLogado.consultarHorario();
        }
    }

    public void atualizarDisciplina() {
        if (alunos.isEmpty() || alunoLogado == null) {
            System.out.println("Não há aluno logado");
        } else {
            System.out.println("Feature incompleta");
        }
    }

    public void consultarDisciplinasAconselhadas() {
        if (alunos.isEmpty() || alunoLogado == null) {
            System.out.println("Não há aluno logado");
        } else {
            System.out.println("Feature incompleta");
        }
    }

    public void desmatricularEmDisciplina(){
        Scanner entrada = new Scanner(System.in);
        System.out.println("Escolha uma disciplina:\n");

        int i=1;
        for(Disciplina disciplina : this.alunoLogado.getDisciplinasAtuais()){
            System.out.println(i +" - "+disciplina.getNome());
            i++;
        }

        int disciplina = entrada.nextInt();
        this.alunoLogado.removerDisciplina(this.alunoLogado.getDisciplinasAtuais().get(disciplina-1));

    }

    public void trocarDisciplina() {

        if (alunos.isEmpty() || alunoLogado == null) {
            System.out.println("Não há aluno logado");
        } else {
            System.out.println("Feature incompleta");
        }
    }

    public void matricularEmDisciplina() {
        if (alunos.isEmpty() || alunoLogado == null) {
            System.out.println("Não há aluno logado");
        } else {
            Scanner entrada = new Scanner(System.in);
            int i=1;
            System.out.println("Escolha uma disciplina:\n");
            for (Disciplina disciplina1 : disciplinas) {
                if(!(this.alunoLogado.getDisciplinasConcluidas().contains(disciplina1))
                        && !(this.alunoLogado.getDisciplinasAtuais().contains(disciplina1))) {
                    System.out.println(i + " - " + disciplina1.getNome()
                            + " | Carga Horária: " + disciplina1.getCargaHoraria()
                            + " | Período: " + disciplina1.getPeriodo());
                    i++;
                }
            }
            int disciplina = entrada.nextInt();
            alunoLogado.matricularEmDisciplina(disciplinas.get(disciplina-1));
        }
    }
}
