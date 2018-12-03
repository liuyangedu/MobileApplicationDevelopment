package cn.edu.bupt.sdmda.customview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import cn.edu.bupt.sdmda.customview.R;

public class MyView extends View {

    float x, y, r;
    Paint pen;

    public MyView(Context context) {
        super(context);
        initValue();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initValue();
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initValue();
    }

    void initValue(){
        x = 400;
        y = 400;
        r = 100;
        pen = new Paint();
        pen.setColor(getContext().getResources().getColor(R.color.colorPrimaryDark));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(x,y,r,pen);
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setR(float r) {
        this.r = r;
    }

    public void setPen(Paint pen) {
        this.pen = pen;
    }
}
