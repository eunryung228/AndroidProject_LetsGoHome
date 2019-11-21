package com.example.letsgohome;

import android.widget.Button;

public class ListViewItem
{
    private String region;
    private String station;
    private Button deleteBtn;

    public void setStation(String station)
    {
        this.station = station;
    }
    public void setRegion(String region)
    {
        this.region = region;
    }
    public String getStation()
    {
        return this.station;
    }
    public String getRegion()
    {
        return region;
    }
}