package com.mycompany.agenda_de_salas;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Agenda_de_salas {

    protected static Scanner scan = new Scanner(System.in);
    private static final MenuService menuService = new MenuService();
    private static final ProfessorService professorService = new ProfessorService();

    protected static Agenda agenda = new Agenda();
    protected static ArrayList<Sala> salas = new ArrayList();


    public static void main(String[] args) {
        boot();

        Menu menuPrincipal = new Menu("Inicio");
        menuPrincipal.addOpcao("1", "Reservas", () -> menuReservas());
        menuPrincipal.addOpcao("2", "Salas", () -> menuSalas());
        menuPrincipal.addOpcao("3", "Professores", () -> professorService.menu());

        menuService.exibirMenu(menuPrincipal);
        System.out.println("Saindo do programa...");
    }

    protected static void menuReservas() {
        Menu reservas = new Menu("Reservas");
        reservas.addOpcao("1", "Cadastrar", () -> cadastrarReserva());
        reservas.addOpcao("2", "Listar", () -> agenda.conferirReservas());
        reservas.addOpcao("3", "Cancelar", () -> cancelarReserva());
        menuService.exibirMenu(reservas);
    }

    protected static void menuSalas() {
        Menu salasM = new Menu("Salas");
        salasM.addOpcao("1", "Cadastrar", () -> cadastrarSala());
        salasM.addOpcao("2", "Listar", () -> listarSalas());
        menuService.exibirMenu(salasM);
    }

    protected static void cadastrarReserva() {
        Sala sala = selecionarSala();
        Professor professor = professorService.selecionarProfessor();

        LocalDate dataAtual = LocalDate.now();
        int ano = Integer.parseInt(dataAtual.toString().split("-")[0]);
        int mes = Integer.parseInt(dataAtual.toString().split("-")[1]);

        System.out.println("Selecione o dia: ");
        int dia = scan.nextInt();
        System.out.println("Selecione a hora de inicio da reseverva: : ");
        int hora = scan.nextInt();
        System.out.println("Selecione o minuto de inicio da reseverva: : ");
        int minuto = scan.nextInt();
        System.out.println("Selecione o horario do fim da reseverva: ");
        int horaF = scan.nextInt();
        System.out.println("Selecione o minuto de fim da reseverva: : ");
        int minutoF = scan.nextInt();

        try {
            LocalDateTime hora_inicio = LocalDateTime.of(ano, mes, dia, hora, minuto);
            LocalDateTime hora_fim = LocalDateTime.of(ano, mes, dia, horaF, minutoF);

            agenda.reservarSala(sala, professor, hora_inicio, hora_fim);
            System.out.println("\nRESERVA EFETUADA COM SUCESSO!\n");
        } catch (HorarioInvalidoException e) {
            System.out.println(e.getMessage());
            System.out.println("TENTE NOVAMENTE: ");
            cadastrarReserva();
        }
    }

    protected static void cancelarReserva() {
        agenda.conferirReservas();

        System.out.println("Qual reserva voce deseja cancelar? (digite o identificador)");
        String id = scan.next();

        System.out.println(id);

        try {
            boolean sucesso = agenda.cancelarReserva(id);

            if (sucesso) {
                System.out.println("Reserva cancelada com sucesso!");
            } else {
                System.err.println("[ERRO] HOUVE UM ERRO AO CANCELAR A RESERVA!");
            }
        } catch (Exception e) {
            System.out.println("[ERRO] " + e.getMessage());
        }
    }

    protected static Sala selecionarSala() {
        if (salas.size() <= 0) {
            System.out.println("Nenhuma sala foi cadastrada ainda!");
            cadastrarSala();
        }

        while (true) {
            System.out.println("Selecione a sala: (digite o numero da sala)");
            listarSalas();
            int numero_sala = scan.nextInt();
            scan.nextLine();
            Sala sala = null;

            for (Sala s : salas) {
                if (s.getNumero() == numero_sala) {
                    sala = s;
                }
            }

            if (sala == null) {
                System.err.println("\n[ERRO] SALA NAO ENCONTRADA!\n");
            } else {
                return sala;
            }
        }
    }

    //salas   
    protected static void cadastrarSala() {
        System.out.println("\nCadastrar Sala:");

        while (true) {
            try {
                System.out.println("Digite o bloco da sala: ");
                String bloco = scan.next();

                System.out.println("Digite o andar da sala: (numero inteiro)");
                int piso = scan.nextInt();
                scan.nextLine();

                System.out.println("Digite o numero da sala: (numero inteiro)");
                int numero = scan.nextInt();
                scan.nextLine();

                Sala sala = new Sala(bloco, piso, numero);
                salas.add(sala);
                System.out.println("\nSALA CADASTRADA COM SUCESSO!\n");
                break;
            } catch (InputMismatchException e) {
                System.err.println("[ERRO] Entrada invalida. Tente novamente.");
                scan.nextLine();
            }
        }
    }

    protected static void listarSalas() {
        System.out.println("Salas disponiveis: ");

        for (Sala sala : salas) {
            System.out.println("######################");
            System.out.println("Sala: " + sala.getNumero());
            System.out.println("Bloco: " + sala.getBloco());
            System.out.println("Piso: " + sala.getPiso());
            System.out.println("######################\n");
        }
    }

    protected static void boot() {
        
        professorService.boot();


        salas.add(new Sala("A", 1, 1));
        salas.add(new Sala("A", 1, 2));
        salas.add(new Sala("A", 1, 3));
        salas.add(new Sala("B", 1, 4));
        salas.add(new Sala("B", 1, 5));
        salas.add(new Sala("B", 1, 6));
        salas.add(new Sala("C", 2, 7));
        salas.add(new Sala("C", 2, 8));
        salas.add(new Sala("C", 2, 9));

        int ano = 2024, mes = 2, dia = 6, hora = 8, minuto = 0, duracao = 2;

        for (int i = 0; i < salas.size(); i++) {
            Sala sala = salas.get(i);
            Professor professor = professorService.getProfessor(i % professorService.getQuantidadeProfessores());

            LocalDateTime horaInicio = LocalDateTime.of(ano, mes, dia + i, hora, minuto);
            LocalDateTime horaFim = horaInicio.plusHours(duracao);

            try {
                agenda.reservarSala(sala, professor, horaInicio, horaFim);

            } catch (HorarioInvalidoException e) {
                System.out.println("erro");
                System.exit(dia);
            }
        }

    }
}
