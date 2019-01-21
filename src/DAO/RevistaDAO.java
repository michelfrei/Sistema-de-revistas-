package DAO;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import model.Registro;

import model.Revistas;

/**
 *
 * @author Michel
 */
public class RevistaDAO {
    public Registro registro;

    public void InserirNovaRevistas(Revistas rev) throws SQLException {
        String SQL = "INSERT INTO revista.revista (id, Titulo, Especificacao, Quantidade, Origem, Data, Area) values (?, ?, ?, ?, ?, ?, ?)";

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
    }

    public void RemoverRevista(Revistas rev) throws SQLException {
        String SQL = "Delete from revista.revista where id=?";

        PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
        stmt.setInt(1, rev.getID());

        stmt.execute();
        stmt.close();
    }

    public void AlterarRevista(Revistas rev) throws SQLException {
        String SQL = SQL = "update revista.revista set Titulo=?, Especificacao=?, Origem=?, Data=?, Area=?, Quantidade=? where id = ?";

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
    }
    
    public Revistas retornoRegistro (String titulo) throws SQLException {
        String SQL = "SELECT * FROM Registro WHERE titulo = ?";
        
        PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
        stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);
       /* 
        try {

            stmt.setString(1, titulo);

            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()){
                Registro reg = new Registro();
                reg.setId(id);
                a.setNome( resultado.getString("nome"));
                a.setEmail(  resultado.getString("email"));
                a.setCpf( resultado.getString("cpf"));
                return a;
            }

        } catch (Exception ex) {
            System.out.println("Azedo o cu do piru");
        } finally {
            new Conexao().FecharConexao();    
        }*/
        return null;
    } 
    /*public Agenda selecionar(int id){
        Connection c = new Conexao().criarConexao();
        String sql = "SELECT * FROM agenda WHERE id=?";
        try {
            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1, id);

            ResultSet resultado = p.executeQuery();

            if (resultado.next()){
                Agenda a = new Agenda();
                a.setId(id);
                a.setNome( resultado.getString("nome"));
                a.setEmail(  resultado.getString("email"));
                a.setCpf( resultado.getString("cpf"));
                return a;
            }

        } catch (SQLException ex) {
            Logger.getLogger(GerenciaAgenda.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            new Conexao().fecharConexao(c);    
        }
        return null;
    }*/
    

    public ArrayList<Revistas> ListaRevista() throws SQLException {

        ArrayList<Revistas> ListaRevista;
        ListaRevista = new ArrayList<>();

        String SQL = "select* from revista.revista order by id ASC";
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

        String SQL = "select * from revista.revista ";

        if (rev.getTitulo() != null && rev.getEspecificacao() == null && rev.getArea() == null) {
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
    }

    public List<Revistas> ListaBuscaRevistaa(String var) throws SQLException {
        List<Revistas> retorno = new ArrayList<Revistas>();

        String SQL = "select * from revista.revista";

        if (var.equals(null)) {
            SQL += " where nome like ?";
        }
        PreparedStatement stmt = Conexao.getConexaoMySQL().prepareStatement(SQL);

        if (var.equals(null)) {
            stmt.setString(1, var);
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        stmt.close();
        Conexao.getConexaoMySQL().close();

        return retorno;
    }

}
