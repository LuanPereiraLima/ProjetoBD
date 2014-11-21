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
public class Cliente {
    private int idCliente;
    private String nome;
    private int cpf;
    private int idade;
    private Locadora locadora;

    public Cliente(int id_cliente, String nome, int cpf, int idade, Locadora locadora) {
        this.idCliente = id_cliente;
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.locadora = locadora;
    }

      public Cliente(String nome, int cpf, int idade, Locadora locadora) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.locadora = locadora;
    }

    public Locadora getLocadora() {
        return locadora;
    }

    public void setLocadora(Locadora locadora) {
        this.locadora = locadora;
    }
    
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
    
    
}
