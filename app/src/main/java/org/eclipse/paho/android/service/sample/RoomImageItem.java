package org.eclipse.paho.android.service.sample;

import android.graphics.Bitmap;

public class RoomImageItem {
    private Bitmap image;
    private String title;

    public RoomImageItem(Bitmap image, String title) {
        super();
        this.image = image;
        this.title = title;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
