
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Michel
 */
public class Conexao {

public static String status = "Não conectou...";
      public Conexao() {
  }
      
public static java.sql.Connection getConexaoMySQL() {
      Connection connection = null;
        try {
        String driverName = "com.mysql.jdbc.Driver"; //com.mysql.cj.jdbc.Driver
        Class.forName(driverName);

          //String serverName = "localhost";
          //String mydatabase ="sys";
          //String port ="3306";
          //String aux = "?useTimezone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false";
          String url = "jdbc:mysql://localhost:3306/?user=root";
          
//"jdbc:mysql://"+serverName+":"+port +"/" +mydatabase+aux;
          String username = "root";      
          String password = "root";
          connection = DriverManager.getConnection(url, username, password);

          if (connection != null) {
              System.out.println("Conectou");
              status = ("STATUS--->Conectado com sucesso!");
          } else {
              System.out.println("Não conectou");
              status = ("STATUS--->Não foi possivel realizar conexão");
          }
          return connection;
      } catch (ClassNotFoundException e) {
    	  
          System.out.println("O driver expecificado nao foi encontrado.");
          return null;
      } catch (SQLException e) {
          System.out.println("Nao foi possivel conectar ao Banco de Dados.");
          return null;
      }
  }
  public static String statusConection() {
      return status;
  }

public static boolean FecharConexao() {
      try {
    	  Conexao.getConexaoMySQL().close();
          return true;
      } catch (SQLException e) {
          return false;
      }
  } 

public static java.sql.Connection ReiniciarConexao() {
      FecharConexao();
      return Conexao.getConexaoMySQL();
  }
}