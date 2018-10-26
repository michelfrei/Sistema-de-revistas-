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

    public boolean InserirEspecificacao(Especificacao especificacao) {

        String SQL = "INSERT INTO sys.Especificação (id, Nome) values (?, ?)";

        try {
            System.out.println("entrou aqui inserir da especificacao");
            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

            stmt.setInt(1, especificacao.getId());
            stmt.setString(2, especificacao.getNome());

            stmt.execute();
            stmt.close();

        } catch (SQLException ex) {
            System.out.println("Azedou especificacao");
            return false;
        }
        return false;
    }

    public boolean RemoverEspecificacao(Especificacao especificacao) throws SQLException {
        String SQL = "Delete from sys.Especificação where id=?";

        try {
            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
            stmt.setInt(1, especificacao.getId());

            stmt.execute();
            stmt.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return true;
    }

    public boolean AlterarEspecificacao(Especificacao especificacao) throws SQLException {
        String SQL = "update sys.Especificação set Nome=? where id = ?";

        try {
            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
            stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

            stmt.setString(1, especificacao.getNome());
            stmt.setInt(2, especificacao.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return true;
    }

    public List<Especificacao> ListaEspecificacao() throws SQLException {

        List<Especificacao> ListaEspecificacao;
        ListaEspecificacao = new ArrayList<>();

        String SQL = "select* from sys.Especificação";
        try {

            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ListaEspecificacao.add(new Especificacao(rs.getInt("id"),
                        rs.getString("Nome")));

            }
        } catch (Exception e) {
            System.out.println("Problema tal:");
            System.out.println(e.getMessage());
        }
        return ListaEspecificacao;
    }

    public List<Especificacao> ListaBuscaEspecificacao(Especificacao especificacao) throws SQLException {
        List<Especificacao> retorno = new ArrayList<Especificacao>();

        if (especificacao.getNome() == null) {
            String SQL = "select * from sys.Especificação";
            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

            try {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {

                    retorno.add(new Especificacao(rs.getInt("id"),
                            rs.getString("Nome")));
                }
            } catch (Exception e) {
                System.out.println("Problema tal:");
                System.out.println(e.getMessage());
                stmt.close();
                Conexao.getConexaoMySQL().close();

                return retorno;
            }
        } else {
            String SQL = "select * from sys.Especificação where Nome like ?";
            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

            stmt.setString(1, "%" + especificacao.getNome() + "%");

            try {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {

                    retorno.add(new Especificacao(rs.getInt("id"),
                            rs.getString("Nome")));
                }
            } catch (Exception e) {
                System.out.println("Problema tal:");
                System.out.println(e.getMessage());
            }

            stmt.close();
            Conexao.getConexaoMySQL().close();

            return retorno;
        }
        return null;
    }
}
