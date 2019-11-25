/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.newhospital.controller;

import br.inatel.newhospital.models.Enfermeira;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Leandro Pereira
 */
public class EnfermeiraDAO {

    public boolean InsertEnfermeiraDAO(Enfermeira e){
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO Enfermeira (cpfEnf,senhaAcessoEnf,nomeEnf,telefoneEnf)VALUES(?,?,?,?)");
            stmt.setString(1, e.getCpf());
            stmt.setString(2, e.getSenha());
            stmt.setString(3, e.getNome());
            stmt.setString(4, e.getTelefone());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Erro ao inserir");
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        return true;
    }

    
}
