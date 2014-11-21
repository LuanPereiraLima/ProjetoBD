/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufc.fbd.modelo.excecoes;

/**
 *
 * @author Luan
 */
public class ErroNoUpdate extends Exception{

    @Override
    public String getMessage() {
        return "Ocorreu algum erro no update"; //To change body of generated methods, choose Tools | Templates.
    }
    
}
