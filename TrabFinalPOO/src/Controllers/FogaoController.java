/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Fogao;
import javax.swing.table.TableModel;

/**
 *
 * @author Mateus Bernini
 */
public class FogaoController {

    public boolean create(String marca, String modelo, double quantidade, double valor, int qtdBocas, double desconto) throws ValorNuloException {
        if (marca.isEmpty() || modelo.isEmpty() || valor == 0 || qtdBocas == 0) {
            throw new ValorNuloException("Não foi possível cadastrar !!\nHá campos sem preencher!!");
        } else {
            Fogao f = new Fogao(marca, modelo, quantidade, valor, qtdBocas, desconto);
            return f.save();
        }
    }

    public boolean edit(String marca, String modelo, double quantidade, double valor, int qtdBocas, double desconto, int id) throws ValorNuloException {
        if (marca.isEmpty() || modelo.isEmpty() || valor == 0 || qtdBocas == 0) {
            throw new ValorNuloException("Não foi possível cadastrar !!\nHá campos sem preencher!!");
        } else {
            return new Fogao().update(marca, modelo, quantidade, valor, qtdBocas, desconto, id);
        }
    }

    public TableModel fogaoMarca(String busca) {
        return new Fogao().findByMarca(busca);
    }

    public TableModel fogaoModelo(String busca) {
        return new Fogao().findByCode(busca);
    }

    public TableModel tblCompleta() {
        return new Fogao().all();
    }

    public boolean delete(int id) {
        return new Fogao().destroy(id);

    }

    public void dicas() {
        new Fogao().DicasDeUso();
    }

    public double desconto15(double valor) {
        return new Fogao().Desconto15(valor);
    }

}
