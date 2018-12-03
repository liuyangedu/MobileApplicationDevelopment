package cn.edu.bupt.sdmda.customview.views;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class MyGLSurfaceView extends GLSurfaceView {

    MyGLRender render;

    public MyGLSurfaceView(Context context) {
        super(context);
        init();
    }

    public MyGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    void init() {
        setEGLContextClientVersion(2);
        render = new MyGLRender();
        setRenderer(render);
    }
}
