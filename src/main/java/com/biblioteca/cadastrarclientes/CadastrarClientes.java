/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.biblioteca.cadastrarclientes;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import telas.FrmLogin;
/**
 *
 * @author Erick Daniel Teixeira Vier - RA: 235908-1
 */
public class CadastrarClientes {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
            FrmLogin frmLogin = new FrmLogin();
            frmLogin.setLocationRelativeTo(null);
            frmLogin.setResizable(false);
            frmLogin.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao iniciar!" + e.getMessage());
        }
    }
}
