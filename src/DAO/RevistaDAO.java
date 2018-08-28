package DAO;


import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import model.*;

public class RevistaDAO {

    public boolean InserirNovaRevistas(Revistas rev) { //funcionando
		
	String SQL = "INSERT INTO sys.revista (id, Titulo, Especificacao, Quantidade, Origem, Data, Classificacao) values (?, ?, ?, ?, ?, ?, ?)"; 

        try {
        System.out.println("entrou no try do inserir RevistaDAO");
	PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

	stmt.setInt(1, rev.getID());
	stmt.setString(2, rev.getTitulo());
        stmt.setString(3, rev.getEspecificacao());
	stmt.setInt(4, rev.getQuantidade());
	stmt.setString(5, rev.getOrigem());
	stmt.setString(6, rev.getData());
        stmt.setString(7, rev.getClassificacao());
        
        stmt.execute();
        stmt.close();
        
        } catch(SQLException ex) {
	   System.out.println("Azedou no try do inserir RevistaDAO");
	return false;
}
return false;	
}
    
    public boolean BuscarRevista(Revistas rev){ //terminar
        String SQL = "SELECT * FROM sys.revista WHERE nome = ?";
	
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
                
        }catch(SQLException e){
            System.out.println("azedou");
        }
        return true;
    }
    
    public boolean InserirRevista(Revistas rev) throws SQLException{ //funcionando
        
        String SQL = "Select id from sys.revista where Titulo = ?";
        
        PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
        stmt.setString(1, rev.getTitulo());
        ResultSet rs = stmt.executeQuery();
        
        if(!rs.next()){
            return false;
        }
        
        SQL = "update sys.revista set Quantidade = Quantidade + ? where Titulo = ?";
        try{
            stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
            System.out.println("Entrou no inserirRevistas");
            stmt.setInt(1, rev.getQuantidade());
            stmt.setString(2, rev.getTitulo());
	
            stmt.execute();
            stmt.close();
        }catch(SQLException ex){
            System.out.println("Azedou no inserirRevistas");
        }
        
        return true;
    }
    
    public boolean SubtrairRevista(Revistas rev) throws SQLException{
        String SQL = "Select id from sys.revista where Titulo = ?";
        
        PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
        stmt.setString(1, rev.getTitulo());
        ResultSet rs = stmt.executeQuery();
        
        if(!rs.next()){
            return false;
        }
        
        SQL = "update sys.revista set Quantidade = Quantidade - ? where Titulo = ?";
        try{
            stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
            System.out.println("Entrou no inserirRevistas");
            stmt.setInt(1, rev.getQuantidade());
            stmt.setString(2, rev.getTitulo());
	
            stmt.execute();
            stmt.close();
        }catch(SQLException ex){
            System.out.println("Azedou no inserirRevistas");
        }
        
        return true;
    }
    
    public boolean RemoverRevista(Revistas rev) throws SQLException{
        String SQL = "Select id from sys.revista where Titulo = ?";
        
        PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
        stmt.setString(1, rev.getTitulo());
        ResultSet rs = stmt.executeQuery();
        
        if(!rs.next()){
            return false;
        }
        
        try{
            SQL = "Delete from sys.revista where Titulo=?";
            stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
            System.out.println("Entrou no RemoverRevista");
            stmt.setString(1, rev.getTitulo());
	
            stmt.execute();
            stmt.close();
            
        }catch(SQLException ex){
            System.out.println("Azedou no RemoverRevista");
        }
        return true;
    }
}