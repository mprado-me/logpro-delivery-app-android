package solutions.logpro.msg;

import android.net.Uri;

import java.util.UUID;

/**
 * Created by MarcoAurelio on 22/02/2016.
 */
public abstract class OutMsg {
    private String id;

    public OutMsg(){
        id = UUID.randomUUID().toString();
    }

    public String getId(){
        return id;
    }

    public abstract Uri getUri();
}
