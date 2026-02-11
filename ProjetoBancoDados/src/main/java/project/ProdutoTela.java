package project;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Produto;

public class ProdutoTela extends javax.swing.JFrame {

        private List<Produto> listaProdutos = new ArrayList<>();
        private int proximoId = 1;
        private DefaultTableModel modelo;

        public ProdutoTela() {
                initComponents();

                // Configura tabela
                modelo = (DefaultTableModel) jTable2.getModel();
                modelo.setRowCount(0); // limpa linhas padrão
        }

        private void initComponents() {

                jScrollPane1 = new javax.swing.JScrollPane();
                jTable1 = new javax.swing.JTable();
                jLabel1 = new javax.swing.JLabel();
                jTextField1 = new javax.swing.JTextField();
                jLabel2 = new javax.swing.JLabel();
                jTextField2 = new javax.swing.JTextField();
                jButton1 = new javax.swing.JButton();
                jButton2 = new javax.swing.JButton();
                jScrollPane2 = new javax.swing.JScrollPane();
                jTable2 = new javax.swing.JTable();

                jTable1.setModel(new javax.swing.table.DefaultTableModel(
                                new Object[][] {
                                                { null, null, null, null },
                                                { null, null, null, null },
                                                { null, null, null, null },
                                                { null, null, null, null }
                                },
                                new String[] {
                                                "Title 1", "Title 2", "Title 3", "Title 4"
                                }));
                jScrollPane1.setViewportView(jTable1);

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                jLabel1.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
                jLabel1.setText("Nome:");

                jLabel2.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
                jLabel2.setText("Valor:");

                jTextField2.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jTextField2ActionPerformed(evt);
                        }
                });

                jButton1.setText("Salvar");
                jButton1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButton1ActionPerformed(evt);
                        }
                });

                jButton2.setText("Cancelar");
                jButton2.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButton2ActionPerformed(evt);
                        }
                });

                jTable2.setModel(new javax.swing.table.DefaultTableModel(
                                new Object[][] {
                                                { null, null, null },
                                                { null, null, null },
                                                { null, null, null },
                                                { null, null, null }
                                },
                                new String[] {
                                                "ID", "Nome", "Valor"
                                }) {
                        Class[] types = new Class[] {
                                        java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
                        };

                        public Class getColumnClass(int columnIndex) {
                                return types[columnIndex];
                        }
                });
                jScrollPane2.setViewportView(jTable2);

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());

                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addGap(26, 26, 26)
                                                                                                .addGroup(layout.createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(jLabel1)
                                                                                                                .addComponent(jLabel2,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                42,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(18, 18, 18)
                                                                                                .addGroup(layout.createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                false)
                                                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                                                .addComponent(jButton1)
                                                                                                                                .addPreferredGap(
                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                                                53,
                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                .addComponent(jButton2))
                                                                                                                .addComponent(jTextField2)
                                                                                                                .addComponent(jTextField1)))
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addGap(36, 36, 36)
                                                                                                .addComponent(jScrollPane2,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                303,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addContainerGap(68, Short.MAX_VALUE)));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel1,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                30,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jTextField1,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                30,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel2,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                30,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jTextField2,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                30,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jButton1)
                                                                                .addComponent(jButton2))
                                                                .addGap(55, 55, 55)
                                                                .addComponent(jScrollPane2,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                204,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));

                pack();
        }// </editor-fold>//GEN-END:initComponents

        // Método para salvar produto
        private void salvarProduto() {
                String nome = jTextField1.getText();
                String valorTexto = jTextField2.getText();

                if (nome.trim().isEmpty()) {
                        javax.swing.JOptionPane.showMessageDialog(this, "Nome não pode ser vazio!");
                        return;
                }

                try {
                        int valor = Integer.parseInt(valorTexto);

                        if (valor < 0) {
                                javax.swing.JOptionPane.showMessageDialog(this, "Valor não pode ser negativo!");
                                return;
                        }

                        Produto produto = new Produto(proximoId++, nome, valor);
                        listaProdutos.add(produto);

                        modelo.addRow(new Object[] {
                                        produto.getId(),
                                        produto.getNome(),
                                        produto.getValor()
                        });

                        limparCampos();

                } catch (NumberFormatException e) {
                        javax.swing.JOptionPane.showMessageDialog(this, "Valor deve ser numérico!");
                }
        }

        // Limpar campos
        private void limparCampos() {
                jTextField1.setText("");
                jTextField2.setText("");
                jTextField1.requestFocus();
        }

        // Evento botão Salvar
        private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
                salvarProduto();
        }

        // Evento botão Cancelar
        private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
                limparCampos();
        }

        private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {
                // Não é necessário, pode deixar vazio
        }

        public static void main(String args[]) {
                try {
                        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
                                        .getInstalledLookAndFeels()) {
                                if ("Nimbus".equals(info.getName())) {
                                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                                        break;
                                }
                        }
                } catch (Exception ex) {
                        ex.printStackTrace();
                }

                java.awt.EventQueue.invokeLater(() -> new ProdutoTela().setVisible(true));
        }

        // Variables declaration - do not modify
        private javax.swing.JButton jButton1;
        private javax.swing.JButton jButton2;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JScrollPane jScrollPane2;
        private javax.swing.JTable jTable1;
        private javax.swing.JTable jTable2;
        private javax.swing.JTextField jTextField1;
        private javax.swing.JTextField jTextField2;
}