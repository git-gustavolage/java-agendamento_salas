package com.mycompany.agenda_de_salas;

public class Professor extends Funcionario{
    private double carga_horaria;

    public Professor(String nome, int codigo, double carga_horaria) {
        super(nome, codigo);
        this.carga_horaria = carga_horaria;
    }

    public double getCarga_horaria() {
        return carga_horaria;
    }

    public void setCarga_horaria(double carga_horaria) {
        this.carga_horaria = carga_horaria;
    }
    
}
