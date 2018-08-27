
package model;

/**
 *
 * @author Michel
 */
public class Especificacao {
    private int idEspecificacao;
    private String nome;

    public Especificacao(String nome, int idEspecificacao) {
        this.nome = nome;
        this.idEspecificacao = idEspecificacao;
    }

    public int getIdEspecificacao() {
        return idEspecificacao;
    }

    public void setIdEspecificacao(int idEspecificacao) {
        this.idEspecificacao = idEspecificacao;
    }
    
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
