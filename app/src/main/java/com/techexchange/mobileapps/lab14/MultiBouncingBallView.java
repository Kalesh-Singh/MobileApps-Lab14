package com.techexchange.mobileapps.lab14;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MultiBouncingBallView extends View {
    private static final String TAG = "BallsView";

    private static final float RADIUS = 50.f;
    private static final int NUM_BALLS = 20;
    private static final float MAX_SPEED = 800.f;
    private static final float MIN_SPEED = 100.f;
    private static final long DELAY_MS = 5;
    private static final float TIME_STEP = DELAY_MS / 1000.f;

    private float screenWidth;
    private float screenHeight;
    private List<Ball> balls;

    public MultiBouncingBallView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (this.balls != null) {
            drawBalls(canvas);
            updateBalls();
        }
        try {
            Thread.sleep(DELAY_MS);
        } catch (InterruptedException e) {
            Log.e(TAG, "Sleep interrupted!", e);
        }
        invalidate();   // Force a redraw
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        this.screenWidth = w;
        this.screenHeight = h;
        this.balls = initializeBalls();
    }

    private void handleCollision(Ball b1, Ball b2) {
        float depth = RADIUS - (distanceBetweenCenters(b1.center, b2.center) / 2.f);
        if (b1.center.x > b2.center.x) {
            b1.center.x += 2 * depth;
            b2.center.x -= 2 * depth;
        } else {
            b1.center.x -= 2 * depth;
            b2.center.x += 2 * depth;
        }
        if (b1.center.y > b2.center.y) {
            b1.center.y += 2 * depth;
            b2.center.y -= 2 * depth;
        } else {
            b1.center.y -= 2 * depth;
            b2.center.y += 2 * depth;
        }

        // Swap the speeds of the two balls that collided.
        Speed temp = b1.speed;
        b1.speed = b2.speed;
        b2.speed = temp;
    }

    private boolean collides(Ball b1, Ball b2) {
        return distanceBetweenCenters(b1.center, b2.center) < (2 * RADIUS);
    }

    private void updateBalls() {
        for (Ball b1 : balls) {
            b1.updateCenter();
            for (Ball b2 : balls) {
                if (collides(b1, b2)) {
                    Log.d(TAG, "Collision!");
                    handleCollision(b1, b2);
                }
            }
        }
    }

    private void drawBalls(Canvas canvas) {
        for (Ball ball : this.balls) {
            ball.draw(canvas);
        }
    }

    private List<Ball> initializeBalls() {
        List<Ball> balls = new ArrayList<>();
        List<Center> centers = getRandomCenters();
        for (Center c : centers) {
            Ball ball = new Ball(c, randomSpeed(), RADIUS, screenWidth, screenHeight, TIME_STEP);
            balls.add(ball);
        }
        return balls;
    }

    private List<Center> getRandomCenters() {
        List<Center> centers = new ArrayList<>();
        while (centers.size() < NUM_BALLS) {
            Center c = randomCenter();
            if (validCenter(centers, c)) {
                centers.add(c);
            }
        }
        return centers;
    }

    private boolean validCenter(List<Center> centers, Center center) {
        for (Center c : centers) {
            if (distanceBetweenCenters(c, center) < (2 * RADIUS)) {
                return false;
            }
        }
        return true;
    }

    private float distanceBetweenCenters(Center c1, Center c2) {
        float xDiff = c1.x - c2.x;
        float yDiff = c1.y - c2.y;
        return (float) Math.sqrt((xDiff * xDiff) + (yDiff * yDiff));
    }

    private Center randomCenter() {
        return new Center(randomX(), randomY());
    }

    private float randomX() {
        float rangeX = screenWidth - (2 * RADIUS);
        return (float) (RADIUS + (Math.random() * rangeX));
    }

    private float randomY() {
        float rangeY = screenHeight - (2 * RADIUS);
        return (float) (RADIUS + (Math.random() * rangeY));
    }

    private Speed randomSpeed() {
        return new Speed(random1DSpeed(), random1DSpeed());
    }

    private float random1DSpeed() {
        float speedRange = MAX_SPEED - MIN_SPEED;
        return (float) (MIN_SPEED +(Math.random() * speedRange));
    }
}
