/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Mateus Bernini
 */
public class DAO {

    Connection conexao;
    String url, user, password;

    public DAO() {
        url = "jdbc:mysql://localhost:3306/bdpoo";
        user = "root";
        password = "root";
    }

    public java.sql.Connection conectar() {
        try {
            conexao = (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("Conexão efetuada com sucesso!!");
        } catch (SQLException e) {
            System.out.println("Erro na conexao!!");
            conexao = null;
        } finally {
            return conexao;
        }
    }

    public void desconectar() {
        try {
            conexao.close();
            conexao = null;
            System.out.println("Conexão encerrada");
        } catch (SQLException e) {
            System.out.println("Não foi possível fechar a conexão" + e);
        }
    }
}
