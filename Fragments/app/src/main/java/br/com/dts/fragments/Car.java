package br.com.dts.fragments;

import java.io.Serializable;

/**
 * Created by diegosouza on 7/28/16.
 */
public class Car implements Serializable{
    String marca;
    String modelo;
    String placa;
    String cor;
    int ano;

    public Car(String marca, String modelo, String placa, String cor, int ano) {
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.cor = cor;
        this.ano = ano;
    }

    public Car(){
        
    }
}
