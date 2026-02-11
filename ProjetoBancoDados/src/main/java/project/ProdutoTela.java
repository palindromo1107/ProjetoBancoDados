package project;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Produto;

public class ProdutoTela extends javax.swing.JFrame {

        private List<Produto> listaProdutos = new ArrayList<>();
        private int proximoId = 1;
        private DefaultTableModel modelo;
        private int produtoSelecionadoId = -1; // ID do produto selecionado para edição

        public ProdutoTela() {
                initComponents();

                // Configura tabela
                modelo = (DefaultTableModel) jTable2.getModel();
                modelo.setRowCount(0); // limpa linhas padrão
        }

        private void initComponents() {

                jLabel1 = new javax.swing.JLabel();
                jTextField1 = new javax.swing.JTextField();
                jLabel2 = new javax.swing.JLabel();
                jTextField2 = new javax.swing.JTextField();
                jButton1 = new javax.swing.JButton();
                jButton2 = new javax.swing.JButton();
                jButton3 = new javax.swing.JButton();
                jButton4 = new javax.swing.JButton();
                jScrollPane2 = new javax.swing.JScrollPane();
                jTable2 = new javax.swing.JTable();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                jLabel1.setText("Nome:");
                jLabel2.setText("Valor:");

                jButton1.setText("Salvar");
                jButton1.addActionListener(evt -> salvarProduto());

                jButton2.setText("Cancelar");
                jButton2.addActionListener(evt -> {
                        limparCampos();
                        produtoSelecionadoId = -1;
                        jButton1.setText("Salvar");
                });

                jButton3.setText("Editar");
                jButton3.addActionListener(evt -> editarProduto());

                jButton4.setText("Excluir");
                jButton4.addActionListener(evt -> excluirProduto());

                jTable2.setModel(new DefaultTableModel(
                                new Object[][] {},
                                new String[] { "ID", "Nome", "Valor" }) {
                        Class[] types = new Class[] { Integer.class, String.class, Integer.class };

                        public Class getColumnClass(int columnIndex) {
                                return types[columnIndex];
                        }

                        public boolean isCellEditable(int row, int column) {
                                return false;
                        }
                });

                jScrollPane2.setViewportView(jTable2);

                // Layout simples
                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(20)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jScrollPane2,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                360,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addComponent(jLabel1)
                                                                                                .addGap(10)
                                                                                                .addComponent(jTextField1,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                200,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addComponent(jLabel2)
                                                                                                .addGap(10)
                                                                                                .addComponent(jTextField2,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                200,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addComponent(jButton1)
                                                                                                .addGap(10)
                                                                                                .addComponent(jButton2)
                                                                                                .addGap(10)
                                                                                                .addComponent(jButton3)
                                                                                                .addGap(10)
                                                                                                .addComponent(jButton4)))
                                                                .addContainerGap(20, Short.MAX_VALUE)));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(10)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel1)
                                                                                .addComponent(jTextField1,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                30,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(10)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel2)
                                                                                .addComponent(jTextField2,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                30,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(10)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jButton1)
                                                                                .addComponent(jButton2)
                                                                                .addComponent(jButton3)
                                                                                .addComponent(jButton4))
                                                                .addGap(20)
                                                                .addComponent(jScrollPane2,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                200,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(10, Short.MAX_VALUE)));

                pack();
        }

        // Salvar ou atualizar produto
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

                        if (produtoSelecionadoId >= 0) {
                                // Editar produto existente
                                for (Produto p : listaProdutos) {
                                        if (p.getId() == produtoSelecionadoId) {
                                                p.setNome(nome);
                                                p.setValor(valor);
                                                break;
                                        }
                                }
                                produtoSelecionadoId = -1;
                                jButton1.setText("Salvar");
                        } else {
                                // Novo produto
                                Produto p = new Produto(proximoId++, nome, valor);
                                listaProdutos.add(p);
                        }

                        atualizarTabela();
                        limparCampos();

                } catch (NumberFormatException e) {
                        javax.swing.JOptionPane.showMessageDialog(this, "Valor deve ser numérico!");
                }
        }

        // Editar produto
        private void editarProduto() {
                int linha = jTable2.getSelectedRow();
                if (linha < 0) {
                        javax.swing.JOptionPane.showMessageDialog(this, "Selecione um produto na tabela!");
                        return;
                }

                produtoSelecionadoId = (int) modelo.getValueAt(linha, 0);
                jTextField1.setText((String) modelo.getValueAt(linha, 1));
                jTextField2.setText(String.valueOf(modelo.getValueAt(linha, 2)));
                jButton1.setText("Atualizar");
        }

        // Excluir produto
        private void excluirProduto() {
                int linha = jTable2.getSelectedRow();
                if (linha < 0) {
                        javax.swing.JOptionPane.showMessageDialog(this, "Selecione um produto para excluir!");
                        return;
                }

                int idExcluir = (int) modelo.getValueAt(linha, 0);
                listaProdutos.removeIf(p -> p.getId() == idExcluir);
                atualizarTabela();
        }

        // Limpar campos
        private void limparCampos() {
                jTextField1.setText("");
                jTextField2.setText("");
                jTextField1.requestFocus();
        }

        // Atualizar tabela
        private void atualizarTabela() {
                modelo.setRowCount(0);
                for (Produto p : listaProdutos) {
                        modelo.addRow(new Object[] { p.getId(), p.getNome(), p.getValor() });
                }
        }

        public static void main(String[] args) {
                java.awt.EventQueue.invokeLater(() -> new ProdutoTela().setVisible(true));
        }

        // Variáveis
        private javax.swing.JButton jButton1; // Salvar/Atualizar
        private javax.swing.JButton jButton2; // Cancelar
        private javax.swing.JButton jButton3; // Editar
        private javax.swing.JButton jButton4; // Excluir
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JScrollPane jScrollPane2;
        private javax.swing.JTable jTable2;
        private javax.swing.JTextField jTextField1;
        private javax.swing.JTextField jTextField2;
}
