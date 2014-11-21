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
public class Locadora {
    private int idLocadora;
    private String nome;
    private double precoFilmes;

    public Locadora(int idLocadora, String nome,double precoFilmes) {
        this.idLocadora = idLocadora;
        this.nome = nome;
        this.precoFilmes = precoFilmes;
    }
    public Locadora(String nome,double precoFilmes) {
        this.nome = nome;
        this.precoFilmes = precoFilmes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Locadora(double precoFilmes) {
        this.precoFilmes = precoFilmes;
    }

    public double getPrecoFilmes() {
        return precoFilmes;
    }

    public void setPrecoFilmes(double precoFilmes) {
        this.precoFilmes = precoFilmes;
    }

    public int getIdLocadora() {
        return idLocadora;
    }

    public void setIdLocadora(int idLocadora) {
        this.idLocadora = idLocadora;
    }
    
    
    
}
