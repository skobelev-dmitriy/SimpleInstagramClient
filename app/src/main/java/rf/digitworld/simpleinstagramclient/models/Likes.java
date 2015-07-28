package rf.digitworld.simpleinstagramclient.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дмитрий on 13.07.2015.
 */
public class Likes {

    @Expose
    private int count;
    @Expose
    private List<User> data = new ArrayList<User>();

    /**
     *
     * @return
     *     The count
     */
    public int getCount() {
        return count;
    }

    /**
     *
     * @param count
     *     The count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     *
     * @return
     *     The data
     */
    public List<User> getData() {
        return data;
    }

    /**
     *
     * @param data
     *     The data
     */
    public void setData(List<User> data) {
        this.data = data;
    }

}

