package entidades;
import java.util.ArrayList;
import java.util.Arrays;

public class Disciplina {
    private String codigo;
    private String nome;
    private Integer periodo;
    private String horario;
    private String cargaHoraria;
    private String tipo;
    private String area;
    private boolean disciplinaNaoNula;
    private ArrayList<String> preRequisito = new ArrayList<>();

    public Disciplina(){}

    public Disciplina(String codigo, String nome, String periodo, String horario, String cargaHoraria,
                      String preRequisitoString, String area, String tipo) {
        this.codigo = codigo;
        this.nome = nome;
        this.periodo = Integer.parseInt(periodo);
        this.horario = horario;
        this.cargaHoraria = cargaHoraria;
        this.tipo = tipo;
        this.area = area;
        this.disciplinaNaoNula = true;
        this.preRequisito = new ArrayList<>();
        String[] separado = preRequisitoString.split(" ");
        this.preRequisito.addAll(Arrays.asList(separado));
    }

    public Integer getPeriodo() {
        return periodo;
    }

    public String getNome(){
        return this.nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getHorario() {
        return horario;
    }

    public boolean isDisciplinaNaoNula() {
        return disciplinaNaoNula;
    }

    public String getCargaHoraria() {
        return cargaHoraria;
    }

    public ArrayList<String> getPreRequisito() {
        return preRequisito;
    }

    public String codigoToString() {
        if (!disciplinaNaoNula) {
            return "------";
        } else {
            return this.codigo;
        }
    }

    @Override
    public String toString() {
        if (this.preRequisito == null) {
            return "Disciplina{" +
                    "Código: " + codigo +
                    "; Disciplina: " + nome +
                    "; Período: " + periodo +
                    "; Horário: " + horario +
                    "; Carga Horária: " + cargaHoraria + '}';
        }else {
            return "Disciplina{" +
                    "Código: " + codigo +
                    "; Disciplina: " + nome +
                    "; Período: " + periodo +
                    "; Horário: " + horario +
                    "; Carga Horária: " + cargaHoraria +
                    "; Pré-Requisito(s): " + preRequisito +
                    '}';
        }
    }
}
