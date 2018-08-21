package DAO;


import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import model.*;

public class RevistaDAO {

    public boolean InserirRevistas(Revistas rev) {
		
	String SQL = "INSERT INTO sys.revista (id, Titulo, Especificacao, Quantidade, Origem, Data) values (?, ?, ?, ?, ?, ?)"; 

try {
        System.out.println("entrou aqui inserir");
	PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

	stmt.setInt(1, rev.getID());
	stmt.setString(2, rev.getTitulo());
        stmt.setString(3, rev.getEspecificacao());
	stmt.setInt(4, rev.getQuantidade());
	stmt.setString(5, rev.getOrigem());
	stmt.setString(6, rev.getData());
        
        stmt.execute();
        stmt.close();
        
} catch(SQLException ex) {
	   System.out.println("Azedou");
	return false;
}
return false;	
}
    
    public boolean BuscarRevista(Revistas rev){
        String SQL = "SELECT * FROM sys.revista WHERE id = ?";
	
	try {
                System.out.println("entrou aqui buscador");
		PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
		stmt.setInt(1, 1); // alterar aqui
	
		ResultSet result = stmt.executeQuery();
                
                while (result.next()) {
                    System.out.println("result set");
                    String Especificacao = result.getString("Especificacao");
                    System.out.println(Especificacao);
    }                
                
        }catch(Exception e){
            System.out.println("azedou");
        }
        return true;
    }
    
    
}