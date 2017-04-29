package com.example.hitesharora.smartcampus.Streaming;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Ravi.Kumar on 4/23/2017.
 */

public class StreamingItem  implements Serializable{

    @SerializedName("id")
    int id;

    @SerializedName("type")
    String type;

    @SerializedName("name")
    String name;

    @SerializedName("url")
    String url;

    @SerializedName("background_image_url")
    String backgroundUrl;
}
