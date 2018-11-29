package DAO;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.*;

public class EditoraDAO {

    public void InserirEditora(Editora editora) throws SQLException {

        String SQL = "INSERT INTO revista.editora (id, Nome) values (?, ?)";

            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

            stmt.setInt(1, editora.getId());
            stmt.setString(2, editora.getNome());

            stmt.execute();
            stmt.close();
    }

    public void RemoverEditora(Editora editora) throws SQLException {
        String SQL = "Delete from revista.editora where id=?";

            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
            stmt.setInt(1, editora.getId());

            stmt.execute();
            stmt.close();
    }

    public void AlterarEditora(Editora editora) throws SQLException {
        String SQL = "update revista.editora set Nome=? where id = ?";

            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
            stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

            stmt.setString(1, editora.getNome());
            stmt.setInt(2, editora.getId());
            stmt.execute();
            stmt.close();
    }

    public List<Editora> ListaEditora() throws SQLException {

        List<Editora> ListaEditora;
        ListaEditora = new ArrayList<>();

        String SQL = "select* from revista.editora order by id desc";
        try {

            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ListaEditora.add(new Editora(rs.getInt("id"),
                        rs.getString("Nome")));

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ListaEditora;
    }

    public List<Editora> ListaBuscaEditora(Editora editora) throws SQLException {
        List<Editora> retorno = new ArrayList<Editora>();
        
        String SQL = "select * from revista.editora ";
        
        if(editora.getNome() != null){
            SQL += "where Nome like ?";
        }
        
        if(1==1){
            SQL += " order by id desc";
        }
        PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
        
        if(editora.getNome() != null){
            stmt.setString(1, "%" + editora.getNome() + "%");
        }

        try {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                retorno.add(new Editora(rs.getInt("id"),
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
