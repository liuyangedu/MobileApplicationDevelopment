# EventDemo

## 1. 工程结构

工程包含3个 Activity。

- MainActiviy 实现不同界面的跳转
- EventActivity主要演示KeyEvent和 MotionEvent的传播路径和响应
- TouchViewActivity 演示如何自定义视图，如何利用事件获取数据并绘制出来

本工程重写了八个控件，都在views包中。

1. MyFrameLayout, MyLinearLayout1,MyLinearLayout2, MyEditText1, MyEditText2, MyEditText3, MyEditText4重写了其中和事件相关的函数，并进行输出
2. DrawTouchView 是自定义的控件，能完成绘制触控点的功能

## 2. EventActivity

关于界面布局和细节请参考课件中的内容

## 3. TouchView

DrawTouchView为自定义视图。TouchViewActivity负责显示界面，其布局是在FrameLayout中放置一个DrawTouchView。



DrawTouchView

1. 为了保存触控点信息，自定义类`TouchData`，记录了每一个触控点的id，位置和压力。
2. 函数`setData`以`MotionEvent` 为参数，从中读取出所有触控点的信息，并保存在一个`ArrayList<TouchData>`中。
3. `int[] COLORS`记录了若干颜色，用于绘制时表示不同的触控点
4. `init`函数负责进行数据、画笔、圆半径的初始化，再构造函数中使用
5. `onDraw`函数从`ArrayList<TouchData>`中获取数据，并使用`canvas.drawCircle`函数，结合触控点的位置和压力完成园的绘制

TouchViewActivity

1. 通过`setOnTouchListener`处理MotionEvent。
2. 调用`DrawTouchView`的`setData`函数传递事件信息
3. 调用`DrawTouchView`的`invalidate`使当前视图生效，并重新绘制
4. 函数需要返回true，否则第一个ACTION_DOWN后的事件无法被捕获



