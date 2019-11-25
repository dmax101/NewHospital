/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.newhospital.controller;

import br.inatel.newhospital.models.Caixa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Leandro Pereira
 */
public class CaixaDAO {
    
    public CaixaDAO(Caixa c){
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO Enfermeira (idCaixa,descricaoCaixa,empresaCaixa)VALUES(?,?,?)");
            stmt.setInt(1, c.getId());
            stmt.setString(2, c.getDescricao());
            stmt.setString(3, c.getEmpresa());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    
}
