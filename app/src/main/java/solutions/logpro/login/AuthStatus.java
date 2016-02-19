package solutions.logpro.login;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MarcoAurelio on 18/02/2016.
 */
public enum AuthStatus {
    // !!! IMPORTANTE !!!
    // Ao modificar um dos valores abaixo, certifique-se de que o valor que o servidor retorna
    // tb foi modificado
    NONE(-1),
    NO_CONNECTION(0),
    SUCCESS(1),
    EMAIL_NOT_REGISTERED(2),
    INCORRECT_PASSWORD(3);

    private static Map<Integer, AuthStatus> map = new HashMap<Integer, AuthStatus>();
    private int value;

    private AuthStatus(int value){
        this.value = value;
    }

    static {
        for (AuthStatus authStatus : AuthStatus.values()) {
            map.put(authStatus.value, authStatus);
        }
    }

    public int getIntValue(){
        return this.value;
    }

    public static AuthStatus getEnumValue(int i){
        if(map.containsKey(i))
            return map.get(i);
        return NONE;
    }
}
