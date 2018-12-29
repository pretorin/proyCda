package com.example.portatilcda.cobroscda.Modelo;

import java.security.PublicKey;

public class ModeloSpinnerZonas {
    private  int valor;
    private String texto;

    public ModeloSpinnerZonas(){}

    public ModeloSpinnerZonas(int valor, String texto){
        setTexto(texto);
        setValor(valor);
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String toString() {
        return texto;
    }
    public int getValor() {
        return valor;
    }
}
