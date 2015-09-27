package org.eclipse.paho.android.service.sample;

import android.widget.Switch;

/**
 * Created by Nikhil on 27/09/2015.
 */
public class RowItem {

    private int imageId;
    private String text;
    private Switch sw;

    public RowItem(int imageId, String text, Switch sw) {
        this.imageId = imageId;
        this.text = text;
        this.sw = sw;
    }
    public int getImageId() {
        return imageId;
    }
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public Switch getSwitch() {
        return sw;
    }
    public void setSwitch(Switch sw) {
        this.sw = sw;
    }
}
