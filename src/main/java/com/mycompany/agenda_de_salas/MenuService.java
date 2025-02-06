package com.mycompany.agenda_de_salas;

import java.util.Scanner;
import java.util.Stack;

public class MenuService {

    private final Scanner scan = new Scanner(System.in);

    public void exibirMenu(Menu menu) {
        Stack<Menu> menuStack = new Stack<>();
        menuStack.push(menu);

        while (!menuStack.isEmpty()) {
            Menu currentMenu = menuStack.peek();
            try {
                System.out.println("########## " + currentMenu.getTitulo() + " ##########");
                currentMenu.getOpcoes().forEach((chave, opcao)
                        -> System.out.println(chave + " - " + opcao.getDescricao()));
                System.out.println("0 - Voltar");
                System.out.println("############################\n");

                String escolha = scan.nextLine();

                if (escolha.equals("0")) {
                    menuStack.pop();
                    if (menuStack.isEmpty()) {
                        break;
                    }
                } else {
                    MenuOpcao opcaoEscolhida = currentMenu.getOpcoes().get(escolha);
                    if (opcaoEscolhida != null) {
                        opcaoEscolhida.getAcao().run();
                    } else {
                        System.err.println("[ERRO] OPCAO INVALIDA! TENTE NOVAMENTE.");
                    }
                }
            } catch (Exception e) {
                System.err.println("[ERRO] ERRO AO PROCESSAR ENTRADA. TENTE NOVAMENTE.");
                scan.nextLine();
            }
        }
    }

    public void exibir(Menu menu) {
        try {
            System.out.println("########## " + menu.getTitulo() + " ##########");
            menu.getOpcoes().forEach((chave, opcao)
                    -> System.out.println(chave + " - " + opcao.getDescricao()));
            System.out.println("0 - Voltar");
            System.out.println("############################\n");

            String escolha = scan.nextLine();

            if (!escolha.equals("0")) {
                MenuOpcao opcaoEscolhida = menu.getOpcoes().get(escolha);
                if (opcaoEscolhida != null) {
                    opcaoEscolhida.getAcao().run();
                } else {
                    System.out.println("[ERRO] OPCAO INVALIDA! TENTE NOVAMENTE.");
                }
            }
        } catch (Exception e) {
            System.out.println("[ERRO] ERRO AO PROCESSAR ENTRADA. TENTE NOVAMENTE.");
            scan.nextLine();
        }
    }
}
