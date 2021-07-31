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
public class MaquinaLavar extends Eletrodomestico implements DescontoEspecial{
    private double kgs, desconto;
    
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    
    public MaquinaLavar(){}

    public MaquinaLavar(String marca, String modelo, double quantidade, double valor, double kgs, double desconto) {
        super(marca, modelo, quantidade, valor);
        this.kgs = kgs;
        this.desconto = desconto;
    }
    
    public boolean save() {
        String sql = "insert into maquinalavar (marca, modelo, quantidade, valor, kgs, desconto) value(?,?,?,?,?,?)";

        try {
            this.conectar();
            pst = this.conexao.prepareStatement(sql);
            pst.setString(1, this.marca);
            pst.setString(2, this.modelo);
            pst.setString(3, String.valueOf(this.quantidade));
            pst.setString(4, String.valueOf(this.valor));
            pst.setString(5, String.valueOf(this.kgs));
            pst.setString(6, String.valueOf(this.desconto));
            pst.executeUpdate();

            this.desconectar();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Não foi possível cadastrar !!\nVerifique se o modelo já foi cadastrado em outro item !!\nO modelo é único para cada item !!");
            System.out.println("Não foi possível inserir os dados da máquina de lavar!" + e);
            return false;
        } 
    }
    
    public boolean update(String marca, String modelo, double quantidade, double valor, double kgs, double desconto, int id) {
        String sql = "update maquinalavar set marca=?, modelo=?, quantidade=?, valor=?, desconto=?, kgs=? where id=?";

        try {
            this.conectar();
            pst = this.conexao.prepareStatement(sql);
            pst.setString(1, marca);
            pst.setString(2, modelo);
            pst.setString(3, String.valueOf(quantidade));
            pst.setString(4, String.valueOf(valor));
            pst.setString(5, String.valueOf(this.kgs));
            pst.setString(6, String.valueOf(this.desconto));
            pst.setString(7, String.valueOf(id));
            int editado = pst.executeUpdate();
            if (editado > 0) {
            } else {
                throw new IdInexistenteException("Impossível editar!! \n Id inexistente!!");
            }
            this.desconectar();
            return true;
        } catch (SQLException e) {
            System.out.println("Não foi possível editar a máquina de lavar!!" + e);
            return false;
        }
    }
    
    public boolean destroy(int id) {
        String sql = "delete from maquinalavar where id=?";

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
    
    public TableModel findByMarca(String busca) {
        String sql = "select * from maquinalavar where marca like ?";

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
        String sql = "select * from maquinalavar";
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
    
    public TableModel findByCode(String busca) {
        String sql = "select * from maquinalavar where modelo like ?";

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

    @Override
    public void DicasDeUso() {
        JOptionPane.showMessageDialog(null, "Não exagere na quantidade de sabão, coloque o necessário para cada ciclo.\n"
                + "Cheque os bolsos e zíperes antes de colocar na máquina.\n"
                + "Instale a máquina em um local protegido de variações climáticas.\n"
                + "Desligue a máquina da tomada quando não estiver utilizando.");
    }

    @Override
    public double Desconto15(double valor) {
        desconto = valor - (valor * 0.15);
        return desconto;
    }

    @Override
    public double DescontoEspecial30(double valor) {
        desconto = valor - (valor * 0.3);
        return desconto;
    }

    @Override
    public double DescontoEspecial50(double valor) {
        desconto = valor - (valor * 0.5);
        return desconto;
    }
  
}
