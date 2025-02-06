package com.mycompany.agenda_de_salas;

import com.mycompany.agenda_de_salas.Funcionario;
import java.time.LocalDateTime;

public class Registro {

    private String id;
    private Funcionario funcionario;
    private Sala sala;
    private LocalDateTime horario_inicio;
    private LocalDateTime horario_fim;

    public Registro(String id, Funcionario funcionario, Sala sala, LocalDateTime horario_inicio, LocalDateTime horario_fim) {
        this.id = id;
        this.funcionario = funcionario;
        this.sala = sala;
        this.horario_inicio = horario_inicio;
        this.horario_fim = horario_fim;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public LocalDateTime getHorario_inicio() {
        return horario_inicio;
    }

    public void setHorario_inicio(LocalDateTime horario_inicio) {
        this.horario_inicio = horario_inicio;
    }

    public LocalDateTime getHorario_fim() {
        return horario_fim;
    }

    public void setHorario_fim(LocalDateTime horario_fim) {
        this.horario_fim = horario_fim;
    }

}
