package com.mycompany.agenda_de_salas;

public class Funcionario {
    
    protected String nome;
    protected int codigo_identificador;

    public Funcionario(String nome, int codigo_identificador) {
        this.nome = nome;
        this.codigo_identificador = codigo_identificador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigo_identificador() {
        return codigo_identificador;
    }

    public void setCodigo_identificador(int codigo_identificador) {
        this.codigo_identificador = codigo_identificador;
    }
    
    
}
