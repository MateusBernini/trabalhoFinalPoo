/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

/**
 *
 * @author Mateus Bernini
 */
public class IdInexistenteException extends NumberFormatException{
    public IdInexistenteException(String mensagem){
    super(mensagem);
    }
    
}
