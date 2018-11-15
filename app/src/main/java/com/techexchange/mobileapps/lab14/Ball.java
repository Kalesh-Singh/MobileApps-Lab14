package com.techexchange.mobileapps.lab14;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

public class Ball {
    private static final float GRAVITY = 600f;      // An arbitrary value for gravity
    public Center center;
    public Speed speed;
    private float radius;
    private float screenWidth;
    private float screenHeight;
    private float timeStep;
    private Paint color = new Paint();

    public Ball(Center center, Speed speed, float radius, float screenWidth,
                float screenHeight, float timeStep) {
        this.center = center;
        this.speed = speed;
        this.radius = radius;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.timeStep = timeStep;
        this.color.setColor(randomColor());
    }

    public void updateCenter() {
        center.x += speed.x * timeStep;
        center.y += speed.y * timeStep + (0.5 * GRAVITY * timeStep * timeStep);
        speed.y += GRAVITY * timeStep;
        if (center.x >= screenWidth - radius) {
            speed.x = -speed.x;
            float depth = center.x + radius - screenWidth;
            center.x = screenWidth - radius - depth;
        }
        if (center.y >= screenHeight - radius) {
            speed.y = -speed.y;
            float depth = center.y + radius - screenHeight;
            center.y = screenHeight - radius - depth;
        }
        if (center.x - radius <= 0) {
            speed.x = -speed.x;
            center.x += Math.abs(center.x - radius);
        }
        if (center.y - radius <= 0) {
            speed.y = -speed.y;
            center.y += Math.abs(center.y - radius);
        }
    }

    private int randomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256),
                rnd.nextInt(256), rnd.nextInt(256));
    }

    public void draw(Canvas canvas) {
        // Draws the ball on the canvas
        canvas.drawCircle(center.x, center.y, radius, color);
    }
}
