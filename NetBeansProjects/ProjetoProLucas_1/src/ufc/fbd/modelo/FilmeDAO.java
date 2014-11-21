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
public class FilmeDAO {
    private Connection conexao;
 
    public FilmeDAO(Connection conexao) {
        this.conexao = conexao;
    }
    
    public void addFilme(Filme filme){
        String sql = "INSERT INTO filme (nome, descricao, indicacao, id_categoria, url_imagem) VALUES (?, ?, ?, ?, ?)";    
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, filme.getNome());
            pstmt.setString(2, filme.getDescricao());
            pstmt.setInt(3, filme.getIndicacao());
            pstmt.setInt(4, filme.getCategoria().getIdCategoria());
            pstmt.setString(5, filme.getCaminho());
            
            pstmt.execute();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FilmeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      public void setFilme(Filme filme){
        String sql = "UPDATE filme SET nome = ?, descricao = ?, indicacao = ?, id_categoria = ?, url_imagem = ? WHERE id_filme = ?";    
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            
            pstmt.setString(1, filme.getNome());
            pstmt.setString(2, filme.getDescricao());
            pstmt.setInt(3, filme.getIndicacao());
            pstmt.setInt(4, filme.getCategoria().getIdCategoria());
             pstmt.setString(5, filme.getCaminho());
            pstmt.setInt(6, filme.getIdFilme());
            
            pstmt.execute();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FilmeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void dropFilme(int id){
        String sql = "DELETE FROM filme WHERE id_filme = ?";    
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, id);
            
            pstmt.execute();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FilmeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public Filme getFilme(int id){
        String sql = "SELECT * "
                + "FROM filme AS fil "
                + "INNER JOIN categoria AS cat "
                + "INNER JOIN locadora AS loc "
                + "ON cat.id_categoria = fil.id_categoria "
                + "AND loc.id_locadora = fil.id_locadora "
                + "WHERE fil.id_filme = ?";    
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet resultado = pstmt.executeQuery();
            resultado.beforeFirst();
            
            Filme filme = null;
            Categoria categoria = null;
            while(resultado.next()){
                int id_filme = resultado.getInt("id_filme");
                categoria = new Categoria(resultado.getInt("id_categoria"), "categoria");
                filme = new Filme(id_filme, resultado.getString("nome"), categoria , resultado.getString("descricao"), resultado.getInt("indicacao"), isFilmeAlugado(id_filme), resultado.getString("url_imagem"), new Locadora(resultado.getInt("fil.id_locadora"), resultado.getString("loc.nome"), resultado.getDouble("preco_filmes")));
            }
            resultado.close();
            pstmt.close();
            return filme;
        } catch (SQLException ex) {
            Logger.getLogger(FilmeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
     
      public List<Filme> getFilmesNome(String nome){
        String sql = "SELECT * FROM filme AS fil "
                + "INNER JOIN categoria AS cat "
                + "INNER JOIN locadora AS loc "
                + "ON cat.id_categoria = fil.id_categoria "
                + "AND fil.id_locadora = loc.id_locadora "
                + "WHERE fil.nome like '%'?'%'";
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, nome);
            ResultSet resultado = pstmt.executeQuery();
            
            resultado.beforeFirst();
            
            List<Filme> lista =  new ArrayList<>();
            Categoria categoria = null;
            
            while(resultado.next()){
                categoria = new Categoria(resultado.getInt("id_categoria"), resultado.getString("categoria"));
                int id_filme = resultado.getInt("id_filme");
                lista.add(new Filme(id_filme , resultado.getString("fil.nome"), categoria, resultado.getString("descricao"), resultado.getInt("indicacao"), isFilmeAlugado(id_filme), resultado.getString("url_imagem"), new Locadora(resultado.getInt("fil.id_locadora"), resultado.getString("loc.nome"), resultado.getDouble("preco_filme"))));
            }
            resultado.close();
            pstmt.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(FilmeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
      public List<Filme> getFilmes(){
        String sql = "SELECT * FROM filme AS fil "
                + "INNER JOIN categoria AS cat "
                + "INNER JOIN locadora AS loc "
                + "ON cat.id_categoria = fil.id_categoria "
                + "AND fil.id_locadora = loc.id_locadora";
        try {
            Statement stmtm = conexao.createStatement();
            ResultSet resultado = stmtm.executeQuery(sql);
            
            resultado.beforeFirst();
            
            List<Filme> lista =  new ArrayList<>();
            Categoria categoria = null;
            
            while(resultado.next()){
                categoria = new Categoria(resultado.getInt("id_categoria"), resultado.getString("categoria"));
                int id_filme = resultado.getInt("id_filme");
                lista.add(new Filme(id_filme , resultado.getString("fil.nome"), categoria, resultado.getString("descricao"), resultado.getInt("indicacao"), isFilmeAlugado(id_filme), resultado.getString("url_imagem"), new Locadora(resultado.getInt("fil.id_locadora"), resultado.getString("loc.nome"), resultado.getDouble("preco_filme"))));
            }
            resultado.close();
            stmtm.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(FilmeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
         public List<Filme> getFilmesAlugadosPorCliente(int idCliente){
        String sql = "SELECT * FROM filme AS fil "
                + "INNER JOIN categoria AS cat "
                + "INNER JOIN locadora AS loc "
                + "ON cat.id_categoria = fil.id_categoria and fil.id_locadora = loc.id_locadora "
                + "WHERE id_filme IN (SELECT id_filme FROM aluga WHERE id_cliente = ?)";
        try {
            PreparedStatement stmtm = conexao.prepareStatement(sql);
            stmtm.setInt(1, idCliente);
            
            ResultSet resultado = stmtm.executeQuery();
            resultado.beforeFirst();
            
            List<Filme> lista =  new ArrayList<>();
            Categoria categoria = null;
            
            while(resultado.next()){
                categoria = new Categoria(resultado.getInt("id_categoria"), resultado.getString("categoria"));
                int id_filme = resultado.getInt("id_filme");
                lista.add(new Filme(id_filme , resultado.getString("nome"), categoria, resultado.getString("descricao"), resultado.getInt("indicacao"), isFilmeAlugado(id_filme), resultado.getString("url_imagem"), new Locadora(resultado.getInt("fil.id_locadora"), resultado.getString("loc.nome"), resultado.getDouble("preco_filme"))));
            }
            resultado.close();
            stmtm.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(FilmeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
      
    private boolean isFilmeAlugado(int id){
        String sql = "SELECT COUNT(*) AS qtd FROM aluga WHERE id_filme = ?";    
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet resultado = pstmt.executeQuery();

            resultado.beforeFirst();
            
            int numeroLinhas = 0;
            
            while(resultado.next()){
                numeroLinhas = resultado.getInt("qtd");
            }
            
            resultado.close();
            pstmt.close();
            
            if(numeroLinhas > 0)
                return true;
            else
                return false;
           
        } catch (SQLException ex) {
            Logger.getLogger(AlugaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    
}
