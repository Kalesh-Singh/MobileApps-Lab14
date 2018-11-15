package com.techexchange.mobileapps.lab14;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

public class Ball {
    private Center center;
    private Speed speed;
    private float radius;
    private Paint color = new Paint();

    public Ball(Center center, Speed speed, float radius) {
        this.center = center;
        this.speed = speed;
        this.radius = radius;
        this.color.setColor(randomColor());
    }

    private int randomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    public void draw(Canvas canvas) {
        // Draws the ball on the canvas
        canvas.drawCircle(center.x, center.y, radius, color);
    }
}
