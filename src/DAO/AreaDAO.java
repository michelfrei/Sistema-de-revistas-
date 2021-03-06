package DAO;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.*;

public class AreaDAO {

    public void InserirArea(Area area) throws SQLException {

        String SQL = "INSERT INTO revista.Area (id, Nome) values (?, ?)";

            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

            stmt.setInt(1, area.getId());
            stmt.setString(2, area.getNome());

            stmt.execute();
            stmt.close();
    }

    public void RemoverArea(Area area) throws SQLException {
        String SQL = "Delete from revista.Area where id=?";

            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
            stmt.setInt(1, area.getId());

            stmt.execute();
            stmt.close();
    }

    public void AlterarArea(Area area) throws SQLException {
        String SQL = "update revista.Area set Nome=? where id = ?";

            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
            stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

            stmt.setString(1, area.getNome());
            stmt.setInt(2, area.getId());
            stmt.execute();
            stmt.close();
    }

    public List<Area> ListaArea() throws SQLException {

        List<Area> ListaArea;
        ListaArea = new ArrayList<>();

        String SQL = "select* from revista.Area";
        try {

            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ListaArea.add(new Area(rs.getInt("id"),
                        rs.getString("Nome")));

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ListaArea;
    }

    public List<Area> ListaBuscaArea(Area area) throws SQLException {
        List<Area> retorno = new ArrayList<Area>();

        String SQL = "select * from revista.Area ";
        
        
        if(area.getNome() != null){
            SQL += "where Nome like ?";
        }
        PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
        
        if(area.getNome() != null){
            stmt.setString(1, "%" + area.getNome() + "%");
        }

        try {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                retorno.add(new Area(rs.getInt("id"),
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
