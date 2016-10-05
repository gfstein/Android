package avmb.com.br.pontoavmb.model;

import com.orm.SugarRecord;

/**
 * Created by guilh on 03/10/2016.
 */

public class Item extends SugarRecord{

    private Long id;
    private String nome;
    private Float preco;
    private Grupo grupo;
    private Integer qtd;

    public Item() {
    }

    public Item(String nome, Float preco, Grupo grupo, Integer qtd) {
        this.nome = nome;
        this.preco = preco;
        this.grupo = grupo;
        this.qtd = qtd;
    }

    @Override
    public String toString() {
        return nome + " - " + preco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }
}
