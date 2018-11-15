package com.techexchange.mobileapps.lab14;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

public class BBBall {
    /*
    private static final float RADIUS = 50.f;
    private float x;
    private float y;
    private float speedX = 100.f;
    private float speedY = 100.f;
    private float screenWidth;
    private float screenHeight;
    private Paint color = new Paint();
    private float timeStep;
    private long delayMs;

    public BBBall(float screenWidth, float screenHeight, long delayMs) {
        // Initializes the ball position and color
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.x = randomX(screenWidth);
        this.y = randomY(screenHeight);
        this.color.setColor(randomColor());
        this.delayMs = delayMs;
        timeStep = this.delayMs / 1000.f;
    }

    public void update() {
        x += speedX * timeStep;
        y += speedY * timeStep;
        if (x >= screenWidth - RADIUS) {
            speedX = -speedX;
            float depth = x + RADIUS - screenWidth;
            x -= 2 * depth;
        }
        if (y >= screenHeight - RADIUS) {
            speedY = -speedY;
            float depth = y + RADIUS - screenHeight;
            y -= 2 * depth;
        }
        if (x - RADIUS < 0) {
            speedX = -speedX;
        }
        if (y - RADIUS < 0) {
            speedY = -speedY;
        }
    }

    public void draw(Canvas canvas) {
        // Draws the ball on the canvas
        canvas.drawCircle(x, y, RADIUS, color);
    }
    */
}
