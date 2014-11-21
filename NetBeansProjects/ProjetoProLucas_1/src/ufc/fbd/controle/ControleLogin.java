/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufc.fbd.controle;

import java.sql.Connection;
import ufc.fbd.conexao.Conexao;
import ufc.fbd.modelo.Funcionario;
import ufc.fbd.modelo.FuncionarioDAO;

/**
 *
 * @author Luan
 */
public class ControleLogin {
    
    private FuncionarioDAO funcionario;
    private Connection conexao;
    public ControleLogin() {
        conexao = new Conexao().getConexao();
        funcionario = new FuncionarioDAO(conexao);
    }
    
    public Funcionario isLogin(Funcionario funcionario){
        return this.funcionario.getFuncionario(funcionario);
    }
}
