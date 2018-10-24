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

    public boolean InserirArea(Area area) {

        String SQL = "INSERT INTO sys.Area (id, Nome) values (?, ?)";

        try {
            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

            stmt.setInt(1, area.getId());
            stmt.setString(2, area.getNome());

            stmt.execute();
            stmt.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return false;
    }

    public boolean RemoverArea(Area area) throws SQLException {
        String SQL = "Delete from sys.Area where id=?";

        try {
            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
            stmt.setInt(1, area.getId());

            stmt.execute();
            stmt.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return true;
    }

    public boolean AlterarArea(Area area) throws SQLException {// não ta funcionando
        String SQL = SQL = "update sys.Area set Nome=? where id = ?";

        try {
            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
            stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

            stmt.setString(1, area.getNome());
            stmt.setInt(2, area.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return true;
    }

    public List<Area> ListaArea() throws SQLException {

        List<Area> ListaArea;
        ListaArea = new ArrayList<>();

        String SQL = "select* from sys.Area order by id DESC";
        try {

            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ListaArea.add(new Area(rs.getInt("id"),
                        rs.getString("Nome")));

            }
        } catch (Exception e) {
            System.out.println("Problema tal:");
            System.out.println(e.getMessage());
        }
        return ListaArea;
    }

    public List<Area> ListaBuscaArea(Area area) throws SQLException {
        List<Area> retorno = new ArrayList<Area>();

        String SQL = "select * from sys.Area ";

        /*if (area.getTitulo() != null && area.getEspecificacao() == null && area.getArea() == null) {
            SQL += " where Titulo like ? order by id DESC";
        } else if (area.getArea() != null && area.getTitulo() == null && area.getEspecificacao() == null) {
            SQL += " where Area like ? order by id DESC";
        } else if (area.getArea() == null && area.getTitulo() == null && area.getEspecificacao() != null) {
            SQL += " where Especificacao like ? order by id DESC";
        }*/
        PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

        /*if (area.getTitulo() != null && area.getEspecificacao() == null && area.getArea() == null) {
            stmt.setString(1, "%" + area.getTitulo() + "%");
        } else if (area.getArea() != null && area.getTitulo() == null && area.getEspecificacao() == null) {
            stmt.setString(1, "%" + area.getArea() + "%");
        } else if (area.getArea() == null && area.getTitulo() == null && area.getEspecificacao() != null) {
            stmt.setString(1, "%" + area.getEspecificacao() + "%");
        }*/
        try {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                retorno.add(new Area(rs.getInt("id"),
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

}
