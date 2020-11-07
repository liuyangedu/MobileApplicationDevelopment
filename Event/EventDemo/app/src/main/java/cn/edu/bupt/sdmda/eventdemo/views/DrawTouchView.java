package cn.edu.bupt.sdmda.eventdemo.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DrawTouchView extends View {
    ArrayList<TouchData> _data;

    Paint circlePaint;
    float circleRadius;

    public final int[] COLORS = {
            0xFF33B5E5, 0xFFAA66CC, 0xFF99CC00, 0xFFFFBB33, 0xFFFF4444,
            0xFF0099CC, 0xFF9933CC, 0xFF669900, 0xFFFF8800, 0xFFCC0000
    };


    public ArrayList<TouchData> getData() {
        return _data;
    }

    public void setData(MotionEvent event) {
        _data.clear();
        int n = event.getPointerCount();
        for (int i = 0; i < n; ++i) {
            TouchData data = new TouchData(i, event.getX(i), event.getY(i), event.getPressure(i));
            _data.add(data);
        }
    }

    void init() {
        _data = new ArrayList<>();
        circlePaint = new Paint();
        circleRadius = 100;
    }

    public DrawTouchView(Context context) {
        super(context);
        init();
    }

    public DrawTouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawTouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < _data.size(); ++i) {
            circlePaint.setColor(COLORS[i % COLORS.length]);
            canvas.drawCircle(_data.get(i)._x, _data.get(i)._y,
                    circleRadius * _data.get(i)._p * 10, circlePaint);
        }
    }

    static class TouchData {
        int _id;
        float _x, _y;
        float _p;

        public TouchData(int id, float x, float y, float p) {
            _id = id;
            _x = x;
            _y = y;
            _p = p;
        }
    }
}
