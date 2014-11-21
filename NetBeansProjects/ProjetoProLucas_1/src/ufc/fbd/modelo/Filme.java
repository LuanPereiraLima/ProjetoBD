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
public class Filme {

    private int idFilme;
    private String nome;
    private Categoria categoria;
    private String descricao;
    private int indicacao;
    private boolean alugado;
    private String caminho;
    private Locadora locadora;
    
    public Filme(int id_filme, String nome, Categoria categoria, String descricao, int indicacao, boolean alugado, String caminho, Locadora locadora) {
        this.idFilme = id_filme;
        this.nome = nome;
        this.categoria = categoria;
        this.descricao = descricao;
        this.indicacao = indicacao;
        this.alugado = alugado;
        this.caminho = caminho;
        this.locadora = locadora;
    }

    public Filme(String nome, Categoria categoria, String descricao, int indicacao, boolean alugado, String caminho, Locadora locadora) {
        this.nome = nome;
        this.categoria = categoria;
        this.descricao = descricao;
        this.indicacao = indicacao;
        this.alugado = alugado;
        this.caminho = caminho;
        this.locadora= locadora;
    }

        
    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    
    public boolean isAlugado() {
        return alugado;
    }

    public void setAlugado(boolean alugado) {
        this.alugado = alugado;
    }
    public int getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(int idFilme) {
        this.idFilme = idFilme;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIndicacao() {
        return indicacao;
    }

    public void setIndicacao(int indicacao) {
        this.indicacao = indicacao;
    }
    
    
    public Locadora getLocadora() {
        return locadora;
    }

    public void setLocadora(Locadora locadora) {
        this.locadora = locadora;
    }
    
    
 }
