/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufc.fbd.modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luan
 */
public class AlugaDAO {
    private Connection conexao;
    
    public AlugaDAO(Connection conexao) {
        this.conexao = conexao;
    }    
    
    public void addAluga(Aluga aluga){
        String sql = "INSERT INTO aluga (id_cliente, id_filme, data_aluguel) VALUES (?, ?, ?)";    
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, aluga.getCliente().getIdCliente());
            pstmt.setInt(2, aluga.getFilme().getIdFilme());
            pstmt.setDate(3, aluga.getData());

            pstmt.execute();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(AlugaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void dropAluga(Aluga aluga){
        String sql = "DELETE FROM aluga WHERE id_cliente = ? AND id_filme = ?";  
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, aluga.getCliente().getIdCliente());
            pstmt.setInt(2, aluga.getFilme().getIdFilme());

            pstmt.execute();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(AlugaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
      public void realizarDevolucao(int id_filme){
        String sql = "DELETE FROM aluga WHERE id_filme = ?";  
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, id_filme);

            pstmt.execute();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(AlugaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public Aluga getAluga(Aluga aluga){
        String sql = "SELECT * FROM aluga WHERE id_cliente = ? AND id_filme = ?";    
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, aluga.getCliente().getIdCliente());
            pstmt.setInt(2, aluga.getFilme().getIdFilme());
            
            ResultSet resultado = pstmt.executeQuery();
            resultado.beforeFirst();
            
            Aluga alugaResultado = null;
            while(resultado.next()){
                alugaResultado = new Aluga(aluga.getCliente(), aluga.getFilme(), resultado.getDate("data_aluguel"));
            }
            
            resultado.close();
            pstmt.close();
            return aluga;
        } catch (SQLException ex) {
            Logger.getLogger(AlugaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
      public List<Aluga> getFilmesAlugadosPorCliente(Cliente cliente){
        String sql = "SELECT * FROM "
                + "aluga AS alu "
                + "INNER JOIN filme AS fil "
                + "INNER JOIN categoria AS cat "
                + "INNER JOIN locadora AS loc "
                + "ON alu.id_filme = fil.id_filme "
                + "AND cat.id_categoria = fil.id_categoria "
                + "AND fil.id_locadora = loc.id_locadora "
                + "WHERE AND id_cliente = ?";  
        try {
            
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, cliente.getIdCliente());
            ResultSet resultado = pstmt.executeQuery();
            
            resultado.beforeFirst();
            
            List<Aluga> lista = new ArrayList<>();
            Filme filme = null;
            Categoria categoria = null;
            Date data = null;
            while(resultado.next()){
                categoria = new Categoria(resultado.getInt("id_categoria"), resultado.getString("categoria"));
                filme = new Filme(resultado.getInt("id_filme"), resultado.getString("fil.nome"), categoria, resultado.getString("descricao"), resultado.getInt("indicacao"), true, resultado.getString("url_imagem"), new Locadora(resultado.getInt("loc.id_locadora"), resultado.getString("loc.nome"), resultado.getDouble("preco_filmes")));
                data = resultado.getDate("data_aluguel");
                lista.add(new Aluga(cliente, filme, data));
            }
            
            resultado.close();
            pstmt.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(AlugaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }  
}
