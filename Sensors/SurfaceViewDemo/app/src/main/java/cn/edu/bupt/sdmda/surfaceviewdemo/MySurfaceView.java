package cn.edu.bupt.sdmda.surfaceviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView  implements SurfaceHolder.Callback {
    float x,y,r,vx,vy;
    boolean isDrawing = false;
    SurfaceHolder holder;
    Paint pen;

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

    public void setR(float r) {
        this.r = r;
    }

    public void setVx(float vx) {
        this.vx = vx;
    }

    public void setVy(float vy) {
        this.vy = vy;
    }

    public void setPen(Paint pen) {
        this.pen = pen;
    }

    public MySurfaceView(Context context) {
        super(context);
        init();
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init() {
        initHolder();
        initValue();
    }

    void initHolder() {
        holder = getHolder();
        holder.addCallback(this);
    }

    void initValue() {
        x = 400;
        y = 400;
        r = 100;
        vx = 30;
        vy = 30;
        pen = new Paint();
        pen.setColor(getContext().getResources().getColor(R.color.colorPrimaryDark));
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        RenderThread rt = new RenderThread();
        isDrawing = true;
        rt.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isDrawing = false;
    }


    class RenderThread extends Thread {
        @Override
        public void run() {
            while (isDrawing) {
                Canvas c = holder.lockCanvas();
                draw(c);
                calcNext();
                holder.unlockCanvasAndPost(c);
            }
        }

        void draw(Canvas c) {
            c.drawColor(Color.WHITE);
            c.drawCircle(x, y, r, pen);
        }

        void calcNext() {
            if (x + vx > getWidth() - r || x < r)
                vx = -vx;
            if (y + vy > getHeight() - r || y < r)
                vy = -vy;
            x += vx;
            y += vy;

        }
    }

}
