/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufc.fbd.visao;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import ufc.fbd.conexao.Conexao;
import ufc.fbd.modelo.Categoria;
import ufc.fbd.modelo.CategoriaDAO;
import ufc.fbd.modelo.Funcionario;

/**
 *
 * @author Luan
 */
public class TabelaCategorias extends javax.swing.JFrame implements TableModelListener{

    /**
     * Creates new form ListaClientes
     */
    
    private DefaultTableModel modelo;
    private CategoriaDAO categoria;
    private Connection conexao;
    private int selecionada = -1;
    
    public TabelaCategorias() {
        montandoModeloTabela();
        initComponents();
        setLocationRelativeTo(null);
        conexao = new Conexao().getConexao();
        categoria = new CategoriaDAO(conexao);
        verificacaoInicial();
        preenchendoListaCategorias();
           jTable1.addMouseListener(new MouseListener() {

              @Override
              public void mouseClicked(MouseEvent e) {
                  selecionada = jTable1.getSelectedRow();
                  btEditar.setEnabled(false);
                  btExcluir.setEnabled(false);
                  if(selecionada != -1){
                      linhaSelecionada();
                  }
              }

              @Override
              public void mousePressed(MouseEvent e) {
              }

              @Override
              public void mouseReleased(MouseEvent e) {
              }

              @Override
              public void mouseEntered(MouseEvent e) {
              }

              @Override
              public void mouseExited(MouseEvent e) {
             }
          });
           
           btEditar.setEnabled(false);
           btExcluir.setEnabled(false);
    }
    
    private void montandoModeloTabela(){
          modelo = new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Categia"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
    }
    
    private void linhaSelecionada(){
        
        btEditar.setEnabled(true);
        btExcluir.setEnabled(true);
         
    }
    
     private void removendoLinhasTabela(){
       modelo.setRowCount(0);
    }
    
    private void preenchendoListaCategorias(){
        removendoLinhasTabela();
        List<Categoria> listaCategorias = categoria.getCategorias();
        if(listaCategorias != null){
            for(Categoria cat : listaCategorias){
                modelo.addRow(new Object[]{cat.getIdCategoria(),cat.getCategoria()});
            }
        }
    }
    
         private void verificacaoInicial(){
        List<Categoria> listaCategorias = categoria.getCategorias();
        if(listaCategorias != null && listaCategorias.size() > 0){
        }else{
            JOptionPane.showMessageDialog(null, "Não existem Categorias cadastrados."); 
            dispose();
        }
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btEditar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        btSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(270, 215));
        setMinimumSize(new java.awt.Dimension(270, 215));
        setPreferredSize(new java.awt.Dimension(270, 215));
        setResizable(false);

        jPanel1.setMaximumSize(new java.awt.Dimension(620, 443));
        jPanel1.setMinimumSize(new java.awt.Dimension(620, 443));

        jTable1.setModel(modelo);
        jScrollPane1.setViewportView(jTable1);

        btEditar.setText("Editar");
        btEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarActionPerformed(evt);
            }
        });

        btExcluir.setText("Excluir");
        btExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirActionPerformed(evt);
            }
        });

        btSair.setText("Sair");
        btSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btSair, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btEditar)
                        .addGap(18, 18, 18)
                        .addComponent(btExcluir)
                        .addGap(69, 69, 69)
                        .addComponent(btSair))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed
        dispose();
    }//GEN-LAST:event_btSairActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        if(selecionada > -1){
            Integer id = (Integer) modelo.getValueAt(selecionada, 0);
            if(JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir a categoria selecionada?", "Exclusão", 2)== JOptionPane.OK_OPTION){
            categoria.dropCategoria(id);
            preenchendoListaCategorias();
            selecionada = -1;
        }
        }else{
            JOptionPane.showMessageDialog(null, "Selecione a categoria na tabela");
        }
    }//GEN-LAST:event_btExcluirActionPerformed

    private void btEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarActionPerformed
        if(selecionada > -1){
            Integer id = (Integer) modelo.getValueAt(selecionada, 0);
            Categoria cat = categoria.getCategoria(id);
            new EditarCategoria(cat).setVisible(true);
            selecionada = -1;
        }else{
            JOptionPane.showMessageDialog(null, "Selecione a categoria na tabela");
        }
        atualizarAutomaticamente();
    }//GEN-LAST:event_btEditarActionPerformed

    private void atualizarAutomaticamente(){
         new Thread(new Runnable() {

             @Override
             public void run() {
                 try {
                     Thread.sleep(1000);
                 } catch (InterruptedException ex) {
                     Logger.getLogger(TabelaFilmes.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 preenchendoListaCategorias();
             }
         }).start();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btEditar;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btSair;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void tableChanged(TableModelEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
