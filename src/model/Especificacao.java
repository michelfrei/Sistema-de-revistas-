
package model;

/**
 *
 * @author Michel
 */
public class Especificacao {
    private int id;
    private String nome;

    public Especificacao(int id, String nome) {
        this.id = id;
        this.nome = nome; 
    }
    
    public Especificacao(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
