package com.example.hitesharora.smartcampus.Emergency;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by hitesharora on 3/5/17.
 */

public class EmergencyItem implements Serializable {

    @SerializedName("title")
    public String Title;

    @SerializedName("description")
    public String Description;

    @SerializedName("content")
    public String Content;
}