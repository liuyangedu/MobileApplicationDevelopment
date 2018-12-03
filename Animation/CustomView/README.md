# CustomView

## 1. 整体逻辑

act 目录下共四个 Activity。

- MainActivity 为入口，各界面的跳转。

- MyViewActivity 中演示了如何通过自定义视图实现动画

- MySurfaceViewActivity 中演示了如何通过自定义SurfaceView实现动画

- MyGLSurfaceViewActivity 中演示了如何在安卓中使用 OpenGL

views 目录下为相关的自定义 View。

- MyView继承 View
- MySurfaceView 继承 SurfaceView
- MyGLSurfaceView 继承 GLSurfaceView。MyGLRender 为MyGLSurfaceView中用到的 Render

## 2. 自定义 View

自定义的 View 默认拥有透明的背景，可以在它上面直接绘图。绘图的两个关键函数：

- View 在需要绘制时会调用直接的`onDraw`函数
- 如果 View的`invalidate`函数被调用，则会立马使当前界面无效(invalidate)，并重新绘制(调用`onDraw`)

工程演示了如何在 View 上绘制一个圆，并通过触屏确定圆的中心。

## 3. 自定义 SurfaceView

如果是那种时刻刷新的绘图，继承 View 会导致效率低下，因为 View 是在主线程中绘图。

安卓提供了 SurfaceView，它在单独的线程绘制。

使用 SurfaceView 的几个关键元素：SurfaceHolder，用于触发绘制的回调；一个线程，用于绘制

- 在SurfaceView的构造函数中获取Holder(`getHolder`)，并增加回调(`addCallback`)
- 回调接口中有三个函数。分别对应创建、改变、销毁
- 一般会设置一个布尔型标记位，标识是否正在绘制
- 在创建时启动绘制线程，线程通过判断一个标记位决定是否绘制
- 在销毁时只需要修改标记位即可停止绘制
- `holder.lockCanvas`会返回当前的`Canvas`，注意，它不是空白的，上面包含上一帧的信息。



## 4. 自定义 GLSurfaceView

更加复杂的绘制需要使用 OpenGL。安卓通过GLSurfaceView提供 OpenGL 支持。

它的使用需要具备 OpenGL 的知识，具体代码见工程中的例子。



