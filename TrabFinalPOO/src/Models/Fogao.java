/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Controllers.IdInexistenteException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Mateus Bernini
 */
public class Fogao extends Eletrodomestico {
    private int qtdBocas;
    private double desconto;
    
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public Fogao(){}

    public Fogao(String marca, String modelo, double quantidade, double valor, int qtdBocas, double desconto) {
        super(marca, modelo, quantidade, valor);
        this.qtdBocas = qtdBocas;
        this.desconto = desconto;
    }
    
    public boolean save() {
        String sql = "insert into fogao (marca, modelo, quantidade, valor, qtdbocas, desconto) value(?,?,?,?,?,?)";

        try {
            this.conectar();
            pst = this.conexao.prepareStatement(sql);
            pst.setString(1, this.marca);
            pst.setString(2, this.modelo);
            pst.setString(3, String.valueOf(this.quantidade));
            pst.setString(4, String.valueOf(this.valor));
            pst.setString(5, String.valueOf(this.qtdBocas));
            pst.setString(6, String.valueOf(this.desconto));
            pst.executeUpdate();

            this.desconectar();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Não foi possível cadastrar !!\nVerifique se o modelo já foi cadastrado em outro item !!\nO modelo é único para cada item !!");
            System.out.println("Não foi possível inserir os dados do fogão!" + e);
            return false;
        } 
    }
    
    public boolean update(String marca, String modelo, double quantidade, double valor, int qtdBocas, double desconto, int id) {
        String sql = "update fogao set marca=?, modelo=?, quantidade=?, valor=?, qtdbocas=?, desconto=? where id=?";

        try {
            this.conectar();
            pst = this.conexao.prepareStatement(sql);
            pst.setString(1, marca);
            pst.setString(2, modelo);
            pst.setString(3, String.valueOf(quantidade));
            pst.setString(4, String.valueOf(valor));
            pst.setString(5, String.valueOf(qtdBocas));
            pst.setString(6, String.valueOf(desconto));
            pst.setString(7, String.valueOf(id));
            int editado = pst.executeUpdate();
            if (editado > 0) {
            } else {
                throw new IdInexistenteException("Impossível editar!! \n Id inexistente!!");
            }
            this.desconectar();
            return true;
        } catch (SQLException e) {
            System.out.println("Não foi possível editar os dados do fogão!!" + e);
            return false;
        }
    }
         

    public TableModel findByMarca(String busca) {
        String sql = "select * from fogao where marca like ?";

        TableModel tb = null;
        try {
            this.conectar();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, busca + "%");
            rs = pst.executeQuery();
            tb = DbUtils.resultSetToTableModel(rs);
            this.desconectar();

        } catch (SQLException e) {
            System.out.println("Não foi possível buscar os dados!!" + e);
            tb = null;
        } finally {
            return tb;
        }
    }

    public TableModel findByCode(String busca) {
        String sql = "select * from fogao where modelo like ?";

        TableModel tb = null;
        try {
            this.conectar();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, busca + "%");
            rs = pst.executeQuery();
            tb = DbUtils.resultSetToTableModel(rs);
            this.desconectar();

        } catch (SQLException e) {
            System.out.println("Não foi possível buscar os dados!!" + e);
            tb = null;
        } finally {
            return tb;
        }
    }

    public TableModel all() {
        String sql = "select * from fogao";
        TableModel tb = null;
        try {
            this.conectar();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            tb = DbUtils.resultSetToTableModel(rs);
            this.desconectar();
        } catch (SQLException e) {
            System.out.println("Não foi possível buscar os dados!!" + e);
            tb = null;
        }
        return tb;
    }

    public boolean destroy(int id) {
        String sql = "delete from fogao where id=?";

        try {
            this.conectar();
            pst = this.conexao.prepareStatement(sql);
            pst.setString(1, String.valueOf(id));
            int deletado = pst.executeUpdate();
            if (deletado > 0) {
            } else {
                throw new IdInexistenteException("Impossível excluir!! \nId inexistente!!");
            }
            this.desconectar();
            return true;
        } catch (SQLException e) {
            System.out.println("Não foi possível excluir!" + e);
            return false;
        }
    }

    @Override
    public void DicasDeUso() {
        JOptionPane.showMessageDialog(null, "Instale o fogão em um local protegido de correntes de ar.\n"
                + "Quando a água começar a ferver, reduza a chama.\n"
                + "Regule o fogão quando as chamas se tornarem amareladas e emitirem ruídos.\n"
                + "Caso não consiga acender o forno de imediato , feche o registro, deixe o forno aberto e aguarde alguns minutos antes de tentar de novo.");
    }

    @Override
    public double Desconto15(double valor) {
        desconto = valor - (valor * 0.15);
        return desconto;
    }
    
}
