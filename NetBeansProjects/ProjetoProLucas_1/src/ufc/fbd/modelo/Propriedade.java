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
public class Propriedade {
    private int idPropriedade;
    private double precoFilmes;

    public Propriedade(int idPropriedade, double precoFilmes) {
        this.idPropriedade = idPropriedade;
        this.precoFilmes = precoFilmes;
    }

    public Propriedade(double precoFilmes) {
        this.precoFilmes = precoFilmes;
    }
    
    public int getIdPropriedade() {
        return idPropriedade;
    }

    public void setIdPropriedade(int idPropriedade) {
        this.idPropriedade = idPropriedade;
    }

    public double getPrecoFilmes() {
        return precoFilmes;
    }

    public void setPrecoFilmes(double precoFilmes) {
        this.precoFilmes = precoFilmes;
    }
    
    
}
