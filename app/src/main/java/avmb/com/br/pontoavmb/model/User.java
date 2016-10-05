package avmb.com.br.pontoavmb.model;

import com.orm.SugarRecord;

/**
 * Created by guilh on 05/10/2016.
 */

public class User extends SugarRecord {

    private Double saldo = 0D;

    public User(Double saldo) {
        this.saldo = saldo;
    }

    public User() {
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
}
