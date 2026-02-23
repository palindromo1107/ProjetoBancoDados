/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package project;

import java.sql.Connection;

import util.ConnectionFactory;

/**
 *
 * @author Mateus
 */
public class ProjetoBancoDados {

    public static void main(String[] args) {

        try (Connection c = ConnectionFactory.getConnection()) {
            System.out.println("Conectado com sucesso!");
        } catch (Exception e) {
            System.out.println("Falha na conexão!");
            e.printStackTrace();
            return; // Se não conectar, não abre a tela
        }

        // Abre a tela
        java.awt.EventQueue.invokeLater(() -> {
            new Principal().setVisible(true);
        });
    }
}