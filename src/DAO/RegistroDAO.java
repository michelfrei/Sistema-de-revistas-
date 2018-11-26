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

    public void InserirNovaRevistas(Registro reg) throws SQLException {
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

    public void RemoverRevista(Registro reg) throws SQLException {
        String SQL = "Delete from revista.registro where registro=?";

        PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
        stmt.setInt(1, reg.getRegistro());

        stmt.execute();
        stmt.close();
    }

    public void AlterarRevista(Registro reg) throws SQLException {
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

        String SQL = "select* from revista.registros order by registro ASC";
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
                        rs.getInt("Data")));

            }
        } catch (Exception e) {
            System.out.println("Problema tal:");
            System.out.println(e.getMessage());
        }
        return ListaRegistros;

    }

    public List<Registro> ListaBuscaRevista(Registro reg) throws SQLException {
        List<Registro> retorno = new ArrayList<Registro>();

        String SQL = "select * from revista.registro ";

        /*if (rev.getTitulo() != null && rev.getEspecificacao() == null && rev.getArea() == null) {
            SQL += " where Titulo like ? order by id ASC";
        } else if (rev.getArea() != null && rev.getTitulo() == null && rev.getEspecificacao() == null) {
            SQL += " where Area like ? order by id ASC";
        } else if (rev.getArea() == null && rev.getTitulo() == null && rev.getEspecificacao() != null) {
            SQL += " where Especificacao like ? order by id ASC";
        } else if (rev.getTitulo() != null && rev.getEspecificacao() == null && rev.getArea() != null) {
            SQL += " where Titulo like ? and Area like ? order by id ASC";
        } else if (rev.getTitulo() != null && rev.getEspecificacao() != null && rev.getArea() == null) {
            SQL += " where Titulo like ? and Especificacao like ? order by id ASC";
        } else if (rev.getTitulo() != null && rev.getEspecificacao() != null && rev.getArea() != null) {
            SQL += " where Titulo like ? and Area like ? and Especificacao like ? order by id ASC";
        } else if (rev.getTitulo() == null && rev.getEspecificacao() != null && rev.getArea() != null) {
            SQL += " where Area like ? and Especificacao like ? order by id ASC";
        }*/

        PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

        /*if (rev.getTitulo() != null && rev.getEspecificacao() == null && rev.getArea() == null) {
            stmt.setString(1, "%" + rev.getTitulo() + "%");
        } else if (rev.getArea() != null && rev.getTitulo() == null && rev.getEspecificacao() == null) {
            stmt.setString(1, "%" + rev.getArea() + "%");
        } else if (rev.getArea() == null && rev.getTitulo() == null && rev.getEspecificacao() != null) {
            stmt.setString(1, "%" + rev.getEspecificacao() + "%");
        } else if (rev.getTitulo() != null && rev.getEspecificacao() == null && rev.getArea() != null) {
            stmt.setString(1, "%" + rev.getTitulo() + "%");
            stmt.setString(2, rev.getArea());
        } else if (rev.getTitulo() != null && rev.getEspecificacao() != null && rev.getArea() == null) {
            stmt.setString(1, "%" + rev.getTitulo() + "%");
            stmt.setString(2, rev.getEspecificacao());
        } else if (rev.getTitulo() != null && rev.getEspecificacao() != null && rev.getArea() != null) {
            stmt.setString(1, "%" + rev.getTitulo() + "%");
            stmt.setString(2, rev.getArea());
            stmt.setString(3, rev.getEspecificacao());
        } else if (rev.getTitulo() == null && rev.getEspecificacao() != null && rev.getArea() != null) {
            stmt.setString(1, rev.getArea());
            stmt.setString(2, rev.getEspecificacao());
        }*/
        try {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                retorno.add(new Registro(rs.getInt("registro"),
                        rs.getString("Titulo"),
                        rs.getString("Editora"),
                        rs.getString("Origem"),
                        rs.getString("local"),
                        rs.getString("Area"),
                        rs.getInt("Data")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        stmt.close();
        Conexao.getConexaoMySQL().close();

        return retorno;
    }

    /*public List<Revistas> ListaBuscaRevistaa(String var) throws SQLException {
        List<Revistas> retorno = new ArrayList<Revistas>();

        String SQL = "select * from revista.revista";

        if (var.equals(null)) {
            SQL += " where nome like ?";
        }
        PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

        if (var.equals(null)) {
            stmt.setString(1, var);
        }

        try {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                retorno.add(new Revistas(rs.getInt("id"),
                        rs.getString("Titulo"),
                        rs.getString("Especificacao"),
                        rs.getInt("Quantidade"),
                        rs.getString("Origem"),
                        rs.getInt("Data"),
                        rs.getString("Area")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        stmt.close();
        Conexao.getConexaoMySQL().close();

        return retorno;
    }*/

}
