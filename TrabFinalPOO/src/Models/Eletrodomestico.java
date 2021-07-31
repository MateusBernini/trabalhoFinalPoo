/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import javax.swing.table.TableModel;

/**
 *
 * @author Mateus Bernini
 */
public abstract class Eletrodomestico extends DAO{
    protected String marca, modelo;
    protected double quantidade ,valor;
    
    
    public Eletrodomestico(){}

    
    public Eletrodomestico(String marca, String modelo, double quantidade, double valor) {
        this.marca = marca;
        this.modelo = modelo;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public abstract void DicasDeUso();
    
    public abstract double Desconto15(double valor);
           
}
