package com.mycompany.agenda_de_salas;

import java.util.LinkedHashMap;
import java.util.Map;

public class Menu {
    private final String titulo;
    private final Map<String, MenuOpcao> opcoes;

    public Menu(String titulo) {
        this.titulo = titulo;
        this.opcoes = new LinkedHashMap<>();
    }

    public void addOpcao(String chave, String descricao, Runnable acao) {
        opcoes.put(chave, new MenuOpcao(descricao, acao));
    }

    public String getTitulo() {
        return titulo;
    }

    public Map<String, MenuOpcao> getOpcoes() {
        return opcoes;
    }
}
