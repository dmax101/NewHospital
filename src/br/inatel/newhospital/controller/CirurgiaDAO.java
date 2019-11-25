/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.newhospital.controller;

import br.inatel.newhospital.models.Cirurgia;
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
public class CirurgiaDAO {
    
    // objeto responsável pela conexão com o servidor do banco de dados
    Connection con;
    // objeto responsável por preparar as consultas dinâmicas
    PreparedStatement pst;
    // objeto responsável por executar as consultas estáticas
    Statement st;
    // objeto responsável por referenciar a tabela resultante da busca
    ResultSet rs;

    // NOME DO BANCO DE DADOS
    String database = "newhospital";
    // URL: VERIFICAR QUAL A PORTA
    String url = "jdbc:mysql://localhost:3306/" + database + "?useTimezone=true&serverTimezone=UTC&useSSL=false";
    // USUÁRIO
    String user = "root";
    // SENHA
    String password = "root";

    boolean sucesso = false;
    
    // Conectar ao banco de dados
    public void connectToDb() 
    {
        try 
        {  
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Conexão feita com sucesso!");
        } 
        catch (SQLException ex) 
        {
             System.out.println("Erro: " + ex.getMessage());
        }
                
    }
    
    public boolean inserirEnfermeira(Cirurgia novoUsuario) 
    {
        connectToDb(); //Conecta ao banco de dados
        //Comando em SQL:
        String sql = "INSERT INTO Cirurgia (idCirurgia,nomePaciente,dataCir,Medico,obsCir) values (?,?,?,?,?)";
        //O comando recebe paramêtros -> consulta dinâmica (pst)
        try 
        {
            pst = con.prepareStatement(sql);
            pst.setInt(1, novoUsuario.getId());     //1- refere-se à primeira interrogação
            pst.setString(2, novoUsuario.getNomePaciente());   //2- refere-se à segunda interrogação
            pst.setString(3, novoUsuario.getDataCir()); 
            pst.setString(4, novoUsuario.getMedico());  
            pst.setString(5, novoUsuario.getObs());  
                                                        //e assim por diante....
            pst.execute();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            sucesso = true;
        } 
        catch (SQLException ex) 
        {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } 
        finally 
        {
            try 
            {   //Encerra a conexão
                con.close();
                pst.close();
            } 
            catch (SQLException ex) 
            {
                System.out.println("Erro = " + ex.getMessage());
            }
        }
        return sucesso;
    }
    
    public ArrayList<Cirurgia> buscarCirurgiaSemFiltro() 
    {
        ArrayList<Cirurgia> listaDeCirurgias = new ArrayList<>();
        connectToDb();
        //Comando em SQL:
        String sql = "SELECT * FROM Cirurgia";
        //O comando NÃO recebe parâmetros -> consulta estática (st)
        try 
        {
            st = con.createStatement();
            rs = st.executeQuery(sql); //ref. a tabela resultante da busca
            System.out.println("Lista de Cirurgias: ");
            while(rs.next())
            {
                Cirurgia usuarioTemp = new Cirurgia();
                JOptionPane.showMessageDialog(null, "Id: " + usuarioTemp.getId() + "\nPaciente: " + usuarioTemp.getNomePaciente() + "\nData: " + usuarioTemp.getDataCir());
                listaDeCirurgias.add(usuarioTemp);
            }
            sucesso = true;
        } 
        catch (SQLException ex) 
        {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } 
        finally 
        {
            try 
            {
                con.close();
                st.close();
            } catch (SQLException ex) {
                System.out.println("Erro = " + ex.getMessage());
            }
        }
        return listaDeCirurgias;
    }
    
    public boolean atualizarCirurgia(int id, String novaData) 
    {
        connectToDb();
        //Comando em SQL
        String sql = "UPDATE Enfermeira SET dataCir=? WHERE id=?";
         //O comando recebe paramêtros -> consulta dinâmica (pst)
        try 
        {
            pst = con.prepareStatement(sql);
            pst.setString(1, novaData);
            pst.setInt(2, id);
            pst.execute();
            sucesso = true;
        } 
        catch (SQLException ex) 
        {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } 
        finally 
        {
            try 
            {
                con.close();
                pst.close();
            } 
            catch (SQLException ex) 
            {
                System.out.println("Erro = " + ex.getMessage());
            }

        }
        return sucesso;
    }

    public boolean deletarCirurgia(int id) 
    {
        connectToDb();
        //Comando em SQL:
        String sql = "DELETE FROM Cirurgia WHERE id=?";
        try 
        {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();
            sucesso = true;
        } 
        catch (SQLException ex) 
        {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } 
        finally 
        {
            try 
            {
                con.close();
                pst.close();
            } 
            catch (SQLException ex) 
            {
                System.out.println("Erro = " + ex.getMessage());
            }
        }
        return sucesso;
    }
    
}
