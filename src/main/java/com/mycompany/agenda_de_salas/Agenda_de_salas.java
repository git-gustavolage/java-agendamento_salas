package com.mycompany.agenda_de_salas;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.Scanner;

public class Agenda_de_salas {

    protected static Scanner scan = new Scanner(System.in);
    private static final MenuService menuService = new MenuService();
    private static final ProfessorService professorService = new ProfessorService();
    private static final SalaService salaService = new SalaService();

    protected static Agenda agenda = new Agenda();

    
    public static void main(String[] args) {
        boot();

        Menu menuPrincipal = new Menu("Inicio");
        menuPrincipal.addOpcao("1", "Reservas", () -> menuReservas());
        menuPrincipal.addOpcao("2", "Salas", () -> salaService.menu());
        menuPrincipal.addOpcao("3", "Professores", () -> professorService.menu(agenda));

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

    
    protected static void cadastrarReserva() {
        Sala sala = salaService.selecionarSala();
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

    
    private static void boot() {
        
        professorService.boot();
        salaService.boot();

        int ano = 2025, mes = 2, dia = 6, hora = 8, minuto = 0, duracao = 2;

        for (int i = 0; i < salaService.getQuantidadeSalas(); i++) {
            Sala sala = salaService.getSala(i);
            Professor professor = professorService.getProfessor(i % professorService.getQuantidadeProfessores());

            LocalDateTime horaInicio = LocalDateTime.of(ano, mes, dia + i, hora, minuto);
            LocalDateTime horaFim = horaInicio.plusHours(duracao);

            try {
                agenda.reservarSala(sala, professor, horaInicio, horaFim);

            } catch (HorarioInvalidoException e) {
                System.out.println("[BOOT ERROR]");
                System.exit(dia);
            }
        }

    }
}
