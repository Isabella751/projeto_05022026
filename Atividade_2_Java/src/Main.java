import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.Period;

public class Main {

    private static final ArrayList<Aluno> listaAlunos = new ArrayList<>();
    private static final ArrayList<Turma> listaTurmas = new ArrayList<>();

    public static void main(String[] args) {
        menuPrincipal();
    }

    public static void menuPrincipal() {
        System.out.println("\n==== Secretaria ====");
        System.out.println("1 - Alunos");
        System.out.println("2 - Turmas");
        System.out.println("3 - Sair");
        String opcao = Leitura.dados("Digite a opção desejada: ");
        switch (opcao) {
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
                System.out.println("Opção inválida! Tente novamente");
                menuPrincipal();
        }

    }

    private static void menuTurmas() {
        System.out.println("\n==== Turmas ====");
        System.out.println("1 - Listar Turmas");
        System.out.println("2 - Cadastrar Turma");
        System.out.println("3 - Atualizar Turma");
        System.out.println("4 - Excluir Turma");
        System.out.println("5 - Voltar ao menu principal");
        String opcao = Leitura.dados("Digite a opção desejada: ");
        switch (opcao) {
            case "1":
                listarTurmas();
                menuTurmas();
                break;
            case "2":
                cadastrarTurma();
                menuTurmas();
                break;
            case "3":
                atualizarTurma();
                menuTurmas();
                break;
            case "4":
                excluirTurma();
                menuTurmas();
                break;
            case "5":
                menuPrincipal();
                break;
            default:
                System.out.println("Opção inválida! Tente novamente");
                menuTurmas();
        }
    }

    private static void menuAlunos() {
        System.out.println("\n==== Alunos ====");
        System.out.println("1 - Listar Alunos");
        System.out.println("2 - Cadastrar Aluno");
        System.out.println("3 - Atualizar Aluno");
        System.out.println("4 - Excluir Aluno");
        System.out.println("5 - Voltar ao menu principal");
        String opcao = Leitura.dados("Digite a opção desejada: ");
        switch (opcao) {
            case "1":
                listarAlunos();
                menuAlunos();
                break;
            case "2":
                cadastrarAluno();
                menuAlunos();
                break;
            case "3":
                atualizarAluno();
                menuAlunos();
                break;
            case "4":
                excluirAluno();
                menuAlunos();
                break;
            case "5":
                menuPrincipal();
                break;
            default:
                System.out.println("Opção inválida! Tente novamente");
                menuAlunos();
        }
    }

    private static void excluirTurma() {
        if(isVazio(listaTurmas)) {
            System.out.println("Não há turmas cadastradas");
            return;
        }

        listarTurmasIndiceSigla();

        int idExcluir = validaIdTurma();

        if (confirmaExclusao()){
//            listaTurmas.remove(idExcluir);
            listaTurmas.get(idExcluir).setAtivo(false);
            System.out.println("Turma excluída com sucesso!");
        }
    }

    private static boolean isVazio(ArrayList<Turma> listaTurmas) {
        if (listaTurmas.isEmpty()) return true;

        for (Turma turma : listaTurmas){
            if (turma.isAtivo()) return false;
        }

        return true;
    }

    private static boolean confirmaExclusao() {
        while (true) {
            String confirma = Leitura.dados("Você tem certeza? (S/N): ").toUpperCase();
            switch (confirma) {
                case "S":
                    return true;
                case "N":
                    return false;
                default:
                    System.out.println("Opção inválida, digite S para sim ou N para não!");
                    break;
            }
        }
    }

    private static int validarItemLista(String opcao) {
        if (opcao.isBlank()) return -1;

        int opcaoNumero = -1;

        try{
            opcaoNumero = Integer.parseInt(opcao);
        } catch (NumberFormatException e) {
            return -1;
        }

        int indiceLista = opcaoNumero-1;
        return indiceLista >= 0 && listaTurmas.size() > indiceLista ? indiceLista : -1;
    }

    private static void listarTurmasIndiceSigla() {
        System.out.println("\nLista das Turmas:");
        for (int i=0;i<listaTurmas.size();i++){
            if (listaTurmas.get(i).isAtivo())
                System.out.printf("\n%d - %s",i+1, listaTurmas.get(i).getSigla());
        }
    }

    private static void atualizarTurma() {
        if(isVazio(listaTurmas)) {
            System.out.println("Não há turmas cadastradas");
            return;
        }

        listarTurmasIndiceSigla();

        int idAtualizar = validaIdTurma();

        System.out.printf("O período atual é: %s", listaTurmas.get(idAtualizar).getPeriodo());
        atualizarParcial("Período", idAtualizar);

        System.out.printf("O curso atual é: %s", listaTurmas.get(idAtualizar).getCurso());
        atualizarParcial("Curso", idAtualizar);

        System.out.printf("A sigla atual é: %s", listaTurmas.get(idAtualizar).getSigla());
        atualizarParcial("Sigla", idAtualizar);

//        System.out.println("O período atual é: " + listaTurmas.get(idAtualizar).getPeriodo());
//        System.out.printf("O período atual é: %s", listaTurmas.get(idAtualizar).getPeriodo());
//        atualizarPeriodo(idAtualizar);
//
//        System.out.printf("O curso atual é: %s", listaTurmas.get(idAtualizar).getCurso());
//        atualizarCurso(idAtualizar);
//
//        System.out.printf("A sigla atual é: %s", listaTurmas.get(idAtualizar).getSigla());
//        atualizarSigla(idAtualizar);
    }

    private static void atualizarParcial(String atributo, int idAtualizar){
        boolean rodarNovamente = true;
        while (rodarNovamente) {
            String opcao = Leitura.dados("\nDeseja modificar "+ atributo +" ? (S/N): ").toUpperCase();
            switch (opcao) {
                case "S":
                    switch (atributo){
                        case "Período":
                            Periodo periodo = validarPeriodo();
                            listaTurmas.get(idAtualizar).setPeriodo(periodo);
                            break;
                        case "Curso":
                            String curso = validarCurso();
                            listaTurmas.get(idAtualizar).setCurso(curso);
                            break;
                        case "Sigla":
                            String sigla = validarSigla();
                            listaTurmas.get(idAtualizar).setSigla(sigla);
                            break;
                        case "Nome":
                            String nome = validarNome();
                            listaAlunos.get(idAtualizar).setNome(nome);
                            break;
                        case "Data de nascimento":
                            LocalDate dataNascimento = validarDataNascimento();
                            listaAlunos.get(idAtualizar).setDataNascimento(dataNascimento);
                            break;
                        case "Turma":
                            Turma turma = validarTurma();
                            listaAlunos.get(idAtualizar).setTurma(turma);
                            break;
                    }
                    System.out.println(atributo + " atualizado com sucesso!");
                    rodarNovamente = false;
                    break;
                case "N":
                    rodarNovamente = false;
                    break;
                default:
                    System.out.println("Opção inválida! Escolha S para SIM ou N para NÃO");
            }
        }
    }

    private static String validarSigla() {
        String sigla = Leitura.dados("Digite a sigla: ").toUpperCase();
        while(!validarSigla(sigla)){
            System.out.println("Sigla inválida! Precisa conter texto e não pode ser repetida");
            sigla = Leitura.dados("Digite a sigla: ").toUpperCase();
        }
        return sigla;
    }

    private static String validarCurso() {
        String curso = Leitura.dados("Digite o curso: ");
        while(!isCharacter(curso)) {
            System.out.println("Nome de curso inválido! Não use números ou caracteres especiais, por favor");
            curso = Leitura.dados("Digite o curso: ");
        }
        return curso;
    }

    private static void atualizarPeriodo(int idAtualizar) {
        boolean rodarNovamente = true;
        while (rodarNovamente) {
            String opcaoPeriodo = Leitura.dados("\nDeseja modificar o período? (S/N): ").toUpperCase();
            switch (opcaoPeriodo) {
                case "S":
                    Periodo periodo = validarPeriodo();
                    listaTurmas.get(idAtualizar).setPeriodo(periodo);
                    System.out.println("Período atualizado com sucesso para " + periodo);
                    rodarNovamente = false;
                    break;
                case "N":
                    rodarNovamente = false;
                    break;
                default:
                    System.out.println("Opção inválida! Escolha S para SIM ou N para NÃO");
            }
        }

//        while (true) {
//            String opcaoPeriodo = Leitura.dados("\nDeseja modificar o período? (S/N): ").toUpperCase();
//            switch (opcaoPeriodo) {
//                case "S":
////                Periodo periodo = validarPeriodo();
////                listaTurmas.get(idAtualizar).setPeriodo(periodo);
//                    listaTurmas.get(idAtualizar).setPeriodo(validarPeriodo());
//                    break;
//                case "N":
//                    break;
//                default:
//                    System.out.println("Opção inválida! Escolha S para SIM ou N para NÃO");
//                    continue;
//            }
//            break;
//        }
    }

    private static int validaIdTurma() {
        String opcao = Leitura.dados("\nDigite o número da turma desejada: ");
        int opcaoValida = -1;
        int opcaoUsuario = -1;
        while (opcaoValida==-1){
            opcaoUsuario = validarItemLista(opcao);

            if (opcaoUsuario==-1) {
                System.out.println("Opção inválida! Digite novamente: ");
                opcao = Leitura.dados("Digite o número da turma desejada: ");
            } else {
                opcaoValida = opcaoUsuario;
            }
        }
        return opcaoValida;
    }

    private static void cadastrarTurma() {
        Periodo periodo = validarPeriodo();
        String curso = validarCurso();
        String sigla = validarSigla();

        Turma turma = new Turma(curso, sigla, periodo);
        listaTurmas.add(turma);
        System.out.println("Turma cadastrada com sucesso!");
    }

    private static boolean validarSigla(String sigla) {
        if (sigla.isBlank()) return false;

        for (Turma turma : listaTurmas){
            if (turma.getSigla().equals(sigla)){
                return false;
            }
        }
        return true;
    }

    private static boolean isCharacter(String texto) {
        return texto != null && !texto.isBlank() && texto.matches("[a-zA-ZÀ-ÿ ]+");
    }

    private static Periodo validarPeriodo() {
        String opcaoPeriodo = Leitura.dados("""
                Digite o número do período escolhido:
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
                System.out.println("Opção inválida, digite novamente");
                return validarPeriodo();
        }
    }

    private static void listarTurmas() {
        if(isVazio(listaTurmas)) {
            System.out.println("Não há turmas cadastradas");
            return;
        }
        for(Turma t : listaTurmas){
            if (t.isAtivo())
                System.out.println(t);
        }
    }

    private static void excluirAluno() {

    }

    private static void atualizarAluno() {
        if(isVazioAluno(listaAlunos)) {
            System.out.println("Não há alunos cadastrados.");
            return;
        }

        // int idAtualizarAluno = validaIdAluno();

        listarTurmasIndiceSigla();

        int idTurma = validaIdTurmaDoAluno();
        Turma turmaSelecionada = listaTurmas.get(idTurma);

        int contador = 1;

        for (int i = 0; i < listaAlunos.size(); i++) {
            if (listaAlunos.get(i).getTurma().equals(turmaSelecionada)) {
                System.out.println(contador + " - " + listaAlunos.get(i).getNome());
                contador++;
            }
        }

        int idAtualizar = validaIdAluno();

        System.out.printf("O nome do aluno é: %s", listaAlunos.get(idAtualizar).getNome());
        atualizarParcial("Nome", idAtualizar);

        System.out.printf("A data de nascimento do aluno é: %s", listaAlunos.get(idAtualizar).getDataNascimento());
        atualizarParcial("Data de nascimento", idAtualizar);

        System.out.printf("A turma do aluno é: %s", listaAlunos.get(idAtualizar).getTurma());
        atualizarParcial("Turma", idAtualizar);

//        System.out.printf("A turma do aluno é: %s", listaAlunos.getTurma().setTurma());
//        atualizarParcial("turma", idAtualizar);
//
//        System.out.printf("O nome do aluno é: %s", listaAlunos.get(idAtualizar).getNome());
//        atualizarParcial("nome", idAtualizar);
//
//        System.out.printf("A data de nascimento do aluno é: %s", listaAlunos.get(idAtualizar).getDataNascimento());
//        atualizarParcial("data de nascimento", idAtualizar);
    }

    private static int validaIdAluno() {
        String opcao = Leitura.dados("\nDigite o id do aluno que deseja atualizar: ");
        int opcaoValida = -1;
        int opcaoUsuario = -1;
        while (opcaoValida==-1){
            opcaoUsuario = validarItemLista(opcao);

            if (opcaoUsuario==-1) {
                System.out.println("Opção inválida! Digite novamente: ");
                opcao = Leitura.dados("\nDigite o id do aluno que deseja atualizar: ");
            } else {
                opcaoValida = opcaoUsuario;
            }
        }
        return opcaoValida;
    }


    private static int validaIdTurmaDoAluno() {

        String opcao = Leitura.dados("\nDigite o id da turma do aluno: ");
        int opcaoValida = -1;
        int opcaoUsuario = -1;
        while (opcaoValida==-1){
            opcaoUsuario = validarItemLista(opcao);

            if (opcaoUsuario==-1) {
                System.out.println("Opção inválida! Digite novamente: ");
                opcao = Leitura.dados("\nDigite o id da turma do aluno: ");
            } else {
                opcaoValida = opcaoUsuario;
            }
        }
        return opcaoValida;
    }

//    private static int validaIdAluno() {
//
//        String opcao = Leitura.dados("\nDigite o número da turma desejada: ");
//        int opcaoValida = -1;
//        int opcaoUsuario = -1;
//        while (opcaoValida==-1){
//            opcaoUsuario = validarItemLista(opcao);
//
//            if (opcaoUsuario==-1) {
//                System.out.println("Opção inválida! Digite novamente: ");
//                opcao = Leitura.dados("Digite o número da turma desejada: ");
//            } else {
//                opcaoValida = opcaoUsuario;
//            }
//        }
//        return opcaoValida;
//    }

    private static void cadastrarAluno() {
        String nome = validarNome();
        LocalDate dataNascimento = validarDataNascimento();
        Turma turma = validarTurma();

        Aluno aluno = new Aluno(nome, dataNascimento, turma);
        listaAlunos.add(aluno);
        System.out.println("Aluno cadastrado com sucesso.");
    }

    private static Turma validarTurma() {

        if(isVazio(listaTurmas)){
            System.out.println("Não há turmas cadastradas.");
            menuAlunos();
            return null;
        }

        listarTurmasIndiceSigla();
        int idTurma = validaIdTurma();
        return listaTurmas.get(idTurma);
    }

    private static LocalDate validarDataNascimento() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while(true) {
            String data = Leitura.dados("Digite a data de nascimento (dd/MM/aaaa): ");

            try {
                LocalDate dataNascimento = LocalDate.parse(data, formatter);
                int idade = Period.between(dataNascimento, LocalDate.now()).getYears();

                if (idade < 14) {
                    System.out.println("Aluno precisa ter pelo menos 14 anos.");
                    menuAlunos();
                }
                else if (idade > 130) {
                    System.out.println("Idade inválida. Máximo permitido: 130 anos.");
                    menuAlunos();
                }

                return dataNascimento;

            } catch (DateTimeParseException e) {
                System.out.println("Data inválida! Use o formato dd/MM/aaaa.");
            }
        }

    }

    private static String validarNome() {

        String nome = Leitura.dados("Digite seu nome completo:  ");
        while(!isCharacter(nome)) {
            System.out.println("Nome inválido! Não use números ou caracteres especiais, por favor");
            nome = Leitura.dados("Digite seu nome completo:  ");
        }
        return nome;
    }



    private static void listarAlunos() {
        if(isVazioAluno(listaAlunos)){
            System.out.println("Não há alunos cadastrados.");
            return;
        }
        for(Aluno a : listaAlunos){
            if(a.isAtivoAluno())
                System.out.println(a);
        }
    }

    private static boolean isVazioAluno(ArrayList<Aluno> listaAlunos) {
        if(listaAlunos.isEmpty()) return true;
        for(Aluno aluno : listaAlunos){
            if(aluno.isAtivoAluno()) return false;
        }
        return true;
    }
}