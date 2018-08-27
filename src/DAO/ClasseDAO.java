package DAO;


import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import model.*;

public class ClasseDAO {

    public boolean InserirClasse(Classe classe) {
		
	String SQL = "INSERT INTO sys.Classificação (idClassificação, Nome) values (?, ?)"; 

        try {
        System.out.println("entrou aqui inserir da classificacao");
	PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

	stmt.setInt(1, classe.getIdClasse());
	stmt.setString(2, classe.getNome());
        
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