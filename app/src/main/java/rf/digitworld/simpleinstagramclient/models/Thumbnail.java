package rf.digitworld.simpleinstagramclient.models;

import com.google.gson.annotations.Expose;

/**
 * Created by Дмитрий on 13.07.2015.
 */
public class Thumbnail {

    @Expose
    private String url;
    @Expose
    private int width;
    @Expose
    private int height;

    /**
     *
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return
     *     The width
     */
    public int getWidth() {
        return width;
    }

    /**
     *
     * @param width
     *     The width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     *
     * @return
     *     The height
     */
    public int getHeight() {
        return height;
    }

    /**
     *
     * @param height
     *     The height
     */
    public void setHeight(int height) {
        this.height = height;
    }

}
