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
public class FrmCadastroFornecedores extends javax.swing.JFrame {

    /**
     * Creates new form FrmCadastroFornecedores
     */
    String strCaminhoArquivo = "src\\main\\java\\banco\\FORNECEDORES.txt";

    public FrmCadastroFornecedores() {
        initComponents();
        configurarTabela();
        carregarDados(strCaminhoArquivo);
    }

    private void carregarDados(String caminhoArquivo) {
        DefaultTableModel model = (DefaultTableModel) tblTabelaFornecedor.getModel();
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
                            case "Nome da Empresa" -> dados[0] = valor;
                            case "CNPJ" -> dados[1] = valor;
                            case "CEP" -> dados[2] = valor;
                            case "Estado" -> dados[3] = valor;
                            case "Cidade" -> dados[4] = valor;
                            case "Bairro" -> dados[5] = valor;
                            case "Logradouro" -> dados[6] = valor;
                            case "Complemento" -> dados[7] = valor;
                            case "Numero" -> dados[8] = valor;
                            case "E-mail" -> dados[9] = valor;
                            case "Telefone" -> dados[10] = valor;
                            case "Profissao" -> dados[11] = valor;
                            case "Tipo" -> dados[12] = valor;
                            case "Status" -> dados[13] = valor;
                            case "Observacoes" -> dados[14] = valor;
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
            tblTabelaFornecedor.getColumnModel().getColumn(0).setPreferredWidth(250);
            tblTabelaFornecedor.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblTabelaFornecedor.getColumnModel().getColumn(2).setPreferredWidth(150);
            tblTabelaFornecedor.getColumnModel().getColumn(3).setPreferredWidth(150);
            tblTabelaFornecedor.getColumnModel().getColumn(4).setPreferredWidth(100);
            tblTabelaFornecedor.getColumnModel().getColumn(5).setPreferredWidth(50);
            tblTabelaFornecedor.getColumnModel().getColumn(6).setPreferredWidth(150);
            tblTabelaFornecedor.getColumnModel().getColumn(7).setPreferredWidth(180);
            tblTabelaFornecedor.getColumnModel().getColumn(8).setPreferredWidth(200);
            tblTabelaFornecedor.getColumnModel().getColumn(9).setPreferredWidth(130);
            tblTabelaFornecedor.getColumnModel().getColumn(10).setPreferredWidth(100);
            tblTabelaFornecedor.getColumnModel().getColumn(11).setPreferredWidth(200);
            tblTabelaFornecedor.getColumnModel().getColumn(12).setPreferredWidth(150);
            tblTabelaFornecedor.getColumnModel().getColumn(13).setPreferredWidth(200);
            tblTabelaFornecedor.getColumnModel().getColumn(14).setPreferredWidth(150);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao configurar tabela!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void gravarDados() {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(strCaminhoArquivo, true), "UTF-8"))) {
            writer.write("-------------------Cadastro de Fornecedores-------------------");
            writer.newLine();
            writer.write("Nome da Empresa: " + txtNomeEmpresa.getText());
            writer.newLine();
            writer.write("CNPJ: " + txtCNPJ.getText());
            writer.newLine();
            writer.write("CEP: " + txtCEP.getText());
            writer.newLine();
            writer.write("Estado: " + txtEstado.getText());
            writer.newLine();
            writer.write("Cidade: " + txtCidade.getText());
            writer.newLine();
            writer.write("Bairro: " + txtBairro.getText());
            writer.newLine();
            writer.write("Logradouro: " + txtLogradouro.getText());
            writer.newLine();
            writer.write("Complemento: " + cboComplemento.getSelectedItem().toString());
            writer.newLine();
            writer.write("Numero: " + txtNumero.getText());
            writer.newLine();
            writer.write("E-mail: " + txtEmail.getText());
            writer.newLine();
            writer.write("Telefone: " + txtTelefone.getText());
            writer.newLine();
            writer.write("Nome do Responsavel: " + txtResponsavel.getText());
            writer.newLine();
            writer.write("Tipo de Fornecedor: " + cboTipo.getSelectedItem().toString());
            writer.newLine();
            writer.write("Status: " + cboStatus.getSelectedItem().toString());
            writer.newLine();
            writer.write("Observacoes: " + txtObs.getText());
            writer.newLine();
            writer.newLine();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!", "Mensagem do sistema!", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar os dados: " + ex.getMessage());
        }
    }

    private static String buscarDadosPorCep(String strCEP) {
        StringBuilder strResultado = new StringBuilder();
        HttpURLConnection httpConn = null;

        try {
            String strUrlString = "https://viacep.com.br/ws/" + strCEP + "/json/";
            URL url = new URL(strUrlString);
            httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");

            if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                try (BufferedReader brIn = new BufferedReader(new InputStreamReader(httpConn.getInputStream()))) {
                    String strlinha;
                    while ((strlinha = brIn.readLine()) != null) {
                        strResultado.append(strlinha);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "Erro ao buscar CEP! código " + httpConn.getResponseCode(), "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao acessar os dados: " + e.getMessage() + "Erro!" + JOptionPane.ERROR_MESSAGE);
        } finally {
            Optional.ofNullable(httpConn).ifPresent(HttpURLConnection::disconnect);
        }

        return strResultado.toString();
    }

    private void preencherCampos(String dados) {
        try {
            JSONObject json = new JSONObject(dados);
            if (!json.has("erro")) {
                txtEstado.setText(json.getString("uf"));
                txtCidade.setText(json.getString("localidade"));
                txtBairro.setText(json.getString("bairro"));
                txtLogradouro.setText(json.getString("logradouro"));
            } else {
                JOptionPane.showMessageDialog(null, "CEP não encontrado", "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        } catch (JSONException e) {
            JOptionPane.showMessageDialog(null, "Erro ao processar os dados", "Erro!", JOptionPane.ERROR_MESSAGE);
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

        pnlInf = new javax.swing.JPanel();
        lblNome = new javax.swing.JLabel();
        txtNomeEmpresa = new javax.swing.JTextField();
        lblCPF = new javax.swing.JLabel();
        txtCNPJ = new javax.swing.JFormattedTextField();
        pnlEndereco = new javax.swing.JPanel();
        lblCEP = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        txtEstado = new javax.swing.JTextField();
        lblCidade = new javax.swing.JLabel();
        txtCidade = new javax.swing.JTextField();
        lblBairro = new javax.swing.JLabel();
        txtBairro = new javax.swing.JTextField();
        txtLogradouro = new javax.swing.JTextField();
        lblLogradouro = new javax.swing.JLabel();
        cboComplemento = new javax.swing.JComboBox<>();
        lblComplemento = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        lblNumero = new javax.swing.JLabel();
        txtCEP = new javax.swing.JFormattedTextField();
        pnlContato = new javax.swing.JPanel();
        txtEmail = new javax.swing.JTextField();
        txtTelefone = new javax.swing.JFormattedTextField();
        lblEmail = new javax.swing.JLabel();
        lblWhats = new javax.swing.JLabel();
        pnlInfAdd = new javax.swing.JPanel();
        txtResponsavel = new javax.swing.JTextField();
        lblProfissao = new javax.swing.JLabel();
        cboTipo = new javax.swing.JComboBox<>();
        lblTipo = new javax.swing.JLabel();
        cboStatus = new javax.swing.JComboBox<>();
        lblStatus = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObs = new javax.swing.JTextArea();
        lblObs = new javax.swing.JLabel();
        pnlAcoes = new javax.swing.JPanel();
        btnSalvar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        scrlPanel = new javax.swing.JScrollPane();
        tblTabelaFornecedor = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Clientes");

        pnlInf.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações"));
        pnlInf.setToolTipText("");

        lblNome.setText("Nome da Empresa:");

        lblCPF.setText("CNPJ:");

        try {
            txtCNPJ.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout pnlInfLayout = new javax.swing.GroupLayout(pnlInf);
        pnlInf.setLayout(pnlInfLayout);
        pnlInfLayout.setHorizontalGroup(
            pnlInfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInfLayout.createSequentialGroup()
                        .addComponent(lblNome)
                        .addGap(0, 590, Short.MAX_VALUE))
                    .addComponent(txtNomeEmpresa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCPF)
                    .addComponent(txtCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnlInfLayout.setVerticalGroup(
            pnlInfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnlInfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInfLayout.createSequentialGroup()
                        .addComponent(lblNome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNomeEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlInfLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblCPF)
                        .addGap(3, 3, 3)
                        .addComponent(txtCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlEndereco.setBorder(javax.swing.BorderFactory.createTitledBorder("Endereço"));

        lblCEP.setText("CEP:");

        lblEstado.setText("Estado:");

        txtEstado.setToolTipText("");

        lblCidade.setText("Cidade:");

        lblBairro.setText("Bairro:");

        lblLogradouro.setText("Logradouro:");

        cboComplemento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CASA", "APARTAMENTO" }));

        lblComplemento.setText("Complemento:");

        lblNumero.setText("Número:");

        try {
            txtCEP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCEP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCEPKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout pnlEnderecoLayout = new javax.swing.GroupLayout(pnlEndereco);
        pnlEndereco.setLayout(pnlEnderecoLayout);
        pnlEnderecoLayout.setHorizontalGroup(
            pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEnderecoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEnderecoLayout.createSequentialGroup()
                        .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCEP)
                            .addComponent(txtCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEstado))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlEnderecoLayout.createSequentialGroup()
                                .addComponent(lblCidade)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtCidade))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblBairro)
                            .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlEnderecoLayout.createSequentialGroup()
                        .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLogradouro))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlEnderecoLayout.createSequentialGroup()
                                .addComponent(lblComplemento)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(cboComplemento, 0, 379, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNumero)
                            .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        pnlEnderecoLayout.setVerticalGroup(
            pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEnderecoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCEP)
                    .addComponent(lblEstado)
                    .addComponent(lblCidade)
                    .addComponent(lblBairro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLogradouro)
                    .addComponent(lblComplemento)
                    .addComponent(lblNumero))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pnlContato.setBorder(javax.swing.BorderFactory.createTitledBorder("Contatos"));
        pnlContato.setPreferredSize(new java.awt.Dimension(830, 156));

        try {
            txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        lblEmail.setText("E-mail:");

        lblWhats.setText("Telefone:");

        javax.swing.GroupLayout pnlContatoLayout = new javax.swing.GroupLayout(pnlContato);
        pnlContato.setLayout(pnlContatoLayout);
        pnlContatoLayout.setHorizontalGroup(
            pnlContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContatoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEmail)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblWhats)
                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(131, 131, 131))
        );
        pnlContatoLayout.setVerticalGroup(
            pnlContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContatoLayout.createSequentialGroup()
                .addGap(0, 25, Short.MAX_VALUE)
                .addGroup(pnlContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(lblWhats))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlContatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        pnlInfAdd.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações adicionais"));

        lblProfissao.setText("Nome do Responsável:");

        cboTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fornecedor de Matéria Prima", "Fornecedor de Equipamentos" }));

        lblTipo.setText("Tipo de Fornecedor:");

        cboStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ATIVO", "INATIVO", "EM ANALISE" }));

        lblStatus.setText("Status:");

        txtObs.setColumns(20);
        txtObs.setRows(5);
        jScrollPane1.setViewportView(txtObs);

        lblObs.setText("Observação:");

        javax.swing.GroupLayout pnlInfAddLayout = new javax.swing.GroupLayout(pnlInfAdd);
        pnlInfAdd.setLayout(pnlInfAddLayout);
        pnlInfAddLayout.setHorizontalGroup(
            pnlInfAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfAddLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInfAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInfAddLayout.createSequentialGroup()
                        .addComponent(lblObs)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlInfAddLayout.createSequentialGroup()
                        .addGroup(pnlInfAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInfAddLayout.createSequentialGroup()
                                .addGroup(pnlInfAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblProfissao)
                                    .addComponent(txtResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlInfAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(pnlInfAddLayout.createSequentialGroup()
                                        .addComponent(lblTipo)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlInfAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblStatus)
                                    .addComponent(cboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())))
        );
        pnlInfAddLayout.setVerticalGroup(
            pnlInfAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfAddLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(pnlInfAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProfissao)
                    .addComponent(lblTipo)
                    .addComponent(lblStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInfAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGap(49, 49, 49)
                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlAcoesLayout.setVerticalGroup(
            pnlAcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAcoesLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnlAcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        scrlPanel.setToolTipText("");
        scrlPanel.setViewportBorder(javax.swing.BorderFactory.createTitledBorder(""));
        scrlPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        scrlPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                scrlPanelMouseClicked(evt);
            }
        });

        tblTabelaFornecedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome da Empresa", "CNPJ", "CEP", "Estado", "Cidade", "Bairro", "Logradouro", "Complemento", "Número", "E-mail", "Telefone", "Profissão", "Tipo de Fornecedor", "Status", "Observação"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTabelaFornecedor.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblTabelaFornecedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTabelaFornecedorMouseClicked(evt);
            }
        });
        scrlPanel.setViewportView(tblTabelaFornecedor);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Fornecedor.png"))); // NOI18N
        jLabel2.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrlPanel)
                    .addComponent(pnlEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(pnlInfAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pnlContato, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlAcoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlInf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlInf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlContato, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlAcoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(pnlInfAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

    private void txtCEPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCEPKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String cep = txtCEP.getText();
            String dados = buscarDadosPorCep(cep);
            preencherCampos(dados);
        }
    }//GEN-LAST:event_txtCEPKeyPressed

    private void limpar() {
        try {
            txtNomeEmpresa.setText("");
            txtCNPJ.setText("");
            txtCEP.setText("");
            txtEstado.setText("");
            txtCidade.setText("");
            txtBairro.setText("");
            txtLogradouro.setText("");
            cboComplemento.setSelectedIndex(0);
            txtNumero.setText("");
            txtResponsavel.setText("");
            cboTipo.setSelectedIndex(0);
            cboStatus.setSelectedIndex(0);
            txtEmail.setText("");
            txtTelefone.setText("");
            txtObs.setText("");
            txtNomeEmpresa.requestFocus();
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
        String nomeEditado = txtNomeEmpresa.getText();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            boolean encontrado = false;
            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("Nome:") && linha.contains(nomeEditado)) {
                    encontrado = true;
                    linhas.add("Nome da Empresa: " + txtNomeEmpresa.getText());
                    linhas.add("CNPJ: " + txtCNPJ.getText());
                    linhas.add("CEP: " + txtCEP.getText());
                    linhas.add("Estado: " + txtEstado.getText());
                    linhas.add("Cidade: " + txtCidade.getText());
                    linhas.add("Bairro: " + txtBairro.getText());
                    linhas.add("Logradouro: " + txtLogradouro.getText());
                    linhas.add("Complemento: " + cboComplemento.getSelectedItem().toString());
                    linhas.add("Numero: " + txtNumero.getText());
                    linhas.add("E-mail: " + txtEmail.getText());
                    linhas.add("WhatsApp: " + txtTelefone.getText());
                    linhas.add("Nome do Responsavel: " + txtResponsavel.getText());
                    linhas.add("Tipo de Fornecedor: " + cboTipo.getSelectedItem().toString());
                    linhas.add("Status: " + cboStatus.getSelectedItem().toString());
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
            txtNomeEmpresa.setText((String) tblTabelaFornecedor.getValueAt(row, 0));
            txtCNPJ.setText((String) tblTabelaFornecedor.getValueAt(row, 2));
            txtCEP.setText((String) tblTabelaFornecedor.getValueAt(row, 4));
            txtEstado.setText((String) tblTabelaFornecedor.getValueAt(row, 5));
            txtCidade.setText((String) tblTabelaFornecedor.getValueAt(row, 6));
            txtBairro.setText((String) tblTabelaFornecedor.getValueAt(row, 7));
            txtLogradouro.setText((String) tblTabelaFornecedor.getValueAt(row, 8));
            cboComplemento.setSelectedItem(tblTabelaFornecedor.getValueAt(row, 9));
            txtNumero.setText((String) tblTabelaFornecedor.getValueAt(row, 10));
            txtEmail.setText((String) tblTabelaFornecedor.getValueAt(row, 11));
            txtTelefone.setText((String) tblTabelaFornecedor.getValueAt(row, 12));
            txtResponsavel.setText((String) tblTabelaFornecedor.getValueAt(row, 13));
            cboTipo.setSelectedItem(tblTabelaFornecedor.getValueAt(row, 14));
            cboStatus.setSelectedItem(tblTabelaFornecedor.getValueAt(row, 15));
            txtObs.setText((String) tblTabelaFornecedor.getValueAt(row, 16));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados nos campos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean validarCampos() {
    if (txtNomeEmpresa.getText().trim().isEmpty() ||
        txtCNPJ.getText().trim().isEmpty() ||
        txtCEP.getText().trim().isEmpty() ||
        txtNumero.getText().trim().isEmpty() ||
        txtResponsavel.getText().trim().isEmpty() || 
        txtTelefone.getText().trim().isEmpty() || 
        txtEmail.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos obrigatórios.");
        return false;
    }
    return true;
}

    private void tblTabelaFornecedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTabelaFornecedorMouseClicked
        tblTabelaFornecedor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = tblTabelaFornecedor.getSelectedRow();
                    carregarDadosNosCampos(row);
                }
            }
        });
    }//GEN-LAST:event_tblTabelaFornecedorMouseClicked

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
            String nomeExcluir = txtNomeEmpresa.getText();
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
            java.util.logging.Logger.getLogger(FrmCadastroFornecedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCadastroFornecedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCadastroFornecedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCadastroFornecedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmCadastroFornecedores().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cboComplemento;
    private javax.swing.JComboBox<String> cboStatus;
    private javax.swing.JComboBox<String> cboTipo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBairro;
    private javax.swing.JLabel lblCEP;
    private javax.swing.JLabel lblCPF;
    private javax.swing.JLabel lblCidade;
    private javax.swing.JLabel lblComplemento;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblLogradouro;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblObs;
    private javax.swing.JLabel lblProfissao;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JLabel lblWhats;
    private javax.swing.JPanel pnlAcoes;
    private javax.swing.JPanel pnlContato;
    private javax.swing.JPanel pnlEndereco;
    private javax.swing.JPanel pnlInf;
    private javax.swing.JPanel pnlInfAdd;
    private javax.swing.JScrollPane scrlPanel;
    private javax.swing.JTable tblTabelaFornecedor;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JFormattedTextField txtCEP;
    private javax.swing.JFormattedTextField txtCNPJ;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtLogradouro;
    private javax.swing.JTextField txtNomeEmpresa;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextArea txtObs;
    private javax.swing.JTextField txtResponsavel;
    private javax.swing.JFormattedTextField txtTelefone;
    // End of variables declaration//GEN-END:variables

}
