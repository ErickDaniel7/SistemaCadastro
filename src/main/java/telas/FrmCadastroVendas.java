/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package telas;

import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Erick Daniel Teixeira Vier - RA: 235908-1
 */
public class FrmCadastroVendas extends javax.swing.JFrame {

    /**
     * Creates new form FrmCadastroVendas
     */
    String strCaminhoArquivo = "C:\\Users\\erixk\\OneDrive\\Documentos\\CadastrarClientes\\CadastrarClientes\\src\\main\\java\\banco\\VENDAS.txt";

    public FrmCadastroVendas() {
        initComponents();
        configurarTabela();
        carregarClientesNoComboBox();
        carregarProdutosNoComboBox();
        carregarDados(strCaminhoArquivo);
    }
    
    private void carregarClientesNoComboBox() {
    DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
    String strCaminhoArquivo = "C:\\Users\\erixk\\OneDrive\\Documentos\\CadastrarClientes\\CadastrarClientes\\src\\main\\java\\banco\\CLIENTES.txt";
    
    // Lê o arquivo e adiciona os nomes dos clientes ao ComboBox
    try (BufferedReader br = new BufferedReader(new FileReader(strCaminhoArquivo))) {
        String linha;
        while ((linha = br.readLine()) != null) {  
            // Verifica se a linha começa com "Nome:"
            if (linha.startsWith("Nome:")) {
                // Extrai o nome do cliente, que vem após "Nome: "
                String nomeCliente = linha.substring("Nome:".length()).trim();
                modelo.addElement(nomeCliente);  // Adiciona o nome do cliente ao ComboBox
            }
        }
    } catch (IOException e) {
        System.out.println("Erro ao carregar Clientes: " + e.getMessage());
    }
    
    // Define o modelo do ComboBox com os nomes dos clientes
    cboCliente.setModel(modelo);
}
    
    private void carregarProdutosNoComboBox() {
    DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
    String strCaminhoArquivo = "C:\\Users\\erixk\\OneDrive\\Documentos\\CadastrarClientes\\CadastrarClientes\\src\\main\\java\\banco\\PRODUTOS.txt";
    
    // Lê o arquivo e adiciona os nomes dos produtos ao ComboBox
    try (BufferedReader br = new BufferedReader(new FileReader(strCaminhoArquivo))) {
        String linha;
        while ((linha = br.readLine()) != null) {  
            // Verifica se a linha começa com "Produto:"
            if (linha.startsWith("Nome:")) {
                // Extrai o produto, que vem após "Produto: "
                String nomeProduto = linha.substring("Nome:".length()).trim();
                modelo.addElement(nomeProduto);  // Adiciona o produto ao ComboBox
                System.out.println("Produto carregado: " + nomeProduto);  // Debug
            }
        }
    } catch (IOException e) {
        System.out.println("Erro ao carregar Produtos: " + e.getMessage());
    }
    
    // Define o modelo do ComboBox com os produtos
    cboProduto.setModel(modelo);
}



    private void carregarDados(String caminhoArquivo) {
        DefaultTableModel model = (DefaultTableModel) tblTabelaVenda.getModel();
        model.setRowCount(0);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(caminhoArquivo), StandardCharsets.UTF_8))) {
            String linha;
            String[] dados = new String[17];
            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                if (!linha.isEmpty() && !linha.startsWith("-")) {
                    String[] chaveValor = linha.split(":", 2);
                    if (chaveValor.length == 2) {
                        String chave = chaveValor[0].trim();
                        String valor = chaveValor[1].trim();
                        switch (chave) {
                            case "Cliente" -> dados[0] = valor;
                            case "Produto" -> dados[1] = valor;
                            case "Quantidade" -> dados[2] = valor;
                            case "Data da Venda" -> dados[3] = valor;
                            case "Valor Total" -> dados[4] = valor;
                            case "Forma de Pagamento" -> dados[5] = valor;
                            case "Status da Venda" -> dados[6] = valor;
                            case "Observacoes" -> dados[7] = valor;
                        }
                    }
                } else if (dados[0] != null) {
                    model.addRow(dados);
                    dados = new String[17];
                }
            }
            if (dados[0] != null) {
                model.addRow(dados);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar Informações!", "Mensagem do sistema!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void configurarTabela() {
        try {
            tblTabelaVenda.getColumnModel().getColumn(0).setPreferredWidth(200);
            tblTabelaVenda.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblTabelaVenda.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblTabelaVenda.getColumnModel().getColumn(3).setPreferredWidth(100);
            tblTabelaVenda.getColumnModel().getColumn(4).setPreferredWidth(100);
            tblTabelaVenda.getColumnModel().getColumn(5).setPreferredWidth(150);
            tblTabelaVenda.getColumnModel().getColumn(6).setPreferredWidth(150);
            tblTabelaVenda.getColumnModel().getColumn(7).setPreferredWidth(200);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao configurar tabela!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void gravarDados() {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(strCaminhoArquivo, true), "UTF-8"))) {
            writer.write("-------------------Cadastro de Vendas-------------------");
            writer.newLine();
            writer.write("Cliente: " + cboCliente.getSelectedItem().toString());
            writer.newLine();
            writer.write("Produto: " + cboProduto.getSelectedItem().toString());
            writer.newLine();
            writer.write("Quantidade: " + txtQuantidade.getText());
            writer.newLine();
            writer.write("Data da Venda: " + txtDtaVenda.getText());
            writer.newLine();
            writer.write("Valor Total: " + txtValorTotal.getText());
            writer.newLine();
            writer.write("Forma de Pagamento: " + cboFormaPagamento.getSelectedItem().toString());
            writer.newLine();
            writer.write("Status da Venda: " + cboStatusVenda.getSelectedItem().toString());
            writer.newLine();
            writer.write("Observacoes: " + txtObs.getText());
            writer.newLine();
            writer.newLine();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!", "Mensagem do sistema!", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar os dados: " + ex.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlInf = new javax.swing.JPanel();
        lbCliente = new javax.swing.JLabel();
        txtDtaVenda = new javax.swing.JFormattedTextField();
        lbDataDaVenda = new javax.swing.JLabel();
        lbProduto = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JTextField();
        lbQuantidade = new javax.swing.JLabel();
        cboCliente = new javax.swing.JComboBox<>();
        cboProduto = new javax.swing.JComboBox<>();
        pnlInfAdd = new javax.swing.JPanel();
        lblValorTotal = new javax.swing.JLabel();
        cboFormaPagamento = new javax.swing.JComboBox<>();
        lblFormaPagamento = new javax.swing.JLabel();
        cboStatusVenda = new javax.swing.JComboBox<>();
        lblStatusVenda = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObs = new javax.swing.JTextArea();
        lblObs = new javax.swing.JLabel();
        txtValorTotal = new javax.swing.JFormattedTextField();
        pnlAcoes = new javax.swing.JPanel();
        btnSalvar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        scrlPanel = new javax.swing.JScrollPane();
        tblTabelaVenda = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Clientes");

        pnlInf.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações"));
        pnlInf.setToolTipText("");

        lbCliente.setText("Cliente:");

        try {
            txtDtaVenda.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDtaVenda.setText("##/##/####");

        lbDataDaVenda.setText("Data da Venda:");

        lbProduto.setText("Produto:");

        lbQuantidade.setText("Quantidade:");

        javax.swing.GroupLayout pnlInfLayout = new javax.swing.GroupLayout(pnlInf);
        pnlInf.setLayout(pnlInfLayout);
        pnlInfLayout.setHorizontalGroup(
            pnlInfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCliente)
                    .addComponent(cboCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInfLayout.createSequentialGroup()
                        .addComponent(lbProduto)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(cboProduto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbQuantidade)
                    .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbDataDaVenda)
                    .addComponent(txtDtaVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnlInfLayout.setVerticalGroup(
            pnlInfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnlInfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlInfLayout.createSequentialGroup()
                        .addComponent(lbQuantidade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlInfLayout.createSequentialGroup()
                        .addGroup(pnlInfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbCliente)
                            .addComponent(lbDataDaVenda)
                            .addComponent(lbProduto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlInfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDtaVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pnlInfAdd.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações de Pagamento"));

        lblValorTotal.setText("Valor Total");

        cboFormaPagamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dinheiro", "Cartão de Débito", "Pix", "Cartão de Crédito", "Boleto Bancário" }));

        lblFormaPagamento.setText("Forma de Pagamento");

        cboStatusVenda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Paga", "Aberta", "Cancelada" }));

        lblStatusVenda.setText("Status da Venda:");

        txtObs.setColumns(20);
        txtObs.setRows(5);
        jScrollPane1.setViewportView(txtObs);

        lblObs.setText("Observação:");

        txtValorTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        txtValorTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorTotalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlInfAddLayout = new javax.swing.GroupLayout(pnlInfAdd);
        pnlInfAdd.setLayout(pnlInfAddLayout);
        pnlInfAddLayout.setHorizontalGroup(
            pnlInfAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfAddLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInfAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInfAddLayout.createSequentialGroup()
                        .addComponent(lblObs)
                        .addGap(458, 458, 458))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInfAddLayout.createSequentialGroup()
                        .addGroup(pnlInfAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblValorTotal)
                            .addComponent(txtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlInfAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFormaPagamento)
                            .addComponent(cboFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlInfAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboStatusVenda, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlInfAddLayout.createSequentialGroup()
                                .addComponent(lblStatusVenda)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        pnlInfAddLayout.setVerticalGroup(
            pnlInfAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfAddLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(pnlInfAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblValorTotal)
                    .addComponent(lblFormaPagamento)
                    .addComponent(lblStatusVenda))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInfAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboStatusVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblObs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlAcoes.setBorder(javax.swing.BorderFactory.createTitledBorder("Ações"));

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Salvar.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Editar.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.setPreferredSize(new java.awt.Dimension(81, 23));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Excluir.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Limpar.png"))); // NOI18N
        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlAcoesLayout = new javax.swing.GroupLayout(pnlAcoes);
        pnlAcoes.setLayout(pnlAcoesLayout);
        pnlAcoesLayout.setHorizontalGroup(
            pnlAcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAcoesLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        pnlAcoesLayout.setVerticalGroup(
            pnlAcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAcoesLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnlAcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        scrlPanel.setToolTipText("");
        scrlPanel.setViewportBorder(javax.swing.BorderFactory.createTitledBorder(""));
        scrlPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        scrlPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                scrlPanelMouseClicked(evt);
            }
        });

        tblTabelaVenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cliente", "Produto", "Quantidade", "Data da Venda", "Valor Total", "Forma de Pagamento", "Status da Venda", "Observação"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTabelaVenda.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblTabelaVenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTabelaVendaMouseClicked(evt);
            }
        });
        scrlPanel.setViewportView(tblTabelaVenda);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Venda.png"))); // NOI18N
        jLabel2.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlInf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlInfAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnlAcoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(scrlPanel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlInf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlInfAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(5, 5, 5)
                        .addComponent(pnlAcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlInf.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if (validarCampos()) {
    gravarDados();
    carregarDados(strCaminhoArquivo);
}
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void limpar() {
        try {
            cboCliente.setSelectedIndex(0);
            cboProduto.setSelectedIndex(0);
            txtQuantidade.setText("");
            txtDtaVenda.setText("");
            txtValorTotal.setText("");
            txtValorTotal.setText("");
            cboFormaPagamento.setSelectedIndex(0);
            cboStatusVenda.setSelectedIndex(0);
            txtObs.setText("");
            cboCliente.requestFocus();
            cboProduto.requestFocus();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao limpar campos", "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        limpar();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void excluir(String caminhoArquivo, String nomeCliente, String produtoExcluir) throws IOException {
    List<String> linhas = new ArrayList<>();
    boolean dentroDaVenda = false;
    boolean dentroDoCadastroDeCliente = false;
    boolean deletouVenda = false;

    try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
        String linha;

        while ((linha = br.readLine()) != null) {
            // Verifica quando começa um bloco de cadastro de vendas
            if (linha.startsWith("-------------------Cadastro de Vendas-------------------")) {
                dentroDaVenda = true;  // Sai do bloco de vendas ao encontrá-lo
            }

            // Se o nome do cliente for encontrado dentro do bloco de vendas
            if (linha.startsWith("Cliente:") && linha.contains(nomeCliente)) {
                deletouVenda = true; // Marca que a venda foi deletada
                dentroDaVenda = true;  // Está dentro do bloco de vendas para o cliente específico
                continue;
            }

            // Quando a linha marca o final de um bloco de vendas, deve ser ignorada se o bloco foi deletado
            if (dentroDaVenda && linha.startsWith("-------------------Cadastro de Vendas-------------------")) {
                dentroDaVenda = false; // Sai do bloco de vendas
                continue;
            }

            // Se não estiver no bloco de vendas que precisa ser excluído, adiciona a linha na lista
            if (!dentroDaVenda) {
                if (linha.startsWith("-------------------Cadastro de Clientes-------------------") && !deletouVenda) {
                    dentroDoCadastroDeCliente = true;
                    linhas.add(linha);  // Adiciona as linhas de "Cadastro de Clientes"
                } else {
                    linhas.add(linha);  // Adiciona as outras linhas
                }
            }
        }
    } catch (IOException ex) {
        throw new IOException("Erro ao ler o arquivo para exclusão", ex);
    }

    // Regrava as linhas de volta no arquivo sem o bloco de venda removido
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))) {
        for (String linha : linhas) {
            bw.write(linha);
            bw.newLine();
        }
    } catch (IOException ex) {
        throw new IOException("Erro ao gravar os dados no arquivo após exclusão", ex);
    }
}

    private void editar(String caminhoArquivo) throws IOException {
        List<String> linhas = new ArrayList<>();
        String clienteEditado = cboCliente.getSelectedItem().toString();
        String produtoEditado = cboProduto.getSelectedItem().toString();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            boolean encontrado = false;
            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("Cliente:") && linha.contains(clienteEditado) && linha.contains(produtoEditado)) {
                    encontrado = true;
                    linhas.add("Cliente: " + cboCliente.getSelectedItem().toString());
                    linhas.add("Produto: " + cboProduto.getSelectedItem().toString());
                    linhas.add("Quantidade: " + txtQuantidade.getText());
                    linhas.add("Data da Venda: " + txtDtaVenda.getText());
                    linhas.add("Valor Total: " + txtValorTotal.getText());
                    linhas.add("Forma de Pagamento: " + cboFormaPagamento.getSelectedItem().toString());
                    linhas.add("Status da Venda: " + cboStatusVenda.getSelectedItem().toString());
                    linhas.add("Observacoes: " + txtObs.getText());
                    linhas.add("------------------------------------------------------");
                    while ((linha = br.readLine()) != null && !linha.startsWith("Nome:")) {
                    }
                }
                if (linha != null) {
                    linhas.add(linha);
                }
            }
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            for (String linha : linhas) {
                bw.write(linha);
                bw.newLine();
            }
            JOptionPane.showMessageDialog(null, "Cadastro atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar o arquivo!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void carregarDadosNosCampos(int row) {
        try {
            cboCliente.setSelectedItem (tblTabelaVenda.getValueAt(row, 0));
            cboProduto.setSelectedItem (tblTabelaVenda.getValueAt(row, 1));
            txtQuantidade.setText((String) tblTabelaVenda.getValueAt(row, 2));
            txtDtaVenda.setText((String) tblTabelaVenda.getValueAt(row, 3));
            txtValorTotal.setText((String) tblTabelaVenda.getValueAt(row, 4));
            cboFormaPagamento.setSelectedItem(tblTabelaVenda.getValueAt(row, 5));
            cboStatusVenda.setSelectedItem(tblTabelaVenda.getValueAt(row, 6));
            txtObs.setText((String) tblTabelaVenda.getValueAt(row, 7));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados nos campos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean validarCampos() {
    if (txtQuantidade.getText().trim().isEmpty() ||
        txtDtaVenda.getText().trim().isEmpty() ||
        txtValorTotal.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos obrigatórios.");
        return false;
    }
    return true;
}

    private void tblTabelaVendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTabelaVendaMouseClicked
        tblTabelaVenda.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = tblTabelaVenda.getSelectedRow();
                    carregarDadosNosCampos(row);
                }
            }
        });
    }//GEN-LAST:event_tblTabelaVendaMouseClicked

    private void scrlPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scrlPanelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_scrlPanelMouseClicked

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        try {
            editar(strCaminhoArquivo);
            carregarDados(strCaminhoArquivo);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao editar o cadastro!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
    try {
        // Obtem o nome do cliente selecionado no ComboBox
        String nomeExcluir = cboCliente.getSelectedItem().toString();
        String produtoExcluir = cboProduto.getSelectedItem().toString();
        
        // Verifica se o nome do cliente está vazio (caso o usuário não tenha selecionado nenhum)
        if (nomeExcluir.isEmpty() || produtoExcluir.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione um cliente e um produto para excluir", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            // Chama a função excluir passando o caminho do arquivo e o nome do cliente a ser excluído
            excluir(strCaminhoArquivo, nomeExcluir, produtoExcluir);

            // Recarrega os dados na tabela após a exclusão (atualiza a interface)
            carregarDados(strCaminhoArquivo);

            // Limpa os campos do formulário
            limpar();

            JOptionPane.showMessageDialog(null, "Registro excluído com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
    } catch (IOException ex) {
        // Exibe mensagem de erro caso haja falha ao excluir
        JOptionPane.showMessageDialog(null, "Erro ao excluir registro", "Erro", JOptionPane.ERROR_MESSAGE);
    }


    }//GEN-LAST:event_btnExcluirActionPerformed

    private void txtValorTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorTotalActionPerformed

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
            java.util.logging.Logger.getLogger(FrmCadastroVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCadastroVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCadastroVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCadastroVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmCadastroVendas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cboCliente;
    private javax.swing.JComboBox<String> cboFormaPagamento;
    private javax.swing.JComboBox<String> cboProduto;
    private javax.swing.JComboBox<String> cboStatusVenda;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbCliente;
    private javax.swing.JLabel lbDataDaVenda;
    private javax.swing.JLabel lbProduto;
    private javax.swing.JLabel lbQuantidade;
    private javax.swing.JLabel lblFormaPagamento;
    private javax.swing.JLabel lblObs;
    private javax.swing.JLabel lblStatusVenda;
    private javax.swing.JLabel lblValorTotal;
    private javax.swing.JPanel pnlAcoes;
    private javax.swing.JPanel pnlInf;
    private javax.swing.JPanel pnlInfAdd;
    private javax.swing.JScrollPane scrlPanel;
    private javax.swing.JTable tblTabelaVenda;
    private javax.swing.JFormattedTextField txtDtaVenda;
    private javax.swing.JTextArea txtObs;
    private javax.swing.JTextField txtQuantidade;
    private javax.swing.JFormattedTextField txtValorTotal;
    // End of variables declaration//GEN-END:variables

}
