package br.com.chicobentojr.coverdiscover.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Francisco on 24/05/2016.
 */
public class Album implements Serializable {
    public String album_type;
    public String href;
    public String id;
    public List<Image> images;
    public String name;
    public String type;
    public String uri;
}
