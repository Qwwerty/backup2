/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import com.sun.corba.se.pept.transport.Connection;
import connection.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.LoginBD;

/**
 *
 * @author qwerty
 */
public class LoginDao {

    public void Create(LoginBD objeto) {

        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {

            stmt = con.prepareStatement("INSERT INTO usuario (login, senha) VALUES(?, ?)");
            stmt.setString(1, objeto.getLogin());
            stmt.setString(2, objeto.getSenha());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Inserido com Sucesso");

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Não foi inserido com Sucesso: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public boolean checkLogin(LoginBD objeto) {

        java.sql.Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement("SELECT * FROM usuario WHERE login = ? AND senha = ?");
            stmt.setString(1, objeto.getLogin());
            stmt.setString(2, objeto.getSenha());
            
            ResultSet rs = null;       
            rs = stmt.executeQuery();
            
            
            
            if(rs.next()){
                 JOptionPane.showMessageDialog(null, "Foi encontrado");
                return true;
            }
            else{
                JOptionPane.showMessageDialog(null, "Nao foi encontrado");
                return false;
            }
                
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi encontrado: " + ex);
            return false;
        }
        
        finally{
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

}
