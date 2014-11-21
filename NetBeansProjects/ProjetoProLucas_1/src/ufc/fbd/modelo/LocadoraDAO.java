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
import ufc.fbd.modelo.excecoes.ErroNaInsercaoException;
import ufc.fbd.modelo.excecoes.ErroNoUpdate;

/**
 *
 * @author Luan
 */
public class LocadoraDAO {
    private Connection conexao;

    public LocadoraDAO(Connection conexao) {
        this.conexao = conexao;
    }  
    
    public void addLocadora(Locadora locadora) throws ErroNaInsercaoException{
        String sql = "INSERT INTO locadora (nome, preco_filmes) VALUES (?, ?)";    
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, locadora.getNome());
            pstmt.setDouble(2, locadora.getPrecoFilmes());
            
            pstmt.execute();
            pstmt.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
           throw new ErroNaInsercaoException();
        }
    }
    
    public void setLocadora(Locadora locadora) throws ErroNoUpdate{
        String sql = "UPDATE locadora SET nome = ?, preco_filmes = ? WHERE id_locadora = ?";    
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);

            pstmt.setString(1, locadora.getNome());
            pstmt.setDouble(2, locadora.getPrecoFilmes());
            pstmt.setInt(3, locadora.getIdLocadora());
            
            pstmt.execute();
            pstmt.close();
        } catch (SQLException ex) {
            throw new ErroNoUpdate();
        }
    }
    
     public void dropLocadora(int id){
        String sql = "DELETE FROM locadora WHERE id_locadora = ?";    
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, id);
            
            pstmt.execute();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(LocadoraDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public Locadora getLocadora(int id){
        String sql = "SELECT * FROM locadora WHERE id_locadora = ?";    
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, id);
            
            ResultSet resultado = pstmt.executeQuery();
            resultado.beforeFirst();
            Locadora locadora1 = null;
            
            while(resultado.next()){
                locadora1 = new Locadora(resultado.getInt("id_locadora"), resultado.getString("nome"), resultado.getDouble("preco_filmes"));
            }
            
            resultado.close();
            pstmt.close();
            return locadora1;
        } catch (SQLException ex) {
            Logger.getLogger(LocadoraDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
     
     public Locadora getLocadoraNome(String nome){
        String sql = "SELECT * FROM locadora WHERE nome = ?";    
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, nome);
            
            ResultSet resultado = pstmt.executeQuery();
            resultado.beforeFirst();
            Locadora locadora1 = null;
            
            while(resultado.next()){
                locadora1 = new Locadora(resultado.getInt("id_locadora"), resultado.getString("nome"), resultado.getDouble("preco_filmes"));
            }
            
            resultado.close();
            pstmt.close();
            return locadora1;
        } catch (SQLException ex) {
            Logger.getLogger(LocadoraDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
      public List<Locadora> getLocadoras(){
        String sql = "SELECT * FROM locadora";    
        try {
            Statement stmtm = conexao.createStatement();
            ResultSet resultado = stmtm.executeQuery(sql);
            
            resultado.beforeFirst();
            List<Locadora> lista = new ArrayList<>();
            
            while(resultado.next()){
                lista.add(new Locadora(resultado.getInt("id_locadora"), resultado.getString("nome"), resultado.getDouble("preco_filmes")));
            }
            
            resultado.close();
            stmtm.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(LocadoraDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
      
      public List<Locadora> getLocadorasNome(String nome){
        String sql = "SELECT * FROM locadora WHERE nome like ?";    
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, "%"+nome+"%");
            ResultSet resultado = pstmt.executeQuery();
            
            resultado.beforeFirst();
            List<Locadora> lista = new ArrayList<>();
            
            while(resultado.next()){
                lista.add(new Locadora(resultado.getInt("id_locadora"), resultado.getString("nome"), resultado.getDouble("preco_filmes")));
            }
            
            resultado.close();
            pstmt.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(LocadoraDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
}
