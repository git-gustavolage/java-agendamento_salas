package com.mycompany.agenda_de_salas;

public class Sala {
    
    private int numero;
    private String bloco;
    private int piso;

    public Sala(String bloco, int piso, int numero) {
        this.bloco = bloco;
        this.piso = piso;
        this.numero = numero;
    }

    public String getBloco() {
        return bloco;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
}
