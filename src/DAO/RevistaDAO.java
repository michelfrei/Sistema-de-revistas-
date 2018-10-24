package DAO;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.Revistas;

/**
 *
 * @author Michel
 */
public class RevistaDAO {

    public boolean InserirNovaRevistas(Revistas rev) {
        String SQL = "INSERT INTO sys.revista (id, Titulo, Especificacao, Quantidade, Origem, Data, Area) values (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

            stmt.setInt(1, 0);
            stmt.setString(2, rev.getTitulo());
            stmt.setString(3, rev.getEspecificacao());
            stmt.setInt(4, rev.getQuantidade());
            stmt.setString(5, rev.getOrigem());
            stmt.setInt(6, rev.getData());
            stmt.setString(7, rev.getArea());

            stmt.execute();
            stmt.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return false;
    }

    public boolean RemoverRevista(Revistas rev) throws SQLException {
        String SQL = "Delete from sys.revista where id=?";

        try {
            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
            stmt.setInt(1, rev.getID());

            stmt.execute();
            stmt.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return true;
    }

    public boolean AlterarRevista(Revistas rev) throws SQLException {// não ta funcionando
        String SQL = SQL = "update sys.revista set Titulo=?, Especificacao=?, Origem=?, Data=?, Area=?, Quantidade=? where id = ?";

        try {
            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
            stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

            stmt.setString(1, rev.getTitulo());
            stmt.setString(2, rev.getEspecificacao());
            stmt.setString(3, rev.getOrigem());
            stmt.setInt(4, rev.getData());
            stmt.setString(5, rev.getArea());
            stmt.setInt(6, rev.getQuantidade());
            stmt.setInt(7, rev.getID());
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return true;
    }

    public List<Revistas> ListaRevista() throws SQLException {

        List<Revistas> ListaRevista;
        ListaRevista = new ArrayList<>();

        String SQL = "select* from sys.revista order by id DESC";
        try {

            PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ListaRevista.add(new Revistas(rs.getInt("id"),
                        rs.getString("Titulo"),
                        rs.getString("Especificacao"),
                        rs.getInt("Quantidade"),
                        rs.getString("Origem"),
                        rs.getInt("Data"),
                        rs.getString("Area")));

            }
        } catch (Exception e) {
            System.out.println("Problema tal:");
            System.out.println(e.getMessage());
        }
        return ListaRevista;

    }

    public List<Revistas> ListaBuscaRevista(Revistas rev) throws SQLException {
        List<Revistas> retorno = new ArrayList<Revistas>();

        String SQL = "select * from sys.revista ";

        if (rev.getTitulo() != null && rev.getEspecificacao() == null && rev.getArea() == null) {
            SQL += " where Titulo like ? order by id DESC";
        } else if (rev.getArea() != null && rev.getTitulo() == null && rev.getEspecificacao() == null) {
            SQL += " where Area like ? order by id DESC";
        } else if (rev.getArea() == null && rev.getTitulo() == null && rev.getEspecificacao() != null) {
            SQL += " where Especificacao like ? order by id DESC";
        } else if (rev.getTitulo() != null && rev.getEspecificacao() == null && rev.getArea() != null) {
            SQL += " where Titulo like ? and Area like ? order by id DESC";
        } else if (rev.getTitulo() != null && rev.getEspecificacao() != null && rev.getArea() == null) {
            SQL += " where Titulo like ? and Especificacao like ? order by id DESC";
        } else if (rev.getTitulo() != null && rev.getEspecificacao() != null && rev.getArea() != null) {
            SQL += " where Titulo like ? and Area like ? and Especificacao like ? order by id DESC";
        } else if (rev.getTitulo() == null && rev.getEspecificacao() != null && rev.getArea() != null) {
            SQL += " where Area like ? and Especificacao like ? order by id DESC";
        }

        PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

        if (rev.getTitulo() != null && rev.getEspecificacao() == null && rev.getArea() == null) {
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
        }
        try{
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
        }catch(Exception e){
            System.out.println("Problema tal:");
            System.out.println(e.getMessage());
        }

        stmt.close();
        Conexao.getConexaoMySQL().close();

        return retorno;
    }

}
