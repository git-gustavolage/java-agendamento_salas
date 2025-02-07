package com.mycompany.agenda_de_salas;

import java.util.ArrayList;
import java.util.Scanner;

public class ProfessorService {

    //variáveis de controle de registros (id)
    protected int professor_auto_incremet;

    protected final ArrayList<Professor> professores;
    protected final Scanner scan;
    protected final MenuService menuService;

    public ProfessorService() {
        this.professor_auto_incremet = 1;
        this.professores = new ArrayList();
        this.scan = new Scanner(System.in);
        this.menuService = new MenuService();
    }

    public void boot() {
        professores.add(new Professor("Leo", professor_auto_incremet++, 30));
        professores.add(new Professor("Bibá", professor_auto_incremet++, 45));
        professores.add(new Professor("Brenda", professor_auto_incremet++, 20));
        professores.add(new Professor("Sheylla", professor_auto_incremet++, 100));
        professores.add(new Professor("Tenilce", professor_auto_incremet++, 30));
    }

    public void menu(Agenda agenda) {
        Menu funcionarios = new Menu("Funcionarios");
        funcionarios.addOpcao("1", "Cadastrar", () -> cadastrarProfessor());
        funcionarios.addOpcao("2", "Listar", () -> listarProfessores());
        funcionarios.addOpcao("3", "Remover", () -> remover(agenda));
        menuService.exibirMenu(funcionarios);
    }

    public void listarProfessores() {
        for (Professor p : professores) {
            System.out.println("######################");
            System.out.println("Codigo identificador: " + p.getCodigo_identificador());
            System.out.println("Nome: " + p.getNome());
            System.out.println("Carga horaria semanal: " + p.getCarga_horaria());
            System.out.println("######################\n");
        }
    }

    public void cadastrarProfessor() {
        System.out.println("\nCadastrar professor: ");

        System.out.println("Digite o nome do professor: ");
        String nome = scan.nextLine();

        System.out.println("Digite a carga horaria do professor: (double)");
        double carga_horaria = scan.nextDouble();
        scan.nextLine();

        Professor p = new Professor(nome, professor_auto_incremet++, carga_horaria);
        professores.add(p);
        System.out.println("\nPROFESSOR CADASTRADO COM SUCESSO!");
        scan.reset();
    }

    public void remover(Agenda agenda) {
        boolean professor_encontrado = false;

        this.listarProfessores();
        System.out.println("Digite o codigo identificador do professor (numero inteiro)");

        int id = scan.nextInt();

        for (Professor prof : professores) {
            if (prof.getCodigo_identificador() == id) {
                professor_encontrado = true;
                boolean sucesso = professores.remove(prof);

                if (!sucesso) {
                    System.out.println("[ERRO] HOUVE UM ERRO AO REMOVER O PROFESSOR: " + prof.getNome());
                }

                agenda.removerProProfessorId(prof.getCodigo_identificador());

                System.out.println("\nPROFESSOR REMOVIDO COM SUCESSO!\n");
                break;
            }
        }

        if (!professor_encontrado) {
            System.out.println("[ERRO] NENHUM PROFESSOR COM ESTE CODIGO FOI ENCONTRADO");
        }
    }

    public Professor selecionarProfessor() {
        if (professores.size() <= 0) {
            System.out.println("Nenhum professor foi cadastrado ainda!");
            cadastrarProfessor();
        }

        while (true) {
            System.out.println("Selecione o professor: (digite o codigo identificador)");
            listarProfessores();
            int codigo = scan.nextInt();
            scan.nextLine();
            Professor professor = null;

            for (Professor p : professores) {
                if (p.getCodigo_identificador() == codigo) {
                    professor = p;
                }
            }
            if (professor == null) {
                System.out.println("\n[ERRO] PROFESSOR NAO ENCONTRADO!\n");
            } else {
                return professor;
            }
        }
    }

    public Professor getProfessor(int index) {
        return professores.get(index);
    }

    public int getQuantidadeProfessores() {
        return professores.size();
    }

}
