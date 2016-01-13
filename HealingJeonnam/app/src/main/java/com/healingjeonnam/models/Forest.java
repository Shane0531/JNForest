package com.healingjeonnam.models;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class Forest implements Serializable{
    int idx;
    public String name;
    String addr;
    String newaddr;
    String page;
    String telephone;
    String city;
    double gpsLatitude;
    double gpsLongitude;
    String introduce;
    public List<String> images;

}
