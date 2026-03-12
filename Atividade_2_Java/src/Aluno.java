import java.time.LocalDate;

public class Aluno {
    @Override
    public String toString() {
        return "Aluno{" +
                "nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", turma=" + turma +
                '}';
    }

    private String nome;

    public Aluno(String nome, LocalDate dataNascimento, Turma turma) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.turma = turma;
        this.Ativo = true;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public boolean isAtivoAluno() {
        return Ativo;
    }

    public void setAtivoAluno(boolean ativo) {
        Ativo = ativo;
    }

    private LocalDate dataNascimento;
    private Turma turma;
    private boolean Ativo;
}

