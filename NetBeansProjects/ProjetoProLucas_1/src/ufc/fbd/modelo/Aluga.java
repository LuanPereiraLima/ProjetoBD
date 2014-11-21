/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufc.fbd.modelo;

import java.sql.Date;

/**
 *
 * @author Luan
 */
public class Aluga {
    private Cliente cliente;
    private Filme filme;
    private Date data;

    public Aluga(Cliente cliente, Filme filme, Date data) {
        this.cliente = cliente;
        this.filme = filme;
        this.data = data;
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
    
}
