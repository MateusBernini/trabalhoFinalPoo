/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.MaquinaLavar;
import javax.swing.table.TableModel;

/**
 *
 * @author Mateus Bernini
 */
public class MaquinaController {

    public boolean create(String marca, String modelo, double quantidade, double valor, double kgs, double desconto) throws ValorNuloException {
        if (marca.isEmpty() || modelo.isEmpty() || valor == 0 || kgs == 0) {
            throw new ValorNuloException("Não foi possível cadastrar !!\nHá campos sem preencher!!");
        } else {

            MaquinaLavar m = new MaquinaLavar(marca, modelo, quantidade, valor, kgs, desconto);
            return m.save();
        }
    }

    public boolean edit(String marca, String modelo, double quantidade, double valor, double kgs, double desconto, int id) throws ValorNuloException {
        if (marca.isEmpty() || modelo.isEmpty() || valor == 0 || kgs == 0) {
            throw new ValorNuloException("Não foi possível cadastrar !!\nHá campos sem preencher!!");
        } else {
            return new MaquinaLavar().update(marca, modelo, quantidade, valor, kgs, desconto, id);
        }
    }

    public TableModel maquinaMarca(String busca) {
        return new MaquinaLavar().findByMarca(busca);
    }

    public TableModel maquinaModelo(String busca) {
        return new MaquinaLavar().findByCode(busca);
    }

    public TableModel tblCompleta() {
        return new MaquinaLavar().all();
    }

    public boolean delete(int id) {
        return new MaquinaLavar().destroy(id);

    }

    public void dicas() {
        new MaquinaLavar().DicasDeUso();
    }

    public double desconto15(double valor) {
        return new MaquinaLavar().Desconto15(valor);
    }

    public double desconto30(double valor) {
        return new MaquinaLavar().DescontoEspecial30(valor);
    }

    public double desconto50(double valor) {
        return new MaquinaLavar().DescontoEspecial50(valor);
    }

}
