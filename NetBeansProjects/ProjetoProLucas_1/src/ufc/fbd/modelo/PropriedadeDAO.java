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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luan
 */
public class PropriedadeDAO {
    private Connection conexao;

    public PropriedadeDAO() {
        conexao = new Conexao().getConexao();
    }
    
    public void addPropriedade(Propriedade propriedade){
        String sql = "insert into propriedade(preco_filmes) values (?)";    
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setDouble(1, propriedade.getPrecoFilmes());
            
            pstmt.execute();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(PropriedadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setPropriedade(Propriedade propriedade){
        String sql = "update propriedade set preco_filmes = ? where id_propriedade = ?";    
        try {
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(2, propriedade.getIdPropriedade());
            pstmt.setDouble(1, propriedade.getPrecoFilmes());
            
            pstmt.execute();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(PropriedadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      public Propriedade getPropriedade(){
        String sql = "select * from propriedade";
        try {
            Statement stmt = conexao.createStatement();

            ResultSet resultado = stmt.executeQuery(sql);
            resultado.beforeFirst();
            
            Propriedade propriedade = null;
            
            while(resultado.next()){
                propriedade = new Propriedade(resultado.getInt("id_propriedade"), resultado.getDouble("preco_filmes"));
            }
            resultado.close();
            stmt.close();
            return propriedade;
        } catch (SQLException ex) {
            Logger.getLogger(PropriedadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    
}
