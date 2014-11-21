/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufc.fbd.testes;

import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ufc.fbd.conexao.Conexao;
import ufc.fbd.excecoes.ErroNaInsercaoException;
import ufc.fbd.modelo.Aluga;
import ufc.fbd.modelo.AlugaDAO;
import ufc.fbd.modelo.CategoriaDAO;
import ufc.fbd.modelo.Cliente;
import ufc.fbd.modelo.ClienteDAO;
import ufc.fbd.modelo.Filme;
import ufc.fbd.modelo.FilmeDAO;
import ufc.fbd.modelo.Funcionario;
import ufc.fbd.modelo.FuncionarioDAO;
import ufc.fbd.modelo.PropriedadeDAO;

/**
 *
 * @author Luan
 */
public class Testes {
    
        Connection connection = new Conexao().getConexao();
        CategoriaDAO categoria = new CategoriaDAO(connection);
        ClienteDAO cliente = new ClienteDAO(connection);
        PropriedadeDAO propriedade = new PropriedadeDAO();
        FilmeDAO filme = new FilmeDAO(connection);
        AlugaDAO aluga = new AlugaDAO(connection);
        FuncionarioDAO funcionario = new FuncionarioDAO(connection);
        
    public static void main(String[] args) {
 
        
            //categoria.addCategoria(new Categoria("Comédia"));
        /*
        List<Categoria> categorias = categoria.getCategorias();
        for(Categoria cat : categorias){
            System.out.println(cat.getCategoria() + " id : "+cat.getIdCategoria());
        }
       
        
        System.out.println(categoria.getCategoria(4));
        
        categoria.dropCategoria(3);
       
        
        categoria.setCategoria(new Categoria(1, "Ação"));
         */
        
        
        //-------------CLASSE CLIENTE
        
        
       /* cliente.addCliente(new Cliente("Mauro", 123917312, 19));
        
        List<Cliente> clientes = cliente.getClientes();
        for(Cliente clien : clientes){
            System.out.println("id: "+clien.getIdCliente()+"Nome: "+ clien.getNome() + " cpf : "+clien.getCpf()+" idade: "+clien.getIdade());
        }
               
       */
        
        //cliente.dropCliente(2);
        
        //System.out.println(cliente.getCliente(0));
        
        //cliente.setCliente(new Cliente(1, "Italos", 2342352, 21));
        
        //-----------------CLASSE PROPRIEDADE
        
        //propriedade.setPropriedade(new Propriedade(1, 3.50));
           // propriedade.addPropriedade(new Propriedade(3,50));
        
        //System.out.println(propriedade.getPropriedade().getPrecoFilmes());
        
        
        //--------------------CLASSE FILME
        
        //filme.addFilme(new Filme("Annabelle", categoria.getCategoria(2), "Filme baseado em fatos reais, boneca do cão!", 12, false));
        
        /*List<Filme> clientes = filme.getFilmes();
        for(Filme clien : clientes){
            System.out.println("id: "+clien.getIdFilme()+"Nome: "+ clien.getNome() + " descricao: " +clien.getDescricao()+" indicacao: "+clien.getIndicacao());
        }*/
        
        //System.out.println(filme.getFilme(1));
        
        //filme.setFilme(new Filme(1, "mudado", categoria.getCategoria(1), "MUDADENHO", 90, false));
        
        //_-----------------------CLASSE ALUGA
        
        
        new Testes();
        
        
}

    public Testes() {
        
       // List<Aluga> lista = aluga.getFilmesAlugadosPorCliente(cliente.getCliente(6));
        
        //for(Aluga aluga : lista){
        //    System.out.println(aluga.getData());
       // }
        
      //  filme.addFilme(new Filme("Teste", categoria.getCategoria(1), "testePara Display", 17, false, "C://aygduyas"));
       // filme.addFilme(new Filme("Teste2", categoria.getCategoria(1), "testePara Display", 19, false, "C://aygduyas"));
      //  filme.addFilme(new Filme("Teste3", categoria.getCategoria(1), "testePara Display", 17, false, "C://aygduyas"));
      //  filme.addFilme(new Filme("Teste4", categoria.getCategoria(1), "testePara Display", 17, false, "C://aygduyas"));
        //aluga.addAluga(new Aluga(cliente.getCliente(6), filme.getFilme(1), getData()));
        //aluga.addAluga(new Aluga(cliente.getCliente(6), filme.getFilme(5), getData()));
        
        List<Filme> lista = filme.getFilmesNome("");
        
        for(Filme filmes : lista){
            System.out.println(filmes.getNome());
        }
        
        //Filme fil = filme.getFilme(1);
        //System.out.println(fil.getNome());
           
    }
    
    //usar para buscar a data
    private java.sql.Date getData(){
        java.util.Date dataUtil = new java.util.Date();  
        return new java.sql.Date(dataUtil.getTime()); 
    }

}
