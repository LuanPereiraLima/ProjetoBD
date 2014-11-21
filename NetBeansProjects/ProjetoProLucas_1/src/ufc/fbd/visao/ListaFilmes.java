/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufc.fbd.visao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import ufc.fbd.conexao.Conexao;
import ufc.fbd.modelo.Filme;
import ufc.fbd.modelo.FilmeDAO;

/**
 *
 * @author Luan
 */
public class ListaFilmes extends javax.swing.JFrame implements EventoCliqueFilme{

    /**
     * Creates new form ListaFilmes
     */
    private FilmeDAO filmeDAO;
    private Connection conexao;
    private List<PainelFilme> paineis;
    public ListaFilmes(int id) {
        super("Lista de Filmes do cliente");
        paineis = new ArrayList<>();
        setLocationRelativeTo(null);
        initComponents();
        conexao = new Conexao().getConexao();
        filmeDAO = new FilmeDAO(conexao);
        preenchendoListaDeFilmesCliente(id);
    }
    public ListaFilmes(){
        super("Lista de Filmes Disponíveis");
        paineis = new ArrayList<>();
        setLocationRelativeTo(null);
        initComponents();
        conexao = new Conexao().getConexao();
        filmeDAO = new FilmeDAO(conexao);
        preenchendoListaDeFilmes();
    }
    
    private void preenchendoListaDeFilmesCliente(int id){
        List<Filme> listaFilmes = filmeDAO.getFilmesAlugadosPorCliente(id);
        for(Filme film : listaFilmes)
            paineis.add(new PainelFilme(film, this, false));
        distribuindoPaineis();
    }
    private void preenchendoListaDeFilmes(){
        List<Filme> listaFilmes = filmeDAO.getFilmes();
        for(Filme film : listaFilmes)
            paineis.add(new PainelFilme(film, this, true));
        distribuindoPaineis();
    }
    
    private void distribuindoPaineis(){
        
        int posicaoInicialX = 20;
        int posicaoInicialY = 20;
        int contador = 0;
        for(PainelFilme painel : paineis){
            painel.setBounds(posicaoInicialX, posicaoInicialY, 194, 279);
            jPanel1.add(painel);
            posicaoInicialX += painel.getWidth()+20;
            contador++;
            System.out.println(contador);
            if(contador == 4){
                posicaoInicialX = 20;
                posicaoInicialY = painel.getHeight()+50;
                contador = 0;
            }
        }
        this.pack();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setAutoscrolls(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1008, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 866, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ListaFilmes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListaFilmes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListaFilmes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListaFilmes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListaFilmes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void cliqueFilme(Filme filme) {
        JOptionPane.showMessageDialog(null, filme.getDescricao(), "Descrição do filme.",JOptionPane.INFORMATION_MESSAGE);
    }
}
