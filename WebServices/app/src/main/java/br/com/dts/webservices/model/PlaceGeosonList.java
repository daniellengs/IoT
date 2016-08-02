package br.com.dts.webservices.model;

/**
 * Created by diegosouza on 8/1/16.
 */
public class PlaceGeosonList {

    private Place[] features;

    private String type;

    public Place[] getFeatures ()
    {
        return features;
    }

    public void setFeatures (Place[] features)
    {
        this.features = features;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [features = "+features+", type = "+type+"]";
    }
}
