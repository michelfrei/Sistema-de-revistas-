
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
    private int data;
    private String area;
    //classificação
    
    public Revistas(){
    
    }
    
    public Revistas(int ID,String titulo, String especificacao, int quantidade, String origem, int data, String area) {
        this.ID = ID;
        this.titulo = titulo;
        this.especificacao = especificacao;
        this.quantidade = quantidade;
        this.origem = origem;
        this.data = data;
        this.area = area;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
