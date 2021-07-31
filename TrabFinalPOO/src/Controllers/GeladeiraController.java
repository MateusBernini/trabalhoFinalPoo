/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Geladeira;
import javax.swing.table.TableModel;

/**
 *
 * @author Mateus Bernini
 */
public class GeladeiraController {

    public boolean create(String marca, String modelo, double quantidade, double valor, String tipo_degelo, double desconto) throws ValorNuloException {
        if (marca.isEmpty() || modelo.isEmpty() || valor == 0 || tipo_degelo.isEmpty()) {
            throw new ValorNuloException("Não foi possível cadastrar !!\nHá campos sem preencher!!");
        } else {
            Geladeira g = new Geladeira(marca, modelo, quantidade, valor, tipo_degelo, desconto);
            return g.save();
        }
    }

    public boolean edit(String marca, String modelo, double quantidade, double valor, String tipo_degelo, double desconto, int id) throws ValorNuloException {
        if (marca.isEmpty() || modelo.isEmpty() || valor == 0 || tipo_degelo.isEmpty()) {
            throw new ValorNuloException("Não foi possível cadastrar !!\nHá campos sem preencher!!");
        } else {
            return new Geladeira().update(marca, modelo, quantidade, valor, tipo_degelo, desconto, id);
        }
    }

    public TableModel geladeiraMarca(String busca) {
        return new Geladeira().findByMarca(busca);
    }

    public TableModel geladeiraModelo(String busca) {
        return new Geladeira().findByCode(busca);
    }

    public TableModel tblCompleta() {
        return new Geladeira().all();
    }

    public boolean delete(int id) {
        return new Geladeira().destroy(id);

    }

    public void dicas() {
        new Geladeira().DicasDeUso();
    }

    public double desconto15(double valor) {
        return new Geladeira().Desconto15(valor);
    }

    public double desconto30(double valor) {
        return new Geladeira().DescontoEspecial30(valor);
    }

    public double desconto50(double valor) {
        return new Geladeira().DescontoEspecial50(valor);
    }

}
