package com.tenz.tenzmusic.widget.snow;

import com.tenz.tenzmusic.R;
import com.tenz.tenzmusic.util.ResourceUtil;

public class Snow {

    private float x;
    private float y;
    private int size;
    private int color = ResourceUtil.getColor(R.color.app_color);

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
