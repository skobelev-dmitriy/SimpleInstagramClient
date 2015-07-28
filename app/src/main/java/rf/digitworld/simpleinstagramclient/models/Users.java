package rf.digitworld.simpleinstagramclient.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дмитрий on 13.07.2015.
 */
public class Users {
    @Expose
    private Meta meta;
    @Expose
    private List<User> data = new ArrayList<User>();

    public String getMeta() {
        return String.valueOf(meta.getCode());
    }
    public void setMeta(Meta meta) {
        this.meta = meta;
    }
    public List<User> getData() {
        return data;
    }
    public void setData(List<User> data) {
        this.data = data;
    }



    }

