
package model;

/**
 *
 * @author Michel
 */
public class Area {
    private int id;
    private String nome;

    public Area(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    public Area(){
        
    }

    public int getId() {
        return id;
    }

    public void setIdClasse(int id) {
        this.id = id;
    }
    
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
