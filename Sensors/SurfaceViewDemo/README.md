## SurfaceViewDemo

## 1. 整体逻辑

本工程演示了SurfaceView的方法，并完成了一个撞球类小游戏。

1. MainActivity。主活动
2. MySurfaceView。自定义的SurfaceView，显示一个小球，并按照一定的速度运动，碰到墙壁会反弹

## 2. MySurfaceView

SurfaceView的使用中最重要的是SurfaceHolder

1. 使用getHolder获取当前SurfaceView的SurfaceHolder
2. 给SurfaceHolder增加回调函数，关键函数为surfaceCreated，当创建surface时调用。一般在此函数中开启绘制线程
3. 定义绘制线程，根据速度计算小球的位置，并更新小球的速度
4. 开放接口，可以设置小球的位置

# 3. Activity

活动中只需要处理MotionEvnet即可，当触屏时，将触屏事件发生的位置作为小球的中心