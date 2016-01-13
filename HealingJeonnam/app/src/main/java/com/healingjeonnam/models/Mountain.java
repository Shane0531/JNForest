package com.healingjeonnam.models;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * Created by econo110 on 2015-10-28.
 */
@Data
public class Mountain implements Serializable {
    int idx;
    public String name;
    String addr;
    String city;
    double gpsLatitude;
    double gpsLongitude;
    int height;
    String introduce;
    public List<String> images;
}
