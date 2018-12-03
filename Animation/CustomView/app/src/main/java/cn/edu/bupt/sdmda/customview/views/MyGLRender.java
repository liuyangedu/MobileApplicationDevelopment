package cn.edu.bupt.sdmda.customview.views;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyGLRender implements GLSurfaceView.Renderer {
    static final String VERTEX_SHADER = "attribute vec4 vPosition;\n"
            + "void main() {\n"
            + "  gl_Position = vPosition;\n"
            + "}";
    static final String FRAGMENT_SHADER = "precision mediump float;\n"
            + "void main() {\n"
            + "  gl_FragColor = vec4(0.5,0,0,1);\n"
            + "}";
    static final float[] VERTEX = {   // in counterclockwise order:
            0, 1, 0.0f, // top
            -0.5f, -1, 0.0f, // bottom left
            1f, -1, 0.0f,  // bottom right
    };

    FloatBuffer mVertexBuffer;

    int mProgram;
    int mPositionHandle;



    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        mVertexBuffer = ByteBuffer.allocateDirect(VERTEX.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer()
                .put(VERTEX);
        mVertexBuffer.position(0);
    }

    @Override
    public void onSurfaceChanged(GL10 unused, int width, int height) {
        mProgram = GLES20.glCreateProgram();
        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, VERTEX_SHADER);
        int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, FRAGMENT_SHADER);
        GLES20.glAttachShader(mProgram, vertexShader);
        GLES20.glAttachShader(mProgram, fragmentShader);
        GLES20.glLinkProgram(mProgram);

        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
    }

    @Override
    public void onDrawFrame(GL10 unused) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        GLES20.glUseProgram(mProgram);

        GLES20.glEnableVertexAttribArray(mPositionHandle);
        GLES20.glVertexAttribPointer(mPositionHandle, 3, GLES20.GL_FLOAT, false,
                12, mVertexBuffer);

        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);

        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }

    int loadShader(int type, String shaderCode) {
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }
}
