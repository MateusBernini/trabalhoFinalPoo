/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.sql.SQLException;

/**
 *
 * @author Mateus Bernini
 */
public class ValorNuloException extends SQLException{
    public ValorNuloException(String mensagem){
    super(mensagem);
    }

    
    
}
