package DAO;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.Registro;

/**
 *
 * @author Michel
 */
public class RegistroDAO {

    public void InserirNovaRegistro(Registro reg) throws SQLException {
        String SQL = "INSERT INTO revista.registro (registro, Titulo, Editora, Origem, Local, Area, Ano) values (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

        stmt.setInt(1, reg.getRegistro());
        stmt.setString(2, reg.getTitulo());
        stmt.setString(3, reg.getEditora());
        stmt.setString(4, reg.getOrigem());
        stmt.setString(5, reg.getLocal());
        stmt.setString(6, reg.getArea());
        stmt.setInt(7, reg.getAno());

        stmt.execute();
        stmt.close();
    }

    public void RemoverRegistro(Registro reg) throws SQLException {
        String SQL = "Delete from revista.registro where registro=?";

        PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
        stmt.setInt(1, reg.getRegistro());

        stmt.execute();
        stmt.close();
    }

    public void AlterarRegistro(Registro reg) throws SQLException {
        String SQL = SQL = "update revista.registro set Titulo=?, Editora=?, Origem=?, Local=?, Area=?, Ano=? where registro = ?";

        PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
        stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

        stmt.setString(1, reg.getTitulo());
        stmt.setString(2, reg.getEditora());
        stmt.setString(3, reg.getOrigem());
        stmt.setString(4, reg.getLocal());
        stmt.setString(5, reg.getArea());
        stmt.setInt(6, reg.getAno());
        stmt.setInt(7, reg.getRegistro());
        stmt.execute();
        stmt.close();
    }

    public ArrayList<Registro> ListaRegistros() throws SQLException {

        ArrayList<Registro> ListaRegistros;
        ListaRegistros = new ArrayList<>();

        String SQL = "select* from revista.registro order by registro ASC";
        try {

            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ListaRegistros.add(new Registro(rs.getInt("registro"),
                        rs.getString("Titulo"),
                        rs.getString("Editora"),
                        rs.getString("Origem"),
                        rs.getString("local"),
                        rs.getString("Area"),
                        rs.getInt("Ano")));

            }
        } catch (Exception e) {
            System.out.println("Problema tal:");
            System.out.println(e.getMessage());
        }
        return ListaRegistros;

    }

    public List<Registro> ListaBuscaRegistros(Registro reg) throws SQLException {
        List<Registro> retorno = new ArrayList<Registro>();

        String SQL = "select * from revista.registro ";

        if (reg.getTitulo() != null && reg.getRegistro() == 0 && reg.getArea() == null) {
            SQL += " where Titulo like ? order by registro ASC";
        } else if (reg.getArea() != null && reg.getTitulo() == null && reg.getRegistro() == 0) {
            SQL += " where Area like ? order by registro ASC";
        } else if (reg.getArea() == null && reg.getTitulo() == null && reg.getRegistro() != 0) {
            SQL += " where Registro = ? order by registro ASC";
        } else if (reg.getTitulo() != null && reg.getRegistro() == 0 && reg.getArea() != null) {
            SQL += " where Titulo like ? and Area like ? order by registro ASC";
        } else if (reg.getTitulo() != null && reg.getRegistro() != 0 && reg.getArea() == null) {
            SQL += " where Titulo like ? and Registro = ? order by registro ASC";
        } else if (reg.getTitulo() != null && reg.getRegistro() != 0 && reg.getArea() != null) {
            SQL += " where Titulo like ? and Area like ? and Registro = ? order by registro ASC";
        } else if (reg.getTitulo() == null && reg.getRegistro() != 0 && reg.getArea() != null) {
            SQL += " where Area like ? and Registro = ? order by registro ASC";
        }

        PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

        if (reg.getTitulo() != null && reg.getRegistro() == 0 && reg.getArea() == null) {
            stmt.setString(1, "%" + reg.getTitulo() + "%");
        } else if (reg.getArea() != null && reg.getTitulo() == null && reg.getRegistro() == 0) {
            stmt.setString(1, "%" + reg.getArea() + "%");
        } else if (reg.getArea() == null && reg.getTitulo() == null && reg.getRegistro() != 0) {
            stmt.setInt(1, reg.getRegistro());
        } else if (reg.getTitulo() != null && reg.getRegistro() == 0 && reg.getArea() != null) {
            stmt.setString(1, "%" + reg.getTitulo() + "%");
            stmt.setString(2, reg.getArea());
        } else if (reg.getTitulo() != null && reg.getRegistro() != 0 && reg.getArea() == null) {
            stmt.setString(1, "%" + reg.getTitulo() + "%");
            stmt.setInt(2, reg.getRegistro());
        } else if (reg.getTitulo() != null && reg.getRegistro() != 0 && reg.getArea() != null) {
            stmt.setString(1, "%" + reg.getTitulo() + "%");
            stmt.setString(2, reg.getArea());
            stmt.setInt(3, reg.getRegistro());
        } else if (reg.getTitulo() == null && reg.getRegistro() != 0 && reg.getArea() != null) {
            stmt.setString(1, reg.getArea());
            stmt.setInt(2, reg.getRegistro());
        }

        try {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                retorno.add(new Registro(rs.getInt("registro"),
                        rs.getString("Titulo"),
                        rs.getString("Editora"),
                        rs.getString("Origem"),
                        rs.getString("local"),
                        rs.getString("Area"),
                        rs.getInt("Ano")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        stmt.close();
        Conexao.getConexaoMySQL().close();

        return retorno;
    }

    public ArrayList<Registro> ListaRegistrosDir(Registro reg) throws SQLException {
        ArrayList<Registro> retorno = new ArrayList<Registro>();

        String SQL = "select * from revista.registro where titulo = ?";
        PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
        try {
            stmt.setString(1, reg.getTitulo());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                retorno.add(new Registro(rs.getInt("registro"),
                        rs.getString("Titulo"),
                        rs.getString("Editora"),
                        rs.getString("Origem"),
                        rs.getString("local"),
                        rs.getString("Area"),
                        rs.getInt("Ano")));

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
