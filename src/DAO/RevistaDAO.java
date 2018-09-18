package DAO;


import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        String SQL = "Delete from sys.revista where id=?";

        try{
            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
            stmt.setInt(1, rev.getID());
            System.out.println("Entrou no RemoverRevista");
	
            stmt.execute();
            stmt.close();
            
            /*        String sql = "DELETE FROM PRODUTO where COD_PRODUTO=?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, reg.getCOD_PRODUTO());
        stmt.execute();
        stmt.close();*/
            
            
            
        }catch(SQLException ex){
            System.out.println("Azedou no RemoverRevista");
        }
        return true;
    }
    /*public Revistas BuscarRevista(int Id) throws SQLException{ //não ta funcionando
        
        Revistas rev = null;
        int i = 0;
        String SQL = "Select * from sys.revista where id = ?";
        PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
               
        stmt.setInt(1, Id);
        ResultSet rs = stmt.executeQuery();
        
        
        while(rs.next()){
            rev = new Revistas(
            rs.getInt("id"),
            rs.getString("Titulo"),
            rs.getString("Especificacao"),
            rs.getInt("Quantidade"),
            rs.getString("Data"),
            rs.getString("Classificacao"),
            rs.getString("Origem"));
            
            i++;
        }
        stmt.close();
        if(i==0){
            return null;
        }else{
            return rev;
        }
        
    }*/    
     public boolean AlterarRevista(Revistas rev) throws SQLException{// não ta funcionando
        String SQL =  SQL = "update sys.revista set Titulo=?, Especificacao=?, Origem=?, Data=?, Classificacao=? where id = ?";
        
             
        try{
            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);  
            stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
            System.out.println("Entrou no inserirRevistas");

            stmt.setString(1, rev.getTitulo());
            stmt.setString(2, rev.getEspecificacao());
            stmt.setString(3, rev.getOrigem());
            stmt.setString(4, rev.getData());
            stmt.setString(5, rev.getClassificacao());
            stmt.setInt(6, rev.getID());
            stmt.execute();
            stmt.close();
        }catch(SQLException ex){
            System.out.println("Azedou no inserirRevistas");
        }
        
        return true;
    }
        public List<Revistas> ListaRevista() throws SQLException{
            
        List<Revistas> ListaRevista;
        ListaRevista = new ArrayList<>();
        
        String SQL = "select* from sys.revista order by id DESC";
        try{
            
            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                ListaRevista.add(new Revistas(rs.getInt("id"), 
                        rs.getString("Titulo"), 
                        rs.getString("Especificacao"),
                        rs.getInt("Quantidade"), 
                        rs.getString("Origem"), 
                        rs.getString("Data"), 
                        rs.getString("Classificacao")));
                
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return ListaRevista;
 
    }

    public List<Revistas> ListaBuscaRevista(Revistas rev) throws SQLException {      
              
        List<Revistas> ListaRevista = new ArrayList<>();
        int i = 0;
        String SQL = "select * from sys.revista";
        if(!rev.getEspecificacao().equals(null)){
                SQL = SQL+" where Especificacao = ?";
                i=i+1;
            }
        else if(i==1){
                SQL = SQL+" and Classificação = ?";
        }
        
        
        
        System.out.println(SQL);
        PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

        stmt.setString(1, rev.getEspecificacao());
        if(i==1){
            stmt.setString(2, rev.getClassificacao());
        }
        ResultSet rs = stmt.executeQuery();
        try{    
        while(rs.next()){
            ListaRevista.add(new Revistas(
                rs.getInt("id"), 
                rs.getString("Titulo"), 
                rs.getString("Especificacao"),
                rs.getInt("Quantidade"), 
                rs.getString("Origem"), 
                rs.getString("Data"), 
                rs.getString("Classificacao")));               
            } 
        stmt.close();
        return ListaRevista;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        /*}catch(SQLException e){
                System.out.println(e.getMessage());
            } */          
             //return null;
             /*try{  
            if(1==1){
                SQL = SQL+" where Titulo = ?";
            }
            
            
            !tit.equals(null)
            
            else if(!tit.equals(null)){
                SQL = SQL+" where Titulo like ?";
            }
            if(are.equals(null)){
            }
            else if(!are.equals(null)){
                SQL = SQL+" and Classificacao like ?";
            }
            if(esp.equals(null)){
            }
            else if(!esp.equals(null)){
                SQL = SQL+" and Especificacao like ''";
            }*/
             return null;
    }
}