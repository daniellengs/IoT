package br.com.dts.webservices.model;

import java.io.Serializable;

/**
 * Created by diegosouza on 8/1/16.
 */
public class Place implements Serializable{

    private String id;

    private PlaceInfo properties;

    private String type;

    private Geometry geometry;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public PlaceInfo getProperties ()
    {
        return properties;
    }

    public void setProperties (PlaceInfo properties)
    {
        this.properties = properties;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public Geometry getGeometry ()
    {
        return geometry;
    }

    public void setGeometry (Geometry geometry)
    {
        this.geometry = geometry;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", properties = "+properties+", type = "+type+", geometry = "+geometry+"]";
    }
}
