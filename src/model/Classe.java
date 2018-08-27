
package model;

/**
 *
 * @author Michel
 */
public class Classe {
    private int idClasse;
    private String nome;

    public Classe(String nome, int idClasse) {
        this.nome = nome;
        this.idClasse = idClasse;
    }

    public int getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(int idClasse) {
        this.idClasse = idClasse;
    }
    
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
