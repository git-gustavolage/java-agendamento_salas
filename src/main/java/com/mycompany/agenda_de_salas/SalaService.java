package com.mycompany.agenda_de_salas;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SalaService {
    protected final ArrayList<Sala> salas;
    protected final Scanner scan;
    protected final MenuService menuService;

    public SalaService() {
        this.salas = new ArrayList();
        this.scan = new Scanner(System.in);
        this.menuService = new MenuService();
    }

    public void boot() {
        salas.add(new Sala("A", 1, 1));
        salas.add(new Sala("A", 1, 2));
        salas.add(new Sala("A", 1, 3));
        salas.add(new Sala("B", 1, 4));
        salas.add(new Sala("B", 1, 5));
        salas.add(new Sala("B", 1, 6));
        salas.add(new Sala("C", 2, 7));
        salas.add(new Sala("C", 2, 8));
        salas.add(new Sala("C", 2, 9));
    }

    public void menu() {
        Menu menu = new Menu("Salas");
        menu.addOpcao("1", "Cadastrar", () -> cadastrarSala());
        menu.addOpcao("2", "Listar", () -> listarSalas());
        this.menuService.exibirMenu(menu);
    }

    public void cadastrarSala() {
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

    public void listarSalas() {
        System.out.println("Salas disponiveis: ");

        for (Sala sala : salas) {
            System.out.println("######################");
            System.out.println("Sala: " + sala.getNumero());
            System.out.println("Bloco: " + sala.getBloco());
            System.out.println("Piso: " + sala.getPiso());
            System.out.println("######################\n");
        }
    }

    public Sala selecionarSala() {
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

    public Sala getSala(int index) {
        return salas.get(index);
    }

    public int getQuantidadeSalas() {
        return this.salas.size();
    }

}
