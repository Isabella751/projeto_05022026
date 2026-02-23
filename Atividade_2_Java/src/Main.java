import java.util.ArrayList;

public class Main {

    private static ArrayList <Aluno> listaAlunos = new ArrayList<>();
    private static ArrayList <Turma> listaTurmas = new ArrayList<>();

    public static void main(String[] args) {

        menu();
    }

    public static void menu(){

        System.out.println("==== Secretaria ====");
        System.out.println("1 - Alunos");
        System.out.println("2 - Turmas");
        System.out.println("3 - Sair");
        String opcao = Leitura.dados("Digite a opção desejada:  ");
        switch (opcao){
            case "1":
                menuAlunos();
                break;
            case "2":
                menuTurmas();
                break;
            case "3":
                System.out.println("Até breve...");
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida! Tente novamente.");
                menu();
        }
    }

    private static void menuTurmas() {

        System.out.println("==== Turmas ====");
        System.out.println("1 - Listar Turmas");
        System.out.println("2 - Cadastrar Turma");
        System.out.println("3 - Atualizar Turma");
        System.out.println("4 - Excluir Turma");
        System.out.println("5 - Voltar ao menu principal");
        String opcao = Leitura.dados("Digite a opção desejada:  ");
        switch (opcao){
            case "1":
                listarTurmas();
                menuTurmas();
                break;
            case "2":
                cadastrarTurma();
                break;
            case "3":
                atualizarTurma();
                break;
            case "4":
                excluirTurma();
                break;
            case "5":
                menu();
                break;
            default:
                System.out.println("Opção inválida! Tente novamente.");
                menuTurmas();
        }
    }

    private static void menuAlunos() {

        System.out.println("==== Alunos ====");
        System.out.println("1 - Listar Alunos");
        System.out.println("2 - Cadastrar Aluno");
        System.out.println("3 - Atualizar Aluno");
        System.out.println("4 - Excluir Aluno");
        System.out.println("5 - Voltar ao menu principal");
        String opcao = Leitura.dados("Digite a opção desejada:  ");
        switch (opcao){
            case "1":
                listarAlunos();
                menuAlunos();
                break;
            case "2":
                cadastrarAluno();
                break;
            case "3":
                atualizarAluno();
                break;
            case "4":
                excluirAluno();
                break;
            case "5":
                menu();
                break;
            default:
                System.out.println("Opção inválida! Tente novamente.");
                menuAlunos();
        }
    }

    private static void listarAlunos() {

        if(listaAlunos.isEmpty()){
            System.out.println("Não há alunos cadastrados.");
            return;
        }
        for(Aluno a: listaAlunos){
            System.out.println(a);
        }
    }

    private static void cadastrarAluno() {
    }

    private static void atualizarAluno() {
    }

    private static void excluirAluno() {
    }

    private static void listarTurmas() {

        if(listaTurmas.isEmpty()) {
            System.out.println("Não há turmas cadastradas.");
            return;
        }
        for(Turma t: listaTurmas){
            System.out.println(t);
        }
    }

    private static void cadastrarTurma() {

        Periodo periodo = validarPeriodo();

        String curso = Leitura.dados("Digite o curso:  ");
        while(!isCharacter(curso)) {
            System.out.println("Nome de curso inválido! Não use números ou caracteres especiais, por favor.");
            curso = Leitura.dados("Digite o curso:  ");
        }

        String sigla = Leitura.dados("Digite a sigla:  ");
        while(!validarSigla(sigla)){
            System.out.println("Sigla inválida! Precisa conter texto e não pode ser repetida.");
            sigla = Leitura.dados("Digite a sigla:  ");
        }

        Turma turma = new Turma(curso, sigla, periodo);
        listaTurmas.add(turma);
        System.out.println("Turma cadastrada com sucesso!");
        menuTurmas();
    }

    private static boolean validarSigla(String sigla) {

        if(sigla.isBlank()) return false;

        for (Turma turma : listaTurmas) {
            if (turma.getSigla().equals(sigla)) return false;
        }

        return true;
    }

    private static boolean isCharacter(String texto) {

        String textoSemNumeros = texto.replaceAll("\\d", "");
        return !texto.isBlank() && texto.equals(textoSemNumeros);
    }


    private static Periodo validarPeriodo() {

        String opcaoPeriodo = Leitura.dados("""
                Escolha o período:
                1 - Matutino
                2 - Vespertino
                3 - Noturno
                4 - Integral""");
        switch (opcaoPeriodo){
            case "1":
                return Periodo.MATUTINO;
            case "2":
                return Periodo.VESPERTINO;
            case "3":
                return Periodo.NOTURNO;
            case "4":
                return Periodo.INTEGRAL;
            default:
                System.out.println("Opção inválida. Digite novamente!");
                return validarPeriodo();
        }
    }

    private static void atualizarTurma() {
    }

    private static void excluirTurma() {
    }
}