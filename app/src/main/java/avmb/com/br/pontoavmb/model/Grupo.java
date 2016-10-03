package avmb.com.br.pontoavmb.model;

import com.orm.SugarRecord;

/**
 * Created by guilh on 29/09/2016.
 */

public class Grupo extends SugarRecord{

    private Long id;
    private String nome;
    private String descricao;

    public Grupo() {
    }

    public Grupo(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
