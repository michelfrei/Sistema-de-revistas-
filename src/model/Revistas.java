
package model;

import java.util.Date;


/**
 *
 * @author Michel
 */
public class Revistas {
    
    private int ID;
    private String titulo;
    private String especificacao;
    private int quantidade;
    private String origem;
    private String data;
    private String classificacao;
    //classificação
    
    public Revistas(){
    
    }
    
    public Revistas(int ID,String titulo, String especificacao, int quantidade, String origem, String data, String classificacao) {
        this.ID = ID;
        this.titulo = titulo;
        this.especificacao = especificacao;
        this.quantidade = quantidade;
        this.origem = origem;
        this.data = data;
        this.classificacao = classificacao;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }    
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEspecificacao() {
        return especificacao;
    }

    public void setEspecificacao(String especificacao) {
        this.especificacao = especificacao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void getID(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
}
