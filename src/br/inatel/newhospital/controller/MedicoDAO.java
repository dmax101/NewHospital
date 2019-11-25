/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.newhospital.controller;

import br.inatel.newhospital.models.Medico;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Leandro Pereira
 */
public class MedicoDAO {
    
     public boolean InsertMedicoaDAO(Medico m){
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO Medico (cpfMed,senhaAcessoMed,nomeMed,telefoneMed)VALUES(?,?,?,?)");
            stmt.setString(1, m.getCpf());
            stmt.setString(2, m.getSenha());
            stmt.setString(3, m.getNome());
            stmt.setString(4, m.getTelefone());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        return true;
    }
    
}
