package avmb.com.br.pontoavmb.service;

import avmb.com.br.pontoavmb.model.Grupo;

/**
 * Created by guilh on 04/10/2016.
 */

public class GrupoService {

    public static void save(Grupo grupo){
        grupo.save();
    }

}
