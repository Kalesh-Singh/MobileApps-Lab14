package com.techexchange.mobileapps.lab14;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.View;

public class MultiBouncingBallView extends View {
    private static final String TAG = "MultiBouncingBallView";
    private static final int NUM_BALLS = 20;

    private static long DELAY_MS = 300;

    private int screenWidth;
    private int screenHeight;
    Ball[] balls;

    public MultiBouncingBallView(Context context) {
        super(context);
    }

    private void initializeBalls() {
        balls = new Ball[NUM_BALLS];
        for (int i = 0; i < NUM_BALLS; ++i) {
            balls[i] = new Ball(screenWidth, screenHeight, DELAY_MS);
        }
    }

    private void drawBalls(Canvas canvas) {
        for (Ball ball : balls) {
            ball.draw(canvas);
        }
    }

    private void updateBalls() {
        for (Ball ball : balls) {
            ball.update();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawBalls(canvas);
        updateBalls();

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

        screenWidth = w;
        screenHeight = h;
        initializeBalls();
    }
}
