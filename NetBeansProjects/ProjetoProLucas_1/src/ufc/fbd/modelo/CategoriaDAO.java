/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufc.fbd.modelo;

import ufc.fbd.conexao.Conexao;
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
public class CategoriaDAO {
    private Connection conexao;

    public CategoriaDAO(Connection conexao) {
        this.conexao = conexao;
    }
    
    public void addCategoria(Categoria categoria){
        String sql = "insert into categoria (categoria) values (?)";    
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, categoria.getCategoria());

            pstmt.execute();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setCategoria(Categoria categoria){
        String sql = "update categoria set categoria = ? where id_categoria = ?";    
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, categoria.getCategoria());
            pstmt.setInt(2, categoria.getIdCategoria());

            pstmt.execute();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void dropCategoria(int id){
        String sql = "delete from categoria where id_categoria = ?";  
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, id);
            
            pstmt.execute();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public Categoria getCategoria(int id){
        String sql = "select * from categoria where id_categoria = ?";    
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            
            pstmt.setInt(1, id);

            ResultSet resultado = pstmt.executeQuery();
            resultado.beforeFirst();
            
            Categoria categoria = null;
            
            while(resultado.next()){
                categoria = new Categoria(resultado.getInt("id_categoria"), resultado.getString("categoria"));
            }
            resultado.close();
            pstmt.close();
            return categoria;
        } catch (SQLException ex) {
           // Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        
        return null;
    }
     
    public Categoria getCategoria(String cat){
        String sql = "select * from categoria where categoria = ?";    
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            
            pstmt.setString(1, cat);

            ResultSet resultado = pstmt.executeQuery();
            resultado.beforeFirst();
            
            Categoria categoria = null;
            
            while(resultado.next()){
                categoria = new Categoria(resultado.getInt("id_categoria"), resultado.getString("categoria"));
            }
            resultado.close();
            pstmt.close();
            return categoria;
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
      public List<Categoria> getCategorias(){
        String sql = "select * from categoria";    
        try {
            Statement stmtm = conexao.createStatement();
            
            ResultSet resultado = stmtm.executeQuery(sql);
            resultado.beforeFirst();
            
            List<Categoria> lista = new ArrayList<>();
            
            while(resultado.next()){
                lista.add(new Categoria(resultado.getInt("id_categoria"), resultado.getString("categoria")));
            }
            resultado.close();
            stmtm.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    
}
