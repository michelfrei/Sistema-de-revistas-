
package model;

import java.util.Date;
import java.util.Objects;


/**
 *
 * @author Michel
 */
public class Registro {
    
    private int registro;
    private String titulo;
    private String Editora;
    private String origem;
    private String local;
    private String area;
    private int ano;
    //classificação
    
    public Registro(){
    
    }

    public Registro(int registro, String titulo, String Editora, String origem, String local, String area, int ano) {
        this.registro = registro;
        this.titulo = titulo;
        this.Editora = Editora;
        this.origem = origem;
        this.local = local;
        this.area = area;
        this.ano = ano;
    }
    public Registro(String titulo, String area, int ano, String origem){
        this.titulo = titulo;
        this.area = area;
        this.ano = ano;
        this.origem = origem;
    }

    public int getRegistro() {
        return registro;
    }

    public void setRegistro(int registro) {
        this.registro = registro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return Editora;
    }

    public void setEditora(String Editora) {
        this.Editora = Editora;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
}
