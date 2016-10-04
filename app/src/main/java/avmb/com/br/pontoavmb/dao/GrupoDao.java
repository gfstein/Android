package avmb.com.br.pontoavmb.dao;

import avmb.com.br.pontoavmb.model.Grupo;

/**
 * Created by guilh on 04/10/2016.
 */

public class GrupoDao {

    public static Grupo findById(Grupo grupo){
        return Grupo.findById(Grupo.class, grupo.getId());
    }

}
