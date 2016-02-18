package solutions.logpro.utils;

/**
 * Created by MarcoAurelio on 17/02/2016.
 */
public class Consts {
    public static class Login{
        public static final int FORGET_SUCCESSFUL_LOGIN_TIME = 45; // Em minutos

        // !!! IMPORTANTE !!!
        // Ao modificar um dos valores abaixo, certifique-se de que o valor que o servidor retorna
        // tb foi modificado
        public static final int NO_CONNECTION = 0;
        public static final int SUCCESS = 1;
        public static final int EMAIL_NOT_REGISTERED = 2;
        public static final int INCORRECT_PASSWORD = 3;
    }

}
