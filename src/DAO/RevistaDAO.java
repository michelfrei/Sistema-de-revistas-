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
    
    public boolean SubtrairRevista(Revistas rev) throws SQLException{ //funcionando
        String SQL = "Select id from sys.revista where Titulo = ?";
        
        PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
        stmt.setString(1, rev.getTitulo());
        ResultSet rs = stmt.executeQuery();
        
        if(!rs.next()){
            return false;
        }
        
        
        try{
            SQL = "update sys.revista set Quantidade = Quantidade - ? where Titulo = ?";
            stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
            System.out.println("Entrou no SubtrairRevista");
            stmt.setInt(1, rev.getQuantidade());
            stmt.setString(2, rev.getTitulo());
	
            stmt.execute();
            stmt.close();
        }catch(SQLException ex){
            System.out.println("Azedou no inserirRevistas");
        }
        
        return true;
    }
    
    public boolean RemoverRevista(Revistas rev) throws SQLException{ // funcionando
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
    public boolean BuscarRevista(Revistas rev) throws SQLException{ //não ta funcionando
        try{
        
        
        String SQL = "Select * from sys.revista where Titulo = ?";
        PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
        stmt.setString(1, rev.getTitulo());
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            rev.setTitulo(rs.getString("Titulo"));
            rev.setEspecificacao(rs.getString("Especificacao"));
            rev.setClassificacao(rs.getString("Classificacao"));
            rev.setData(rs.getString("Data"));
            rev.setQuantidade(rs.getInt("Quantidade"));
            rev.setOrigem(rs.getString("Origem"));
            System.out.println(rs.getString("Titulo"));
            System.out.println("quantidade " + rs.getInt("Quantidade"));
            stmt.close();
        }
        }catch(SQLException e){
            System.out.println("Azedou dms mds pt1 " + e.getMessage());
        }
        return true;
    }    
     public boolean AlterarRevista(Revistas rev) throws SQLException{// não ta funcionando
        String SQL = "Select id from sys.revista where Titulo = ?";
        
        PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
        stmt.setString(1, rev.getTitulo());
        ResultSet rs = stmt.executeQuery();
        int aux = rs.getInt("id");
        
        SQL = "update sys.revista set Titulo=?, Especificacao=?, Origem=?, Data=?, Classificacao=? where id = aux";
        try{
            stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
            System.out.println("Entrou no inserirRevistas");

            stmt.setString(2, rev.getTitulo());
            stmt.setString(3, rev.getEspecificacao());
            stmt.setString(5, rev.getOrigem());
            stmt.setString(6, rev.getData());
            stmt.setString(7, rev.getClassificacao());
	
            stmt.execute();
            stmt.close();
        }catch(SQLException ex){
            System.out.println("Azedou no inserirRevistas");
        }
        
        return true;
    }
}