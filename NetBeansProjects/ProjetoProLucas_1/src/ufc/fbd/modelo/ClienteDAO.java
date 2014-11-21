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
public class ClienteDAO {
    private Connection conexao;

    public ClienteDAO(Connection conexao) {
        this.conexao = conexao;
    }  
    
    public void addCliente(Cliente cliente) throws ErroNaInsercaoException{
        String sql = "INSERT INTO cliente (nome, cpf, idade, id_locadora) VALUES (?, ?, ?, ?)";    
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, cliente.getNome());
            pstmt.setInt(2, cliente.getCpf());
            pstmt.setInt(3, cliente.getIdade());
            pstmt.setInt(4, cliente.getLocadora().getIdLocadora());
            
            pstmt.execute();
            pstmt.close();
        } catch (SQLException ex) {
           throw new ErroNaInsercaoException();
        }
    }
    
    public void setCliente(Cliente cliente) throws ErroNoUpdate{
        String sql = "UPDATE cliente SET nome = ?, cpf = ?, idade = ?, id_locadora = ? WHERE id_cliente = ?";    
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);

            pstmt.setString(1, cliente.getNome());
            pstmt.setInt(2, cliente.getCpf());
            pstmt.setInt(3, cliente.getIdade());
            pstmt.setInt(4, cliente.getLocadora().getIdLocadora());
            pstmt.setInt(5, cliente.getIdCliente());
            
            
            pstmt.execute();
            pstmt.close();
        } catch (SQLException ex) {
            throw new ErroNoUpdate();
        }
    }
    
     public void dropCliente(Cliente client){
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";    
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, client.getIdCliente());
            
            pstmt.execute();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public Cliente getCliente(int id){
        String sql = "SELECT * FROM cliente cl "
                + "INNER JOIN locadora lc "
                + "ON cl.id_locadora = lc.id_locadora "
                + "WHERE cl.id_cliente = ?";    
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, id);
            
            ResultSet resultado = pstmt.executeQuery();
            resultado.beforeFirst();
            Cliente cliente = null;
            
            while(resultado.next()){
                cliente = new Cliente(resultado.getInt("id_cliente"), resultado.getString("cl.nome"), 
                        resultado.getInt("cpf"), resultado.getInt("idade"), new Locadora(resultado.getInt("lc.id_locadora"), 
                                resultado.getString("lc.nome"), resultado.getDouble("lc.preco_filmes")));
            }
            
            resultado.close();
            pstmt.close();
            return cliente;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
  
     public List<Cliente> getClientesNome(String nome){
        String sql = "SELECT * FROM cliente cl "
                + "INNER JOIN locadora lc "
                + "ON cl.id_locadora = lc.id_locadora "
                + "WHERE cl.nome LIKE ?";    
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1,"%"+nome+"%");
            
            ResultSet resultado = pstmt.executeQuery();
            
            resultado.beforeFirst();
            List<Cliente> lista = new ArrayList<>();
            
            while(resultado.next()){
                lista.add(new Cliente(resultado.getInt("id_cliente"), resultado.getString("cl.nome"), 
                        resultado.getInt("cpf"), resultado.getInt("idade")
                        ,new Locadora(resultado.getInt("lc.id_locadora"), 
                                resultado.getString("lc.nome"), resultado.getDouble("lc.preco_filmes"))));
            }
            
            resultado.close();
            pstmt.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
     
      public List<Cliente> getClientes(){
        String sql = "SELECT * FROM cliente cl "
                + "INNER JOIN locadora lc "
                + "ON cl.id_locadora = lc.id_locadora";    
        try {
            Statement stmtm = conexao.createStatement();
            ResultSet resultado = stmtm.executeQuery(sql);
            
            resultado.beforeFirst();
            List<Cliente> lista = new ArrayList<>();
            
            while(resultado.next()){
                lista.add(new Cliente(resultado.getInt("id_cliente"), resultado.getString("cl.nome"), 
                        resultado.getInt("cpf"), resultado.getInt("idade")
                        ,new Locadora(resultado.getInt("lc.id_locadora"), 
                                resultado.getString("lc.nome"), resultado.getDouble("lc.preco_filmes"))));
            }
            
            resultado.close();
            stmtm.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
      
    public int quantidadeFilmesAlugados(int id){
        String sql = "SELECT COUNT(*) AS qtd FROM aluga WHERE id_cliente = ?";    
        int numero = 0;
        try {
            PreparedStatement stmtm = conexao.prepareStatement(sql);
            stmtm.setInt(1, id);
            
            ResultSet resultado = stmtm.executeQuery();
            
            resultado.beforeFirst();
            
            
            while(resultado.next()){
                numero = resultado.getInt("qtd");
            }
            
            resultado.close();
            stmtm.close();
            return numero;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return numero;
    }
    
    
}
