package com.mycompany.agenda_de_salas;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Agenda {

    private final ArrayList<Registro> registros;

    public Agenda() {
        this.registros = new ArrayList<>();
    }

    public boolean reservarSala(Sala sala, Funcionario funcionario, LocalDateTime horario_inicio, LocalDateTime horario_fim) throws HorarioInvalidoException {
        String uuid = UUID.randomUUID().toString();
        Registro r = new Registro(uuid, funcionario, sala, horario_inicio, horario_fim);

        if (!this.isHorarioDisponivel(sala, horario_inicio, horario_fim)) {
            throw new HorarioInvalidoException("\n[ERRO]: O HORARIO SELECIONADO ESTA INDIPONIVEL!\n\n");
        }

        registros.add(r);
        return true;
    }

    public void conferirReservas() {
        
        if(registros.size() <= 0){
            System.out.println("NENHUMA RESERVA FOI FEITA AINDA!");
            return;
        }
        System.out.println("Reservas de salas: ");
        System.out.println("#########################################");
        for (Registro registro : registros) {
            System.out.println("Identificador: " + registro.getId());
            System.out.println("Sala: " + registro.getSala().getNumero());
            System.out.println("Horario: " + registro.getHorario_inicio().toString() + " ate " + registro.getHorario_fim().toString());
            System.out.println("Reservada por: " + registro.getFuncionario().getNome());
            System.out.println("#########################################\n\n");
        }
    }

    public boolean cancelarReserva(String id) {
        boolean deletado = false;

        for (Registro registro : this.registros) {
            if (registro.getId().equalsIgnoreCase(id)) {
                System.out.println("ENCONTROU REGISTRO PARA SER DELETADO");
                this.registros.remove(registro);
                System.out.println("DELETOU O REGISTRO!");
                deletado = true;
                break;
            }
        }

        return deletado;
    }

    private boolean isHorarioDisponivel(Sala sala, LocalDateTime horario_inicio, LocalDateTime horario_fim) {

        for (Registro registro : registros) {
            if (registro.getSala().getNumero() == sala.getNumero()) {
                LocalDateTime horarioCadastradoInicio = registro.getHorario_inicio();
                LocalDateTime horarioCadastradoFim = registro.getHorario_fim();

                boolean horariosConflitam = horario_inicio.isBefore(horarioCadastradoFim) && horario_fim.isAfter(horarioCadastradoInicio);


                if (horariosConflitam) {
                    return false;
                }
            }
        }

        return true;
    }

}
