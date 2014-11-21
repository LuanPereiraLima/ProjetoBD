/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufc.fbd.modelo;

/**
 *
 * @author Luan
 */
public class Funcionario {
    private int idFuncionario;
    private String nome;
    private String usuario;
    private String senha;
    private Locadora locadora;

    public Funcionario(int idFuncionario, String nome, String usuario, String senha, Locadora locadora) {
        this.idFuncionario = idFuncionario;
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.locadora = locadora;
    }

    public Funcionario(String nome, String usuario, String senha, Locadora locadora) {
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.locadora = locadora;
    }

    public Locadora getLocadora() {
        return locadora;
    }

    public void setLocadora(Locadora locadora) {
        this.locadora = locadora;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
}
