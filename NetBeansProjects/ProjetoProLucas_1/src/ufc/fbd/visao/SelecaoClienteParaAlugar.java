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
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import ufc.fbd.conexao.Conexao;
import ufc.fbd.modelo.Cliente;
import ufc.fbd.modelo.ClienteDAO;
import ufc.fbd.modelo.Filme;
import ufc.fbd.modelo.FilmeDAO;
import ufc.fbd.modelo.Funcionario;

/**
 *
 * @author Luan
 */
public class SelecaoClienteParaAlugar extends javax.swing.JFrame implements TableModelListener{

    /**
     * Creates new form ListaClientes
     */
    
    private DefaultTableModel modelo;
    private ClienteDAO clienteDAO;;
    private Connection conexao;
    private int selecionada = -1;
    private Funcionario funcionarioLogado;
    private Filme filme;
    public SelecaoClienteParaAlugar(Funcionario funcionarioLogado, Filme filme){
        this.filme = filme;
        setLocationRelativeTo(null);
        this.funcionarioLogado = funcionarioLogado;
        montandoModeloTabela();
        initComponents();
        setLocationRelativeTo(null);
        conexao = new Conexao().getConexao();
        clienteDAO = new ClienteDAO(conexao);
        verificacaoInicial();
        preenchendoListaClientes("");
           jTable1.addMouseListener(new MouseListener() {

              @Override
              public void mouseClicked(MouseEvent e) {
                  selecionada = jTable1.getSelectedRow();
                  btSelecionarCliente.setEnabled(false);
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
           btSelecionarCliente.setEnabled(false);
           
    }
    
    private void montandoModeloTabela(){
          modelo = new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "CPF", "Idade", "Qtd de Filmes Alugados"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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
            btSelecionarCliente.setEnabled(true);
    }
    
      private void removendoLinhasTabela(){
       modelo.setRowCount(0);
    }
    
    private void preenchendoListaClientes(String nome){
        removendoLinhasTabela();
        List<Cliente> listaClientes = clienteDAO.getClientesNome(nome);
        if(listaClientes != null){
            for(Cliente cliente : listaClientes){
                int quantidade = clienteDAO.quantidadeFilmesAlugados(cliente.getIdCliente());
                modelo.addRow(new Object[]{cliente.getIdCliente(), cliente.getNome(), cliente.getCpf(), cliente.getIdade(), quantidade});
            }
        }
    }
    
    
    private void verificacaoInicial(){
        List<Cliente> listaCliente = clienteDAO.getClientes();
        if(listaCliente != null && listaCliente.size() > 0){
        }else{
            JOptionPane.showMessageDialog(null, "Não existem Clientes cadastrados."); 
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
        btSelecionarCliente = new javax.swing.JButton();
        btSair = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        tfBusca = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(620, 443));
        setResizable(false);

        jPanel1.setMaximumSize(new java.awt.Dimension(620, 443));
        jPanel1.setMinimumSize(new java.awt.Dimension(620, 443));

        jTable1.setModel(modelo);
        jScrollPane1.setViewportView(jTable1);

        btSelecionarCliente.setText("Selecionar");
        btSelecionarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSelecionarClienteActionPerformed(evt);
            }
        });

        btSair.setText("Sair");
        btSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSairActionPerformed(evt);
            }
        });

        jLabel1.setText("Busca:");

        tfBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfBuscaActionPerformed(evt);
            }
        });
        tfBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfBuscaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(6, 6, 6)
                        .addComponent(tfBusca)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 89, Short.MAX_VALUE)
                        .addComponent(btSair))
                    .addComponent(btSelecionarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btSair)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 3, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSelecionarCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed
        dispose();
    }//GEN-LAST:event_btSairActionPerformed

    private void btSelecionarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSelecionarClienteActionPerformed
        if(selecionada > -1){
            Integer id = (Integer) modelo.getValueAt(selecionada, 0);
            Cliente cliente = clienteDAO.getCliente(id);
            new ConfirmacaoAluguel(funcionarioLogado, filme, cliente).setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Selecione o Cliente na tabela");
        }
    }//GEN-LAST:event_btSelecionarClienteActionPerformed

    
    private void tfBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfBuscaActionPerformed
       
    }//GEN-LAST:event_tfBuscaActionPerformed

    private void tfBuscaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBuscaKeyTyped
        preenchendoListaClientes(tfBusca.getText());
    }//GEN-LAST:event_tfBuscaKeyTyped
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btSair;
    private javax.swing.JButton btSelecionarCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField tfBusca;
    // End of variables declaration//GEN-END:variables

    @Override
    public void tableChanged(TableModelEvent e) {
    }
}
