package DAO;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.*;

public class EspecificacaoDAO {

    public void InserirEspecificacao(Especificacao especificacao) throws SQLException {

        String SQL = "INSERT INTO revista.Especificação (id, Nome) values (?, ?)";

        PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

        stmt.setInt(1, especificacao.getId());
        stmt.setString(2, especificacao.getNome());

        stmt.execute();
        stmt.close();
    }

    public void RemoverEspecificacao(Especificacao especificacao) throws SQLException {
        String SQL = "Delete from revista.Especificação where id=?";

        PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
        stmt.setInt(1, especificacao.getId());

        stmt.execute();
        stmt.close();
    }

    public void AlterarEspecificacao(Especificacao especificacao) throws SQLException {
        String SQL = "update revista.Especificação set Nome=? where id = ?";

        PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
        stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

        stmt.setString(1, especificacao.getNome());
        stmt.setInt(2, especificacao.getId());
        stmt.execute();
        stmt.close();
    }

    public List<Especificacao> ListaEspecificacao() throws SQLException {

        List<Especificacao> ListaEspecificacao;
        ListaEspecificacao = new ArrayList<>();

        String SQL = "select* from revista.Especificação";
        try {

            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ListaEspecificacao.add(new Especificacao(rs.getInt("id"),
                        rs.getString("Nome")));

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ListaEspecificacao;
    }

    public List<Especificacao> ListaBuscaEspecificacao(Especificacao especificacao) throws SQLException {
        List<Especificacao> retorno = new ArrayList<Especificacao>();

        String SQL = "select * from revista.Especificação";
        
        if(especificacao.getNome()!= null){
            SQL += " where Nome like ?";
        }
        
        
        PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
        
        if(especificacao.getNome()!= null){
        stmt.setString(1, "%" + especificacao.getNome() + "%");
        }
        try {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                retorno.add(new Especificacao(rs.getInt("id"),
                        rs.getString("Nome")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        stmt.close();
        Conexao.getConexaoMySQL().close();

        return retorno;
    }
    
}
