/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Pecas;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

/**
 *
 * @author Mateus Bernini
 */
public class PecasController {

    public boolean create(String codigo, String nome, double quantidade, double valor, double desconto) throws ValorNuloException {
        if (codigo.isEmpty() || (nome.isEmpty()) || valor == 0) {
            throw new ValorNuloException("Não foi possível cadastrar !!\nHá campos sem preencher!!");
        } else {
            Pecas p = new Pecas(codigo, nome, quantidade, valor, desconto);
            return p.save();
        }
    }

    public boolean edit(String codigo, String nome, double quantidade, double valor, double desconto, int id) throws ValorNuloException {
        //return new Pecas().update(codigo, nome, quantidade, valor, id);
        if (codigo.isEmpty() || (nome.isEmpty()) || valor == 0) {
            throw new ValorNuloException("Não foi possível cadastrar !!\nHá campos sem preencher!!");
        } else {
            return new Pecas().update(codigo, nome, quantidade, valor, desconto, id);
        }
    }

    public boolean delete(int id) {
        return new Pecas().destroy(id);

    }

    public TableModel pecas(String busca) {
        return new Pecas().findByMarca(busca);

    }

    public TableModel pecas() {
        return new Pecas().all();
    }

    public TableModel pecas2(String busca) {
        return new Pecas().findByCode(busca);
    }

    public double desconto(double valor) {
        return new Pecas().desconto(valor);
    }

}
