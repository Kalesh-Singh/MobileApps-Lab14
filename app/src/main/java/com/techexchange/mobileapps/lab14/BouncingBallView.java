package com.techexchange.mobileapps.lab14;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

public class BouncingBallView extends View {
    private static final String TAG = "BouncingBallView";

    private static long DELAY_MS = 30;
    private static final float TIME_STEP = DELAY_MS / 1000.f;

    private int screenWidth;
    private int screenHeight;

    private Paint ballColor = new Paint();

    private float circleX = 100.f;
    private float circleY = 100.f;
    private float circleR = 100.f;

    private int speedX = 200;
    private int speedY = 100;

    public BouncingBallView(Context context) {
        super(context);
        ballColor.setColor(Color.MAGENTA);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(circleX, circleY, circleR, ballColor);

        update();

        try {
            Thread.sleep(DELAY_MS);
        } catch (InterruptedException e) {
            Log.e(TAG, "Sleep interrupted!", e);
        }
        invalidate();   // Force a redraw
    }

    private void update() {
        circleX += speedX * TIME_STEP;
        circleY += speedY * TIME_STEP;

        if (circleX >= screenWidth - circleR) {
            speedX = -speedX;
            float depth = circleX + circleR - screenWidth;
            circleX = screenWidth - circleR - depth;
        }
        if (circleY >= screenHeight - circleR) {
            speedY = -speedY;
            float depth = circleY + circleR - screenHeight;
            circleY = screenHeight - circleR - depth;
        }
        if (circleX - circleR <= 0) {
            speedX = -speedX;
            circleX += Math.abs(circleX -circleR);
        }
        if (circleY - circleR <= 0) {
            speedY = -speedY;
            circleY += Math.abs(circleY - circleR);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        screenWidth = w;
        screenHeight = h;
    }
}
