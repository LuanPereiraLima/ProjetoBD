/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufc.fbd.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luan
 */
public class FuncionarioDAO {
    private Connection conexao;

    public FuncionarioDAO(Connection conexao) {
        this.conexao = conexao;
    }  
    
    public void addFuncionario(Funcionario funcionario){
        String sql = "INSERT INTO funcionario (nome, usuario, senha, id_locadora) VALUES (?, ?, ?, ?)";    
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, funcionario.getNome());
            pstmt.setString(2, funcionario.getUsuario());
            pstmt.setString(3, funcionario.getSenha());
            pstmt.setInt(4, funcionario.getLocadora().getIdLocadora());
            
            pstmt.execute();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setFuncionario(Funcionario funcionario){
        String sql = "UPDATE funcionario SET nome = ?, usuario = ?, senha = ?, id_locadora = ? WHERE id_funcionario = ?";    
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);

            pstmt.setString(1, funcionario.getNome());
            pstmt.setString(2, funcionario.getUsuario());
            pstmt.setString(3, funcionario.getSenha());
            pstmt.setInt(4, funcionario.getLocadora().getIdLocadora());
            pstmt.setInt(5, funcionario.getIdFuncionario());
             
            pstmt.execute();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void dropFuncionario(int id){
        String sql = "DELETE FROM funcionario WHERE id_funcionario = ?";    
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, id);
            
            pstmt.execute();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public Funcionario getFuncionario(int id){
        String sql = "SELECT * FROM funcionario fun "
                + "INNER JOIN locadora loc "
                + "ON fun.id_locadora = loc.id_locadora "
                + "WHERE id_funcionario = ?";    
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, id);
            
            ResultSet resultado = pstmt.executeQuery();
            resultado.beforeFirst();
            Funcionario funcionario = null;
            
            while(resultado.next()){
                funcionario = new Funcionario(id, resultado.getString("fun.nome"), resultado.getString("usuario"), resultado.getString("senha"), new Locadora(resultado.getInt("fun.id_locadora"), resultado.getString("loc.nome"), resultado.getDouble("preco_filmes")));
            }
            
            resultado.close();
            pstmt.close();
            return funcionario;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
     
     public Funcionario getFuncionario(Funcionario ffuncionario){
        String sql = "SELECT * FROM funcionario fun "
                + "INNER JOIN locadora loc "
                + "ON loc.id_locadora = fun.id_locadora "
                + "WHERE usuario = ? AND senha = ?";    
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, ffuncionario.getUsuario());
            pstmt.setString(2, ffuncionario.getSenha());
            
            ResultSet resultado = pstmt.executeQuery();
            resultado.beforeFirst();
            Funcionario funcionario = null;
            
            while(resultado.next()){
                funcionario = new Funcionario(resultado.getInt("id_funcionario"), resultado.getString("fun.nome"), resultado.getString("usuario"), resultado.getString("senha"), new Locadora(resultado.getInt("fun.id_locadora"), resultado.getString("loc.nome"), resultado.getDouble("preco_filmes")));
            }
            
            resultado.close();
            pstmt.close();
            return funcionario;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public List<Funcionario> getFuncionarios(){
        String sql = "SELECT * FROM funcionario fun "
                   + "INNER JOIN locadora loc "
                   + "ON loc.id_locadora = fun.id_locadora";  
        try {
            Statement stmtm = conexao.createStatement();
            
            ResultSet resultado = stmtm.executeQuery(sql);
            
            resultado.beforeFirst();
            List<Funcionario> lista = new ArrayList<>();
            
            while(resultado.next()){
                lista.add(new Funcionario(resultado.getInt("id_funcionario"), resultado.getString("fun.nome"), resultado.getString("usuario"), resultado.getString("senha"), new Locadora(resultado.getInt("fun.id_locadora"), resultado.getString("loc.nome"), resultado.getDouble("preco_filmes"))));
            }
            
            resultado.close();
            stmtm.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
     public List<Funcionario> getFuncionariosNome(String nome){
        String sql = "SELECT * FROM funcionario fun "
                   + "INNER JOIN locadora loc "
                   + "ON loc.id_locadora = fun.id_locadora "
                   + "WHERE fun.nome LIKE ?";  
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            
            pstmt.setString(1, "%"+nome+"%");
            
            ResultSet resultado = pstmt.executeQuery();
            
            resultado.beforeFirst();
            List<Funcionario> lista = new ArrayList<>();
            
            while(resultado.next()){
                lista.add(new Funcionario(resultado.getInt("id_funcionario"), resultado.getString("fun.nome"), resultado.getString("usuario"), resultado.getString("senha"), new Locadora(resultado.getInt("fun.id_locadora"), resultado.getString("loc.nome"), resultado.getDouble("preco_filmes"))));
            }
            
            resultado.close();
            pstmt.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    
}
