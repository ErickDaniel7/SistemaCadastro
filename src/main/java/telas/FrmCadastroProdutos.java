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
public class FrmCadastroProdutos extends javax.swing.JFrame {

    /**
     * Creates new form FrmCadastroProdutos
     */
    String strCaminhoArquivo = "src\\main\\java\\banco\\PRODUTOS.txt";

    public FrmCadastroProdutos() {
        initComponents();
        configurarTabela();
        carregarFornecedoresNoComboBox();
        carregarDados(strCaminhoArquivo);
    }
    
    private void carregarFornecedoresNoComboBox() {
    DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
    String strCaminhoArquivo = "src\\main\\java\\banco\\FORNECEDORES.txt";
    
    // Lê o arquivo e adiciona os nomes dos Fornecedores ao ComboBox
    try (BufferedReader br = new BufferedReader(new FileReader(strCaminhoArquivo))) {
        String linha;
        while ((linha = br.readLine()) != null) {  
            // Verifica se a linha começa com "Nome da Empresa:"
            if (linha.startsWith("Nome da Empresa:")) {
                // Extrai o nome da Empresa, que vem após "Nome da Empresa: "
                String nomeEmpresa = linha.substring("Nome da Empresa:".length()).trim();
                modelo.addElement(nomeEmpresa);  // Adiciona o nome do cliente ao ComboBox
            }
        }
    } catch (IOException e) {
        System.out.println("Erro ao carregar Clientes: " + e.getMessage());
    }
    
    // Define o modelo do ComboBox com os nomes dos Fornecedores
    cboFornecedor.setModel(modelo);
}


    private void carregarDados(String caminhoArquivo) {
        DefaultTableModel model = (DefaultTableModel) tblTabelaProduto.getModel();
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
                            case "Nome" -> dados[0] = valor;
                            case "Codigo" -> dados[1] = valor;
                            case "Quantidade em Estoque" -> dados[2] = valor;
                            case "Fornecedor" -> dados[3] = valor;
                            case "Categoria" -> dados[4] = valor;
                            case "Data de Validade" -> dados[5] = valor;
                            case "Observacoes" -> dados[6] = valor;
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
            tblTabelaProduto.getColumnModel().getColumn(0).setPreferredWidth(200);
            tblTabelaProduto.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblTabelaProduto.getColumnModel().getColumn(2).setPreferredWidth(200);
            tblTabelaProduto.getColumnModel().getColumn(3).setPreferredWidth(150);
            tblTabelaProduto.getColumnModel().getColumn(4).setPreferredWidth(100);
            tblTabelaProduto.getColumnModel().getColumn(5).setPreferredWidth(150);
            tblTabelaProduto.getColumnModel().getColumn(6).setPreferredWidth(150);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao configurar tabela!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void gravarDados() {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(strCaminhoArquivo, true), "UTF-8"))) {
            writer.write("-------------------Cadastro de Vendas-------------------");
            writer.newLine();
            writer.write("Nome: " + txtNome.getText());
            writer.newLine();
            writer.write("Codigo: " + txtCodigo.getText());
            writer.newLine();
            writer.write("Quantidade em Estoque: " + txtQuantidadeEstoque.getText());
            writer.newLine();
            writer.write("Fornecedor: " + cboFornecedor.getSelectedItem().toString());
            writer.newLine();
            writer.write("Categoria: " + txtCategoria.getText());
            writer.newLine();
            writer.write("Data de Validade: " + txtDtaValidade.getText());
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
        lbNome = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        lblCodigo = new javax.swing.JLabel();
        txtQuantidadeEstoque = new javax.swing.JTextField();
        lbQuantidadeEstoque = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        pnlInfAdd = new javax.swing.JPanel();
        lblFornecedor = new javax.swing.JLabel();
        lblCategoria = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObs = new javax.swing.JTextArea();
        lblObs = new javax.swing.JLabel();
        txtCategoria = new javax.swing.JTextField();
        txtDtaValidade = new javax.swing.JFormattedTextField();
        lbDataDaValidade = new javax.swing.JLabel();
        cboFornecedor = new javax.swing.JComboBox<>();
        pnlAcoes = new javax.swing.JPanel();
        btnSalvar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        scrlPanel = new javax.swing.JScrollPane();
        tblTabelaProduto = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Clientes");

        pnlInf.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações"));
        pnlInf.setToolTipText("");

        lbNome.setText("Nome:");

        lblCodigo.setText("Código:");

        lbQuantidadeEstoque.setText("Quantidade em Estoque:");

        javax.swing.GroupLayout pnlInfLayout = new javax.swing.GroupLayout(pnlInf);
        pnlInf.setLayout(pnlInfLayout);
        pnlInfLayout.setHorizontalGroup(
            pnlInfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNome)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInfLayout.createSequentialGroup()
                        .addComponent(lblCodigo)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtCodigo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbQuantidadeEstoque)
                    .addComponent(txtQuantidadeEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnlInfLayout.setVerticalGroup(
            pnlInfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnlInfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlInfLayout.createSequentialGroup()
                        .addComponent(lbQuantidadeEstoque)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtQuantidadeEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlInfLayout.createSequentialGroup()
                        .addGroup(pnlInfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbNome)
                            .addComponent(lblCodigo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlInfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pnlInfAdd.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações Adicionais"));

        lblFornecedor.setText("Fornecedor:");

        lblCategoria.setText("Categoria:");

        txtObs.setColumns(20);
        txtObs.setRows(5);
        jScrollPane1.setViewportView(txtObs);

        lblObs.setText("Observação:");

        try {
            txtDtaValidade.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDtaValidade.setText("##/##/####");

        lbDataDaValidade.setText("Data de Validade:");

        javax.swing.GroupLayout pnlInfAddLayout = new javax.swing.GroupLayout(pnlInfAdd);
        pnlInfAdd.setLayout(pnlInfAddLayout);
        pnlInfAddLayout.setHorizontalGroup(
            pnlInfAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfAddLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInfAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInfAddLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblObs)
                        .addGap(458, 458, 458))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInfAddLayout.createSequentialGroup()
                        .addGroup(pnlInfAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFornecedor)
                            .addComponent(cboFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlInfAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCategoria)
                            .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlInfAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlInfAddLayout.createSequentialGroup()
                                .addComponent(lbDataDaValidade)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtDtaValidade)))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        pnlInfAddLayout.setVerticalGroup(
            pnlInfAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfAddLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(pnlInfAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlInfAddLayout.createSequentialGroup()
                        .addGroup(pnlInfAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFornecedor)
                            .addComponent(lblCategoria))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlInfAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlInfAddLayout.createSequentialGroup()
                        .addComponent(lbDataDaValidade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDtaValidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

        tblTabelaProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Codigo", "Quantidade em Estoque", "Fornecedor", "Categoria", "Data de Validade", "Observação"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTabelaProduto.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblTabelaProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTabelaProdutoMouseClicked(evt);
            }
        });
        scrlPanel.setViewportView(tblTabelaProduto);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Produto.png"))); // NOI18N
        jLabel2.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlInf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
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
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
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
            txtNome.setText("");
            txtCodigo.setText("");
            txtQuantidadeEstoque.setText("");
            txtDtaValidade.setText("");
            cboFornecedor.setSelectedIndex(0);
            txtCategoria.setText("");
            txtDtaValidade.setText("");
            txtObs.setText("");
            cboFornecedor.requestFocus();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao limpar campos", "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        limpar();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void excluir(String caminhoArquivo, String nomeExcluir) throws IOException {
        List<String> linhas = new ArrayList<>();
        boolean excluir = false;

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("Nome:") && linha.contains(nomeExcluir)) {
                    excluir = true;
                }
                if (excluir) {
                    if (linha.startsWith("------------------------------------------------------")) {
                        excluir = false;
                        continue;
                    }
                } else {
                    linhas.add(linha);
                }
            }
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            for (String linha : linhas) {
                bw.write(linha);
                bw.newLine();
            }
            JOptionPane.showMessageDialog(null, "Registro deletado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar o arquivo!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

  private void editar(String caminhoArquivo) throws IOException {
    List<String> linhas = new ArrayList<>();
    String nomeProdutoEditar = txtNome.getText().trim(); // Pega o nome do produto a ser editado

    try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
        String linha;
        StringBuilder produtoAtual = new StringBuilder(); // Usado para armazenar um produto inteiro
        boolean produtoEditado = false;

        while ((linha = br.readLine()) != null) {
            produtoAtual.append(linha).append("\n"); // Adiciona a linha ao produto atual

            // Verifica se encontramos o final de um produto (usando o marcador "------------------------------------------------------")
            if (linha.startsWith("------------------------------------------------------")) {
                // Verifica se o nome do produto corresponde ao nome que queremos editar
                if (produtoAtual.toString().contains("Nome: " + nomeProdutoEditar)) {
                    produtoEditado = true;
                    // Substitui o bloco inteiro de dados do produto com as novas informações
                    linhas.add("-------------------Cadastro de Vendas-------------------");
                    linhas.add("Nome: " + txtNome.getText().trim());
                    linhas.add("Codigo: " + txtCodigo.getText().trim());
                    linhas.add("Quantidade em Estoque: " + txtQuantidadeEstoque.getText().trim());
                    linhas.add("Fornecedor: " + cboFornecedor.getSelectedItem().toString().trim());
                    linhas.add("Categoria: " + txtCategoria.getText().trim());
                    linhas.add("Data de Validade: " + txtDtaValidade.getText().trim());
                    linhas.add("Observacoes: " + txtObs.getText().trim());
                    linhas.add("------------------------------------------------------");
                } else {
                    // Se não for o produto a ser editado, adiciona o bloco do produto atual
                    linhas.add(produtoAtual.toString());
                }
                // Reseta o StringBuilder para o próximo produto
                produtoAtual.setLength(0);
            }
        }

        // Caso o produto não tenha sido encontrado
        if (!produtoEditado) {
            JOptionPane.showMessageDialog(null, "Produto não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Erro ao ler o arquivo!", "Erro", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Regrava todas as linhas no arquivo, incluindo as alterações feitas
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))) {
        for (String linha : linhas) {
            bw.write(linha);
            bw.newLine(); // Garante que as linhas sejam separadas
        }
        JOptionPane.showMessageDialog(null, "Cadastro atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Erro ao salvar o arquivo!", "Erro", JOptionPane.ERROR_MESSAGE);
    }
}

    private void carregarDadosNosCampos(int row) {
        try {
            txtNome.setText((String) tblTabelaProduto.getValueAt(row, 0));
            txtCodigo.setText((String) tblTabelaProduto.getValueAt(row, 1));
            txtQuantidadeEstoque.setText((String) tblTabelaProduto.getValueAt(row, 2));
            cboFornecedor.setSelectedItem(tblTabelaProduto.getValueAt(row, 3));
            txtCategoria.setText((String) tblTabelaProduto.getValueAt(row, 4));
            txtDtaValidade.setText((String) tblTabelaProduto.getValueAt(row, 5));
            txtObs.setText((String) tblTabelaProduto.getValueAt(row, 6));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados nos campos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean validarCampos() {
    if (txtNome.getText().trim().isEmpty() ||
        txtCodigo.getText().trim().isEmpty() ||
        txtQuantidadeEstoque.getText().trim().isEmpty() ||
        txtCategoria.getText().trim().isEmpty() ||
        txtDtaValidade.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos obrigatórios.");
        return false;
    }
    return true;
}

    private void tblTabelaProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTabelaProdutoMouseClicked
        // Verifica se o clique foi duplo
    if (evt.getClickCount() == 2) {
        int row = tblTabelaProduto.getSelectedRow();
        carregarDadosNosCampos(row);  // Carrega os dados da linha selecionada nos campos
    }

    }//GEN-LAST:event_tblTabelaProdutoMouseClicked

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
            String nomeExcluir = txtNome.getText();
            if (nomeExcluir.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Selecione um registro para excluir", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                excluir(strCaminhoArquivo, nomeExcluir);
                carregarDados(strCaminhoArquivo);
                limpar();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir registro", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

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
            java.util.logging.Logger.getLogger(FrmCadastroProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCadastroProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCadastroProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCadastroProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmCadastroProdutos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cboFornecedor;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbDataDaValidade;
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbQuantidadeEstoque;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblFornecedor;
    private javax.swing.JLabel lblObs;
    private javax.swing.JPanel pnlAcoes;
    private javax.swing.JPanel pnlInf;
    private javax.swing.JPanel pnlInfAdd;
    private javax.swing.JScrollPane scrlPanel;
    private javax.swing.JTable tblTabelaProduto;
    private javax.swing.JTextField txtCategoria;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JFormattedTextField txtDtaValidade;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextArea txtObs;
    private javax.swing.JTextField txtQuantidadeEstoque;
    // End of variables declaration//GEN-END:variables

}
