/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.newhospital.controller;

import br.inatel.newhospital.models.Enfermeira;
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
public class EnfermeiraDAO {

    // objeto responsável pela conexão com o servidor do banco de dados
    Connection con;
    // objeto responsável por preparar as consultas dinâmicas
    PreparedStatement pst;
    // objeto responsável por executar as consultas estáticas
    Statement st;
    // objeto responsável por referenciar a tabela resultante da busca
    ResultSet rs;

    // NOME DO BANCO DE DADOS
    String database = "NewHospital";
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
    
    public boolean inserirEnfermeira(Enfermeira novoUsuario) 
    {
        connectToDb(); //Conecta ao banco de dados
        //Comando em SQL:
        String sql = "INSERT INTO usuario (cpfEnf,senhaAcessoEnf,nomeEnf,telefoneEnf) values (?,?,?,?)";
        //O comando recebe paramêtros -> consulta dinâmica (pst)
        try 
        {
            pst = con.prepareStatement(sql);
            pst.setString(1, novoUsuario.getCpf());     //1- refere-se à primeira interrogação
            pst.setString(2, novoUsuario.getSenha());   //2- refere-se à segunda interrogação
            pst.setString(3, novoUsuario.getNome()); 
            pst.setString(4, novoUsuario.getTelefone());  
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
    
    public ArrayList<Enfermeira> buscarEnfermeiraSemFiltro() 
    {
        ArrayList<Enfermeira> listaDeEnfermeiras = new ArrayList<>();
        connectToDb();
        //Comando em SQL:
        String sql = "SELECT * FROM Enfermeira";
        //O comando NÃO recebe parâmetros -> consulta estática (st)
        try 
        {
            st = con.createStatement();
            rs = st.executeQuery(sql); //ref. a tabela resultante da busca
            System.out.println("Lista de Enfermeiras: ");
            while(rs.next())
            {
                Enfermeira usuarioTemp = new Enfermeira();
                System.out.println("Nome = "+usuarioTemp.getNome());
                System.out.println("CPF = "+usuarioTemp.getCpf());
                System.out.println("---------------------------------");
                listaDeEnfermeiras.add(usuarioTemp);
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
        return listaDeEnfermeiras;
    }
    
    public boolean atualizarNomeEnfermeira(int id, String novoNome) 
    {
        connectToDb();
        //Comando em SQL
        String sql = "UPDATE Enfermeira SET nome=? WHERE id=?";
         //O comando recebe paramêtros -> consulta dinâmica (pst)
        try 
        {
            pst = con.prepareStatement(sql);
            pst.setString(1, novoNome);
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

    public boolean deletarEnfermeira(int id) 
    {
        connectToDb();
        //Comando em SQL:
        String sql = "DELETE FROM Enfermeira WHERE id=?";
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
