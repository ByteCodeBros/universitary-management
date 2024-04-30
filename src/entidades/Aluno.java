package entidades;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Aluno {
    private Disciplina[][] disciplinasAtuais;
    private ArrayList<Disciplina> disciplinasConcluidas;
    private String nome;
    private Integer periodo;
    private String areaInteresse;

    public Aluno(String nome, String areaInteresse, Integer periodo ) {
        this.areaInteresse = areaInteresse;
        this.nome = nome;
        this.disciplinasAtuais = new Disciplina[9][5];
        this.periodo = periodo;

        for(int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                this.disciplinasAtuais[i][j] = new Disciplina();
            }
        }

        this.disciplinasConcluidas = new ArrayList<>();
    }

    public void consultarHorario() {
        System.out.println("      HORÁRIOS      ||  SEGUNDA-FEIRA  |  TERÇA-FEIRA  |  QUARTA-FEIRA  |  QUINTA-FEIRA  |  SEXTA-FEIRA");
        System.out.println("=========================================================================================================");
        for(int i = 0; i < 9; i++){

            int horario = i*100+450;
            ArrayList<String> listaDeFormatacao = new ArrayList<>();
            listaDeFormatacao.add(Integer.toString(horario/60));
            listaDeFormatacao.add(Integer.toString(horario%60));
            listaDeFormatacao.add(Integer.toString((horario+50)/60));
            listaDeFormatacao.add(Integer.toString((horario+50)%60));

            for(int j = 0; j<4; j++){
                if(listaDeFormatacao.get(j).length()==1) {
                    listaDeFormatacao.set(j,"0" + listaDeFormatacao.get(j));
                }
            }

            System.out.print(listaDeFormatacao.get(0) + "h" + listaDeFormatacao.get(1) + "min - " +
                             listaDeFormatacao.get(2) + "h" + listaDeFormatacao.get(3) + "min ||     " +
                             disciplinasAtuais[i][0].codigoToString() + "      |    " +
                             disciplinasAtuais[i][1].codigoToString() + "     |     " +
                             disciplinasAtuais[i][2].codigoToString() + "     |     " +
                             disciplinasAtuais[i][3].codigoToString() + "     |     " +
                             disciplinasAtuais[i][4].codigoToString() + "\n");
        }
    }

    public void adicionarDisciplinaConcluida(Disciplina disciplina) {
        this.disciplinasConcluidas.add(disciplina);
    }

    public void matricularEmDisciplina(Disciplina disciplina) {

        String[] separado = disciplina.getHorario().split(" ");
        ArrayList<String> horario = new ArrayList<>(Arrays.asList(separado));
        int indiceHorario = 0;
        int indiceDia = 0;
        boolean erroMatricula = false;
        for (String stringHorario : horario) {
            if (stringHorario.charAt(0) == 'M') {
                switch (stringHorario.charAt(1)) {
                    case '1' -> indiceHorario = 0;
                    case '2' -> indiceHorario = 1;
                    case '3' -> indiceHorario = 2;
                }
            } else if (stringHorario.charAt(0) == 'T') {
                switch (stringHorario.charAt(1)) {
                    case '1' -> indiceHorario = 3;
                    case '2' -> indiceHorario = 4;
                    case '3' -> indiceHorario = 5;
                }
            } else if (stringHorario.charAt(0) == 'N') {
                switch (stringHorario.charAt(1)) {
                    case '1' -> indiceHorario = 6;
                    case '2' -> indiceHorario = 7;
                    case '3' -> indiceHorario = 8;
                }
            }

            String dia = stringHorario.substring(2, 5);

            switch (dia) {
                case "SEG" -> indiceDia = 0;
                case "TER" -> indiceDia = 1;
                case "QUA" -> indiceDia = 2;
                case "QUI" -> indiceDia = 3;
                case "SEX" -> indiceDia = 4;
            }
            if (disciplinasAtuais[indiceHorario][indiceDia].isDisciplinaNaoNula()) {
                erroMatricula = true;
            } else {
                this.disciplinasAtuais[indiceHorario][indiceDia] = disciplina;
            }
        }
        if (erroMatricula) {
            System.out.print("A disciplina '" + disciplinasAtuais[indiceHorario][indiceDia].getNome() +
                    "' está cadastrada nesse horário. Não foi possível realizar a matrícula.\n");
        }
    }

    public void atualizarDisciplina(Disciplina disciplina) {

    }

    public void removerDisciplina(Disciplina disciplina) {
        for(int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if(this.disciplinasAtuais[i][j] == disciplina){
                    this.disciplinasAtuais[i][j] = new Disciplina();
                }
            }
        }
    }

    public ArrayList<Disciplina> getDisciplinasAtuais() {

        ArrayList<Disciplina> disciplinasArrayList = new ArrayList<>();

        for(int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if(!(disciplinasAtuais[i][j]==null)&&!(disciplinasArrayList.contains(disciplinasAtuais[i][j]))) {
                    disciplinasArrayList.add(disciplinasAtuais[i][j]);
                }
            }
        }
        return disciplinasArrayList;
    }

    public void trocarDisciplina(Disciplina disciplina1, Disciplina disciplina2) {

    }

    public ArrayList<Disciplina> getDisciplinasConcluidas() {
        return disciplinasConcluidas;
    }

    public Integer getPeriodo() {
        return periodo;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Aluno: " + nome;
    }
}
