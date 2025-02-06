package com.mycompany.agenda_de_salas;

public class MenuOpcao {
    private final String descricao;
    private final Runnable acao;

    public MenuOpcao(String descricao, Runnable acao) {
        this.descricao = descricao;
        this.acao = acao;
    }

    public String getDescricao() {
        return descricao;
    }

    public Runnable getAcao() {
        return acao;
    }
}
