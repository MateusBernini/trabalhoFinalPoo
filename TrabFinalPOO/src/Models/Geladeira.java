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
public class Geladeira extends Eletrodomestico implements DescontoEspecial{

    private String tipo_degelo;
    private double desconto;

    PreparedStatement pst = null;
    ResultSet rs = null;
    PreparedStatement pst2 = null;

    public Geladeira() {
    }

    public Geladeira(String marca, String modelo, double quantidade, double valor, String tipo_degelo, double desconto) {
        super(marca, modelo, quantidade, valor);
        this.tipo_degelo = tipo_degelo;
        this.desconto = desconto;

    }

    public boolean save() {
        String sql = "insert into geladeira (marca, modelo, quantidade, valor, tipodegelo, desconto) value(?,?,?,?,?,?)";

        try {
            this.conectar();
            pst = this.conexao.prepareStatement(sql);
            pst.setString(1, this.marca);
            pst.setString(2, this.modelo);
            pst.setString(3, String.valueOf(this.quantidade));
            pst.setString(4, String.valueOf(this.valor));
            pst.setString(5, this.tipo_degelo);
            pst.setString(6, String.valueOf(this.desconto));
            pst.executeUpdate();
            this.desconectar();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Não foi possível cadastrar !!\nVerifique se o modelo já foi cadastrado em outro item !!\nO modelo é único para cada item !!");
            System.out.println("Não foi possível inserir os dados da geladeira!" + e);
            return false;
        } 
    }

    public boolean update(String marca, String modelo, double quantidade, double valor, String tipodegelo, double desconto, int id) {
        String sql = "update geladeira set marca=?, modelo=?, quantidade=?, valor=?, tipodegelo=?, desconto=? where id=?";

        try {
            this.conectar();
            pst = this.conexao.prepareStatement(sql);
            pst.setString(1, marca);
            pst.setString(2, modelo);
            pst.setString(3, String.valueOf(quantidade));
            pst.setString(4, String.valueOf(valor));
            pst.setString(5, tipodegelo);
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
            System.out.println("Não foi possível editar a peça!" + e);
            return false;
        }
    }

    public boolean destroy(int id) {
        String sql = "delete from geladeira where id=?";

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
        String sql = "select * from geladeira where marca like ?";

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
        String sql = "select * from geladeira";
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
        String sql = "select * from geladeira where modelo like ?";

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
        JOptionPane.showMessageDialog(null, "Deixe a geladeira sempre ligada.\n"
                + "Não utilize a parte traseira para secar roupas.\n"
                + "Espere os alimentos esfriarem para guardá-los.\n"
                + "Verifique o estado das borrachas de vedação.\n"
                + "Descongele o aparelho regularmente.\n"
                + "Não forre as prateleiras.\n"
                + "Fique atento ao consumo de energia dos aparelhos.");
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
