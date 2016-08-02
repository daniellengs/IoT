package br.com.dts.webservices.model;

import java.io.Serializable;

/**
 * Created by diegosouza on 8/1/16.
 */
public class PlaceInfo implements Serializable{

    private String PTurist;

    private String Descritv;

    private String Id;

    private String OBJECTID;

    public String getPTurist ()
    {
        return PTurist;
    }

    public void setPTurist (String PTurist)
    {
        this.PTurist = PTurist;
    }

    public String getDescritv ()
    {
        return Descritv;
    }

    public void setDescritv (String Descritv)
    {
        this.Descritv = Descritv;
    }

    public String getId ()
    {
        return Id;
    }

    public void setId (String Id)
    {
        this.Id = Id;
    }

    public String getOBJECTID ()
    {
        return OBJECTID;
    }

    public void setOBJECTID (String OBJECTID)
    {
        this.OBJECTID = OBJECTID;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [PTurist = "+PTurist+", Descritv = "+Descritv+", Id = "+Id+", OBJECTID = "+OBJECTID+"]";
    }
}
