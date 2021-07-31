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
public class Pecas extends DAO {

    private String codigo, nome;
    private double quantidade, valor, desconto;   

    PreparedStatement pst2 = null;
    
    PreparedStatement pst = null;
    ResultSet rs = null;

    public Pecas() {
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public double getValor() {
        return valor;
    }

    public Pecas(String codigo, String nome, double quantidade, double valor, double desconto) {
        this.codigo = codigo;
        this.nome = nome;
        this.quantidade = quantidade;
        this.valor = valor;
        this.desconto = desconto;
    }


    public boolean save() {
        String sql = "insert into pecas (codigo, nome, quantidade, valor, desconto) value(?,?,?,?,?)";

        try {
            this.conectar();
            pst = this.conexao.prepareStatement(sql);
            pst.setString(1, this.codigo);
            pst.setString(2, this.nome);
            pst.setString(3, String.valueOf(this.quantidade));
            pst.setString(4, String.valueOf(this.valor));
            pst.setString(5, String.valueOf(this.desconto));
            pst.executeUpdate();
            this.desconectar();
            return true;            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Não foi possível cadastrar !\nVerifique se o código já existe em outro item !\nO código é único para cada peça!");
            System.out.println("Não foi possível inserir a peça!" + e);
            return false;
        }

    }
       

    public boolean update(String codigo, String nome, double quantidade, double valor, double desconto, int id) {
       
    String sql = "update pecas set codigo=?, nome=?, quantidade=?, desconto=?, valor=? where id=?";
        

        try {
            this.conectar();
            pst = this.conexao.prepareStatement(sql);
            pst.setString(1, codigo);
            pst.setString(2, nome);
            pst.setString(3, String.valueOf(quantidade));
            pst.setString(4, String.valueOf(valor));
            pst.setString(5, String.valueOf(desconto));
            pst.setString(6, String.valueOf(id));
            int editado = pst.executeUpdate();            
            if (editado > 0) {
               
            } else {
                throw new IdInexistenteException("Impossível editar!! \nId inexistente!!");
                
            }
            this.desconectar();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println("Não foi possível editar a peça!!" + e);
            return false;
        } 
    }

    public boolean destroy(int id) {
        String sql = "delete from pecas where id=?";

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
        String sql = "select * from pecas where nome like ?";

        TableModel tb = null;
        try {
            this.conectar();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, busca + "%");
            rs = pst.executeQuery();
            tb = DbUtils.resultSetToTableModel(rs);
            this.desconectar();

        } catch (SQLException e) {
            System.out.println("Não foi possível buscar as peças!" + e);
            tb = null;
        } finally {
            return tb;
        }

    }

    public TableModel all() {
        String sql = "select * from pecas";
        TableModel tb = null;
        try {
            this.conectar();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            tb = DbUtils.resultSetToTableModel(rs);
            this.desconectar();
        } catch (SQLException e) {
            System.out.println("Não foi possível buscar os produtos" + e);
            tb = null;
        }
        return tb;

    }

    public TableModel findByCode(String busca) {
        String sql = "select * from pecas where codigo like ?";

        TableModel tb = null;
        try {
            this.conectar();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, busca + "%");
            rs = pst.executeQuery();
            tb = DbUtils.resultSetToTableModel(rs);
            this.desconectar();

        } catch (SQLException e) {
            System.out.println("Não foi possível buscar as peças!" + e);
            tb = null;
        } finally {
            return tb;
        }

    }
    
    public double desconto(double valor){
    
    desconto = valor - (valor * 0.15);
    return desconto;
    
    }
    
    
}
